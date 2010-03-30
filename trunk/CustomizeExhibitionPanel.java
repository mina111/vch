import java.awt.BorderLayout;



public class CustomizeExhibitionPanel extends JPanel{

	
	int width,height;
	CustomizePanel p_left;
	JPanel  p_bar , p_right;
	Config con ;
	ImageIcon btn_split_img ;
	VSHMainFrame frame;
	
	

	InitalMenuPanel initalMenuPanel;
	MyLabel flowchart;
	
	AnimationPanel animationPanel;
	CustomizeExhibitionPanel(VSHMainFrame frame,int width,int height)
	{

		this.width = width;
		this.height = height;
		this.frame = frame;
		initPanel();
	}

	private void initPanel()
	{

		con = new Config();
		btn_split_img = con.getImgUrl("btn_split_l.png");
		int splitbarwidth = con.getSplitBarWidth() ; 
		int leftwidth = con.getLeftMenuWidth() ;
		int rightwidth = width - splitbarwidth - leftwidth ; 

		this.setPreferredSize(new Dimension(width,height));
		this.setOpaque(false);
		this.setLayout(con.getFlowLayout(0,0,0));

		p_left = new CustomizePanel(frame, leftwidth, height);

		p_left.setLayout(con.getFlowLayout(1,0,0));
		this.add(p_left);

		p_bar = new JPanel();
		p_bar.setPreferredSize(new Dimension(splitbarwidth,height));
		p_bar.setBackground(con.getSplitBgColor());
		p_bar.setLayout(con.getFlowLayout(1, 0, (height - btn_split_img.getIconHeight())/2-30));
		this.add(p_bar);


		p_right = new JPanel();
		p_right.setPreferredSize(new Dimension(rightwidth,height));
		p_right.setLayout(new BorderLayout());
		initalMenuPanel = new InitalMenuPanel(rightwidth,con,frame);
		p_right.add(initalMenuPanel,BorderLayout.NORTH);
		animationPanel = new AnimationPanel(frame, con);
		animationPanel.setBackground(new Color(153,153,153));
		flowchart = new MyLabel(con,"flowchart.png",rightwidth,height-initalMenuPanel.getHeight());
		animationPanel.add(flowchart);
		p_right.add(animationPanel,BorderLayout.CENTER);
		this.add(p_right);
		
		
		
		
	}


}