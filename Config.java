import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.UIManager;



public class Config
{
	//===========================
	//һЩ��ʼ������
	//===========================
	String skin_dir , softtitle ;//���Ƥ�� | ����ı�������
	int width,height;//��ʼ������
	int leftmenuwidth , splitbarwidth ; //��Ҫ����,������˵����|���ҷָ����Ŀ��
	boolean iffulltop , ifnormaltop , openfull , lmenucanclose; //�������ʱ��ǰ����ʾ|����ģʽ��ǰ����ʾ|����ʱ��߲˵��Ƿ���ʾ|����Ƿ���Թر�
	private int splitbarHeight;

	//===========================
	//��ʼ��
	Config()
	{
		skin_dir = "icon";//���Ƥ���ļ���
		width = 1024 ; //�����
		height = 728 ; //�����
		iffulltop = true ; //ȫ��Ļ�Ƿ�����ǰ����ʾ
		ifnormaltop = false ; //��ȫ���Ƿ�����ǰ����ʾ
		leftmenuwidth = 150 ; //��߶����˵����
		splitbarwidth = 6 ; //�ָ������
		splitbarHeight = 6;
		openfull = false ; //�������ʱ��ȫ��
		softtitle = "Visualisation Tool for a Selection Hyper-Heuristic (code.google.com/p/vch)";
		lmenucanclose = true; //�Ƿ���Թر���߲˵���
	}
	//===========================
	//����Ƥ���ļ���
	public String getSkinDir()
	{
		return skin_dir;//����Ƥ���ļ���
	}
	//=================================
	//���ô����С
	//=================================

	// ==================================
	// ���ذ���ָ��ͼƬ����
	// ==================================
	public ImageIcon getImgUrl(String img)
	{
		return new ImageIcon(VSHMainFrame.class.getResource(getSkinDir() + "/" + img));
	}
	// ==================================
	// ���ش�����С�ߴ�
	// ==================================
	public Dimension getFrameSmallSize(boolean full)
	{
		if(!full)//������󻯳�ʼ
		{
			return new Dimension(width,height);
		}
		else//ȫ��
		{
			Toolkit kit = Toolkit.getDefaultToolkit();
			return new Dimension(kit.getScreenSize());
		}
	}
	// ==================================
	// ���طֱ���
	// ==================================
	public Dimension getScreenSize()
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		return new Dimension(kit.getScreenSize());
	}
	// ==================================
	//  ���ʱ�Ƿ���ʾ��ǰ��,�翼��ϵͳ�������������
	// ==================================
	public void fullAllWaysTop(VSHMainFrame frame)
	{
		frame.setAlwaysOnTop(iffulltop);
	}
	// ==================================
	//  ����ʱ�Ƿ���ʾ��ǰ��,�翼��ϵͳ�������������
	// ==================================
	public void normalAllWaysTop(VSHMainFrame frame)
	{
		frame.setAlwaysOnTop(ifnormaltop);
	}
	// ==================================
	//  ���ز��ֶ���,����:0��,1��,2��
	// ==================================
	public FlowLayout getFlowLayout(int flag,int hgap,int vgap)
	{
		FlowLayout fl = new FlowLayout();
		fl.setHgap(hgap);
		fl.setVgap(vgap);
		fl.setAlignment(flag);
		return fl;
	}
	//===================================
	//������߶����˵����Ŀ��
	//===================================
	public int getLeftMenuWidth()
	{
		return this.leftmenuwidth;
	}
	//===================================
	//���طָ������
	//===================================
	public int getSplitBarWidth()
	{
		return this.splitbarwidth;
	}
	//===================================
	//������߲˵�����
	//===================================
	public Color getLeftBgColor()
	{
		return new Color(50,52,55);
	}
	//===================================
	//���طָ�ҳ�汳��ɫ
	//===================================
	public Color getSplitBgColor()
	{
		return new Color(30,30,30);
	}
	//===================================
	//�����ұ�����ɫ��
	//===================================
	public Color getMainColor()
	{
		return new Color(55,55,55);
	}
	//===================================
	//��ʼ��UI
	//===================================
	public void setFrameUI()
	{
		Font font = new Font("����", Font.PLAIN, 12);
		UIManager.put("Label.font", font);
		UIManager.put("Button.font", font);

		Color gray = new Color(240,240,240);
		Color black = new Color(0,0,0);

       /* UIManager.put("ScrollBar.track", black);
        UIManager.put("ScrollBar.trackHighlight",black);

        UIManager.put("ScrollBar.background", gray);
        UIManager.put("ScrollBar.shadow", gray);
        UIManager.put("ScrollBar.darkShadow", gray);

        UIManager.put("ScrollBar.thumb", gray);
        UIManager.put("ScrollBar.thumbShadow",gray);
        UIManager.put("ScrollBar.thumbDarkShadow",gray);
        UIManager.put("ScrollBar.thumbHighlight", new Color(250,250,250));
        //UIManager.put("ScrollBar.thumbLightShadow", new Color(250,60,250));
        UIManager.put("ScrollBar.width", 17);

        */

        /*
        ScrollBar.background
		ScrollBar.darkShadow
		ScrollBar.focusInputMap
		ScrollBar.foreground
		ScrollBar.highlight
		ScrollBar.maximumThumbSize
		ScrollBar.minimumThumbSize
		ScrollBar.shadow
		ScrollBar.thumb
		ScrollBar.thumbDarkShadow
		ScrollBar.thumbHighlight
		ScrollBar.thumbLightShadow
		ScrollBar.thumbShadow
		ScrollBar.track
		ScrollBar.trackHighlight
		ScrollBar.width
         */


	}
	public int getSplitBarHeight() {
		// TODO Auto-generated method stub
		return this.splitbarHeight;
	}
	public int getUpMenuHeight(int height ) {
		// TODO Auto-generated method stub
		return (int) ((height-splitbarHeight)*0.65) ;
	}
}
