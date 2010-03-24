
public class HeuristicsSelectionFactory {
	public static HeuristicsSelection createHeuristicsSelection(String name){
		if(name.equals("Simple Random"))
			return new SimpleRandom();
		else if(name.equals("Greedy Random"))
			return new GreedyRandom();
		else if(name.equals("Reinforcement Learning"))
			return new ReinforcementLearning();
		else 
			return null;

	}
}
