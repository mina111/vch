
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
		int newSolution[] = new int[candidateSolution.length];
		for(int i =0;i<candidateSolution.length;i++)
			newSolution[i]=candidateSolution[i];

		if(startPostion<endPostion){
			int temp = newSolution[startPostion];
			for(int i = startPostion;i<endPostion;i++){
				newSolution[i]= newSolution[i+1];
			}
			newSolution[endPostion] = temp;
			return newSolution;
		}else{
			int temp = newSolution[startPostion];
			for(int i = startPostion;i<HyperHeuristic.DIGIT_NUM ;i++){
				if(i==HyperHeuristic.DIGIT_NUM -1){
					newSolution[i]=newSolution[0];
				}else{
					newSolution[i]= newSolution[i+1];
				}
			}
			for(int i = 0;i<endPostion;i++){
				newSolution[i]= newSolution[i+1];
			}
			newSolution[endPostion] = temp;
			return newSolution;
		}

	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Shift";
	}

}
