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
 *  the class is  the low-level heuristics Inverse which extends from LowLevelHeuristic
 */
public class Inverse extends LowLevelHeuristic{
	/**
	 *  store the number of positions changed
	 */
	int modifiedDigitNum ;
	/**
	 *  store the start position
	 */
	int startPostion;
	/**
	 *  store the end position
	 */
	int endPostion ;
	/**
	 *  Override the method to generate new solution by a selected area being inverse.
	 */
	@Override
	int[] generateNewSolution(int[] candidateSoluation) {
		// TODO Auto-generated method stub
		modifiedDigitNum = getModifiedDigitNum();
		startPostion = getStartPostion();
		endPostion = getEndPostion(startPostion,modifiedDigitNum);
		int newSoluatuon[] = new int[candidateSoluation.length];
		for(int i =0;i<candidateSoluation.length;i++)
			newSoluatuon[i]=candidateSoluation[i];

		if(startPostion<endPostion){
			for(int i= startPostion;i<=endPostion;i++){
				if(newSoluatuon[i] == 0){
					newSoluatuon[i] = 1;
				}else{
					newSoluatuon[i] = 0;					
				}
			}
			
			return newSoluatuon;
		}else{
			for(int i= startPostion;i<newSoluatuon.length;i++){
				if(newSoluatuon[i] == 0){
					newSoluatuon[i] = 1;
				}else{
					newSoluatuon[i] = 0;					
				}
			}
			for(int i=0; i<=endPostion;i++ ){
				if(newSoluatuon[i] == 0){
					newSoluatuon[i] = 1;
				}else{
					newSoluatuon[i] = 0;					
				}
			}
			return newSoluatuon;
		}
		
	}

	/**
	 *  return the name as "Inverse"
	 */
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Inverse";
	}
	
	

	
}
