import java.util.Random;

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

public class SimpleRandom extends HeuristicsSelection {

	/**
	 * create an random instance
	 */
	Random random = new Random();
	/**
	 *   the instance of HyperHeuristic
	 */
	HyperHeuristic hyperHeuristic;
	
	/**
	 * Defines an SimpleRandom object with a instance of HyperHeuristic 
	 * @param hyperHeuristic
	 */
	public SimpleRandom(HyperHeuristic hyperHeuristic) {
		// TODO Auto-generated constructor stub
		this.hyperHeuristic = hyperHeuristic;
	}

	/**
	 * return the name as "Simple Random"
	 */
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Simple Random";
	}

	/**
	 * Override the method to get a low-level heuristic by simple random
	 */
	@Override
	LowLevelHeuristic selectLowLevelHeuristic(int[] candiateSolution) {
		// TODO Auto-generated method stub
		return hyperHeuristic.lowLevelHeuristics.get(random.nextInt(hyperHeuristic.lowLevelHeuristics.size()));
		
	}
	
	
}
