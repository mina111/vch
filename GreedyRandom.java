
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
 * @author	   	Alexander Jermstad (asj08u) 
 * 			
 */

/**
 *  the class is  the heuristics selection greedy random which extends from HeuristicsSelection
 */
public class GreedyRandom extends HeuristicsSelection {
	/**
	 *  the instance of HyperHeuristic
	 */
	HyperHeuristic hyperHeuristic;
	/**
	 *  to store the optimum solution
	 */
	int[] optimumSolution;
	/**
	 *  to store the histories of new solution
	 */
	int[][] histories ;
	/**
	 *  the index of optimum solution
	 */
	int optimumSolutionIndex;
	int SGoptimumSolutionIndex;
	/**
	 * Defines an GreedyRandom object with a instance of HyperHeuristic 
	 * @param hyperHeuristic
	 */
	public GreedyRandom(HyperHeuristic hyperHeuristic) {
		// TODO Auto-generated constructor stub
		this.hyperHeuristic = hyperHeuristic;
		histories = new int[hyperHeuristic.lowLevelHeuristics.size()][HyperHeuristic.DIGIT_NUM];
	}

	
	/**
	 *  return the name as "Greedy Random"
	 */
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Greedy Random";
	}

	/**
	 * Override the method to get a low-level heuristic by greedy random
	 */
	@Override
	LowLevelHeuristic selectLowLevelHeuristic(int[] candiateSolution) {
		// TODO Auto-generated method stub
		LowLevelHeuristic lowLevelHeuristic = hyperHeuristic.lowLevelHeuristics.get(0);
		optimumSolutionIndex = 0;
		optimumSolution = lowLevelHeuristic.generateNewSolution(candiateSolution);
		histories[0] = optimumSolution;
		for(int i=1; i<hyperHeuristic.lowLevelHeuristics.size();i++){
			int[] newSolution = hyperHeuristic.lowLevelHeuristics.get(i).generateNewSolution(candiateSolution);
			if(hyperHeuristic.lowLevelHeuristics.get(i).getName().equals("Steepest Gradient")){
				SGoptimumSolutionIndex = ((SteepestGradient)hyperHeuristic.lowLevelHeuristics.get(i)).optimumSolutionIndex;
			}
			histories[i] = newSolution;
			if( hyperHeuristic.function.evaluate(newSolution) <= hyperHeuristic.function.evaluate(optimumSolution)    ){
				lowLevelHeuristic = hyperHeuristic.lowLevelHeuristics.get(i);
				optimumSolution = newSolution;
				optimumSolutionIndex = i;
			}
				
				
		}
		return lowLevelHeuristic;
	}

}
