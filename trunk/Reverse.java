
public class Reverse extends LowLevelHeuristic{

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
			while(startPostion<endPostion){
				int temp = newSoluatuon[startPostion];
				newSoluatuon[startPostion] = newSoluatuon[endPostion];
				newSoluatuon[endPostion] = temp;
				startPostion++;
				endPostion--;
			}
			return newSoluatuon;
		}else{
			int middlePostion = startPostion + modifiedDigitNum/2;
			if(middlePostion <= HyperHeuristic.DIGIT_NUM ){
				while(startPostion < middlePostion){
					int temp = newSoluatuon[startPostion];
					newSoluatuon[startPostion] = newSoluatuon[endPostion];
					newSoluatuon[endPostion] = temp;
					startPostion++;
					if(endPostion==0){
						endPostion = HyperHeuristic.DIGIT_NUM-1 ;
					}else{
						endPostion--;
					}
				}
			}else{
				while(endPostion > (middlePostion - HyperHeuristic.DIGIT_NUM -1)){
					int temp = newSoluatuon[startPostion];
					newSoluatuon[startPostion] = newSoluatuon[endPostion];
					newSoluatuon[endPostion] = temp;
					if(startPostion==HyperHeuristic.DIGIT_NUM-1 ){
						startPostion=0;
					}else{
						startPostion++;
					}
					endPostion--;
				}				
			}
			return newSoluatuon;
		}
		
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Reverse";
	}


}