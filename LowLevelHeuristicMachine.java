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



// LowLevelHeuristicMachine
// This object will take a value and then perform one of several different heuristics upon it.



// IMPORT Java Libraries

import java.io.*;
import java.lang.*;
import java.util.*;



public class LowLevelHeuristicMachine {
	
	// DECLARE GLOBAL VARIABLES & STATE IDENTIFIERS
	private Vch vch;
		
	// Create and seed the random number generator
	private Random random = new Random( System.nanoTime() );
	
	
	
	// CONSTRUCTOR
	
	public LowLevelHeuristicMachine( Vch parent ) {
		
		// Save the parent frame.
		this.vch = parent;
		
		this.vch.printDebugMessage("LowLevelHeuristicMachine()");
		this.vch.printDebugMessage("LowLevelHeuristicMachine()->\"THIS IS DEPRECATED, STOP USING IT\"");
		throw new UnsupportedOperationException("Stop using this, it's deprecated.");
		
	} // END LowLevelHeuristicMachine constructor
	
	
	
	// METHODS
	
	public int flip( int input ) {
		
		// Perform the "Flip" low-level heuristic upon the input and return a new integer.
		this.vch.printDebugMessage("LowLevelHeuristicMachine()->flip("+input+")");
		
		// Convert the input integer into a binary array.
		int[] array = convertIntToBinaryArray( input );
		
		// Select a random bit from the array
		int arrayPosition = this.random.nextInt(array.length);
		
		// Operate on that bit
		if( array[arrayPosition] == 1 ) {
			// If it's '1', then flip it to '0'
			array[arrayPosition] = 0;
		} else {
			// If it's not '1', it must already be '0', so flip it to '1'
			array[arrayPosition] = 1;
		}
		
		// Convert the array back to an integer and return it.
		return convertBinaryArrayToInt( array );
		
	} // END method flip( int input )
	
	public int swap( int input ) {
		
		// Perform the "Swap" low-level heuristic upon the input and return a new integer.
		this.vch.printDebugMessage("LowLevelHeuristicMachine()->swap("+input+")");
		
		int output = 0;
		
		return output;
		
	} // END method swap( int input )
	
	public int flipAndSwap( int input ) {
		
		// Perform both the "Flip" and "Swap" low-level heuristics upon the input and return a new integer.
		this.vch.printDebugMessage("LowLevelHeuristicMachine()->flipAndSwap("+input+")");
		
		return swap( flip( input ) );
		
	} // END method swap( int input )

	public int[] convertIntToBinaryArray( int input ) {
		
		// Converts an integer to a binary string, and then inserts each digit into an array.
		
		// First, convert the integer into a string of binary.
		String binaryString = Integer.toBinaryString(input);
		
		// Add a whole load of spare zeroes to make it up to the maximum value of the problem.
		while( binaryString.length() < Integer.toBinaryString( this.vch.getMaximumValue() ).length() ){
			
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
	
	
	
	// LISTENERS
	
} // END class LowLevelHeuristicMachine