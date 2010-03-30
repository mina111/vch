
public class SteepestGradient extends LowLevelHeuristic{

	ImprovingEqual ie = new ImprovingEqual();
	int optimumSoluationIndex = 0;
	int[] generateNewSoluation(int[] candidateSoluation) {
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
				optimumSoluationIndex = i;	
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
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Steepest Gradient";
	}



}
