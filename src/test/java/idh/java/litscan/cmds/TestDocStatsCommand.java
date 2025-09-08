package idh.java.litscan.cmds;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import idh.java.litscan.ICorpus;
import idh.java.litscan.LitScan;
import idh.java.litscan.Token;
import idh.java.litscan.cmds.DocStatsCommand;
import idh.java.litscan.ex.InvalidTokenBoundariesException;
import idh.java.litscan.impl.CorpusDocument;

public class TestDocStatsCommand {

    DocStatsCommand cmd;
    LitScan litscan;
    ICorpus corpus;

    @BeforeEach
    void setUp() {
	litscan = mock(LitScan.class);
	corpus = mock(ICorpus.class);
	cmd = new DocStatsCommand(litscan);
    }

    @Test
    void testVerify() {
	assertFalse(cmd.verify(new String[] {}));
	assertFalse(cmd.verify(new String[] { "bla" }));
	assertFalse(cmd.verify(new String[] { "docstats", "extra" }));

	when(litscan.getCurrentCorpus()).thenReturn(null);
	assertFalse(cmd.verify(new String[] { "docstats" }));

	when(litscan.getCurrentCorpus()).thenReturn(corpus);
	assertTrue(cmd.verify(new String[] { "docstats" }));
    }

    @Test
    void testExecute() throws InvalidTokenBoundariesException {
	CorpusDocument doc1 = mock(CorpusDocument.class);
	when(doc1.getId()).thenReturn("doc1.txt");
	String entireText = "The cat sat";
	when(doc1.getTokens()).thenReturn(Arrays.asList(new Token(entireText, 0, 3), // "The"
		new Token(entireText, 4, 7), // "cat"
		new Token(entireText, 8, 11) // "sat"
	));
	when(doc1.getTypes()).thenReturn(Arrays.asList("The", "cat", "sat"));
	when(doc1.getTTR()).thenReturn(1.0);
	when(litscan.getCurrentCorpus()).thenReturn(corpus);
	when(corpus.getName()).thenReturn("TestCorpus");
	when(corpus.getDocumentsSortedByTokenCount()).thenReturn(List.of(doc1));

	cmd.execute(new String[] { "docstats" });

	verify(doc1, atLeastOnce()).getTokens();
    }
}
