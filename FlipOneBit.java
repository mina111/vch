
public class FlipOneBit extends LowLevelHeuristic{

	@Override
	int[] generateNewSoluation(int[] candidateSoluation) {
		
		int newSoluatuon[] = new int[candidateSoluation.length];
		for(int i =0;i<candidateSoluation.length;i++)
			newSoluatuon[i]=candidateSoluation[i];
		// TODO Auto-generated method stub
		int postion = getStartPostion();
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
