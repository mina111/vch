import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;



public class Animation extends JFrame implements Runnable{

	public  static final int WIDTH=1280, HEIGHT=768;
	JPanel problemDomainArea, state,jp; 
	MyPanel process;
	JComboBox problemDomain;
	JButton start, end;
	JEditorPane currentResult, times, currentMethod;
	int count,methodNum;
	public int [] bit = new int[10];
	public Animation(){
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
		start = new JButton("start");
		start.setBounds(20,30,80,39);
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
		process = new MyPanel();
		process.setBorder(new BevelBorder(BevelBorder.LOWERED));
		process.setPreferredSize (new Dimension(1024,768));
		this.add(jp,BorderLayout.WEST);
		this.add(process,BorderLayout.CENTER);		
		this.setResizable(false);		
		this.setVisible(true);
		process.requestFocus();
		
	}

	@Override
	public void run() {
		
	}
	public static void main(String[] args){
		new Animation();
	}
	
	private class MyPanel extends JPanel{
		 MyPanel (){
				this.setSize(1024, 768);
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
		 }		 
	}

}
