
public class FlipOneBit extends LowLevelHeuristic{
	int postion;
	@Override
	int[] generateNewSolution(int[] candidateSolution) {
		
		int newSoluatuon[] = new int[candidateSolution.length];
		for(int i =0;i<candidateSolution.length;i++)
			newSoluatuon[i]=candidateSolution[i];
		// TODO Auto-generated method stub
		postion = getStartPostion();
		if(newSoluatuon[postion]==1){
			newSoluatuon[postion] = 0;
		}else{
			newSoluatuon[postion] = 1;
		}
		return newSoluatuon;
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Flip One Bit";
	}

}
