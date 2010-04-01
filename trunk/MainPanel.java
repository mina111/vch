
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
 *  main panel 
 */

class MainPanel extends JPanel 
{

	
	/**
	 *  left border image 
	 */
	ImageIcon img_t_l ;
	/**
	 *  the image for left part of title bar panel
	 */
	ImageIcon img_t_bg ;
	/**
	 *  the image for middle  part of title bar panel
	 */	
	ImageIcon img_t_mid ;
	/**
	 * the image for right  part of title bar panel
	 */
	ImageIcon img_t_rbg ;
	/**
	 *  right border image
	 */
	ImageIcon img_t_r;
	/**
	 *  left border width
	 */
	int img_t_l_w ;
	/**
	 *  left part of title bar panel width
	 */
	int img_t_bg_w ;
	/**
	 *  the middle part of title bar panel width
	 */
	int img_t_mid_w ;
	/**
	 * the right part of title bar panel width
	 */
	int img_t_rbg_w ;
	/**
	 * right border width
	 */
	int img_t_r_w;
	
	/**
	 *  the sideline image
	 */
	ImageIcon img_m;
	/**
	 *  the sideline witdh
	 */
	int img_m_w;
	

	/**
	 *  the image for the left corner of bottom panel
	 */
	ImageIcon img_b_l;
	/**
	 *  the image for the background of bottom panel
	 */	
	ImageIcon img_b_bg ;
	/**
	 * the image for the right corner of bottom panel
	 */
	ImageIcon img_b_r ;
	/**
	 * width of the left corner of bottom panel 
	 */
	int img_b_l_w ;
	/**
	 * width of the  background of bottom panel 
	 */
	int img_b_bg_w ;
	/**
	 * width of the right corner of bottom panel 
	 */
	int img_b_r_w;
	
	/**
	 *  image for menu background
	 */
	ImageIcon img_menu;
	/**
	 *  image for the panel behind the menu panel
	 */
	ImageIcon img_info;
	
	/**
	 *  width
	 */
	int width;
	/**
	 *  height
	 */
	int height;
	
	/**
	 *  config instance
	 */
	Config con = new Config();
	
	/**
	 *  the middle panel
	 */
	CustomizeExhibitionPanel m_panel;
	
	/**
	 *  top panel
	 */
	JPanel toppanel;
	/**
	 *  the label for title
	 */
	JLabel toptitle;
	/**
	 *  image for minimum button
	 */
	ImageIcon btn_min_img;
	/**
	 *  image for close button
	 */
	ImageIcon btn_close_img;
	/**
	 *   minimum button
	 */
	JButton btn_min;
	/**
	 *  close button 
	 */
	JButton btn_close;

	/**
	 *  menu panel
	 */
	JPanel menupanel;
	
	/**
	 *  play button
	 */
	MyButton play;
	/**
	 *  pause button
	 */	
	MyButton pause;
	/**
	 *  stop button
	 */
	MyButton stop;
	/**
	 * speed up button
	 */
	MyButton speedUp;
	/**
	 *  slow down button
	 */
	MyButton slowDown;
	/**
	 *  info button
	 */
	MyButton info;
	/**
	 *  help button
	 */
	MyButton help;
	
	/**
	 *  info button
	 */
	JPanel infopanel;
	/**
	 *   show the current benchmark function
	 */
	MyLabel benchmarkFunctionInfo;
	/**
	 *  show the current heuristic selection
	 */
	MyLabel heuristicSelectionInfo;
	/**
	 *  show the current low-level heuristics
	 */
	MyLabel lowLevelHeuristicsInfo;
	/**
	 *  show acceptance method
	 */
	MyLabel acceptanceMethodInfo;
	/**
	 *  get  benchmark function sub-menu
	 */
	MyButton benchmarkFunction;
	/**
	 *  get  heuristic selection sub-menu
	 */	
	MyButton heuristicSelection;
	/**
	 * get  low-level heuristics sub-menu
	 */
	MyButton lowLevelHeuristics;
	/**
	 *  get  acceptance method sub-menu
	 */
	MyButton acceptanceMethod;

    /**
     *  the bottom panel
     */
	JPanel bottompanel ;
	/**
	 *  the main frame
	 */
	VSHMainFrame frame;
	
	/**
	 * Initial the main panel
	 * @param frame
	 * @param w
	 * @param h
	 */
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
	/**
	 *  add the title bar to the main panel
	 */
	public void setTop()
	{
		toppanel = new JPanel();
		toppanel.setPreferredSize(new Dimension(width,img_t_l.getIconHeight()));
		toppanel.setOpaque(false);
		toppanel.setLayout(con.getFlowLayout(2,0,0)); 
		toppanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		this.add(toppanel);
		toptitle = new JLabel();
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
			
							// Construct a new URI using the string, and then call the desktop's default browser.
			                java.net.URI link = new java.net.URI( uri );
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
		btn_min = new JButton(btn_min_img);
		btn_min.setPreferredSize(new Dimension(btn_min_img.getIconWidth(),btn_min_img.getIconHeight()));
		btn_min.setBorder(null);
		btn_min.setFocusCycleRoot(false);
		btn_min.setRolloverIcon(con.getImgUrl("min_on.png"));
		btn_min.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_min.setToolTipText("Minimise");
		toppanel.add(btn_min);
		btn_close = new JButton(btn_close_img);
		btn_close.setPreferredSize(new Dimension(btn_close_img.getIconWidth(),btn_close_img.getIconHeight()));
		btn_close.setBorder(null);
		btn_close.setFocusCycleRoot(false);
		btn_close.setRolloverIcon(con.getImgUrl("close_on.png"));
		btn_close.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_close.setToolTipText("Close");
		toppanel.add(btn_close);
	}
	
	
	/**
	 *  Initial the menu
	 */
	public void setMenu()
	{
		menupanel = new JPanel();
		menupanel.setPreferredSize(new Dimension(width,img_menu.getIconHeight()));
		menupanel.setOpaque(false);
		menupanel.setLayout(con.getFlowLayout(0,0,0)); // ×ó¶ÔÆë
		play = new MyButton(con,"menu-button-play.png","menu-button-play-hover.png","Play");
		pause = new MyButton(con,"menu-button-pause.png","menu-button-pause-hover.png","Pause");
		slowDown = new MyButton(con,"menu-button-slowdown.png","menu-button-slowdown-hover.png","Slow Down");
		speedUp = new MyButton(con,"menu-button-speedup.png","menu-button-speedup-hover.png","Speed Up");
		stop = new MyButton(con,"menu-button-stop.png","menu-button-stop-hover.png","Stop & Settings");
		info = new MyButton(con,"menu-button-info.png","menu-button-info-hover.png","About");
		help = new MyButton(con,"menu-button-help.png","menu-button-help-hover.png","Help");
		play.setActionCommand("play");
		play.addActionListener(new Action(frame,play));
		pause.setActionCommand("pause");
		pause.addActionListener(new Action(frame,pause));
		stop.setActionCommand("stop");
		stop.addActionListener(new Action(frame,stop));
		speedUp.setActionCommand("speed up");
		speedUp.addActionListener(new Action(frame,speedUp));
		slowDown.setActionCommand("slow down");
		slowDown.addActionListener(new Action(frame,slowDown));		
		info.setActionCommand("info");
		info.addActionListener(new Action(frame,info));
		help.setActionCommand("help");
		help.addActionListener(new Action(frame,help));	
		menupanel.add(play);
		menupanel.add(pause);
		menupanel.add(speedUp);
		menupanel.add(slowDown);
		menupanel.add(stop);
		menupanel.add(info);
		menupanel.add(help);
		this.add(menupanel);
	}

	/**
	 *  add the split panel
	 */
	public void setInfoPanel()
	{
		infopanel = new JPanel();
		infopanel.setPreferredSize(new Dimension(width,img_info.getIconHeight()));
		infopanel.setOpaque(false);
		infopanel.setLayout(con.getFlowLayout(0,0,0)); 	
		this.add(infopanel);

	}
	
	/**
	 * add middle panel
	 */
	public void setMiddlePanel()
	{
		int w = width - 2*this.img_m_w;
		int h = height - this.img_t_l.getIconHeight() - this.img_menu.getIconHeight() - this.img_info.getIconHeight() - this.img_b_l.getIconHeight();
		m_panel = new CustomizeExhibitionPanel(frame,w,h);
		this.add(m_panel);
	}

	/**
	 * add bottom panel
	 */
	public void setBottomPanel()
	{
		bottompanel = new JPanel();
		bottompanel.setPreferredSize(new Dimension(width,this.img_b_l.getIconHeight()));
		bottompanel.setLayout(con.getFlowLayout(2,0,0));
		bottompanel.setOpaque(false);
		bottompanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		this.add(bottompanel);
	}

	/**
	 *  initial the skin
	 */
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

	/**
	 * paint the skin
	 */
	public void paintComponent(Graphics g) 
	{
		g.drawImage(img_t_l.getImage(), 0, 0, this);
		g.drawImage(img_t_bg.getImage(), 0, 0, img_t_bg_w, img_t_bg.getIconHeight(), this);
		g.drawImage(con.getImgUrl("icon1.png").getImage(), 20, 3, 34,16, this);
		g.setColor(Color.white);
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

