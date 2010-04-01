import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
 * @author	   	Alexander Jermstad (asj08u) 
 * 			
 */

/**
 *  This panel is left panel of middle part of the interface
 */
public class CustomizePanel extends JPanel{
	/**
	 *  the width of this panel
	 */
	int width;
	/**
	 *  the height of this panel 
	 */
	int height;
	/**
	 *  the sub-menu panel at the top of this panel
	 */
	JPanel upPanel;
	/**
	 *  the bar panel in the middle of this panel
	 */
	JPanel barPanel ;
	/**
	 *  the states panel at the bottom of this panel
	 */
	JPanel downPanel;
	/**
	 *  the blank panel is at the top of top panel for typesetting
	 */
	JPanel blankPanel;
	/**
	 *  the panel is in the middle of top panel for the sub-menu
	 */
	JPanel menuPanelInUpPanel;
	/**
	 *  the panel is at the bottom of top panel for the confirm button
	 */
	JPanel confirmationPanelInUpPanel;
	/**
	 *  the config instance
	 */
	Config con ;
	/**
	 *  the image for split bar
	 */
	ImageIcon split_img ;
	/**
	 *  the main frame
	 */
	VSHMainFrame frame;
	
	/**
	 *  the button group for the the groups of  benchmark radioButtons
	 */
	ButtonGroup   benchmarkFunctionGroupButton;
	/**
	 *  the button group for the the groups of  heuristic selections radioButtons
	 */
	ButtonGroup heuristicSelectionGroupButton;
	/**
	 *  the button group for the the groups of  acceptance methods radioButtons
	 */
	ButtonGroup acceptanceMethodGroupButton;
	
	/**
	 *  the radio button for benchmark functions
	 */
	MyRadioButton squareFunction,sinFunction,logFunction;
	/**
	 *  the radio button for heuristic selections
	 */
	MyRadioButton simpleRandom,greedyRandom,reinforcementLearning;
	/**
	 *  the radio button for  acceptance methods
	 */
	MyRadioButton onlyImproving, improvingEqual,allMovesAccepted; 
	/**
	 *  the check box for low-level heuristics
	 */
	MyCheckBox inverse, reverse,shift,flipOneBit,steepestGradient;


	/**
	 *  confirm button 
	 */
	MyButton confirm;
	/**
	 *  show the name of the current data
	 */
	MyLabel candidateSolution,newSolution,lowLevelHeuristic,accepted,count;
	/**
	 *  show the current data
	 */
	MyLabel candidateSolutionContent,newSolutionContent,lowLevelHeuristicContent,acceptedContent,countContent;
	/**
	 *  the number of low-level heuristics selected by the user
	 */
	int countLowLevelHeuristics = 0;
	/**
	 *  create the CustomizePanel instance
	 * @param frame  main frame
	 * @param width
	 * @param height
	 */
	CustomizePanel(VSHMainFrame frame,int width,int height)
	{
		//=============================

		this.width = width;
		this.height = height;
		this.frame = frame;
		initPanel();
	}

	/**
	 * initial  the CustomizePanel
	 */
	private void initPanel() {
		// TODO Auto-generated method stub
		con = new Config();
		split_img = con.getImgUrl("b_bg.png");
		this.setLayout(con.getFlowLayout(1,0,0)); 
		this.setPreferredSize(new Dimension(width,height));
		addPanel(width,height);
	}
	/**
	 * add panel to  CustomizePanel
	 * @param width
	 * @param height
	 */
	void addPanel(int width,int height){
			int splitbarheight = con.getSplitBarHeight() ;;
			int upHeight = con.getUpMenuHeight(height) ;
			int downHeight = height - splitbarheight - upHeight; 

			upPanel = new JPanel();
			upPanel.setPreferredSize(new Dimension(width,upHeight));
			upPanel.setLayout(new BorderLayout());
			blankPanel = new JPanel();
			blankPanel.setBackground(con.getLeftBgColor());
			upPanel.add(blankPanel,BorderLayout.NORTH); 
			menuPanelInUpPanel = new JPanel();
			menuPanelInUpPanel.setBackground(con.getLeftBgColor());
			menuPanelInUpPanel.setLayout(con.getFlowLayout(1,0,20));
			createButtonGroups();
			menuPanelInUpPanel.add(squareFunction);
			menuPanelInUpPanel.add(sinFunction);
			menuPanelInUpPanel.add(logFunction);
			upPanel.add(menuPanelInUpPanel,BorderLayout.CENTER);
			confirmationPanelInUpPanel = new JPanel();
			confirmationPanelInUpPanel.setBackground(con.getLeftBgColor());
			confirm = new MyButton (con,"button-confirm.png","button-confirm-hover.png","button-confirm-disabled.png","Confirm");
			confirm.setActionCommand("confirm");
			confirm.addActionListener(new Action(frame,confirm));
			confirmationPanelInUpPanel.add(confirm);
			upPanel.add(confirmationPanelInUpPanel,BorderLayout.SOUTH);
			this.add(upPanel);
			barPanel = new JPanel();
			barPanel.setPreferredSize(new Dimension(width,splitbarheight));
			barPanel.setBackground(con.getSplitBgColor());
			this.add(barPanel);
			downPanel = new JPanel();
			downPanel.setPreferredSize(new Dimension(width,downHeight));
			downPanel.setBackground(con.getLeftBgColor());
			downPanel.setLayout(con.getFlowLayout(1,0,10));
			candidateSolution = new MyLabel(con,"Candidate Solution");
			candidateSolutionContent = new MyLabel(con,"");
			newSolution = new MyLabel(con,"New Solution");
			newSolutionContent = new MyLabel(con,"");
			lowLevelHeuristic = new MyLabel(con,"Low Level Heuristic");
			lowLevelHeuristicContent =  new MyLabel(con,"");
			accepted = new MyLabel(con,"Accepted");
			acceptedContent =  new MyLabel(con,"");
			count = new MyLabel(con,"Count");
			countContent = new MyLabel(con,"");

			downPanel.add(count);
			downPanel.add(countContent);
			downPanel.add(candidateSolution);
			downPanel.add(candidateSolutionContent);
			downPanel.add(newSolution);
			downPanel.add(newSolutionContent);
			downPanel.add(lowLevelHeuristic);
			downPanel.add(lowLevelHeuristicContent);
			downPanel.add(accepted);
			downPanel.add(acceptedContent);
			this.add(downPanel);
		}
	
		/**
		 *  create the the group buttons for benchmark functions, heuristic selections and acceptance methods
		 */
		private void createButtonGroups() {
			// TODO Auto-generated method stub
			benchmarkFunctionGroupButton = new  ButtonGroup();
			squareFunction = new MyRadioButton(con,"radio-function-square.png","radio-function-square-hover.png","radio-function-square-selected.png","Square Function");
			squareFunction.setSelected(true);
			sinFunction = new MyRadioButton(con,"radio-function-sin.png","radio-function-sin-hover.png","radio-function-sin-selected.png","Sin Function");
			logFunction = new MyRadioButton(con,"radio-function-log.png","radio-function-log-hover.png","radio-function-log-selected.png","Log Function");
			benchmarkFunctionGroupButton.add(squareFunction);
			benchmarkFunctionGroupButton.add(sinFunction);
			benchmarkFunctionGroupButton.add(logFunction);
			
			heuristicSelectionGroupButton = new ButtonGroup();
			simpleRandom = new MyRadioButton(con,"radio-simplerandom.png","radio-simplerandom-hover.png","radio-simplerandom-selected.png","Simple Random");
			simpleRandom.setSelected(true);
			greedyRandom = new MyRadioButton(con,"radio-greedyrandom.png","radio-greedyrandom-hover.png","radio-greedyrandom-selected.png","Greedy Random");
			reinforcementLearning = new MyRadioButton(con,"radio-reinforcementlearning.png","radio-reinforcementlearning-hover.png","radio-reinforcementlearning-selected.png","Reinforcement Learning");
			heuristicSelectionGroupButton.add(simpleRandom);
			heuristicSelectionGroupButton.add(greedyRandom);
			heuristicSelectionGroupButton.add(reinforcementLearning);
			
			inverse = new MyCheckBox(con,"checkbox-inverse.png","checkbox-inverse-hover.png","checkbox-inverse-selected.png","Inverse");
			inverse.setSelected(true);
			reverse = new MyCheckBox(con,"checkbox-reverse.png","checkbox-reverse-hover.png","checkbox-reverse-selected.png","Reverse");
			reverse.setSelected(true);
			shift = new MyCheckBox(con,"checkbox-shift.png","checkbox-shift-hover.png","checkbox-shift-selected.png","Shift");
			shift.setSelected(true);
			flipOneBit = new MyCheckBox(con,"checkbox-fliponebit.png","checkbox-fliponebit-hover.png","checkbox-fliponebit-selected.png","Flip One Bit");
			flipOneBit.setSelected(true);
			steepestGradient = new MyCheckBox(con,"checkbox-steepestgradient.png","checkbox-steepestgradient-hover.png","checkbox-steepestgradient-selected.png","Steepest Gradient");
			steepestGradient.setSelected(true);


			acceptanceMethodGroupButton = new ButtonGroup();
			onlyImproving = new MyRadioButton(con,"radio-onlyimproving.png","radio-onlyimproving-hover.png","radio-onlyimproving-selected.png","Only Improving");
			onlyImproving.setSelected(true);
			improvingEqual= new MyRadioButton(con,"radio-improvingequal.png","radio-improvingequal-hover.png","radio-improvingequal-selected.png","Improving or Equal");
			allMovesAccepted = new MyRadioButton(con,"radio-allmoves.png","radio-allmoves-hover.png","radio-allmoves-selected.png","All Moves Accepted");
			acceptanceMethodGroupButton.add(onlyImproving);
			acceptanceMethodGroupButton.add(improvingEqual);
			acceptanceMethodGroupButton.add(allMovesAccepted);
			
		}	
	}

	
	
	
