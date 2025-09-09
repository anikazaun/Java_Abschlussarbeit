package idh.java.litscan.impl;

import java.util.ArrayList;
import java.util.List;

import idh.java.litscan.ITokenizer;
import idh.java.litscan.Token;
import idh.java.litscan.ex.InvalidTokenBoundariesException;

public class Tokenizer implements ITokenizer {

    @Override
    public List<Token> tokenize(String text) {
	List<Token> tokens = new ArrayList<>();
	if (text == null || text.isEmpty()) {
	    return tokens;
	}

	int start = -1;
	for (int i = 0; i < text.length(); i++) {
	    char c = text.charAt(i);

	    // Trennzeichen: alles außer Buchstaben und Zahlen
	    if (!Character.isLetterOrDigit(c)) {
		if (start != -1) {
		    try {
			tokens.add(new Token(text, start, i));
		    } catch (InvalidTokenBoundariesException e) {
			e.printStackTrace();
		    }
		    start = -1;
		}
	    } else {
		if (start == -1) {
		    start = i; // Token beginnt hier
		}
	    }
	}

	// Letztes Token hinzufügen, falls Text nicht mit Trennzeichen endet
	if (start != -1) {
	    try {
		tokens.add(new Token(text, start, text.length()));
	    } catch (InvalidTokenBoundariesException e) {
		e.printStackTrace();
	    }
	}

	return tokens;
    }
}
