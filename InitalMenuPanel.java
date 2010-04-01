
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 *  A Visualisation Tool for 
 *  Selection Hyper-Heuristics                                    <br>   
 *  															  <br>
 *  http://code.google.com/p/vch/   							  <br>
 *  															  <br>
 *  Module:                  G52GRP, University of Nottingham     <br>
 *  															  <br>
 *  Group:        gp09-exo    						  			  <br>
 * @author 	   	Lao Jingqi (jxl29u)
 * @author	   	Zhang Chao (cxz09u)
 * @author		Thomas Barton (txb18u)
 * @author		Ben Jenkinson (bxj08u)
 * @author	   	Alexander Jermstad (asj08u) 					  <br>
 * 			
 */

public class InitalMenuPanel extends JPanel{
	/**
	 *   show the current benchmark function
	 */
	MyLabel benchmarkFunctionInfo;
	/**
	 *  show the current heuristic selection
	 */
	MyLabel heuristicSelectionInfo;
	/**
	 *  show the current low-level heuristics
	 */
	MyLabel lowLevelHeuristicsInfo;
	/**
	 *  show acceptance method
	 */
	MyLabel acceptanceMethodInfo;
	/**
	 *  get  benchmark function sub-menu
	 */
	MyButton benchmarkFunction;
	/**
	 *  get  heuristic selection sub-menu
	 */	
	MyButton heuristicSelection;
	/**
	 * get  low-level heuristics sub-menu
	 */
	MyButton lowLevelHeuristics;
	/**
	 *  get  acceptance method sub-menu
	 */
	MyButton acceptanceMethod;
	JPanel left,center,right;
	/**
	 *  judge if the button is pressed
	 */
	boolean benchmarkFunctionMenu = true,heuristicSelectionMenu = false,lowLevelHeuristicsMenu = false, acceptanceMethodMenu = false;
	
	
	public InitalMenuPanel(int width,Config con,VSHMainFrame frame){
		this.setOpaque(false);
		left = new JPanel();
		left.setBackground(con.getLeftBgColor());
		this.setLayout(new BorderLayout());
		this.add(left,BorderLayout.WEST);
		center = new JPanel();
		center.setLayout(con.getFlowLayout(1,0,0));
		center.setBackground(con.getLeftBgColor());
		benchmarkFunction = new MyButton(con,"menu-selection-benchmarkfunction.png","menu-selection-benchmarkfunction-hover.png","menu-selection-benchmarkfunction-disabled.png","Benchmark Function");
		benchmarkFunction.setActionCommand("Benchmark Function");
		benchmarkFunction.addActionListener(new Action(frame,benchmarkFunction));
		heuristicSelection = new MyButton(con,"menu-selection-heuristicselection.png","menu-selection-heuristicselection-hover.png","menu-selection-heuristicselection-disabled.png","Heuristic Selection");
		heuristicSelection.setActionCommand("Heuristic Selection");
		heuristicSelection.addActionListener(new Action(frame,heuristicSelection));
		lowLevelHeuristics = new MyButton(con,"menu-selection-lowlevelheuristics.png","menu-selection-lowlevelheuristics-hover.png","menu-selection-lowlevelheuristics-disabled.png","Low Level Heuristics");
		lowLevelHeuristics.setActionCommand("Low Level Heuristics");
		lowLevelHeuristics.addActionListener(new Action(frame,lowLevelHeuristics));
		acceptanceMethod = new MyButton(con,"menu-selection-acceptancemethod.png","menu-selection-acceptancemethod-hover.png","menu-selection-acceptancemethod-disabled.png","Acceptance Method");
		acceptanceMethod.setActionCommand("Acceptance Method");
		acceptanceMethod.addActionListener(new Action(frame,acceptanceMethod));
		benchmarkFunctionInfo  = new MyLabel("Benchmark Function",con);
		heuristicSelectionInfo = new MyLabel("Heuristic Selection",con);
		lowLevelHeuristicsInfo = new MyLabel("Low Level Heuristics",con);
		acceptanceMethodInfo = new MyLabel("Acceptance Method",con);
		center.add(benchmarkFunction);
		center.add(benchmarkFunctionInfo);
		center.add(heuristicSelection);
		center.add(heuristicSelectionInfo);
		center.add(lowLevelHeuristics);
		center.add(lowLevelHeuristicsInfo);
		center.add(acceptanceMethod);
		center.add(acceptanceMethodInfo);
		this.add(center,BorderLayout.CENTER);
		
		right = new JPanel();
		right.setBackground(con.getLeftBgColor());
		this.add(right,BorderLayout.EAST);
	}
	
}
