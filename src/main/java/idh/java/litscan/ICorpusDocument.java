package idh.java.litscan;

import java.util.List;

import idh.java.litscan.ex.InvalidOperationException;

/**
 * The {@code ICorpusDocument} interface defines the structure and essential
 * operations for a single document within a corpus in the LitScan framework.
 * Each document stores metadata (title, ID), raw text content, and tokenized
 * representations of its content.
 *
 * <p>
 * Implementations of this interface should ensure immutability of core
 * identifiers (such as ID and text content) once they are set, in order to
 * preserve consistency within the corpus and index.
 * </p>
 *
 * <p>
 * <b>Responsibilities of an implementation include:</b>
 * </p>
 * <ul>
 * <li>Managing document metadata (ID, title).</li>
 * <li>Providing access to the raw text and its tokenized form.</li>
 * <li>Computing and exposing statistics such as type-token ratio (TTR).</li>
 * <li>Ensuring safe mutation rules, e.g., ID and text content can only be set
 * once.</li>
 * <li>Supporting ordering based on token count or other criteria via
 * {@code compareTo}.</li>
 * </ul>
 *
 * <p>
 * This interface also plays a key role in indexing, searching, and statistical
 * analysis at the corpus level.
 * </p>
 *
 * @see Token
 * @see InvalidOperationException
 * 
 * @author hermesj, nreiter, bkiss
 */
public interface ICorpusDocument extends Comparable<ICorpusDocument> {

    /**
     * The title of the document.
     * 
     * @return
     */
    String getTitle();

    /**
     * Set the title of the document.
     * 
     * @param title
     */
    void setTitle(String title);

    /**
     * Get the id value of the document.
     * 
     * @return
     */
    String getId();

    /**
     * Get the text content of the document.
     * 
     * @return
     */
    String getTextContent();

    /**
     * Sets the text content of the document. This can only be done once.
     * 
     * @param textContent
     * @throws InvalidOperationException If a text content has already been set.
     */
    void setTextContent(String textContent) throws InvalidOperationException;

    /**
     * Sets the id of the document. This can only be done once.
     * 
     * @param id
     * @throws InvalidOperationException If an id has already been set.
     */
    void setId(String id) throws InvalidOperationException;

    /**
     * Sets the tokens of the corpus document. Might be a good place to set the
     * types too...
     * 
     * @param tokens
     */
    void setTokens(List<Token> tokens);

    /**
     * Returns tokens as a List of Tokens
     * 
     * @return
     */
    List<Token> getTokens();

    /**
     * Returns the types as a List of Strings
     * 
     * @return
     */
    List<String> getTypes();

    /**
     * Returns the type-token-ratio, defined as types per token.
     * 
     * @return
     */
    double getTTR();

    @Override
    int compareTo(ICorpusDocument o);

}