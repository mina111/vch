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
	//�������panel
	MainPanel panel;
	boolean isFullScreen = true; // �Ƿ�ȫ��
	boolean lmenu_isopen = true ; // ��߶����˵��Ƿ�չ��
	String img_dir = "skin_black";//Ƥ���ļ���
	Dimension fdm,sdm;//�����С:����|�ֱ���
	Config con;//������
	//Vector<MenuButtonBox> menulist = new Vector<MenuButtonBox>();//�˵�����\
	String reDoCommand ;
	public boolean stop = true;
	public boolean pause = false;
	public boolean start = false;
	int[] lock = new int[0];
	VSH vsh;
	//RepaintActionDo action;
	//====================
	//��ʼ��
	VSHMainFrame(String title,VSH vsh)
	{
		
		super(title);
		this.vsh = vsh;
		//��ʼ������
		setWindow(this);
		//���´���
		updateWindow(this);
		
	}
	//===============================
	//�����ʼ��
	//===============================
	private void setWindow(VSHMainFrame f)
	{
		//=============================
		//��ȡ������
		con = new Config();
		con.setFrameUI();
	//	action = new RepaintActionDo(f);
		//����ʱ�Ƿ�ȫ��
		isFullScreen = con.openfull;
		//��ʼ��Ƥ���ļ�
		img_dir = con.getSkinDir();
		fdm = con.getFrameSmallSize(isFullScreen);
		sdm = con.getScreenSize();
		f.setSize(fdm);//���ô����С
		addMainPanel(this,fdm.width,fdm.height,isFullScreen);//��������
		f.setLocation((sdm.width-f.getWidth())/2, (sdm.height-f.getHeight())/2);//λ��
		f.setIconImage(Toolkit.getDefaultToolkit().getImage(VSH.class.getResource(img_dir + "/icon1.png")));//ͼ��
		f.setBackground(Color.WHITE);//���ñ���ɫ
		
	}
	//===============================
	//���´���
	//===============================
	private void updateWindow(VSHMainFrame f)
	{
		f.setUndecorated(true);//����û�б߿�
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ϵͳ�˳��¼�
		f.validate();//��Ч
		f.setVisible(true);//�ɼ�
		f.panel.m_panel.animationPanel.setLayout(null);
		f.panel.m_panel.flowchart.setBounds(new Rectangle((f.panel.m_panel.animationPanel.getWidth()-f.panel.m_panel.flowchart.label_img.getIconWidth())/2,(f.panel.m_panel.animationPanel.getHeight()-f.panel.m_panel.flowchart.label_img.getIconHeight())/2,f.panel.m_panel.flowchart.label_img.getIconWidth(),f.panel.m_panel.flowchart.label_img.getIconHeight()));
	}
	//================================
	//��������
	//================================
	private void addMainPanel(final VSHMainFrame frame,int fwidth,int fheight,boolean iffull)
	{
		//=====================
		//�������
		panel = new MainPanel(frame,fwidth,fheight);
		panel.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
		this.getContentPane().add(panel);
		//=====================
		//�رհ�ť�¼�
		panel.btn_close.setActionCommand("cmd_close");
		panel.btn_close.addActionListener(new Action(this,panel.btn_close));

		//=====================
		//��С��
		panel.btn_min.setActionCommand("cmd_min");
		panel.btn_min.addActionListener(new Action(this,panel.btn_min));
		//=====================
		//����¼������
		final Point point = new Point();
		//=====================
		//����panel����¼�,����˫��
		//================
		//����Panel�϶��¼�
		panel.toppanel.addMouseMotionListener(new MouseMotionAdapter()
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
		
		//����Panel�϶��¼�
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

	//===============================
	//�����˵�����,
	//===============================
	public JButton getMenuButton(String img,String rimg,String text,String flag)
	{
		String menudir ; //�˵�ͼ���ļ���
		if(flag.equals("mainmenu")) menudir = "mainmenu/";//���˵�
		else menudir = "submenu/"; //�����˵�
		ImageIcon btn_img = con.getImgUrl(menudir+img);
		JButton btn = new JButton(btn_img);
		btn.setPreferredSize(new Dimension(btn_img.getIconWidth(),btn_img.getIconHeight()));
		btn.setBorder(null);
		btn.setFocusCycleRoot(false);
		btn.setRolloverIcon(con.getImgUrl(menudir+rimg));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setToolTipText(text);
		return btn;//���ذ�ť
	}
	//===============================
	//���ز˵����
	//===============================
	public JPanel getMenuPanel()
	{
		return this.panel.menupanel;
	}
	//===============================
	//���ض����˵����
	//===============================
	public JPanel getSubMenuPanel()
	{
		return this.panel.m_panel.p_left;
	}
	//===============================
	//�������˵�,����:���˵���ť
	//===============================
	public void setMainMenu(JButton button)
	{
		//����¼�
		button.addActionListener(new Action(this,button));
		button.setActionCommand("lmenu_add");//��������
		getMenuPanel().add(button);
	}
	//===============================
	// ��֯�˵���Ӧ����,����:���˵���ť,��ӵĲ˵���ť,��ʼ����
	//===============================
/*	public void addSubMenu(JButton mainbutton,JButton button,String comand)
	{
		//���Ӳ˵���ӵ��˵�������
		menulist.add(new MenuButtonBox(mainbutton,button,comand));
	}*/
	//===============================
	//����Ĭ�ϲ˵�,����:�˵���ť
	//===============================
/*	public void setDefaultMenu(JButton button)
	{
		getSubMenu(button);
	}
	//===============================
	//��ʾָ���˵����Ӳ˵�
	//===============================
	public void getSubMenu(JButton button)
	{
		//============================
		//��ʾС�˵�
		//============================
		//�������
		JPanel submenupanel = this.getSubMenuPanel();
		submenupanel.removeAll();//ɾ��ԭ����
		this.repaint();//���»滭
		Vector<MenuButtonBox> vct = this.menulist;//�˵�����
		SubMenuActionDo saction = new SubMenuActionDo(this);
		//����������С��
		for(int i=0;i<vct.size();i++)
		{
			//��������˵����ڵ�ǰ������ť
			if(vct.elementAt(i).getMainbutton() == button)
			{
				JButton tbutton = vct.elementAt(i).getButton();
				tbutton.addActionListener(saction);
				tbutton.setActionCommand(vct.elementAt(i).getActioncommand());
				submenupanel.add(tbutton);
			}
		}
	}
	//==================================
	//�����ұ߿���С���
	//==================================
	public int getMinMainBoardWidth()
	{
		int w =this.panel.m_panel.scrollPane.getWidth() - 17;
		if(w<619) w = 619;
		return w;
	}*/
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
}
