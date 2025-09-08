package idh.java.litscan.impl;

import java.util.List;
import java.util.Set;

import idh.java.litscan.ICorpus;
import idh.java.litscan.ICorpusDocument;
import idh.java.litscan.Result;

public class Corpus implements ICorpus {

    public Corpus(String corpusName) {
	// TODO Auto-generated constructor stub
    }

    @Override
    public String getName() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setName(String n) {
	// TODO Auto-generated method stub

    }

    @Override
    public void add(ICorpusDocument document) {
	// TODO Auto-generated method stub

    }

    @Override
    public Set<Result> search(String query, boolean ci) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public int getDocumentCount() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public int getTokenCount() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public int getTypeCount() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public double getTypeTokenRatio() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public double getAverageTokenCountPerDocument() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public List<ICorpusDocument> getDocumentsSortedByTokenCount() {
	// TODO Auto-generated method stub
	return null;
    }

}
