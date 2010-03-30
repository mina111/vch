
public class GreedyRandom extends HeuristicsSelection {
	HyperHeuristic hyperHeuristic;
	int[] optimumSolution;
	int[][] histories = new int[hyperHeuristic.lowLevelHeuristics.size()][HyperHeuristic.DIGIT_NUM];
	int optimumSolutionIndex;
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
		optimumSolutionIndex = 0;
		optimumSolution = lowLevelHeuristic.generateNewSolution(candiateSolution);
		histories[0] = optimumSolution;
		for(int i=1; i<hyperHeuristic.lowLevelHeuristics.size();i++){
			int[] newSolution = hyperHeuristic.lowLevelHeuristics.get(i).generateNewSolution(candiateSolution);
			histories[i] = newSolution;
			if( hyperHeuristic.function.evaluate(newSolution) <= hyperHeuristic.function.evaluate(optimumSolution)    ){
				lowLevelHeuristic = hyperHeuristic.lowLevelHeuristics.get(i);
				optimumSolution = newSolution;
				optimumSolutionIndex = i;
			}


		}
		return lowLevelHeuristic;
	}

}
