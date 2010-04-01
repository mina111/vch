
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
 *  This class abstract the method for all acceptance methods
 */
public abstract class AcceptanceMethod {

	/**
	 *  get the name of accept method.
	 * @return
	 */
	abstract String getName();
	
	/**
	 *  check if accept the new solution or not
	 * @param candidate_Solution
	 * @param new_Solution
	 * @return a boolean value to decide whether accept the new solution or not
	 */
	abstract boolean checkIfAcceptance(int[] candidate_Solution, int[] new_Solution);


}
