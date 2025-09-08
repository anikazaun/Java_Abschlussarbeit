package idh.java.litscan.cmds;

import idh.java.litscan.ICorpus;
import idh.java.litscan.LitScan;

public class StatsCommand extends AbstractCommand {

    public static String commandTerm = "stats";

    public StatsCommand(LitScan mainApp) {
	super(mainApp);
    }

    @Override
    public boolean verify(String[] args) {
	// nur Parameter name
	return args.length == 1;
    }

    @Override
    public void execute(String[] args) {
	ICorpus corpus = getMainApplication().getCurrentCorpus();

	if (corpus == null) {
	    log("No corpus loaded.");
	    return;
	}

	int docCount = corpus.getDocumentCount();
	int tokenCount = corpus.getTokenCount();
	int typeCount = corpus.getTypeCount();
	double ttr = corpus.getTypeTokenRatio();
	double avgTokens = corpus.getAverageTokenCountPerDocument();

	log("Global corpus stats:");
	log("Document count: " + docCount);
	log("Token count: " + tokenCount);
	log("Type count: " + typeCount);
	log(String.format("Type-Token-Ratio: %.3f", ttr));
	log(String.format("Average count of tokens per document: %.2f", avgTokens));
    }
}
