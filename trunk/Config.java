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
	//===========================
	//一些初始化参数
	//===========================
	String skin_dir , softtitle ;//软件皮肤 | 上面的标题文字
	int width,height;//初始窗体宽高
	int leftmenuwidth , splitbarwidth ; //主要区域,左二级菜单宽度|左右分格面板的宽度
	boolean iffulltop , ifnormaltop , openfull , lmenucanclose; //窗体最大时总前端显示|正常模式总前端显示|启动时左边菜单是否显示|左边是否可以关闭
	private int splitbarHeight;

	//===========================
	//初始化
	Config()
	{
		skin_dir = "icon";//软件皮肤文件夹
		width = 1024 ; //窗体宽
		height = 728 ; //窗体高
		iffulltop = true ; //全屏幕是否在最前端显示
		ifnormaltop = false ; //非全屏是否在最前端显示
		leftmenuwidth = 150 ; //左边二级菜单宽度
		splitbarwidth = 6 ; //分格面板宽度
		splitbarHeight = 6;
		openfull = false ; //软件启动时就全屏
		softtitle = "Visualisation Tool for a Selection Hyper-Heuristic (code.google.com/p/vch)";
		lmenucanclose = true; //是否可以关闭左边菜单栏
	}
	//===========================
	//返回皮肤文件夹
	public String getSkinDir()
	{
		return skin_dir;//返回皮肤文件夹
	}
	//=================================
	//重置窗体大小
	//=================================

	// ==================================
	// 返回包中指定图片函数
	// ==================================
	public ImageIcon getImgUrl(String img)
	{
		return new ImageIcon(VSHMainFrame.class.getResource(getSkinDir() + "/" + img));
	}
	// ==================================
	// 返回窗体最小尺寸
	// ==================================
	public Dimension getFrameSmallSize(boolean full)
	{
		if(!full)//不是最大化初始
		{
			return new Dimension(width,height);
		}
		else//全屏
		{
			Toolkit kit = Toolkit.getDefaultToolkit();
			return new Dimension(kit.getScreenSize());
		}
	}
	// ==================================
	// 返回分辨率
	// ==================================
	public Dimension getScreenSize()
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		return new Dimension(kit.getScreenSize());
	}
	// ==================================
	//  最大化时是否显示在前端,如考试系统不许打开其它东西
	// ==================================
	public void fullAllWaysTop(VSHMainFrame frame)
	{
		frame.setAlwaysOnTop(iffulltop);
	}
	// ==================================
	//  正常时是否显示在前端,如考试系统不许打开其它东西
	// ==================================
	public void normalAllWaysTop(VSHMainFrame frame)
	{
		frame.setAlwaysOnTop(ifnormaltop);
	}
	// ==================================
	//  返回布局对象,参数:0左,1中,2右
	// ==================================
	public FlowLayout getFlowLayout(int flag,int hgap,int vgap)
	{
		FlowLayout fl = new FlowLayout();
		fl.setHgap(hgap);
		fl.setVgap(vgap);
		fl.setAlignment(flag);
		return fl;
	}
	//===================================
	//返回左边二级菜单面板的宽度
	//===================================
	public int getLeftMenuWidth()
	{
		return this.leftmenuwidth;
	}
	//===================================
	//返回分格面板宽度
	//===================================
	public int getSplitBarWidth()
	{
		return this.splitbarwidth;
	}
	//===================================
	//返回左边菜单背景
	//===================================
	public Color getLeftBgColor()
	{
		return new Color(50,52,55);
	}
	//===================================
	//返回分格页面背景色
	//===================================
	public Color getSplitBgColor()
	{
		return new Color(30,30,30);
	}
	//===================================
	//返回右边区域色彩
	//===================================
	public Color getMainColor()
	{
		return new Color(55,55,55);
	}
	//===================================
	//初始化UI
	//===================================
	public void setFrameUI()
	{
		Font font = new Font("宋体", Font.PLAIN, 12);
		UIManager.put("Label.font", font);
		UIManager.put("Button.font", font);

		Color gray = new Color(240,240,240);
		Color black = new Color(0,0,0);

       /* UIManager.put("ScrollBar.track", black);
        UIManager.put("ScrollBar.trackHighlight",black);

        UIManager.put("ScrollBar.background", gray);
        UIManager.put("ScrollBar.shadow", gray);
        UIManager.put("ScrollBar.darkShadow", gray);

        UIManager.put("ScrollBar.thumb", gray);
        UIManager.put("ScrollBar.thumbShadow",gray);
        UIManager.put("ScrollBar.thumbDarkShadow",gray);
        UIManager.put("ScrollBar.thumbHighlight", new Color(250,250,250));
        //UIManager.put("ScrollBar.thumbLightShadow", new Color(250,60,250));
        UIManager.put("ScrollBar.width", 17);

        */

        /*
        ScrollBar.background
		ScrollBar.darkShadow
		ScrollBar.focusInputMap
		ScrollBar.foreground
		ScrollBar.highlight
		ScrollBar.maximumThumbSize
		ScrollBar.minimumThumbSize
		ScrollBar.shadow
		ScrollBar.thumb
		ScrollBar.thumbDarkShadow
		ScrollBar.thumbHighlight
		ScrollBar.thumbLightShadow
		ScrollBar.thumbShadow
		ScrollBar.track
		ScrollBar.trackHighlight
		ScrollBar.width
         */


	}
	public int getSplitBarHeight() {
		// TODO Auto-generated method stub
		return this.splitbarHeight;
	}
	public int getUpMenuHeight(int height ) {
		// TODO Auto-generated method stub
		return (int) ((height-splitbarHeight)*0.65) ;
	}
}
