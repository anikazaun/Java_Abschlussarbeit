package idh.java.litscan;

import java.util.List;
import java.util.Set;

/**
 * The {@code ICorpus} interface defines the core structure and functionality of
 * a corpus within the LitScan framework. A corpus is a collection of text
 * documents ({@link ICorpusDocument}) that can be imported, indexed, and
 * analyzed.
 * <p>
 * Implementations of this interface should manage corpus-level metadata (such
 * as name and size) and provide methods for adding documents, executing
 * full-text searches, and retrieving various corpus-level statistics.
 * <p>
 * Additionally, the interface allows for retrieval of individual documents
 * sorted by token count, which can be useful for statistical analyses and
 * diagnostics.
 *
 * <p>
 * <b>Responsibilities of an implementation include:</b>
 * </p>
 * <ul>
 * <li>Maintaining a collection of documents and updating internal statistics
 * upon addition.</li>
 * <li>Supporting keyword-based search functionality (with optional
 * case-insensitivity).</li>
 * <li>Providing access to aggregate information like token and type counts, as
 * well as derived statistics.</li>
 * <li>Ensuring consistency and integrity of document indexing and name
 * management.</li>
 * </ul>
 *
 * @see ICorpusDocument
 * @see Result
 * @author hermesj, nreiter, bkiss
 */
public interface ICorpus {

    /**
     * Returns the name of the corpus. The currently loaded corpus is also displayed
     * in the command line user interface of CorpEx.
     */
    String getName();

    /**
     * Sets the name of the corpus. Should only be called once.
     */
    void setName(String n);

    /**
     * Adds a single new {@CorpusDocument}-object to the corpus. This method in
     * particular also registers the document in the search index.
     */
    void add(ICorpusDocument document);

    /**
     * Executes a search within the corpus.
     */
    Set<Result> search(String query, boolean ci);

    /**
     * Returns the number of documents in the corpus.
     */
    int getDocumentCount();

    /**
     * Returns the total number of tokens across all documents.
     */
    int getTokenCount();

    /**
     * Returns the number of distinct tokens (types) in the corpus.
     */
    int getTypeCount();

    /**
     * Returns the type-token ratio (types / tokens).
     */
    double getTypeTokenRatio();

    /**
     * Returns the average number of tokens per document.
     */
    double getAverageTokenCountPerDocument();

    /**
     * Returns a list of documents, sorted by their token number.
     * 
     * @return
     */
    List<ICorpusDocument> getDocumentsSortedByTokenCount();

}
