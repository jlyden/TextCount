class SumTen {
	public static void main ( String [] args )
	{
		// Use loop to sum numbers from 1-10
		int sum = 0;
		for(int i = 0; i < 11; i++) {
			sum += i;
		}
		
		// Print the result
		System.out.println("The sum is: " + sum);
	}
}