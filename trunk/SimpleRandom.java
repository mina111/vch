import java.util.Random;


public class SimpleRandom extends HeuristicsSelection {

	Random random = new Random();
	
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Simple Random";
	}

	@Override
	LowLevelHeuristic selectLowLevelHeuristic(int[] candiateSolution) {
		// TODO Auto-generated method stub
		return HyperHeuristic.lowLevelHeuristics.get(random.nextInt(HyperHeuristic.lowLevelHeuristics.size()));
		
	}
	
	
}
