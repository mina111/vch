import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;


public class InitalMenuPanel extends JPanel{
	MyLabel benchmarkFunctionInfo,heuristicSelectionInfo, lowLevelHeuristicsInfo, acceptanceMethodInfo, launchDemonstrationInfo;
	MyButton benchmarkFunction, heuristicSelection, lowLevelHeuristics, acceptanceMethod, launchDemonstration;
	JPanel left,center,right;
	boolean benchmarkFunctionMenu = true,heuristicSelectionMenu = false,lowLevelHeuristicsMenu = false, acceptanceMethodMenu = false;
	public InitalMenuPanel(int width,Config con,VSHMainFrame frame){
		this.setOpaque(false);
		//this.setPreferredSize(new Dimension(width,28));
		left = new JPanel();
		left.setBackground(con.getLeftBgColor());
		this.setLayout(new BorderLayout());
		this.add(left,BorderLayout.WEST);
		center = new JPanel();
		center.setLayout(con.getFlowLayout(1,0,0));
		center.setBackground(con.getLeftBgColor());
		benchmarkFunction = new MyButton(con,"benchmark_function_on.png","benchmark_function_pressed.png","benchmark_function_disable.png","Benchmark Function");
		benchmarkFunction.setActionCommand("Benchmark Function");
		benchmarkFunction.addActionListener(new Action(frame,benchmarkFunction));
		heuristicSelection = new MyButton(con,"heuristic_selection_on.png","heuristic_selection_pressed.png","heuristic_selection_disable.png","Heuristic Selection");
		heuristicSelection.setActionCommand("Heuristic Selection");
		heuristicSelection.addActionListener(new Action(frame,heuristicSelection));
		lowLevelHeuristics = new MyButton(con,"low_level_on.png","low_level_pressed.png","low_level_disable.png","Low Level Heuristics");
		lowLevelHeuristics.setActionCommand("Low Level Heuristics");
		lowLevelHeuristics.addActionListener(new Action(frame,lowLevelHeuristics));
		acceptanceMethod = new MyButton(con,"acceptance_method_on.png","acceptance_method_pressed.png","acceptance_method_disable.png","Acceptance Method");
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
