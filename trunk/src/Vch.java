///////////////////////////////////////////////////////////
/////
/////  FullName:		Ben, Chao, Tom, Alexander, Lao
/////  CreationDate:	2009-10-28
/////  Module:			G52GRP, University of Nottingham
/////
///////////////////////////////////////////////////////////



// IMPORT Java Libraries
import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;



import java.util.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;



public class Vch extends JFrame implements Runnable,ActionListener {
	/**
	 * the size of the picture
	 */
	public  static final int WIDTH=1280, HEIGHT=780;
	/**
	 *  the original number
	 */
	public int [] bit = new int[10];
	/**
	 *  the new number produced by method1
	 */
	public int[] bit1 = new int[10];
	/**
	 *  the new number produced by method2
	 */
	public int[] bit2 = new int[10];
	/**
	 *  the new number produced by method3
	 */	
	public int[] bit3 = new int[10];
	/**
	 *  the instance of  the class random for producing two random position and choosing a lower-level heuristic
	 */
	Random rd = new Random();
	/**  
	 * the two random positions for three heuristics
	 */
	public int postion1, postion2;
	/**
	 * the coordinate for drawing the binary number
	 */
	public int y1=400,y2=400,y3=400, x1,x2,x3,x4,y11=160,y12=400,x11=250,x12;
	/**
	 *  the instance of a JPanel for drawing
	 */
	MyPanel process;
	/**
	 *  to judge if the animation finishes and the calculation can start
	 */
	public static boolean continueRun;
	/**
	 * to decide to show which heuristic
	 */
	public boolean method1,method2,method3;
	static Vch hh;
	static boolean run,endButton,init;
	/**
	 * to judge if the draw finishes
	 */
	public boolean finishDrawMethod1,finishDrawMethod2,finishDrawMethod3,allfinishedMethod2,finishMethod2Part2,finishMethod3Part2,allfinishedMethod3;
	JPanel problemDomainArea, state,jp; 
	JComboBox problemDomain;
	JButton start, end;
	JLabel currentResult, times, currentMethod;
	int count,methodNum;
	double current;
	double original;
    public int OFFSETX = 450;
    public int OFFSETY = 200;
    public int GRAPHX = 512;
    public int GRAPHY = 512;
    public int SCALEY = (1024 * 1024) / GRAPHY;
    public int SCALEX = 1024 / GRAPHX;
	public int xLine=GRAPHY+OFFSETY+20;
	public double yLine ;
	public ArrayList<Statistics> statistics = new ArrayList<Statistics>();
	 Statistics sta;
	public static void main (String[] args){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//Nimbus风格，新出来的外观，jdk6 update10版本以后的才会出现
		} catch (Exception e) {
			e.printStackTrace();
		}
		hh = new Vch();

			new Thread(hh).start();	

			while(true){
				if(run){
					if(continueRun){
						hh.randomChooseHeuristic();
					}
				}else if(endButton){
					hh.reset();
					
				}
			}

	}
	
	/**
	 * 
	 * @see java.lang.Runnable#run()
	 * the thread for drawing
	 */
	private void reset(){
		for(int i=0;i<10;i++){
			bit[i] =0;
		}
		postion1 = 0;
		postion2 = 0;
		y1=400;
		y2=400;
		y3=400;
		x1=0;
		x2=0;
		x3=0;
		x4=0;
		y11=160;
		y12=400;
		x11=250;
		x12=0;
		count=0;
		methodNum=0;
		continueRun = false;
		method1=false;
		method2=false;
		method3=false;
		finishDrawMethod1=false;
		finishDrawMethod2=false;
		finishDrawMethod3=false;
		allfinishedMethod2=false;	
		finishMethod2Part2=false;
		finishMethod3Part2=false;
		allfinishedMethod3=false;
		init=false;
		xLine=GRAPHY+OFFSETY+20;
		
	}
	public void run(){
		while(true){
		if(finishDrawMethod1||finishDrawMethod2||finishDrawMethod3){
			try {
				Thread.sleep(0);				
				method1 = false;
				method2 = false;
				method3 = false;
				continueRun = true;
				finishDrawMethod1 = false;
				finishDrawMethod2 = false;
				finishDrawMethod3 = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		process.repaint();
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	
	
	public Vch(){
		super("HyperHeuristic");
		this.setBounds(400, 400, WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		problemDomainArea = new JPanel();
		state = new JPanel();
		problemDomainArea = new JPanel();
		jp = new JPanel();
		jp.setPreferredSize (new Dimension(256,768));
		problemDomainArea = new JPanel();
		problemDomainArea.setPreferredSize (new Dimension(256,256));
		problemDomainArea.setBorder(new BevelBorder(BevelBorder.RAISED));	
		problemDomain = new JComboBox(new String[]{"f(x)=x^2"});
		problemDomain.setBounds(30,150,150,30);
		problemDomainArea.setLayout(null);
		JLabel jfp = new JLabel("Problem Domain");
		jfp.setBounds(40,100,100,30);
		end = new JButton("end");
		end.setBounds(110,30,80,39);
		end.addActionListener(this);
		start = new JButton("start");
		start.setBounds(20,30,80,39);
		start.addActionListener(this);
		problemDomainArea.add(jfp);
		problemDomainArea.add(problemDomain);
		problemDomainArea.add(start);
		problemDomainArea.add(end);
		state = new JPanel();
		state.setLayout(null);
		state.setPreferredSize (new Dimension(256,512));
		currentResult = new JLabel("currentResult is : "+bit[0]+bit[1]+bit[2]+bit[3]+bit[4]+bit[5]+bit[6]+bit[7]+bit[8]+bit[9]);
		currentResult.setBounds(20,20,200,100);
		times = new JLabel("count  : "+ count);
		times.setBounds(20,140,200,100);
		currentMethod = new JLabel("currentMethod is : "+ methodNum);
		currentMethod.setBounds(20,260,200,100);
		state.add(currentResult);
		state.add(times);
		state.add(currentMethod);
		jp.add(problemDomainArea,BorderLayout.NORTH);
		jp.add(state,BorderLayout.CENTER);
		jp.setBorder(new BevelBorder(BevelBorder.RAISED));
		process = new MyPanel();
		process.setBorder(new BevelBorder(BevelBorder.LOWERED));
		process.setPreferredSize (new Dimension(1024,768));
		this.add(jp,BorderLayout.WEST);
		this.add(process,BorderLayout.CENTER);		
		this.setResizable(false);		
		this.setVisible(true);
		process.requestFocus();
	}
	
	/**
	 *  producing the first random number
	 */
	private void initialization(){
		System.out.println("the first random number is");
		for(int i=0; i<10; i++){
			bit[i] = rd.nextInt(2);
			System.out.print(bit[i]);
		}
		System.out.println("");
	}
	
	/**
	 * the HyperHeuristic choose a lower level heuristic randomly
	 */
	private void randomChooseHeuristic(){
		 count++;
		 double randomNumber = rd.nextDouble();;
		 if (randomNumber >= 0 && randomNumber< 1.0/3)  
		 {
			 method1();
			 sta = new Statistics();
			 sta.count = count;
			 for(int i=0;i<10;i++){
				 sta.preValue[i] = bit[i];
				 sta.curValue[i] = bit1[i];

				 
			 }
			 sta.method = 1;
			 if(function(decode(bit))<=function(decode(bit1))){
				 for(int i=0;i<10;i++){
					 sta.curResult[i] =  bit[i];
				 }
				sta.state ="rejection";
			 }else{
				 for(int i=0;i<10;i++){
					 sta.curResult[i] =  bit1[i];
				 }
				sta.state ="acceptance";
			 }
			 statistics.add(sta);
			 method1 =true;
			 continueRun = false;		 
		 }
		 else if (randomNumber >= (1.0/3) && randomNumber < (1.0-1.0/3))
		 {
			 method2();
			 sta = new Statistics();
			 sta.count = count;
			 for(int i=0;i<10;i++){
				 sta.preValue[i] = bit[i];
				 sta.curValue[i] = bit2[i];
				
			 }

			 sta.method = 2;
			 if(function(decode(bit))<=function(decode(bit2))){
				 for(int i=0;i<10;i++){
					 sta.curResult[i] =  bit[i];
				 }
				sta.state ="rejection";
			 }else{
				 for(int i=0;i<10;i++){
					 sta.curResult[i] =  bit2[i];
				 }
				sta.state ="acceptance";
			 }
			 statistics.add(sta);
			 method2 =true;
			 continueRun = false;		 
		 }
		 else  
		 {
			 method3();
			 sta = new Statistics();
			 sta.count = count;
			 for(int i=0;i<10;i++){
				 sta.preValue[i] = bit[i];
				 sta.curValue[i] = bit3[i];
			 }

			 sta.method = 3;
			 if(function(decode(bit))<=function(decode(bit3))){
				 for(int i=0;i<10;i++){
					 sta.curResult =  bit;
				 }
				sta.state ="rejection";
			 }else{
				 for(int i=0;i<10;i++){
					 sta.curResult =  bit3;
				 }
				sta.state ="acceptance";
			 }
			 statistics.add(sta);
			 method3 =true;
			 continueRun = false;		 
			 
		 }

	}
	
	/**
	 * the first heuristic: choose two positions randomly, then change their values.
	 */
	private void method1(){
		for(int i=0; i<10; i++){
			bit1[i] = bit[i];
		}
		get2Postions();
		if(postion1!=postion2){
			if(bit1[postion1]==0){
		
				bit1[postion1]=1;
	
			}else{
				bit1[postion1]=0;
			}
			if(bit1[postion2]==0){
				bit1[postion2]=1;
			}else{
				bit1[postion2]=0;
			}
		}else{
			if(bit1[postion1]==0){
				
				bit1[postion1]=1;
	
			}else{
				bit1[postion1]=0;
			}
		}
		System.out.println("invoke method1");		
		current = decode(bit1);
		original = decode(bit);
		yLine = (current/SCALEX)+OFFSETX;
	}
	
	
	/**
	 * the second heuristic: choose two positions randomly, then swap their values.
	 */
	private void method2(){
		int temp1,temp2;
		for(int i=0; i<10; i++){
			bit2[i] = bit[i];
		}
		get2Postions();
		temp1 = bit[postion1];
		temp2 = bit[postion2];
		bit2[postion1] = temp2;
		bit2[postion2] = temp1;
		System.out.println("invoke method2");	
		current = decode(bit2);
		original = decode(bit);
		yLine = (current/SCALEX)+OFFSETX;
	}
	
	/**
	 * the third heuristic: choose two positions randomly,then  swap their values and change the values of number between them.
	 */
	private void method3(){
		int temp1,temp2;
		get2Postions();
		for(int i=0; i<10; i++){
			bit3[i] = bit[i];
		}
		temp1 = bit[postion1];
		temp2 = bit[postion2];
		bit3[postion1] = temp2;
		bit3[postion2] = temp1; 
		if(postion1<=postion2){
			for(int i=(postion1+1); i<postion2;i++){
				if(bit3[i]==0){
					bit3[i]=1;
				}else{
					bit3[i]=0;
				}
			}
		}else{
			for(int i=(postion2+1); i<postion1;i++){
				if(bit3[i]==0){
					bit3[i]=1;
				}else{
					bit3[i]=0;
				}
			}
		}
		System.out.println("invoke method3");	
		current = decode(bit3);
		original = decode(bit);
		yLine = (current/SCALEX)+OFFSETX;
	}
	
	
	/**
	 * 
	 * @param bit4  the new binary number 
	 * compare the original number and the new number by comparing the values f(x) and f(x') and get smaller one
	 */
	private void compare(int[] bit4){	
		
		double oldNum, newNum;
		oldNum = decode(bit);
		newNum = decode(bit4);
		if(function(oldNum)>function(newNum)){
			for(int i=0; i<10; i++){
				bit[i]=bit4[i];
			}
			System.out.println("Because function(old number)="+"function("+oldNum+")="+function(oldNum)+">function(new number)"+"=function("+newNum+")="+function(newNum));	
			System.out.println("the number becomes");
		}else{
			System.out.println("Because function(old number)="+"function("+oldNum+")="+function(oldNum)+"<=function(new number)"+"=function("+newNum+")="+function(newNum));
			System.out.println("there is no need to change, the number is still");
		}
		
		for(int j=0; j<10; j++){
			System.out.print(bit[j]);				
		}
		System.out.println("");
	}
	
	/**
	 *  get the two positions for lower level heuristic
	 */
	private void get2Postions(){
		postion1 = rd.nextInt(10);
		postion2 = rd.nextInt(10);
	}
	
	/**
	 * 
	 * @param bit5 the binary represent number
	 * @return the corresponding  integer 
	 */
	private double decode(int[] bit5){
		double intNum = 0;
		for(int i=0; i<10; i++){
			intNum = intNum + bit5[i] * Math.pow(2, (9-i));
		}
		return intNum;
	}
	/**
	 * 
	 * @param x
	 * @return the value f(x)
	 *  the domain of question
	 */
	private double function(double x){
		return Math.pow(x, 2);
	}
	
	
	private class MyPanel extends JPanel{
		int x = 40,x_=0,x0=0;
		 MyPanel (){
				this.setSize(WIDTH, HEIGHT);
				this.setBackground(Color.GRAY);
				this.setDoubleBuffered(true);
			 }
		 public void paintComponent(Graphics g){
			 if(run){
				if(!init){
					hh.initialization();
					init = true;
				}
			 	x_=0;
			 	Font F1=new Font("Font1",Font.BOLD+Font.ITALIC,36);
			 	super.paintComponent(g);
				Font F = g.getFont();
				g.setFont(F1);
				Color c = g.getColor();				
				g.setColor(Color.RED);
				g.drawString("HyperHeuristic", 300, 30);
				g.setColor(c);
				g.setFont(F);
				if(!method1&&!method2&&!method3){
					Font F2=new Font("Font2",Font.BOLD+Font.ITALIC,20);
					g.setFont(F2);
					g.setColor(Color.BLACK);
					g.drawString("The first random number", 0, 70);
					for(int i=0;i<10;i++){
						g.drawString(""+bit[i],x_,125);
						x_=x_+10;	
					}
					g.setColor(Color.green);
					g.drawRect(250, 100, 150, 60);
					g.setColor(Color.BLACK);
					g.drawString("Heuristic Selection:", 250, 70);
					g.drawString("simple random",250,90);
					g.drawString("Acceptance Method:", 650, 55);
					g.drawString("Improving & Equal", 650,70);
					g.setColor(Color.yellow);
					g.drawOval(650,100,150,60);
					continueRun = true;
				}
				
				
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.black);							
                g2.draw(new Line2D.Double(0+OFFSETX, 0+OFFSETY, 0+OFFSETX, GRAPHY+OFFSETY));
                g2.draw(new Line2D.Double(0+OFFSETX, GRAPHY+OFFSETY, GRAPHX+OFFSETX,GRAPHY+OFFSETY));
                
                int gridxoffset = 0;
                int gridyoffset = 0;
                while (gridxoffset <= GRAPHX){
                        g2.draw(new Line2D.Double(gridxoffset+OFFSETX, GRAPHY+OFFSETY, gridxoffset+OFFSETX, GRAPHY+10+OFFSETY));
                        gridxoffset = gridxoffset + 8;
                }
                while (gridyoffset <= GRAPHY){
                        g2.draw(new Line2D.Double(OFFSETX-10, gridyoffset+OFFSETY, OFFSETX, gridyoffset+OFFSETY));
                        gridyoffset = gridyoffset + 8;
                }
                for (int t=0; t <= 1024; t++ ){
                    g2.draw(new Line2D.Double((t/SCALEX)+OFFSETX, (GRAPHY-(t * t/SCALEY))+OFFSETY, (t/SCALEX)+OFFSETX, (GRAPHY-(t * t/SCALEY))+OFFSETY));
                }	
                g2.draw(new Line2D.Double((original/SCALEX)+OFFSETX, GRAPHY+OFFSETY+20, (original/SCALEX)+OFFSETX, GRAPHY-((original*original)/SCALEY)+OFFSETY)); //x
                g2.draw(new Line2D.Double(OFFSETX-20, GRAPHY-((original*original)/SCALEY)+OFFSETY, (original/SCALEX)+OFFSETX, GRAPHY-((original*original)/SCALEY)+OFFSETY)); //y
                
                for(int i=0,k=(int)(original/SCALEX)+OFFSETX;i<10;i++,k=k+10){
                	g2.drawString(""+bit[i],k,GRAPHY+OFFSETY+30);
                }
                g2.drawString(""+original*original,OFFSETX, (int) (GRAPHY-((original*original)/SCALEY)+OFFSETY));
                for(int i=0;i<(statistics.size()-1);i++){
               System.out.println(statistics.size());
                	g.setColor(Color.RED);
                	int x_dot = 0;
                	if(function(decode(statistics.get(i).curValue))>function(decode(statistics.get(i).preValue))){
                		x_dot =(int) decode(statistics.get(i).curValue);
                	}else{
                		x_dot =(int) decode(statistics.get(i).preValue);
                	}
                	g.fillRect((x_dot/SCALEX)+OFFSETX, (GRAPHY-(x_dot*x_dot/SCALEY)+OFFSETY)-5, 6,6);
                }
                g.setColor(Color.yellow);
                g.drawLine(140, 600, 250,600);
                g.drawString("Current Value",50, 605);
                g.setColor(Color.black);
                g.drawLine(140, 625, 250,625);
                g.drawString("Original Value",50, 630);
                g.setColor(Color.red);
                g.fillRect(155, 645, 8, 8);
                g.drawString("Previous Results",50, 655);
                
				if(method1){
					drawMethod1(g);
				}else if (method2){
					drawMethod2(g);
				}else if(method3){
					drawMethod3(g);
				}
				
			}
		 }
	}
	
	/**
	 * 
	 * @param g the instance of Graphics 
	 *  the method of drawing the first heuristic
	 */
	private void drawMethod1(Graphics g){	
		methodNum = 1;
		currentResult.setText("currentResult is : "+bit[0]+bit[1]+bit[2]+bit[3]+bit[4]+bit[5]+bit[6]+bit[7]+bit[8]+bit[9]);
		times.setText("count  : "+ count);
		currentMethod.setText("currentMethod is   : "+ methodNum);
		int x=250,x111;
		g.setColor(Color.green);
		g.drawRect(250, 100, 150, 60);
		g.setColor(Color.BLACK);
		g.setColor(Color.yellow);
		g.drawLine(650, 130, 725, 80);
		g.drawLine(800, 130, 725, 80);
		g.drawLine(650, 130, 725, 180);
		g.drawLine(800, 130, 725, 180);
		g.setColor(Color.DARK_GRAY);
		g.fill3DRect(0, 180, 1024, 20, true);
		g.draw3DRect(30, 240, 90, 90, true);
		g.draw3DRect(30, 350, 90, 90, true);
		g.draw3DRect(30, 460, 90, 90, true);
		Font F2=new Font("Font2",Font.BOLD+Font.ITALIC,20);
		Font F = g.getFont();
		g.setFont(F2);
		Color c = g.getColor();		
		g.setColor(Color.BLACK);

		g.drawString("Heuristic Selection:", 250, 70);
		g.drawString("simple random",250,90);
		g.drawString("Acceptance Method:", 650, 55);
		g.drawString("Improving & Equal", 650,70);
		g.drawString("Low Level Heuristic", 30, 220);
		g.drawString("Method1", 35, 280);
		g.drawString("Method2", 35, 390);
		g.drawString("Method3", 35, 500);
		if(x12<255){
			x111 = x12;
			for(int i=0;i<10;i++){
				g.drawString(""+bit[i],x12,125);
				x12=x12+10;	
			}
			x12=x111+1;
		}else{
			if(y11<400){
				g.drawString("Method1", 260, 130);
				g.setColor(Color.red);
				g.drawLine(125, 285,175,285);
				g.drawLine(125, 285, 135, 300);
				g.drawLine(125, 285, 135, 270);
				g.draw3DRect(25, 235, 100, 100, true);
				g.setColor(Color.black);
				for(int i=0;i<10;i++){
					g.drawString(""+bit[i],x,y11);
					x=x+10;
				}
				y11=y11+1;
			}else{
				if(y1<=500){
					if(y1<500){
					g.drawString("Method1", 260, 130);
					g.setColor(Color.red);
					g.drawLine(125, 285,175,285);
					g.drawLine(125, 285, 135, 300);
					g.drawLine(125, 285, 135, 270);
					g.draw3DRect(25, 235, 100, 100, true);
					g.setColor(Color.black);
					for(int i=0;i<10;i++){
						
						if(i==postion1||i==postion2){
							g.drawString(""+bit[i],x,y1);
							if(bit[i]==0){
								g.drawString("1",x, y1-100);
							}else{
								g.drawString("0",x, y1-100);
							}
						}else{
							g.drawString(""+bit[i],x,400);
						}
						x=x+10;	
					}
					y1=y1+1;
					}else{
						y1=y1+1;
					}
				}
				if(y1==501){
					g.setColor(Color.red);
					g.drawLine(125, 285,175,285);
					g.drawLine(125, 285, 135, 300);
					g.drawLine(125, 285, 135, 270);
					g.draw3DRect(25, 235, 100, 100, true);
					g.setColor(Color.black);
					g.setColor(Color.yellow);
					x=250;
					if(y12>125){
						for(int i=0;i<10;i++){
							g.drawString(""+bit1[i],x,y12);
							x=x+10;
						}
						y12=y12-1;
					}else{
						if(x11<655){
							x111=x11;
							for(int i=0;i<10;i++){
								g.drawString(""+bit1[i],x11,125);
								x11=x11+10;
							}
							x11=x111+1;
						}else{
							//int[] tempBit = bit;

							
			                Graphics2D g2 = (Graphics2D) g;
	                        g2.setColor(Color.yellow);
	                        for(int i=0,k=(int)(current/SCALEX)+OFFSETX;i<10;i++,k=k+10){
	                        	g2.drawString(""+bit1[i],k, GRAPHY+OFFSETY-10);
	                        }
	                        if(xLine>GRAPHY-((current*current)/SCALEY)+OFFSETY){
	                        	for(int i= GRAPHY+OFFSETY+20;i>xLine;i=i-20){
	                        		g2.draw(new Line2D.Double((current/SCALEX)+OFFSETX, i, (current/SCALEX)+OFFSETX, i-20)); //x
	                        	}
	                        	xLine=xLine-2;
	                        }else{
	                        	g2.draw(new Line2D.Double((current/SCALEX)+OFFSETX, GRAPHY+OFFSETY+20, (current/SCALEX)+OFFSETX, GRAPHY-((current*current)/SCALEY)+OFFSETY)); //x
		                        
	                        	if(yLine>OFFSETX-20){
	                        		for(double i= (current/SCALEX)+OFFSETX;i>yLine;i=i-20){
	                        			g2.draw(new Line2D.Double(yLine, GRAPHY-((current*current)/SCALEY)+OFFSETY, (current/SCALEX)+OFFSETX, GRAPHY-((current*current)/SCALEY)+OFFSETY));
	                        			//g2.draw(new Line2D.Double(OFFSETX-20, GRAPHY-((current*current)/SCALEY)+OFFSETY, (current/SCALEX)+OFFSETX, GRAPHY-((current*current)/SCALEY)+OFFSETY)); //y
	                        		}
	                        		yLine=yLine-2;	
	                        	}else{
	                        		g2.drawString(""+current*current,OFFSETX-120, (int) (GRAPHY-((current*current)/SCALEY)+OFFSETY));
	                        		g2.draw(new Line2D.Double((current/SCALEX)+OFFSETX, GRAPHY+OFFSETY+20, (current/SCALEX)+OFFSETX, GRAPHY-((current*current)/SCALEY)+OFFSETY)); //x
	                        		g2.draw(new Line2D.Double(OFFSETX-20, GRAPHY-((current*current)/SCALEY)+OFFSETY, (current/SCALEX)+OFFSETX, GRAPHY-((current*current)/SCALEY)+OFFSETY)); //y
	                        		if(function(decode(bit))<=function(decode(bit1))){
	                        			g.setColor(Color.RED);
	                        			g.drawString("unaccepted", 670, 130);
	                        			g.setColor(Color.yellow);
	                        			if(y12>=-60){
	                        				x111=x11;
	                						for(int i=0;i<10;i++){
	                							g.drawString(""+bit1[i],x111,y12);
	                							x111=x111+10;
	                						}
	                						y12=y12-1;
	                						
	                        			}else{
	    	    							compare(bit1);							
	    	    							y1=400;
	    	    							y11=160;
	    	    							y12=400;
	    	    							x11=250;
	    	    							x12=0;
	    	    							xLine=GRAPHY+OFFSETY+20;
	    	    							finishDrawMethod1 = true;
	    	    							g.setColor(c);
	    	    							g.setFont(F);
	                        			}
	                        		}else{
	                        			g.setColor(Color.RED);
	                        			g.drawString("accepted", 670, 130);
	                        			g.setColor(Color.yellow);
	            						if(x11<1100){
	            							x111=x11;
	            							for(int i=0;i<10;i++){
	            								g.drawString(""+bit1[i],x11,125);
	            								x11=x11+10;
	            							}
	            							x11=x111+1;
	            						}else{
	    	    							compare(bit1);							
	    	    							y1=400;
	    	    							y11=160;
	    	    							y12=400;
	    	    							x11=250;
	    	    							x12=0;
	    	    							xLine=GRAPHY+OFFSETY+20;
	    	    							finishDrawMethod1 = true;
	    	    							g.setColor(c);
	    	    							g.setFont(F);	            							
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

	
		
	
	/**
	 * 
	 * @param g the instance of Graphics 
	 *  the method of drawing the second heuristic
	 */
	private void drawMethod2(Graphics g){	
		methodNum = 2;
		currentResult.setText("currentResult is : "+bit[0]+bit[1]+bit[2]+bit[3]+bit[4]+bit[5]+bit[6]+bit[7]+bit[8]+bit[9]);
		times.setText("count  : "+ count);
		currentMethod.setText("currentMethod is   : "+ methodNum);
		int x=250,x222;
		g.setColor(Color.green);
		g.drawRect(250, 100, 150, 60);
		g.setColor(Color.BLACK);
		g.setColor(Color.yellow);
		g.drawLine(650, 130, 725, 80);
		g.drawLine(800, 130, 725, 80);
		g.drawLine(650, 130, 725, 180);
		g.drawLine(800, 130, 725, 180);
		g.setColor(Color.DARK_GRAY);
		g.fill3DRect(0, 180, 1024, 20, true);
		g.draw3DRect(30, 240, 90, 90, true);
		g.draw3DRect(30, 350, 90, 90, true);
		g.draw3DRect(30, 460, 90, 90, true);
		Font F2=new Font("Font2",Font.BOLD+Font.ITALIC,20);
		Font F = g.getFont();
		g.setFont(F2);
		Color c = g.getColor();		
		g.setColor(Color.BLACK);
		g.drawString("Heuristic Selection:", 250, 70);
		g.drawString("simple random",250,90);
		g.drawString("Acceptance Method:", 650, 55);
		g.drawString("Improving & Equal", 650,70);
		g.drawString("Low Level Heuristic", 30, 220);
		g.drawString("Method1", 35, 280);
		g.drawString("Method2", 35, 390);
		g.drawString("Method3", 35, 500);
		if(x12<255){
			x222 = x12;
			for(int i=0;i<10;i++){
				g.drawString(""+bit[i],x12,125);
				x12=x12+10;	
			}
			x12=x222+1;
		}else{
			if(y11<400){
				g.drawString("Method2", 260, 130);
				g.setColor(Color.red);
				g.drawLine(125, 395,175,395);
				g.drawLine(125, 395, 135, 410);
				g.drawLine(125, 395, 135, 380);
				g.draw3DRect(25, 345, 100, 100, true);
				g.setColor(Color.black);
				for(int i=0;i<10;i++){
					g.drawString(""+bit[i],x,y11);
					x=x+10;
				}
				y11=y11+1;
			}else{
				if(postion1!=postion2){
					if(y1<451&&!finishMethod2Part2){
						g.setColor(Color.red);
						g.drawLine(125, 395,175,395);
						g.drawLine(125, 395, 135, 410);
						g.drawLine(125, 395, 135, 380);
						g.draw3DRect(25, 345, 100, 100, true);
						g.setColor(Color.black);
						for(int i=0;i<10;i++){					
							if(i==postion1){
								g.drawString(""+bit[i],x,y1);
								x1=x;
								x3=x;
							}
							else if(i==postion2){
								g.drawString(""+bit[i],x,y1);
								x2=x;
								x4=x;
							}else{
								g.drawString(""+bit[i],x,400);
							}
							x=x+10;	
						}
						y1=y1+1;
					}else{
						if(x1!=x4&&x2!=x3){
							g.setColor(Color.red);
							g.drawLine(125, 395,175,395);
							g.drawLine(125, 395, 135, 410);
							g.drawLine(125, 395, 135, 380);
							g.draw3DRect(25, 345, 100, 100, true);
							g.setColor(Color.black);
							for(int i=0;i<10;i++){
								
								if(i==postion1){
									g.drawString(""+bit[i],x1,y1);
								}
								else if(i==postion2){
									g.drawString(""+bit[i],x2,y1);
								}else{
									g.drawString(""+bit[i],x,400);
								}
								x=x+10;	
							}
							if(x3<x4){
								x1=x1+1;
								x2=x2-1;
							}else{
								x1=x1-1;
								x2=x2+1;
							}
							if(x1==x4&&x2==x3){
								finishMethod2Part2 =true;
							}
						}else{
							if(y1>400){
								g.setColor(Color.red);
								g.drawLine(125, 395,175,395);
								g.drawLine(125, 395, 135, 410);
								g.drawLine(125, 395, 135, 380);
								g.draw3DRect(25, 345, 100, 100, true);
								g.setColor(Color.black);
								for(int i=0;i<10;i++){
									
									if(i==postion1){
										g.drawString(""+bit[i],x1,y1);
									}
									else if(i==postion2){
										g.drawString(""+bit[i],x2,y1);
									}else{
										g.drawString(""+bit[i],x,400);
									}
									x=x+10;	
								}
								y1=y1-1;	
								
							}else if(y1==400){
								y1=y1-1;
							}
						}
			
					}
					
				}
				if((y1<400&&finishMethod2Part2)||postion1==postion2){
					g.setColor(Color.red);
					g.drawLine(125, 395,175,395);
					g.drawLine(125, 395, 135, 410);
					g.drawLine(125, 395, 135, 380);
					g.draw3DRect(25, 345, 100, 100, true);
					g.setColor(Color.black);
					g.setColor(Color.yellow);
					x=250;
					if(y12>125){
						for(int i=0;i<10;i++){
							g.drawString(""+bit2[i],x,y12);
							x=x+10;
						}
						y12=y12-1;
					}else{
						if(x11<655){
							x222=x11;
							for(int i=0;i<10;i++){
								g.drawString(""+bit2[i],x11,125);
								x11=x11+10;
							}
							x11=x222+1;
						}else{					
			                Graphics2D g2 = (Graphics2D) g;
	                        g2.setColor(Color.yellow);
	                        for(int i=0,k=(int)(current/SCALEX)+OFFSETX;i<10;i++,k=k+10){
	                        	g2.drawString(""+bit2[i],k, GRAPHY+OFFSETY-10);
	                        }
	                        if(xLine>GRAPHY-((current*current)/SCALEY)+OFFSETY){
	                        	for(int i= GRAPHY+OFFSETY+20;i>xLine;i=i-20){
	                        		g2.draw(new Line2D.Double((current/SCALEX)+OFFSETX, i, (current/SCALEX)+OFFSETX, i-20)); //x
	                        	}
	                        	xLine=xLine-2;
	                        }else{
	                        	g2.draw(new Line2D.Double((current/SCALEX)+OFFSETX, GRAPHY+OFFSETY+20, (current/SCALEX)+OFFSETX, GRAPHY-((current*current)/SCALEY)+OFFSETY)); //x
		                        
	                        	if(yLine>OFFSETX-20){
	                        		for(double i= (current/SCALEX)+OFFSETX;i>yLine;i=i-20){
	                        			g2.draw(new Line2D.Double(yLine, GRAPHY-((current*current)/SCALEY)+OFFSETY, (current/SCALEX)+OFFSETX, GRAPHY-((current*current)/SCALEY)+OFFSETY));
	                        			//g2.draw(new Line2D.Double(OFFSETX-20, GRAPHY-((current*current)/SCALEY)+OFFSETY, (current/SCALEX)+OFFSETX, GRAPHY-((current*current)/SCALEY)+OFFSETY)); //y
	                        		}
	                        		yLine=yLine-2;	
	                        	}else{
	                        		g2.drawString(""+current*current,OFFSETX-120, (int) (GRAPHY-((current*current)/SCALEY)+OFFSETY));
	                        		g2.draw(new Line2D.Double((current/SCALEX)+OFFSETX, GRAPHY+OFFSETY+20, (current/SCALEX)+OFFSETX, GRAPHY-((current*current)/SCALEY)+OFFSETY)); //x
	                        		g2.draw(new Line2D.Double(OFFSETX-20, GRAPHY-((current*current)/SCALEY)+OFFSETY, (current/SCALEX)+OFFSETX, GRAPHY-((current*current)/SCALEY)+OFFSETY)); //y
	                        		if(function(decode(bit))<=function(decode(bit2))){
	                        			g.setColor(Color.RED);
	                        			g.drawString("unaccepted", 670, 130);
	                        			g.setColor(Color.yellow);
	                        			if(y12>=-60){
	                        				x222=x11;
	                						for(int i=0;i<10;i++){
	                							g.drawString(""+bit2[i],x222,y12);
	                							x222=x222+10;
	                						}
	                						y12=y12-1;
	                						
	                        			}else{
	            							compare(bit2);
	            							finishDrawMethod2 = true;
	            							y1=400;
	            							//count2=0;
	            							x1=0;
	            							x2=0;
	            							x3=0;
	            							x4=0;
	            							finishMethod2Part2=false;
	            							allfinishedMethod2=false;
	            							y1=400;
	            							y11=160;
	            							y12=400;
	            							x11=250;
	            							x12=0;
	            							g.setColor(c);
	            							g.setFont(F);
	    	    							xLine=GRAPHY+OFFSETY+20;

	                        			}
	                        		}else{
	                        			g.setColor(Color.RED);
	                        			g.drawString("accepted", 670, 130);
	                        			g.setColor(Color.yellow);
	            						if(x11<1100){
	            							x222=x11;
	            							for(int i=0;i<10;i++){
	            								g.drawString(""+bit2[i],x11,125);
	            								x11=x11+10;
	            							}
	            							x11=x222+1;
	            						}else{
	            							compare(bit2);
	            							finishDrawMethod2 = true;
	            							y1=400;
	            							//count2=0;
	            							x1=0;
	            							x2=0;
	            							x3=0;
	            							x4=0;
	            							finishMethod2Part2=false;
	            							allfinishedMethod2=false;
	            							y1=400;
	            							y11=160;
	            							y12=400;
	            							x11=250;
	            							x12=0;
	            							g.setColor(c);
	            							g.setFont(F);
	    	    							xLine=GRAPHY+OFFSETY+20;
	            							
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
	
	
	
	/**
	 * 
	 * @param g the instance of Graphics 
	 *  the method of drawing the third heuristic
	 */
	private void drawMethod3(Graphics g){	
		methodNum=3;
		currentResult.setText("currentResult is"+bit[0]+bit[1]+bit[2]+bit[3]+bit[4]+bit[5]+bit[6]+bit[7]+bit[8]+bit[9]);
		times.setText("count  "+ count);
		currentMethod.setText("currentMethod is   "+ methodNum);
		int x=250,x5=250,x333;
		g.setColor(Color.green);
		g.drawRect(250, 100, 150, 60);
		g.setColor(Color.BLACK);
		g.setColor(Color.yellow);
		g.drawLine(650, 130, 725, 80);
		g.drawLine(800, 130, 725, 80);
		g.drawLine(650, 130, 725, 180);
		g.drawLine(800, 130, 725, 180);
		g.setColor(Color.DARK_GRAY);		
		g.fill3DRect(0, 180, 1024, 20, true);
		g.draw3DRect(30, 240, 90, 90, true);
		g.draw3DRect(30, 350, 90, 90, true);
		g.draw3DRect(30, 460, 90, 90, true);
		Font F2=new Font("Font2",Font.BOLD+Font.ITALIC,20);
		Font F = g.getFont();
		g.setFont(F2);
		Color c = g.getColor();		
		g.setColor(Color.BLACK);
		g.drawString("Heuristic Selection:", 250, 70);
		g.drawString("simple random",250,90);
		g.drawString("Acceptance Method:", 650, 55);
		g.drawString("Improving & Equal", 650,70);	
		g.drawString("Low Level Heuristic", 30, 220);
		g.drawString("Method1", 35, 280);
		g.drawString("Method2", 35, 390);
		g.drawString("Method3", 35, 500);
		if(x12<255){
			x333 = x12;
			for(int i=0;i<10;i++){
				g.drawString(""+bit[i],x12,125);
				x12=x12+10;	
			}
			x12=x333+1;
		}else{
			if(y11<400){
				g.drawString("Method3", 260, 130);
				g.setColor(Color.red);
				g.drawLine(125, 505,175,505);
				g.drawLine(125, 505, 135, 520);
				g.drawLine(125, 505, 135, 490);
				g.draw3DRect(25, 455, 100, 100, true);
				g.setColor(Color.black);
				for(int i=0;i<10;i++){
					g.drawString(""+bit[i],x,y11);
					x=x+10;
				}
				y11=y11+1;
			}else{
				if(postion1!=postion2){
					if(y3<451&&!finishMethod3Part2){
						g.setColor(Color.red);
						g.drawLine(125, 505,175,505);
						g.drawLine(125, 505, 135, 520);
						g.drawLine(125, 505, 135, 490);
						g.draw3DRect(25, 455, 100, 100, true);
						g.setColor(Color.black);
						for(int i=0;i<10;i++){
							
							if(i==postion1){
								g.drawString(""+bit[i],x,y3);
								x1=x;
								x3=x;
							}
							else if(i==postion2){
								g.drawString(""+bit[i],x,y3);
								x2=x;
								x4=x;
							}else {
								if(postion1<postion2){
									if(i<postion1||i>postion2){
										g.drawString(""+bit[i],x,400);
									}
								}else if(postion1>postion2){
									if(i>postion1||i<postion2){
										g.drawString(""+bit[i],x,400);
									}						
								}
								
							}
							x=x+10;	
						}
						y3=y3+1;
					}else{
						if(x1!=x4&&x2!=x3){
							g.setColor(Color.red);
							g.drawLine(125, 505,175,505);
							g.drawLine(125, 505, 135, 520);
							g.drawLine(125, 505, 135, 490);
							g.draw3DRect(25, 455, 100, 100, true);
							g.setColor(Color.black);
							for(int i=0;i<10;i++){
								
								if(i==postion1){
									g.drawString(""+bit[i],x1,y3);
								}
								else if(i==postion2){
									g.drawString(""+bit[i],x2,y3);
								}else {
									if(postion1<postion2){
										if(i<postion1||i>postion2){
											g.drawString(""+bit[i],x,400);
										}
									}else if(postion1>postion2){
										if(i>postion1||i<postion2){
											g.drawString(""+bit[i],x,400);
										}						
									}
								}
								x=x+10;	
							}
							if(x3<x4){
								x1=x1+1;
								x2=x2-1;
							}else{
								x1=x1-1;
								x2=x2+1;
							}
							if(x1==x4&&x2==x3){
								finishMethod3Part2 =true;
							}
						}else{
							if(y3>400){
								g.setColor(Color.red);
								g.drawLine(125, 505,175,505);
								g.drawLine(125, 505, 135, 520);
								g.drawLine(125, 505, 135, 490);
								g.draw3DRect(25, 455, 100, 100, true);
								g.setColor(Color.black);
								for(int i=0;i<10;i++){
									if(i==postion1){
										g.drawString(""+bit[i],x1,y3);
									}
									else if(i==postion2){
										g.drawString(""+bit[i],x2,y3);
									}else {
										if(postion1<postion2){
											if(i<postion1||i>postion2){
												g.drawString(""+bit[i],x,400);
											}
										}else if(postion1>postion2){
											if(i>postion1||i<postion2){
												g.drawString(""+bit[i],x,400);
											}						
										}
									}
									x=x+10;	
								}
								y3=y3-1;	
								
							}else if(y3==400){
								y3=y3-1;
							}
						}
			
					}
					if(y2>299){
						g.setColor(Color.red);
						g.drawLine(125, 505,175,505);
						g.drawLine(125, 505, 135, 520);
						g.drawLine(125, 505, 135, 490);
						g.draw3DRect(25, 455, 100, 100, true);
						g.setColor(Color.black);
						if(postion1<postion2){
							for(int i=postion1+1;i<postion2;i++){
								g.drawString(""+bit[i],x5+i*10,y2);
								if(bit[i]==0){
									g.drawString("1",x5+i*10, y2+100);
								}else{
									g.drawString("0",x5+i*10, y2+100);
								}
							}
						}else{
							for(int i=postion2+1;i<postion1;i++){
								g.drawString(""+bit[i],x5+i*10,y2);
								if(bit[i]==0){
									g.drawString("1",x5+i*10, y2+100);
								}else{
									g.drawString("0",x5+i*10, y2+100);
								}
							}
						}
			
						y2=y2-1;
					}
					if(y2<300&&y3>400){
						g.setColor(Color.red);
						g.drawLine(125, 505,175,505);
						g.drawLine(125, 505, 135, 520);
						g.drawLine(125, 505, 135, 490);
						g.draw3DRect(25, 455, 100, 100, true);
						g.setColor(Color.black);
						if(postion1<postion2){
							for(int i=postion1+1;i<postion2;i++){
								if(bit[i]==0){
									g.drawString("1",x5+i*10, y2+100);
								}else{
									g.drawString("0",x5+i*10, y2+100);
								}
							}
						}else{
							for(int i=postion2+1;i<postion1;i++){
								if(bit[i]==0){
									g.drawString("1",x5+i*10, y2+100);
								}else{
									g.drawString("0",x5+i*10, y2+100);
								}
						}
					}
				}
			}
			if((y2<300&&y3<400&&finishMethod3Part2)||postion1==postion2){//allfinishedMethod3){
				g.setColor(Color.red);
				g.drawLine(125, 505,175,505);
				g.drawLine(125, 505, 135, 520);
				g.drawLine(125, 505, 135, 490);
				g.draw3DRect(25, 455, 100, 100, true);
				g.setColor(Color.black);
				g.setColor(Color.yellow);
				x=250;
				if(y12>125){
					for(int i=0;i<10;i++){
						g.drawString(""+bit3[i],x,y12);
						x=x+10;
					}
					y12=y12-1;
				}else{
					if(x11<652){
						x333=x11;
						for(int i=0;i<10;i++){
							g.drawString(""+bit3[i],x11,125);
							x11=x11+10;
						}
						x11=x333+1;
					}else{
		                Graphics2D g2 = (Graphics2D) g;
                        g2.setColor(Color.yellow);
                        for(int i=0,k=(int)(current/SCALEX)+OFFSETX;i<10;i++,k=k+10){
                        	g2.drawString(""+bit3[i],k, GRAPHY+OFFSETY-10);
                        }
                        if(xLine>GRAPHY-((current*current)/SCALEY)+OFFSETY){
                        	for(int i= GRAPHY+OFFSETY+20;i>xLine;i=i-20){
                        		g2.draw(new Line2D.Double((current/SCALEX)+OFFSETX, i, (current/SCALEX)+OFFSETX, i-20)); //x
                        	}
                        	xLine=xLine-2;
                        }else{
                        	g2.draw(new Line2D.Double((current/SCALEX)+OFFSETX, GRAPHY+OFFSETY+20, (current/SCALEX)+OFFSETX, GRAPHY-((current*current)/SCALEY)+OFFSETY)); //x
	                        
                        	if(yLine>OFFSETX-20){
                        		for(double i= (current/SCALEX)+OFFSETX;i>yLine;i=i-20){
                        			g2.draw(new Line2D.Double(yLine, GRAPHY-((current*current)/SCALEY)+OFFSETY, (current/SCALEX)+OFFSETX, GRAPHY-((current*current)/SCALEY)+OFFSETY));
                        			//g2.draw(new Line2D.Double(OFFSETX-20, GRAPHY-((current*current)/SCALEY)+OFFSETY, (current/SCALEX)+OFFSETX, GRAPHY-((current*current)/SCALEY)+OFFSETY)); //y
                        		}
                        		yLine=yLine-2;	
                        	}else{
                        		g2.drawString(""+current*current,OFFSETX-120, (int) (GRAPHY-((current*current)/SCALEY)+OFFSETY));
                        		g2.draw(new Line2D.Double((current/SCALEX)+OFFSETX, GRAPHY+OFFSETY+20, (current/SCALEX)+OFFSETX, GRAPHY-((current*current)/SCALEY)+OFFSETY)); //x
                        		g2.draw(new Line2D.Double(OFFSETX-20, GRAPHY-((current*current)/SCALEY)+OFFSETY, (current/SCALEX)+OFFSETX, GRAPHY-((current*current)/SCALEY)+OFFSETY)); //y
                        		if(function(decode(bit))<=function(decode(bit3))){
                        			g.setColor(Color.RED);
                        			g.drawString("unaccepted", 670, 130);
                        			g.setColor(Color.yellow);
                        			if(y12>=-60){
                        				x333=x11;
                						for(int i=0;i<10;i++){
                							g.drawString(""+bit3[i],x333,y12);
                							x333=x333+10;
                						}
                						y12=y12-1;
                						
                        			}else{
                						compare(bit3);
                						finishDrawMethod3 = true;
                						y3=400;
                						y2=400;
                						x1=0;
                						x2=0;
                						x3=0;
                						x4=0;
                						y1=400;
                						y11=160;
                						y12=400;
                						x11=250;
                						x12=0;
                						finishMethod3Part2=false;
                						allfinishedMethod3=false;
                						g.setColor(c);
                						g.setFont(F);
    	    							xLine=GRAPHY+OFFSETY+20;
                        			}
                        		}else{
                        			g.setColor(Color.RED);
                        			g.drawString("accepted", 670, 130);
                        			g.setColor(Color.yellow);
            						if(x11<1100){
            							x333=x11;
            							for(int i=0;i<10;i++){
            								g.drawString(""+bit3[i],x11,125);
            								x11=x11+10;
            							}
            							x11=x333+1;
            						}else{
            							compare(bit3);
            							finishDrawMethod3 = true;
            							y3=400;
            							y2=400;
            							x1=0;
            							x2=0;
            							x3=0;
            							x4=0;
            							y1=400;
            							y11=160;
            							y12=400;
            							x11=250;
            							x12=0;
            							finishMethod3Part2=false;
            							allfinishedMethod3=false;
            							g.setColor(c);
            							g.setFont(F);
    	    							xLine=GRAPHY+OFFSETY+20;
            							
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

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("start")){
			statistics = new ArrayList<Statistics>();
			run = true;
		}
		if(command.equals("end")){
			run = false;
			endButton = true;
			StatisticsTable table= new StatisticsTable(statistics);
			for(int i=0;i<statistics.size();i++){
				table.data[i][0]=""+statistics.get(i).count;
				table.data[i][1]="";
				table.data[i][2]="";
				table.data[i][4]="";
				for(int j=0;j<10;j++){
					table.data[i][1]=table.data[i][1]+statistics.get(i).preValue[j];
					table.data[i][2]=table.data[i][2]+statistics.get(i).curValue[j];
					table.data[i][4]=table.data[i][4]+statistics.get(i).curResult[j];
				}
				table.data[i][3]=""+statistics.get(i).state;
				table.data[i][5]=""+statistics.get(i).method;
			}
			
			 
		}
	}

	public class Statistics {
		int count;
		int[] preValue = new int[10];
		int[] curValue = new int[10];
		int[] curResult = new int[10];
		String state;
		int method;
		
	}
		
	
}




