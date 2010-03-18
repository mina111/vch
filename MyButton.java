import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;



public class MyButton extends JButton{

	public MyButton (Config con,String img,String rimg,String text){
		ImageIcon btn_img = con.getImgUrl(img);
		this.setIcon(btn_img);
		this.setPreferredSize(new Dimension(btn_img.getIconWidth(),btn_img.getIconHeight()));
		this.setBorder(null);
		this.setFocusCycleRoot(false);
		this.setRolloverIcon(con.getImgUrl(rimg));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setToolTipText(text);
		
	}
}
