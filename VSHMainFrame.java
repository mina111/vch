import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
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

	MainPanel panel;
	boolean isFullScreen = true; 
	boolean lmenu_isopen = true ; 
	String img_dir = "skin_black";
	Dimension fdm,sdm;
	Config con;
	String reDoCommand ;

	VSHMainFrame(String title)
	{
		super(title);

		setWindow(this);

		updateWindow(this);
	}

	private void setWindow(VSHMainFrame f)
	{
		
		con = new Config();
		con.setFrameUI();

		isFullScreen = con.openfull;
	
		img_dir = con.getSkinDir();
		fdm = con.getFrameSmallSize(isFullScreen);
		sdm = con.getScreenSize();
		f.setSize(fdm);
		addMainPanel(this,fdm.width,fdm.height,isFullScreen);
		f.setLocation((sdm.width-f.getWidth())/2, (sdm.height-f.getHeight())/2);
		f.setIconImage(Toolkit.getDefaultToolkit().getImage(VSH.class.getResource(img_dir + "/icon.png")));
		f.setBackground(Color.WHITE);
	}

	private void updateWindow(JFrame f)
	{
		f.setUndecorated(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.validate();
		f.setVisible(true);
	}
	
	private void addMainPanel(final VSHMainFrame frame,int fwidth,int fheight,boolean iffull)
	{
		
		panel = new MainPanel(frame,fwidth,fheight);
		panel.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
		this.getContentPane().add(panel);

		panel.btn_close.setActionCommand("cmd_close");
		panel.btn_close.addActionListener(new Action(this,panel.btn_close));

		if(iffull)
		{
			panel.btn_max.setIcon(con.getImgUrl("maxed.png"));
			panel.btn_max.setRolloverIcon(con.getImgUrl("maxed_on.png"));
			panel.btn_max.setActionCommand("cmd_normal");
			panel.btn_max.addActionListener(new Action(this,panel.btn_max));
		}
		else
		{
			panel.btn_max.setActionCommand("cmd_max");
			panel.btn_max.addActionListener(new Action(this,panel.btn_max));
		}

		panel.btn_min.setActionCommand("cmd_min");
		panel.btn_min.addActionListener(new Action(this,panel.btn_min));
	
		final Point point = new Point();
	
		panel.toppanel.addMouseListener (new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e) 
			{
				point.x = e.getX();
				point.y = e.getY();
			}
			public void mouseClicked(MouseEvent e)
			{
				if(e.getClickCount() == 2)
				{
					if(panel.btn_max.getActionCommand().equals("cmd_normal"))
					{
						fdm = con.getFrameSmallSize(false);
						con.resetFrameSize(frame,fdm.width,fdm.height);
						validate(); 
						con.normalAllWaysTop(frame);
						isFullScreen = false;
						
						panel.btn_max.setIcon(con.getImgUrl("max.png"));
						panel.btn_max.setRolloverIcon(con.getImgUrl("max_on.png"));
						setLocation((sdm.width-getWidth())/2, (sdm.height-getHeight())/2);
						panel.btn_max.setActionCommand("cmd_max");
						panel.btn_max.setToolTipText("Full Screen");
						//action.repaintFrame();
						//new SubMenuActionDo(frame).btnExamKindAction();
						validate();
					}
					else
					{
						con.resetFrameSize(frame,sdm.width,sdm.height);						
						validate(); 
						con.fullAllWaysTop(frame);
						isFullScreen = true;
						setLocation((sdm.width-getWidth())/2, (sdm.height-getHeight())/2);
						
						panel.btn_max.setIcon(con.getImgUrl("maxed.png"));
						panel.btn_max.setRolloverIcon(con.getImgUrl("maxed_on.png"));
						panel.btn_max.setActionCommand("cmd_normal");
						panel.btn_max.setToolTipText("Normal");
						
						validate();
					}
				}
			}
		});
	
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
	
		panel.bottom_resize.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
			
				int min_w = 800;
				int min_h = 600 ;
				
				int max_w = con.getScreenSize().width;
				int max_h = con.getScreenSize().height;
				int fw = frame.getWidth(); 
				int fh = frame.getHeight();
				int ex = fw + e.getX() - panel.bottom_resize.getWidth(); 
				int ey = fh + e.getY() - panel.bottom_resize.getHeight(); 
				if( ex < min_w ) ex = min_w ; 
				if( ey < min_h ) ey = min_h ;
				if( ex > max_w ) ex = max_w ; 
				if( ey > max_h ) ey = max_h ; 

				if(!isFullScreen)
				{
					
					con.resetFrameSize(frame, ex, ey);
					frame.validate();
				}
			}
		});
	
		panel.bottom_resize.addMouseListener (new MouseAdapter() 
		{
			public void mouseReleased(MouseEvent e) 
			{

			}
		});
	}

	public void openLeftMenu()
	{
		int tw = this.panel.m_panel.p_left.getWidth() ; 
		this.panel.m_panel.p_left.setVisible(true);
		this.panel.m_panel.p_right.setPreferredSize(new Dimension(this.panel.m_panel.p_right.getWidth() - tw -17 ,this.panel.m_panel.getHeight()));
		this.panel.m_panel.p_right.setPreferredSize(new Dimension(this.panel.m_panel.p_right.getWidth() - tw,this.panel.m_panel.getHeight()));
		this.lmenu_isopen = true;
		this.panel.m_panel.btn_split.setIcon(con.getImgUrl("btn_split_l.png"));
		this.panel.m_panel.btn_split.setActionCommand("lmenu_close");
		this.panel.m_panel.btn_split.setToolTipText("Close Menu");
		this.validate();

		this.validate();
	}

	public void closeLeftMenu()
	{
		if(!con.lmenucanclose) return; 
		int tw = this.panel.m_panel.p_left.getWidth() ; 
		this.panel.m_panel.p_left.setVisible(false);
		this.panel.m_panel.p_right.setPreferredSize(new Dimension(this.panel.m_panel.p_right.getWidth() + tw,this.panel.m_panel.getHeight()));
		this.panel.m_panel.p_right.setPreferredSize(new Dimension(this.panel.m_panel.p_right.getWidth() + tw,this.panel.m_panel.getHeight()));
		this.lmenu_isopen = false;
		this.panel.m_panel.btn_split.setIcon(con.getImgUrl("btn_split_r.png"));
		this.panel.m_panel.btn_split.setActionCommand("lmenu_open");
		this.panel.m_panel.btn_split.setToolTipText("Open Menu");
		this.validate();
		this.validate();
	}

	public JButton getMenuButton(String img,String rimg,String text,String flag)
	{
		String menudir ; 
		if(flag.equals("mainmenu")) menudir = "mainmenu/";
		else menudir = "submenu/"; 
		ImageIcon btn_img = con.getImgUrl(menudir+img);
		JButton btn = new JButton(btn_img);
		btn.setPreferredSize(new Dimension(btn_img.getIconWidth(),btn_img.getIconHeight()));
		btn.setBorder(null);
		btn.setFocusCycleRoot(false);
		btn.setRolloverIcon(con.getImgUrl(menudir+rimg));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setToolTipText(text);
		return btn;
	}

	public JPanel getMenuPanel()
	{
		return this.panel.menupanel;
	}

	public JPanel getSubMenuPanel()
	{
		return this.panel.m_panel.p_left;
	}
	
	public void setMainMenu(JButton button)
	{

		button.addActionListener(new Action(this,button));
		button.setActionCommand("lmenu_add");
		getMenuPanel().add(button);
	}

}
