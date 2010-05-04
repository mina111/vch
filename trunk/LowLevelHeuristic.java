import java.util.Random;

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
 *  the class abstract the attributes and methods of low-level heuristic
 */
public abstract  class LowLevelHeuristic {
	
	/**
	 *  the upper boundary percent for calculating upperBoundModifiedDigitNum
	 */
	final int UPPER_BOUND_PERCENT = 40;
	/**
	 *  the lower boundary percent for calculating lowerBoundModifiedDigitNum
	 */
	final int LOWER_BOUND_PERCENT = 20;	

	/**
	 * the upper boundary  how many bits will be changed
	 */
	int upperBoundModifiedDigitNum = 0;
	
	/**
	 * the lower boundary  how many bits will be changed
	 */
	int lowerBoundModifiedDigitNum = 0;
	
	/**
	 *  create an instance of Random for generating index
	 */
	Random random = new Random();
	
	/**
	 *  Initial the low-level heuristics
	 */
	public LowLevelHeuristic(){
		setUpperBoundModifiedDigitNum();
		setLowerBoundModifiedDigitNum();
	}
	
	/**
	 *  set the upperBoundModifiedDigitNum
	 */
	void setUpperBoundModifiedDigitNum() {
		upperBoundModifiedDigitNum = HyperHeuristic.DIGIT_NUM * UPPER_BOUND_PERCENT /100;
	}

	/**
	 * set the lowerBoundModifiedDigitNum
	 */
	void setLowerBoundModifiedDigitNum() {
		lowerBoundModifiedDigitNum = HyperHeuristic.DIGIT_NUM  * LOWER_BOUND_PERCENT /100;
	}
	
	/**
	 * get the number of positions changed
	 * @return the number of positions changed
	 */
	int getModifiedDigitNum(){
		return (random.nextInt(upperBoundModifiedDigitNum-lowerBoundModifiedDigitNum+1))+ lowerBoundModifiedDigitNum;
	}
	
	/**
	 * get the start position 
	 * @return the start position 
	 */
	int getStartPostion(){
		return random.nextInt(HyperHeuristic.DIGIT_NUM );
	}
	

	/**
	 * get the end position 
	 * @param startPostion
	 * @param modifiedDigitNum
	 * @return the end position 
	 */
	int getEndPostion(int startPostion,int modifiedDigitNum){
		if(startPostion+modifiedDigitNum-1<=HyperHeuristic.DIGIT_NUM -1){
			return startPostion+modifiedDigitNum-1;
		}else{
			return modifiedDigitNum - (HyperHeuristic.DIGIT_NUM  - startPostion) -1;
		}		
	}
	/**
	 *  return the concrete  low-level heuristic's name
	 * @return the concrete  low-level heuristic's name
	 */
	abstract String getName();
	
	/**
	 * return the new solution
	 * @param candidateSolution
	 * @return the new solution
	 */
	abstract int[] generateNewSolution(int[] candidateSolution);
	
}