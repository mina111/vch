
public class Reverse extends LowLevelHeuristic{
	int modifiedDigitNum ;
	int startPostion ;
	int endPostion;	
	@Override
	int[] generateNewSoluation(int[] candidateSoluation) {
		modifiedDigitNum = getModifiedDigitNum();
		startPostion = getStartPostion();
		endPostion = getEndPostion(startPostion,modifiedDigitNum);
		int tempStartPostion = startPostion;
		int tempEndPostion = endPostion;
		 System.out.println("+modifiedDigitNum"+modifiedDigitNum+"startPostion"+startPostion+"endPostion"+endPostion);
		// TODO Auto-generated method stub
		int newSoluatuon[] = new int[candidateSoluation.length];
		for(int i =0;i<candidateSoluation.length;i++)
			newSoluatuon[i]=candidateSoluation[i];
		
		if(startPostion<endPostion){
			while(startPostion<endPostion){
				int temp = newSoluatuon[startPostion];
				newSoluatuon[startPostion] = newSoluatuon[endPostion];
				newSoluatuon[endPostion] = temp;
				startPostion++;
				endPostion--;
			}
			startPostion = tempStartPostion;
			endPostion = tempEndPostion;
			for(int i=0;i<newSoluatuon.length;i++)
				System.out.print(candidateSoluation[i]);
			System.out.println("New");
			for(int i=0;i<newSoluatuon.length;i++)
				System.out.print(newSoluatuon[i]);
			System.out.println("");
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
			startPostion = tempStartPostion;
			endPostion = tempEndPostion;
			return newSoluatuon;
		}
		
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Reverse";
	}


}