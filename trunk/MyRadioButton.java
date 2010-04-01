import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;


public class MyRadioButton extends JRadioButton{
	public MyRadioButton (Config con,String img,String rimg,String rsimg,String text){
		ImageIcon btn_img = con.getImgUrl(img);
		this.setIcon(btn_img);
		this.setPreferredSize(new Dimension(btn_img.getIconWidth(),btn_img.getIconHeight()));
		this.setBorder(null);
		this.setFocusCycleRoot(false);
		this.setRolloverIcon(con.getImgUrl(rimg));
		this.setSelectedIcon(con.getImgUrl(rsimg));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setToolTipText(text);

	}
}
