package indexer;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

import org.jsoup.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser_reader {
	private static IndexWriter writer;
	private static Analyzer analyzer;
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		 init();
		File currentDirectory = new File("java");
		System.out.println("Dir': " + currentDirectory.getCanonicalPath());
		for (File x : currentDirectory.listFiles()) {
			if(x.getName().endsWith(".htm")) {
				System.out.println("\tFile: " + x.getCanonicalPath());
				File input = new File(x.getCanonicalPath());
				org.jsoup.nodes.Document doc = Jsoup.parse(input, "UTF-8");
				System.out.println("Title: " + doc.getElementsByTag("title").toString().substring(7, doc.getElementsByTag("title").toString().length()-8));
				System.out.println("Body: " + doc.getElementsByTag("body"));
			}
		}
		
		getDirContents(currentDirectory);
		
	}

	@SuppressWarnings("deprecation")
	public static boolean init(){
		File directory = new File("java");
		SimpleFSDirectory dir;
		try {
			dir = new SimpleFSDirectory(new File("IndexFiles"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		analyzer = new StandardAnalyzer(Version.LUCENE_33);
		try {
			writer = new IndexWriter(dir, analyzer,IndexWriter.MaxFieldLength.UNLIMITED);
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	
	
	@SuppressWarnings("deprecation")
	public static void getDirContents(File dir) {
		Document d;
		
		try {
			for (File file : dir.listFiles()) {
				 d = new Document();
				if(file.getName().endsWith(".htm")) {
					System.out.println("\tFile: " + file.getCanonicalPath());
					File input = new File(file.getCanonicalPath());
					org.jsoup.nodes.Document doc = Jsoup.parse(input, "UTF-8");
					System.out.println("Indexing"+ file.getName());
					d.add(new Field("title", doc.getElementsByTag("title").toString().substring(7, doc.getElementsByTag("title").toString().length()-8), Field.Store.YES, Field.Index.ANALYZED));
					for (Element table : doc.select(file.getName())) {
				        for (Element row : table.select("tr")) {
				            Elements tds = row.select("td");
				            if (tds.size() > 6) {
				                System.out.println(tds.get(0).text() + ":" + tds.get(1).text());
				                d.add(new Field("td0",tds.get(0).text(),Field.Store.YES, Field.Index.ANALYZED));
				                d.add(new Field("td1",tds.get(1).text(),Field.Store.YES, Field.Index.ANALYZED));
				                
				            }
				        }
				        	
				    }
					writer.addDocument(d);
					
					
					
					System.out.println("Title: " + doc.getElementsByTag("title").toString().substring(7, doc.getElementsByTag("title").toString().length()-8));
				} else if (file.isDirectory()) {
					System.out.println("Dir': " + file.getCanonicalPath());
					getDirContents(file);
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
