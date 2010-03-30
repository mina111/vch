
public class Inverse extends LowLevelHeuristic{
	int modifiedDigitNum ;
	int startPostion;
	int endPostion ;
	@Override
	int[] generateNewSoluation(int[] candidateSoluation) {
		// TODO Auto-generated method stub
		modifiedDigitNum = getModifiedDigitNum();
		startPostion = getStartPostion();
		endPostion = getEndPostion(startPostion,modifiedDigitNum);
		int newSoluatuon[] = new int[candidateSoluation.length];
		for(int i =0;i<candidateSoluation.length;i++)
			newSoluatuon[i]=candidateSoluation[i];

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

