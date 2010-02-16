////////////////////////////////////////////////////////////
/////
/////	A Visualisation Tool for 
/////	Selection Hyper-Heuristics
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



// Problem
// Represents and stores all the settings for the problem.



public class Problem {
	
	// GLOBAL VARIABLES
	private Vch vch;
	
	// CONSTRUCTOR
	
	public Problem( Vch parent ) {
		
		// Save the parent frame.
		this.vch = parent;
		
		this.vch.printDebugMessage("Problem()");
		
	} // END ProblemModel constructor
	
	
	
	// METHODS
	
	public void start() {
		
		this.vch.printDebugMessage( "Problem->start()" );
		
	} // END start()
	
	
	
	// LISTENERS
	
	
	
} // END class ProblemModel