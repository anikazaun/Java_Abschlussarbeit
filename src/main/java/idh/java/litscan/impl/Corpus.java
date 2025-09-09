package idh.java.litscan.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import idh.java.litscan.ICorpus;
import idh.java.litscan.ICorpusDocument;
import idh.java.litscan.Result;
import idh.java.litscan.Token;

public class Corpus implements ICorpus {

    private String name;
    private List<ICorpusDocument> documents;
    private Set<String> types; // alle verschiedenen Wörter

    public Corpus(String corpusName) {
	this.name = corpusName;
	this.documents = new java.util.ArrayList<>();
	this.types = new java.util.HashSet<>();
    }

    @Override
    public String getName() {
	return this.name;
    }

    @Override
    public void setName(String n) {
	this.name = n;
    }

    @Override
    public void add(ICorpusDocument document) {
	if (document != null) {
	    documents.add(document);
	}
    }

    @Override
    public Set<Result> search(String query, boolean ci) {
	Set<Result> results = new java.util.HashSet<>();

	if (query == null || query.isEmpty()) {
	    return results; // leere Suchanfrage → leeres Ergebnis
	}

	// Suchbegriff je nach Case-Insensitivity vorbereiten
	String searchTerm = ci ? query.toLowerCase() : query;

	for (ICorpusDocument doc : documents) {
	    if (doc.getTokens() == null)
		continue; // Sicherheitscheck

	    for (Token t : doc.getTokens()) {
		String tokenText = t.getCoveredText();
		if (ci)
		    tokenText = tokenText.toLowerCase();

		if (tokenText.equals(searchTerm)) {
		    results.add(new Result(doc, t));
		}
	    }
	}

	return results;
    }

    @Override
    public int getDocumentCount() {
	return documents.size();
    }

    @Override
    public int getTokenCount() {
	int count = 0;
	for (ICorpusDocument doc : documents) {
	    count += doc.getTokens().size();
	}
	return count;
    }

    @Override
    public int getTypeCount() {
	Set<String> allTypes = new HashSet<>();
	for (ICorpusDocument doc : documents) {
	    allTypes.addAll(doc.getTypes());
	}
	return allTypes.size();
    }

    @Override
    public double getTypeTokenRatio() {
	int tokens = getTokenCount();
	if (tokens == 0)
	    return 0;
	return (double) getTypeCount() / tokens;
    }

    @Override
    public double getAverageTokenCountPerDocument() {
	if (documents.isEmpty())
	    return 0;
	return (double) getTokenCount() / documents.size();
    }

    @Override
    public List<ICorpusDocument> getDocumentsSortedByTokenCount() {
	// Erstelle eine Kopie der Liste, damit die Original-Liste unverändert bleibt
	List<ICorpusDocument> sortedDocs = new java.util.ArrayList<>(documents);

	// Sortiere die Kopie nach Tokenanzahl
	sortedDocs.sort((d1, d2) -> Integer.compare(d2.getTokens().size(), d1.getTokens().size()));

	return sortedDocs;
    }

}
