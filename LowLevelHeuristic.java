import java.util.Random;


public abstract  class LowLevelHeuristic {
	final int UPPER_BOUND_PERCENT = 40;
	final int LOWER_BOUND_PERCENT = 20;


	int upperBoundModifiedDigitNum = 0, lowerBoundModifiedDigitNum = 0;

	Random random = new Random();

	public LowLevelHeuristic(){
		setUpperBoundModifiedDigitNum();
		setLowerBoundModifiedDigitNum();
	}
	void setUpperBoundModifiedDigitNum() {
		upperBoundModifiedDigitNum = HyperHeuristic.DIGIT_NUM * UPPER_BOUND_PERCENT /100;
	}

	void setLowerBoundModifiedDigitNum() {
		lowerBoundModifiedDigitNum = HyperHeuristic.DIGIT_NUM  * LOWER_BOUND_PERCENT /100;
	}

	int getModifiedDigitNum(){
		return (random.nextInt(upperBoundModifiedDigitNum-lowerBoundModifiedDigitNum+1))+ lowerBoundModifiedDigitNum;
	}

	int getStartPostion(){
		return random.nextInt(HyperHeuristic.DIGIT_NUM );
	}



	int getEndPostion(int startPostion,int modifiedDigitNum){
		if(startPostion+modifiedDigitNum-1<=HyperHeuristic.DIGIT_NUM -1){
			return startPostion+modifiedDigitNum-1;
		}else{
			return modifiedDigitNum - (HyperHeuristic.DIGIT_NUM  - startPostion) -1;
		}
	}
	abstract String getName();
	abstract int[] generateNewSolution(int[] candidateSolution);

}