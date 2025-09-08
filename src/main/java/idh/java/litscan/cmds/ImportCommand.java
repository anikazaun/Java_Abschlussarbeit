package idh.java.litscan.cmds;

import java.io.File;
import java.io.FilenameFilter;

import idh.java.litscan.ICorpusDocument;
import idh.java.litscan.LitScan;

public class ImportCommand extends AbstractCommand {

    public ImportCommand(LitScan mainApp) {
	super(mainApp);
    }

    public static String commandTerm = "import";

    @Override
    public boolean verify(String[] args) {
	// check general things
	if (args.length < 2 || args.length > 3)
	    return false;
	if (!args[0].equalsIgnoreCase(commandTerm))
	    return false;

	// check argument 1
	String a1 = args[1];
	File file = new File(a1);
	if (!file.isDirectory())
	    return false;
	if (!file.canRead())
	    return false;

	// everything okay
	return true;
    }

    @Override
    public void execute(String[] args) {
	File corpusDirectory = new File(args[1]);
	String corpusName = corpusDirectory.getName();
	if (args.length == 3)
	    corpusName = args[2];
	int counter = 0;
	for (File file : corpusDirectory.listFiles(new FilenameFilter() {
	    @Override
	    public boolean accept(File dir, String name) {
		return name.endsWith(".xml") || name.endsWith(".txt");
	    }
	})) {

	    try {
		ICorpusDocument cd = null;

		if (file.getName().endsWith(".xml")) {
		    cd = getMainApplication().getFileImporter().importXMLFile(file);
		} else if (file.getName().endsWith(".txt")) {
		    cd = getMainApplication().getFileImporter().importTXTFile(file);
		}
		if (cd != null) {
		    getMainApplication().getImportedCorpus(corpusName).add(cd);
		    counter++;
		}

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	;
	log("Imported " + String.valueOf(counter) + " files in corpus " + corpusName + ".");
    }

}
