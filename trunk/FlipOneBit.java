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
 *  the class is  the low-level heuristics flip one bit which extends from LowLevelHeuristic
 */
public class FlipOneBit extends LowLevelHeuristic{
	
	/**
	 *  the variable to store the position flipped
	 */
	int postion;
	/**
	 *  Override the method to generate new solution by flipping one bit in candidate solution
	 */
	@Override
	int[] generateNewSolution(int[] candidateSolution) {
		
		int newSolution[] = new int[candidateSolution.length];
		for(int i =0;i<candidateSolution.length;i++)
			newSolution[i]=candidateSolution[i];
		// TODO Auto-generated method stub
		postion = getStartPostion();
		if(newSolution[postion]==1){
			newSolution[postion] = 0;
		}else{
			newSolution[postion] = 1;
		}
		return newSoluatuon;
	}

	/**
	 *  return the name as "Flip One Bit"
	 */
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Flip One Bit";
	}

}
