import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;


public class MyLabel extends JLabel{
	private String value;
	public final int LABELWIDTH = 79;
	public final int LABELHEIGHT = 27;
	
	ImageIcon label_img;
	public MyLabel(String name,Config con){
		if(name.equals("Benchmark Function"))
			value = "f(x) = x ^ 2";
		else if(name.equals("Heuristic Selection"))
			value = "<html><p>Simple </p></p>Random</p></html>";
		else if(name.equals("Low Level Heuristics"))
			value = "<html><p>Inverse, </p></p>Reverse, Shift</p></html>";
		else if(name.equals("Acceptance Method"))
			value = "<html><p>Improving </p></p>and Equal</p></html>";
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(LABELWIDTH,LABELHEIGHT));
		this.setBackground(con.getLeftBgColor());	
		this.setForeground(Color.WHITE);
		Font font = new Font("Comic Sans MS",Font.BOLD ,10);
		this.setFont(font);
		this.setHorizontalAlignment(SwingConstants.CENTER); 
		this.setText(value);  
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
	
	}
	
	public MyLabel (Config con,String name){
		this.setPreferredSize(new Dimension(con.getLeftMenuWidth(),25));
		this.setBorder(null);
		this.setOpaque(true);
		this.setBackground(con.getLeftBgColor());	
		this.setForeground(Color.WHITE);
		Font font = new Font("Arial",Font.BOLD ,14);
		this.setFont(font);
		this.setHorizontalAlignment(SwingConstants.CENTER); 
		this.setText(name);
	}
	
	
	public MyLabel (Config con,String img,int width,int height){
		this.setOpaque(true);
		label_img = con.getImgUrl(img);
		//label_img.setImage(label_img.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)); 
		//this.setBackground(new Color(153,153,153));
		this.setIcon(label_img);
		this.setPreferredSize(new Dimension(label_img.getIconWidth(),label_img.getIconHeight()));
		this.setBorder(null);
		this.setFocusCycleRoot(false);
	}
		
}

