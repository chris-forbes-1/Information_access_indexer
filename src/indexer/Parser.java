package indexer;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.nio.*;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

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
		InputStream input;
		File folder = new File(fileDirectory);
		File[] listOfFiles = folder.listFiles();
		LinkContentHandler linkHandler = new LinkContentHandler();
        ContentHandler textHandler = new BodyContentHandler();
        ToHTMLContentHandler toHTMLHandler = new ToHTMLContentHandler();
        TeeContentHandler teeHandler = new TeeContentHandler(linkHandler, textHandler, toHTMLHandler);
        Metadata metadata = new Metadata();
        ParseContext parseContext = new ParseContext();
        HtmlParser parser = new HtmlParser();
        
		for(int i = 0; i < listOfFiles.length;i++)
		{
			//if(listOfFiles[i].getAbsoluteFile()fileDirectory.)
		}
	}
}
