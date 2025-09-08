package idh.java.litscan;

import java.io.File;
import java.io.IOException;

import idh.java.litscan.ex.InvalidFileException;

/**
 * The {@code IFileImporter} interface defines methods for importing external
 * text data into the system as {@link ICorpusDocument} objects.
 * 
 * <p>
 * It supports two types of file formats:
 * <ul>
 * <li><b>XML files</b>, which require parsing to extract all text content that
 * is not enclosed within tags</li>
 * <li><b>Plain text files (TXT)</b>, which are read in their entirety as raw
 * text</li>
 * </ul>
 * 
 * <p>
 * Each method must validate that the input file exists and is readable
 * <em>before</em> attempting to parse its content. If the file is missing,
 * inaccessible, or invalid, appropriate exceptions must be thrown.
 * </p>
 * 
 * <p>
 * The returned {@link ICorpusDocument} should be properly initialized with text
 * content, and optionally metadata such as document ID or title, if
 * extractable.
 * </p>
 * 
 * 
 * @see ICorpusDocument
 * @see InvalidFileException
 * 
 * @author hermesj, nreiter, bkiss
 */
public interface IFileImporter {

    /**
     * This method gets a {@link File}-object as argument, and returns a object of
     * the class {@link ICorpusDocument}.
     * 
     * An implementation of this method <b>must</b> check that the file exists and
     * is readable. If not, it throws an IOException. Ideally, this check occurs
     * before any time-consuming reading attempts are done.
     * 
     * <br/>
     * The method further employs an XML parser to extract the text content of the
     * input file. Any XML parser can be used here, as long as it's able to return
     * the <b>entire</b> textual content of the document. The textual content of the
     * document consists of all characters that are not part of an XML tag. E.g.,
     * the textual content of
     * <code>&lt;bla number="7"&gt;blubb&nbsp;&nbsp;blobb&lt;/bla&gt;</code> would
     * be <code>blubb&nbsp;&nbsp;blobb</code> (note the two space characters in the
     * middle.
     * 
     * @see ICorpusDocument
     * 
     * @param inputFile
     * @return
     * @throws IOException
     * @throws InvalidFileException
     */
    ICorpusDocument importXMLFile(File inputFile) throws IOException, InvalidFileException;

    /**
     * This method gets a {@link File}-object as argument, and returns a object of
     * the class {@link ICorpusDocument}.
     * 
     * An implementation of this method <b>must</b> check that the file exists and
     * is readable. If not, it throws an IOException. Ideally, this check occurs
     * before any time-consuming reading attempts are done.
     * 
     * <br/>
     * The method further employs an TXT reader to extract the text content of the
     * input file.
     * 
     * @see ICorpusDocument
     * 
     * @param inputFile
     * @return
     * @throws IOException
     * @throws InvalidFileException
     */
    ICorpusDocument importTXTFile(File inputFile) throws IOException, InvalidFileException;
}
