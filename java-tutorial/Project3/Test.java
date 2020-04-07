/*
 * TODO: Write a main function to test the new version of the nextElement 
 * 		 method in a (public) class named Test in the parent directory of 
 *		 the ListPkg subdirectory. The main function should include calls 
 * 		 that cause the new exception to be thrown, and should include code 
 * 		 to catch and handle the exception (e.g., first print a message saying 
 * 		 that a bad call is about to be made, then in the catch clause for 
 * 		 the exception print a message saying that the exception was thrown 
 * 		 and caught). The main function should also include calls to nextElement 
 * 		 that do not cause the exception to be thrown.
 * 
 * Ref: http://pages.cs.wisc.edu/~hasti/cs368/JavaTutorial/PROGRAMS/Program3.html
 */
import ListPkg.*;

public class Test {

	public static void main ( String [] args ) 
	{
		System.out.println("Create list and Print");
		List sampleList = new List();
		sampleList.addToEnd(100);
		sampleList.addToEnd(200);
		sampleList.addToEnd(300);
		sampleList.print();

		System.out.println("Set firstElement, then call nextElement");
		sampleList.firstElement();
		String thisElement = sampleList.nextElement().toString();
		System.out.println("thisElement s/b 100: " + thisElement);
		System.out.println("currentElement s/b 200: " + sampleList.getCurrentElement());
		System.out.println("Call hasMoreElements and expect NO exception");
		try {
			sampleList.hasMoreElements();
		} catch ( NoNextElementException e ) {
			System.out.println("Exception caught!");
			System.out.println("Error: " + e);
		}
		
		System.out.println("Forward another element, there should be NOT more");
		sampleList.nextElement();
		System.out.println("currentElement s/b 300: " + sampleList.getCurrentElement());
		System.out.println("hasMoreElements should thrown an Exception");
		try {
			sampleList.hasMoreElements();
		} catch ( NoNextElementException e ) {
			System.out.println("Exception caught!");
			System.out.println("Error: " + e);
		}

		System.out.println("Add item to end of list and Print");
		sampleList.addToEnd(400);
		sampleList.print();
		System.out.println("Call hasMoreElements and expect NO exception");
		try {
			sampleList.hasMoreElements();
		} catch ( NoNextElementException e ) {
			System.out.println("Exception caught!");
			System.out.println("Error: " + e);
		}

		System.out.println("Call nextElement, then hasMoreElements, expecting an exception");
		sampleList.nextElement();
		try {
			sampleList.hasMoreElements();
		} catch ( NoNextElementException e ) {
			System.out.println("Exception caught!");
			System.out.println("Error: " + e);
		}
	}
}