import searcher.Searcher_;
import searcher.SearchError;
import org.apache.lucene.search.TopDocs;
@SuppressWarnings("unused")
/**
 * 
 * @author cforbes2012
 *
 */
public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{

			Searcher_ s = new Searcher_();
	
			TopDocs dd = s.search("LimitedBasic", "java", "body", 10);
			System.out.println("TOTAL HITS FOR LIMITED BASIC:" + dd.totalHits);
			System.out.println("TOTAL RETURN DOCUMENTS FOR LIMITED BASIC:"+dd.scoreDocs.length);
			System.out.println("");
			dd = s.search("LimitedWildCard", "jav*", "body", 10);
			System.out.println("TOTAL HITS FOR LIMITEDWILDCARD:" + dd.totalHits);
			System.out.println("Total return docs for Limited wildcard:"+ dd.scoreDocs.length);
			System.out.println("");
			dd = s.search("LimitedFuzzyQuery", "jav", "body", 10);
			System.out.println("TOTAL HITS FOR LIMITEDFUZZYQUERY:" + dd.totalHits);
			System.out.println("TOTAL RETURN DOCS FOR LIMITED FUZZY:"+ dd.scoreDocs.length);
			System.out.println("");
			dd = s.search("Basic", "java", "body");
			System.out.println("TOTAL HITS FOR unbounded BASIC SEARCH:"+ dd.totalHits);
			System.out.println("TOTAL RETURN DOCS FOR unbounded BASIC SEARCH:" + dd.scoreDocs.length);
			System.out.println("");
			dd = s.search("WildCard", "jav*", "body");
			System.out.println("TOTAL HITS FOR UNBOUNDED WILDCARD: "+ dd.totalHits);
			System.out.println("TOTAL RETURN DOCS FOR UNBOUNDED WILDCARD:" + dd.scoreDocs.length);
			System.out.println("");
			dd = s.search("FuzzyQuery", "jav", "body");
			System.out.println("TOTAL HITS FOR UNBOUNDED FUZZY: "+ dd.totalHits);
			System.out.println("total return docs for unbounded fuzzy:" + dd.scoreDocs.length);
		}catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
