////////////////////////////////////////////////////////////
/////
/////  A Visualisation Tool for 
/////  Selection Hyper-Heuristics
/////
/////  http://code.google.com/p/vch/
/////
/////  Group:			gp09-exo
/////  FullNames:		Thomas Barton (txb18u)
/////					Zhang Chao (cxz09u)
/////					Ben Jenkinson (bxj08u)
/////					Alexander Jermstad (asj08u)
/////					Lao Jingqi (jxl29u)
/////
/////  CreationDate: 	2010-02-15
/////  Module:			G52GRP, University of Nottingham
/////
////////////////////////////////////////////////////////////



// LowLevelHeuristic
// This is the base class extended by all low-level heuristic objects.



public abstract class LowLevelHeuristic implements LowLevelHeuristicInterface {
	
	// DECLARE GLOBAL VARIABLES & STATE IDENTIFIERS
	
	private String name;
	private String explanation;
	
	
	
	// CONSTRUCTOR
	
	public LowLevelHeuristic() {
		
	} // END LowLevelHeuristic constructor
	
	
	
	// METHODS
	
	public String getName() {
		
		// Returns a string to represent the heuristic.
		return this.name;
		
	} // END String getName()
	
	public String getExplanation() {
		
		// Returns a short explanation of the heuristic.
		return this.explanation;
		
	} // END String getExplanation()
	
	public int[] convertIntToBinaryArray( int input ) {
		
		// Converts an integer to a binary string, and then inserts each digit into an array.
		
		// First, convert the integer into a string of binary.
		String binaryString = Integer.toBinaryString(input);
		
		// Add a whole load of spare zeroes to make it up to the maximum value of the problem.
		while( binaryString.length() < Integer.toBinaryString( this.vch.getMaxiumValue() ).length() ){
			
			binaryString = "0" + binaryString;
			
		}
		int[] intArray = new int[binaryString.length()];
		for( int j=0; j<binaryString.length(); j++ ) {
			intArray[j] = Character.getNumericValue( binaryString.charAt(j) );
		}
		
		return intArray;
		
	} // END convertIntToBinaryArray( int input )
	
	private int convertBinaryArrayToInt( int[] input ) {
		
		// Converts an array of 1s & 0s to a String of binary, and then to an Int
		String string = Arrays.toString(input).replace(", ", "");
		string = string.substring(1, string.length()-1);
	
		// Convert the string into an integer
		int output = Integer.parseInt(string, 2);
		
		// Prints debug message
		this.vch.printDebugMessage("LowLevelHeuristicMachine()->convertBinaryArrayToInt("+input+")->("+output+")");
		
		// Returns an integer
		return output;
		
	} // END convertBinaryArrayToInt( int[] input )
	
} // END class LowLevelHeuristic