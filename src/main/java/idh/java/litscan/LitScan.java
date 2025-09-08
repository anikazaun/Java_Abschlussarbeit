package idh.java.litscan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import idh.java.litscan.cmds.DocStatsCommand;
import idh.java.litscan.cmds.ICommand;
import idh.java.litscan.cmds.ImportCommand;
import idh.java.litscan.cmds.LoadCommand;
import idh.java.litscan.cmds.SearchCommand;
import idh.java.litscan.cmds.StatsCommand;
import idh.java.litscan.impl.Corpus;
import idh.java.litscan.impl.FileImporter;
import idh.java.litscan.impl.Tokenizer;

public class LitScan {

    /**
     * Constants to store property names
     */
    public static String KEY_CONTEXT_SIZE = "KEY_CONTEXT_SIZE";
    public static String KEY_RESULTS_ON_SINGLE_LINE = "KEY_RESULTS_ON_SINGLE_LINE";

    /**
     * The store for different corpora
     */
    Map<String, ICorpus> corpora = new HashMap<String, ICorpus>();

    /**
     * The currently loaded corpus
     */
    ICorpus currentCorpus = null;

    /**
     * Configuration options for the whole application
     */
    Properties properties = new Properties();

    /**
     * Returns a (new) instance of ITokenizer
     * 
     * @return
     */
    public ITokenizer getTokenizer() {
	return new Tokenizer();
    }

    /**
     * Returns a (new) isntance of IFileImporter
     * 
     * @return
     */
    public IFileImporter getFileImporter() {
	return new FileImporter();
    }

    /**
     * Returns a (new) instance of ICorpus, with the provided name as corpus name.
     * 
     * @param corpusName The name of the new corpus.
     * @return
     */
    public ICorpus getCorpus(String corpusName) {
	return new Corpus(corpusName);
    }

    /**
     * Retrieves a corpus that has already been imported. If it doesn't exist, the
     * method creates a new corpus using {@link #getCorpus(String)}.
     * 
     * @param corpusName The name of the corpus. Can be any string.
     * @return
     */
    public ICorpus getImportedCorpus(String corpusName) {
	if (!this.corpora.containsKey(corpusName))
	    this.corpora.put(corpusName, this.getCorpus(corpusName));
	return this.corpora.get(corpusName);
    }

    /**
     * Main method to start the application.
     * 
     * @param args No arguments accepted.
     */
    public static void main(String[] args) {
	LitScan ce = new LitScan();
	ce.run();
    }

    /**
     * Interprets the command entered by a user. The method expects the command to
     * be split and provided as argument args.
     * 
     * @param args
     */
    private void command(String[] args) {
	ICommand subProgram = null;

	// go over the different possible commands
	if (args[0].equalsIgnoreCase(ImportCommand.commandTerm)) {
	    subProgram = new ImportCommand(this);
	} else if (args[0].equalsIgnoreCase(SearchCommand.commandTerm)) {
	    subProgram = new SearchCommand(this);
	} else if (args[0].equalsIgnoreCase(LoadCommand.commandTerm)) {
	    subProgram = new LoadCommand(this);
	} else if (args[0].equalsIgnoreCase(StatsCommand.commandTerm)) {
	    subProgram = new StatsCommand(this);
	} else if (args[0].equalsIgnoreCase(DocStatsCommand.commandTerm)) {
	    subProgram = new DocStatsCommand(this);
	}

	// Prüfen, ob ein Kommando erkannt wurde
	if (subProgram == null) {
	    System.out.println("Unknown command: " + args[0]);
	    return;
	}

	// if the command thinks the user input is valid, we execute it
	if (subProgram.verify(args)) {
	    subProgram.execute(args);
	} else {
	    System.out.println("Invalid parameters for: " + args[0]);
	}
    }

    /**
     * Main UI loop. Shows a prompt to the user and retrieves their input. Splits
     * the input on white space and passes the result to {@link #command(String[])}.
     */
    private void run() {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	while (true) {
	    try {
		StringBuilder b = new StringBuilder();
		if (getCurrentCorpus() != null)
		    b.append(getCurrentCorpus().getName());
		b.append("> ");
		System.out.print(b.toString());
		String command = br.readLine();
		command(command.split(" +"));
	    } catch (Exception e) {
		e.printStackTrace();
		break;
	    }
	}
    }

    /**
     * 
     * @return access to the properties of the whole application
     */
    public Properties getProperties() {
	return properties;
    }

    /**
     * 
     * @return A reference to the currently loaded corpus
     */
    public ICorpus getCurrentCorpus() {
	return currentCorpus;
    }

    /**
     * 
     * @return A set of all imported corpus names
     */
    public Set<String> getCorpusNames() {
	return this.corpora.keySet();
    }

    /**
     * Change the currently loaded corpus
     * 
     * @param currentEngine
     */
    public void setCurrentCorpus(ICorpus currentEngine) {
	this.currentCorpus = currentEngine;
    }

    /**
     * Change the currently loaded corpus
     * 
     * @param corpusName
     */
    public void setCurrentCorpus(String corpusName) {
	this.currentCorpus = this.getImportedCorpus(corpusName);
    }

}
