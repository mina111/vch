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
	int[] generateNewSolution(int[] candidateSolution) {
		// TODO Auto-generated method stub
		int[] optimumSolution = new int[candidateSolution.length ];
		for(int i=0;i<candidateSolution.length ;i++){
			
			optimumSolution[i] = candidateSolution[i];
		}
		
		if(optimumSolution[0]==0)
			optimumSolution[0]=1;
		else
			optimumSolution[0]=0;
		for(int i=1;i<candidateSolution.length ;i++){
			if(candidateSolution[i]==1)
				candidateSolution[i]=0;
			else
				candidateSolution[i]=1;
	
			if(ie.checkIfAcceptance(optimumSolution,candidateSolution)){
				optimumSolutionIndex = i;	
				for(int j=0;j<candidateSolution.length;j++){
					
					optimumSolution[j] = candidateSolution[j];
				}	
			}
			if(candidateSolution[i]==1)
				candidateSolution[i]=0;
			else
				candidateSolution[i]=1;			
			
				
		}
		
		return optimumSolution;
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
