////////////////////////////////////////////////////////////
/////
/////  A Visualisation Tool for 
/////  Selection Hyper-Heuristics
/////
/////  http://code.google.com/p/vch/
/////
/////  Group:			gp09-exo
/////  FullNames:		Thomas Barton (txb18u)
/////					Zhang Chao (cxz09u)
/////					Ben Jenkinson (bxj08u)
/////					Alexander Jermstad (asj08u)
/////					Lao Jingqi (jxl29u)
/////
/////  CreationDate: 	2010-02-15
/////  Module:			G52GRP, University of Nottingham
/////
////////////////////////////////////////////////////////////



// Graph

import java.awt.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class Graph extends JPanel {
	
	// DECLARE GLOBAL VARIABLES & STATE IDENTIFIERS
	private Vch vch;
	private int xCentre;
	private int yCentre;
	private int xLength;
	private int yLength;
	private int xMaxValue;
	private int yMaxValue;
	private int notchDistance = 10;
	private int notchLength = 7;
	private boolean positive;
	private boolean drawAxisNotch = true;
	private boolean drawLabel = true;
	private boolean plotGraph = false;
	public enum Type { SIN, EXPONENTIAL };
	private Type type;
	int[] plot;
	double[] plotSinP;
	double[] plotSinN;
	
	// CONSTRUCTOR
	//Centre point constructor
	public Graph(int xCen, int yCen, int xLen, int yLen, boolean isPositive) {
		// Save the parent frame.
		//this.vch = parent;
		//this.vch.printDebugMessage("Graph()");
			xCentre 	= xCen;
			yCentre 	= yCen;
			xLength 	= xLen;
			yLength 	= yLen;
			positive 	= isPositive;
			calculateAxis();
	} // END Graph constructor
	
	//Top left constructor
 //   public Graph() {
 //   	
 //   }
 //   
 //   //Box constructor
 //   public Graph() {
 //   	
 //   }
	
	// METHODS
	
	// Pass X and Y co-ordinates of the centre of the graph, and how wide the graph must be.
	// The graph must be told if its justPositive, i.e. only a positive results graph such as x^2
	// A graph of sin x would be justPositive = false as it has negative results?
	public void calculateAxis() {
		calculateXAxis();
		calculateYAxis();
	}
	
	public void calculateXAxis() {
		if(positive){
			
		} else {
			
		}
	}
	
	public void calculateYAxis() {
		if(positive){
			
		} else {
			
		}
	}
	
	public void plotGraphExp(int xMax) {
		type 		= type.EXPONENTIAL;
		plot 		= new int[xLength];
		xMaxValue	= xMax;
		yMaxValue 	= xMaxValue * xMaxValue;
		plotGraph 	= true;
		double scaleX = (double)xMaxValue / (double)xLength;
		double scaleY = (double)yMaxValue / (double)yLength;
		for(int k = 0; k < xLength; k++){
			plot[k] = (int)(((k/scaleX)*(k/scaleX))/scaleY);
		}
	}
	
	public void plotGraphSin(){
		type		= type.SIN;
		plotSinP 	= new double[xLength];
		plotSinN	= new double[xLength];
		xMaxValue	= 360;
		yMaxValue 	= 1;
		plotGraph = true;
		double scaleX = (double)xMaxValue / (double)xLength;
		double scaleY = (double)yMaxValue / (double)yLength;
		System.out.println("scaleY : " + scaleY + "\tyLength : " + yLength + "\tyMaxValue : " + yMaxValue);
		for(int k = 0; k < xLength; k++){
			plotSinP[k] = (Math.sin(Math.toRadians(k*scaleX)))/scaleY;
			System.out.println((Math.sin(Math.toRadians(k*scaleX))));
		}
		for(int k = 0; k < xLength; k++){
			plotSinN[k] = (Math.sin(Math.toRadians(-k*scaleX)))/scaleY;
		}
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setPaint(Color.white);
		FontMetrics fm = g2d.getFontMetrics();
		int fontHeight = fm.getHeight();
		
		if(positive) {
			g2d.drawLine(xCentre, yCentre, xCentre+xLength, yCentre);	//Draw x Axis
			g2d.drawLine(xCentre, yCentre, xCentre, yCentre-yLength);	//Draw y Axis
			if(drawAxisNotch) {
				for(int j = 0; j <= xLength; j+=notchDistance) {
					if(j == 0 || j == xLength || j == (xLength/2)) {
						g2d.drawLine(xCentre+j, yCentre, xCentre+j, yCentre+(notchLength*2));
					} else {
						g2d.drawLine(xCentre+j, yCentre, xCentre+j, yCentre+notchLength);
					}
				}
				
				for(int j = 0; j <= yLength; j+=notchDistance) {
					if(j == 0 || j == yLength || j == yLength/2) {
						g2d.drawLine(xCentre, yCentre-j, xCentre-(notchLength*2), yCentre-j);
					} else {
						g2d.drawLine(xCentre, yCentre-j, xCentre-notchLength, yCentre-j);
					}
				}
			}
			
			if(drawLabel) {
				g2d.drawString("0",xCentre-13,yCentre+14);
				g2d.drawString(""+xMaxValue,xCentre+xLength-(fm.stringWidth(""+xMaxValue)/2),yCentre+(notchLength*2)+fm.getHeight()); 		//Draw X Max
				g2d.drawString(""+yMaxValue,xCentre-(notchLength*2)-fm.stringWidth(""+yMaxValue)-2,yCentre-yLength+(fm.getHeight()/2)-2);	//Draw Y Max
			}
			
			
			
			
		} else if(positive == false) {
			g2d.drawLine(xCentre-xLength, yCentre, xCentre+xLength, yCentre);	
			g2d.drawLine(xCentre, yCentre+yLength, xCentre, yCentre-yLength);
			if(drawAxisNotch){
				for(int j = 0; j <= xLength; j+=notchDistance) {
					if(j == 0 || j == xLength || j == (xLength/2)) {
						g2d.drawLine(xCentre+j, yCentre, xCentre+j, yCentre+(notchLength*2));
						g2d.drawLine(xCentre-j, yCentre, xCentre-j, yCentre+(notchLength*2));
					} else {
						g2d.drawLine(xCentre+j, yCentre, xCentre+j, yCentre+notchLength);
						g2d.drawLine(xCentre-j, yCentre, xCentre-j, yCentre+notchLength);
					}
				}
				
				for(int j = 0; j <= yLength; j+=notchDistance) {
					if(j == 0 || j == yLength || j == yLength/2) {
						g2d.drawLine(xCentre, yCentre-j, xCentre-(notchLength*2), yCentre-j);
						g2d.drawLine(xCentre, yCentre+j, xCentre-(notchLength*2), yCentre+j);
					} else {
						g2d.drawLine(xCentre, yCentre-j, xCentre-notchLength, yCentre-j);
						g2d.drawLine(xCentre, yCentre+j, xCentre-notchLength, yCentre+j);
					}
				}
			}
		}
		
		
		if(plotGraph && type == type.EXPONENTIAL){
			for(int k = 0; k < plot.length-1; k++){
				g2d.drawLine(xCentre+k, yCentre-plot[k], xCentre+k+1, yCentre-plot[k+1]);
			}	
		}
		
		if(plotGraph && type == type.SIN){
			for(int k = 0; k < plotSinP.length-1; k++){
				g2d.drawLine(xCentre+k, (int)(yCentre-plotSinP[k]), xCentre+k+1, (int)(yCentre-plotSinP[k+1]));
			}
			for(int k = 0; k < plotSinN.length-1; k++){
				g2d.drawLine(xCentre-k, (int)(yCentre-plotSinN[k]), xCentre-k-1, (int)(yCentre-plotSinN[k+1]));
			}
		}
	}
	// LISTENERS
	
} // END class Graph