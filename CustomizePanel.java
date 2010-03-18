import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class CustomizePanel extends JPanel{
  
	int width,height;
	
	JPanel upPanel , barPanel , downPanel;
	JPanel blankPanel,menuPanelInUpPanel, confirmationPanelInUpPanel;
	Config con ;
	
	ImageIcon split_img ;
	
	VSHMainFrame frame;
	
	
	ButtonGroup   benchmarkFunctionGroupButton, heuristicSelectionGroupButton, acceptanceMethodGroupButton;
	MyRadioButton squareFunction,sinFunction;
	MyRadioButton simpleRandom,greedyRandom;
	MyCheckBox inverse, reverse,shift;

	MyRadioButton onlyImproving, improvingEqual; 
	
	MyButton confirm;
	
	MyLabel candidateSolution,newSolution,lowLevelHeuristic,count;
	
	CustomizePanel(VSHMainFrame frame,int width,int height)
	{
		//=============================

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
			upPanel.add(menuPanelInUpPanel,BorderLayout.CENTER);
			confirmationPanelInUpPanel = new JPanel();
			confirmationPanelInUpPanel.setBackground(con.getLeftBgColor());
			//confirmationPanelInUpPanel.setLayout(con.getFlowLayout(1,0,10));
			confirm = new MyButton (con,"confirm_on.png","confirm_pressed.png","Confirm");
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
			newSolution = new MyLabel(con,"New Solution");
			lowLevelHeuristic = new MyLabel(con,"Low Level Heuristic");
			count = new MyLabel(con,"Count");
			downPanel.add(candidateSolution);
			downPanel.add(newSolution);
			downPanel.add(lowLevelHeuristic);
			downPanel.add(count);
			this.add(downPanel);
		}
		private void createButtonGroups() {
			// TODO Auto-generated method stub
			benchmarkFunctionGroupButton = new  ButtonGroup();//
			squareFunction = new MyRadioButton(con,"square_function_on.png","square_function_pressing.png","square_function_pressed.png","Square Function");
			squareFunction.setSelected(true);
			sinFunction = new MyRadioButton(con,"sin_function_on.png","sin_function_pressing.png","sin_function_pressed.png","Sin Function");
			benchmarkFunctionGroupButton.add(squareFunction);
			benchmarkFunctionGroupButton.add(sinFunction);
			
			heuristicSelectionGroupButton = new ButtonGroup();
			simpleRandom = new MyRadioButton(con,"simple_random_on.png","simple_random_pressing.png","simple_random_pressed.png","Simple Random");
			simpleRandom.setSelected(true);
			greedyRandom = new MyRadioButton(con,"greedy_random_on.png","greedy_random_pressing.png","greedy_random_pressed.png","Greedy Random");
			heuristicSelectionGroupButton.add(simpleRandom);
			heuristicSelectionGroupButton.add(greedyRandom);
			
			
			inverse = new MyCheckBox(con,"inverse_on.png","inverse_pressing.png","inverse_pressed.png","Inverse");
			inverse.setSelected(true);
			reverse = new MyCheckBox(con,"reverse_on.png","reverse_pressing.png","reverse_pressed.png","Reverse");
			shift = new MyCheckBox(con,"shift_on.png","shift_pressing.png","shift_pressed.png","Shift");

			
			acceptanceMethodGroupButton = new ButtonGroup();
			onlyImproving = new MyRadioButton(con,"only_improving_on.png","only_improving_pressing.png","only_improving_pressed.png","Only Improving");
			onlyImproving.setSelected(true);
			improvingEqual= new MyRadioButton(con,"improving_euqal_on.png","improving_equal_pressing.png","improving_equal_pressed.png","Improving or Equal"); 
			acceptanceMethodGroupButton.add(onlyImproving);
			acceptanceMethodGroupButton.add(improvingEqual);
			
		}	
	}

	
	
	
