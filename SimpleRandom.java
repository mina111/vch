import java.util.Random;


public class SimpleRandom extends HeuristicsSelection {

	Random random = new Random();
	HyperHeuristic hyperHeuristic;
	public SimpleRandom(HyperHeuristic hyperHeuristic) {
		// TODO Auto-generated constructor stub
		this.hyperHeuristic = hyperHeuristic;
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Simple Random";
	}

	@Override
	LowLevelHeuristic selectLowLevelHeuristic(int[] candiateSolution) {
		// TODO Auto-generated method stub
		return hyperHeuristic.lowLevelHeuristics.get(random.nextInt(hyperHeuristic.lowLevelHeuristics.size()));

	}


}
