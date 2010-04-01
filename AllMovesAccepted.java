
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
 *  The class is a kind of acceptance method which implements from AcceptanceMethod
 */
public class AllMovesAccepted extends AcceptanceMethod {

	/**
	 *  acceptance all new solution
	 */
	@Override
	boolean checkIfAcceptance(int[] candidate_Solution, int[] new_Solution) {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 *  get the name of the acceptance method
	 */
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "All Moves Accepted";
	}

}
