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
 *  This class plays the role to produce the acceptance method.
 */
public class AcceptanceMethodFactory {

	/**
	 * produce the instance of acceptance method for acceptance method with specified string and instance of hyper-heuristic
	 * @param name   the name acceptance method 
	 * @param hyperHeuristic the instance of hyper-heuristic
	 * @return   a acceptance method instance
	 */
	public static AcceptanceMethod createAcceptanceMethod(String name,HyperHeuristic hyperHeuristic){
		if(name.equals("All Moves Accepted"))
			return new AllMovesAccepted();
		else if(name.equals("Only Improving"))
			return new OnlyImproving(hyperHeuristic);
		else if(name.equals("Improving or Equal"))
			return new ImprovingEqual(hyperHeuristic);
		else 
			return null;

	}
}
