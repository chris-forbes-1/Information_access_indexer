package searcher;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;

import java.io.*;

import javax.management.Query;

@SuppressWarnings({ "deprecation", "unused" })
/**
 * 
 * @author cforbes2012
 *
 */
public class Searcher_ {
	/**
	 * The only real differences between limited and unbounded searches is that
	 * the unbounded searches use @see Integer.MAX_VALUE which is approxamatley
	 * 2^31 -1 possible entries (our data set doesn't have that many words)
	 * <p>
	 * Please use only the provided enum values for the searchtype parameter it
	 * is public so you can access it.
	 * 
	 * @param String
	 *            searchtypein this is the enum value discussed above
	 * @param String
	 *            searchTerm (the term / terms you want to search for)
	 * @param String
	 *            TextLocation (you need to pass which part of the index you
	 *            want to search most commonly the body)
	 * @param int numberOfReferences this is part of the overloaded header and
	 *        should only be used to cut down the size of the search
	 * @author cforbes2012
	 * @throws IOException
	 * @throws SearchError
	 * @return org.apache.lucene.search.TopDocs;
	 */

	/*
	 * IMPLEMENTED FOR TESTING PURPOSES ONLY
	 */
	// public static void main(String[] args) throws IOException, SearchError {
	// Searcher_ s = new Searcher_();
	//
	// TopDocs dd = s.search("LimitedBasic", "java", "body", 10);
	// System.out.println("TOTAL HITS FOR LIMITED BASIC:" + dd.totalHits);
	// dd = s.search("LimitedWildCard", "jav*", "body", 10);
	// System.out.println("TOTAL HITS FOR LIMITEDWILDCARD:" + dd.totalHits);
	// dd = s.search("LimitedFuzzyQuery", "jav", "body", 10);
	// System.out.println("TOTAL HITS FOR LIMITEDFUZZYQUERY:" + dd.totalHits);
	// }

	/*
	 * END MAIN REMOVE AFTER TESTING
	 */

	/**
	 * Public enum values for search types please use only these or else we will
	 * end up with undefined behaviour
	 * 
	 * @author cforbes2012
	 * 
	 */
	public static enum searchType {
		Basic, LimitedBasic, WildCard, FuzzyQuery, LimitedWildCard, LimitedFuzzyQuery
	}

	private static Directory dir;
	private static IndexSearcher searcher_;

	/**
	 * Setup call as the main method will be removed
	 * 
	 * @throws IOException
	 */
	public Searcher_() throws IOException {
		File indexFileLoc = new File("IndexFiles");
		dir = new SimpleFSDirectory(indexFileLoc);
		searcher_ = new IndexSearcher(dir);
	}

	/**
	 * Used for cutting down number of hits you are required to specify how many
	 * hits you want of N possible hits
	 * <p>
	 * All Searches are essentially the same so the total hits should be
	 * identical the main differences is how many documents are actually
	 * returned to you.
	 * 
	 * @param searchTypeIn
	 * @param SearchTerm
	 * @param textLocation
	 * @param number_of_references
	 * @return TopDocs
	 * @throws SearchError
	 */
	public TopDocs search(String searchTypeIn, String SearchTerm,
			String textLocation, int number_of_references) throws SearchError {
		TopDocs tds = null;
		switch (searchType.valueOf(searchTypeIn)) {
		case LimitedBasic:
			try {
				tds = LimitedBasic(SearchTerm, textLocation,
						number_of_references);
			} catch (IOException e1) {
				throw new SearchError("404");
			}
			break;
		case LimitedWildCard:
			try {
				tds = LimitedWildCardSearch(SearchTerm, textLocation,
						number_of_references);
			} catch (IOException e1) {
				throw new SearchError("404");
			}
			break;
		case LimitedFuzzyQuery:
			try {
				tds = LimitedFuzzySearch(SearchTerm, textLocation,
						number_of_references);
			} catch (IOException e1) {
				throw new SearchError("404");
			}
			break;
		default:
			try {
				tds = LimitedBasic(SearchTerm, textLocation,
						number_of_references);
			} catch (IOException e) {
				throw new SearchError("404");
			}
			break;
		}
		return tds;
	}

	/**
	 * This is for unbounded searches and will return 2^31 -1 possible searches
	 * if available (max integer value)
	 * <p>
	 * All Searches are essentially the same so the total hits should be
	 * identical the main differences is how many documents are actually
	 * returned to you.
	 * 
	 * @param searchTypeIn
	 * @param SearchTerm
	 * @param textLocation
	 * @return TopDocs
	 * @throws SearchError
	 */
	public TopDocs search(String searchTypeIn, String SearchTerm,
			String textLocation) throws SearchError {
		TopDocs tds = null;
		switch (searchType.valueOf(searchTypeIn)) {
		case Basic:
			try {
				tds = BasicSearch(SearchTerm, textLocation);
			} catch (IOException e) {
				throw new SearchError("404");
			}
			break;
		case WildCard:
			try {
				tds = WildCardSearch(SearchTerm, textLocation);
			} catch (IOException e1) {
				throw new SearchError("404");
			}
			break;
		case FuzzyQuery:
			try {
				tds = FuzzySearch(SearchTerm, textLocation);
			} catch (IOException e1) {
					throw new SearchError("404");
			}
			break;
		default:
			try {
				tds = BasicSearch(SearchTerm, textLocation);
			} catch (IOException e) {
				throw new SearchError("404");
			}
			break;
		}
		return tds;
	}

	/**
	 * Private method called from limitedSearch for use with regex searches
	 * 
	 * @param searchterm
	 * @param textLocaiton
	 * @param number_of_references
	 * @return TopDocs
	 * @throws IOException
	 */
	private TopDocs LimitedWildCardSearch(String searchterm,
			String textLocaiton, int number_of_references) throws IOException {
		Term term = new Term(textLocaiton, searchterm);
		WildcardQuery wc = new WildcardQuery(new Term(textLocaiton, searchterm));
		TopDocs tds = searcher_.search(wc, number_of_references);
		return tds;
	}

	/**
	 * private method called from limitedSearch for use with incorrect spelling
	 * or incomplete words (this should be defined in the front end)
	 * 
	 * @param searchterm
	 * @param textLocation
	 * @param number_of_references
	 * @return TopDocs
	 * @throws IOException
	 */
	private TopDocs LimitedFuzzySearch(String searchterm, String textLocation,
			int number_of_references) throws IOException {

		FuzzyQuery fq = new FuzzyQuery(new Term(textLocation, searchterm));
		TopDocs tdc = searcher_.search(fq, number_of_references);
		return tdc;
	}

	/**
	 * Private method unbounded fuzzy search @see {@link FuzzyQuery}
	 * 
	 * @param searchTerm
	 * @param textLocation
	 * @return TopDocs
	 * @throws IOException 
	 */
	private TopDocs FuzzySearch(String searchTerm, String textLocation) throws IOException {
		FuzzyQuery fq = new FuzzyQuery(new Term(textLocation, searchTerm));
		TopDocs tp = searcher_.search(fq, Integer.MAX_VALUE);
		return tp;
	}

	/**
	 * Private method unbounded wildcard search @see {@link WildcardQuery}
	 * 
	 * @param SearchTerm
	 * @param TextLocation
	 * @return TopDocs
	 * @throws IOException
	 */
	private TopDocs WildCardSearch(String SearchTerm, String TextLocation)
			throws IOException {
		Term term = new Term(TextLocation, SearchTerm);
		WildcardQuery wc = new WildcardQuery(new Term(TextLocation, SearchTerm));
		TopDocs tds = searcher_.search(wc, Integer.MAX_VALUE);
		return tds;
	}

	/**
	 * Private Method bounded search of NumRef terms, returning NumRef total
	 * documents of N hits
	 * 
	 * @param searchTerm
	 * @param textLocation
	 * @param NumRef
	 * @return TopDocs
	 * @throws IOException
	 */
	private TopDocs LimitedBasic(String searchTerm, String textLocation,
			int NumRef) throws IOException {
		Term term = new Term(textLocation, searchTerm);
		TermQuery tmQ = new TermQuery(term);
		TopDocs topd = searcher_.search(tmQ, NumRef);
		return topd;
	}

	/**
	 * Most Basic search, unbounded @see {@link TermQuery}
	 * 
	 * @param SearchTerm
	 * @param textLocation
	 * @return TopDocs
	 * @throws IOException
	 */
	private TopDocs BasicSearch(String SearchTerm, String textLocation)
			throws IOException {
		Term term = new Term(textLocation, SearchTerm);
		TermQuery tmq_ = new TermQuery(term);
		TopDocs tpd = searcher_.search(tmq_, Integer.MAX_VALUE);
		return tpd;
	}

}
