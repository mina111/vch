
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
 *  This class abstract the method of heuristics selection
 */
public abstract class HeuristicsSelection {
	
	/**
	 * get the name of concrete heuristics selection
	 * @return  the name of concrete heuristics selection 
	 */
	abstract String getName();
	
	/**
	 *  select a low-level heuristic
	 * @param candiateSolution
	 * @return a instance of low-level heuristic
	 */
	abstract LowLevelHeuristic selectLowLevelHeuristic(int[] candiateSolution);
	
	
}
