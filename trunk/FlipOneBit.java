
public class FlipOneBit extends LowLevelHeuristic{
	int postion;
	@Override
	int[] generateNewSolution(int[] candidateSolution) {

		int newSolution[] = new int[candidateSolution.length];
		for(int i =0;i<candidateSolution.length;i++)
			newSolution[i]=candidateSolution[i];
		// TODO Auto-generated method stub
		postion = getStartPostion();
		if(newSolution[postion]==1){
			newSolution[postion] = 0;
		}else{
			newSolution[postion] = 1;
		}
		return newSolution;
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Flip One Bit";
	}

}
