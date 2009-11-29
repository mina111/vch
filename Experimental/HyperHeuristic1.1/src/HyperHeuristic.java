import java.util.*;

import javax.swing.*;
import java.awt.*;
/**
 * 
 * @author Lao
 * @version 1.1  
 * in this version, I show how the three lower level heuristic work by animation.
 * I create a new thread for draw the animation, and the main thread for calculation. Each time when the lower level heuristic finishes, its processes will be showed.
 */


public class HyperHeuristic extends JFrame implements Runnable{
	/**
	 * the size of the picture
	 */
	public  static final int WIDTH=800, HEIGHT=600;
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
	public int y1=400,y2=400,y3=400, x1,x2,x3,x4;
	/**
	 *  the instance of a JPanel for drawing
	 */
	MyPanel mp;
	/**
	 *  to judge if the animation finishes and the calculation can start
	 */
	public static boolean continueRun;
	/**
	 * to decide to show which heuristic
	 */
	public boolean method1,method2,method3;
	/**
	 * to judge if the draw finishes
	 */
	public boolean finishDrawMethod1,finishDrawMethod2,finishDrawMethod3,allfinishedMethod2,finishMethod2Part2,finishMethod3Part2,allfinishedMethod3;
	public static void main (String[] args){
		HyperHeuristic hh = new HyperHeuristic();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(hh).start();	

		while(true){
			if(continueRun){
				hh.randomChooseHeuristic();
			}
		}
	}
	
	/**
	 * 
	 * @see java.lang.Runnable#run()
	 * the thread for drawing
	 */
	public void run(){
		while(true){
		if(finishDrawMethod1||finishDrawMethod2||finishDrawMethod3){
			try {
				Thread.sleep(5000);
				
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
		mp.repaint();
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	
	
	public HyperHeuristic(){
		super("HyperHeuristic");
		this.initialization();
		this.setBounds(400, 400, WIDTH, HEIGHT);
		this.setResizable(false);		
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mp=new MyPanel();
		this.add(mp);
		mp.requestFocus();
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
		 double randomNumber = rd.nextDouble();;
		 if (randomNumber >= 0 && randomNumber< 1.0/3)  
		 {
			 method1();
			 method1 =true;
			 continueRun = false;		 
		 }
		 else if (randomNumber >= (1.0/3) && randomNumber < (1.0-1.0/3))
		 {
			 method2();
			 method2 =true;
			 continueRun = false;		 
		 }
		 else  
		 {
			 method3();
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
		int x = 40,x_=-200 ;
		 MyPanel (){
				this.setSize(WIDTH, HEIGHT);
				this.setBackground(Color.BLUE);
				this.setDoubleBuffered(true);
			 }
		 public void paintComponent(Graphics g){
			 	Font F1=new Font("Font1",Font.BOLD+Font.ITALIC,36);
			 	super.paintComponent(g);
				Font F = g.getFont();
				g.setFont(F1);
				Color c = g.getColor();				
				g.setColor(Color.RED);
				g.drawString("HyperHeuristic", 320, 100);
				g.setColor(c);
				g.setFont(F);
				if(!method1&&!method2&&!method3){
					Font F2=new Font("Font2",Font.BOLD+Font.ITALIC,36);
					g.setFont(F2);
					g.setColor(Color.BLACK);
					g.drawString("The first random number is", 100, 200);
					for(int i=0;i<10;i++){
						g.drawString(""+bit[i],x_,400);
						x_=x_+30;	
					}
					continueRun = true;
				}
				if(method1){
					drawMethod1(g);
				}else if (method2){
					drawMethod2(g);
				}else if(method3){
					drawMethod3(g);
				}
				
			}
	}
	
	/**
	 * 
	 * @param g the instance of Graphics 
	 *  the method of drawing the first heuristic
	 */
	private void drawMethod1(Graphics g){	
		int x=100;
		Font F2=new Font("Font2",Font.BOLD+Font.ITALIC,36);
		Font F = g.getFont();
		g.setFont(F2);
		Color c = g.getColor();		
		g.setColor(Color.BLACK);
		if(y1<=500){
			if(y1<500){
			g.drawString("Low Level Heuristic: Method1",100, 200);
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
				x=x+30;	
			}
			y1=y1+2;
			}else{
				y1=y1+2;
			}
		}


	
		if(y1==502){
				x=100;
				g.drawString("After invoking method1",100, 300);
				g.drawString("The number becomes",100, 350);
				for(int i=0;i<10;i++){
					g.drawString(""+bit1[i],x,400);
					x=x+30;	
				}
				g.drawString("="+decode(bit1),x, 400);
				x=100;
				g.drawString("The original number is ",100, 450);
				for(int i=0;i<10;i++){
					g.drawString(""+bit[i],x,500);
					x=x+30;	
				}
				g.drawString("="+decode(bit),x, 500);
				Font F3=new Font("Font2",Font.BOLD+Font.ITALIC,20);
				g.setFont(F3);
					g.drawString("f("+decode(bit1)+")="+function(decode(bit1)),x+150,400);
						g.drawString("f("+decode(bit)+")="+function(decode(bit)),x+150,500);
				if(function(decode(bit))<=function(decode(bit1))){
					g.drawLine(100,500,400,500);
				}
				else if(function(decode(bit))>function(decode(bit1))){
					g.drawLine(100,400,400,400);
				}
				
				
				compare(bit1);
				finishDrawMethod1 = true;
				y1=400;
			}
			g.setColor(c);
			g.setFont(F);
	
	}
	
	/**
	 * 
	 * @param g the instance of Graphics 
	 *  the method of drawing the second heuristic
	 */
	private void drawMethod2(Graphics g){	
		int x=100;
		Font F2=new Font("Font2",Font.BOLD+Font.ITALIC,36);
		Font F = g.getFont();
		g.setFont(F2);
		Color c = g.getColor();		
		g.setColor(Color.BLACK);
		if(postion1!=postion2){
			if(y1<451&&!finishMethod2Part2){
				g.drawString("Low Level Heuristic: Method2",100, 300);
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
					x=x+30;	
				}
				y1=y1+1;
			}else{
				if(x1!=x4&&x2!=x3){
					g.drawString("Low Level Heuristic: Method2",100, 300);
					for(int i=0;i<10;i++){
						
						if(i==postion1){
							g.drawString(""+bit[i],x1,y1);
						}
						else if(i==postion2){
							g.drawString(""+bit[i],x2,y1);
						}else{
							g.drawString(""+bit[i],x,400);
						}
						x=x+30;	
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
						g.drawString("Low Level Heuristic: Method2",100, 300);
						for(int i=0;i<10;i++){
							
							if(i==postion1){
								g.drawString(""+bit[i],x1,y1);
							}
							else if(i==postion2){
								g.drawString(""+bit[i],x2,y1);
							}else{
								g.drawString(""+bit[i],x,400);
							}
							x=x+30;	
						}
						y1=y1-1;	
						
					}else if(y1==400){
						y1=y1-1;
					}
				}
	
			}
			
		}
		if((y1<400&&finishMethod2Part2)||postion1==postion2){
				if(postion1==postion2){
					g.drawString("There is no need to move",100, 150);
					g.drawString(" because of the same postions",100, 200);
				}
				x=100;
				g.drawString("After invoking method2",100, 300);
				g.drawString("The number becomes",100, 350);
				for(int i=0;i<10;i++){
					g.drawString(""+bit2[i],x,400);
					x=x+30;	
				}
				g.drawString("="+decode(bit2),x, 400);
				x=100;
				g.drawString("The original number is ",100, 450);
				for(int i=0;i<10;i++){
					g.drawString(""+bit[i],x,500);
					x=x+30;	
				}
				g.drawString("="+decode(bit),x, 500);
				Font F3=new Font("Font2",Font.BOLD+Font.ITALIC,20);
				g.setFont(F3);
					g.drawString("f("+decode(bit2)+")="+function(decode(bit2)),x+150,400);
						g.drawString("f("+decode(bit)+")="+function(decode(bit)),x+150,500);
				if(function(decode(bit))<=function(decode(bit2))){
					g.drawLine(100,500,400,500);
				}
				else if(function(decode(bit))>function(decode(bit2))){
					g.drawLine(100,400,400,400);
				}
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
			}
		g.setColor(c);
		g.setFont(F);
	}
	
	
	
	/**
	 * 
	 * @param g the instance of Graphics 
	 *  the method of drawing the third heuristic
	 */
	private void drawMethod3(Graphics g){	
		int x=100,x5=100;
		Font F2=new Font("Font2",Font.BOLD+Font.ITALIC,36);
		Font F = g.getFont();
		g.setFont(F2);
		Color c = g.getColor();		
		g.setColor(Color.BLACK);
		if(postion1!=postion2){
			if(y3<451&&!finishMethod3Part2){
				g.drawString("Low Level Heuristic: Method3",100, 200);
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
					x=x+30;	
				}
				y3=y3+1;
			}else{
				if(x1!=x4&&x2!=x3){
					g.drawString("Low Level Heuristic: Method3",100, 200);
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
						x=x+30;	
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
						g.drawString("Low Level Heuristic: Method3",100, 200);
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
							x=x+30;	
						}
						y3=y3-1;	
						
					}else if(y3==400){
						y3=y3-1;
					}
				}
	
			}
			if(y2>299){
				if(postion1<postion2){
					for(int i=postion1+1;i<postion2;i++){
						g.drawString(""+bit[i],x5+i*30,y2);
						if(bit[i]==0){
							g.drawString("1",x5+i*30, y2+100);
						}else{
							g.drawString("0",x5+i*30, y2+100);
						}
					}
				}else{
					for(int i=postion2+1;i<postion1;i++){
						g.drawString(""+bit[i],x5+i*30,y2);
						if(bit[i]==0){
							g.drawString("1",x5+i*30, y2+100);
						}else{
							g.drawString("0",x5+i*30, y2+100);
						}
					}
				}
	
				y2=y2-1;
			}
			if(y2<300&&y3>400){
				if(postion1<postion2){
					for(int i=postion1+1;i<postion2;i++){
						if(bit[i]==0){
							g.drawString("1",x5+i*30, y2+100);
						}else{
							g.drawString("0",x5+i*30, y2+100);
						}
					}
				}else{
					for(int i=postion2+1;i<postion1;i++){
						if(bit[i]==0){
							g.drawString("1",x5+i*30, y2+100);
						}else{
							g.drawString("0",x5+i*30, y2+100);
						}
				}
			}
			}
		}
		if((y2<300&&y3<400&&finishMethod3Part2)||postion1==postion2){//allfinishedMethod3){
				if(postion1==postion2){
					g.drawString("There is no need to move",100, 150);
					g.drawString(" because of the same postions",100, 200);
				}
				x=100;
				g.drawString("After invoking method3",100, 300);
				g.drawString("The number becomes",100, 350);
				for(int i=0;i<10;i++){
					g.drawString(""+bit3[i],x,400);
					x=x+30;	
				}
				g.drawString("="+decode(bit3),x, 400);
				x=100;
				g.drawString("The original number is ",100, 450);
				for(int i=0;i<10;i++){
					g.drawString(""+bit[i],x,500);
					x=x+30;	
				}
				g.drawString("="+decode(bit),x, 500);
				Font F3=new Font("Font2",Font.BOLD+Font.ITALIC,20);
				g.setFont(F3);
					g.drawString("f("+decode(bit3)+")="+function(decode(bit3)),x+150,400);
						g.drawString("f("+decode(bit)+")="+function(decode(bit)),x+150,500);
				if(function(decode(bit))<=function(decode(bit3))){
					g.drawLine(100,500,400,500);
				}
				else if(function(decode(bit))>function(decode(bit3))){
					g.drawLine(100,400,400,400);
				}
				compare(bit3);
				finishDrawMethod3 = true;
				y3=400;
				y2=400;
				x1=0;
				x2=0;
				x3=0;
				x4=0;
				finishMethod3Part2=false;
				allfinishedMethod3=false;
			}

		g.setColor(c);
		g.setFont(F);
	}
	
		
	
}




