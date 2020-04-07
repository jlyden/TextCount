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
 *  Source: http://pages.cs.wisc.edu/~hasti/cs368/JavaTutorial/ - Programming Assignment 3
 */
package ListPkg;

import java.lang.NullPointerException;

public class List {
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
	public List() {
		head = null;
	}

	/*
	 * Add object to end of list
	 */
	public void addToEnd(Object ob) {
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
	public void firstElement() {
		if(this.head != null){
			this.current = this.head;
		}
	}
	
	/* 
	 * Get object stored in current node
	 */
	public Object getCurrentElement() throws NullPointerException {
		Object thisItem = null;

		try {
			thisItem = this.current.content;
		} catch ( NullPointerException e) {
			System.out.println("Error: Current element not defined.");
		}
		
		return thisItem.toString();
	}
	
	/*
	 * Return current object and point current to next node
	 */
	public Object nextElement() {
		// Grab current item so we can return it
		Object thisItem = getCurrentElement();

		// Point current to next node
		this.current = this.current.nextNode;

		return thisItem;
	}

	/*
	 * Return true if there are still elements to point to
	 */
	public boolean hasMoreElements() throws NoNextElementException {
		if (this.head == null || this.current == this.tail) {
			throw new NoNextElementException();
		} else {
			return true;
		}
	}
	
	/*
	 * Print (to standard output) objects in list
	 */
	public void print() {
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
}