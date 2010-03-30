
public class Shift extends LowLevelHeuristic{
	int modifiedDigitNum ;
	int startPostion ;
	int endPostion ;
	@Override
	int[] generateNewSolution(int[] candidateSolution) {
		modifiedDigitNum = getModifiedDigitNum();
		startPostion = getStartPostion();
		endPostion = getEndPostion(startPostion,modifiedDigitNum);
		// TODO Auto-generated method stub
		int newSoluatuon[] = new int[candidateSolution.length];
		for(int i =0;i<candidateSolution.length;i++)
			newSoluatuon[i]=candidateSolution[i];

		if(startPostion<endPostion){
			int temp = newSoluatuon[startPostion];
			for(int i = startPostion;i<endPostion;i++){
				newSoluatuon[i]= newSoluatuon[i+1];
			}
			newSoluatuon[endPostion] = temp;
			return newSoluatuon;
		}else{
			int temp = newSoluatuon[startPostion];
			for(int i = startPostion;i<HyperHeuristic.DIGIT_NUM ;i++){
				if(i==HyperHeuristic.DIGIT_NUM -1){
					newSoluatuon[i]=newSoluatuon[0];
				}else{
					newSoluatuon[i]= newSoluatuon[i+1];
				}
			}
			for(int i = 0;i<endPostion;i++){				
				newSoluatuon[i]= newSoluatuon[i+1];
			}
			newSoluatuon[endPostion] = temp;
			return newSoluatuon;
		}
		
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Shift";
	}

}
