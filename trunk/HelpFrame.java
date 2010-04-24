import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;


public class HelpFrame extends JFrame{
	public HelpFrame(Config con, VSHMainFrame mainFrame){
		setTitle("Flowchart");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VSHMainFrame.class.getResource(con.getSkinDir() + "/" + "icon1.png")));
		Point mainFramePoint = mainFrame.getLocation();
		setSize(605, 430);
		setLocation((mainFrame.getSize().width-605)/2+mainFramePoint.x, (mainFrame.getSize().height-430)/2+mainFramePoint.y);
		HelpPanel helpPanel = new HelpPanel(con,mainFrame);
		add(helpPanel);
		final Point point = new Point();
		helpPanel.toppanel.addMouseListener (new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e) 
			{
				point.x = e.getX();
				point.y = e.getY();
			}
		});
		helpPanel.toppanel.addMouseMotionListener(new MouseMotionAdapter()
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
