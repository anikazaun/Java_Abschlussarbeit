package idh.java.litscan;

import idh.java.litscan.impl.Corpus;
import idh.java.litscan.impl.CorpusDocument; // wir nehmen unsere eigene Klasse

public class TestLitscan {
    public static void main(String[] args) throws Exception {
	Corpus corpus = new Corpus("MeinKorpus");

	// Testdokumente erstellen
	ICorpusDocument doc1 = new CorpusDocument();
	doc1.setId("1");
	doc1.setTextContent("Hallo Welt!");

	ICorpusDocument doc2 = new CorpusDocument();
	doc2.setId("2");
	doc2.setTextContent("Java Programmierung");

	// Dokumente hinzufügen
	corpus.add(doc1);
	corpus.add(doc2);

	// Testausgabe
	System.out.println("Korpusname: " + corpus.getName());
	System.out.println("Anzahl Dokumente: " + corpus.getDocumentCount());
    }
}
