
public abstract class HeuristicsSelection {


	abstract String getName();

	abstract LowLevelHeuristic selectLowLevelHeuristic(int[] candiateSolution);


}
