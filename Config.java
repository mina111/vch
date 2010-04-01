import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.UIManager;


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
 *  This class is used to do the configuration  on the whole interface.
 */

public class Config
{
	/**
	 *  the path of skin and icon
	 */
	String skin_dir;
	/** 
	 *  the title
	 */
	String softtitle ;
	/**
	 *  the width
	 */
	int width;
	/**
	 *  the height
	 */
	int height;
	/**
	 *  the width of left menu
	 */
	int leftmenuwidth;
	/**
	 *  the width of split bar
	 */
	int splitbarwidth ;
	/**
	 *  Whether the window is at top if it is full screen 
	 */
	boolean iffulltop;
	/**
	 *  Whether the window is at the top if it is normal size
	 */
	boolean ifnormaltop;
	
	/** 
	 *  the split height
	 */
	private int splitbarHeight;
	
	/**
	 *  initial the config instance
	 */
	Config()
	{
		skin_dir = "/icon";
		width = 1024 ; 
		height = 728 ; 
		iffulltop = true ; 
		ifnormaltop = false ; 
		leftmenuwidth = 150 ; 
		splitbarwidth = 6 ; 
		splitbarHeight = 6;
		softtitle = "Visualisation Tool for a Selection Hyper-Heuristic (code.google.com/p/vch)";
	}

	/**
	 *  get the name of directory of skin and icon
	 * @return
	 */
	public String getSkinDir()
	{
		return skin_dir;
	}

	/**
	 *  get the image
	 * @param img
	 * @return
	 */
	public ImageIcon getImgUrl(String img) 
	{
		return new ImageIcon(VSHMainFrame.class.getResource(getSkinDir() + "/" + img));
	}
	/**
	 * get the minimum size of the frame
	 * @param full
	 * @return
	 */
	public Dimension getFrameSmallSize(boolean full)
	{
		if(!full)
		{
			return new Dimension(width,height);
		}
		else
		{
			Toolkit kit = Toolkit.getDefaultToolkit();
			return new Dimension(kit.getScreenSize());
		}
	}
	
	/**
	 *  get the size of screen
	 */
	public Dimension getScreenSize()
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		return new Dimension(kit.getScreenSize());
	}

	/**
	 *  get the instance of layout
	 * @param flag left
	 * @param hgap center
	 * @param vgap right
	 * @return
	 */
	public FlowLayout getFlowLayout(int flag,int hgap,int vgap)
	{
		FlowLayout fl = new FlowLayout();
		fl.setHgap(hgap);
		fl.setVgap(vgap);
		fl.setAlignment(flag);
		return fl;
	}
	
	/**
	 * get the left menu width
	 * 
	 * @return
	 */
	public int getLeftMenuWidth()
	{
		return this.leftmenuwidth;
	}

	/**
	 * get the split bar width
	 * @return
	 */

	public int getSplitBarWidth()
	{
		return this.splitbarwidth;
	}

	/**
	 *  get the left background color
	 * @return
	 */
	public Color getLeftBgColor()
	{
		return new Color(50,52,55);
	}

	/**
	 *  get the split background color
	 * @return
	 */
	public Color getSplitBgColor()
	{
		return new Color(30,30,30);
	}

	/**
	 * get the color of right panel 
	 * @return
	 */
	public Color getMainColor()
	{
		return new Color(55,55,55);
	}

	/**
	 * initial the UI
	 */
	public void setFrameUI()
	{
		Font font = new Font("Verdana", Font.PLAIN, 12);
		UIManager.put("Label.font", font);
		UIManager.put("Button.font", font);
		
		Color gray = new Color(240,240,240);
		Color black = new Color(0,0,0);
		
	}
	
	/**
	 *  get the split bar height
	 * @return
	 */
	public int getSplitBarHeight() {
		// TODO Auto-generated method stub
		return this.splitbarHeight;
	}
	
	/**
	 *  get the up menu height
	 * @param height
	 * @return
	 */
	public int getUpMenuHeight(int height ) {
		// TODO Auto-generated method stub
		return (int) ((height-splitbarHeight)*0.65) ;
	}
}
