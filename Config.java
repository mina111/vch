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

	public void resetFrameSize(VSHMainFrame f,int w,int h)
	{
		int tw,th,lw,rw; 
		f.setSize(w,h);
		f.panel.toppanel.setPreferredSize(new Dimension(w,f.panel.toppanel.getHeight()));
		f.panel.menupanel.setPreferredSize(new Dimension(w,f.panel.menupanel.getHeight()));
		tw = w-f.panel.btn_close_img.getIconWidth()-f.panel.btn_max_img.getIconWidth()-f.panel.btn_min_img.getIconWidth()-f.panel.img_t_bg_w-f.panel.img_t_mid_w;
		f.panel.toptitle.setPreferredSize(new Dimension(tw,f.panel.img_t_l.getIconHeight()));
		f.panel.infopanel.setPreferredSize(new Dimension(w,f.panel.img_info.getIconHeight()));
		tw = w - 2*f.panel.img_m_w;
		th = h - f.panel.img_t_l.getIconHeight() - f.panel.img_menu.getIconHeight() - f.panel.img_info.getIconHeight() - f.panel.img_b_l.getIconHeight();
		f.panel.m_panel.setPreferredSize(new Dimension(tw,th));
	
		lw = this.getLeftMenuWidth() ;
	
		
		
		f.panel.m_panel.p_left.setPreferredSize(new Dimension(lw,th));
		
		
		f.panel.m_panel.p_left.remove(f.panel.m_panel.p_left.upPanel);
		f.panel.m_panel.p_left.remove(f.panel.m_panel.p_left.barPanel);
		f.panel.m_panel.p_left.remove(f.panel.m_panel.p_left.downPanel);
		f.panel.m_panel.p_left.addPanel(lw,th);
		
		if(!f.lmenu_isopen) lw = 0; 
		rw = w - splitbarwidth - lw  ; 
		
		f.panel.m_panel.p_bar.setPreferredSize(new Dimension(splitbarwidth,th));
		f.panel.m_panel.p_bar.setLayout(this.getFlowLayout(1, 0, (th - f.panel.m_panel.btn_split_img.getIconHeight())/2-10)); 
		f.panel.m_panel.p_right.setPreferredSize(new Dimension(rw-8,th));

		

		f.panel.bottompanel.setPreferredSize(new Dimension(w,f.panel.img_b_l.getIconHeight()));
		
		f.validate();
	

		f.panel.m_panel.animationPanel.setLayout(null);
		f.panel.m_panel.animationPanel.removeAll();
		f.panel.m_panel.flowchart.setBounds(new Rectangle((f.panel.m_panel.animationPanel.getWidth()-f.panel.m_panel.flowchart.label_img.getIconWidth())/2,(f.panel.m_panel.animationPanel.getHeight()-f.panel.m_panel.flowchart.label_img.getIconHeight())/2,f.panel.m_panel.flowchart.label_img.getIconWidth(),f.panel.m_panel.flowchart.label_img.getIconHeight()));
		f.panel.m_panel.animationPanel.add(f.panel.m_panel.flowchart);
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
