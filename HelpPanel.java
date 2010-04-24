import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HelpPanel extends JPanel{
	ImageIcon img_m,img_t_rbg,img_b_bg;
	JPanel toppanel,m_panel;
	
	ImageIcon btn_min_img,btn_max_img,btn_close_img;//
	JButton btn_min,btn_max,btn_close;
	
	
	Config con;
	
	int width = 605;
	int height = 430;
	
	JLabel info = new JLabel();
	VSHMainFrame mainFrame;
	public HelpPanel(Config con, VSHMainFrame mainFrame){
		this.con = con;
		this.mainFrame = mainFrame;
		this.setLayout(con.getFlowLayout(1,0,0));
		
		img_m = con.getImgUrl("exam_b_line.png");
		img_t_rbg = con.getImgUrl("exam_h_rc.png");
		btn_min_img= con.getImgUrl("min.png");
		btn_max_img= con.getImgUrl("max.png");
		btn_close_img= con.getImgUrl("close.png");
		img_b_bg = con.getImgUrl("exam_b_bg.png");
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
		btn_min.setActionCommand("min_help");
		btn_min.addActionListener(new Action(mainFrame));
		toppanel.add(btn_min);



		btn_close = new JButton(btn_close_img);
		btn_close.setPreferredSize(new Dimension(btn_close_img.getIconWidth(),btn_close_img.getIconHeight()));
		btn_close.setBorder(null);
		btn_close.setFocusCycleRoot(false);
		btn_close.setRolloverIcon(con.getImgUrl("close_on.png"));
		btn_close.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_close.setToolTipText("close");
		btn_close.setActionCommand("close_help");
		btn_close.addActionListener(new Action(mainFrame));
		toppanel.add(btn_close);
		this.add(toppanel);
	}
	
	
	public void setMainPanel()
	{
		int h = height - img_t_rbg.getIconHeight();
		m_panel = new JPanel();
		m_panel.setLayout(con.getFlowLayout(1,0,0));
		info.setPreferredSize(new Dimension(width-img_m.getIconWidth()*2,400));
		info.setIcon(con.getImgUrl("help.png"));	
		m_panel.add(info);

		this.add(m_panel);

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