
public class GreedyRandom extends HeuristicsSelection {
	int[] optimumSoluation;
	int[][] histories = new int[HyperHeuristic.lowLevelHeuristics.size()][HyperHeuristic.DIGIT_NUM];
	int optimumSoluationIndex;
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Greedy Random";
	}

	@Override
	LowLevelHeuristic selectLowLevelHeuristic(int[] candiateSolution) {
		// TODO Auto-generated method stub
		LowLevelHeuristic lowLevelHeuristic = HyperHeuristic.lowLevelHeuristics.get(0);
		optimumSoluationIndex = 0;
		optimumSoluation = lowLevelHeuristic.generateNewSoluation(candiateSolution);
		histories[0] = optimumSoluation;
		for(int i=1; i<HyperHeuristic.lowLevelHeuristics.size();i++){
			int[] newSoluation = HyperHeuristic.lowLevelHeuristics.get(i).generateNewSoluation(candiateSolution);
			histories[i] = newSoluation;
			if( HyperHeuristic.function.evaluate(newSoluation) <= HyperHeuristic.function.evaluate(optimumSoluation)    ){
				lowLevelHeuristic = HyperHeuristic.lowLevelHeuristics.get(i);
				optimumSoluation = newSoluation;
				optimumSoluationIndex = i;
			}
				
				
		}
		return lowLevelHeuristic;
	}

}
