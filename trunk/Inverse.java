
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
		int newSoluatuon[] = new int[candidateSolution.length];
		for(int i =0;i<candidateSolution.length;i++)
			newSoluatuon[i]=candidateSolution[i];

		if(startPostion<endPostion){
			for(int i= startPostion;i<=endPostion;i++){
				if(newSoluatuon[i] == 0){
					newSoluatuon[i] = 1;
				}else{
					newSoluatuon[i] = 0;					
				}
			}
			
			return newSoluatuon;
		}else{
			for(int i= startPostion;i<newSoluatuon.length;i++){
				if(newSoluatuon[i] == 0){
					newSoluatuon[i] = 1;
				}else{
					newSoluatuon[i] = 0;					
				}
			}
			for(int i=0; i<=endPostion;i++ ){
				if(newSoluatuon[i] == 0){
					newSoluatuon[i] = 1;
				}else{
					newSoluatuon[i] = 0;					
				}
			}
			return newSoluatuon;
		}
		
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Inverse";
	}
	
	

	
}
