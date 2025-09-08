package idh.java.litscan.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;

import idh.java.litscan.ICorpus;
import idh.java.litscan.ICorpusDocument;
import idh.java.litscan.Result;
import idh.java.litscan.impl.Corpus;

public class TestCorpus {
    ICorpus corpus;
    ICorpusDocument doc;

    @BeforeEach
    public void setUp() throws Exception {
	corpus = new Corpus("testcorpus");
	doc = mock(ICorpusDocument.class);
	String textContent = "\n\nsome title\n\nThis is a a text.\n";
	when(doc.getTextContent()).thenReturn(textContent);
	when(doc.getId()).thenReturn("Test Id");
	doNothing().when(doc).setTokens(any());

	corpus.add(doc);
    }

    @Test
    public void testMetaData() {
	assertNotNull(corpus.getName());
	assertEquals("testcorpus", corpus.getName());
    }

    @Test
    public void testRegularCSSearch() {
	Set<Result> res = corpus.search("This", false);
	assertNotNull(res);
	assertEquals(1, res.size());

	Result r = res.stream().findFirst().get();
	assertNotNull(r);
	assertEquals(14, r.getBegin());
	assertEquals(18, r.getEnd());
    }

    @Test
    @EnabledIf("optionalTask")
    public void testRegularCISearch() {
	Set<Result> res;
	Result r;

	res = corpus.search("This", true);
	assertNotNull(res);
	assertEquals(1, res.size());

	r = res.stream().findFirst().get();
	assertNotNull(r);
	assertEquals(14, r.getBegin());
	assertEquals(18, r.getEnd());

	res = corpus.search("this", true);
	assertNotNull(res);
	assertEquals(1, res.size());

	r = res.stream().findFirst().get();
	assertNotNull(r);
	assertEquals(14, r.getBegin());
	assertEquals(18, r.getEnd());

    }

    @Test
    public void testStatistics() {
	assertEquals(1, corpus.getDocumentCount());
	assertEquals(7, corpus.getTokenCount());
	assertEquals(6, corpus.getTypeCount());
	assertEquals(6.0 / 7, corpus.getTypeTokenRatio(), 0.0001);
	assertEquals(7.0, corpus.getAverageTokenCountPerDocument(), 0.0001);

	List<ICorpusDocument> docs = corpus.getDocumentsSortedByTokenCount();
	assertEquals(1, docs.size());
	assertSame(doc, docs.get(0));
    }

    boolean optionalTask() {
	return true;
    }

}
