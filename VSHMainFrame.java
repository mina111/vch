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
 * @author	   	Alexander Jermstad (asj08u) 					  <br>
 * 			
 */

/**
 *  main frame 
 */
public class VSHMainFrame extends JFrame{
	/**
	 * the main panel 
	 */
	MainPanel panel;
	/**
	 *  the skin and icon directory
	 */
	String img_dir = "skin_black";
	/**
	 *  the size of the frame
	 */
	Dimension fdm,sdm;
	/**
	 * the config instance
	 */
	Config con;
	/**
	 *  the boolean variable to control if stop
	 */
	public boolean stop = true;
	/**
	 *  the boolean variable to control if pause
	 */
	public boolean pause = false;
	/**
	 *  the boolean variable to control if play
	 */
	public boolean start = false;
	/**
	 *  the integer of array to treat as lock to control animation draw
	 */
	int[] lock = new int[0];
	/**
	 *  references to vsh instance
	 */
	VSH vsh;
	
	
/**
 *  the constructor for VSHMainFrame
 */
	VSHMainFrame(String title,VSH vsh)
	{
		
		super(title);
		this.vsh = vsh;
		setWindow(this);
		updateWindow(this);
		
	}

	
	/**
	 * Initial the frame
	 * @param f
	 */
	private void setWindow(VSHMainFrame f)
	{
		con = new Config();
		con.setFrameUI();
		img_dir = con.getSkinDir();
		fdm = con.getFrameSmallSize(false);
		sdm = con.getScreenSize();
		f.setSize(fdm);
		addMainPanel(this,fdm.width,fdm.height,false);
		f.setLocation((sdm.width-f.getWidth())/2, (sdm.height-f.getHeight())/2);
		f.setIconImage(Toolkit.getDefaultToolkit().getImage(VSH.class.getResource(img_dir + "/icon1.png")));
		f.setBackground(Color.WHITE);
		
	}
	
	
	/**
	 *  Update the window
	 */
	private void updateWindow(VSHMainFrame f)
	{
		f.setUndecorated(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.validate();
		f.setVisible(true);
		f.panel.m_panel.animationPanel.setLayout(null);
		f.panel.m_panel.flowchart.setBounds(new Rectangle((f.panel.m_panel.animationPanel.getWidth()-f.panel.m_panel.flowchart.label_img.getIconWidth())/2,(f.panel.m_panel.animationPanel.getHeight()-f.panel.m_panel.flowchart.label_img.getIconHeight())/2,f.panel.m_panel.flowchart.label_img.getIconWidth(),f.panel.m_panel.flowchart.label_img.getIconHeight()));
	}

	/**
	 *  Add the main panel
	 * @param frame
	 * @param fwidth
	 * @param fheight
	 * @param iffull
	 */
	private void addMainPanel(final VSHMainFrame frame,int fwidth,int fheight,boolean iffull)
	{
		panel = new MainPanel(frame,fwidth,fheight);
		panel.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
		this.getContentPane().add(panel);
		panel.btn_close.setActionCommand("cmd_close");
		panel.btn_close.addActionListener(new Action(this,panel.btn_close));
		panel.btn_min.setActionCommand("cmd_min");
		panel.btn_min.addActionListener(new Action(this,panel.btn_min));
		final Point point = new Point();
		panel.toppanel.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{

					Point tpoint = getLocation();
					setLocation(tpoint.x + e.getX() - point.x, tpoint.y + e.getY() - point.y );
			}
		});
		panel.bottompanel.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
					Point tpoint = getLocation();
					setLocation(tpoint.x + e.getX() - point.x, tpoint.y + e.getY() - point.y );
			}
		});
	}

/**
 *  create the m
 * @param img
 * @param rimg
 * @param text
 * @param flag
 * @return
 */

 /**
  *  Get the menu panel
  */
	public JPanel getMenuPanel()
	{
		return this.panel.menupanel;
	}
	
	
/**
 *  Get the sub menu
 * @return the sub menu panel
 */ 
	public JPanel getSubMenuPanel()
	{
		return this.panel.m_panel.p_left;
	}

	/**
	 *  Set main Menu
	 * @param button
	 */
	public void setMainMenu(JButton button)
	{
		button.addActionListener(new Action(this,button));
		button.setActionCommand("lmenu_add");
		getMenuPanel().add(button);
	}
	
	/**
	 *  Stop the animation 
	 */
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
	
	/**
	 *  play the animation
	 */
	public void play() {
		// TODO Auto-generated method stub
		synchronized(lock){
			lock.notifyAll();
		}
	}
}
