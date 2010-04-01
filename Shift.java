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
	int[] generateNewSolution(int[] candidateSoluation) {
		modifiedDigitNum = getModifiedDigitNum();
		startPostion = getStartPostion();
		endPostion = getEndPostion(startPostion,modifiedDigitNum);
		// TODO Auto-generated method stub
		int newSoluatuon[] = new int[candidateSoluation.length];
		for(int i =0;i<candidateSoluation.length;i++)
			newSoluatuon[i]=candidateSoluation[i];

		if(startPostion<endPostion){
			int temp = newSoluatuon[startPostion];
			for(int i = startPostion;i<endPostion;i++){
				newSoluatuon[i]= newSoluatuon[i+1];
			}
			newSoluatuon[endPostion] = temp;
			return newSoluatuon;
		}else{
			int temp = newSoluatuon[startPostion];
			for(int i = startPostion;i<HyperHeuristic.DIGIT_NUM ;i++){
				if(i==HyperHeuristic.DIGIT_NUM -1){
					newSoluatuon[i]=newSoluatuon[0];
				}else{
					newSoluatuon[i]= newSoluatuon[i+1];
				}
			}
			for(int i = 0;i<endPostion;i++){				
				newSoluatuon[i]= newSoluatuon[i+1];
			}
			newSoluatuon[endPostion] = temp;
			return newSoluatuon;
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
