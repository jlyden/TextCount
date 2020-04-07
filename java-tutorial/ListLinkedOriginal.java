/*
 *  List class (Linked List)
 *  
 *  A List is an ordered collection of any kind of Object.
 * 
 *  Operations:
 *     addToEnd    		Add a given object to the end of the list.
 *     print       		Print (to standard output) the objects in the list in order,
 *                 		enclosed in square brackets, separated by spaces.
 *	  firstElement		Go to first element of list
 *	  nextElement		Advance pointer to next object in list; return current object
 * 	  hasMoreElements	Return true if pointer not pointing past last element
 *
 * TODO: Define a class ListNode that contains two fields: 
 * 			an Object (the item stored in this node of the linked list) 
 *			and a ListNode (pointer to the next item on the linked list). 
 * 
 * 		List class should have ListNode fields instead of a field that is an array of Objects. 
 * 		Define the ListNode class as a non-public class in the same file as your List class.
 * 
 * 		Print operation should be O(n) 
 * 		All other operations (including AddToEnd!) should be O(c) - no loop
 *
 *  Source: http://pages.cs.wisc.edu/~hasti/cs368/JavaTutorial/ - Programming Assignment 2
 */
import java.lang.NullPointerException;

class List {
	private ListNode head = null;		// pointer to start of list
	private ListNode tail = null;		// pointer to last node
	private ListNode current = null;	// pointer to current node

	private class ListNode {
		Object content;
		ListNode nextNode;
		
		// constructor 
		private ListNode(Object ob) {
			content = ob;
			nextNode = null;
		}
	}

	/*
	 * Constructor: initialize the list to be empty
	 */
	public List()
	{
		head = null;
	}

	/*
	 * Add object to end of list
	 */
	public void addToEnd(Object ob)
	{
		// if this is first element, we need head & tail to point to it
		if(this.head == null) {
			this.head = new ListNode(ob);
			this.tail = this.head;
		} else {
			// Otherwise, point tail nextNode to new node
			this.tail.nextNode = new ListNode(ob);
			// Then redirect tail itself to BE the new node
			this.tail = this.tail.nextNode;
		}
	}

	/* 
	 * Go to first element of list
	 */
	public void firstElement()
	{
		if(this.head != null){
			this.current = this.head;
		}
	}
	
	/* 
	 * Get object stored in current node
	 */
	private Object getCurrentElement() throws NullPointerException {
		Object thisItem = null;

		try {
			thisItem = this.current.content;
		} catch ( NullPointerException e) {
			System.out.println("Error: Current element not defined.");
		}
		
		return thisItem;
	}
	
	/*
	 * Return current object then point current to next node
	 */
	public Object nextElement()
	{
		// Grab current item so we can return it
		Object thisItem = getCurrentElement();

		// Point current to next node
		this.current = this.current.nextNode;

		return thisItem;
	}

	/*
	 * Return true if there are still elements to point to
	 */
	public boolean hasMoreElements()
	{
		return (this.head != null) && (this.current != this.tail);
	}
	
	/*
	 * Print (to standard output) objects in list
	 */
	public void print()
	{
		System.out.print("[");

		// Only try print elements if there are any
		if(this.head != null){
			// Set up a pointer to traverse list
			ListNode runner = this.head;

			// While there are nodes to traverse
			while(runner != null) {
				// Print & direct runner to nextNode
				System.out.print(runner.content);
				runner = runner.nextNode;

				// Print space if not final element
				if(runner !=null) {
					System.out.print(" ");
				}
			}
		}

		System.out.print("]\n");
	}
	
	public static void main ( String [] args ) 
	{
		System.out.println("Create list and Print while empty");
		List sampleList = new List();
		sampleList.print();
	
		System.out.println("Call firstElement, but it shouldn't set anything (expect caught Error)");
		sampleList.firstElement();
		System.out.println("currentElement should be Null: " + sampleList.getCurrentElement());

		System.out.println("Add 1st item");
		sampleList.addToEnd("alligator");

		System.out.println("Call firstElement, now should set, Print list");
		sampleList.firstElement();
		System.out.println("currentElement should be alligator: " + sampleList.getCurrentElement());
		sampleList.print();

		System.out.println("Add another item, check firstElement (which shouldn't change)");
		sampleList.addToEnd("baboon");
		sampleList.firstElement();
		System.out.println("currentElement should still be alligator: " + sampleList.getCurrentElement());

		System.out.println("Add 3rd item & Print");
		sampleList.addToEnd("caterpillar");
		sampleList.print();
		
		System.out.println("Call nextElement - should return \"alligator\"");
		String thisElement = sampleList.nextElement().toString();
		System.out.println("thisElement s/b alligator: " + thisElement);
		System.out.println("currentObject s/b \"baboon\" and there s/b more elements");
		System.out.println("currentElement s/b baboon: " + sampleList.getCurrentElement());
		System.out.println("hasMoreElements s/b true: " + sampleList.hasMoreElements());

		System.out.println("Forward another element, there should be NOT more");
		sampleList.nextElement();
		System.out.println("currentElement s/b caterpillar: " + sampleList.getCurrentElement());
		System.out.println("hasMoreElements s/b false: " + sampleList.hasMoreElements());		

		System.out.println("Add item to end of list, Print, then go back to first item");
		sampleList.addToEnd("dingo");
		sampleList.print();
		sampleList.firstElement();
		System.out.println("currentElement s/b alligator: " + sampleList.getCurrentElement());

		System.out.println("nextElement() x 3, check currentElement and hasMoreElements");
		sampleList.nextElement();
		sampleList.nextElement();
		sampleList.nextElement();
		System.out.println("currentElement s/b dingo: " + sampleList.getCurrentElement());
		System.out.println("hasMoreElements s/b false: " + sampleList.hasMoreElements());		
	}
}