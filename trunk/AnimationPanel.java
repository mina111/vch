import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class AnimationPanel extends JPanel {
	boolean drawBackgroundPic = false;
	VSHMainFrame f;
	Config con;
	ImageIcon animation_bg_img;
	ImageIcon selected_mark_img;
	ImageIcon reverse_animation_img, inverse_animation_img, shift_animation_img, flip_one_bit_img, steepest_gradient_img;
	
	final int ORIGINAL_WIDTH = 860;
	final int ORIGINAL_HEIGHT = 608;
	int lowLevelHeuristicBoxX = 41,lowLevelHeuristicBoxY = 185;
	int reverse_y,inverse_y,shift_y,flip_one_bit_y,steepest_gradient_y;
	int bitSpace1 = 7;
	int bitSpace2 = 10;
	int remainTime = 100;
	int x = 47;
	int x1 = 47;
	int y1 = 54;
	int y2 = 150;
	int x2 = 185;
	int y3 = 265;
	int y4 = 265;
	int y5 = 345;
	int x3 = 0;
	int y6 = 200;
	int x4 = bitSpace2;
	int x6 = 0;
	int count = 0;
	int y7 = 230;
	int y8 = 230;
	int x7 = 228;
	int y9 = 80;
	int y11 = 90;
	int x10 = 593;
	int y12 = 30;
	int x11 = 610;
	int x12 = 185;
	int y13 = 265;
	int y14=0;
	public boolean animationFinished = false;
	
	
	
	int lowLevelHeuristicsCount = 0;
	int boundaryMinX = 0;
	int boundaryMaxX = 32768;
	int OFFSETX = 421;
	int OFFSETY = 172;
	double GRAPHX = 400;
	double GRAPHY = 400;
	double SCALEY = (32768 * 32768) / GRAPHY;
	double SCALEX = 32768 / GRAPHX;
	double y10 =0;
	double x9 = 0;
	public boolean drawToAcceptanceMethod = false,drawAcceptanceMethod= false;
	
	Font F2=new Font("Arial",Font.PLAIN,12);
    Font F4=new Font("Arial",Font.PLAIN,16);
    String newSolutionString = "";
	public AnimationPanel(VSHMainFrame frame,Config con){
		f = frame;
		this.con = con;
		animation_bg_img = con.getImgUrl("animation_bg1.png");
		selected_mark_img = con.getImgUrl("selected_mark.png");
		reverse_animation_img = con.getImgUrl("reverse_animation.png");
		inverse_animation_img = con.getImgUrl("inverse_animation.png");
		shift_animation_img = con.getImgUrl("shift_animation.png");
		flip_one_bit_img = con.getImgUrl("flip_one_bit_animation.png");
		steepest_gradient_img = con.getImgUrl("steepest_gradient_animation.png");
		
	}
	
	public void paintComponent(Graphics g){
	    
		Graphics2D g2D = (Graphics2D)g;
		
		super.paintComponent(g2D);
		if(drawBackgroundPic){
			 
			
			Font F3 = new Font ("Comic Sans MS", Font.PLAIN,12);
			 String candidateSolutionString = "";
			 newSolutionString = "";
			 f.panel.m_panel.p_left.newSolutionContent.setText(newSolutionString);
			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setText("");
			 f.panel.m_panel.p_left.acceptedContent.setText("");
				
			 for(int i=0;i<f.vsh.candidateSolution.length;i++){
				 candidateSolutionString = candidateSolutionString+f.vsh.candidateSolution[i];
			 }
			 f.panel.m_panel.p_left.candidateSolutionContent.setForeground(Color.RED);
			 f.panel.m_panel.p_left.candidateSolutionContent.setText(candidateSolutionString);
			 f.panel.m_panel.p_left.countContent.setForeground(new Color(102,255,255));
			 f.panel.m_panel.p_left.countContent.setText(""+f.vsh.count);
			 g2D.drawImage(animation_bg_img.getImage(),0,0, this.getWidth(), this.getHeight(), null);
			 
			 
		     this.setOpaque(true);
		     
		     
		     g2D.setFont(F3);
			 g2D.setColor(Color.RED);
			 g2D.drawString("Candidate Solution", 400, 590);
			 g2D.drawLine(510, 585, 550,585);
			 g2D.setColor(Color.YELLOW);
			 g2D.drawString("New Solution", 560, 590);
			 g2D.drawLine(640, 585, 680,585);
			 g2D.setColor(Color.GREEN);
			 g2D.drawString("Previous Solutions", 690, 590);
			 g2D.drawLine(800, 585, 840,585);			 
		     g.setColor(new Color(214,214,214));
		     if(HyperHeuristic.function.getName().equals("f(x)=sinx")||HyperHeuristic.function.getName().equals("f(x)=sinx^2")){
		    	 GRAPHY = 200;
		    	 g2D.draw(new Line2D.Double(0+OFFSETX, 0+OFFSETY, 0+OFFSETX, GRAPHY+OFFSETY+GRAPHY));
			     g2D.draw(new Line2D.Double(0+OFFSETX, GRAPHY+OFFSETY, GRAPHX+OFFSETX,GRAPHY+OFFSETY));
		     }
		     g2D.draw(new Line2D.Double(0+OFFSETX, 0+OFFSETY, 0+OFFSETX, GRAPHY+OFFSETY));
		     g2D.draw(new Line2D.Double(0+OFFSETX, GRAPHY+OFFSETY, GRAPHX+OFFSETX,GRAPHY+OFFSETY));
		     if(HyperHeuristic.function.getName().equals("f(x)=x^2")){
		    	 SCALEY = (32768 * 32768) / GRAPHY; 
	             for (int x=0; x <= boundaryMaxX; x=x+10){        	 
	                 double value = HyperHeuristic.function.evaluateInteger(x);
	                 g2D.draw(new Line2D.Double((x/SCALEX)+OFFSETX, (GRAPHY-(value/SCALEY))+OFFSETY, (x/SCALEX)+OFFSETX, (GRAPHY-(value/SCALEY))+OFFSETY));
	             }	
		     }else if(HyperHeuristic.function.getName().equals("f(x)=sinx")||HyperHeuristic.function.getName().equals("f(x)=sinx^2")){
		    	 SCALEY = 1/GRAPHY; 
	             for (int x=0; x <= boundaryMaxX; x=x+10){        	 
	                 double value = HyperHeuristic.function.evaluateInteger(x);
	                 g2D.draw(new Line2D.Double((x/SCALEX)+OFFSETX, (GRAPHY-(value/SCALEY))+OFFSETY, (x/SCALEX)+OFFSETX, (GRAPHY-(value/SCALEY))+OFFSETY));
	             }	
		     }else if(HyperHeuristic.function.getName().equals("f(x)=logx")){
		    	 SCALEY = Math.log(1+boundaryMaxX) / GRAPHY; 
	             for (int x=0; x <= boundaryMaxX; x=x+10){        	 
	                 double value = HyperHeuristic.function.evaluateInteger(x);
	                 g2D.draw(new Line2D.Double((x/SCALEX)+OFFSETX, (GRAPHY-(value/SCALEY))+OFFSETY, (x/SCALEX)+OFFSETX, (GRAPHY-(value/SCALEY))+OFFSETY));
	             }	
		     }
		     g.setColor(Color.GREEN);
		     for(int i=0;i<f.vsh.count;i++){
		    	 double value = HyperHeuristic.function.evaluate(f.vsh.history[i]);
		    	 g2D.draw(new Rectangle2D .Double((HyperHeuristic.bit2int(f.vsh.history[i])/SCALEX)+OFFSETX-2, (GRAPHY-(value/SCALEY))+OFFSETY-2, 3, 3));
		    	 g2D.draw(new Rectangle2D .Double((HyperHeuristic.bit2int(f.vsh.history[i])/SCALEX)+OFFSETX-2, (GRAPHY-(value/SCALEY))+OFFSETY-2, 2, 2));
		    	 g2D.draw(new Rectangle2D .Double((HyperHeuristic.bit2int(f.vsh.history[i])/SCALEX)+OFFSETX-2, (GRAPHY-(value/SCALEY))+OFFSETY-2, 1, 1));
		     }
		     g.setColor(Color.RED);
		     double value1 =HyperHeuristic.function.evaluate(f.vsh.candidateSolution);
		     g2D.draw(new Line2D.Double((HyperHeuristic.bit2int(f.vsh.candidateSolution)/SCALEX)+OFFSETX, GRAPHY+OFFSETY, (HyperHeuristic.bit2int(f.vsh.candidateSolution)/SCALEX)+OFFSETX, (GRAPHY-(value1/SCALEY))+OFFSETY));
		     g2D.draw(new Line2D.Double(OFFSETX, (GRAPHY-(value1/SCALEY))+OFFSETY, (HyperHeuristic.bit2int(f.vsh.candidateSolution)/SCALEX)+OFFSETX, (GRAPHY-(value1/SCALEY))+OFFSETY));
		     drawLowLevelHeuristicBox(g2D);
		     Font F1 = new Font ("Comic Sans MS", Font.PLAIN,16);
		     if(!HyperHeuristic.heuristicsSelection.getName().equals("Reinforcement Learning"))
		     g.setFont(F1);
		     g.setColor(new Color(255,0,255));
		     g2D.drawString(""+HyperHeuristic.heuristicsSelection.getName(),214, 100);
		     
		    
		     g.setFont(F3);
		     g2D.drawString(""+HyperHeuristic.acceptanceMethod.getName(),614, 90);
		     
		     g.setColor(Color.RED);
		     g.setFont(F2);
		     int temp0 = x;
		     for(int i=0;i<f.vsh.candidateSolution.length;i++){
		    	 g2D.drawString(""+f.vsh.candidateSolution[i],temp0, 54);
		    	 //System.out.print(f.vsh.candidateSolution[i]);
		    	 temp0=temp0+7;
		     }
		     //x = 47;
		     
		     if(HyperHeuristic.heuristicsSelection.getName().equals("Reinforcement Learning")){
		    	 g.setFont(F1);
		    	 for(int i=0;i<HyperHeuristic.lowLevelHeuristics.size();i++){
		    		 g2D.drawString(""+((ReinforcementLearning)HyperHeuristic.heuristicsSelection).keys[i],115, 220+80*i);
		    	 }
		    	 g.setFont(F2);
		     }
		     
		     if(y1<100){
			     for(int i=0;i<f.vsh.candidateSolution.length;i++){
			    	 g2D.drawString(""+f.vsh.candidateSolution[i],x, y1);
			    	 //System.out.print(f.vsh.candidateSolution[i]);
			    	 x=x+7;
			     }
			     x = 47;
			     y1 ++;
		     }else{
		    	 if(x1<211){
		    		 int temp = x1;
				     for(int i=0;i<f.vsh.candidateSolution.length;i++){
				    	 if(temp<202)
				    		 g2D.drawString(""+f.vsh.candidateSolution[i], temp, y1);
				    	 else
				    		 break;
				    	 //System.out.print(f.vsh.candidateSolution[i]);
				    	 temp=  temp+7;
				     }
				     x1++;
		    	 }else{
		    		 if(HyperHeuristic.heuristicsSelection.getName().equals("Simple Random")||HyperHeuristic.heuristicsSelection.getName().equals("Reinforcement Learning")){

		    		 }
		    	 }
		    	 
		     }
		     
		     //System.out.println("");
		    
		     
		    
		     f.validate();
		     // g2D.drawImage(candidate_solution_animation_img.getImage(),30+offsetX,7+offsetY, candidate_solution_animation_img.getIconWidth(), candidate_solution_animation_img.getIconHeight(), null);
		}
	}
	
	
	void drawGreedyInverse(Graphics2D g2D, int startPostion,int endPostion) {
		// TODO Auto-generated method stub
	
		
	}

	void drawLowLevelHeuristicBox(Graphics g){
		for(int i=0;i<HyperHeuristic.lowLevelHeuristics.size();i++){
			if(HyperHeuristic.lowLevelHeuristics.get(i).getName().equals("Reverse")){
				 g.drawImage(reverse_animation_img.getImage(),lowLevelHeuristicBoxX,lowLevelHeuristicBoxY, reverse_animation_img.getIconWidth(), reverse_animation_img.getIconHeight(), null);  
				 reverse_y = lowLevelHeuristicBoxY;
				 lowLevelHeuristicBoxY = lowLevelHeuristicBoxY + 80;
			}else if(HyperHeuristic.lowLevelHeuristics.get(i).getName().equals("Inverse")){
				 g.drawImage(inverse_animation_img.getImage(),lowLevelHeuristicBoxX,lowLevelHeuristicBoxY, inverse_animation_img.getIconWidth(), inverse_animation_img.getIconHeight(), null);  
				 inverse_y = lowLevelHeuristicBoxY;
				 lowLevelHeuristicBoxY = lowLevelHeuristicBoxY + 80;				
			}else if(HyperHeuristic.lowLevelHeuristics.get(i).getName().equals("Shift")){
				 g.drawImage(shift_animation_img.getImage(),lowLevelHeuristicBoxX,lowLevelHeuristicBoxY, shift_animation_img.getIconWidth(), shift_animation_img.getIconHeight(), null);  
				 shift_y = lowLevelHeuristicBoxY;
				 lowLevelHeuristicBoxY = lowLevelHeuristicBoxY + 80;					
			}else if(HyperHeuristic.lowLevelHeuristics.get(i).getName().equals("Flip One Bit")){
				 g.drawImage(flip_one_bit_img.getImage(),lowLevelHeuristicBoxX,lowLevelHeuristicBoxY, flip_one_bit_img.getIconWidth(), flip_one_bit_img.getIconHeight(), null);  
				 flip_one_bit_y = lowLevelHeuristicBoxY;
				 lowLevelHeuristicBoxY = lowLevelHeuristicBoxY + 80;			
			}else if(HyperHeuristic.lowLevelHeuristics.get(i).getName().equals("Steepest Gradient")){
				 g.drawImage(steepest_gradient_img.getImage(),lowLevelHeuristicBoxX,lowLevelHeuristicBoxY, steepest_gradient_img.getIconWidth(), steepest_gradient_img.getIconHeight(), null);  
				 steepest_gradient_y = lowLevelHeuristicBoxY;
				 lowLevelHeuristicBoxY = lowLevelHeuristicBoxY + 80;				
				
			}
		}
		lowLevelHeuristicBoxY = 185;
	}
	
	void drawToAcceptanceMethod(Graphics g){
		if(x7<716){	
			int temp = x7;
			g.setFont(F2);
			g.setColor(Color.YELLOW);
			for(int i=0;i<f.vsh.candidateSolution.length;i++ ){
				if(temp>363&&temp<557){
					g.drawString(""+f.vsh.newSolution[i],temp, y9);
				}
				temp = temp + bitSpace1;
			}
			x7++;
			
		}else{
			drawToAcceptanceMethod = true;
		}
		
		
	}
	
	void drawFinalPart(Graphics2D g2D){
		 if(!drawToAcceptanceMethod){
			 drawToAcceptanceMethod(g2D);
		 }else{
			 double value2 =HyperHeuristic.function.evaluate(f.vsh.newSolution);
			 if(value2>=0){
				 if(GRAPHY+OFFSETY-y10>(GRAPHY-(value2/SCALEY))+OFFSETY){
					 g2D.setColor(Color.YELLOW);
					 g2D.draw(new Line2D.Double((HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX, GRAPHY+OFFSETY, (HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX, GRAPHY+OFFSETY-y10));
					 y10=y10+1;;
				 }else{
					 if(x9<(HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)){
						 g2D.setColor(Color.YELLOW);
						 g2D.draw(new Line2D.Double((HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX, GRAPHY+OFFSETY, (HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX, (GRAPHY-(value2/SCALEY))+OFFSETY));
						 g2D.draw(new Line2D.Double((HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX-x9, (GRAPHY-(value2/SCALEY))+OFFSETY, (HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX, (GRAPHY-(value2/SCALEY))+OFFSETY));
						 x9=x9+1;
					 }else{
						 if(!drawAcceptanceMethod){
							 drawAcceptanceMethod(g2D);
						 }else{
							 x = 47;
							 x1 = 47;
							y1 = 54;
							 y2 = 150;
							x2 = 185;
							y3 = 265;
							y4 = 265;
							y5 = 345;
							x3 = 0;
							y6 = 200;
							x4 = bitSpace2;
							x6 = 0;
							count = 0;
							y7 = 230;
							y8 = 230;
							x7= 228;
							x9= 0;
							y10 = 0;
							remainTime=100;
							y11 = 80;
							x10 = 593;
							x11 = 610;
							y12 = 30;
							x11 = 610;
							x12 = 185;
							y13 = 265;
							y14=0;
							 animationFinished = true; 
							 drawToAcceptanceMethod = false;
							 drawAcceptanceMethod = false;
								lowLevelHeuristicsCount = 0;
						 }
					 }
				 }
			 }else{
				 if(GRAPHY+OFFSETY+y10<(GRAPHY-(value2/SCALEY))+OFFSETY){
					 g2D.setColor(Color.YELLOW);
					 g2D.draw(new Line2D.Double((HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX, GRAPHY+OFFSETY, (HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX, GRAPHY+OFFSETY+y10));
					 y10=y10+1;
				 }else{
					 if(x9<(HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)){
						 g2D.setColor(Color.YELLOW);
						 g2D.draw(new Line2D.Double((HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX, GRAPHY+OFFSETY, (HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX, (GRAPHY-(value2/SCALEY))+OFFSETY));
						 g2D.draw(new Line2D.Double((HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX-x9, (GRAPHY-(value2/SCALEY))+OFFSETY, (HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX, (GRAPHY-(value2/SCALEY))+OFFSETY));
						 x9=x9+1;
					 }else{
						 if(!drawAcceptanceMethod){
							 drawAcceptanceMethod(g2D);
						 }else{
							 x = 47;
							 x1 = 47;
							y1 = 54;
							 y2 = 150;
							x2 = 185;
							y3 = 265;
							y4 = 265;
							y5 = 345;
							x3 = 0;
							y6 = 200;
							x4 = bitSpace2;
							x6 = 0;
							count = 0;
							y7 = 230;
							y8 = 230;
							x7= 228;
							x9= 0;
							y10 = 0;
							remainTime=100;
							y11 = 80;
							x10 = 593;
							x11 = 610;
							y12 = 30;
							x11 = 610;
							x12 = 185;
							y13 = 265;
							y14=0;
							 animationFinished = true; 
							 drawToAcceptanceMethod = false;
							 drawAcceptanceMethod = false;
								lowLevelHeuristicsCount = 0;
						 }
					 }
				 }
			 }
			 
			 
		
		 }
	}
	
	void drawAcceptanceMethod(Graphics2D g){
		g.setColor(Color.YELLOW);
	     double value =HyperHeuristic.function.evaluate(f.vsh.newSolution);
	     g.draw(new Line2D.Double((HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX, GRAPHY+OFFSETY, (HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX, (GRAPHY-(value/SCALEY))+OFFSETY));
	     g.draw(new Line2D.Double(OFFSETX, (GRAPHY-(value/SCALEY))+OFFSETY, (HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX, (GRAPHY-(value/SCALEY))+OFFSETY));
		
		if(HyperHeuristic.acceptanceMethod.checkIfAcceptance(f.vsh.candidateSolution, f.vsh.newSolution)){
			f.panel.m_panel.p_left.acceptedContent.setForeground(new Color(255,102,255));
			f.panel.m_panel.p_left.acceptedContent.setText("Yes");
			g.setColor(new Color(255,0,255));
			g.drawString("Accepted", 625, 20);
			g.setFont(F2);
			if(y11>30){
				g.setColor(Color.YELLOW);
				for(int i=0;i<f.vsh.newSolution.length;i++ ){
					if(y11<60){
						g.drawString(""+f.vsh.newSolution[i],x10, y11);
					}
					x10 = x10 + bitSpace1;
				}
				x10 = 593;
				y11--;
			}else{
				if(x10>164){
					g.setColor(Color.YELLOW);
					int temp = x10;
					for(int i=0;i<f.vsh.newSolution.length;i++ ){
						g.drawString(""+f.vsh.newSolution[i],temp, 30);
						temp = temp + bitSpace1;
					}
					x10--;
				}else{
					if(x>-150){
						g.setColor(Color.YELLOW);
						int temp = x10;
						for(int i=0;i<f.vsh.newSolution.length;i++ ){
							g.drawString(""+f.vsh.newSolution[i],temp, 30);
							temp = temp + bitSpace1;
						}
						x--;
					}else{
						if(x10>50){
							g.setColor(Color.YELLOW);
							int temp = x10;
							for(int i=0;i<f.vsh.newSolution.length;i++ ){
								if(temp>164){
									g.drawString(""+f.vsh.newSolution[i],temp, 30);
								}else{
									g.setColor(Color.RED);
									g.drawString(""+f.vsh.newSolution[i],temp, 30);
									g.setColor(Color.YELLOW);
								}
								temp = temp + bitSpace1;
							}
							x10--;
						}else{
							if(y12<47){
								g.setColor(Color.RED);
								for(int i=0;i<f.vsh.newSolution.length;i++ ){
									g.drawString(""+f.vsh.newSolution[i],x10, y12);
									x10 = x10 + bitSpace1;
								}
								x10 = 50;
								y12++;
							}else{
								drawAcceptanceMethod = true;
							}
						}
					}
				}
			}
		}else{
			f.panel.m_panel.p_left.acceptedContent.setForeground(new Color(255,102,255));
			f.panel.m_panel.p_left.acceptedContent.setText("No");
			g.setColor(new Color(255,0,255));
			g.drawString("Rejected", 770, 40);
			g.setFont(F2);
			if(x11<900){
				g.setColor(Color.YELLOW);
				int temp = x11;
				for(int i=0;i<f.vsh.newSolution.length;i++ ){
					if(temp<860&&temp>750){
						g.drawString(""+f.vsh.newSolution[i],temp, 75);
					}
					temp = temp + bitSpace1;
				}
				x11++;
			}else{
				drawAcceptanceMethod = true;
			}
		}
	}
	
	
	void drawInverse(Graphics2D g2D){
		int startPostion = ((Inverse)f.vsh.lowLevelHeuristic).startPostion ;
		 int endPostion = ((Inverse)f.vsh.lowLevelHeuristic).endPostion ;
//System.out.println("startPostion"+startPostion+"endPostion"+endPostion);
		 if(y3>185){
		 
			 if(startPostion < endPostion){
				 for(int i=0;i<f.vsh.candidateSolution.length;i++){
					 if(i>=startPostion&&i<=endPostion)
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y3);
					 else
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
			    	 //System.out.print(f.vsh.candidateSolution[i]);
			    	 x2=x2+bitSpace2;
			     }
				 x2 = 185;
				 for(int i=startPostion;i<=endPostion;i++){
					 g2D.setColor(Color.YELLOW);
					 if(f.vsh.candidateSolution[i]==0)
						 g2D.drawString("1",x2+bitSpace2*startPostion, y3+80);
					 else
						 g2D.drawString("0",x2+bitSpace2*startPostion, y3+80);
					 x2 = x2 + bitSpace2;
				 }
				 x2 = 185;
				 y3--;
			 }else{
				 for(int i=0;i<f.vsh.candidateSolution.length;i++){
					 if((i>=startPostion&&i<=f.vsh.candidateSolution.length-1)||(i>=0&&i<=endPostion))
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y3);
					 else
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
			    	 //System.out.print(f.vsh.candidateSolution[i]);
			    	 x2=x2+bitSpace2;
			     }	
				 x2 = 185;
				 g2D.setColor(Color.YELLOW);
				 for(int i=startPostion;i<f.vsh.candidateSolution.length;i++){
					 if(f.vsh.candidateSolution[i]==0)
						 g2D.drawString("1",x2+bitSpace2*startPostion, y3+80);
					 else
						 g2D.drawString("0",x2+bitSpace2*startPostion, y3+80);
					 x2 = x2 + bitSpace2;
				 }	
				 x2 = 185;
				 for(int i=0;i<=endPostion;i++){
					 if(f.vsh.candidateSolution[i]==0)
						 g2D.drawString("1",x2, y3+80);
					 else
						 g2D.drawString("0",x2, y3+80);
					 x2 = x2 + bitSpace2;
				 }	
				 x2 = 185;
				 y3--;	 
			 }
		 }else{
			 for(int i=0;i<f.vsh.newSolution.length;i++){
				 newSolutionString = newSolutionString+f.vsh.newSolution[i];
			 }
			 f.panel.m_panel.p_left.newSolutionContent.setForeground(Color.YELLOW);
			 f.panel.m_panel.p_left.newSolutionContent.setText(newSolutionString);
			 	g2D.setColor(Color.YELLOW);
				 if(y4>170){

					 for(int i=0;i<f.vsh.newSolution.length;i++){
				    	 g2D.drawString(""+f.vsh.newSolution[i],x2, y4);
				    	 //System.out.print(f.vsh.candidateSolution[i]);
				    	 x2=x2+bitSpace2;
				     }
				     x2 = 185;
				     y4--;
				 }else{
					 drawFinalPart(g2D);
				 }
			 }	
	}
	
	
	void drawReverse(Graphics2D g2D){
		
	}
	
	void drawShift(Graphics2D g2D){
	
		
	}

	void drawFlipOneBit(Graphics2D g2D){
	
	}

	void drawSteepestGradient(Graphics2D g2D){
		
	}


	void drawGreedyReverse(Graphics2D g2D,int startPostion,int endPostion){
		

	}


	void drawGreedyShift(Graphics2D g2D,int startPostion,int endPostion){
		
	}

	void drawGreedyFlipOneBit(Graphics2D g2D, int position){
	
	}

	void drawGreedySteepestGradient(Graphics2D g2D){
	
	}

	void reset() {
	
}


