
public class Reverse extends LowLevelHeuristic{
	int modifiedDigitNum ;
	int startPostion ;
	int endPostion;
	@Override
	int[] generateNewSolution(int[] candidateSolution) {
		modifiedDigitNum = getModifiedDigitNum();
		startPostion = getStartPostion();
		endPostion = getEndPostion(startPostion,modifiedDigitNum);
		int tempStartPostion = startPostion;
		int tempEndPostion = endPostion;
		 System.out.println("+modifiedDigitNum"+modifiedDigitNum+"startPostion"+startPostion+"endPostion"+endPostion);
		// TODO Auto-generated method stub
		int newSolution[] = new int[candidateSolution.length];
		for(int i =0;i<candidateSolution.length;i++)
			newSolution[i]=candidateSolution[i];

		if(startPostion<endPostion){
			while(startPostion<endPostion){
				int temp = newSolution[startPostion];
				newSolution[startPostion] = newSolution[endPostion];
				newSolution[endPostion] = temp;
				startPostion++;
				endPostion--;
			}
			startPostion = tempStartPostion;
			endPostion = tempEndPostion;
			for(int i=0;i<newSolution.length;i++)
				System.out.print(candidateSolution[i]);
			System.out.println("New");
			for(int i=0;i<newSolution.length;i++)
				System.out.print(newSolution[i]);
			System.out.println("");
			return newSolution;
		}else{
			int middlePostion = startPostion + modifiedDigitNum/2;
			if(middlePostion <= HyperHeuristic.DIGIT_NUM ){
				while(startPostion < middlePostion){
					int temp = newSolution[startPostion];
					newSolution[startPostion] = newSolution[endPostion];
					newSolution[endPostion] = temp;
					startPostion++;
					if(endPostion==0){
						endPostion = HyperHeuristic.DIGIT_NUM-1 ;
					}else{
						endPostion--;
					}
				}
			}else{
				while(endPostion > (middlePostion - HyperHeuristic.DIGIT_NUM -1)){
					int temp = newSolution[startPostion];
					newSolution[startPostion] = newSolution[endPostion];
					newSolution[endPostion] = temp;
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
			return newSolution;
		}

	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Reverse";
	}


}