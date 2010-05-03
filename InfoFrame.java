import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;


public class InfoFrame extends JFrame{
	
	public InfoFrame(Config con, VSHMainFrame mainFrame){
		setTitle("About");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VSHMainFrame.class.getResource(con.getSkinDir() + "/" + "icon1.png")));
		Point mainFramePoint = mainFrame.getLocation();
		setSize(400, 250);
		setLocation((mainFrame.getSize().width-300)/2+mainFramePoint.x, (mainFrame.getSize().height-250)/2+mainFramePoint.y);
		InfoPanel infoPanel = new InfoPanel(con,mainFrame);
		add(infoPanel);
		final Point point = new Point();
		infoPanel.toppanel.addMouseListener (new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e) 
			{
				point.x = e.getX();
				point.y = e.getY();
			}
		});
		infoPanel.toppanel.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{

					Point tpoint = getLocation();
					setLocation(tpoint.x + e.getX() - point.x, tpoint.y + e.getY() - point.y );
			}
		});
		setUndecorated(true);
		setVisible(true);
	}
}
