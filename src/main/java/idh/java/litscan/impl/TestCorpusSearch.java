package idh.java.litscan.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import idh.java.litscan.Result;
import idh.java.litscan.Token;

public class TestCorpusSearch {

    public static void main(String[] args) throws Exception {

	// 1. Corpus erstellen
	Corpus corpus = new Corpus("MeinTestCorpus");

	// 2. Dummy-Dokument erstellen
	CorpusDocument doc = new CorpusDocument();
	doc.setId("doc1");
	doc.setTitle("Testdokument");
	String text = "Das ist ein einfacher Test. Testen wir die Suche.";
	doc.setTextContent(text);

	// 3. Tokens manuell erstellen (für Testzwecke)
	List<Token> tokens = new ArrayList<>();
	String[] words = text.split(" ");
	int pos = 0;
	for (String w : words) {
	    // Token mit Positionen im Originaltext
	    tokens.add(new Token(text, pos, pos + w.length()));
	    pos += w.length() + 1; // +1 wegen Leerzeichen
	}
	doc.setTokens(tokens);

	// 4. Dokument zum Corpus hinzufügen
	corpus.add(doc);

	// 5. Suche testen
	Set<Result> results = corpus.search("Test", true); // Case-insensitive Suche

	// 6. Ergebnisse ausgeben
	System.out.println("Gefundene Treffer:");
	for (Result r : results) {
	    System.out.println(r.getCorpusDocument().getTitle() + " -> " + r.getToken().getCoveredText());
	}
    }
}
