import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.net.URI;
import java.awt.Desktop;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



class MainPanel extends JPanel
{
	//======================
	// �ϱ߿�ͼƬ�����:��߿�|��뱳��|�м����|�ұ߱���| �ұ߱߿�
	ImageIcon img_t_l , img_t_bg , img_t_mid , img_t_rbg ,img_t_r;
	int img_t_l_w , img_t_bg_w , img_t_mid_w , img_t_rbg_w , img_t_r_w;

	// =====================
	// ���ұ���
	ImageIcon img_m;
	int img_m_w;

	// =====================
	// ��β��:��߽�|����|�ұ߽�
	ImageIcon img_b_l , img_b_bg , img_b_r ;
	int img_b_l_w , img_b_bg_w , img_b_r_w;

	//======================
	//�˵�����Ϣ������ͼƬ:�˵�����ͼƬ|�˵�������Ϣ������
	ImageIcon img_menu , img_info;

	// =====================
	//���
	int width,height;

	//======================
	//�õ�����
	//======================
	Config con = new Config();

	//======================
	//�м���Ҫ���
	//======================
	CustomizeExhibitionPanel m_panel;

	//======================
	//��Ҫ���
	//======================
	JPanel toppanel;//��һ��
	JLabel toptitle;//����������
	//Ҫ�õ��İ�ťͼƬ:��С��ͼƬ|���ͼƬ|�ر�ͼƬ,�����Ƕ�Ӧ�İ�ť
	ImageIcon btn_min_img,btn_close_img;//
	JButton btn_min,btn_close;//Ҫ�õ��İ�ť

	//======================
	//�˵����
	//======================
	JPanel menupanel;

	MyButton play, pause, stop, speedUp, slowDown, info, help;
	//======================
	//��Ϣ���
	//======================
	JPanel infopanel;
	MyLabel first;
	MyLabel benchmarkFunctionInfo,heuristicSelectionInfo, lowLevelHeuristicsInfo, acceptanceMethodInfo, launchDemonstrationInfo;
	MyButton benchmarkFunction, heuristicSelection, lowLevelHeuristics, acceptanceMethod, launchDemonstration;
	//======================
	//�ײ����
	//======================
	JPanel bottompanel ;
	ImageIcon bottom_resize_img;
	//JButton bottom_resize;

	//======================
	//  ���ڵĴ���
	VSHMainFrame frame;
	//======================
	//��ʼ��,����:���
	MainPanel(VSHMainFrame frame,int w,int h)
	{
		//��ʼ�����
		this.width = w;
		this.height = h;
		this.frame = frame;
		this.setOpaque(true);
		initSkinImg(); // ��ʼ��Ƥ����Ϣ
		this.setLayout(con.getFlowLayout(1,0,0)); //���ж���
		//=============================
		//��ʼ����ť
		setTop();
		//=============================
		//��ʼ���˵�
		setMenu();
		//��ʼ���˵��������Ϣ��
		setInfoPanel();
		//=============================
		//�����Ҫ���
		setMiddlePanel();
		//=============================
		//��ӵײ����
		setBottomPanel();

	}
	//===================================
	//   Ϊ�����Ӵ�С�رռ���չ��ť
	//===================================
	public void setTop()
	{
		//================================================
		//���һ�������һ��panel,�������С����ť
		//================================================
		toppanel = new JPanel();
		toppanel.setPreferredSize(new Dimension(width,img_t_l.getIconHeight()));
		toppanel.setOpaque(false);
		toppanel.setLayout(con.getFlowLayout(2,0,0)); // �Ҷ���
		toppanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		this.add(toppanel);
		//=================================
		//��������ļ���ʾ����
		toptitle = new JLabel();
		//���еĿ�ȼ������
		int w = width-btn_close_img.getIconWidth()-btn_min_img.getIconWidth()-img_t_l_w-img_t_bg_w-img_t_mid_w;
		toptitle.setPreferredSize(new Dimension(w,img_t_l.getIconHeight()));
		toptitle.setForeground(Color.white);
		toptitle.setFont(new Font("Arial",0,12));
		toptitle.setText(con.softtitle);
		final String  uri = "http://code.google.com/p/vch";
		toptitle.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				// When someone clicks on the top-bar link:

				// Check that we have access to the desktop.
				if( java.awt.Desktop.isDesktopSupported() ) {

					java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

					// Check that we are allowed to browse.
					if( desktop.isSupported( java.awt.Desktop.Action.BROWSE ) ) {

						try {

							// Construct a new URI using the string.
							java.net.URI link = new java.net.URI( uri );
							// Then call the desktop's default browser to open the URI.
							desktop.browse( link );

						} catch ( Exception be ) {

							System.err.println( be.getMessage() );

						} // END try/catch

					} // END if

				} // END if

			} // END mouseclicked

		});
		toptitle.setCursor(new Cursor(Cursor.HAND_CURSOR));
		toppanel.add(toptitle);
		//=================================
		//��С����ť;
		btn_min = new JButton(btn_min_img);
		btn_min.setPreferredSize(new Dimension(btn_min_img.getIconWidth(),btn_min_img.getIconHeight()));
		btn_min.setBorder(null);
		btn_min.setFocusCycleRoot(false);
		btn_min.setRolloverIcon(con.getImgUrl("min_on.png"));
		btn_min.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_min.setToolTipText("��С��ģʽ");
		toppanel.add(btn_min);
		//=================================
		//��󻯰�ť
		//=================================
		//�رմ��ڰ�ť
		btn_close = new JButton(btn_close_img);
		btn_close.setPreferredSize(new Dimension(btn_close_img.getIconWidth(),btn_close_img.getIconHeight()));
		btn_close.setBorder(null);
		btn_close.setFocusCycleRoot(false);
		btn_close.setRolloverIcon(con.getImgUrl("close_on.png"));
		btn_close.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_close.setToolTipText("�رմ���");
		toppanel.add(btn_close);
	}
	//===================================
	//   ��ʼ���˵����
	//===================================
	public void setMenu()
	{
		//================================================
		//�½��˵���岢���
		//================================================
		menupanel = new JPanel();
		menupanel.setPreferredSize(new Dimension(width,img_menu.getIconHeight()));
		menupanel.setOpaque(false);
		menupanel.setLayout(con.getFlowLayout(0,0,0)); // �����
		play = new MyButton(con,"menu-button-play.png","menu-button-play-hover.png","Play");
		pause = new MyButton(con,"menu-button-pause.png","menu-button-pause-hover.png","Pause");
		slowDown = new MyButton(con,"menu-button-slowdown.png","menu-button-slowdown-hover.png","Slow Down");
		speedUp = new MyButton(con,"menu-button-speedup.png","menu-button-speedup-hover.png","Speed Up");
		stop = new MyButton(con,"menu-button-stop.png","menu-button-stop-hover.png","Stop");
		info = new MyButton(con,"menu-button-info.png","menu-button-info-hover.png","About");
		help = new MyButton(con,"menu-button-help.png","menu-button-help-hover.png","Help");
		play.setActionCommand("play");
		play.addActionListener(new Action(frame,play));
		pause.setActionCommand("pause");
		pause.addActionListener(new Action(frame,pause));
		slowDown.setActionCommand("slow down");
		slowDown.addActionListener(new Action(frame,slowDown));
		speedUp.setActionCommand("speed up");
		speedUp.addActionListener(new Action(frame,speedUp));
		stop.setActionCommand("stop");
		stop.addActionListener(new Action(frame,stop));
		info.setActionCommand("info");
		info.addActionListener(new Action(frame,info));
		help.setActionCommand("help");
		help.addActionListener(new Action(frame,help));
		menupanel.add(play);
		menupanel.add(pause);
		menupanel.add(slowDown);
		menupanel.add(speedUp);
		menupanel.add(stop);
		menupanel.add(info);
		menupanel.add(help);
		this.add(menupanel);
	}
	//===================================
	//��Ӳ˵�
	//===================================
	/*public void addMenu(JButton button)
	{
		menupanel.add(button);
	}*/
	//===================================
	//�˵��������Ϣ��
	//===================================
	public void setInfoPanel()
	{
		infopanel = new JPanel();
		infopanel.setPreferredSize(new Dimension(width,img_info.getIconHeight()));
		infopanel.setOpaque(false);
		infopanel.setLayout(con.getFlowLayout(0,0,0)); //�����

	//	first = new MyLabel(con,"first_bg.png");
	//	benchmarkFunction = new MyButton(con,"benchmark_function_on.png","benchmark_function_pressed.png","Benchmark Function");
	//	benchmarkFunctionInfo = new MyLabel("Benchmark Function: ",con);

	//	infopanel.add(first);
	//	infopanel.add(benchmarkFunction);
	//	infopanel.add(benchmarkFunctionInfo);
	//	launchDemonstration;

		this.add(infopanel);

		//=====================
		//����������������
	}
	//===================================
	//�����Ҫ���
	//===================================
	public void setMiddlePanel()
	{
		int w = width - 2*this.img_m_w;
		//�õ�ʣ��߶�
		int h = height - this.img_t_l.getIconHeight() - this.img_menu.getIconHeight() - this.img_info.getIconHeight() - this.img_b_l.getIconHeight();
		m_panel = new CustomizeExhibitionPanel(frame,w,h);
		this.add(m_panel);
	}
	//===================================
	//  �ײ����
	//===================================
	public void setBottomPanel()
	{
		bottompanel = new JPanel();
		bottompanel.setPreferredSize(new Dimension(width,this.img_b_l.getIconHeight()));
		bottompanel.setLayout(con.getFlowLayout(2,0,0));//�Ҷ���
		bottompanel.setOpaque(false);
		bottompanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		this.add(bottompanel);
		//=====================
		//������½ǿ���������С��ͼ��
	}
	//===================================
	//��ʼ��Ƥ��ͼƬ����
	//===================================
	private void initSkinImg()
	{
		//========================================
		//���ͷ��Ӧ����
		//========================================
		img_t_l = con.getImgUrl("exam_h_l.png");// ���
		img_t_l_w = img_t_l.getIconWidth();
		img_t_bg = con.getImgUrl("exam_h_lc.png");// ��߱���
		img_t_bg_w = 120;
		img_t_mid = con.getImgUrl("exam_h_tag.png");// ����ͼƬ
		img_t_mid_w = img_t_mid.getIconWidth();
		img_t_rbg = con.getImgUrl("exam_h_rc.png");// �ұ߱���
		img_t_rbg_w = img_t_rbg.getIconWidth();
		img_t_r = con.getImgUrl("exam_h_r.png");// �ұ߱߿�
		img_t_r_w = img_t_r.getIconWidth();
		//============
		//��ť��
		btn_min_img= con.getImgUrl("min.png");
		btn_close_img= con.getImgUrl("close.png");


		//========================================
		//�˵���
		//========================================
		img_menu = con.getImgUrl("menu_bg.png");//�˵�����
		img_info = con.getImgUrl("info_bg.png");//��Ϣ����

		//========================================
		// ���ұ���
		//========================================
		img_m = con.getImgUrl("exam_b_line.png");//����
		img_m_w = img_m.getIconWidth();//����

		//========================================
		// β��
		//========================================
		img_b_l = con.getImgUrl("exam_b_l.png");//��߽�
		img_b_l_w = img_b_l.getIconWidth();
		img_b_bg = con.getImgUrl("exam_b_bg.png");// �м�
		img_b_bg_w = img_b_bg.getIconWidth();
		img_b_r = con.getImgUrl("exam_b_r.png");//�ұ߽�
		img_b_r_w = img_b_r.getIconWidth();
	}
	// ==================================
	// �����Ƥ������
	// ==================================
	public void paintComponent(Graphics g)
	{
		//===========================================
		//��ʼ��Ƥ��
		//===========================================

		//==========================
		// �����ͷ
		g.drawImage(img_t_l.getImage(), 0, 0, this);
		// ����߱���
		g.drawImage(img_t_bg.getImage(), 0, 0, img_t_bg_w, img_t_bg.getIconHeight(), this);
		// ���ͼ��
		g.drawImage(con.getImgUrl("icon1.png").getImage(), 20, 3, 34,16, this);
		// ��������
		g.setColor(Color.white);
		//g.drawString("VSH", 40, 15);
		g.setFont(new Font("Verdana", 0, 12));
		// ��������,��Ϊ��߱���������ֱ������,�������ﲻ��Ҫ����߱߿��ȼ���
		g.drawImage(img_t_mid.getImage(), img_t_bg_w, 0, img_t_mid_w, img_t_bg.getIconHeight(), this);
		// �ұ߱���
		// ��ʼ��
		int spot = img_t_bg_w + img_t_mid_w;
		int epot = this.getWidth() - img_t_r_w;
		g.drawImage(img_t_rbg.getImage(), spot, 0, epot, img_t_rbg.getIconHeight(), this);
		// ���ұ�,ʹ��������ͼ����յ�
		g.drawImage(img_t_r.getImage(), epot, 0, this);

		//=============================
		//���˵�����
		g.drawImage(img_menu.getImage(),0,img_t_l.getIconHeight(),this.getWidth(),img_menu.getIconHeight(),this);
		//����Ϣ��
		g.drawImage(img_info.getImage(),0,img_t_l.getIconHeight()+img_menu.getIconHeight(),this.getWidth(),img_info.getIconHeight(),this);

		// ============================
		// �����ұ���
		g.drawImage(img_m.getImage(), 0, img_t_l.getIconHeight()+img_menu.getIconHeight()+img_info.getIconHeight(), img_m_w, this.getHeight()- img_t_l.getIconHeight(), this);
		g.drawImage(img_m.getImage(), this.getWidth() - img_m.getIconWidth(),img_t_l.getIconHeight()+img_menu.getIconHeight()+img_info.getIconHeight(), img_m_w, this.getHeight()- img_t_l.getIconHeight(), this);

		// ============================
		// β��
		g.drawImage(img_b_l.getImage(), 0, this.getHeight()- img_b_l.getIconHeight(), this);
		g.drawImage(img_b_bg.getImage(), img_b_l_w, this.getHeight()- img_b_bg.getIconHeight(), this.getWidth()- img_b_r.getIconWidth(), img_b_r.getIconHeight(), this);
		g.drawImage(img_b_r.getImage(), this.getWidth()- img_b_r.getIconWidth(), this.getHeight()- img_b_r.getIconHeight(), this);
	}
}