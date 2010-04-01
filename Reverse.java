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
 *  the class is  the low-level heuristics Reverse which extends from LowLevelHeuristic
 */
public class Reverse extends LowLevelHeuristic{
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
	int endPostion;	
	/**
	 *  Override the method to generate new solution by a selected area being reverse.
	 */
	@Override
	int[] generateNewSolution(int[] candidateSoluation) {
		modifiedDigitNum = getModifiedDigitNum();
		startPostion = getStartPostion();
		endPostion = getEndPostion(startPostion,modifiedDigitNum);
		int tempStartPostion = startPostion;
		int tempEndPostion = endPostion;	
		// TODO Auto-generated method stub
		int newSoluatuon[] = new int[candidateSoluation.length];
		for(int i =0;i<candidateSoluation.length;i++)
			newSoluatuon[i]=candidateSoluation[i];
		
		if(startPostion<endPostion){
			while(startPostion<endPostion){
				int temp = newSoluatuon[startPostion];
				newSoluatuon[startPostion] = newSoluatuon[endPostion];
				newSoluatuon[endPostion] = temp;
				startPostion++;
				endPostion--;
			}
			startPostion = tempStartPostion;
			endPostion = tempEndPostion;
			for(int i=0;i<newSoluatuon.length;i++)
				System.out.print(candidateSoluation[i]);
			System.out.println("New");
			for(int i=0;i<newSoluatuon.length;i++)
				System.out.print(newSoluatuon[i]);
			System.out.println("");
			return newSoluatuon;
		}else{
			int middlePostion = startPostion + modifiedDigitNum/2;
			if(middlePostion <= HyperHeuristic.DIGIT_NUM ){
				while(startPostion < middlePostion){
					int temp = newSoluatuon[startPostion];
					newSoluatuon[startPostion] = newSoluatuon[endPostion];
					newSoluatuon[endPostion] = temp;
					startPostion++;
					if(endPostion==0){
						endPostion = HyperHeuristic.DIGIT_NUM-1 ;
					}else{
						endPostion--;
					}
				}
			}else{
				while(endPostion > (middlePostion - HyperHeuristic.DIGIT_NUM -1)){
					int temp = newSoluatuon[startPostion];
					newSoluatuon[startPostion] = newSoluatuon[endPostion];
					newSoluatuon[endPostion] = temp;
					if(startPostion==HyperHeuristic.DIGIT_NUM-1 ){
						startPostion=0;
					}else{
						startPostion++;
					}
					endPostion--;
				}				
			}
			startPostion = tempStartPostion;
			endPostion = tempEndPostion;
			return newSoluatuon;
		}
		
	}
	
	/**
	 *  return the name as "reverse"
	 */
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Reverse";
	}


}