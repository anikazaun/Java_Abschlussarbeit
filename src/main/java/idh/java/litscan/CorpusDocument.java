package idh.java.litscan;

import java.util.ArrayList;
import java.util.List;

import idh.java.litscan.ex.InvalidOperationException;

public class CorpusDocument implements ICorpusDocument {

    // ====================
    // Attribute (Felder)
    // ====================
    private String title;
    private String id;
    private String textContent;
    private List<Token> tokens = new ArrayList<>();
    private List<String> types = new ArrayList<>();

    // ====================
    // Konstruktor(en)
    // ====================
    public CorpusDocument() {
	// Leerer Konstruktor
    }

    // ====================
    // Getter & Setter
    // ====================

    @Override
    public String getTitle() {
	return title;
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
    public String getTextContent() {
	return textContent;
    }

    @Override
    public void setTextContent(String textContent) throws InvalidOperationException {
	if (this.textContent != null) {
	    throw new InvalidOperationException("Text content already set!");
	}
	this.textContent = textContent;
    }

    @Override
    public void setId(String id) throws InvalidOperationException {
	if (this.id != null) {
	    throw new InvalidOperationException("ID already set!");
	}
	this.id = id;
    }

    @Override
    public void setTokens(List<Token> tokens) {
	this.tokens = tokens;

	// Types automatisch berechnen:
	List<String> uniqueTypes = new ArrayList<>();
	for (Token t : tokens) {
	    String surface = t.getCoveredText();
	    if (!uniqueTypes.contains(surface)) {
		uniqueTypes.add(surface);
	    }
	}
	this.types = uniqueTypes;
    }

    @Override
    public List<Token> getTokens() {
	return tokens;
    }

    @Override
    public List<String> getTypes() {
	return types;
    }

    @Override
    public double getTTR() {
	if (tokens.isEmpty()) {
	    return 0.0;
	}
	return (double) types.size() / tokens.size();
    }

    @Override
    public int compareTo(ICorpusDocument other) {
	// Beispiel: Vergleich anhand der Tokenanzahl
	return Integer.compare(this.tokens.size(), other.getTokens().size());
    }

    // Für Debugging: Dokument als String ausgeben
    @Override
    public String toString() {
	return "CorpusDocument [id=" + id + ", title=" + title + ", textLength="
		+ (textContent != null ? textContent.length() : 0) + "]";
    }
}
