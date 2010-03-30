
public class Inverse extends LowLevelHeuristic{
	int modifiedDigitNum ;
	int startPostion;
	int endPostion ;
	@Override
	int[] generateNewSolution(int[] candidateSolution) {
		// TODO Auto-generated method stub
		modifiedDigitNum = getModifiedDigitNum();
		startPostion = getStartPostion();
		endPostion = getEndPostion(startPostion,modifiedDigitNum);
		int newSolution[] = new int[candidateSolution.length];
		for(int i =0;i<candidateSolution.length;i++)
			newSolution[i]=candidateSolution[i];

		if(startPostion<endPostion){
			for(int i= startPostion;i<=endPostion;i++){
				if(newSolution[i] == 0){
					newSolution[i] = 1;
				}else{
					newSolution[i] = 0;					
				}
			}
			
			return newSolution;
		}else{
			for(int i= startPostion;i<newSolution.length;i++){
				if(newSolution[i] == 0){
					newSolution[i] = 1;
				}else{
					newSolution[i] = 0;					
				}
			}
			for(int i=0; i<=endPostion;i++ ){
				if(newSolution[i] == 0){
					newSolution[i] = 1;
				}else{
					newSolution[i] = 0;					
				}
			}
			return newSolution;
		}
		
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Inverse";
	}
	
	

	
}
