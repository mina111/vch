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
 *  This class plays the role to produce the heuristics selection.
 */
public class HeuristicsSelectionFactory {
	/**
	 * produce the instance of the heuristics selection with specified string and instance of hyper-heuristic
	 * @param name
	 * @param hyperHeuristic
	 * @return a low-level heuristic
	 */
	public static HeuristicsSelection createHeuristicsSelection(String name, HyperHeuristic hyperHeuristic){
		if(name.equals("Simple Random"))
			return new SimpleRandom(hyperHeuristic);
		else if(name.equals("Greedy Random"))
			return new GreedyRandom(hyperHeuristic);
		else if(name.equals("Reinforcement Learning"))
			return new ReinforcementLearning(hyperHeuristic);
		else 
			return null;

	}
}
