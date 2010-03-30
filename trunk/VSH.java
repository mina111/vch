////////////////////////////////////////////////////////////
/////
/////  A Visualisation Tool for 
/////  Selection Hyper-Heuristics
/////
/////  http://code.google.com/p/vch/
/////
/////  Group:			gp09-exo
/////  FullNames:		Thomas Barton (txb18u)
/////					Zhang Chao (cxz09u)
/////					Ben Jenkinson (bxj08u)
/////					Alexander Jermstad (asj08u)
/////					Lao Jingqi (jxl29u)
/////
/////  Module:			G52GRP, University of Nottingham
/////
////////////////////////////////////////////////////////////


import java.util.Random;


public class VSH {
	VSHMainFrame frame;
	HyperHeuristicBuilder HHBuilder;
	HyperHeuristicDirector HHDirector;
	Random random = new Random();
	int candidateSolution [] = new int[HyperHeuristic.DIGIT_NUM];
	int newSolution[] = new int[candidateSolution.length];
	LowLevelHeuristic lowLevelHeuristic;
	int count = 0;
	int [][] history = new int[1000][15];
	int sleepTime = 10;
	String functionNmae = "f(x)=x^2", acceptanceMethodName = "Improving or Equal", heuristicSelectionName = "Simple Random";
	String [] lowLevelHeuristicNames = {"Reverse","Inverse","Shift","Flip One Bit","Steepest Gradient"};;
	public static void main(String[] args){

		new VSH().start();
	}
	
	
	void start(){
		frame =new VSHMainFrame("VSH",this);
		for(;;){
			if(!frame.start){
				//System.out.println("AAA");
				frame.stop();				
			}
			//System.out.println("bbb");
			count++;
			calculate();
			
			while(!frame.panel.m_panel.animationPanel.animationFinished){
				if(frame.pause){
					frame.stop();	
				}
				if(frame.stop){
					frame.panel.m_panel.animationPanel.reset();
					break;
				}
				exhibition();
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			acceptanceCheck();
			//System.out.println("ccc");
		}
	}
	
	
	void buildHyperHeuristic(){
		
		HHBuilder= new HyperHeuristicBuilder();
		HHDirector = new HyperHeuristicDirector(); 
		HHDirector.construct(HHBuilder, functionNmae, acceptanceMethodName, lowLevelHeuristicNames, heuristicSelectionName);
		for(int i =0; i<candidateSolution.length;i++){
			candidateSolution[i] = random.nextInt(2);
			history[0][i]=candidateSolution[i];
		}
		
		
	}
	
	void calculate(){
		lowLevelHeuristic = HyperHeuristic.heuristicsSelection.selectLowLevelHeuristic(candidateSolution);
		if(HyperHeuristic.heuristicsSelection.getName().equals("Greedy Random")){
			newSolution = ((GreedyRandom)HyperHeuristic.heuristicsSelection).optimumSoluation;
		}else{
			newSolution= lowLevelHeuristic.generateNewSoluation(candidateSolution);
			
		}
		for(int i=0;i<newSolution.length;i++){
			history[count][i] = newSolution[i];
		}
		frame.panel.m_panel.animationPanel.animationFinished = false;
		
	}
	
	void exhibition(){
		frame.panel.m_panel.animationPanel.repaint();
	}
	void acceptanceCheck(){
		if(HyperHeuristic.acceptanceMethod.checkIfAcceptance(candidateSolution, newSolution)){
			candidateSolution = newSolution;
			if(HyperHeuristic.heuristicsSelection.getName().equals("Reinforcement Learning"))
				((ReinforcementLearning)HyperHeuristic.heuristicsSelection).incrementScore();
		}else{
			if(HyperHeuristic.heuristicsSelection.getName().equals("Reinforcement Learning"))
				((ReinforcementLearning)HyperHeuristic.heuristicsSelection).decrementScore();
		}
	}
}


