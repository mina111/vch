////////////////////////////////////////////////////////////
/////
/////  UserName:		bxj08u
/////  FullName:		Ben, Chao, Tom, Alexander, Lao
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