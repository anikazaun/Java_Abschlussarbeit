package idh.java.litscan.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import idh.java.litscan.ITokenizer;
import idh.java.litscan.LitScan;
import idh.java.litscan.Token;

public class TestTokenizer {
    ITokenizer tokenizer;

    @BeforeEach
    public void setUp() {
	tokenizer = new LitScan().getTokenizer();
    }

    @Test
    public void testNormalText() {
	String text = "this is a test.";
	List<Token> tokenList = tokenizer.tokenize(text);

	Iterator<Token> tokenIter = tokenList.iterator();
	Token token;
	assertTrue(tokenIter.hasNext());
	token = tokenIter.next();
	assertEquals("this", text.substring(token.getBegin(), token.getEnd()));
	assertTrue(tokenIter.hasNext());
	token = tokenIter.next();
	assertEquals("is", text.substring(token.getBegin(), token.getEnd()));
	assertTrue(tokenIter.hasNext());
	token = tokenIter.next();
	assertEquals("a", text.substring(token.getBegin(), token.getEnd()));
	assertTrue(tokenIter.hasNext());
	token = tokenIter.next();
	assertEquals("test.", text.substring(token.getBegin(), token.getEnd()));
	assertFalse(tokenIter.hasNext());
    }

    @Test
    public void testEmptyText() {
	String text = "";
	List<Token> tokenList = tokenizer.tokenize(text);
	assertTrue(tokenList.isEmpty());
    }

    @Test
    public void testTextWithWhitespace() {
	String text = " test ";
	List<Token> tokenList = tokenizer.tokenize(text);
	assertFalse(tokenList.isEmpty());
	assertEquals(1, tokenList.size());
	assertEquals("test", tokenList.get(0).getCoveredText());

	text = "    		 test  		 ";
	tokenList = tokenizer.tokenize(text);
	assertFalse(tokenList.isEmpty());
	assertEquals(1, tokenList.size());
	assertEquals("test", tokenList.get(0).getCoveredText());
    }

}
