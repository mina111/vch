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
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.awt.geom.*;



public class Vch extends JFrame implements ActionListener {
		// GLOBAL VARIABLES
        int[] resultsArray = new int[2048];
        public static int boundaryMinX = 0;
        public static int boundaryMaxX = 1024;
        public static int OFFSETX = 150;
        public static int OFFSETY = 20;
        public static int GRAPHX = 512;
        public static int GRAPHY = 512;
        public static int SCALEY = (1024 * 1024) / GRAPHY;
        public static int SCALEX = 1024 / GRAPHX;
        public boolean vchrun = true;
        public boolean DrawResultsTable = true;
        public boolean DrawGridLines = true;
        public static int THREADSPEED = 300;
        public int Current = 0;
        public int GlobalBest = 1024;
        
       	// Create and seed the random number generator
        public static Random random = new Random( System.nanoTime() );
		
        public  static final int WIDTH=960, HEIGHT=768;
        JPanel problemDomainArea, state,jp;
        VCHUIPanel vchPanel = new VCHUIPanel();
        JComboBox problemDomain;
        JButton start, end;
        JEditorPane currentResult, times, currentMethod;
        int count,methodNum;
        public int [] bit = new int[10];
        
        
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
                problemDomain = new JComboBox(new String[]{"", "f(x)=x^2"});
                problemDomain.setBounds(30,150,150,30);
                problemDomainArea.setLayout(null);
                JTextPane jfp = new JTextPane();
                jfp.setText("Problem Domain");
                jfp.setBounds(40,100,100,30);
                
                end = new JButton("end");
                end.setBounds(110,30,80,39);
                end.setActionCommand("end");
                end.addActionListener(this);
                
                start = new JButton("start");
                start.setBounds(20,30,80,39);
                start.setActionCommand("start");
                start.addActionListener(this);
                
                problemDomainArea.add(jfp);
                problemDomainArea.add(problemDomain);
                problemDomainArea.add(start);
                problemDomainArea.add(end);
                state = new JPanel();
                state.setLayout(null);
                state.setPreferredSize (new Dimension(256,512));
                currentResult = new JEditorPane("text/html", "currentResult is\n<font size='+1' color='blue'>"+bit+"</font>\n");
                currentResult.setBounds(20,20,200,100);
                times = new JEditorPane("text/html", "count  <font size='+1' color='red'>"+ count+"</font>\n");
                times.setBounds(20,140,200,100);
                currentMethod = new JEditorPane("text/html", "currentMethod is   <font size='+1' color='red'>"+ methodNum+"</font>\n");
                currentMethod.setBounds(20,260,200,100);
                state.add(currentResult);
                state.add(times);
                state.add(currentMethod);
                jp.add(problemDomainArea,BorderLayout.NORTH);
                jp.add(state,BorderLayout.CENTER);
                jp.setBorder(new BevelBorder(BevelBorder.RAISED));

                vchPanel.setPreferredSize (new Dimension(700,600));
                vchPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                
                this.add(jp,BorderLayout.WEST);
                this.add(vchPanel,BorderLayout.CENTER);  
                this.setResizable(false);              
                this.setVisible(true);
                vchPanel.requestFocus(true);
        }

        
        public static void main(String[] args){
                new Vch();     
        }
       
        /*private class MyPanel extends JPanel{
                 MyPanel (){
			         this.setSize(700, 600);
			         this.setBackground(Color.BLUE);
			         this.setDoubleBuffered(true);
			         VCHUIPanel vhcP = new VCHUIPanel();
			         this.add(vhcP,BorderLayout.CENTER);
			         this.setVisible(true);
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
                 }              
        }*/
        
        public static int convertArrayToInt(int[] i) {
                
                // Converts an array of 1s & 0s to a String of binary, and then to an Int
                String str = Arrays.toString(i).replace(", ", "");
                str = str.substring(1, str.length()-1);
                
                return Integer.parseInt(str, 2);
                
        } // END convertArrayToInt
        
        public static int[] convertIntToArray(int i) {
                
                // Converts an integer to a binary string, and then inserts each digit into an array.
                
                // Check X value does not exceed the boundaries.
                checkXValue(i);
                
                String binaryString = Integer.toBinaryString(i);
                while( binaryString.length() < Integer.toBinaryString(boundaryMaxX).length() ){
                        
                        binaryString = "0" + binaryString;
                        
                }
                int[] intArray = new int[binaryString.length()];
                for( int j=0; j<binaryString.length(); j++ ) {
                        intArray[j] = Character.getNumericValue( binaryString.charAt(j) );
                }
                
                return intArray;
                
        } // END convertIntToArray
        
        public static int applyHeuristic(int x) {
                
                int h = random.nextInt(2);
                
                if( h == 0 ){
                        x = generateRandomInt();
                } else {
                        x = convertArrayToInt(heuristicFlip(convertIntToArray(x)));
                }
                
                return x;
                
        } // END applyHeuristic
        
        public static int[] heuristicFlip(int[] ints) {
                
                // Flips a random bit of the array
                int i = random.nextInt(ints.length);
                
                if( ints[i] == 1 ) {
                        ints[i] = 0;
                } else {
                        ints[i] = 1;
                }
                
                return ints;
                
        } // END heuristicFlip
        
        public static int applyFunction(int x) {
                
                x = x*x;
                return x;
                
        } // END applyFunction(int x)
        
        public static int generateRandomInt() {
                
                // Returns a random int, between the two boundaries.
                return random.nextInt(boundaryMaxX-boundaryMinX)+boundaryMinX;
                
        } // END generateRandomInt()
        
        public static void checkXValue(int x) {
                
                if( x>boundaryMaxX || x<boundaryMinX ) {
                        System.out.println("ERROR: Value (" + x + ") not within x value boundary");
                        System.exit(-1);
                }
                
        } // END checkXValue(int x)
        
        public void actionPerformed(ActionEvent e) {
        	if ("start".equals(e.getActionCommand())) {
        		vchPanel.setRun(true);
        	} 
        	if ("end".equals(e.getActionCommand())) {
        		vchPanel.setRun(false);
        	}
        }
        
        public void setCount(int counter){
        	times.setText("Iterations:"+counter);
        }
        
        public void setCurrentResult(int currentRes){
        	currentResult.setText("Current:"+currentRes);
        }
}


