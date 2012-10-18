/*
 * @author Chris Forbes
 * @version 1.4
 */

package indexer;
//Custom Exception
import exception.custom.MissingPath;

//General Java Imports
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.ObjectInputStream.GetField;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.*;
import java.net.URL;
import java.nio.*;

//Jericho (html parsing framework) 
import net.htmlparser.jericho.*;

//Lucene Imports (indexing framework)
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;



@SuppressWarnings("unused")
public class Parser {

	private static File fileDir;
	private static File indexDir;
	/**
	 * @param args
	 */
	
	public static void main(String fileDirectory, String indexDirectory) throws Exception {
		
		fileDir = new File(fileDirectory); //The directories of the files to index
		indexDir = new File(indexDirectory);//The directory of the index
		

		
	}
	/*
	 * @return ArrayList<String> Extracted_text
	 */
	private static ArrayList<String> HTMLParse_Me(String fileToParsePath) throws MissingPath
	{// prepair the parser to look for Microsoft IE specific tags (Not sure if the data set has this as it is fairly old but just incase)
		MicrosoftConditionalCommentTagTypes.register(); 
		
		Source src_to_work_with = null;
		
		try {
			
			 src_to_work_with = new Source(new URL(fileToParsePath));
		} catch (MalformedURLException e) {
			throw new MissingPath("Malformed URL");
		} catch (IOException e) {
			throw new MissingPath("IOException");
		}
		src_to_work_with.fullSequentialParse();
		
		for(Element E: src_to_work_with.getAllElements())
		{
			System.out.println(E.toString());
		}
		
		
		
		
		
		return null;
	}
}
