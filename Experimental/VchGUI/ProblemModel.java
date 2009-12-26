////////////////////////////////////////////////////////////
/////
/////  Group:			gp09-exo
/////  FullNames:		Thomas Barton (txb18u)
/////					Zhang Chao (cxz09u)
/////					Ben Jenkinson (bxj08u)
/////					Alexander Jermstad (asj08u)
/////					Lao Jingqi (jxl29u)
/////
/////  CreationDate: 	2009-12-10
/////  Module:			G52GRP, University of Nottingham
/////
////////////////////////////////////////////////////////////



// ProblemModel
// Represents and stores all the settings for the problem.



public class ProblemModel {
	
	// DECLARE GLOBAL VARIABLES & STATE IDENTIFIERS
	private Vch vch;
	
	// CONSTRUCTOR
	
	public ProblemModel( Vch parent ) {
		
		// Save the parent frame.
		this.vch = parent;
		
		this.vch.printDebugMessage("ProblemModel()");
		
	} // END ProblemModel constructor
	
	// METHODS
	
	public void start() {
		
		this.vch.printDebugMessage( "ProblemModel->start()" );
		
	} // END start()
	
	public void stepBack() {
		
		this.vch.printDebugMessage( "ProblemModel->stepBack()" );
		
	} // END stepBack()
	
	public void stepForward() {
		
		this.vch.printDebugMessage( "ProblemModel->stepForward()" );
		
	} // END stepForward()
	
	public void restart() {
		
		this.vch.printDebugMessage( "ProblemModel->restart()" );
		
	} // END restart()
	
	public void destroy() {
		
		this.vch.printDebugMessage( "ProblemModel->destroy()" );
		
	} // END destroy()
	
	// LISTENERS
	
} // END class ProblemModel