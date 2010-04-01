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
 *  This class plays the role to produce the low-level heuristic.
 */
public class LowLevelHeuristicsFactory {

	/**
	 * produce the instance of low-level heuristic with specified string and instance of hyper-heuristic
	 * @param name
	 * @param hyperHeuristic
	 * @return the instance of low-level heuristic
	 */
	public static LowLevelHeuristic createLowLevelHeuristic(String name, HyperHeuristic hyperHeuristic){
		if(name.equals("Reverse"))
			return new Reverse();
		else if(name.equals("Inverse"))
			return new Inverse();
		else if(name.equals("Shift"))
			return new Shift();
		else if(name.equals("Flip One Bit"))
			return new FlipOneBit();
		else if(name.equals("Steepest Gradient"))
			return new SteepestGradient(hyperHeuristic);
		else 
			return null;

	}

}
