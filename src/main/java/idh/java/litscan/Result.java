package idh.java.litscan;

public class Result {

    int begin;
    int end;
    ICorpusDocument corpusDocument;

    public Result(int begin, int end, ICorpusDocument corpusDocument) {
	super();
	this.begin = begin;
	this.end = end;
	this.corpusDocument = corpusDocument;
    }

    public Result(ICorpusDocument doc, Token token) {
	// TODO Auto-generated constructor stub
    }

    public int getBegin() {
	return begin;
    }

    public void setBegin(int begin) {
	this.begin = begin;
    }

    public int getEnd() {
	return end;
    }

    public void setEnd(int end) {
	this.end = end;
    }

    public ICorpusDocument getCorpusDocument() {
	return corpusDocument;
    }

    public void setCorpusDocument(ICorpusDocument corpusDocument) {
	this.corpusDocument = corpusDocument;
    }

    public Token getToken() {
	// TODO Auto-generated method stub
	return null;
    }
}
