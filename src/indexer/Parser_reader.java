package indexer;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Fieldable;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.FieldInfo.IndexOptions;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

import org.jsoup.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SuppressWarnings("unused")
public class Parser_reader {
	private static IndexWriter writer;
	private static Analyzer analyzer;
	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		 init();
		File currentDirectory = new File("java");
		System.out.println("Dir': " + currentDirectory.getCanonicalPath());
		for (File x : currentDirectory.listFiles()) {
			if(x.getName().endsWith(".htm")) {
				System.out.println("\tFile: " + x.getCanonicalPath());
				File input = new File(x.getCanonicalPath());
				org.jsoup.nodes.Document doc = Jsoup.parse(input, "UTF-8");
				//System.out.println("Title: " + doc.getElementsByTag("title").text().substring(7, doc.getElementsByTag("title").text().length()-8));
				//System.out.println("Body: " + doc.getElementsByTag("body").text());
				//System.out.println("Title: " + doc.getElementsByTag("title").text());
			}
		}
		
		getDirContents(currentDirectory);
		writer.optimize();
		writer.close();
		System.out.println("\t\t\t INDEXING COMPLETE \t\t\t");
	}

	@SuppressWarnings({ "deprecation" })
	
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
		analyzer = new StandardAnalyzer(Version.LUCENE_33,StandardAnalyzer.STOP_WORDS_SET);
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
	
	
	
	
	
	public static void getDirContents(File dir) {
		Document d;
		
		
		
		
		try {
			for (File file : dir.listFiles()) {
				 d = new Document();
				if(file.getName().endsWith(".htm")) {
					//System.out.println("\tFile: " + file.getCanonicalPath());
					File input = new File(file.getCanonicalPath());
					org.jsoup.nodes.Document doc = Jsoup.parse(input, "UTF-8");
					//System.out.println("Indexing: "+ file.getName());
					//Elements e = doc.getElementsByTag("title");
					Elements e = doc.getElementsByTag("body");
					
					for(Element eS : e)
					{
						System.out.println(e.text());
						Field f = new Field("body",e.text(),Field.Store.YES,Field.Index.ANALYZED);
						d.add(f);
					}
					//d.add(new Field("Titles", doc.getElementsByTag("<title>").text(),Field.Store.YES,Field.Index.ANALYZED));
					//doc.text();
					//d.add(new Field("title", doc.getElementsByTag("title").text().toLowerCase(), Field.Store.YES, Field.Index.ANALYZED));
					//d.add(new Field("body",doc.getElementsByTag("body").text(),Field.Store.YES,Field.Index.ANALYZED));
					/*for (Element table : doc.select(file.getName())) {
				        for (Element row : table.select("tr")) {
				            Elements tds = row.select("td");
				            if (tds.size() > 6) {
				            	tds.html().trim();
				                System.out.println(tds.get(0).text() + ":" + tds.get(1).text());
				                //pre modifyd.add(new Field("td0",tds.get(0).text(),Field.Store.YES, Field.Index.ANALYZED));
				                d.add(new Field("td0",trimdat(tds),Field.Store.YES, Field.Index.ANALYZED));
				                // </end pre modify>
				                d.add(new Field("td1",tds.get(1).text(),Field.Store.YES, Field.Index.ANALYZED));
				                
				            }
				        }
				        	
				    }*/
					
					
					
					//TODO
					
					//String string = doc.getElementsByTag("body").text();
//					if(string.contains((CharSequence)StandardAnalyzer.STOP_WORDS_SET))
//					{
//						System.out.println("HOLY SHIT LOL");
//					}else{
//						System.out.println("Dont worry you violated OO so it violated you back");
//					}
//					String[] words = string.split(" ");
//						for(int i = 0; i< string.length(); i++) {
//							System.out.println("");
//							
//						}
//					}
					//Grab string: doc.getElementsByTag("body").text()
					// loop through doc.getElementsByTag("body").text()
					// remove stop words
					//do d.add(new Field("body",doc.getElementsByTag("body").text(),Field.Store.YES,Field.Index.ANALYZED)); for each word
					
					
					
					writer.addDocument(d);
					
					
					
					//System.out.println("Title: " + doc.getElementsByTag("title").toString().substring(7, doc.getElementsByTag("title").toString().length()-8));
				} else if (file.isDirectory()) {
					//System.out.println("Dir': " + file.getCanonicalPath());
					getDirContents(file);
				}
			}
		
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static String trimdat(Elements tds)
	{
		String temp_s = tds.text();
		for(int i = 0; i < temp_s.length(); i++){
			temp_s.replace("<td*", " ");
		}
		System.out.println("********************************************************************");
		System.out.println("*\t \t \t *");
		System.out.println(temp_s);
		System.out.println("********************************************************************");
		
		return temp_s;
	}

}
