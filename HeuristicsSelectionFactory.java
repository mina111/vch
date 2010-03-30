
public class HeuristicsSelectionFactory {
	public static HeuristicsSelection createHeuristicsSelection(String name, HyperHeuristic hyperHeuristic){
		if(name.equals("Simple Random"))
			return new SimpleRandom(hyperHeuristic);
		else if(name.equals("Greedy Random"))
			return new GreedyRandom(hyperHeuristic);
		else if(name.equals("Reinforcement Learning"))
			return new ReinforcementLearning(hyperHeuristic);
		else 
			return null;

	}
}
