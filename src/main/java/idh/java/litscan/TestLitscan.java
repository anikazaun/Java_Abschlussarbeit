package idh.java.litscan;

import java.util.List;

import idh.java.litscan.impl.Tokenizer;

public class TestLitscan {

    public static void main(String[] args) {
	Tokenizer tokenizer = new Tokenizer();

	String text = "Hallo Welt! Das ist ein Test, 123.";
	List<Token> tokens = tokenizer.tokenize(text);

	System.out.println("Tokenisierungsergebnis:");
	for (Token t : tokens) {
	    System.out.println(t + " -> \"" + t.getCoveredText() + "\"");
	}
    }
}
