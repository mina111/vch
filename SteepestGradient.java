
public class SteepestGradient extends LowLevelHeuristic{

	ImprovingEqual ie = new ImprovingEqual();
	int[] generateNewSoluation(int[] candidateSoluation) {
		// TODO Auto-generated method stub
		int[] optimumSoluation = new int[candidateSoluation.length ];
		for(int i=0;i<candidateSoluation.length ;i++){
			
			optimumSoluation[i] = candidateSoluation[i];
		}
		
		for(int i=0;i<candidateSoluation.length ;i++){
			if(candidateSoluation[i]==1)
				candidateSoluation[i]=0;
			else
				candidateSoluation[i]=1;
			
			if(ie.checkIfAcceptance(optimumSoluation,candidateSoluation)){
				for(int j=0;j<candidateSoluation.length;j++){
					
					optimumSoluation[i] = candidateSoluation[i];
				}	
			}
			if(candidateSoluation[i]==1)
				candidateSoluation[i]=0;
			else
				candidateSoluation[i]=1;			
			
				
		}
		
		return optimumSoluation;
	}
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Steepest Gradient";
	}



}
