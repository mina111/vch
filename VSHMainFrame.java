import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class VSHMainFrame extends JFrame{
	//软件主体panel
	MainPanel panel;
	boolean isFullScreen = true; // 是否全屏
	boolean lmenu_isopen = true ; // 左边二级菜单是否展开
	String img_dir = "skin_black";//皮肤文件夹
	Dimension fdm,sdm;//窗体大小:窗体|分辨率
	Config con;//配置类
	//Vector<MenuButtonBox> menulist = new Vector<MenuButtonBox>();//菜单数组\
	String reDoCommand ;
	public boolean stop = true;
	public boolean pause = false;
	public boolean start = false;
	int[] lock = new int[0];
	VSH vsh;
	//RepaintActionDo action;
	//====================
	//初始化
	VSHMainFrame(String title,VSH vsh)
	{
		
		super(title);
		this.vsh = vsh;
		//初始化窗体
		setWindow(this);
		//更新窗口
		updateWindow(this);
		
	}
	//===============================
	//窗体初始化
	//===============================
	private void setWindow(VSHMainFrame f)
	{
		//=============================
		//读取配置类
		con = new Config();
		con.setFrameUI();
	//	action = new RepaintActionDo(f);
		//启动时是否全屏
		isFullScreen = con.openfull;
		//初始化皮肤文件
		img_dir = con.getSkinDir();
		fdm = con.getFrameSmallSize(isFullScreen);
		sdm = con.getScreenSize();
		f.setSize(fdm);//设置窗体大小
		addMainPanel(this,fdm.width,fdm.height,isFullScreen);//添加主面板
		f.setLocation((sdm.width-f.getWidth())/2, (sdm.height-f.getHeight())/2);//位置
		f.setIconImage(Toolkit.getDefaultToolkit().getImage(VSH.class.getResource(img_dir + "/icon1.png")));//图标
		f.setBackground(Color.WHITE);//设置背景色
		
	}
	//===============================
	//更新窗口
	//===============================
	private void updateWindow(VSHMainFrame f)
	{
		f.setUndecorated(true);//设置没有边框
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//添加系统退出事件
		f.validate();//有效
		f.setVisible(true);//可见
		f.panel.m_panel.animationPanel.setLayout(null);
		f.panel.m_panel.flowchart.setBounds(new Rectangle((f.panel.m_panel.animationPanel.getWidth()-f.panel.m_panel.flowchart.label_img.getIconWidth())/2,(f.panel.m_panel.animationPanel.getHeight()-f.panel.m_panel.flowchart.label_img.getIconHeight())/2,f.panel.m_panel.flowchart.label_img.getIconWidth(),f.panel.m_panel.flowchart.label_img.getIconHeight()));
	}
	//================================
	//添加主面板
	//================================
	private void addMainPanel(final VSHMainFrame frame,int fwidth,int fheight,boolean iffull)
	{
		//=====================
		//创建面板
		panel = new MainPanel(frame,fwidth,fheight);
		panel.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
		this.getContentPane().add(panel);
		//=====================
		//关闭按钮事件
		panel.btn_close.setActionCommand("cmd_close");
		panel.btn_close.addActionListener(new Action(this,panel.btn_close));

		//=====================
		//最小化
		panel.btn_min.setActionCommand("cmd_min");
		panel.btn_min.addActionListener(new Action(this,panel.btn_min));
		//=====================
		//鼠标事件点击点
		final Point point = new Point();
		//=====================
		//顶部panel点击事件,单击双击
		//================
		//顶部Panel拖动事件
		panel.toppanel.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				if(!isFullScreen)
				{
					Point tpoint = getLocation();
					setLocation(tpoint.x + e.getX() - point.x, tpoint.y + e.getY() - point.y );
				}
			}
		});
		
		//顶部Panel拖动事件
		panel.bottompanel.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				if(!isFullScreen)
				{
					Point tpoint = getLocation();
					setLocation(tpoint.x + e.getX() - point.x, tpoint.y + e.getY() - point.y );
				}
			}
		});
	}

	//===============================
	//创建菜单函数,
	//===============================
	public JButton getMenuButton(String img,String rimg,String text,String flag)
	{
		String menudir ; //菜单图象文件夹
		if(flag.equals("mainmenu")) menudir = "mainmenu/";//主菜单
		else menudir = "submenu/"; //二级菜单
		ImageIcon btn_img = con.getImgUrl(menudir+img);
		JButton btn = new JButton(btn_img);
		btn.setPreferredSize(new Dimension(btn_img.getIconWidth(),btn_img.getIconHeight()));
		btn.setBorder(null);
		btn.setFocusCycleRoot(false);
		btn.setRolloverIcon(con.getImgUrl(menudir+rimg));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setToolTipText(text);
		return btn;//返回按钮
	}
	//===============================
	//返回菜单面板
	//===============================
	public JPanel getMenuPanel()
	{
		return this.panel.menupanel;
	}
	//===============================
	//返回二级菜单面板
	//===============================
	public JPanel getSubMenuPanel()
	{
		return this.panel.m_panel.p_left;
	}
	//===============================
	//设置主菜单,参数:主菜单按钮
	//===============================
	public void setMainMenu(JButton button)
	{
		//添加事件
		button.addActionListener(new Action(this,button));
		button.setActionCommand("lmenu_add");//设置命令
		getMenuPanel().add(button);
	}
	//===============================
	// 组织菜单对应数组,参数:主菜单按钮,添加的菜单按钮,初始命令
	//===============================
/*	public void addSubMenu(JButton mainbutton,JButton button,String comand)
	{
		//把子菜单添加到菜单向量中
		menulist.add(new MenuButtonBox(mainbutton,button,comand));
	}*/
	//===============================
	//设置默认菜单,参数:菜单按钮
	//===============================
/*	public void setDefaultMenu(JButton button)
	{
		getSubMenu(button);
	}
	//===============================
	//显示指定菜单的子菜单
	//===============================
	public void getSubMenu(JButton button)
	{
		//============================
		//显示小菜单
		//============================
		//清除所有
		JPanel submenupanel = this.getSubMenuPanel();
		submenupanel.removeAll();//删除原来的
		this.repaint();//重新绘画
		Vector<MenuButtonBox> vct = this.menulist;//菜单向量
		SubMenuActionDo saction = new SubMenuActionDo(this);
		//根据容器大小算
		for(int i=0;i<vct.size();i++)
		{
			//如果二级菜单属于当前操作按钮
			if(vct.elementAt(i).getMainbutton() == button)
			{
				JButton tbutton = vct.elementAt(i).getButton();
				tbutton.addActionListener(saction);
				tbutton.setActionCommand(vct.elementAt(i).getActioncommand());
				submenupanel.add(tbutton);
			}
		}
	}
	//==================================
	//返回右边框最小宽度
	//==================================
	public int getMinMainBoardWidth()
	{
		int w =this.panel.m_panel.scrollPane.getWidth() - 17;
		if(w<619) w = 619;
		return w;
	}*/
	public void stop() {
		// TODO Auto-generated method stub
		synchronized(lock){
			try {
				lock.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void play() {
		// TODO Auto-generated method stub
		synchronized(lock){
			lock.notifyAll();
		}
	}
}
