package idh.java.litscan.impl;

import java.util.List;
import java.util.Set;

import idh.java.litscan.CorpusDocument;
import idh.java.litscan.ICorpusDocument;
import idh.java.litscan.Result;

public class TestCorpusSearch {

    public static void main(String[] args) throws Exception {

	Corpus corpus = new Corpus("MeinTestCorpus");

	// Ein Dokument erstellen, Tokenisierung erfolgt automatisch
	CorpusDocument doc = new CorpusDocument();
	doc.setId("doc1");
	doc.setTitle("Testdokument");
	doc.setTextContent("Das ist ein einfacher Test. Testen wir die Suche.");

	corpus.add(doc);

	// Suche testen
	Set<Result> results = corpus.search("Test", true);

	System.out.println("Gefundene Treffer:");
	if (results.isEmpty()) {
	    System.out.println("Keine Treffer gefunden.");
	} else {
	    for (Result r : results) {
		System.out.println(r.getCorpusDocument().getTitle() + " -> " + r.getToken().getCoveredText());
	    }
	}

	// Dokumente nach Tokenanzahl sortiert ausgeben
	System.out.println("\nDokumente sortiert nach Tokenanzahl:");
	List<ICorpusDocument> sortedDocs = corpus.getDocumentsSortedByTokenCount();
	for (ICorpusDocument d : sortedDocs) {
	    System.out.println(d.getTitle() + " -> Tokens: " + d.getTokens().size());
	}
    }
}
