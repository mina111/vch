////////////////////////////////////////////////////////////
/////
/////  UserName:		bxj08u
/////  FullName:		Ben, Chao, Tom, Alexander, Lao
/////  CreationDate: 	2009-12-10
/////  Module:			G52GRP, University of Nottingham
/////
////////////////////////////////////////////////////////////



// ToolBar



// IMPORT LIBRARIES

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.*;
import java.net.*;



public class ToolBar extends JToolBar {
	
	// DECLARE GLOBAL VARIABLES & STATE IDENTIFIERS
	private Vch vch;
	
	// CONSTRUCTOR
	
	public ToolBar( Vch parent ) {
		
		// Save the parent frame.
		this.vch = parent;
		
		this.vch.printDebugMessage("ToolBar()");
		
		this.setFloatable( false );
		this.setOrientation( 0 );
		
		// This line doesn't seem to change anything.
		this.setMargin( new Insets( 30,30,30,30 ) );
		
		// Add the New Problem button.
		this.add( createButton( "New Problem", "newProblem", "Create a new problem", "icons/new-problem.png" ) );
		this.addSeparator();
		// Buttons to control the current problem.
		this.add( createButton( "Step Back", "stepBack", "Rewind one step", "icons/arrow-left.png" ) );
		this.add( createButton( "Play Animation", "startProblem", "Play animation", "icons/traffic-light-off.png" ) );
		this.add( createButton( "Step Forward", "stepForward", "Jump one step forward", "icons/arrow-right.png" ) );
		this.add( createButton( "Restart", "restartProblem", "Restart the problem", "icons/arrow-restart.png" ) );
		this.addSeparator();
		// Add the bonus buttons.
		this.add( createButton( "About Vch", "aboutVch", "About the program", "icons/about.png" ) );
		
		
		// this.add( createButton( "Select Folder", "openFolder", "Select a folder to browse", "icons/folder_open_image.png" ) );
		// this.addSeparator();
		// For the photos.
		// this.add( createButton( "Full-Size", "setSizeFull", "Show images full-size", "icons/arrow-out.png" ) );
		// this.add( createButton( "Scale to 50%", "setSizeScale", "Scale images to fit the window", "icons/arrow-in.png" ) );
		// this.addSeparator();
		// this.add( createButton( "Print", "print", "Print the current image", "icons/printer.png" ) );
		// this.addSeparator();
		// Just for fun..
		// this.add( createButton( "Piano", "piano", "Show Piano", "icons/piano.png" ) );
		
	} // END ToolBar constructor
	
	// METHODS
	
	public JButton createButton( String text, String actionCommand, String tipText, String path ) {
		
		// Create a button.
		JButton button = new JButton( text );
		button.setToolTipText( tipText );
		button.setActionCommand( actionCommand );
		button.setMargin( new Insets( 5,6,5,6 ) );
		
		// Add the action listener to the parent Vch class.
		button.addActionListener( this.vch );
		
		// Try and add an icon
		URL imageURL = Vch.class.getResource( path );
		if ( imageURL != null ) {
			button.setIcon( new ImageIcon( imageURL, text ) );
		} else {
			button.setText( text );
			System.err.println( "ToolBar->ImageNotFound(" + path + ")" );
		} // END if/else
		
		return button;
		
	} // END createButton();
	
	public void disableToolbar() {
		
	}
	
	
	// LISTENERS
	
} // END class ToolBar