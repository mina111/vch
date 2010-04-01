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
 *  the class is  the low-level heuristics Steepest Gradient which extends from LowLevelHeuristic
 */
public class SteepestGradient extends LowLevelHeuristic{
	/**
	 *  the instance of hyper-heuristic
	 */
	HyperHeuristic hyperHeuristic;
	/**
	 *  the ImprovingEqual instance 
	 */
	ImprovingEqual ie;
	/**
	 *  the index of optimum solution
	 */
	int optimumSolutionIndex = 0;
	
	/**
	 *  Defines an SteepestGradient object with a instance of HyperHeuristic
	 * @param hyperHeuristic
	 */
	public SteepestGradient(HyperHeuristic hyperHeuristic) {
		// TODO Auto-generated constructor stub
		this.hyperHeuristic = hyperHeuristic;
		ie = new ImprovingEqual(hyperHeuristic);
	}
	
	/**
	 *  Override the method to generate new solution by SteepestGradient.
	 */
	int[] generateNewSolution(int[] candidateSoluation) {
		// TODO Auto-generated method stub
		int[] optimumSoluation = new int[candidateSoluation.length ];
		for(int i=0;i<candidateSoluation.length ;i++){
			
			optimumSoluation[i] = candidateSoluation[i];
		}
		
		if(optimumSoluation[0]==0)
			optimumSoluation[0]=1;
		else
			optimumSoluation[0]=0;
		for(int i=1;i<candidateSoluation.length ;i++){
			if(candidateSoluation[i]==1)
				candidateSoluation[i]=0;
			else
				candidateSoluation[i]=1;
	
			if(ie.checkIfAcceptance(optimumSoluation,candidateSoluation)){
				optimumSolutionIndex = i;	
				for(int j=0;j<candidateSoluation.length;j++){
					
					optimumSoluation[j] = candidateSoluation[j];
				}	
			}
			if(candidateSoluation[i]==1)
				candidateSoluation[i]=0;
			else
				candidateSoluation[i]=1;			
			
				
		}
		
		return optimumSoluation;
	}
	
	/**
	 *  return the name as "Steepest Gradient"
	 */
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Steepest Gradient";
	}



}
