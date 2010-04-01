
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
 *  This class is the customized  button
 */
public class MyButton extends JButton{
	/**
	 * define the object button with config, string name of image for icon, string name of image for rolloverIcon and name
	 * @param con	config
	 * @param img	name of image for icon
	 * @param rimg	name of image for rolloverIcon
	 * @param text 	name
	 */
	public MyButton (Config con,String img,String rimg,String text){
		setUp(con,img,rimg,text);
		
	}
	
	/**
	 * define the object button with config, string name of image for icon, string name of image for rolloverIcon, string name of image for disabled and name
	 * @param con	config
	 * @param img	name of image for icon
	 * @param rimg	name of image for rolloverIcon
	 * @param disableImg  name of image for disabled state
	 * @param text 	name 
	 */
	public MyButton(Config con, String img,String rimg,String disableImg,String text){
		setUp(con,img,rimg,text);
		this.setDisabledIcon(con.getImgUrl(disableImg));
	}
	
	/**
	 *  set up the icon, rolloverIcon and name for button
	 * @param con
	 * @param img
	 * @param rimg
	 * @param text
	 */
	void setUp(Config con,String img,String rimg,String text){
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
