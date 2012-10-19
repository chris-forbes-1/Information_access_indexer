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
	public static ArrayList<String> HTMLParse_Me(String fileToParsePath) throws MissingPath
	{// prepair the parser to look for Microsoft IE specific tags (Not sure if the data set has this as it is fairly old but just incase)
		MicrosoftConditionalCommentTagTypes.register(); 
		
		Source src_to_work_with = null;
		
		src_to_work_with = new Source(fileToParsePath);
		src_to_work_with.fullSequentialParse();
		
		System.out.println("Document title:");
		String title=getTitle(src_to_work_with);
		System.out.println(title==null ? "(none)" : title);

		System.out.println("\nDocument description:");
		String description=getMetaValue(src_to_work_with,"description");
		System.out.println(description==null ? "(none)" : description);

		System.out.println("\nDocument keywords:");
		String keywords=getMetaValue(src_to_work_with,"keywords");
		System.out.println(keywords==null ? "(none)" : keywords);
	
		
		
		
		
		
		return null;
	}
	private static String getTitle(Source source) {
		Element titleElement=source.getFirstElement(HTMLElementName.TITLE);
		if (titleElement==null) return null;
		// TITLE element never contains other tags so just decode it collapsing whitespace:
		return CharacterReference.decodeCollapseWhiteSpace(titleElement.getContent());
	}

	private static String getMetaValue(Source source, String key) {
		for (int pos=0; pos<source.length();) {
			StartTag startTag=source.getNextStartTag(pos,"name",key,false);
			if (startTag==null) return null;
			if (startTag.getName()==HTMLElementName.META)
				return startTag.getAttributeValue("content"); // Attribute values are automatically decoded
			pos=startTag.getEnd();
		}
		return null;
	}
}
