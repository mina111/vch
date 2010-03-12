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



// LowLevelHeuristicInterface
// This is the interface used by all low-level heuristic objects.



public interface LowLevelHeuristicInterface {
	
	// Returns a string to represent the heuristic.
	String getName();
	
	// Returns a short explanation of the heuristic.
	String getExplanation();
	
	// The method that actually applies the heuristic. Returns an integer.
	int apply( int input );
	
} // END interface LowLevelHeuristicInterface