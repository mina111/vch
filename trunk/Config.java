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



public class Config
{

	String skin_dir , softtitle ;
	int width,height;
	int leftmenuwidth , splitbarwidth ; 
	boolean iffulltop , ifnormaltop , openfull , lmenucanclose; 
	private int splitbarHeight;
	

	Config()
	{
		skin_dir = "icon";
		width = 800 ;
		height = 600 ; 
		iffulltop = true ; 
		ifnormaltop = false ; 
		leftmenuwidth = 150 ; 
		splitbarwidth = 6 ; 
		splitbarHeight = 6;
		openfull = false ; 
		softtitle = "A Visualisation Tool for a Choice Hyper-Heuristic---CODE.GOOGLE.COM/P/VCH";
		lmenucanclose = true; 
	}

	public String getSkinDir()
	{
		return skin_dir;
	}


	public ImageIcon getImgUrl(String img) 
	{
		return new ImageIcon(VSHMainFrame.class.getResource(getSkinDir() + "/" + img));
	}

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

	public Dimension getScreenSize()
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		return new Dimension(kit.getScreenSize());
	}

	public void fullAllWaysTop(VSHMainFrame frame)
	{
		frame.setAlwaysOnTop(iffulltop);
	}

	public void normalAllWaysTop(VSHMainFrame frame)
	{
		frame.setAlwaysOnTop(ifnormaltop);
	}

	public FlowLayout getFlowLayout(int flag,int hgap,int vgap)
	{
		FlowLayout fl = new FlowLayout();
		fl.setHgap(hgap);
		fl.setVgap(vgap);
		fl.setAlignment(flag);
		return fl;
	}

	public int getLeftMenuWidth()
	{
		return this.leftmenuwidth;
	}

	public int getSplitBarWidth()
	{
		return this.splitbarwidth;
	}

	public Color getLeftBgColor()
	{
		return new Color(50,52,55);
	}

	public Color getSplitBgColor()
	{
		return new Color(30,30,30);
	}

	public Color getMainColor()
	{
		return new Color(55,55,55);
	}

	public void setFrameUI()
	{
		Font font = new Font("ËÎÌו", Font.PLAIN, 12);
		UIManager.put("Label.font", font);
		UIManager.put("Button.font", font);
		
		Color gray = new Color(240,240,240);
		Color black = new Color(0,0,0);
		

        

	}
	public int getSplitBarHeight() {
		
		return this.splitbarHeight;
	}
	public int getUpMenuHeight(int height ) {
		// TODO Auto-generated method stub
		return (int) ((height-splitbarHeight)*0.65) ;
	}
}
