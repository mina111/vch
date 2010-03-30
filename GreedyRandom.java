
public class GreedyRandom extends HeuristicsSelection {
	HyperHeuristic hyperHeuristic;
	int[] optimumSoluation;
	int[][] histories = new int[hyperHeuristic.lowLevelHeuristics.size()][HyperHeuristic.DIGIT_NUM];
	int optimumSoluationIndex;
	public GreedyRandom(HyperHeuristic hyperHeuristic) {
		// TODO Auto-generated constructor stub
		this.hyperHeuristic = hyperHeuristic;
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Greedy Random";
	}

	@Override
	LowLevelHeuristic selectLowLevelHeuristic(int[] candiateSolution) {
		// TODO Auto-generated method stub
		LowLevelHeuristic lowLevelHeuristic = hyperHeuristic.lowLevelHeuristics.get(0);
		optimumSoluationIndex = 0;
		optimumSoluation = lowLevelHeuristic.generateNewSoluation(candiateSolution);
		histories[0] = optimumSoluation;
		for(int i=1; i<hyperHeuristic.lowLevelHeuristics.size();i++){
			int[] newSoluation = hyperHeuristic.lowLevelHeuristics.get(i).generateNewSoluation(candiateSolution);
			histories[i] = newSoluation;
			if( hyperHeuristic.function.evaluate(newSoluation) <= hyperHeuristic.function.evaluate(optimumSoluation)    ){
				lowLevelHeuristic = hyperHeuristic.lowLevelHeuristics.get(i);
				optimumSoluation = newSoluation;
				optimumSoluationIndex = i;
			}
				
				
		}
		return lowLevelHeuristic;
	}

}
