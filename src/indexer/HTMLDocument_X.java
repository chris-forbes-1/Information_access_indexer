package indexer;
import java.io.*;
import org.apache.lucene.document.*;
public class HTMLDocument_X {

	public static void main(String[] args){
		
	}
	/*public static Document Document(File f) throws IOException, InterruptedException 
	{
	    // make a new, empty document
	    Document doc = new Document();
	    // Add the url as a field named "path".  Use a field that is 
	    // indexed (i.e. searchable), but don't tokenize the field into words.
	    doc.add(new Field("path", f.getPath().replace(dirSep, '/'), Field.Store.YES,
	        Field.Index.NOT_ANALYZED));
	    // Add the last modified date of the file a field named "modified".  
	    // Use a field that is indexed (i.e. searchable), but don't tokenize
	    // the field into words.
	    doc.add(new Field("modified",
	        DateTools.timeToString(f.lastModified(), DateTools.Resolution.MINUTE),
	        Field.Store.YES, Field.Index.NOT_ANALYZED));
	    // Add the uid as a field, so that index can be incrementally maintained.
	    // This field is not stored with document, it is indexed, but it is not
	    // tokenized prior to indexing.
	    doc.add(new Field("uid", uid(f), Field.Store.NO, Field.Index.NOT_ANALYZED));
	    FileInputStream fis = new FileInputStream(f);
	    Parser_reader parser = new Parser_reader(fis);
	    // Add the tag-stripped contents as a Reader-valued Text field so it will
	    // get tokenized and indexed.
	    doc.add(new Field("contents", parser.getReader()));
	    // Add the summary as a field that is stored and returned with
	    // hit documents for display.
	    doc.add(new Field("summary", parser.getSummary(), Field.Store.YES, Field.Index.NO));
	    // Add the title as a field that it can be searched and that is stored.
	    doc.add(new Field("title", parser.getTitle(), Field.Store.YES, Field.Index.ANALYZED));
	    // return the document
	    return doc;
	}*/
}
