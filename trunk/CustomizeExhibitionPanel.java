import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

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
 * @author	   	Alexander Jermstad (asj08u) 
 * 			
 */

/**
 *  This panel is middle part in the interface. The sub-menu panel and animation are built on this.  
 */


public class CustomizeExhibitionPanel extends JPanel{
	
	/**
	 *  the width of this panel
	 */
	int width;
	/**
	 *  the height of this panel 
	 */
	int height;
	/**
	 *  the sub-menu panel 
	 */
	CustomizePanel p_left;
	/**
	 *  the bar 
	 */
	JPanel  p_bar ;
	/**
	 *  the right panel of the customize exhibition panel
	 */
	JPanel p_right;
	/**
	 *  the config instance
	 */
	Config con ;
	/**
	 *  the split bar image
	 */
	ImageIcon btn_split_img ;
	/**
	 *  the main frame
	 */
	VSHMainFrame frame;
	
	/**
	 *  the menu panel
	 */

	InitalMenuPanel initalMenuPanel;
	
	/**
	 *  the background about the flow chart of hyper-heuristics 
	 */
	MyLabel flowchart;
	/**
	 *  the animation panel
	 */
	AnimationPanel animationPanel;

	/**
	 * initial the CustomizeExhibitionPanel instance
	 * @param frame  main frame
	 * @param width	
	 * @param height
	 */
	CustomizeExhibitionPanel(VSHMainFrame frame,int width,int height)
	{
		

		this.width = width;
		this.height = height;
		this.frame = frame;
		initPanel();
	}
	/**
	 *  initial the parameter about this panel
	 */
	private void initPanel()
	{
		con = new Config();
		btn_split_img = con.getImgUrl("btn_split_l.png");
		int splitbarwidth = con.getSplitBarWidth() ; 
		int leftwidth = con.getLeftMenuWidth() ;
		int rightwidth = width - splitbarwidth - leftwidth ;
		this.setPreferredSize(new Dimension(width,height));
		this.setOpaque(false);
		this.setLayout(con.getFlowLayout(0,0,0));
		p_left = new CustomizePanel(frame, leftwidth, height);
		p_left.setLayout(con.getFlowLayout(1,0,0)); 
		this.add(p_left);
		
		//=============================
		//  the split bar
		//=============================
		p_bar = new JPanel();
		p_bar.setPreferredSize(new Dimension(splitbarwidth,height));
		p_bar.setBackground(con.getSplitBgColor());
		p_bar.setLayout(con.getFlowLayout(1, 0, (height - btn_split_img.getIconHeight())/2-30));
		this.add(p_bar);

		//=============================
		//  the right panel
		//=============================
		p_right = new JPanel();
		p_right.setPreferredSize(new Dimension(rightwidth,height));
		//p_right.setBackground(con.getMainColor());
		p_right.setLayout(new BorderLayout());
		initalMenuPanel = new InitalMenuPanel(rightwidth,con,frame);
		p_right.add(initalMenuPanel,BorderLayout.NORTH);
		animationPanel = new AnimationPanel(frame, con);
		animationPanel.setBackground(new Color(153,153,153));
		flowchart = new MyLabel(con,"flowchart.png",rightwidth,height-initalMenuPanel.getHeight());
		animationPanel.add(flowchart);
		p_right.add(animationPanel,BorderLayout.CENTER);
		this.add(p_right);
		
		
		
	}


}
