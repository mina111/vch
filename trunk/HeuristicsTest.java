///////////////////////////////////////////////////
/////
/////  UserName:		bxj08u
/////  FullName:		Ben Jenkinson
/////  CreationDate: 	2009-10-08
/////  Module:			G52GRP
/////
///////////////////////////////////////////////////



// IMPORT VOODOO

import java.io.*;
import java.lang.*;
import java.util.*;



public class HeuristicsTest {
	
	// GLOBAL VARIABLES
		
	public static int boundaryMinX = 0;
	public static int boundaryMaxX = 1024;
	
	public static Random random = new Random( System.nanoTime() );
	
	// MAIN PROGRAM

	public static void main(String args[]) {
	
		// LOCAL VARIABLES
		
		int[] previousXInput = new int[Integer.toBinaryString(boundaryMaxX).length()];
		int previousResult;
		
		int[] latestXInput = new int[Integer.toBinaryString(boundaryMaxX).length()];
		int latestResult;
		
		// Counts how many times the new input has been rejected in favour of the previous input.
		int haveChosenPreviousCount = 0;

		// Counts how many times we've been doing this.
		int count = 0;
		int timeToGiveUp = 1000;
		
		// MAIN PROGRAM
		
		// Generate initial random number & save it in an array.
		previousXInput = convertIntToArray( generateRandomInt() );
		
		// Initiate main loop
		while( count < timeToGiveUp ) {
			
			int prevInt = convertArrayToInt(previousXInput);
			
			int ltstInt = applyHeuristic(prevInt);
			
			if( applyFunction(ltstInt) < applyFunction(prevInt) ) {
				
				System.out.println("\n" + ltstInt + " < " + prevInt);
				
				// Save the new int as the old int.
				previousXInput = convertIntToArray(ltstInt);
				
				// Reset the count.
				haveChosenPreviousCount = 0;
				
			} else {
				
				// Previous int is still 'better'.
				System.out.println(ltstInt + " > " + prevInt);
				// Increment the count.
				haveChosenPreviousCount++;
			}
			
			count++;
			
		} // END while loop
		
		System.out.println("\n" + "Final Result: " + convertArrayToInt(previousXInput));	
		
	} // END main
	
	public static int convertArrayToInt(int[] i) {
		
		// Converts an array of 1s & 0s to a String of binary, and then to an Int
		String str = Arrays.toString(i).replace(", ", "");
		str = str.substring(1, str.length()-1);
		
		return Integer.parseInt(str, 2);
		
	} // END convertArrayToInt
	
	public static int[] convertIntToArray(int i) {
		
		// Converts an integer to a binary string, and then inserts each digit into an array.
		
		// Check X value does not exceed the boundaries.
		checkXValue(i);
		
		String binaryString = Integer.toBinaryString(i);
		while( binaryString.length() < Integer.toBinaryString(boundaryMaxX).length() ){
			
			binaryString = "0" + binaryString;
			
		}
		int[] intArray = new int[binaryString.length()];
		for( int j=0; j<binaryString.length(); j++ ) {
			intArray[j] = Character.getNumericValue( binaryString.charAt(j) );
		}
		
		return intArray;
		
	} // END convertIntToArray
	
	public static int applyHeuristic(int x) {
		
		int h = random.nextInt(2);
		
		if( h == 0 ){
			x = generateRandomInt();
		} else {
			x = convertArrayToInt(heuristicFlip(convertIntToArray(x)));
		}
		
		return x;
		
	} // END applyHeuristic
	
	public static int[] heuristicFlip(int[] ints) {
		
		// Flips a random bit of the array
		int i = random.nextInt(ints.length);
		
		if( ints[i] == 1 ) {
			ints[i] = 0;
		} else {
			ints[i] = 1;
		}
		
		return ints;
		
	} // END heuristicFlip
	
	public static int applyFunction(int x) {
		
		x = x*x;
		return x;
		
	} // END applyFunction(int x)
	
	public static int generateRandomInt() {
		
		// Returns a random int, between the two boundaries.
		return random.nextInt(boundaryMaxX-boundaryMinX)+boundaryMinX;
		
	} // END generateRandomInt()
	
	public static void checkXValue(int x) {
		
		if( x>boundaryMaxX || x<boundaryMinX ) {
			System.out.println("ERROR: Value ("+x+") not within x value boundary");
			System.exit(-1);
		}
		
	} // END checkXValue(int x)
	
}