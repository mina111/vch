
public class GreedyRandom extends HeuristicsSelection {
	int[] optimumSoluation;
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Greedy Random";
	}

	@Override
	LowLevelHeuristic selectLowLevelHeuristic(int[] candiateSolution) {
		// TODO Auto-generated method stub
		LowLevelHeuristic lowLevelHeuristic = HyperHeuristic.lowLevelHeuristics.get(0);
		optimumSoluation = lowLevelHeuristic.generateNewSoluation(candiateSolution);	
		for(int i=1; i<HyperHeuristic.lowLevelHeuristics.size();i++){
			int[] newSoluation = HyperHeuristic.lowLevelHeuristics.get(i).generateNewSoluation(candiateSolution);
			
			if( HyperHeuristic.function.evaluate(newSoluation) <= HyperHeuristic.function.evaluate(optimumSoluation)    ){
				lowLevelHeuristic = HyperHeuristic.lowLevelHeuristics.get(i);
				optimumSoluation = newSoluation;
			}
				
				
		}
		return lowLevelHeuristic;
	}

}
