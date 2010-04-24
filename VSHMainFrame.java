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
	MainPanel panel;
	boolean isFullScreen = true; 
	boolean lmenu_isopen = true ; 
	String img_dir = "skin_black";
	Dimension fdm,sdm;
	Config con;
	String reDoCommand ;
	public boolean stop = true;
	public boolean pause = false;
	public boolean start = false;
	int[] lock = new int[0];
	VSH vsh;
	InfoFrame info;
	final Point point = new Point();
	HelpFrame help;
	VSHMainFrame(String title,VSH vsh)
	{

		super(title);
		this.vsh = vsh;
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
		f.setIconImage(Toolkit.getDefaultToolkit().getImage(VSH.class.getResource(img_dir + "/icon1.png")));
		f.setBackground(Color.WHITE);

	}

	private void updateWindow(VSHMainFrame f)
	{
		f.setUndecorated(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.validate();
		f.setVisible(true);
		f.panel.m_panel.animationPanel.setLayout(null);
		f.panel.m_panel.flowchart.setBounds(new Rectangle((f.panel.m_panel.animationPanel.getWidth()-f.panel.m_panel.flowchart.label_img.getIconWidth())/2,(f.panel.m_panel.animationPanel.getHeight()-f.panel.m_panel.flowchart.label_img.getIconHeight())/2,f.panel.m_panel.flowchart.label_img.getIconWidth(),f.panel.m_panel.flowchart.label_img.getIconHeight()));
	}

	private void addMainPanel(final VSHMainFrame frame,int fwidth,int fheight,boolean iffull)
	{

		panel = new MainPanel(frame,fwidth,fheight);
		panel.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
		this.getContentPane().add(panel);

		panel.btn_close.setActionCommand("cmd_close");
		panel.btn_close.addActionListener(new Action(this,panel.btn_close));


		panel.btn_min.setActionCommand("cmd_min");
		panel.btn_min.addActionListener(new Action(this,panel.btn_min));

		panel.toppanel.addMouseListener (new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e) 
			{
				point.x = e.getX();
				point.y = e.getY();
			}
		});

		panel.toppanel.addMouseMotionListener(new MouseMotionAdapter()
		{

			public void mouseDragged(MouseEvent e)
			{
				if(!isFullScreen)
				{
					Point tpoint = getLocation();
					setLocation(tpoint.x + e.getX()-point.x , tpoint.y + e.getY()-point.y  );
				}
			}
		});
		
		panel.bottompanel.addMouseListener (new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e) 
			{
				point.x = e.getX();
				point.y = e.getY();
			}
		});


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
	public void createInfoFrame(){
		info = new InfoFrame(con,this);
		
		
	}
	
	public void createHelpFrame(){
		help = new HelpFrame(con,this);
		
		
	}
}
