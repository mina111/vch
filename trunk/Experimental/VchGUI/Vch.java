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

// Icons are from http://www.pinvoke.com/
// Used under a Creative Commons Attribution 3.0 license.
// http://creativecommons.org/licenses/by/3.0/



// IMPORT LIBRARIES

import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.*;



public class Vch extends JFrame implements ActionListener, KeyListener, FocusListener {
	
	// Are we in Debug mode?
	boolean inDebugMode = true;
	
	// DECLARE GLOBAL VARIABLES & STATE IDENTIFIERS
	
	ProblemModel problemModel;
	MainPane mainPane;
	SettingsPane settingsPane;
	AppFrame appFrame;
	Graph graph;
	ToolBar toolBar;
	
	
	
	// CONSTRUCTOR

	public Vch() {
				
		// Hooray, someone's using our program.
		printDebugMessage( "Vch()" );
		
		// We'll need somewhere to save the problem..
		problemModel = new ProblemModel( this );
		
		// Let's build them a window.
		appFrame = new AppFrame( this );
		
		// Let's fill it with things.
		
		// Create a toolbar.
		toolBar = new ToolBar( this );
		// Add the toolbar to the frame.
		this.getContentPane().add( toolBar, BorderLayout.NORTH );
		
		// Create a pane for the main window content.
		mainPane = new MainPane( this, new BorderLayout() );
		mainPane.setBackground( new Color(0,0,0,0) );
		// Add the mainPane to the frame.
		this.getContentPane().add( mainPane, BorderLayout.CENTER );
		
		
		// Set frame variables.
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setTitle( "Visualisation for a Choice Hyper-Heuristic" );
		this.setSize( new Dimension( 600, 400 ) );
		this.setMinimumSize( new Dimension( 600, 400 ) );
		this.setPreferredSize( new Dimension( 900, 600 ) );
		this.setLocationRelativeTo( null );
		this.setResizable( true );
		
		// Try and set an icon for the frame.
		try {
		    setIconImage( ImageIO.read( Vch.class.getResource("icons/counter.png") ) );
		} catch( IOException e ) {
			System.err.println("Vch->Error(Unable to find app icon image)");
		} // END try/catch
		
		// Build a settingsPane, add it to the window.
		displaySettingsPane();
		
		
		
		// Pack components.
		this.pack();
		// Finally, display the frame.
		this.setVisible( true );
		
		
		

		// Create the file list pane.
		// this.filePane = new FileListPane( this );		
		// Add the highest split pane to the main window.
		// this.getContentPane().add( splitPaneTheFirst, BorderLayout.CENTER );
		// this.getContentPane().add( splitPaneTheSecond, BorderLayout.CENTER );
		// acceptUserInput = true;
		
	} // END constructor Vch()
	
	
	
	// METHODS
	
	public static void attemptAppearance() {

		try {
			
			// Attempt to use Java's recreation of the current OS.
			// It will still look stupid sometimes, but it's better than nothing..
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
			
		} catch ( Exception e ) {
			
			// That didn't work, so print an error message.
			System.err.println( "Vch->Error(Unable to access system LookAndFeel)" );
			
		} // END try/catch

	} // END attemptAppearance()
	
	public void displaySettingsPane() {
		
		// Someone has asked us to display the Settings pane.
		// This means they want to create a new problem.
		
		printDebugMessage( "Vch->displaySettingsPane()" );
		
		// Therefore, we'll need a new settings pane.
		settingsPane = new SettingsPane( this );
		
	} // END displayAboutPane()
	
	public void displayAboutPane() {
		
		// Someone has asked us to display the About pane.
		// This should automatically pause the current problem process.
		// It should not harm the current problem.
		
		printDebugMessage( "Vch->displayAboutPane()" );
		
	} // END displayAboutPane()
	
	public void printDebugMessage( String string ) {
		
		// Checks for debug mode and then prints the string.
		
		if ( this.inDebugMode ) {
			
			System.out.println( string );
			
		} // END if
		
	} // END printDebugMessage( String string )
	
	
	
	// LISTENERS
	
	public void keyTyped( KeyEvent e ) {

		printDebugMessage( "Vch->keyTyped()->" + e );

	} // END keyTyped

	public void keyPressed( KeyEvent e ) {

		printDebugMessage( "Vch->keyPressed()->" + e );

	} // END keyPressed
	
	public void keyReleased( KeyEvent e ) {

		printDebugMessage( "Vch->keyReleased()->" + e );

	} // END keyReleased

	public void focusGained( FocusEvent e ) {

		printDebugMessage( "Vch->focusGained()->" + e );

	} // END focusGained

	public void focusLost( FocusEvent e ) {

		printDebugMessage( "Vch->focusLost()->" + e );

	} // END focusLost
	
	public void actionPerformed( ActionEvent e ) {
		
		// System.out.println( "actionPerformed->" + e );
		printDebugMessage( "Vch->actionPerformed()->getActionCommand()->" + e.getActionCommand() );
		
		// Test what action was requested.
		if( e.getActionCommand().equals( "aboutVch" ) ) {
			
			displayAboutPane();
			
		} else if( e.getActionCommand().equals( "stepBack" ) ) {
			
			problemModel.stepBack();
			
		} else if( e.getActionCommand().equals( "startProblem" ) ) {
			
			problemModel.start();
			
		} else if( e.getActionCommand().equals( "restartProblem" ) ) {
			
			// Restart the current problem.
			problemModel.restart();
			
		} else if( e.getActionCommand().equals( "stepForward" ) ) {
			
			problemModel.stepForward();
			
		} else if( e.getActionCommand().equals( "newProblem" ) ) {
			
			// Wipe & destory the problem model we had before.
			problemModel.destroy();
			
			// Display the settings pane so the user can start a new problem.
			displaySettingsPane();
			
		} // END if/else
		
	} // END actionPerformed( ActionEvent e )
	
	
	
	// MAIN PROGRAM

	public static void main( String args[] ) {

		// Try and make everything prettier.
		attemptAppearance();

		// Start a new application instance, window and all.
		Vch vch = new Vch();

	} // END main method
	
} // END class Vch

/*

	Create Vch
	Vch spawns UI
	UI prompts creation of new ProblemModel
	ProblemModel is constructed
		Runs-through everything
		does heuristics
	UI creates graph(ProblemModel)
	graph can then iterate through the data stored in the ProblemModel.

*/