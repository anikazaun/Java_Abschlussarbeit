package idh.java.litscan.impl;

import java.util.List;

import idh.java.litscan.ICorpusDocument;
import idh.java.litscan.Token;
import idh.java.litscan.ex.InvalidOperationException;

/**
 * This class represents a single document within our corpus. A document
 * consists of a text (basically a string), a title and an id value. The id
 * value <b>must be unique</b> within our corpus.
 *
 */
public class CorpusDocument implements ICorpusDocument {

    @Override
    public String getTitle() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setTitle(String title) {
	// TODO Auto-generated method stub

    }

    @Override
    public String getId() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getTextContent() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setTextContent(String textContent) throws InvalidOperationException {
	// TODO Auto-generated method stub

    }

    @Override
    public void setId(String id) throws InvalidOperationException {
	// TODO Auto-generated method stub

    }

    @Override
    public void setTokens(List<Token> tokens) {
	// TODO Auto-generated method stub

    }

    @Override
    public List<Token> getTokens() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<String> getTypes() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public double getTTR() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public int compareTo(ICorpusDocument o) {
	// TODO Auto-generated method stub
	return 0;
    }

}
