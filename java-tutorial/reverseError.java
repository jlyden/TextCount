class Reverse {

	public static void main (String [] args){
		String startString = "castle";
		String endString = GoodReverse("castle");
		System.out.println(startString + " became " + endString);
	}
/*
	static String Reverse(String S) {
		String newS = "";
		char c = S[0];	// chars of String are accessed by charAt(0)
		while (c) {		// author means while (c < S.length())
			newS = c + newS;  // I'm not sure this will work - test
			c++;		// we need to increment the param i passed to charAt(i)
		}
		return newS;
	}
*/
	static String GoodReverse(String S){
		String newS = "";
		int strLen = S.length();
		for(int i = 0; i < strLen; i++){
			newS = S.charAt(i) + newS;
		}
		return newS;
	}
}

