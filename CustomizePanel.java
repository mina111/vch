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
	MyRadioButton squareFunction,sinFunction,logFunction;
	MyRadioButton simpleRandom,greedyRandom,reinforcementLearning;
	MyCheckBox inverse, reverse,shift,flipOneBit,steepestGradient;

	MyRadioButton onlyImproving, improvingEqual,allMovesAccepted;

	MyButton confirm;

	MyLabel candidateSolution,newSolution,lowLevelHeuristic,accepted,count;
	MyLabel candidateSolutionContent,newSolutionContent,lowLevelHeuristicContent,acceptedContent,countContent;
	MyLabel blank;
	int countLowLevelHeuristics = 0;
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
		split_img = con.getImgUrl("b_bg.png");//因为在JButton前就要用,所以在前面先实例化
		this.setLayout(con.getFlowLayout(1,0,0));
		this.setPreferredSize(new Dimension(width,height));
		addPanel(width,height);
	}
		void addPanel(int width,int height){
			int splitbarheight = con.getSplitBarHeight() ;;
			int upHeight = con.getUpMenuHeight(height) ;
			int downHeight = height - splitbarheight - upHeight; // 计算右边剩余宽度

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
			squareFunction = new MyRadioButton(con,"square_function_on.png","square_function_pressing.png","square_function_pressed.png","Square Function");
			squareFunction.setSelected(true);
			sinFunction = new MyRadioButton(con,"sin_function_on.png","sin_function_pressing.png","sin_function_pressed.png","Sin Function");
			logFunction = new MyRadioButton(con,"log_function_on.png","log_function_pressing.png","log_function_pressed.png","Log Function");
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

			inverse = new MyCheckBox(con,"inverse_on.png","inverse_pressing.png","inverse_pressed.png","Inverse");
			inverse.setSelected(true);
			reverse = new MyCheckBox(con,"reverse_on.png","reverse_pressing.png","reverse_pressed.png","Reverse");
			shift = new MyCheckBox(con,"shift_on.png","shift_pressing.png","shift_pressed.png","Shift");
			flipOneBit = new MyCheckBox(con,"flip_one_bit_on.png","flip_one_bit_pressing.png","flip_one_bit_pressed.png","Flip One Bit");
			steepestGradient = new MyCheckBox(con,"steepest_gradient_on.png","steepest_gradient_pressing.png","steepest_gradient_pressed.png","Steepest Gradient");


			acceptanceMethodGroupButton = new ButtonGroup();
			onlyImproving = new MyRadioButton(con,"only_improving_on.png","only_improving_pressing.png","only_improving_pressed.png","Only Improving");
			onlyImproving.setSelected(true);
			improvingEqual= new MyRadioButton(con,"improving_euqal_on.png","improving_equal_pressing.png","improving_equal_pressed.png","Improving or Equal");
			allMovesAccepted = new MyRadioButton(con,"all_moves_accepted_on.png","all_moves_accepted_pressing.png","all_moves_accepted_pressed.png","All Moves Accepted");
			acceptanceMethodGroupButton.add(onlyImproving);
			acceptanceMethodGroupButton.add(improvingEqual);
			acceptanceMethodGroupButton.add(allMovesAccepted);

		}
	}




