class NonZeros {

	public static void main ( String [] args)
	{
		// Create arrays
		int [] startZero = {0,1,2,3};
		int [] endZero = {-4,2,7,10,0};
		int [] middleZero = {5,3,0,-1,-5};
		int [] twoZeros = {50,-30,0,10,0,-6,4,-5342,33};
		int [] allZeros = {0,0,0};
		
		// Pass arrays to NonZeros 
		int [] startZeroAfter = NonZeros(startZero);
		int [] endZeroAfter = NonZeros(endZero);
		int [] middleZeroAfter = NonZeros(middleZero);
		int [] twoZerosAfter = NonZeros(twoZeros);
		int [] allZerosAfter = NonZeros(allZeros);
		
		// Print each original array and the result of NonZeros
		printMessage(startZero, startZeroAfter);
		printMessage(endZero, endZeroAfter);
		printMessage(middleZero, middleZeroAfter);
		printMessage(twoZeros, twoZerosAfter);
		printMessage(allZeros, allZerosAfter);
	}

	/**
	 * @param A = array of ints
	 * @return array ints from A in original order, except zeros removed
	 */
	private static int[] NonZeros( int [] A )
	{
		// declare new array of proper size
		int nonZeroCountA = getNonZeroCount(A);
		int [] nonZeroArray = new int [nonZeroCountA];

		// declare counter to keep track of position in new array
		int nonZeroCounter = 0;
		
		// go through A value by value, adding non-zero values to new array 
		int lengthA = A.length;
		for(int i = 0; i < lengthA; i++) {
			if(A[i] != 0){
				nonZeroArray[nonZeroCounter] = A[i];
				nonZeroCounter ++;
			}
		}
		return nonZeroArray;
	}
	
	private static int getNonZeroCount( int [] A )
	{
		int nonZeroCount = 0;
		int lengthA = A.length;
		for(int i = 0 ; i < lengthA; i++) {
			if(A[i] != 0) {
				nonZeroCount ++;
			}
		}
		return nonZeroCount;
	}
	
	private static void printArray( int [] A )
	{
		int lengthA = A.length;
		System.out.print("[");
		for(int i = 0; i < lengthA; i++) {
			System.out.print(A[i]);
			if(i != lengthA-1){
				System.out.print(",");
			}
		}
		System.out.print("]");
	}
	
	private static void printMessage( int [] A, int [] B) {
		System.out.print("passing ");
		printArray(A);
		System.out.print(" gotback ");
		printArray(B);
		System.out.print("\n");
	} 
}



