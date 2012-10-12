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
@SuppressWarnings("unused")
public class Parser {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String files;
		File folder = new File("java");
		File[] listOfFolders = folder.listFiles();
		System.out.println("Pre-parsing root index: ");
		for(int i = 0 ; i < listOfFolders.length;i++)
		{
			System.out.println(listOfFolders[i].getAbsoluteFile().getName());
			if(listOfFolders[i].isDirectory())
			{
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Directory hit");
				String[] SubFiles = listOfFolders[i].list();
				for(int x = 0; x < SubFiles.length; x++)
				{
					System.out.println(SubFiles[x]);
				}
			}
		}
	}
}
