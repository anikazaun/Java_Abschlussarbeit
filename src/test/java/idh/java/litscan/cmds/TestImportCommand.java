package idh.java.litscan.cmds;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import idh.java.litscan.ICorpus;
import idh.java.litscan.IFileImporter;
import idh.java.litscan.LitScan;
import idh.java.litscan.cmds.ImportCommand;
import idh.java.litscan.ex.InvalidFileException;
import idh.java.litscan.impl.CorpusDocument;

public class TestImportCommand {
    static String IMPORT = "import";

    ImportCommand cmd;
    LitScan corpex;
    ICorpus corpus;
    IFileImporter importer;

    @BeforeEach
    void setUp() throws IOException, InvalidFileException {
	corpex = mock(LitScan.class);
	corpus = mock(ICorpus.class);
	importer = mock(IFileImporter.class);
	when(corpex.getImportedCorpus(anyString())).thenReturn(corpus);
	when(corpex.getFileImporter()).thenReturn(importer);
	when(importer.importXMLFile(any(File.class))).thenReturn(mock(CorpusDocument.class));
	when(importer.importTXTFile(any(File.class))).thenReturn(mock(CorpusDocument.class));
	cmd = new ImportCommand(corpex);
    }

    @Test
    void testVerify() {
	assertFalse(cmd.verify(new String[] {}), "Empty array");
	assertFalse(cmd.verify(new String[] { "bla" }), "Wrong command name");
	assertFalse(cmd.verify(new String[] { IMPORT, "NotExistingFilename" }), "Import from file that does not exist");
	assertFalse(cmd.verify(new String[] { IMPORT, "pom.xml" }), "Import from something that's not a directory");
	assertTrue(cmd.verify(new String[] { IMPORT, "target" }), "Successful verification");
    }

    @Test
    /**
     * This test produces console output.
     */
    void testExecute() throws IOException, InvalidFileException {
	cmd.execute(new String[] { IMPORT, "src/test/resources/xml" });
	verify(importer, times(6)).importXMLFile(any(File.class));
	verify(corpus, times(6)).add(any(CorpusDocument.class));
	cmd.execute(new String[] { IMPORT, "src/test/resources/txt" });
	verify(importer, times(1)).importTXTFile(any(File.class));
	verify(corpus, times(7)).add(any(CorpusDocument.class));
    }
}