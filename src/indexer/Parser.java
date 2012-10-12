package indexer;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.nio.*;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.LinkContentHandler;
import org.apache.tika.sax.TeeContentHandler;
import org.apache.tika.sax.ToHTMLContentHandler;
import org.xml.sax.ContentHandler;
@SuppressWarnings("unused")
public class Parser {

	/**
	 * @param args
	 */
	public static void main(String fileDirectory) throws Exception {
		String files;
		if(fileDirectory == " "){
			throw new FilePathException("Invalid path");
		}
				
		File folder = new File(fileDirectory);
		File[] listOfFolders = folder.listFiles();
		LinkContentHandler linkHandler = new LinkContentHandler();
        ContentHandler textHandler = new BodyContentHandler();
        ToHTMLContentHandler toHTMLHandler = new ToHTMLContentHandler();
        TeeContentHandler teeHandler = new TeeContentHandler(linkHandler, textHandler, toHTMLHandler);
        Metadata metadata = new Metadata();
        ParseContext parseContext = new ParseContext();
        HtmlParser parser = new HtmlParser();
        
		for(int i = 0; i < listOfFolders.length;i++)
		{
			
		}
	}
}
