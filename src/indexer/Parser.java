package indexer;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


@SuppressWarnings("unused")
public class Parser {

	/**
	 * @param args
	 */
	
	
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		 File directory = new File("java");
	
	

	}
	
	private static void IndexDocs(File file) throws Exception{
		if( file . isDirectory()){
			String [] file_list = file . list () ;
			Arrays . sort(file_list);
			for(int i = 0 ; i < file_list . length; i++){
				IndexDocs(new File(file, file_list[i]));
			}
		}else if (file . getPath() . endsWith ("htm")){
			Document doc = new Document();
			
		}
	}

}
