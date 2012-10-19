package username;
import java.io.File;

import indexer.ExtractText;
import indexer.Parser;
@SuppressWarnings("unused")
public class Console_test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//File file = new File("java/copyrght.htm");
		String [] x = new String[0];
		System.out.println(x.length);
		ExtractText e = new ExtractText();
		try {
			e.main(x);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
