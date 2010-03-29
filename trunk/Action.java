import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;




public class Action implements ActionListener{
	VSHMainFrame frame;
	JButton button;
	//======================
	//配置类
	Config con ;
	Dimension sdm ;//分辨率
	//======================
	//皮肤样式夹
	String img_dir = "skin_black";
	//======================
	//重画类
	//RepaintActionDo action ;
	//======================
	
	
	//初始化
	Action(VSHMainFrame f,JButton btn)
	{
		this.frame = f;
		this.button = btn;
		//配置类
		con = new Config();
	//	action = new RepaintActionDo(f);
		//皮肤文件
		img_dir = con.getSkinDir();
		//分辨率
		sdm = con.getScreenSize();
	}
	//======================
	//事件处理
	public void actionPerformed(ActionEvent e) 
	{
		

		//==================
		//窗口最小化
		 if(e.getActionCommand().equals("cmd_min"))
		{
			frame.setExtendedState(frame.ICONIFIED|frame.getExtendedState());
		}
		//==================
		//窗口关闭
		else if(e.getActionCommand().equals("cmd_close"))
		{
			System.exit(0);
		}

		
		
		else if(e.getActionCommand().equals("Benchmark Function")){
			if(frame.panel.m_panel.initalMenuPanel.benchmarkFunctionMenu)
				return;	

			frame.panel.m_panel.p_left.menuPanelInUpPanel.removeAll();
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.squareFunction);
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.sinFunction);
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.logFunction);
			frame.panel.m_panel.initalMenuPanel.benchmarkFunctionMenu = true;
			frame.panel.m_panel.initalMenuPanel.heuristicSelectionMenu = false;
			frame.panel.m_panel.initalMenuPanel.lowLevelHeuristicsMenu = false;
			frame.panel.m_panel.initalMenuPanel.acceptanceMethodMenu = false;
			//frame.update(frame.getGraphics());
			frame.validate();
		}
		
		else if(e.getActionCommand().equals("Heuristic Selection")){
			if(frame.panel.m_panel.initalMenuPanel.heuristicSelectionMenu)
				return;	
			frame.panel.m_panel.p_left.menuPanelInUpPanel.removeAll();
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.simpleRandom);
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.greedyRandom);
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.reinforcementLearning);
			
			frame.panel.m_panel.initalMenuPanel.heuristicSelectionMenu = true;	
			frame.panel.m_panel.initalMenuPanel.benchmarkFunctionMenu = false;
			frame.panel.m_panel.initalMenuPanel.lowLevelHeuristicsMenu = false;
			frame.panel.m_panel.initalMenuPanel.acceptanceMethodMenu = false;
			//frame.update(frame.getGraphics());
			frame.validate();
		}
		
		
		else if(e.getActionCommand().equals("Low Level Heuristics")){
			if(frame.panel.m_panel.initalMenuPanel.lowLevelHeuristicsMenu)
				return;	
			frame.panel.m_panel.p_left.menuPanelInUpPanel.removeAll();
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.inverse);
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.reverse);
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.shift);
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.flipOneBit);
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.steepestGradient);
			
			frame.panel.m_panel.initalMenuPanel.heuristicSelectionMenu = false;	
			frame.panel.m_panel.initalMenuPanel.benchmarkFunctionMenu = false;
			frame.panel.m_panel.initalMenuPanel.lowLevelHeuristicsMenu = true;
			frame.panel.m_panel.initalMenuPanel.acceptanceMethodMenu = false;
			//frame.update(frame.getGraphics());
			frame.validate();
		}
		
		else if(e.getActionCommand().equals("Acceptance Method")){
			if(frame.panel.m_panel.initalMenuPanel.acceptanceMethodMenu)
				return;	
			frame.panel.m_panel.p_left.menuPanelInUpPanel.removeAll();
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.onlyImproving);
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.improvingEqual);
			
			frame.panel.m_panel.p_left.menuPanelInUpPanel.add(frame.panel.m_panel.p_left.allMovesAccepted);
			
			frame.panel.m_panel.initalMenuPanel.heuristicSelectionMenu = false;	
			frame.panel.m_panel.initalMenuPanel.benchmarkFunctionMenu = false;
			frame.panel.m_panel.initalMenuPanel.lowLevelHeuristicsMenu = false;
			frame.panel.m_panel.initalMenuPanel.acceptanceMethodMenu = true;
			//frame.update(frame.getGraphics());
			frame.validate();			
		}
		
		
		else if(e.getActionCommand().equals("confirm")){
			if(frame.panel.m_panel.initalMenuPanel.benchmarkFunctionMenu){
				if(frame.panel.m_panel.p_left.squareFunction.isSelected()){
					frame.panel.m_panel.initalMenuPanel.benchmarkFunctionInfo.setText("f(x) = x ^ 2");
					frame.vsh.functionNmae = "f(x)=x^2";
				}else if(frame.panel.m_panel.p_left.sinFunction.isSelected()){
					frame.panel.m_panel.initalMenuPanel.benchmarkFunctionInfo.setText("f(x) = sin (x*PI/10000)");
					frame.vsh.functionNmae = "f(x)=sinx";
				}else if(frame.panel.m_panel.p_left.logFunction.isSelected()){
					frame.panel.m_panel.initalMenuPanel.benchmarkFunctionInfo.setText("f(x) = log (x+1)");
					frame.vsh.functionNmae = "f(x)=logx";
				}

			}else if(frame.panel.m_panel.initalMenuPanel.heuristicSelectionMenu){
				if(frame.panel.m_panel.p_left.simpleRandom.isSelected()){
					frame.panel.m_panel.initalMenuPanel.heuristicSelectionInfo.setText("Simple Random");
					frame.vsh.heuristicSelectionName = "Simple Random";
				}else if(frame.panel.m_panel.p_left.greedyRandom.isSelected()){
					frame.panel.m_panel.initalMenuPanel.heuristicSelectionInfo.setText("Greedy Random");
					frame.vsh.heuristicSelectionName = "Greedy Random";
				}else if(frame.panel.m_panel.p_left.reinforcementLearning.isSelected()){
					frame.panel.m_panel.initalMenuPanel.heuristicSelectionInfo.setText("Reinforcement Learning");
					frame.vsh.heuristicSelectionName = "Reinforcement Learning";
				}
			}else if(frame.panel.m_panel.initalMenuPanel.lowLevelHeuristicsMenu){
				String output = "";
				Vector <String> names = new Vector <String>();
				if(frame.panel.m_panel.p_left.inverse.isSelected()){
					frame.panel.m_panel.p_left.countLowLevelHeuristics++;
					output = "" + "Inverse";
					names.add("Inverse");
				}
				if(frame.panel.m_panel.p_left.reverse.isSelected()){
					names.add("Reverse");
					frame.panel.m_panel.p_left.countLowLevelHeuristics++;
					if(!output.equals(""))
						output = "Inverse, Reverse";
					else
						output = "" + "Reverse";
				}
				if(frame.panel.m_panel.p_left.shift.isSelected()){
					names.add("Shift");
					if(!output.equals(""))
							output =  output +",Shift";
					else
						output = "" + "Shift";
					frame.panel.m_panel.p_left.countLowLevelHeuristics++;
					
				}
				if(frame.panel.m_panel.p_left.flipOneBit.isSelected()){
					names.add("Flip One Bit");
					if(!output.equals("")){
						if(frame.panel.m_panel.p_left.countLowLevelHeuristics==2){
							output = output +",Flip One Bit";
						}else if(frame.panel.m_panel.p_left.countLowLevelHeuristics==3){
							output = "<html><p>Inverse,Reverse,Flip One Bit</p></p>Shift</p></html>";
						}else if(frame.panel.m_panel.p_left.countLowLevelHeuristics==1){
							output = output +",Flip One Bit";
						}
					}else
						output = "" + "Flip One Bit";
							
					frame.panel.m_panel.p_left.countLowLevelHeuristics++;		
				}
				if(frame.panel.m_panel.p_left.steepestGradient.isSelected()){
					names.add("Steepest Gradien");
					if(!output.equals("")){
						if(frame.panel.m_panel.p_left.countLowLevelHeuristics==2){
							output = "<html><p>"+output +",</p></p>Steepest Gradient</p></html>";
						}else if(frame.panel.m_panel.p_left.countLowLevelHeuristics==3){
							output = "<html><p>Inverse,Reverse</p></p>Shift,Steepest Gradient</p></html>";
						}else if(frame.panel.m_panel.p_left.countLowLevelHeuristics==1){
							if(frame.panel.m_panel.p_left.flipOneBit.isSelected())
								output = "<html><p>"+output +",</p></p>Steepest Gradient</p></html>";
							else
								output = output+",Steepest Gradien";
						}else if(frame.panel.m_panel.p_left.countLowLevelHeuristics==4){
							output = "<html><p>Inverse,Reverse,Flip One Bit</p></p>Shift,Steepest Gradient</p></html>";
						}
					}else
						output = "" + "Steepest Gradient";
				}
				String[] lowLevelHeuristicNames = new String[names.size()];
				for(int i=0;i<names.size();i++){
					lowLevelHeuristicNames[i] = names.get(i);
				}
				frame.vsh.lowLevelHeuristicNames = lowLevelHeuristicNames;
				frame.panel.m_panel.p_left.countLowLevelHeuristics = 0;
				frame.panel.m_panel.initalMenuPanel.lowLevelHeuristicsInfo.setText(output);
			}else if(frame.panel.m_panel.initalMenuPanel.acceptanceMethodMenu){
				if(frame.panel.m_panel.p_left.onlyImproving.isSelected()){
					frame.vsh.acceptanceMethodName = "Only Improving";
					frame.panel.m_panel.initalMenuPanel.acceptanceMethodInfo.setText("Only Improving");
				}else if(frame.panel.m_panel.p_left.improvingEqual.isSelected()){
					frame.vsh.acceptanceMethodName = "Improving or Equal";
					frame.panel.m_panel.initalMenuPanel.acceptanceMethodInfo.setText("Improving and Equal");
				}else if(frame.panel.m_panel.p_left.allMovesAccepted.isSelected()){
					frame.vsh.acceptanceMethodName = "All Moves Accepted";
					frame.panel.m_panel.initalMenuPanel.acceptanceMethodInfo.setText("All Moves Accepted");
				}
			}
			
			
		}else if(e.getActionCommand().equals("play")){
			frame.panel.m_panel.initalMenuPanel.benchmarkFunction.setEnabled(false);
			frame.panel.m_panel.initalMenuPanel.heuristicSelection.setEnabled(false);
			frame.panel.m_panel.initalMenuPanel.lowLevelHeuristics.setEnabled(false);
			frame.panel.m_panel.initalMenuPanel.acceptanceMethod.setEnabled(false);
			frame.panel.m_panel.p_left.confirm.setEnabled(false);
			if(!frame.start){
				frame.vsh.buildHyperHeuristic();
			}
			if(!frame.panel.m_panel.animationPanel.drawBackgroundPic )
				frame.panel.m_panel.animationPanel.removeAll();
			frame.panel.m_panel.animationPanel.drawBackgroundPic = true;	
			frame.validate();
			frame.stop = false;
			frame.play();
			frame.pause = false;
			frame.start = true;
			
		}else if(e.getActionCommand().equals("pause")){
			frame.pause = true;
		}else if(e.getActionCommand().equals("stop")){
			frame.panel.m_panel.initalMenuPanel.benchmarkFunction.setEnabled(true);
			frame.panel.m_panel.initalMenuPanel.heuristicSelection.setEnabled(true);
			frame.panel.m_panel.initalMenuPanel.lowLevelHeuristics.setEnabled(true);
			frame.panel.m_panel.initalMenuPanel.acceptanceMethod.setEnabled(true);
			frame.panel.m_panel.p_left.confirm.setEnabled(true);
			frame.start = false;
			frame.stop = true;
			frame.vsh.sleepTime = 10;
			if(frame.pause)
			frame.play();
		}else if(e.getActionCommand().equals("speed up")){
			if(frame.start){
				if(frame.vsh.sleepTime!=0)
				frame.vsh.sleepTime--;
			}
		}else if(e.getActionCommand().equals("slow down")){
			if(frame.start)
				frame.vsh.sleepTime++;			
		}
	}


}
