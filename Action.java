import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;




public class Action implements ActionListener{
	VSHMainFrame frame;
	JButton button;

	Config con ;
	Dimension sdm ;

	String img_dir = "skin_black";

	
	

	Action(VSHMainFrame f,JButton btn)
	{
		this.frame = f;
		this.button = btn;
		con = new Config();

		img_dir = con.getSkinDir();
		sdm = con.getScreenSize();
	}

	public void actionPerformed(ActionEvent e) 
	{
		

		if(e.getActionCommand().equals("cmd_max"))
		{
			fullFrame();
		}

		if(e.getActionCommand().equals("cmd_normal"))
		{
			normalFrame();
		}

		else if(e.getActionCommand().equals("cmd_min"))
		{
			frame.setExtendedState(frame.ICONIFIED|frame.getExtendedState());
		}

		else if(e.getActionCommand().equals("cmd_close"))
		{
			System.exit(0);
		}

		else if(e.getActionCommand().equals("lmenu_close"))
		{
			frame.closeLeftMenu();
		}

		else if(e.getActionCommand().equals("lmenu_open"))
		{
			frame.openLeftMenu();
		}

		else if(e.getActionCommand().equals("lmenu_add"))
		{
			addSubMenu();
		}
		
		
		else if(e.getActionCommand().equals("Benchmark Function")){
			if(frame.panel.m_panel.initalMenuPanel.benchmarkFunctionMenu)
				return;	

			frame.panel.m_panel.p_left.menuPanelInUpPanel.removeAll();
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.squareFunction);
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.sinFunction);
			
			frame.panel.m_panel.initalMenuPanel.benchmarkFunctionMenu = true;
			frame.panel.m_panel.initalMenuPanel.heuristicSelectionMenu = false;
			frame.panel.m_panel.initalMenuPanel.lowLevelHeuristicsMenu = false;
			frame.panel.m_panel.initalMenuPanel.acceptanceMethodMenu = false;
			frame.update(frame.getGraphics());
			frame.validate();
		}
		
		else if(e.getActionCommand().equals("Heuristic Selection")){
			if(frame.panel.m_panel.initalMenuPanel.heuristicSelectionMenu)
				return;	
			frame.panel.m_panel.p_left.menuPanelInUpPanel.removeAll();
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.simpleRandom);
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.greedyRandom);
			
			frame.panel.m_panel.initalMenuPanel.heuristicSelectionMenu = true;	
			frame.panel.m_panel.initalMenuPanel.benchmarkFunctionMenu = false;
			frame.panel.m_panel.initalMenuPanel.lowLevelHeuristicsMenu = false;
			frame.panel.m_panel.initalMenuPanel.acceptanceMethodMenu = false;
			frame.update(frame.getGraphics());
			frame.validate();
		}
		
		
		else if(e.getActionCommand().equals("Low Level Heuristics")){
			if(frame.panel.m_panel.initalMenuPanel.lowLevelHeuristicsMenu)
				return;	
			frame.panel.m_panel.p_left.menuPanelInUpPanel.removeAll();
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.inverse);
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.reverse);
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.shift);
			
			frame.panel.m_panel.initalMenuPanel.heuristicSelectionMenu = false;	
			frame.panel.m_panel.initalMenuPanel.benchmarkFunctionMenu = false;
			frame.panel.m_panel.initalMenuPanel.lowLevelHeuristicsMenu = true;
			frame.panel.m_panel.initalMenuPanel.acceptanceMethodMenu = false;
			frame.update(frame.getGraphics());
			frame.validate();
		}
		
		else if(e.getActionCommand().equals("Acceptance Method")){
			if(frame.panel.m_panel.initalMenuPanel.acceptanceMethodMenu)
				return;	
			frame.panel.m_panel.p_left.menuPanelInUpPanel.removeAll();
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.onlyImproving);
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.improvingEqual);
			
			frame.panel.m_panel.initalMenuPanel.heuristicSelectionMenu = false;	
			frame.panel.m_panel.initalMenuPanel.benchmarkFunctionMenu = false;
			frame.panel.m_panel.initalMenuPanel.lowLevelHeuristicsMenu = false;
			frame.panel.m_panel.initalMenuPanel.acceptanceMethodMenu = true;
			frame.update(frame.getGraphics());
			frame.validate();			
		}
		
		
		else if(e.getActionCommand().equals("confirm")){
			if(frame.panel.m_panel.initalMenuPanel.benchmarkFunctionMenu){
				if(frame.panel.m_panel.p_left.squareFunction.isSelected()){
					frame.panel.m_panel.initalMenuPanel.benchmarkFunctionInfo.setText("f(x) = x ^ 2");
				}else if(frame.panel.m_panel.p_left.sinFunction.isSelected()){
					frame.panel.m_panel.initalMenuPanel.benchmarkFunctionInfo.setText("f(x) = sin x");
				}

			}else if(frame.panel.m_panel.initalMenuPanel.heuristicSelectionMenu){
				if(frame.panel.m_panel.p_left.simpleRandom.isSelected()){
					frame.panel.m_panel.initalMenuPanel.heuristicSelectionInfo.setText("<html><p>Simple </p></p>Random</p></html>");
				}else if(frame.panel.m_panel.p_left.greedyRandom.isSelected()){
					frame.panel.m_panel.initalMenuPanel.heuristicSelectionInfo.setText("<html><p>Greedy </p></p>Random</p></html>");
				}				
			}else if(frame.panel.m_panel.initalMenuPanel.lowLevelHeuristicsMenu){
				String output = "";
				if(frame.panel.m_panel.p_left.inverse.isSelected()){
					output = "" + "Inverse";
				}
				if(frame.panel.m_panel.p_left.reverse.isSelected()){
					if(!output.equals(""))
						output = "<html><p>"+output +",</p></p>Reverse</p></html>";
					else
						output = "" + "Reverse";
				}
				if(frame.panel.m_panel.p_left.shift.isSelected()){
					if(!output.equals(""))
						if(output.endsWith("</html>"))
								output = "<html><p>Inverse,</p></p>Reverse,Shift</p></html>";
						else
							output =  "<html><p>"+output +",</p></p>Shift</p></html>";
					else
						output = "" + "Shift";
					
					
				}
				frame.panel.m_panel.initalMenuPanel.lowLevelHeuristicsInfo.setText(output);
			}else if(frame.panel.m_panel.initalMenuPanel.acceptanceMethodMenu){
				if(frame.panel.m_panel.p_left.onlyImproving.isSelected()){
					frame.panel.m_panel.initalMenuPanel.acceptanceMethodInfo.setText("<html><p>Only </p></p>Improving</p></html>");
				}else if(frame.panel.m_panel.p_left.improvingEqual.isSelected()){
					frame.panel.m_panel.initalMenuPanel.acceptanceMethodInfo.setText("<html><p>Improving </p></p>and Equal</p></html>");
				}					
			}
			
			
		}
	}

	private void fullFrame()
	{

		con.resetFrameSize(frame,sdm.width,sdm.height);
		con.fullAllWaysTop(frame);
		frame.isFullScreen = true;
		button.setIcon(con.getImgUrl(("maxed.png")));
		button.setRolloverIcon(con.getImgUrl(("maxed_on.png")));
		frame.validate();
		frame.setLocation((sdm.width-frame.getWidth())/2, (sdm.height-frame.getHeight())/2);
		button.setActionCommand("cmd_normal");
		button.setToolTipText("正常模式");

		frame.validate();
	}

	private void normalFrame()
	{
		Dimension fdm = con.getFrameSmallSize(false);
		con.resetFrameSize(frame,fdm.width,fdm.height);
		con.normalAllWaysTop(frame);
		frame.isFullScreen = false;
		
		button.setIcon(con.getImgUrl(("max.png")));
		button.setRolloverIcon(con.getImgUrl(("max_on.png")));
	
		frame.validate();
		frame.setLocation((sdm.width-frame.getWidth())/2, (sdm.height-frame.getHeight())/2);
		button.setActionCommand("cmd_max");
		button.setToolTipText("全屏模式");

		frame.validate();

	private void addSubMenu()
	{

		if(!frame.lmenu_isopen)
		{
			frame.openLeftMenu();
		}
		frame.validate();
	}
}
