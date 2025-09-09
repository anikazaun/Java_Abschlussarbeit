package idh.java.litscan.impl;

import java.util.ArrayList;
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

    public Corpus(String corpusName) {
	this.name = corpusName;
	this.documents = new ArrayList<>();
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
	Set<Result> results = new HashSet<>();
	if (query == null || query.isEmpty())
	    return results;

	for (ICorpusDocument doc : documents) {
	    if (doc.getTokens() == null)
		continue;

	    for (Token token : doc.getTokens()) {
		String tokenText = token.getCoveredText();
		if (tokenText == null)
		    continue;

		boolean match = ci ? tokenText.equalsIgnoreCase(query) : tokenText.equals(query);
		if (match) {
		    results.add(new Result(doc, token));
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
	List<ICorpusDocument> sorted = new ArrayList<>(documents);
	sorted.sort((d1, d2) -> Integer.compare(d2.getTokens().size(), d1.getTokens().size()));
	return sorted;
    }

}
