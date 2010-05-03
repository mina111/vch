import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CustomizePanel extends JPanel {

	int width,height;

	JPanel upPanel , barPanel , downPanel;
	JPanel blankPanel,menuPanelInUpPanel, confirmationPanelInUpPanel;
	Config con ;

	ImageIcon split_img,logo_img;

	VSHMainFrame frame;


	ButtonGroup   benchmarkFunctionGroupButton, heuristicSelectionGroupButton, acceptanceMethodGroupButton;
	MyRadioButton squareFunction,sinFunction,logFunction;
	MyRadioButton simpleRandom,greedyRandom,reinforcementLearning;
	MyCheckBox inverse, reverse,shift,flipOneBit,steepestGradient;

	MyRadioButton onlyImproving, improvingEqual,allMovesAccepted;

	MyButton confirm;

	MyLabel candidateSolution,newSolution,lowLevelHeuristic,accepted,count;
	MyLabel candidateSolutionContent,newSolutionContent,lowLevelHeuristicContent,acceptedContent,countContent;
	MyLabel blank;
	int countLowLevelHeuristics = 0;
	JLabel logo = new JLabel();
	
	
	CustomizePanel(VSHMainFrame frame,int width,int height) {

		this.width = width;
		this.height = height;
		this.frame = frame;
		initPanel();
		
	}

	private void initPanel() {
		// TODO Auto-generated method stub
		con = new Config();
		split_img = con.getImgUrl("b_bg.png");
		this.setLayout(con.getFlowLayout(1,0,0));
		this.setPreferredSize(new Dimension(width,height));
		logo_img = con.getImgUrl("sidebar-instructions.png");
		logo.setPreferredSize(new Dimension(logo_img.getIconWidth(),logo_img.getIconHeight()));
		logo.setIcon(logo_img);
		logo.setBackground(con.getLeftBgColor());
		addPanel(width,height);
	}
	
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
		//confirmationPanelInUpPanel.setLayout(con.getFlowLayout(1,0,10));
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
		blank = new MyLabel(con,"");
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
	
	private void createButtonGroups() {
		// TODO Auto-generated method stub
		benchmarkFunctionGroupButton = new  ButtonGroup();//
		squareFunction = new MyRadioButton(con,"radio-function-square.png","radio-function-square-hover.png","radio-function-square-selected.png","Square Function",frame);
		squareFunction.setSelected(true);
		sinFunction = new MyRadioButton(con,"radio-function-sin.png","radio-function-sin-hover.png","radio-function-sin-selected.png","Sin Function",frame);
		logFunction = new MyRadioButton(con,"radio-function-log.png","radio-function-log-hover.png","radio-function-log-selected.png","Log Function",frame);
		benchmarkFunctionGroupButton.add(squareFunction);
		benchmarkFunctionGroupButton.add(sinFunction);
		benchmarkFunctionGroupButton.add(logFunction);

		heuristicSelectionGroupButton = new ButtonGroup();
		simpleRandom = new MyRadioButton(con,"radio-simplerandom.png","radio-simplerandom-hover.png","radio-simplerandom-selected.png","Simple Random",frame);
		simpleRandom.setSelected(true);
		greedyRandom = new MyRadioButton(con,"radio-greedyrandom.png","radio-greedyrandom-hover.png","radio-greedyrandom-selected.png","Greedy Random",frame);
		reinforcementLearning = new MyRadioButton(con,"radio-reinforcementlearning.png","radio-reinforcementlearning-hover.png","radio-reinforcementlearning-selected.png","Reinforcement Learning",frame);
		heuristicSelectionGroupButton.add(simpleRandom);
		heuristicSelectionGroupButton.add(greedyRandom);
		heuristicSelectionGroupButton.add(reinforcementLearning);

		inverse = new MyCheckBox(con,"checkbox-inverse.png","checkbox-inverse-hover.png","checkbox-inverse-selected.png","Inverse",frame);
		inverse.setSelected(true);
		reverse = new MyCheckBox(con,"checkbox-reverse.png","checkbox-reverse-hover.png","checkbox-reverse-selected.png","Reverse",frame);
		reverse.setSelected(true);
		shift = new MyCheckBox(con,"checkbox-shift.png","checkbox-shift-hover.png","checkbox-shift-selected.png","Shift",frame);
		shift.setSelected(true);
		flipOneBit = new MyCheckBox(con,"checkbox-fliponebit.png","checkbox-fliponebit-hover.png","checkbox-fliponebit-selected.png","Flip One Bit",frame);
		flipOneBit.setSelected(true);
		steepestGradient = new MyCheckBox(con,"checkbox-steepestgradient.png","checkbox-steepestgradient-hover.png","checkbox-steepestgradient-selected.png","Steepest Gradient",frame);
		steepestGradient.setSelected(true);


		acceptanceMethodGroupButton = new ButtonGroup();
		onlyImproving = new MyRadioButton(con,"radio-onlyimproving.png","radio-onlyimproving-hover.png","radio-onlyimproving-selected.png","Only Improving",frame);
		onlyImproving.setSelected(true);
		improvingEqual= new MyRadioButton(con,"radio-improvingequal.png","radio-improvingequal-hover.png","radio-improvingequal-selected.png","Improving or Equal",frame);
		allMovesAccepted = new MyRadioButton(con,"radio-allmoves.png","radio-allmoves-hover.png","radio-allmoves-selected.png","All Moves Accepted",frame);
		acceptanceMethodGroupButton.add(onlyImproving);
		acceptanceMethodGroupButton.add(improvingEqual);
		acceptanceMethodGroupButton.add(allMovesAccepted);

	}
	
}




