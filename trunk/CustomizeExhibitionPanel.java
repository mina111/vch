import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;



public class CustomizeExhibitionPanel extends JPanel{
	//���
	
	int width,height;
	//��,�з���,�ұ�����Panel
	CustomizePanel p_left;
	JPanel  p_bar , p_right;
	//�ұ��õ��Ĺ���
	//JScrollPane scrollPane ; 
	//=====================
	//���ò�����
	Config con ;
	//=====================
	//�ָ�����ť
	ImageIcon btn_split_img ;
	//JButton btn_split ;
	//=====================
	//�������
	VSHMainFrame frame;
	
	

	InitalMenuPanel initalMenuPanel;
	MyLabel flowchart;
	
	AnimationPanel animationPanel;
	//=====================
	//��ʼ��
	//=====================
	CustomizeExhibitionPanel(VSHMainFrame frame,int width,int height)
	{
		//=============================

		this.width = width;
		this.height = height;
		this.frame = frame;
		initPanel();
	}
	//=====================
	//��ʼ����ز���
	//=====================
	private void initPanel()
	{
		//================
		//������
		con = new Config();
		btn_split_img = con.getImgUrl("btn_split_l.png");//��Ϊ��JButtonǰ��Ҫ��,������ǰ����ʵ����
		int splitbarwidth = con.getSplitBarWidth() ; //�����ļ��ж�ȡ�ָ����Ŀ��//int leftwidth = (width-splitbarwidth)*20/100 ; //�������Ϊ�ܿ��20%
		int leftwidth = con.getLeftMenuWidth() ;
		int rightwidth = width - splitbarwidth - leftwidth ; // �����ұ�ʣ����
		//================
		//�����ʽ
		this.setPreferredSize(new Dimension(width,height));
		this.setOpaque(false);
		this.setLayout(con.getFlowLayout(0,0,0));//0��,1 ��,2��
		//================
		//������
		p_left = new CustomizePanel(frame, leftwidth, height);
		//p_left.setPreferredSize(new Dimension(leftwidth,height));

		//p_left.setBackground(con.getLeftBgColor());
		p_left.setLayout(con.getFlowLayout(1,0,0)); //���ж���;//�����,��ֱ���Ϊ20,ˮƽ���Ϊ0;
		this.add(p_left);
		//================
		//�м�ָ���
		p_bar = new JPanel();
		p_bar.setPreferredSize(new Dimension(splitbarwidth,height));
		p_bar.setBackground(con.getSplitBgColor());
		p_bar.setLayout(con.getFlowLayout(1, 0, (height - btn_split_img.getIconHeight())/2-30));
		this.add(p_bar);

		//================
		//�ұ����
		/*
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(rightwidth,height));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.getVerticalScrollBar().setBlockIncrement(100);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
		scrollPane.getHorizontalScrollBar().setBlockIncrement(100);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		this.add(scrollPane);
		*/

		
		//=================
		//�ұ߹������������,�Ժ���Ҫ��嶼��ӵ�������
		p_right = new JPanel();
		p_right.setPreferredSize(new Dimension(rightwidth,height));
		//p_right.setBackground(con.getMainColor());
		p_right.setLayout(new BorderLayout());
		initalMenuPanel = new InitalMenuPanel(rightwidth,con,frame);
		p_right.add(initalMenuPanel,BorderLayout.NORTH);
		animationPanel = new AnimationPanel(frame, con);
		animationPanel.setBackground(new Color(153,153,153));
		flowchart = new MyLabel(con,"flowchart.png",rightwidth,height-initalMenuPanel.getHeight());
		animationPanel.add(flowchart);
		p_right.add(animationPanel,BorderLayout.CENTER);
		this.add(p_right);
		
	//	scrollPane.setViewportView(p_right);
		
		
		
	}


}
