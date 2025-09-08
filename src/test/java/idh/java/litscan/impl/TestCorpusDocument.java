package idh.java.litscan.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import idh.java.litscan.ICorpusDocument;
import idh.java.litscan.Token;
import idh.java.litscan.ex.InvalidOperationException;
import idh.java.litscan.ex.InvalidTokenBoundariesException;
import idh.java.litscan.impl.CorpusDocument;

public class TestCorpusDocument {

    ICorpusDocument doc;

    @BeforeEach
    public void setUp() {
	doc = new CorpusDocument();
    }

    @Test
    public void testSetAndGetTitle() {
	doc.setTitle("Mein Titel");
	assertEquals("Mein Titel", doc.getTitle());
    }

    @Test
    public void testSetAndGetId() throws Exception {
	doc.setId("doc-123");
	assertEquals("doc-123", doc.getId());

	// Setzen des Id-Feldes ein zweites Mal soll Exception werfen
	InvalidOperationException ex = assertThrows(InvalidOperationException.class, () -> {
	    doc.setId("doc-456");
	});
	assertTrue(ex.getMessage().contains("cannot be reset"));
    }

    @Test
    public void testSetAndGetTextContent() throws Exception {
	String text = "Dies ist ein Text.";
	doc.setTextContent(text);
	assertEquals(text, doc.getTextContent());

	// Ein zweites Setzen soll Exception werfen
	InvalidOperationException ex = assertThrows(InvalidOperationException.class, () -> {
	    doc.setTextContent("Neuer Text");
	});
	assertTrue(ex.getMessage().contains("cannot be reset"));
    }

    @Test
    public void testSetAndGetTokensAndTypes() throws InvalidTokenBoundariesException {
	List<Token> tokens = new ArrayList<>();
	String entireString = "This is a test test";
	tokens.add(new Token(entireString, 0, 4));
	tokens.add(new Token(entireString, 5, 7));
	tokens.add(new Token(entireString, 8, 9));
	tokens.add(new Token(entireString, 10, 14));
	tokens.add(new Token(entireString, 15, 19)); // "test" zweimal

	doc.setTokens(tokens);

	List<Token> actualTokens = doc.getTokens();
	assertEquals(tokens.size(), actualTokens.size());
	assertEquals("This", actualTokens.get(0).getCoveredText());

	List<String> types = doc.getTypes();
	assertEquals(4, types.size()); // "This", "is", "a", "test"
	assertTrue(types.contains("test"));
	assertTrue(types.contains("a"));

	// TTR = tokens.size / types.size = 5 / 4 = 1.25
	assertEquals(1.25, doc.getTTR(), 0.0001);
    }

    @Test
    public void testCompareTo() throws InvalidTokenBoundariesException {
	List<Token> tokens1 = new ArrayList<>();
	String entirestring = "one two";
	tokens1.add(new Token(entirestring, 0, 3));
	tokens1.add(new Token(entirestring, 4, 7));
	doc.setTokens(tokens1);
	CorpusDocument other = new CorpusDocument();
	entirestring = "one";
	List<Token> tokens2 = new ArrayList<>();
	tokens2.add(new Token(entirestring, 0, 3));
	other.setTokens(tokens2);
	assertTrue(doc.compareTo(other) > 0);
	assertTrue(other.compareTo(doc) < 0);
	CorpusDocument equalDoc = new CorpusDocument();
	equalDoc.setTokens(tokens1);
	assertEquals(0, doc.compareTo(equalDoc));
    }
}
