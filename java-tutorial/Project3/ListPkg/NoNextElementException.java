/*
 * Exception for hasMoreElements method 
 *
 * Ref: https://beginnersbook.com/2013/04/user-defined-exception-in-java/
 */
package ListPkg;

public class NoNextElementException extends Exception {
	public NoNextElementException() {

	}
	
	public String toString() {
		return("No next element to check.");
	}
}

