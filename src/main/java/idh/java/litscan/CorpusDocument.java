package idh.java.litscan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import idh.java.litscan.ex.InvalidOperationException;

public class CorpusDocument implements ICorpusDocument {

    private String title;
    private String id;
    private String textContent;
    private List<Token> tokens = new ArrayList<>();
    private List<String> types = new ArrayList<>();

    public CorpusDocument() {
	// tokens und types sind schon initialisiert
    }

    @Override
    public String getTitle() {
	return (title != null) ? title : "Unbekannt";
    }

    @Override
    public void setTitle(String title) {
	this.title = title;
    }

    @Override
    public String getId() {
	return id;
    }

    @Override
    public void setId(String id) throws InvalidOperationException {
	if (this.id != null)
	    throw new InvalidOperationException("ID already set!");
	this.id = id;
    }

    @Override
    public String getTextContent() {
	return textContent;
    }

    @Override
    public void setTextContent(String textContent) throws InvalidOperationException {
	if (this.textContent != null)
	    throw new InvalidOperationException("Text already gesetzt!");
	this.textContent = textContent;
	tokenizeText(); // automatische Tokenisierung beim Setzen
    }

    @Override
    public List<Token> getTokens() {
	return tokens;
    }

    @Override
    public List<String> getTypes() {
	return types;
    }

    private void tokenizeText() {
	tokens.clear();
	types.clear();
	Set<String> uniqueTypes = new HashSet<>();

	if (textContent != null && !textContent.isEmpty()) {
	    String[] words = textContent.split("\\s+");
	    for (String w : words) {
		String clean = w.replaceAll("[^a-zA-ZäöüÄÖÜß]", "");
		if (!clean.isEmpty()) {
		    tokens.add(new Token(clean));
		    uniqueTypes.add(clean.toLowerCase());
		}
	    }
	}
	types.addAll(uniqueTypes);
    }

    @Override
    public void setTokens(List<Token> tokens) {
	// TODO Auto-generated method stub

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
