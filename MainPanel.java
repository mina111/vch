import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.net.URI;
import java.awt.Desktop;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



class MainPanel extends JPanel
{
	//======================
	// 上边框图片及宽度:左边框|左半背景|中间过度|右边背景| 右边边框
	ImageIcon img_t_l , img_t_bg , img_t_mid , img_t_rbg ,img_t_r;
	int img_t_l_w , img_t_bg_w , img_t_mid_w , img_t_rbg_w , img_t_r_w;

	// =====================
	// 左右边线
	ImageIcon img_m;
	int img_m_w;

	// =====================
	// 画尾部:左边角|背景|右边角
	ImageIcon img_b_l , img_b_bg , img_b_r ;
	int img_b_l_w , img_b_bg_w , img_b_r_w;

	//======================
	//菜单及信息区背景图片:菜单背景图片|菜单下面信息区背景
	ImageIcon img_menu , img_info;

	// =====================
	//宽高
	int width,height;

	//======================
	//用到的类
	//======================
	Config con = new Config();

	//======================
	//中间主要面板
	//======================
	CustomizeExhibitionPanel m_panel;

	//======================
	//主要面板
	//======================
	JPanel toppanel;//第一行
	JLabel toptitle;//标题文字区
	//要用到的按钮图片:最小化图片|最大化图片|关闭图片,下面是对应的按钮
	ImageIcon btn_min_img,btn_close_img;//
	JButton btn_min,btn_close;//要用到的按钮

	//======================
	//菜单面板
	//======================
	JPanel menupanel;

	MyButton play, pause, stop, speedUp, slowDown, info, help;
	//======================
	//信息面板
	//======================
	JPanel infopanel;
	MyLabel first;
	MyLabel benchmarkFunctionInfo,heuristicSelectionInfo, lowLevelHeuristicsInfo, acceptanceMethodInfo, launchDemonstrationInfo;
	MyButton benchmarkFunction, heuristicSelection, lowLevelHeuristics, acceptanceMethod, launchDemonstration;
	//======================
	//底部面板
	//======================
	JPanel bottompanel ;
	ImageIcon bottom_resize_img;
	//JButton bottom_resize;

	//======================
	//  属于的窗体
	VSHMainFrame frame;
	//======================
	//初始化,参数:宽高
	MainPanel(VSHMainFrame frame,int w,int h)
	{
		//初始化宽高
		this.width = w;
		this.height = h;
		this.frame = frame;
		this.setOpaque(true);
		initSkinImg(); // 初始化皮肤信息
		this.setLayout(con.getFlowLayout(1,0,0)); //居中对齐
		//=============================
		//初始化按钮
		setTop();
		//=============================
		//初始化菜单
		setMenu();
		//初始化菜单下面的信息条
		setInfoPanel();
		//=============================
		//添加主要面板
		setMiddlePanel();
		//=============================
		//添加底部面板
		setBottomPanel();

	}
	//===================================
	//   为软件添加大小关闭及扩展按钮
	//===================================
	public void setTop()
	{
		//================================================
		//添加一最上面的一个panel,及最大最小化按钮
		//================================================
		toppanel = new JPanel();
		toppanel.setPreferredSize(new Dimension(width,img_t_l.getIconHeight()));
		toppanel.setOpaque(false);
		toppanel.setLayout(con.getFlowLayout(2,0,0)); // 右对齐
		toppanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		this.add(toppanel);
		//=================================
		//添加上面文件显示部分
		toptitle = new JLabel();
		//还有的宽度计算出来
		int w = width-btn_close_img.getIconWidth()-btn_min_img.getIconWidth()-img_t_l_w-img_t_bg_w-img_t_mid_w;
		toptitle.setPreferredSize(new Dimension(w,img_t_l.getIconHeight()));
		toptitle.setForeground(Color.white);
		toptitle.setFont(new Font("Arial",0,12));
		toptitle.setText(con.softtitle);
		final String  uri = "http://code.google.com/p/vch";
		toptitle.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				// When someone clicks on the top-bar link:

				// Check that we have access to the desktop.
				if( java.awt.Desktop.isDesktopSupported() ) {

					java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

					// Check that we are allowed to browse.
					if( desktop.isSupported( java.awt.Desktop.Action.BROWSE ) ) {

						try {

							// Construct a new URI using the string.
							java.net.URI link = new java.net.URI( uri );
							// Then call the desktop's default browser to open the URI.
							desktop.browse( link );

						} catch ( Exception be ) {

							System.err.println( be.getMessage() );

						} // END try/catch

					} // END if

				} // END if

			} // END mouseclicked

		});
		toptitle.setCursor(new Cursor(Cursor.HAND_CURSOR));
		toppanel.add(toptitle);
		//=================================
		//最小化按钮;
		btn_min = new JButton(btn_min_img);
		btn_min.setPreferredSize(new Dimension(btn_min_img.getIconWidth(),btn_min_img.getIconHeight()));
		btn_min.setBorder(null);
		btn_min.setFocusCycleRoot(false);
		btn_min.setRolloverIcon(con.getImgUrl("min_on.png"));
		btn_min.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_min.setToolTipText("最小化模式");
		toppanel.add(btn_min);
		//=================================
		//最大化按钮
		//=================================
		//关闭窗口按钮
		btn_close = new JButton(btn_close_img);
		btn_close.setPreferredSize(new Dimension(btn_close_img.getIconWidth(),btn_close_img.getIconHeight()));
		btn_close.setBorder(null);
		btn_close.setFocusCycleRoot(false);
		btn_close.setRolloverIcon(con.getImgUrl("close_on.png"));
		btn_close.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_close.setToolTipText("关闭窗口");
		toppanel.add(btn_close);
	}
	//===================================
	//   初始化菜单面板
	//===================================
	public void setMenu()
	{
		//================================================
		//新建菜单面板并添加
		//================================================
		menupanel = new JPanel();
		menupanel.setPreferredSize(new Dimension(width,img_menu.getIconHeight()));
		menupanel.setOpaque(false);
		menupanel.setLayout(con.getFlowLayout(0,0,0)); // 左对齐
		play = new MyButton(con,"menu-button-play.png","menu-button-play-hover.png","Play");
		pause = new MyButton(con,"menu-button-pause.png","menu-button-pause-hover.png","Pause");
		slowDown = new MyButton(con,"menu-button-slowdown.png","menu-button-slowdown-hover.png","Slow Down");
		speedUp = new MyButton(con,"menu-button-speedup.png","menu-button-speedup-hover.png","Speed Up");
		stop = new MyButton(con,"menu-button-stop.png","menu-button-stop-hover.png","Stop");
		info = new MyButton(con,"menu-button-info.png","menu-button-info-hover.png","About");
		help = new MyButton(con,"menu-button-help.png","menu-button-help-hover.png","Help");
		play.setActionCommand("play");
		play.addActionListener(new Action(frame,play));
		pause.setActionCommand("pause");
		pause.addActionListener(new Action(frame,pause));
		slowDown.setActionCommand("slow down");
		slowDown.addActionListener(new Action(frame,slowDown));
		speedUp.setActionCommand("speed up");
		speedUp.addActionListener(new Action(frame,speedUp));
		stop.setActionCommand("stop");
		stop.addActionListener(new Action(frame,stop));
		info.setActionCommand("info");
		info.addActionListener(new Action(frame,info));
		help.setActionCommand("help");
		help.addActionListener(new Action(frame,help));
		menupanel.add(play);
		menupanel.add(pause);
		menupanel.add(slowDown);
		menupanel.add(speedUp);
		menupanel.add(stop);
		menupanel.add(info);
		menupanel.add(help);
		this.add(menupanel);
	}
	//===================================
	//添加菜单
	//===================================
	/*public void addMenu(JButton button)
	{
		menupanel.add(button);
	}*/
	//===================================
	//菜单下面的信息条
	//===================================
	public void setInfoPanel()
	{
		infopanel = new JPanel();
		infopanel.setPreferredSize(new Dimension(width,img_info.getIconHeight()));
		infopanel.setOpaque(false);
		infopanel.setLayout(con.getFlowLayout(0,0,0)); //左对齐

	//	first = new MyLabel(con,"first_bg.png");
	//	benchmarkFunction = new MyButton(con,"benchmark_function_on.png","benchmark_function_pressed.png","Benchmark Function");
	//	benchmarkFunctionInfo = new MyLabel("Benchmark Function: ",con);

	//	infopanel.add(first);
	//	infopanel.add(benchmarkFunction);
	//	infopanel.add(benchmarkFunctionInfo);
	//	launchDemonstration;

		this.add(infopanel);

		//=====================
		//下面可以添加相关组件
	}
	//===================================
	//添加主要面板
	//===================================
	public void setMiddlePanel()
	{
		int w = width - 2*this.img_m_w;
		//得到剩余高度
		int h = height - this.img_t_l.getIconHeight() - this.img_menu.getIconHeight() - this.img_info.getIconHeight() - this.img_b_l.getIconHeight();
		m_panel = new CustomizeExhibitionPanel(frame,w,h);
		this.add(m_panel);
	}
	//===================================
	//  底部面板
	//===================================
	public void setBottomPanel()
	{
		bottompanel = new JPanel();
		bottompanel.setPreferredSize(new Dimension(width,this.img_b_l.getIconHeight()));
		bottompanel.setLayout(con.getFlowLayout(2,0,0));//右对齐
		bottompanel.setOpaque(false);
		bottompanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		this.add(bottompanel);
		//=====================
		//添加右下角可以扩大缩小的图标
	}
	//===================================
	//初始化皮肤图片函数
	//===================================
	private void initSkinImg()
	{
		//========================================
		//软件头对应变量
		//========================================
		img_t_l = con.getImgUrl("exam_h_l.png");// 左角
		img_t_l_w = img_t_l.getIconWidth();
		img_t_bg = con.getImgUrl("exam_h_lc.png");// 左边背景
		img_t_bg_w = 120;
		img_t_mid = con.getImgUrl("exam_h_tag.png");// 过度图片
		img_t_mid_w = img_t_mid.getIconWidth();
		img_t_rbg = con.getImgUrl("exam_h_rc.png");// 右边背景
		img_t_rbg_w = img_t_rbg.getIconWidth();
		img_t_r = con.getImgUrl("exam_h_r.png");// 右边边框
		img_t_r_w = img_t_r.getIconWidth();
		//============
		//按钮类
		btn_min_img= con.getImgUrl("min.png");
		btn_close_img= con.getImgUrl("close.png");


		//========================================
		//菜单栏
		//========================================
		img_menu = con.getImgUrl("menu_bg.png");//菜单背景
		img_info = con.getImgUrl("info_bg.png");//信息背景

		//========================================
		// 左右边线
		//========================================
		img_m = con.getImgUrl("exam_b_line.png");//左线
		img_m_w = img_m.getIconWidth();//右线

		//========================================
		// 尾部
		//========================================
		img_b_l = con.getImgUrl("exam_b_l.png");//左边角
		img_b_l_w = img_b_l.getIconWidth();
		img_b_bg = con.getImgUrl("exam_b_bg.png");// 中间
		img_b_bg_w = img_b_bg.getIconWidth();
		img_b_r = con.getImgUrl("exam_b_r.png");//右边角
		img_b_r_w = img_b_r.getIconWidth();
	}
	// ==================================
	// 画软件皮肤函数
	// ==================================
	public void paintComponent(Graphics g)
	{
		//===========================================
		//开始画皮肤
		//===========================================

		//==========================
		// 画左边头
		g.drawImage(img_t_l.getImage(), 0, 0, this);
		// 画左边背景
		g.drawImage(img_t_bg.getImage(), 0, 0, img_t_bg_w, img_t_bg.getIconHeight(), this);
		// 软件图标
		g.drawImage(con.getImgUrl("icon1.png").getImage(), 20, 3, 34,16, this);
		// 打上文字
		g.setColor(Color.white);
		//g.drawString("VSH", 40, 15);
		g.setFont(new Font("Verdana", 0, 12));
		// 画过度条,因为左边背景给的是直接数字,所以这里不需要把左边边框宽度加上
		g.drawImage(img_t_mid.getImage(), img_t_bg_w, 0, img_t_mid_w, img_t_bg.getIconHeight(), this);
		// 右边背景
		// 起始点
		int spot = img_t_bg_w + img_t_mid_w;
		int epot = this.getWidth() - img_t_r_w;
		g.drawImage(img_t_rbg.getImage(), spot, 0, epot, img_t_rbg.getIconHeight(), this);
		// 画右边,使用上面那图象的终点
		g.drawImage(img_t_r.getImage(), epot, 0, this);

		//=============================
		//画菜单背景
		g.drawImage(img_menu.getImage(),0,img_t_l.getIconHeight(),this.getWidth(),img_menu.getIconHeight(),this);
		//画信息区
		g.drawImage(img_info.getImage(),0,img_t_l.getIconHeight()+img_menu.getIconHeight(),this.getWidth(),img_info.getIconHeight(),this);

		// ============================
		// 画左右边线
		g.drawImage(img_m.getImage(), 0, img_t_l.getIconHeight()+img_menu.getIconHeight()+img_info.getIconHeight(), img_m_w, this.getHeight()- img_t_l.getIconHeight(), this);
		g.drawImage(img_m.getImage(), this.getWidth() - img_m.getIconWidth(),img_t_l.getIconHeight()+img_menu.getIconHeight()+img_info.getIconHeight(), img_m_w, this.getHeight()- img_t_l.getIconHeight(), this);

		// ============================
		// 尾部
		g.drawImage(img_b_l.getImage(), 0, this.getHeight()- img_b_l.getIconHeight(), this);
		g.drawImage(img_b_bg.getImage(), img_b_l_w, this.getHeight()- img_b_bg.getIconHeight(), this.getWidth()- img_b_r.getIconWidth(), img_b_r.getIconHeight(), this);
		g.drawImage(img_b_r.getImage(), this.getWidth()- img_b_r.getIconWidth(), this.getHeight()- img_b_r.getIconHeight(), this);
	}
}