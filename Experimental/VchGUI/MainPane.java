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



// MainPane



// IMPORT LIBRARIES

import java.io.*;
import java.awt.*;
import javax.swing.*;



public class MainPane extends JPanel {
	
	// DECLARE GLOBAL VARIABLES & STATE IDENTIFIERS
	private Vch vch;
	
	// CONSTRUCTOR
	
	public MainPane( Vch parent, BorderLayout borderLayout ) {
		
		super( borderLayout );
		
		// Save the parent frame.
		this.vch = parent;
		
		this.vch.printDebugMessage("MainPane()");
		
	} // END MainPane constructor
	
	// METHODS
	
	// LISTENERS
	
} // END class MainPane