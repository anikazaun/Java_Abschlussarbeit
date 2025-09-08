package idh.java.litscan.cmds;

import java.util.List;

import idh.java.litscan.ICorpus;
import idh.java.litscan.ICorpusDocument;
import idh.java.litscan.LitScan;

public class DocStatsCommand extends AbstractCommand {
    public static String commandTerm = "docstats";

    public DocStatsCommand(LitScan mainApp) {
	super(mainApp);
    }

    @Override
    public boolean verify(String[] args) {
	// Nur parameter name
	return args.length == 1 && args[0].equals("docstats") && getMainApplication().getCurrentCorpus() != null;
    }

    @Override
    public void execute(String[] args) {
	ICorpus corpus = getMainApplication().getCurrentCorpus();
	if (corpus == null) {
	    log("No corpus loaded.");
	    return;
	}

	log("Document stats of corpus: " + corpus.getName());
	log("----------------------------------------");

	List<ICorpusDocument> documents = corpus.getDocumentsSortedByTokenCount();

	for (ICorpusDocument doc : documents) {
	    log(String.format("%s | Tokens: %d | Types: %d | TTR: %.2f", doc.getTitle(), doc.getTokens().size(),
		    doc.getTypes().size(), doc.getTTR()));
	}
    }
}
