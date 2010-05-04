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
 *  the class is  the low-level heuristics Shift which extends from LowLevelHeuristic
 */
public class Shift extends LowLevelHeuristic{
	/**
	 *  store the number of positions changed
	 */
	int modifiedDigitNum ;
	/**
	 *  store the start position
	 */
	int startPostion ;
	/**
	 *  store the end position
	 */
	int endPostion ;
	/**
	 *  Override the method to generate new solution by a selected area being shift.
	 */
	@Override
	int[] generateNewSolution(int[] candidateSolution) {
		modifiedDigitNum = getModifiedDigitNum();
		startPostion = getStartPostion();
		endPostion = getEndPostion(startPostion,modifiedDigitNum);
		// TODO Auto-generated method stub
		int newSolution[] = new int[candidateSolution.length];
		for(int i =0;i<candidateSolution.length;i++)
			newSolution[i]=candidateSolution[i];

		if(startPostion<endPostion){
			int temp = newSolution[startPostion];
			for(int i = startPostion;i<endPostion;i++){
				newSolution[i]= newSolution[i+1];
			}
			newSolution[endPostion] = temp;
			return newSolution;
		}else{
			int temp = newSolution[startPostion];
			for(int i = startPostion;i<HyperHeuristic.DIGIT_NUM ;i++){
				if(i==HyperHeuristic.DIGIT_NUM -1){
					newSolution[i]=newSolution[0];
				}else{
					newSolution[i]= newSolution[i+1];
				}
			}
			for(int i = 0;i<endPostion;i++){				
				newSolution[i]= newSolution[i+1];
			}
			newSolution[endPostion] = temp;
			return newSolution;
		}
		
	}
	/**
	 *  return the name as "Shift"
	 */
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Shift";
	}

}
