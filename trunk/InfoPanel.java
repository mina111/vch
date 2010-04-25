import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class InfoPanel extends JPanel{
	ImageIcon img_m,img_t_rbg,img_b_bg;
	JPanel toppanel,m_panel;
	
	ImageIcon btn_min_img,btn_max_img,btn_close_img;//
	JButton btn_min,btn_max,btn_close;
	
	
	Config con;
	
	int width = 300;
	int height = 250;
	
	JLabel info = new JLabel();
	JLabel website = new JLabel();
	MyButton confirm;
	VSHMainFrame mainFrame;
	
	public InfoPanel(Config con, VSHMainFrame mainFrame){
		this.con = con;
		this.mainFrame = mainFrame;
		this.setLayout(con.getFlowLayout(1,0,0));
		
		img_m = con.getImgUrl("vsh_b_line.png");
		img_t_rbg = con.getImgUrl("vsh_h_rc.png");
		btn_min_img= con.getImgUrl("min.png");
		btn_max_img= con.getImgUrl("max.png");
		btn_close_img= con.getImgUrl("close.png");
		img_b_bg = con.getImgUrl("vsh_b_bg.png");
		setTop();

		setMainPanel();
		
	}
	public void setTop()
	{
		toppanel = new JPanel();
		toppanel.setPreferredSize(new Dimension(width,img_t_rbg.getIconHeight()));
		toppanel.setOpaque(false);
		toppanel.setLayout(con.getFlowLayout(2,0,0)); 
		toppanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		
		btn_min = new JButton(btn_min_img);
		btn_min.setPreferredSize(new Dimension(btn_min_img.getIconWidth(),btn_min_img.getIconHeight()));
		btn_min.setBorder(null);
		btn_min.setFocusCycleRoot(false);
		btn_min.setRolloverIcon(con.getImgUrl("min_on.png"));
		btn_min.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_min.setToolTipText("Minimum");
		btn_min.setActionCommand("min_info");
		btn_min.addActionListener(new Action(mainFrame));
		toppanel.add(btn_min);



		btn_close = new JButton(btn_close_img);
		btn_close.setPreferredSize(new Dimension(btn_close_img.getIconWidth(),btn_close_img.getIconHeight()));
		btn_close.setBorder(null);
		btn_close.setFocusCycleRoot(false);
		btn_close.setRolloverIcon(con.getImgUrl("close_on.png"));
		btn_close.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_close.setToolTipText("close");
		btn_close.setActionCommand("close_info");
		btn_close.addActionListener(new Action(mainFrame));
		toppanel.add(btn_close);
		this.add(toppanel);
	}
	
	
	public void setMainPanel()
	{
		int h = height - img_t_rbg.getIconHeight();
		m_panel = new JPanel();
		confirm = new MyButton(con,  "confirm_info_on.png", "confirm_info_pressing.png","confirm_info");
		confirm.setActionCommand("close_info");
		confirm.addActionListener(new Action(mainFrame));
		m_panel.setLayout(con.getFlowLayout(1,0,0));
		info.setPreferredSize(new Dimension(width-img_m.getIconWidth()*2,100));
		Font font = new Font("Arial",Font.BOLD ,10);
		info.setBackground(con.getSplitBgColor());
		info.setFont(font);
		info.setText("<html><p>Visualisation Tool for a Selection Hyper-Heuristic(VSH)</p><p></p><p>Version: 1.0.0</p><p></p><p> (c) Copyright VSH contributors, 2010. All rights reserved.</p><p></p></html>");
		info.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		m_panel.add(info);
		website.setPreferredSize(new Dimension(width-img_m.getIconWidth()*2,80));
		website.setFont(font);
		website.setText("<html>Website: <a   href='http://code.google.com/p/vch'>http://code.google.com/p/vch</a></html>");
		website.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				// When someone clicks on the top-bar link:

				// Check that we have access to the desktop.
				if( java.awt.Desktop.isDesktopSupported() ) {

					java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

					// Check that we are allowed to browse.
					if( desktop.isSupported( java.awt.Desktop.Action.BROWSE ) ) {

						try {

							// Construct a new URI using the string.
							java.net.URI link = new java.net.URI( "http://code.google.com/p/vch" );
							// Then call the desktop's default browser to open the URI.
							desktop.browse( link );

						} catch ( Exception be ) {

							System.err.println( be.getMessage() );

						} // END try/catch

					} // END if

				} // END if

			} // END mouseclicked

		});
		website.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(m_panel);
		this.add(website);
		this.add(confirm);
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponents(g);

		g.drawImage(img_t_rbg.getImage(), 0, 0, this.getWidth(), img_t_rbg.getIconHeight(), this);
		g.drawImage(img_m.getImage(), 0, img_t_rbg.getIconHeight(),img_m.getIconWidth(), this.getHeight()- img_t_rbg.getIconHeight(), this);
		g.drawImage(img_m.getImage(), this.getWidth() - img_m.getIconWidth(),img_t_rbg.getIconHeight(), img_m.getIconWidth(), this.getHeight()- img_t_rbg.getIconHeight(), this);
		g.drawImage(img_b_bg.getImage(), 0, this.getHeight()- 5, this.getWidth(), 5, this);
		g.drawImage(con.getImgUrl("icon1.png").getImage(), 10, 3, 34,16, this);
	}
}
