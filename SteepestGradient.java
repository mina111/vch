
public class SteepestGradient extends LowLevelHeuristic{
	HyperHeuristic hyperHeuristic;
	ImprovingEqual ie;
	int optimumSolutionIndex = 0;
	public SteepestGradient(HyperHeuristic hyperHeuristic) {
		// TODO Auto-generated constructor stub
		this.hyperHeuristic = hyperHeuristic;
		ie = new ImprovingEqual(hyperHeuristic);
	}
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
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Steepest Gradient";
	}



}
