import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.*;

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
	final int ALIASVALUE = 30;
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
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
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
			 g2D.setColor(Color.BLUE);
			 g2D.drawString("Previous Solutions", 690, 590);
			 g2D.drawLine(800, 585, 840,585);			 
		     g.setColor(new Color(214,214,214));
		     if(f.vsh.hyperHeuristic.function.getName().equals("f(x)=sinx")||f.vsh.hyperHeuristic.function.getName().equals("f(x)=sinx^2")){
		    	 GRAPHY = 200;
		    	 g2D.draw(new Line2D.Double(0+OFFSETX, 0+OFFSETY, 0+OFFSETX, GRAPHY+OFFSETY+GRAPHY));
			     g2D.draw(new Line2D.Double(0+OFFSETX, GRAPHY+OFFSETY, GRAPHX+OFFSETX,GRAPHY+OFFSETY));
		     }
			 //Draw Axis lines
		     g2D.draw(new Line2D.Double(0+OFFSETX, 0+OFFSETY, 0+OFFSETX, GRAPHY+OFFSETY));
		     g2D.draw(new Line2D.Double(0+OFFSETX, GRAPHY+OFFSETY, GRAPHX+OFFSETX,GRAPHY+OFFSETY));
			 for(int k = 0;k < GRAPHY;k+=5){
			 	g2D.drawLine(OFFSETX, OFFSETY+k, OFFSETX-5, OFFSETY+k);
			 }
		     if(f.vsh.hyperHeuristic.function.getName().equals("f(x)=x^2")){
		    	 SCALEY = (32768 * 32768) / GRAPHY; 
	             for (int x=0; x <= boundaryMaxX; x=x+10){        	 
	                 double value = f.vsh.hyperHeuristic.function.evaluateInteger(x);
					 double value2 = f.vsh.hyperHeuristic.function.evaluateInteger(x+ALIASVALUE);
	                 g2D.draw(new Line2D.Double((x/SCALEX)+OFFSETX, (GRAPHY-(value/SCALEY))+OFFSETY, ((x+ALIASVALUE)/SCALEX)+OFFSETX, (GRAPHY-(value2/SCALEY))+OFFSETY));
					 
	             }	
		     }else if(f.vsh.hyperHeuristic.function.getName().equals("f(x)=sinx")||f.vsh.hyperHeuristic.function.getName().equals("f(x)=sinx^2")){
		    	 SCALEY = 1/GRAPHY; 
	             for (int x=0; x <= boundaryMaxX; x=x+10){        	 
	                 double value = f.vsh.hyperHeuristic.function.evaluateInteger(x);
					 double value2 = f.vsh.hyperHeuristic.function.evaluateInteger(x+ALIASVALUE);
	                 g2D.draw(new Line2D.Double((x/SCALEX)+OFFSETX, (GRAPHY-(value/SCALEY))+OFFSETY, ((x+ALIASVALUE)/SCALEX)+OFFSETX, (GRAPHY-(value2/SCALEY))+OFFSETY));
	             }	
		     }else if(f.vsh.hyperHeuristic.function.getName().equals("f(x)=logx")){
		    	 SCALEY = Math.log(1+boundaryMaxX) / GRAPHY; 
	             for (int x=0; x <= boundaryMaxX; x=x+10){        	 
	                 double value = f.vsh.hyperHeuristic.function.evaluateInteger(x);
					 double value2 = f.vsh.hyperHeuristic.function.evaluateInteger(x+ALIASVALUE);
	                 g2D.draw(new Line2D.Double((x/SCALEX)+OFFSETX, (GRAPHY-(value/SCALEY))+OFFSETY, ((x+ALIASVALUE)/SCALEX)+OFFSETX, (GRAPHY-(value2/SCALEY))+OFFSETY));
	             }	
		     }
		     g.setColor(Color.BLUE);
		     for(int i=0;i<f.vsh.count;i++){
		    	 double value = f.vsh.hyperHeuristic.function.evaluate(f.vsh.history[i]);
		    	 g2D.draw(new Rectangle2D .Double((HyperHeuristic.bit2int(f.vsh.history[i])/SCALEX)+OFFSETX-2, (GRAPHY-(value/SCALEY))+OFFSETY-2, 3, 3));
		    	 g2D.draw(new Rectangle2D .Double((HyperHeuristic.bit2int(f.vsh.history[i])/SCALEX)+OFFSETX-2, (GRAPHY-(value/SCALEY))+OFFSETY-2, 2, 2));
		    	 g2D.draw(new Rectangle2D .Double((HyperHeuristic.bit2int(f.vsh.history[i])/SCALEX)+OFFSETX-2, (GRAPHY-(value/SCALEY))+OFFSETY-2, 1, 1));
		     }
		     g.setColor(Color.RED);
		     double value1 =f.vsh.hyperHeuristic.function.evaluate(f.vsh.candidateSolution);
		     g2D.draw(new Line2D.Double((HyperHeuristic.bit2int(f.vsh.candidateSolution)/SCALEX)+OFFSETX, GRAPHY+OFFSETY, (HyperHeuristic.bit2int(f.vsh.candidateSolution)/SCALEX)+OFFSETX, (GRAPHY-(value1/SCALEY))+OFFSETY));
		     g2D.draw(new Line2D.Double(OFFSETX, (GRAPHY-(value1/SCALEY))+OFFSETY, (HyperHeuristic.bit2int(f.vsh.candidateSolution)/SCALEX)+OFFSETX, (GRAPHY-(value1/SCALEY))+OFFSETY));
		     drawLowLevelHeuristicBox(g2D);
		     Font F1 = new Font ("Comic Sans MS", Font.PLAIN,16);
		     if(!f.vsh.hyperHeuristic.heuristicsSelection.getName().equals("Reinforcement Learning"))
		     g.setFont(F1);
		     g.setColor(new Color(255,0,255));
		     g2D.drawString(""+f.vsh.hyperHeuristic.heuristicsSelection.getName(),214, 100);
		     
		    
		     g.setFont(F3);
		     g2D.drawString(""+f.vsh.hyperHeuristic.acceptanceMethod.getName(),614, 90);
		     
		     g.setColor(Color.RED);
		     g.setFont(F2);
		     int temp0 = x;
		     for(int i=0;i<f.vsh.candidateSolution.length;i++){
		    	 g2D.drawString(""+f.vsh.candidateSolution[i],temp0, 54);
		    	 //System.out.print(f.vsh.candidateSolution[i]);
		    	 temp0=temp0+7;
		     }
		     //x = 47;
		     
		     if(f.vsh.hyperHeuristic.heuristicsSelection.getName().equals("Reinforcement Learning")){
		    	 g.setFont(F1);
		    	 for(int i=0;i<f.vsh.hyperHeuristic.lowLevelHeuristics.size();i++){
		    		 g2D.drawString(""+((ReinforcementLearning)f.vsh.hyperHeuristic.heuristicsSelection).keys[i],115, 220+80*i);
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
		    		 if(f.vsh.hyperHeuristic.heuristicsSelection.getName().equals("Simple Random")||f.vsh.hyperHeuristic.heuristicsSelection.getName().equals("Reinforcement Learning")){
		    		// if(f.vsh.lowLevelHeuristic.getName().)
			    		 if(f.vsh.lowLevelHeuristic.getName().equals("Reverse")){
			    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setForeground(Color.PINK);
			    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setText("Reverse");
			    			 g.setColor(Color.RED);
			    			 g2D.drawRect(lowLevelHeuristicBoxX-8, reverse_y-8, 77, 74);
			    		 }else if(f.vsh.lowLevelHeuristic.getName().equals("Inverse")){
			    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setForeground(Color.PINK);
			    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setText("Inverse");
			    			 g.setColor(Color.RED);
			    			 g2D.drawRect(lowLevelHeuristicBoxX-8, inverse_y-8, 77, 74);		    			 
			    		 }else if(f.vsh.lowLevelHeuristic.getName().equals("Shift")){
			    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setForeground(Color.PINK);
			    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setText("Shift");
			    			 g.setColor(Color.RED);
			    			 g2D.drawRect(lowLevelHeuristicBoxX-8, shift_y-8, 77, 74);		    			 
			    		 }else if(f.vsh.lowLevelHeuristic.getName().equals("Flip One Bit")){
			    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setForeground(Color.PINK);
			    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setText("Flip One Bit");
			    			 g.setColor(Color.RED);
			    			 g2D.drawRect(lowLevelHeuristicBoxX-8, flip_one_bit_y-8, 77, 74);		    			 
			    		 }else if(f.vsh.lowLevelHeuristic.getName().equals("Steepest Gradient")){
			    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setForeground(Color.PINK);
			    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setText("Steepest Gradient");
			    			 g.setColor(Color.RED);
			    			 g2D.drawRect(lowLevelHeuristicBoxX-8, steepest_gradient_y-8, 77, 74);		    			 
			    		 }
			    		 if(y2 < 265){
			    			 g.setFont(F4);
			    			 for(int i=0;i<f.vsh.candidateSolution.length;i++){
						    	 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
						    	 //System.out.print(f.vsh.candidateSolution[i]);
						    	 x2=x2+bitSpace2;
						     }
						     x2 = 185;
						     y2 ++;
			    		 }else{
			    			 g.setFont(F4);
			    			 if(f.vsh.lowLevelHeuristic.getName().equals("Inverse")){
			    				 drawInverse(g2D);
			    				 }else if(f.vsh.lowLevelHeuristic.getName().equals("Reverse")){
			    					 drawReverse(g2D);
			    				 }else if(f.vsh.lowLevelHeuristic.getName().equals("Shift")){
			    					 drawShift(g2D);
			    				 }else if(f.vsh.lowLevelHeuristic.getName().equals("Flip One Bit")){
			    					 drawFlipOneBit(g2D);
			    					 
			    				 }else if(f.vsh.lowLevelHeuristic.getName().equals("Steepest Gradient")){
			    					 drawSteepestGradient(g2D);
			    				 }
	 
			    			 }
			    			
		    		 }else if(f.vsh.hyperHeuristic.heuristicsSelection.getName().equals("Greedy Random")){
			    		 if(y2 < 265){
			    			 g.setFont(F4);
			    			 for(int i=0;i<f.vsh.candidateSolution.length;i++){
						    	 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
						    	 //System.out.print(f.vsh.candidateSolution[i]);
						    	 x2=x2+bitSpace2;
						     }
						     x2 = 185;
						     y2 ++;
			    		 }else{

			    			 if(lowLevelHeuristicsCount<f.vsh.hyperHeuristic.lowLevelHeuristics.size()){
				    			 g.setColor(Color.YELLOW);
				    			 for(int i=0;i<lowLevelHeuristicsCount;i++){
				    				 int temp =lowLevelHeuristicBoxX;
				    				 for(int j=0;j<HyperHeuristic.DIGIT_NUM;j++){
				    					 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[i][j],temp-8, 255+80*i);
				    					 temp = temp + bitSpace1;
				    				 }
				    			 }
					    		 if(f.vsh.hyperHeuristic.lowLevelHeuristics.get(lowLevelHeuristicsCount).getName().equals("Reverse")){
					    			 g.setColor(Color.RED);
					    			 g2D.drawRect(lowLevelHeuristicBoxX-8, reverse_y-8, 77, 74);
					    			 g.setFont(F4);
					    			 int startPostion = ((Reverse)f.vsh.hyperHeuristic.lowLevelHeuristics.get(lowLevelHeuristicsCount)).startPostion ;
					    			 int endPostion = ((Reverse)f.vsh.hyperHeuristic.lowLevelHeuristics.get(lowLevelHeuristicsCount)).endPostion ;
					    			 drawGreedyReverse(g2D, startPostion, endPostion);
					    			 
					    		 }else if(f.vsh.hyperHeuristic.lowLevelHeuristics.get(lowLevelHeuristicsCount).getName().equals("Inverse")){
					    			 g.setColor(Color.RED);
					    			 g2D.drawRect(lowLevelHeuristicBoxX-8, inverse_y-8, 77, 74);
					    			 g.setFont(F4);
					    			 int startPostion = ((Inverse)f.vsh.hyperHeuristic.lowLevelHeuristics.get(lowLevelHeuristicsCount)).startPostion ;
					    			 int endPostion = ((Inverse)f.vsh.hyperHeuristic.lowLevelHeuristics.get(lowLevelHeuristicsCount)).endPostion ;
					    			 drawGreedyInverse(g2D, startPostion, endPostion);
					    		 }else if(f.vsh.hyperHeuristic.lowLevelHeuristics.get(lowLevelHeuristicsCount).getName().equals("Shift")){
					    			 g.setColor(Color.RED);
					    			 g2D.drawRect(lowLevelHeuristicBoxX-8, shift_y-8, 77, 74);		
					    			 g.setFont(F4);
					    			 int startPostion = ((Shift)f.vsh.hyperHeuristic.lowLevelHeuristics.get(lowLevelHeuristicsCount)).startPostion ;
					    			 int endPostion = ((Shift)f.vsh.hyperHeuristic.lowLevelHeuristics.get(lowLevelHeuristicsCount)).endPostion ;
					    			 drawGreedyShift(g2D, startPostion, endPostion);
					    		 }else if(f.vsh.hyperHeuristic.lowLevelHeuristics.get(lowLevelHeuristicsCount).getName().equals("Flip One Bit")){
					    			 g.setColor(Color.RED);
					    			 g2D.drawRect(lowLevelHeuristicBoxX-8, flip_one_bit_y-8, 77, 74);	
					    			 g.setFont(F4);
					    			 int position = ((FlipOneBit)f.vsh.hyperHeuristic.lowLevelHeuristics.get(lowLevelHeuristicsCount)).postion ;
					    			 drawGreedyFlipOneBit(g2D, position);
					    		 }else if(f.vsh.hyperHeuristic.lowLevelHeuristics.get(lowLevelHeuristicsCount).getName().equals("Steepest Gradient")){
					    			 g.setColor(Color.RED);
					    			 g2D.drawRect(lowLevelHeuristicBoxX-8, steepest_gradient_y-8, 77, 74);
					    			 g.setFont(F4);
					    			 drawGreedySteepestGradient(g2D);
					    		 }
	
					    		 
					    		 
			    			 }else{
			    				 for(int i=0;i<f.vsh.newSolution.length;i++){
			    					 newSolutionString = newSolutionString+f.vsh.newSolution[i];
			    				 }
			    				 f.panel.m_panel.p_left.newSolutionContent.setForeground(Color.YELLOW);
			    				 f.panel.m_panel.p_left.newSolutionContent.setText(newSolutionString);
					    		 if(f.vsh.lowLevelHeuristic.getName().equals("Reverse")){
					    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setForeground(Color.PINK);
					    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setText("Reverse");
					    			 g.setColor(Color.RED);
					    			 g2D.drawRect(lowLevelHeuristicBoxX-8, reverse_y-8, 77, 74);
					    		 }else if(f.vsh.lowLevelHeuristic.getName().equals("Inverse")){
					    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setForeground(Color.PINK);
					    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setText("Inverse");
					    			 g.setColor(Color.RED);
					    			 g2D.drawRect(lowLevelHeuristicBoxX-8, inverse_y-8, 77, 74);		    			 
					    		 }else if(f.vsh.lowLevelHeuristic.getName().equals("Shift")){
					    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setForeground(Color.PINK);
					    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setText("Shift");
					    			 g.setColor(Color.RED);
					    			 g2D.drawRect(lowLevelHeuristicBoxX-8, shift_y-8, 77, 74);		    			 
					    		 }else if(f.vsh.lowLevelHeuristic.getName().equals("Flip One Bit")){
					    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setForeground(Color.PINK);
					    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setText("Flip One Bit");
					    			 g.setColor(Color.RED);
					    			 g2D.drawRect(lowLevelHeuristicBoxX-8, flip_one_bit_y-8, 77, 74);		    			 
					    		 }else if(f.vsh.lowLevelHeuristic.getName().equals("Steepest Gradient")){
					    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setForeground(Color.PINK);
					    			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setText("Steepest Gradient");
					    			 g.setColor(Color.RED);
					    			 g2D.drawRect(lowLevelHeuristicBoxX-8, steepest_gradient_y-8, 77, 74);		    			 
					    		 }
			    				if(remainTime>0){
					    			 g.setColor(Color.YELLOW);
					    			 for(int i=0;i<lowLevelHeuristicsCount;i++){
					    				 int temp =lowLevelHeuristicBoxX;
					    				 for(int j=0;j<HyperHeuristic.DIGIT_NUM;j++){
					    					 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[i][j],temp-8, 255+80*i);
					    					 temp = temp + bitSpace1;
					    				 }
					    				 if(((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).optimumSolutionIndex==i){
					    					 g2D.drawRect(lowLevelHeuristicBoxX-15, 255+80*i-12,120, 15);
					    				 }
					    			 }
					    			 remainTime--;
			    				}else{
			    					if(lowLevelHeuristicBoxX-8+x6<185){
			    						int temp =lowLevelHeuristicBoxX;
			    						g.setColor(Color.YELLOW);
			    						g.setFont(F4);
			    						for(int j=0;j<HyperHeuristic.DIGIT_NUM;j++){
					    					 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).optimumSolutionIndex][j],temp-8+x6, 255+80*((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).optimumSolutionIndex);
					    					 temp = temp + bitSpace2;
					    				 }
			    						x6++;
			    					}else{
			    						if((255+80*((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).optimumSolutionIndex-y14)>170){
			    							g.setColor(Color.YELLOW);
				    						g.setFont(F4);
			    							 for(int i=0;i<f.vsh.newSolution.length;i++){
			    						    	 g2D.drawString(""+f.vsh.newSolution[i],x2, (255+80*((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).optimumSolutionIndex-y14));
			    						    	 //System.out.print(f.vsh.candidateSolution[i]);
			    						    	 x2=x2+bitSpace2;
			    						     }
			    						     x2 = 185;
			    						     y14++;
			    						}else{
			    							drawFinalPart(g2D);
			    						}
			    					}
			    				}
			    			 }
			    		 }
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
				 for(int i=startPostion;i<f.vsh.candidateSolution.length;i++){
					 g2D.setColor(Color.YELLOW);
					 if(f.vsh.candidateSolution[i]==0)
						 g2D.drawString("1",x2+bitSpace2*startPostion, y3+80);
					 else
						 g2D.drawString("0",x2+bitSpace2*startPostion, y3+80);
					 x2 = x2 + bitSpace2;
				 }	
				 x2 = 185;
				 for(int i=0;i<=endPostion;i++){
					 g2D.setColor(Color.YELLOW);
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
			 g2D.setColor(Color.YELLOW);
			 if(y13!=inverse_y+68){
				 if(y13>inverse_y+68){
					 for(int i=0;i<f.vsh.newSolution.length;i++){
						 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y13);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185; 
					 y13--; 
				 }else {
					 for(int i=0;i<f.vsh.newSolution.length;i++){
						 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y13);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185; 
					 y13++; 
				 }
				 
				 
				 
			 }else{		 
				 if(x12>lowLevelHeuristicBoxX-20){
					 int temp = x12;
					 for(int i=0;i<f.vsh.newSolution.length;i++){
						 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],temp, y13);
						 temp=temp+bitSpace2;
					 }
					 x12--;

				 }else{
					 	lowLevelHeuristicsCount++;
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
						x12 = 185;
						y13 = 265;
				 }
			 }
			 }	
		
	}

	void drawLowLevelHeuristicBox(Graphics g){
System.out.println(f.vsh.hyperHeuristic.lowLevelHeuristics.get(0).getName());
		for(int i=0;i<f.vsh.hyperHeuristic.lowLevelHeuristics.size();i++){
			if(f.vsh.hyperHeuristic.lowLevelHeuristics.get(i).getName().equals("Reverse")){
				 g.drawImage(reverse_animation_img.getImage(),lowLevelHeuristicBoxX,lowLevelHeuristicBoxY, reverse_animation_img.getIconWidth(), reverse_animation_img.getIconHeight(), null);  
				 reverse_y = lowLevelHeuristicBoxY;
				 lowLevelHeuristicBoxY = lowLevelHeuristicBoxY + 80;
			}else if(f.vsh.hyperHeuristic.lowLevelHeuristics.get(i).getName().equals("Inverse")){
				 g.drawImage(inverse_animation_img.getImage(),lowLevelHeuristicBoxX,lowLevelHeuristicBoxY, inverse_animation_img.getIconWidth(), inverse_animation_img.getIconHeight(), null);  
				 inverse_y = lowLevelHeuristicBoxY;
				 lowLevelHeuristicBoxY = lowLevelHeuristicBoxY + 80;				
			}else if(f.vsh.hyperHeuristic.lowLevelHeuristics.get(i).getName().equals("Shift")){
				 g.drawImage(shift_animation_img.getImage(),lowLevelHeuristicBoxX,lowLevelHeuristicBoxY, shift_animation_img.getIconWidth(), shift_animation_img.getIconHeight(), null);  
				 shift_y = lowLevelHeuristicBoxY;
				 lowLevelHeuristicBoxY = lowLevelHeuristicBoxY + 80;					
			}else if(f.vsh.hyperHeuristic.lowLevelHeuristics.get(i).getName().equals("Flip One Bit")){
				 g.drawImage(flip_one_bit_img.getImage(),lowLevelHeuristicBoxX,lowLevelHeuristicBoxY, flip_one_bit_img.getIconWidth(), flip_one_bit_img.getIconHeight(), null);  
				 flip_one_bit_y = lowLevelHeuristicBoxY;
				 lowLevelHeuristicBoxY = lowLevelHeuristicBoxY + 80;			
			}else if(f.vsh.hyperHeuristic.lowLevelHeuristics.get(i).getName().equals("Steepest Gradient")){
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
			 double value2 =f.vsh.hyperHeuristic.function.evaluate(f.vsh.newSolution);
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
	     double value =f.vsh.hyperHeuristic.function.evaluate(f.vsh.newSolution);
	     g.draw(new Line2D.Double((HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX, GRAPHY+OFFSETY, (HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX, (GRAPHY-(value/SCALEY))+OFFSETY));
	     g.draw(new Line2D.Double(OFFSETX, (GRAPHY-(value/SCALEY))+OFFSETY, (HyperHeuristic.bit2int(f.vsh.newSolution)/SCALEX)+OFFSETX, (GRAPHY-(value/SCALEY))+OFFSETY));
		
		if(f.vsh.hyperHeuristic.acceptanceMethod.checkIfAcceptance(f.vsh.candidateSolution, f.vsh.newSolution)){
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
		 int startPostion = ((Reverse)f.vsh.lowLevelHeuristic).startPostion ;
		 int endPostion = ((Reverse)f.vsh.lowLevelHeuristic).endPostion ;
// System.out.println("startPostion"+((Reverse)f.vsh.lowLevelHeuristic).startPostion+"endPostion"+((Reverse)f.vsh.lowLevelHeuristic).endPostion );
		 if(startPostion < endPostion){
			 if(y3<300){
				 for(int i=0;i<f.vsh.candidateSolution.length;i++){
					 if(i>=startPostion&&i<=endPostion)
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y3);
					 else
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
			    	 //System.out.print(f.vsh.candidateSolution[i]);
			    	 x2=x2+bitSpace2;
			     }
				 x2 = 185;
				 y3++;
			 }else{
				 if(remainTime>0){
					 for(int i=0;i<f.vsh.candidateSolution.length;i++){
						 if(i>=startPostion&&i<=endPostion)
							 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y3);
						 else
							 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
				    	 //System.out.print(f.vsh.candidateSolution[i]);
				    	 x2=x2+bitSpace2;
				     }
					 x2 = 185;
					 g2D.drawLine(x2+bitSpace2*(startPostion+(endPostion-startPostion)/2), y3, x2+bitSpace2*(startPostion+(endPostion-startPostion)/2), y3+30);
					 g2D.drawLine(x2+bitSpace2*(startPostion+(endPostion-startPostion)/2)-5, y3+20, x2+bitSpace2*(startPostion+(endPostion-startPostion)/2), y3+30);
					 g2D.drawLine(x2+bitSpace2*(startPostion+(endPostion-startPostion)/2)+5, y3+20, x2+bitSpace2*(startPostion+(endPostion-startPostion)/2), y3+30);
					 g2D.drawString("Reverse",x2+bitSpace2*(startPostion+(endPostion-startPostion)/2)+20, y3+15);
					 g2D.setColor(Color.YELLOW);
					 for(int i=startPostion;i<=endPostion;i++){
						 g2D.drawString(""+f.vsh.newSolution[i],x2+bitSpace2*startPostion, y3+45);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
					 remainTime--;
				 }else{

					 if(y5>265){
    					 for(int i=0;i<f.vsh.candidateSolution.length;i++){
    						 if(i>=startPostion&&i<=endPostion);
    						 else
    							 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
					    	 //System.out.print(f.vsh.candidateSolution[i]);
					    	 x2=x2+bitSpace2;
					     }	
    					 x2 = 185;
    					 g2D.setColor(Color.YELLOW);
						 for(int i=startPostion;i<=endPostion;i++){
							 g2D.drawString(""+f.vsh.newSolution[i],x2+bitSpace2*startPostion, y5);
							 x2=x2+bitSpace2;
						 }
						 x2 = 185; 
    					 y5--;
					 }else{
						 g2D.setColor(Color.YELLOW);
						 for(int i=0;i<f.vsh.newSolution.length;i++){
							 newSolutionString = newSolutionString+f.vsh.newSolution[i];
						 }
						 f.panel.m_panel.p_left.newSolutionContent.setForeground(Color.YELLOW);
						 f.panel.m_panel.p_left.newSolutionContent.setText(newSolutionString);
						 if(y5>150){
							 for(int i=0;i<f.vsh.newSolution.length;i++){
								 g2D.drawString(""+f.vsh.newSolution[i],x2, y5);
								 x2=x2+bitSpace2;
							 }
							 x2 = 185; 
	    					 y5--; 
						 }else{
							 drawFinalPart(g2D);
						 }
					 }
				 }
			 }
		 }else{
			 if(y3<300){
				 for(int i=0;i<f.vsh.candidateSolution.length;i++){
					 if((i>=startPostion&&i<=f.vsh.candidateSolution.length-1)||(i>=0&&i<=endPostion))
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y3);
					 else
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
			    	 //System.out.print(f.vsh.candidateSolution[i]);
			    	 x2=x2+bitSpace2;
			     }
				 x2 = 185;
				 y3++;
			 }else{
				 if(remainTime>0){
					 for(int i=0;i<f.vsh.candidateSolution.length;i++){
						 if((i>=startPostion&&i<=f.vsh.candidateSolution.length-1)||(i>=0&&i<=endPostion))
							 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y3);
						 else
							 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
				    	 //System.out.print(f.vsh.candidateSolution[i]);
				    	 x2=x2+bitSpace2;
				     }
					 x2 = 185;
					 g2D.drawLine(x2+bitSpace2*(startPostion+(endPostion-startPostion)/2), y3, x2+bitSpace2*(startPostion+(endPostion-startPostion)/2), y3+30);
					 g2D.drawLine(x2+bitSpace2*(startPostion+(endPostion-startPostion)/2)-5, y3+20, x2+bitSpace2*(startPostion+(endPostion-startPostion)/2), y3+30);
					 g2D.drawLine(x2+bitSpace2*(startPostion+(endPostion-startPostion)/2)+5, y3+20, x2+bitSpace2*(startPostion+(endPostion-startPostion)/2), y3+30);
					 g2D.setColor(Color.YELLOW);
					 g2D.drawString("Reverse",x2+bitSpace2*(f.vsh.candidateSolution.length/2), y3+15);
					 for(int i=startPostion;i<f.vsh.candidateSolution.length;i++){
						 g2D.drawString(""+f.vsh.newSolution[i],x2+bitSpace2*startPostion, y3+45);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
					
					 for(int i=0;i<=endPostion;i++){
						 g2D.drawString(""+f.vsh.newSolution[i],x2, y3+45);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
					 remainTime--;
				 }else{
					 if(y5>265){
    					 for(int i=0;i<f.vsh.candidateSolution.length;i++){
    						 if((i>=startPostion&&i<=f.vsh.candidateSolution.length-1)||(i>=0&&i<=endPostion));
    						 else
    							 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
					    	 //System.out.print(f.vsh.candidateSolution[i]);
					    	 x2=x2+bitSpace2;
					     }	
    					 x2 = 185;
    					 g2D.setColor(Color.YELLOW);
						 for(int i=startPostion;i<f.vsh.candidateSolution.length;i++){
							 g2D.drawString(""+f.vsh.newSolution[i],x2+bitSpace2*startPostion, y5);
							 x2=x2+bitSpace2;
						 }
						 x2 = 185; 
						 for(int i=0;i<=endPostion;i++){
							 g2D.drawString(""+f.vsh.newSolution[i],x2, y5);
							 x2=x2+bitSpace2;
						 }
						 x2 = 185;
						 
    					 y5--;
					 }else{
						 g2D.setColor(Color.YELLOW);
						 for(int i=0;i<f.vsh.newSolution.length;i++){
							 newSolutionString = newSolutionString+f.vsh.newSolution[i];
						 }
						 f.panel.m_panel.p_left.newSolutionContent.setForeground(Color.YELLOW);
						 f.panel.m_panel.p_left.newSolutionContent.setText(newSolutionString);
						 if(y5>150){
							 for(int i=0;i<f.vsh.newSolution.length;i++){
								 g2D.drawString(""+f.vsh.newSolution[i],x2, y5);
								 x2=x2+bitSpace2;
							 }
							 x2 = 185; 
	    					 y5--; 
						 }else{
							 drawFinalPart(g2D);
						 }
					 }
				 }
			 }
		 }
	}
	
	void drawShift(Graphics2D g2D){
		 int startPostion = ((Shift)f.vsh.lowLevelHeuristic).startPostion ;
		 int endPostion = ((Shift)f.vsh.lowLevelHeuristic).endPostion ; 
		 if(startPostion < endPostion){
			 if(y3>200){
				 for(int i =0;i<f.vsh.candidateSolution.length;i++){
					 if(i==startPostion){
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y3);
					 }else{
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
					 }
					 x2=x2+bitSpace2;
				 }
				 x2 = 185;
				 y3--;
			 }else{
				 
				 if(x3<bitSpace2){
					 g2D.drawString(""+f.vsh.candidateSolution[startPostion],x2+bitSpace2*startPostion, y3);
					 for(int i=0;i<startPostion;i++){
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
					 for(int i=endPostion+1;i<f.vsh.candidateSolution.length;i++){
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2+bitSpace2*(endPostion+1), y2);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
					 for(int i=startPostion+1;i<=endPostion;i++){
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2+bitSpace2*(startPostion+1)-x3, y2);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
					 x3++;
				 }else{
					 
					 if(x2+bitSpace2*startPostion+x4<x2+bitSpace2*endPostion){

						 for(int i=0;i<endPostion;i++){
							 g2D.drawString(""+f.vsh.newSolution[i],x2, y2);
							 x2=x2+bitSpace2;
						 }
						 x2 = 185;
						 for(int i=endPostion+1;i<f.vsh.newSolution.length;i++){
							 g2D.drawString(""+f.vsh.newSolution[i],x2+bitSpace2*(endPostion+1), y2);
							 x2=x2+bitSpace2;
						 }
						 x2 = 185;
						 g2D.drawString(""+f.vsh.candidateSolution[startPostion],x2+bitSpace2*startPostion+x4, y3);
						 x4++;
	
					 }else{
						 
						 if(y6<265){
					
							 for(int i=0;i<endPostion;i++){
								 g2D.drawString(""+f.vsh.newSolution[i],x2, y2);
								 x2=x2+bitSpace2;
							 }
							 x2 = 185;
							 for(int i=endPostion+1;i<f.vsh.newSolution.length;i++){
								 g2D.drawString(""+f.vsh.newSolution[i],x2+bitSpace2*(endPostion+1), y2);
								 x2=x2+bitSpace2;
							 }
							 x2 = 185;
							 g2D.drawString(""+f.vsh.candidateSolution[startPostion],x2+bitSpace2*endPostion, y6);
							 y6++;
							 
						 }else{
							 for(int i=0;i<f.vsh.newSolution.length;i++){
								 newSolutionString = newSolutionString+f.vsh.newSolution[i];
							 }
							 f.panel.m_panel.p_left.newSolutionContent.setForeground(Color.YELLOW);
							 f.panel.m_panel.p_left.newSolutionContent.setText(newSolutionString);
							 if(y4>150){
								 g2D.setColor(Color.YELLOW);
								 for(int i=0;i<f.vsh.newSolution.length;i++){
    								 g2D.drawString(""+f.vsh.newSolution[i],x2, y4);
    								 x2=x2+bitSpace2;
    							 }
    							 x2 = 185; 
		    					 y4--; 
							 }else{
								 drawFinalPart(g2D);
							 }
						 }
						 
					 }
				 }			 
			 }
		 }else{
			 if(y3>200){
				 for(int i =0;i<f.vsh.candidateSolution.length;i++){
					 if(i==startPostion){
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y3);
					 }else{
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
					 }
					 x2=x2+bitSpace2;
				 }
				 x2 = 185;
				 y3--;
			 }else{
				 if(x3<bitSpace2){
					 g2D.drawString(""+f.vsh.candidateSolution[startPostion],x2+bitSpace2*startPostion, y3);
					 for(int i=endPostion+1;i<startPostion;i++){
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2+bitSpace2*(endPostion+1), y2);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
		
					 for(int i=0;i<=endPostion;i++){
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2-x3, y2);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
					 for(int i=startPostion;i<=endPostion;i++){
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2+bitSpace2*startPostion-x3, y2);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
					 x3++;
				 }else{
					 if(x2+bitSpace2*startPostion-x4>x2+bitSpace2*endPostion){
						    
						 for(int i=endPostion+1;i<startPostion;i++){
							 g2D.drawString(""+f.vsh.candidateSolution[i],x2+bitSpace2*(endPostion+1), y2);
							 x2=x2+bitSpace2;
						 }
						 x2 = 185;
						 for(int i=0;i<=endPostion;i++){
							 g2D.drawString(""+f.vsh.candidateSolution[i],x2-x3, y2);
							 x2=x2+bitSpace2;
						 }
						 x2 = 185;
						 for(int i=startPostion;i<=endPostion;i++){
							 g2D.drawString(""+f.vsh.candidateSolution[i],x2+bitSpace2*startPostion-x3, y2);
							 x2=x2+bitSpace2;
						 }
						 x2 = 185;
						 g2D.drawString(""+f.vsh.candidateSolution[startPostion],x2+bitSpace2*startPostion-x4, y3);
						 x4++;
	
					 }else{ 
						 if(y6<265){
					
							 for(int i=endPostion+1;i<startPostion;i++){
								 g2D.drawString(""+f.vsh.candidateSolution[i],x2+bitSpace2*(endPostion+1), y2);
								 x2=x2+bitSpace2;
							 }
							 x2 = 185;
							 for(int i=0;i<=endPostion;i++){
								 g2D.drawString(""+f.vsh.candidateSolution[i],x2-x3, y2);
								 x2=x2+bitSpace2;
							 }
							 x2 = 185;
							 for(int i=startPostion;i<=endPostion;i++){
								 g2D.drawString(""+f.vsh.candidateSolution[i],x2+bitSpace2*startPostion-x3, y2);
								 x2=x2+bitSpace2;
							 }
							 x2 = 185;
							 g2D.drawString(""+f.vsh.candidateSolution[startPostion],x2+bitSpace2*endPostion, y6);
							 y6++;
							 
						 }else{
							 if(y6<345){
								 for(int i=0;i<f.vsh.newSolution.length-1;i++){
    								 g2D.drawString(""+f.vsh.newSolution[i],x2, y2);
    								 x2=x2+bitSpace2;
    							 }
    							 x2 = 185; 
    							 g2D.drawString(""+f.vsh.candidateSolution[0],x2-bitSpace2, y6);
    							 y6++;
    							 
							 }else{
								if(x2-bitSpace2+x6<x2+bitSpace2*(f.vsh.newSolution.length-1)) {
									 for(int i=0;i<f.vsh.newSolution.length-1;i++){
	    								 g2D.drawString(""+f.vsh.newSolution[i],x2, y2);
	    								 x2=x2+bitSpace2;
	    							 }
	    							 x2 = 185; 
	    							 g2D.drawString(""+f.vsh.candidateSolution[0],x2-bitSpace2+x6, y6);
	    							 x6++;
								}else{
									if(y5>265){
										 for(int i=0;i<f.vsh.newSolution.length-1;i++){
		    								 g2D.drawString(""+f.vsh.newSolution[i],x2, y2);
		    								 x2=x2+bitSpace2;
		    							 }
		    							 x2 = 185; 
		    							 g2D.drawString(""+f.vsh.candidateSolution[0],x2-bitSpace2+x6, y5);
		    							 y5--;
									}else{
										 for(int i=0;i<f.vsh.newSolution.length;i++){
											 newSolutionString = newSolutionString+f.vsh.newSolution[i];
										 }
										 f.panel.m_panel.p_left.newSolutionContent.setForeground(Color.YELLOW);
										 f.panel.m_panel.p_left.newSolutionContent.setText(newSolutionString);
										 if(y4>150){
											 g2D.setColor(Color.YELLOW);
	    									 for(int i=0;i<f.vsh.newSolution.length;i++){
			    								 g2D.drawString(""+f.vsh.newSolution[i],x2, y4);
			    								 x2=x2+bitSpace2;
			    							 }
			    							 x2 = 185; 
					    					 y4--; 
	    								 }else{
	    									 drawFinalPart(g2D);
	    								 }
									}
									 
								}
								
							 }
							 
						 }
					 }
				 }
			 }
		 }
	}

	void drawFlipOneBit(Graphics2D g2D){
		 int postion = ((FlipOneBit)f.vsh.lowLevelHeuristic).postion ;
		 if(y3>185){
			 for(int i=0;i<f.vsh.candidateSolution.length;i++){
				 if(i==postion)
					 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y3);
				 else
					 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
		    	 //System.out.print(f.vsh.candidateSolution[i]);
		    	 x2=x2+bitSpace2;
		     }
			 x2 = 185;
			 if(f.vsh.candidateSolution[postion]==0)
				g2D.drawString("1",x2+bitSpace2*postion, y3+80);
			 else
				g2D.drawString("0",x2+bitSpace2*postion, y3+80);

			 y3--;
		 }else{
			 for(int i=0;i<f.vsh.newSolution.length;i++){
				 newSolutionString = newSolutionString+f.vsh.newSolution[i];
			 }
			 f.panel.m_panel.p_left.newSolutionContent.setForeground(Color.YELLOW);
			 f.panel.m_panel.p_left.newSolutionContent.setText(newSolutionString);
			 if(y4>170){
				 g2D.setColor(Color.YELLOW);
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

	void drawSteepestGradient(Graphics2D g2D){
		 if(count<f.vsh.candidateSolution.length){
		    	//System.out.println("count"+count);
			    					 if(y8<(y7+25*(count))){
			   //System.out.println("y3"+y3+"aa"+(y2+30*(count+1))+(y3<(y2+30*(count+1))));
			    						 for(int i=0;i<f.vsh.candidateSolution.length;i++ ){
			    							 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y7-25); 
			    							 x2=x2+bitSpace2;
			    						 }
			    						 x2 = 185;
			    						 for(int i=0;i<count;i++){
			    							 for(int j=0;j<f.vsh.candidateSolution.length;j++ ){
			    								 if(j==i){
			    									 g2D.setColor(Color.YELLOW);
			    									 if(f.vsh.candidateSolution[j]==0){
			    										 g2D.drawString("1",x2, y7+25*i); 
			    									 }else{
			    										 g2D.drawString("0",x2, y7+25*i); 
			    									 }
			    									 g2D.setColor(Color.RED);
			    								 }else{
			    									 g2D.drawString(""+f.vsh.candidateSolution[j],x2, y7+25*i);
			    								 }
				    							 x2=x2+bitSpace2;
				    						 }
			    							 x2 = 185;
			    						 }
			    						 for(int i=0;i<f.vsh.candidateSolution.length;i++ ){
			    							 if(i==count){
		    									 g2D.setColor(Color.YELLOW);
		    									 if(f.vsh.candidateSolution[i]==0){
		    										 g2D.drawString("1",x2, y8); 
		    									 }else{
		    										 g2D.drawString("0",x2,y8); 
		    									 }
		    									 g2D.setColor(Color.RED);
		    								 }else{
		    									 g2D.drawString(""+f.vsh.candidateSolution[i],x2,y8); 
		    								 }
			    							 x2=x2+bitSpace2;
			    						 }
			    						 x2 = 185;
			    						 y8++;
			    					 }else{
			    						 count++;
			    					 }
		    					 }else{
		    						 if(remainTime>0){
			    						 for(int i=0;i<f.vsh.candidateSolution.length;i++ ){
			    							 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y7-25); 
			    							 x2=x2+bitSpace2;
			    						 }
			    						 x2 = 185;
			    						 for(int i=0;i<count;i++){
			    							 for(int j=0;j<f.vsh.candidateSolution.length;j++ ){
			    								 if(j==i){
			    									 g2D.setColor(Color.YELLOW);
			    									 if(f.vsh.candidateSolution[j]==0){
			    										 g2D.drawString("1",x2, y7+25*i); 
			    									 }else{
			    										 g2D.drawString("0",x2, y7+25*i); 
			    									 }
			    									 g2D.setColor(Color.RED);
			    								 }else{
			    									 g2D.drawString(""+f.vsh.candidateSolution[j],x2, y7+25*i);
			    								 }
				    							 x2=x2+bitSpace2;
				    						 }
			    							 x2 = 185;
			    	//System.out.println(((SteepestGradient)f.vsh.lowLevelHeuristic).optimumSolutionIndex+"SSS");
			    							 if(((SteepestGradient)f.vsh.lowLevelHeuristic).optimumSolutionIndex==i){
			    								 g2D.setColor(Color.YELLOW);
			    								 g2D.drawRect(x2-5, y7+25*i-20, bitSpace2*f.vsh.candidateSolution.length+10, 20);
			    							 }
			    						 }
			    						 remainTime--;
		    						 }else{
		    							 for(int i=0;i<f.vsh.newSolution.length;i++){
		    								 newSolutionString = newSolutionString+f.vsh.newSolution[i];
		    							 }
		    							 f.panel.m_panel.p_left.newSolutionContent.setForeground(Color.YELLOW);
		    							 f.panel.m_panel.p_left.newSolutionContent.setText(newSolutionString);
		    							 g2D.setColor(Color.YELLOW);
		    							 if(((int) (y7+25*((SteepestGradient)f.vsh.lowLevelHeuristic).optimumSolutionIndex)-x6)>165){
		    								 for(int i=0;i<f.vsh.newSolution.length;i++ ){
				    							 g2D.drawString(""+f.vsh.newSolution[i],x2, y7+25*((SteepestGradient)f.vsh.lowLevelHeuristic).optimumSolutionIndex-x6); 
				    							 x2=x2+bitSpace2;
				    						 }
				    						 x2 = 185;
				    						 x6++;
		    							 }else{
		    								 drawFinalPart(g2D);
		    							 }
		    						 }
		    					 }	
	}


	void drawGreedyReverse(Graphics2D g2D,int startPostion,int endPostion){
		
//System.out.println("startPostion"+((Reverse)f.vsh.lowLevelHeuristic).startPostion+"endPostion"+((Reverse)f.vsh.lowLevelHeuristic).endPostion );
		 if(startPostion < endPostion){
			 if(y3<300){
				 for(int i=0;i<f.vsh.candidateSolution.length;i++){
					 if(i>=startPostion&&i<=endPostion)
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y3);
					 else
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
			    	 //System.out.print(f.vsh.candidateSolution[i]);
			    	 x2=x2+bitSpace2;
			     }
				 x2 = 185;
				 y3++;
			 }else{
				 if(remainTime>0){
					 for(int i=0;i<f.vsh.candidateSolution.length;i++){
						 if(i>=startPostion&&i<=endPostion)
							 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y3);
						 else
							 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
				    	 //System.out.print(f.vsh.candidateSolution[i]);
				    	 x2=x2+bitSpace2;
				     }
					 x2 = 185;
					 g2D.drawLine(x2+bitSpace2*(startPostion+(endPostion-startPostion)/2), y3, x2+bitSpace2*(startPostion+(endPostion-startPostion)/2), y3+30);
					 g2D.drawLine(x2+bitSpace2*(startPostion+(endPostion-startPostion)/2)-5, y3+20, x2+bitSpace2*(startPostion+(endPostion-startPostion)/2), y3+30);
					 g2D.drawLine(x2+bitSpace2*(startPostion+(endPostion-startPostion)/2)+5, y3+20, x2+bitSpace2*(startPostion+(endPostion-startPostion)/2), y3+30);
					 g2D.drawString("Reverse",x2+bitSpace2*(startPostion+(endPostion-startPostion)/2)+20, y3+15);
					 g2D.setColor(Color.YELLOW);
					 for(int i=startPostion;i<=endPostion;i++){
						 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2+bitSpace2*startPostion, y3+45);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
					 remainTime--;
				 }else{
					
					 if(y5>265){
   					 for(int i=0;i<f.vsh.candidateSolution.length;i++){
   						 if(i>=startPostion&&i<=endPostion);
   						 else
   							 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
					    	 //System.out.print(f.vsh.candidateSolution[i]);
					    	 x2=x2+bitSpace2;
					     }	
   					 x2 = 185;
   					 g2D.setColor(Color.YELLOW);
						 for(int i=startPostion;i<=endPostion;i++){
							 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2+bitSpace2*startPostion, y5);
							 x2=x2+bitSpace2;
						 }
						 x2 = 185; 
   					 y5--;
					 }else{
						 g2D.setColor(Color.YELLOW);
						 if(y13!=reverse_y+68){
							 if(y13>reverse_y+68){
								 for(int i=0;i<f.vsh.newSolution.length;i++){
									 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y13);
									 x2=x2+bitSpace2;
								 }
								 x2 = 185; 
		    					 y13--; 
							 }else {
								 for(int i=0;i<f.vsh.newSolution.length;i++){
									 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y13);
									 x2=x2+bitSpace2;
								 }
								 x2 = 185; 
		    					 y13++; 
							 }
	    					 
	    					 
	    					 
						 }else{
							 if(x12>lowLevelHeuristicBoxX-20){
								 int temp = x12;
								 for(int i=0;i<f.vsh.newSolution.length;i++){
									 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],temp, y13);
									 temp=temp+bitSpace2;
								 }
								 x12--;
							 }else{
							 	lowLevelHeuristicsCount++;
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
								x12 = 185;
								y13 = 265;
							 }
						 }
						
							
						
					 }
				 }
			 }
		 }else{
			 if(y3<300){
				 for(int i=0;i<f.vsh.candidateSolution.length;i++){
					 if((i>=startPostion&&i<=f.vsh.candidateSolution.length-1)||(i>=0&&i<=endPostion))
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y3);
					 else
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
			    	 //System.out.print(f.vsh.candidateSolution[i]);
			    	 x2=x2+bitSpace2;
			     }
				 x2 = 185;
				 y3++;
			 }else{
				 if(remainTime>0){
					 for(int i=0;i<f.vsh.candidateSolution.length;i++){
						 if((i>=startPostion&&i<=f.vsh.candidateSolution.length-1)||(i>=0&&i<=endPostion))
							 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y3);
						 else
							 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
				    	 //System.out.print(f.vsh.candidateSolution[i]);
				    	 x2=x2+bitSpace2;
				     }
					 x2 = 185;
					 g2D.drawLine(x2+bitSpace2*(startPostion+(endPostion-startPostion)/2), y3, x2+bitSpace2*(startPostion+(endPostion-startPostion)/2), y3+30);
					 g2D.drawLine(x2+bitSpace2*(startPostion+(endPostion-startPostion)/2)-5, y3+20, x2+bitSpace2*(startPostion+(endPostion-startPostion)/2), y3+30);
					 g2D.drawLine(x2+bitSpace2*(startPostion+(endPostion-startPostion)/2)+5, y3+20, x2+bitSpace2*(startPostion+(endPostion-startPostion)/2), y3+30);
					 g2D.drawString("Reverse",x2+bitSpace2*(f.vsh.candidateSolution.length/2), y3+15);
					 g2D.setColor(Color.YELLOW);
					 for(int i=startPostion;i<f.vsh.candidateSolution.length;i++){
						 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2+bitSpace2*startPostion, y3+45);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
					 
					 for(int i=0;i<=endPostion;i++){
						 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y3+45);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
					 remainTime--;
				 }else{
					 if(y5>265){
   					 for(int i=0;i<f.vsh.candidateSolution.length;i++){
   						 if((i>=startPostion&&i<=f.vsh.candidateSolution.length-1)||(i>=0&&i<=endPostion));
   						 else
   							 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
					    	 //System.out.print(f.vsh.candidateSolution[i]);
					    	 x2=x2+bitSpace2;
					     }	
   					 x2 = 185;
   					 g2D.setColor(Color.YELLOW);
						 for(int i=startPostion;i<f.vsh.candidateSolution.length;i++){
							 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2+bitSpace2*startPostion, y5);
							 x2=x2+bitSpace2;
						 }
						 x2 = 185; 
						 for(int i=0;i<=endPostion;i++){
							 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y5);
							 x2=x2+bitSpace2;
						 }
						 x2 = 185;
						 
   					 y5--;
					 }else{
						 g2D.setColor(Color.YELLOW);
						 if(y13!=reverse_y+68){
							 if(y13>reverse_y+68){
								 for(int i=0;i<f.vsh.newSolution.length;i++){
									 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y13);
									 x2=x2+bitSpace2;
								 }
								 x2 = 185; 
		    					 y13--; 
							 }else {
								 for(int i=0;i<f.vsh.newSolution.length;i++){
									 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y13);
									 x2=x2+bitSpace2;
								 }
								 x2 = 185; 
		    					 y13++; 
							 }
	    					 
	    					 
	    					 
						 }else{
							 if(x12>lowLevelHeuristicBoxX-20){
								 int temp = x12;
								 for(int i=0;i<f.vsh.newSolution.length;i++){
									 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],temp, y13);
									 temp=temp+bitSpace2;
								 }
								 x12--;
								 System.out.println(y5+"v"+(reverse_y+26));
							 }else{
							 	lowLevelHeuristicsCount++;
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
								x12 = 185;
								y13 = 265;
							 	
							 }
						 }
					 }
				 }
			 }
		 }
	}


	void drawGreedyShift(Graphics2D g2D,int startPostion,int endPostion){
		 if(startPostion < endPostion){
			 if(y3>200){
				 for(int i =0;i<f.vsh.candidateSolution.length;i++){
					 if(i==startPostion){
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y3);
					 }else{
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
					 }
					 x2=x2+bitSpace2;
				 }
				 x2 = 185;
				 y3--;
			 }else{
				 
				 if(x3<bitSpace2){
					 g2D.drawString(""+f.vsh.candidateSolution[startPostion],x2+bitSpace2*startPostion, y3);
					 for(int i=0;i<startPostion;i++){
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
					 for(int i=endPostion+1;i<f.vsh.candidateSolution.length;i++){
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2+bitSpace2*(endPostion+1), y2);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
					 for(int i=startPostion+1;i<=endPostion;i++){
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2+bitSpace2*(startPostion+1)-x3, y2);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
					 x3++;
				 }else{
					 
					 if(x2+bitSpace2*startPostion+x4<x2+bitSpace2*endPostion){

						 for(int i=0;i<endPostion;i++){
							 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y2);
							 x2=x2+bitSpace2;
						 }
						 x2 = 185;
						 for(int i=endPostion+1;i<f.vsh.newSolution.length;i++){
							 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2+bitSpace2*(endPostion+1), y2);
							 x2=x2+bitSpace2;
						 }
						 x2 = 185;
						 g2D.drawString(""+f.vsh.candidateSolution[startPostion],x2+bitSpace2*startPostion+x4, y3);
						 x4++;
	
					 }else{
						 
						 if(y6<265){
					
							 for(int i=0;i<endPostion;i++){
								 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y2);
								 x2=x2+bitSpace2;
							 }
							 x2 = 185;
							 for(int i=endPostion+1;i<f.vsh.newSolution.length;i++){
								 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2+bitSpace2*(endPostion+1), y2);
								 x2=x2+bitSpace2;
							 }
							 x2 = 185;
							 g2D.drawString(""+f.vsh.candidateSolution[startPostion],x2+bitSpace2*endPostion, y6);
							 y6++;
							 
						 }else{
							 g2D.setColor(Color.YELLOW);
							 if(y13!=shift_y+68){
								 if(y13>shift_y+68){
									 for(int i=0;i<f.vsh.newSolution.length;i++){
										 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y13);
										 x2=x2+bitSpace2;
									 }
									 x2 = 185; 
			    					 y13--; 
								 }else {
									 for(int i=0;i<f.vsh.newSolution.length;i++){
										 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y13);
										 x2=x2+bitSpace2;
									 }
									 x2 = 185; 
			    					 y13++; 
								 }
		    					 
		    					 
		    					 
							 }else{
								 if(x12>lowLevelHeuristicBoxX-20){
									 int temp = x12;
									 for(int i=0;i<f.vsh.newSolution.length;i++){
										 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],temp, y13);
										 temp=temp+bitSpace2;
									 }
									 x12--;
									 System.out.println(y5+"v"+(reverse_y+26));
								 }else{
								 	lowLevelHeuristicsCount++;
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
									x12 = 185;
									y13 = 265;
								 	
								 }
							 }
						 }
						 
					 }
				 }			 
			 }
		 }else{
			 if(y3>200){
				 for(int i =0;i<f.vsh.candidateSolution.length;i++){
					 if(i==startPostion){
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y3);
					 }else{
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
					 }
					 x2=x2+bitSpace2;
				 }
				 x2 = 185;
				 y3--;
			 }else{
				 if(x3<bitSpace2){
					 g2D.drawString(""+f.vsh.candidateSolution[startPostion],x2+bitSpace2*startPostion, y3);
					 for(int i=endPostion+1;i<startPostion;i++){
						 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2+bitSpace2*(endPostion+1), y2);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
		
					 for(int i=0;i<=endPostion;i++){
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2-x3, y2);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
					 for(int i=startPostion;i<=endPostion;i++){
						 g2D.drawString(""+f.vsh.candidateSolution[i],x2+bitSpace2*startPostion-x3, y2);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185;
					 x3++;
				 }else{
					 if(x2+bitSpace2*startPostion-x4>x2+bitSpace2*endPostion){
						    
						 for(int i=endPostion+1;i<startPostion;i++){
							 g2D.drawString(""+f.vsh.candidateSolution[i],x2+bitSpace2*(endPostion+1), y2);
							 x2=x2+bitSpace2;
						 }
						 x2 = 185;
						 for(int i=0;i<=endPostion;i++){
							 g2D.drawString(""+f.vsh.candidateSolution[i],x2-x3, y2);
							 x2=x2+bitSpace2;
						 }
						 x2 = 185;
						 for(int i=startPostion;i<=endPostion;i++){
							 g2D.drawString(""+f.vsh.candidateSolution[i],x2+bitSpace2*startPostion-x3, y2);
							 x2=x2+bitSpace2;
						 }
						 x2 = 185;
						 g2D.drawString(""+f.vsh.candidateSolution[startPostion],x2+bitSpace2*startPostion-x4, y3);
						 x4++;
	
					 }else{ 
						 if(y6<265){
					
							 for(int i=endPostion+1;i<startPostion;i++){
								 g2D.drawString(""+f.vsh.candidateSolution[i],x2+bitSpace2*(endPostion+1), y2);
								 x2=x2+bitSpace2;
							 }
							 x2 = 185;
							 for(int i=0;i<=endPostion;i++){
								 g2D.drawString(""+f.vsh.candidateSolution[i],x2-x3, y2);
								 x2=x2+bitSpace2;
							 }
							 x2 = 185;
							 for(int i=startPostion;i<=endPostion;i++){
								 g2D.drawString(""+f.vsh.candidateSolution[i],x2+bitSpace2*startPostion-x3, y2);
								 x2=x2+bitSpace2;
							 }
							 x2 = 185;
							 g2D.drawString(""+f.vsh.candidateSolution[startPostion],x2+bitSpace2*endPostion, y6);
							 y6++;
							 
						 }else{
							 if(y6<345){
								 for(int i=0;i<f.vsh.newSolution.length-1;i++){
    								 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y2);
    								 x2=x2+bitSpace2;
    							 }
    							 x2 = 185; 
    							 g2D.drawString(""+f.vsh.candidateSolution[0],x2-bitSpace2, y6);
    							 y6++;
    							 
							 }else{
								if(x2-bitSpace2+x6<x2+bitSpace2*(f.vsh.newSolution.length-1)) {
									 for(int i=0;i<f.vsh.newSolution.length-1;i++){
	    								 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y2);
	    								 x2=x2+bitSpace2;
	    							 }
	    							 x2 = 185; 
	    							 g2D.drawString(""+f.vsh.candidateSolution[0],x2-bitSpace2+x6, y6);
	    							 x6++;
								}else{
									if(y5>265){
										 for(int i=0;i<f.vsh.newSolution.length-1;i++){
		    								 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y2);
		    								 x2=x2+bitSpace2;
		    							 }
		    							 x2 = 185; 
		    							 g2D.drawString(""+f.vsh.candidateSolution[0],x2-bitSpace2+x6, y5);
		    							 y5--;
									}else{
										 g2D.setColor(Color.YELLOW);
										 if(y13!=shift_y+68){
											 if(y13>shift_y+68){
												 for(int i=0;i<f.vsh.newSolution.length;i++){
													 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y13);
													 x2=x2+bitSpace2;
												 }
												 x2 = 185; 
						    					 y13--; 
											 }else {
												 for(int i=0;i<f.vsh.newSolution.length;i++){
													 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y13);
													 x2=x2+bitSpace2;
												 }
												 x2 = 185; 
						    					 y13++; 
											 }
					    					 
					    					 
					    					 
										 }else{
											 if(x12>lowLevelHeuristicBoxX-20){
												 int temp = x12;
												 for(int i=0;i<f.vsh.newSolution.length;i++){
													 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],temp, y13);
													 temp=temp+bitSpace2;
												 }
												 x12--;
												 System.out.println(y5+"v"+(reverse_y+26));
											 }else{
											 	lowLevelHeuristicsCount++;
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
												x12 = 185;
												y13 = 265;
											 	
											 }
										 }
									}
									 
								}
								
							 }
							 
						 }
					 }
				 }
			 }
		 }		
	}

	void drawGreedyFlipOneBit(Graphics2D g2D, int position){
		 if(y3>185){
			 for(int i=0;i<f.vsh.candidateSolution.length;i++){
				 if(i==position)
					 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y3);
				 else
					 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y2);
		    	 //System.out.print(f.vsh.candidateSolution[i]);
		    	 x2=x2+bitSpace2;
		     }
			 x2 = 185;
			 if(f.vsh.candidateSolution[position]==0)
				g2D.drawString("1",x2+bitSpace2*position, y3+80);
			 else
				g2D.drawString("0",x2+bitSpace2*position, y3+80);

			 y3--;
		 }else{
			 g2D.setColor(Color.YELLOW);
			 if(y13!=flip_one_bit_y+68){
				 if(y13>flip_one_bit_y+68){
					 for(int i=0;i<f.vsh.newSolution.length;i++){
						 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y13);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185; 
					 y13--; 
				 }else {
					 for(int i=0;i<f.vsh.newSolution.length;i++){
						 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, y13);
						 x2=x2+bitSpace2;
					 }
					 x2 = 185; 
					 y13++; 
				 }
				 
				 
				 
			 }else{
				 if(x12>lowLevelHeuristicBoxX-20){
					 int temp = x12;
					 for(int i=0;i<f.vsh.newSolution.length;i++){
						 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],temp, y13);
						 temp=temp+bitSpace2;
					 }
					 x12--;
				 }else{
				 	lowLevelHeuristicsCount++;
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
					x12 = 185;
					y13 = 265;
				 }
			 }
		 }	
	}

	void drawGreedySteepestGradient(Graphics2D g2D){
		 if(count<f.vsh.candidateSolution.length){
		    	//System.out.println("count"+count);
			    					 if(y8<(y7+25*(count))){
			   //System.out.println("y3"+y3+"aa"+(y2+30*(count+1))+(y3<(y2+30*(count+1))));
			    						 for(int i=0;i<f.vsh.candidateSolution.length;i++ ){
			    							 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y7-25); 
			    							 x2=x2+bitSpace2;
			    						 }
			    						 x2 = 185;
			    						 for(int i=0;i<count;i++){
			    							 for(int j=0;j<f.vsh.candidateSolution.length;j++ ){
			    								 if(j==i){
			    									 g2D.setColor(Color.YELLOW);
			    									 if(f.vsh.candidateSolution[j]==0){
			    										 g2D.drawString("1",x2, y7+25*i); 
			    									 }else{
			    										 g2D.drawString("0",x2, y7+25*i); 
			    									 }
			    									 g2D.setColor(Color.RED);
			    								 }else{
			    									 g2D.drawString(""+f.vsh.candidateSolution[j],x2, y7+25*i);
			    								 }
				    							 x2=x2+bitSpace2;
				    						 }
			    							 x2 = 185;
			    						 }
			    						 for(int i=0;i<f.vsh.candidateSolution.length;i++ ){
			    							 if(i==count){
		    									 g2D.setColor(Color.YELLOW);
		    									 if(f.vsh.candidateSolution[i]==0){
		    										 g2D.drawString("1",x2, y8); 
		    									 }else{
		    										 g2D.drawString("0",x2,y8); 
		    									 }
		    									 g2D.setColor(Color.RED);
		    								 }else{
		    									 g2D.drawString(""+f.vsh.candidateSolution[i],x2,y8); 
		    								 }
			    							 x2=x2+bitSpace2;
			    						 }
			    						 x2 = 185;
			    						 y8++;
			    					 }else{
			    						 count++;
			    					 }
		    					 }else{
		    						 if(remainTime>0){
			    						 for(int i=0;i<f.vsh.candidateSolution.length;i++ ){
			    							 g2D.drawString(""+f.vsh.candidateSolution[i],x2, y7-25); 
			    							 x2=x2+bitSpace2;
			    						 }
			    						 x2 = 185;
			    						 for(int i=0;i<count;i++){
			    							 for(int j=0;j<f.vsh.candidateSolution.length;j++ ){
			    								 if(j==i){
			    									 g2D.setColor(Color.YELLOW);
			    									 if(f.vsh.candidateSolution[j]==0){
			    										 g2D.drawString("1",x2, y7+25*i); 
			    									 }else{
			    										 g2D.drawString("0",x2, y7+25*i); 
			    									 }
			    									 g2D.setColor(Color.RED);
			    								 }else{
			    									 g2D.drawString(""+f.vsh.candidateSolution[j],x2, y7+25*i);
			    								 }
				    							 x2=x2+bitSpace2;
				    						 }
			    							 x2 = 185;
			    	//System.out.println(((SteepestGradient)f.vsh.lowLevelHeuristic).optimumSolutionIndex+"SSS");
			    							 if(((SteepestGradient)f.vsh.lowLevelHeuristic).optimumSolutionIndex==i){
			    								 g2D.setColor(Color.YELLOW);
			    								 g2D.drawRect(x2-5, y7+25*i-20, bitSpace2*f.vsh.candidateSolution.length+10, 20);
			    							 }
			    						 }
			    						 remainTime--;
		    						 }else{
		    					
		    								 g2D.setColor(Color.YELLOW);
		    								 if(((int) (y7+25*((SteepestGradient)f.vsh.lowLevelHeuristic).optimumSolutionIndex)-x6)!=steepest_gradient_y+68){
		    									 if(((int) (y7+25*((SteepestGradient)f.vsh.lowLevelHeuristic).optimumSolutionIndex)-x6)>steepest_gradient_y+68){
		    										 for(int i=0;i<f.vsh.newSolution.length;i++){
		    											 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, ((int) (y7+25*((SteepestGradient)f.vsh.lowLevelHeuristic).optimumSolutionIndex)-x6));
		    											 x2=x2+bitSpace2;
		    										 }
		    										 x2 = 185; 
		    										 x6++; 
		    									 }else {
		    										 for(int i=0;i<f.vsh.newSolution.length;i++){
		    											 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],x2, ((int) (y7+25*((SteepestGradient)f.vsh.lowLevelHeuristic).optimumSolutionIndex)-x6));
		    											 x2=x2+bitSpace2;
		    										 }
		    										 x2 = 185; 
		    										x6--; 
		    									 }
		    									 
		    									 
		    									 
		    								 }else{
		    									 if(x12>lowLevelHeuristicBoxX-20){
		    										 int temp = x12;
		    										 for(int i=0;i<f.vsh.newSolution.length;i++){
		    											 g2D.drawString(""+((GreedyRandom)f.vsh.hyperHeuristic.heuristicsSelection).histories[lowLevelHeuristicsCount][i],temp, ((int) (y7+25*((SteepestGradient)f.vsh.lowLevelHeuristic).optimumSolutionIndex)-x6));
		    											 temp=temp+bitSpace2;
		    										 }
		    										 x12--;
		    									 }else{
		    									 	lowLevelHeuristicsCount++;
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
		    										y10 =0;
		    										remainTime=100;
		    										y11 = 80;
		    										x10 = 593;
		    										x11 = 610;
		    										x12 = 185;
		    										y13 = 265;
		    									 }
		    								 }
		    							
		    						 }
		    					 }		
	}

	void reset() {
		// TODO Auto-generated method stub
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
		 drawBackgroundPic = false;
		 f.vsh.history = new int[1000][15];
		 f.vsh.count = 0;
			lowLevelHeuristicsCount = 0;
			f.panel.m_panel.flowchart.setBounds(new Rectangle((f.panel.m_panel.animationPanel.getWidth()-f.panel.m_panel.flowchart.label_img.getIconWidth())/2,(f.panel.m_panel.animationPanel.getHeight()-f.panel.m_panel.flowchart.label_img.getIconHeight())/2,f.panel.m_panel.flowchart.label_img.getIconWidth(),f.panel.m_panel.flowchart.label_img.getIconHeight()));
			this.add(f.panel.m_panel.flowchart);
			 f.panel.m_panel.p_left.newSolutionContent.setText("");
			 f.panel.m_panel.p_left.lowLevelHeuristicContent.setText("");
			 f.panel.m_panel.p_left.acceptedContent.setText("");
			 f.panel.m_panel.p_left.countContent.setText("");
			 f.panel.m_panel.p_left.candidateSolutionContent.setText("");
			f.validate();
			f.repaint();
	
	}
}


