package exception.custom;

/*
 * Created for cleaner code when calling HTMLParser_Me
 */

public class MissingPath extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MissingPath(String err){
		super(err);
	}

}
