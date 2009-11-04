import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.awt.geom.*;

public class VCHUIPanel extends JPanel implements Runnable{
		// GLOBAL VARIABLES
	int[] resultsArray = new int[2048];
	public static int boundaryMinX = 0;
	public static int boundaryMaxX = 1024;
	public static int OFFSETX = 150;
	public static int OFFSETY = 20;
	public static int GRAPHX = 512;
	public static int GRAPHY = 512;
	public static int SCALEY = (1024 * 1024) / GRAPHY;
	public static int SCALEX = 1024 / GRAPHX;
	public boolean vchrun = true;
	public boolean DrawResultsTable = true;
	public boolean DrawGridLines = true;
	public static int THREADSPEED = 300;
	public int Current = 0;
	public int GlobalBest = 1024;
	public int count = 0;
	
		// Create and seed the random number generator
	public static Random random = new Random( System.nanoTime() );


		// Initialise Panel
	JPanel VCHp = new JPanel();
	
		// Initialise Thread
	private Thread t;
	
	
	public VCHUIPanel(){
		t = new Thread(this); 										
        t.start();
	}
	
		public void run()
	{
		// LOCAL VARIABLES
		int[] previousXInput = new int[Integer.toBinaryString(boundaryMaxX).length()];
		int previousResult;
		
		int[] latestXInput = new int[Integer.toBinaryString(boundaryMaxX).length()];
		int latestResult;
		
		// Which heuristic did we use last?
		int lastChosenHeuristic = -1;

		// Which heuristic are we using this time?

		int currentHeuristic = -1;


		// How many times have we used this heuristic in a row?
		int sameHeuristicCount = 0;

		// Count how many times the new input has been rejected in favour of the previous input.
		int haveChosenPreviousCount = 0;

		// Count how many times we've been doing this.
		

		int timeToGiveUp = 200;
		
		// MAIN PROGRAM
		
		// Generate initial random number & save it in an array.
		previousXInput = convertIntToArray( generateRandomInt() );
		
		
		try{
			while(vchrun){
				Thread.sleep(THREADSPEED);
			// Initiate main loop
			int prevInt = convertArrayToInt(previousXInput);
			
			int ltstInt = applyHeuristic(prevInt);
			
			if( applyFunction(ltstInt) < applyFunction(prevInt) ) {
				
				System.out.println("\n" + ltstInt + " < " + prevInt);
				
				// Save the new int as the old int.
				previousXInput = convertIntToArray(ltstInt);

				// See if the heuristic we used this time is the same as the one we used last time.

				if( currentHeuristic == lastChosenHeuristic ){
					sameHeuristicCount++;

				}

				// Reset the count.
				haveChosenPreviousCount = 0;
				
			} else {
				
				// Previous int is still 'better'.
				System.out.println(ltstInt + " > " + prevInt);
				GlobalBest = prevInt;
				Current = ltstInt;
				// Increment the count.
				haveChosenPreviousCount++;
			}
			resultsArray[count] = ltstInt;
			count++;
			
			if(GlobalBest == 0){
			System.out.println("\n" + "Final Result: " + convertArrayToInt(previousXInput));	
			vchrun = false; // Stop Thread.
			}
					repaint();
				}
			}
	        catch(InterruptedException inter)
	        {
	           System.out.println("Error!"); 
	        }
	}
	
	public void paintComponent(Graphics g){
		int value;
		int tableStartYOffset = 75;
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawString("Current best:       " + GlobalBest,5,12);
		g.drawString("         Current:       " + Current,5,24);
		g.drawString("      Iterations:       " + count,5,36);
		
		//Draw Results Table
			if(DrawResultsTable == true){
			g.drawString("Previous Results",20,60);
			g.drawString("__________________",5,60);
			for(int iterate = 0; resultsArray[iterate] != 0; iterate++){
				g.drawString(""+resultsArray[iterate],55,tableStartYOffset);
				tableStartYOffset = tableStartYOffset + 12;
			}
		}
		


				
		//Draw Axis
		g2.draw(new Line2D.Double(0+OFFSETX, 0+OFFSETY, 0+OFFSETX, GRAPHY+OFFSETY));
		g2.draw(new Line2D.Double(0+OFFSETX, GRAPHY+OFFSETY, GRAPHX+OFFSETX,GRAPHY+OFFSETY));
		
		//Draw Axis Grid?
		if(DrawGridLines == true){
			int gridxoffset = 0;
			int gridyoffset = 0;
			while (gridxoffset <= GRAPHX){
				g2.draw(new Line2D.Double(gridxoffset+OFFSETX, GRAPHY+OFFSETY, gridxoffset+OFFSETX, GRAPHY+10+OFFSETY));
				gridxoffset = gridxoffset + 8;
			}
			while (gridyoffset <= GRAPHY){
				g2.draw(new Line2D.Double(OFFSETX-10, gridyoffset+OFFSETY, OFFSETX, gridyoffset+OFFSETY));
				gridyoffset = gridyoffset + 8;
			}
		}
		
		//Draw Graph
		for (int x=0; x <= 1024; x++ ){
			value = x * x;
			g2.draw(new Line2D.Double((x/SCALEX)+OFFSETX, (GRAPHY-(value/SCALEY))+OFFSETY, (x/SCALEX)+OFFSETX, (GRAPHY-(value/SCALEY))+OFFSETY));
		}
		
		//Draw current black line
		g2.draw(new Line2D.Double((Current/SCALEX)+OFFSETX, GRAPHY+OFFSETY+20, (Current/SCALEX)+OFFSETX, GRAPHY-((Current*Current)/SCALEY)+OFFSETY)); //x
		g2.draw(new Line2D.Double(OFFSETX-20, GRAPHY-((Current*Current)/SCALEY)+OFFSETY, (Current/SCALEX)+OFFSETX, GRAPHY-((Current*Current)/SCALEY)+OFFSETY)); //y
		
		//Draw best green line
		g2.setColor(Color.green);
		g2.draw(new Line2D.Double((GlobalBest/SCALEX)+OFFSETX, GRAPHY+OFFSETY+20, (GlobalBest/SCALEX)+OFFSETX, GRAPHY-((GlobalBest*GlobalBest)/SCALEY)+OFFSETY)); //x
		g2.draw(new Line2D.Double(OFFSETX-20, GRAPHY-((GlobalBest*GlobalBest)/SCALEY)+OFFSETY, (GlobalBest/SCALEX)+OFFSETX, GRAPHY-((GlobalBest*GlobalBest)/SCALEY)+OFFSETY)); //y
		g2.setColor(Color.black);
	
		//Draw Previous results (Square)
		for(int iterate2 = 0; resultsArray[iterate2] != 0; iterate2++){
			int cord = resultsArray[iterate2];
			int sizeOfBox = 10;
			g.drawRect((cord/SCALEX)-3+OFFSETX, (int)(GRAPHY-((cord*cord)/SCALEY))+OFFSETY-3, 6, 6);
			g.fillRect((cord/SCALEX)-3+OFFSETX, (int)(GRAPHY-((cord*cord)/SCALEY))+OFFSETY-3, 6, 6);
		}
				
		//Draw Best result (square)
		g.drawRect((GlobalBest/SCALEX)-3+OFFSETX, (int)(GRAPHY-((GlobalBest*GlobalBest)/SCALEY))+OFFSETY-3, 6, 6);
		g.fillRect((GlobalBest/SCALEX)-3+OFFSETX, (int)(GRAPHY-((GlobalBest*GlobalBest)/SCALEY))+OFFSETY-3, 6, 6);
		g.setColor(Color.green);
		g.drawRect((GlobalBest/SCALEX)-2+OFFSETX, (int)(GRAPHY-((GlobalBest*GlobalBest)/SCALEY))+OFFSETY-2, 4, 4);
		g.fillRect((GlobalBest/SCALEX)-2+OFFSETX, (int)(GRAPHY-((GlobalBest*GlobalBest)/SCALEY))+OFFSETY-2, 4, 4);
		g.setColor(Color.black);
		//Point2D.Double point = new Point2D.Double(100, 100);
	}
	
	public void CalculatePoint(){
		
	}
	
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
			System.out.println("ERROR: Value (" + x + ") not within x value boundary");
			System.exit(-1);
		}
		
	} // END checkXValue(int x)
}