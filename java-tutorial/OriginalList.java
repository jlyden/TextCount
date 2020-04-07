/*
 *  List class
 *    
 *  A List is an ordered collection of any kind of Object.
 * 
 *  Operations:
 *     AddToEnd    Add a given object to the end of the list.
 *     Print       Print (to standard output) the objects in the list in order,
 *                 enclosed in square brackets, separated by spaces.
 *
 *  Source: http://pages.cs.wisc.edu/~hasti/cs368/JavaTutorial/ - Programming Assignment 1
 */
class List {
	private static final int INIT_LEN = 3; // TODO: Reset to 10 after testing
	private Object [] items;  // the actual items
	private int numItems;     // the number of items currently in the list
	private int currentObject = -1; // array index of current object, initialized
	  
	/*
	 * constructor: initialize the list to be empty
	 */
	public List()
	{
		items = new Object [INIT_LEN];
		numItems = 0;
	}

	/*
	 * AddToEnd
	 *
	 * Given: Object ob
	 * Do:    Add ob to the end of the list.
	 */
	public void AddToEnd(Object ob)
	{
		int listLen = this.items.length;
		
		// If array is full, copy items into new array twice the size
		if(this.numItems == listLen) {
			Object [] newItems = new Object [listLen * 2];
			System.arraycopy(this.items,0,newItems,0,this.numItems);
			this.items = newItems;
		}

		// Now assign ob to end of list
		// Position of ob to be added will be numItems, 
		// because existing last item is at this.items[numItems-1]
		int endPosition = this.numItems; 
		this.items[endPosition] = ob;
		this.numItems ++;
	}

	/* 
	 * Make first object in list the current object
	 */
	public void firstElement()
	{
		// If there are no items, there is no first element
		if(this.numItems > 0) {
			this.currentObject = 0;
		}
	}
	
	/*
	 * Return current object and points to next 
	 */
	public Object nextElement()
	{
		// Update currentObject to point to next object
		int currentObjectIndex = this.currentObject;
		this.currentObject = currentObjectIndex + 1;

		// return current Object
		return this.items[currentObjectIndex];
	}

	/*
	 * Return true if there are still elements to point to
	 */
	public boolean hasMoreElements()
	{
		// return true if not empty list and currentObject not set to value past end of list
		return (this.numItems > 0) && (this.currentObject < this.numItems);
	}
	
	/*
	 * Print
	 *
	 * Print (to standard output) the objects in the list in order, enclosed in
	 * square brackets, separated by spaces.
	 */
	public void Print()
	{
		System.out.print("[");
		if(this.numItems > 0){
			// Print each item in items, and a space if not last item
			for(int i = 0; i < this.numItems; i ++) {
				System.out.print(this.items[i]);
				if(i<this.numItems - 1) {
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
		sampleList.Print();
		
		System.out.println("Call firstElement, but it shouldn't set anything");
		sampleList.firstElement();
		System.out.println("currentObject s/b -1: " + sampleList.currentObject);
		System.out.println("moreElements s/b false: " + sampleList.hasMoreElements());

		System.out.println("Add item");
		sampleList.AddToEnd("apple");

		System.out.println("Call firstElement, now should set");
		sampleList.firstElement();
		System.out.println("currentObject s/b 0: " + sampleList.currentObject);

		System.out.println("Add another item, check firstElement (which shouldn't change)");
		sampleList.AddToEnd("banana");
		sampleList.firstElement();
		System.out.println("currentObject s/b 0: " + sampleList.currentObject);

		System.out.println("Call nextElement - should return \"apple\", and currentObject s/b 1 now");
		String thisElement = sampleList.nextElement().toString();
		System.out.println("thisElement s/b apple: " + thisElement);
		System.out.println("currentObject s/b 1: " + sampleList.currentObject);

		System.out.println("Add item, Print");
		sampleList.AddToEnd("carrot");
		sampleList.Print();

		System.out.println("Forward one element, there should be more");
		sampleList.nextElement();
		System.out.println("currentObject s/b 2: " + sampleList.currentObject);
		System.out.println("moreElements s/b true: " + sampleList.hasMoreElements());

		System.out.println("Forward another element, there should be NOT more");
		thisElement = sampleList.nextElement().toString();
		System.out.println("thisElement s/b carrot: " + thisElement);
		System.out.println("currentObject s/b 3: " + sampleList.currentObject);
		System.out.println("moreElements s/b false: " + sampleList.hasMoreElements());
		
		System.out.println("INIT_LEN = 3 during testing, so this pushes us past boundary - add item");
		sampleList.AddToEnd("durian");
		sampleList.Print();

		System.out.println("Confirm array length is what we expect");
		System.out.println("sampleList.numItems is " + sampleList.numItems);
		System.out.println("sampleList.length is " + sampleList.items.length);
		System.out.println("moreElements s/b true: " + sampleList.hasMoreElements());

		System.out.println("Run that same workflow to get the second doubling of array");
		thisElement = sampleList.nextElement().toString();
		System.out.println("thisElement s/b durian: " + thisElement);
		sampleList.AddToEnd("eggplant");
		thisElement = sampleList.nextElement().toString();
		System.out.println("thisElement s/b eggplant: " + thisElement);
		sampleList.AddToEnd("fennel");
		sampleList.Print();
		thisElement = sampleList.nextElement().toString();
		System.out.println("thisElement s/b fennel: " + thisElement);
		System.out.println("sampleList.numItems is " + sampleList.numItems);
		System.out.println("sampleList.length is " + sampleList.items.length);

		System.out.println("Should be at end of existing list");
		System.out.println("currentObject s/b 6: " + sampleList.currentObject);
		System.out.println("moreElements s/b false: " + sampleList.hasMoreElements());

		System.out.println("Add another item, expect doubling");
		sampleList.AddToEnd("grape");
		sampleList.Print();
		System.out.println("sampleList.numItems is " + sampleList.numItems);
		System.out.println("sampleList.length is " + sampleList.items.length);

		System.out.println("currentObject is same, but now we have another item");
		System.out.println("currentObject s/b 6: " + sampleList.currentObject);
		System.out.println("moreElements s/b true: " + sampleList.hasMoreElements());
	}
}