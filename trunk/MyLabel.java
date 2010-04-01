
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

/**
 *  A Visualisation Tool for 
 *  Selection Hyper-Heuristics                                    <br>   
 *  															  <br>
 *  http://code.google.com/p/vch/   							  <br>
 *  															  <br>
 *  Module:                  G52GRP, University of Nottingham     <br>
 *  															  <br>
 *  Group:        gp09-exo    						  			  <br>
 * @author 	   	Lao Jingqi (jxl29u)
 * @author	   	Zhang Chao (cxz09u)
 * @author		Thomas Barton (txb18u)
 * @author		Ben Jenkinson (bxj08u)
 * @author	   	Alexander Jermstad (asj08u) 					  <br>
 * 			
 */

/**
 *  This class is the customized Label
 */
public class MyLabel extends JLabel{

	/**
	 *  the information on label
	 */
	private String value;
	/**
	 *  the width
	 */
	public final int LABELWIDTH = 120;
	/**
	 *  height
	 */
	public final int LABELHEIGHT = 27;
	/**
	 *  the image for this label
	 */
	ImageIcon label_img;
	/**
	 *  define the object  with   name and config
	 * @param name
	 * @param con
	 */
	public MyLabel(String name,Config con){
		if(name.equals("Benchmark Function"))
			value = "f(x) = x ^ 2";
		else if(name.equals("Heuristic Selection"))
			value = "Simple Random";
		else if(name.equals("Low Level Heuristics"))
			value = "<html><p>Inverse,Reverse,Flip One Bit</p></p>Shift,Steepest Gradient</p></html>";
		else if(name.equals("Acceptance Method"))
			value = "Improving or Equal";
		this.setOpaque(true);
		if(name.equals("Low Level Heuristics"))
			this.setPreferredSize(new Dimension(LABELWIDTH+30,LABELHEIGHT));
		else
			this.setPreferredSize(new Dimension(LABELWIDTH,LABELHEIGHT));
		this.setBackground(con.getLeftBgColor());	
		this.setForeground(Color.WHITE);
		Font font = new Font("Comic Sans MS",Font.BOLD ,10);
		this.setFont(font);
		this.setHorizontalAlignment(SwingConstants.CENTER); 
		this.setText(value);  
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
	
	}
	
	/**
	 *  define the object  with config and name
	 * @param con
	 * @param name
	 */
	public MyLabel (Config con,String name){
		this.setPreferredSize(new Dimension(con.getLeftMenuWidth(),12));
		this.setBorder(null);
		this.setOpaque(true);
		this.setBackground(con.getLeftBgColor());	
		this.setForeground(Color.WHITE);
		Font font = new Font("Arial",Font.BOLD ,11);
		this.setFont(font);
		this.setHorizontalAlignment(SwingConstants.CENTER); 
		this.setText(name);
	}
	
	/**
	 *  define the object button with config, string name of image for icon, width and height
	 * @param con
	 * @param img
	 * @param width
	 * @param height
	 */
	public MyLabel (Config con,String img,int width,int height){
		this.setOpaque(true);
		label_img = con.getImgUrl(img);
		this.setIcon(label_img);
		this.setPreferredSize(new Dimension(label_img.getIconWidth(),label_img.getIconHeight()));
		this.setBorder(null);
		this.setFocusCycleRoot(false);
	}
		
}

