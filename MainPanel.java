import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



class MainPanel extends JPanel 
{

	ImageIcon img_t_l , img_t_bg , img_t_mid , img_t_rbg ,img_t_r;
	int img_t_l_w , img_t_bg_w , img_t_mid_w , img_t_rbg_w , img_t_r_w;
	

	ImageIcon img_m;
	int img_m_w;
	

	ImageIcon img_b_l , img_b_bg , img_b_r ;
	int img_b_l_w , img_b_bg_w , img_b_r_w;
	

	ImageIcon img_menu , img_info;
	

	int width,height;

	Config con = new Config();
	
	
	CustomizeExhibitionPanel m_panel;
	

	JPanel toppanel;
	JLabel toptitle;
	
	ImageIcon btn_min_img,btn_max_img,btn_close_img;//
	JButton btn_min,btn_max,btn_close;

	JPanel menupanel;

	MyButton play, pause, stop, forward, info,help;

	JPanel infopanel;
	MyLabel first;
	MyLabel benchmarkFunctionInfo,heuristicSelectionInfo, lowLevelHeuristicsInfo, acceptanceMethodInfo, launchDemonstrationInfo;
	MyButton benchmarkFunction, heuristicSelection, lowLevelHeuristics, acceptanceMethod, launchDemonstration;

	JPanel bottompanel ; 
	ImageIcon bottom_resize_img;
	JButton bottom_resize;
	
	
	VSHMainFrame frame;

	MainPanel(VSHMainFrame frame,int w,int h) 
	{

		this.width = w;
		this.height = h;
		this.frame = frame;
		this.setOpaque(true);
		initSkinImg(); 
		this.setLayout(con.getFlowLayout(1,0,0));
		
		setTop();
	
		setMenu();

		setInfoPanel();

		setMiddlePanel();

		setBottomPanel();
	}

	public void setTop()
	{

		toppanel = new JPanel();
		toppanel.setPreferredSize(new Dimension(width,img_t_l.getIconHeight()));
		toppanel.setOpaque(false);
		toppanel.setLayout(con.getFlowLayout(2,0,0)); 
		toppanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		this.add(toppanel);

		toptitle = new JLabel();

		int w = width-btn_close_img.getIconWidth()-btn_max_img.getIconWidth()-btn_min_img.getIconWidth()-img_t_l_w-img_t_bg_w-img_t_mid_w;
		toptitle.setPreferredSize(new Dimension(w,img_t_l.getIconHeight()));
		toptitle.setForeground(Color.white);
		toptitle.setFont(new Font("Arial",0,12));
		toptitle.setText(con.softtitle);
		final String  uri = "code.google.com/p/vch";  
		toptitle.addMouseListener(new MouseAdapter() {   
			public void mouseClicked(MouseEvent e) {  
				try{  
					  Runtime.getRuntime().exec("C:\\Program   Files\\Internet   Explorer\\IEXPLORE.EXE   "+uri);  
					  }  
					  catch(Exception   ee)  
					  {  
					  ee.printStackTrace();  
					  }   
			}  
		});
		toptitle.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
		toppanel.add(toptitle);

		btn_min = new JButton(btn_min_img);
		btn_min.setPreferredSize(new Dimension(btn_min_img.getIconWidth(),btn_min_img.getIconHeight()));
		btn_min.setBorder(null);
		btn_min.setFocusCycleRoot(false);
		btn_min.setRolloverIcon(con.getImgUrl("min_on.png"));
		btn_min.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_min.setToolTipText("最小化模式");
		toppanel.add(btn_min);
	
		btn_max = new JButton(btn_max_img);
		btn_max.setPreferredSize(new Dimension(btn_max_img.getIconWidth(),btn_max_img.getIconHeight()));
		btn_max.setBorder(null);
		btn_max.setFocusCycleRoot(false);
		btn_max.setRolloverIcon(con.getImgUrl("max_on.png"));
		btn_max.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_max.setToolTipText("全屏模式");
		toppanel.add(btn_max);

		btn_close = new JButton(btn_close_img);
		btn_close.setPreferredSize(new Dimension(btn_close_img.getIconWidth(),btn_close_img.getIconHeight()));
		btn_close.setBorder(null);
		btn_close.setFocusCycleRoot(false);
		btn_close.setRolloverIcon(con.getImgUrl("close_on.png"));
		btn_close.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_close.setToolTipText("关闭窗口");
		toppanel.add(btn_close);
	}

	public void setMenu()
	{

		menupanel = new JPanel();
		menupanel.setPreferredSize(new Dimension(width,img_menu.getIconHeight()));
		menupanel.setOpaque(false);
		menupanel.setLayout(con.getFlowLayout(0,0,0)); // 左对齐
		play = new MyButton(con,"Play_on.png","Play_Pressed.png","Play");
		pause = new MyButton(con,"Pause_on.png","Pause_pressed.png","Pause");
		stop = new MyButton(con,"Stop_on.png","Stop_pressed.png","Stop");
		forward = new MyButton(con,"Forward_on.png","Forward_pressed.png","Forward");
		info = new MyButton(con,"Info_on.png","Info_pressed.png","About");
		help = new MyButton(con,"Help_on.png","Help_pressed.png","Help");
		menupanel.add(play);
		menupanel.add(pause);
		menupanel.add(stop);
		menupanel.add(forward);
		menupanel.add(info);
		menupanel.add(help);
		this.add(menupanel);
	}

	public void setInfoPanel()
	{
		infopanel = new JPanel();
		infopanel.setPreferredSize(new Dimension(width,img_info.getIconHeight()));
		infopanel.setOpaque(false);
		infopanel.setLayout(con.getFlowLayout(0,0,0)); 
		
		this.add(infopanel);
		

	}

	public void setMiddlePanel()
	{
		int w = width - 2*this.img_m_w;

		int h = height - this.img_t_l.getIconHeight() - this.img_menu.getIconHeight() - this.img_info.getIconHeight() - this.img_b_l.getIconHeight();
		m_panel = new CustomizeExhibitionPanel(frame,w,h);
		this.add(m_panel);
	}

	public void setBottomPanel()
	{
		bottompanel = new JPanel();
		bottompanel.setPreferredSize(new Dimension(width,this.img_b_l.getIconHeight()));
		bottompanel.setLayout(con.getFlowLayout(2,0,0));
		bottompanel.setOpaque(false);
		this.add(bottompanel);

		bottom_resize_img = con.getImgUrl("bottom_resize.png"); 
		bottom_resize = new JButton(bottom_resize_img);
		bottom_resize.setPreferredSize(new Dimension(bottom_resize_img.getIconWidth(),bottom_resize_img.getIconHeight()));
		bottom_resize.setBorder(null);
		bottom_resize.setFocusCycleRoot(false);
		bottom_resize.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
		bottompanel.add(bottom_resize);
	}

	private void initSkinImg()
	{

		img_t_l = con.getImgUrl("exam_h_l.png");
		img_t_l_w = img_t_l.getIconWidth();
		img_t_bg = con.getImgUrl("exam_h_lc.png");
		img_t_bg_w = 120;
		img_t_mid = con.getImgUrl("exam_h_tag.png");
		img_t_mid_w = img_t_mid.getIconWidth();
		img_t_rbg = con.getImgUrl("exam_h_rc.png");
		img_t_rbg_w = img_t_rbg.getIconWidth();
		img_t_r = con.getImgUrl("exam_h_r.png");
		img_t_r_w = img_t_r.getIconWidth();

		btn_min_img= con.getImgUrl("min.png");
		btn_max_img= con.getImgUrl("max.png");
		btn_close_img= con.getImgUrl("close.png");
		
		
	
		img_menu = con.getImgUrl("menu_bg.png");
		img_info = con.getImgUrl("info_bg.png");
		
	
		img_m = con.getImgUrl("exam_b_line.png");
		img_m_w = img_m.getIconWidth();

		img_b_l = con.getImgUrl("exam_b_l.png");
		img_b_l_w = img_b_l.getIconWidth();
		img_b_bg = con.getImgUrl("exam_b_bg.png");
		img_b_bg_w = img_b_bg.getIconWidth();
		img_b_r = con.getImgUrl("exam_b_r.png");
		img_b_r_w = img_b_r.getIconWidth();
	}

	public void paintComponent(Graphics g) 
	{
	
		g.drawImage(img_t_l.getImage(), 0, 0, this);

		g.drawImage(img_t_bg.getImage(), 0, 0, img_t_bg_w, img_t_bg.getIconHeight(), this);
		
		g.drawImage(con.getImgUrl("icon.png").getImage(), 5, 3, 16,16, this);
		
		g.setColor(Color.white);
		g.drawString("VSH", 30, 15);
		g.setFont(new Font("Verdana", 0, 12));
		
		g.drawImage(img_t_mid.getImage(), img_t_bg_w, 0, img_t_mid_w, img_t_bg.getIconHeight(), this);
	
		int spot = img_t_bg_w + img_t_mid_w;
		int epot = this.getWidth() - img_t_r_w;
		g.drawImage(img_t_rbg.getImage(), spot, 0, epot, img_t_rbg.getIconHeight(), this);
		
		g.drawImage(img_t_r.getImage(), epot, 0, this);
		
		
		g.drawImage(img_menu.getImage(),0,img_t_l.getIconHeight(),this.getWidth(),img_menu.getIconHeight(),this);
		
		g.drawImage(img_info.getImage(),0,img_t_l.getIconHeight()+img_menu.getIconHeight(),this.getWidth(),img_info.getIconHeight(),this);
		
		
		g.drawImage(img_m.getImage(), 0, img_t_l.getIconHeight()+img_menu.getIconHeight()+img_info.getIconHeight(), img_m_w, this.getHeight()- img_t_l.getIconHeight(), this);
		g.drawImage(img_m.getImage(), this.getWidth() - img_m.getIconWidth(),img_t_l.getIconHeight()+img_menu.getIconHeight()+img_info.getIconHeight(), img_m_w, this.getHeight()- img_t_l.getIconHeight(), this);

	
		g.drawImage(img_b_l.getImage(), 0, this.getHeight()- img_b_l.getIconHeight(), this);
		g.drawImage(img_b_bg.getImage(), img_b_l_w, this.getHeight()- img_b_bg.getIconHeight(), this.getWidth()- img_b_r.getIconWidth(), img_b_r.getIconHeight(), this);
		g.drawImage(img_b_r.getImage(), this.getWidth()- img_b_r.getIconWidth(), this.getHeight()- img_b_r.getIconHeight(), this);		
	}
}

