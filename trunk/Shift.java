
public class Shift extends LowLevelHeuristic{

	@Override
	int[] generateNewSoluation(int[] candidateSoluation) {
		// TODO Auto-generated method stub
		int newSoluatuon[] = new int[candidateSoluation.length];
		for(int i =0;i<candidateSoluation.length;i++)
			newSoluatuon[i]=candidateSoluation[i];
		int modifiedDigitNum = getModifiedDigitNum();
		int startPostion = getStartPostion();
		int endPostion = getEndPostion(startPostion,modifiedDigitNum);
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
