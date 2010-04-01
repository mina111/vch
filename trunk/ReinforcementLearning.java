import java.util.HashMap;
import java.util.Random;
import java.util.Vector;


public class ReinforcementLearning extends HeuristicsSelection {

	HyperHeuristic hyperHeuristic;
	int[] keys;
	Random random = new Random();
	int currentLowLevelHeuristicNO;
	public ReinforcementLearning(HyperHeuristic hyperHeuristic) {
		this.hyperHeuristic = hyperHeuristic;
		keys = new int[hyperHeuristic.lowLevelHeuristics.size()];
		for(int i=0;i<hyperHeuristic.lowLevelHeuristics.size();i++){
			keys[i] = 0;
		}
	}

	String getName() {
		// TODO Auto-generated method stub
		return "Reinforcement Learning";
	}

	@Override
	LowLevelHeuristic selectLowLevelHeuristic(int[] candiateSolution) {
		// TODO Auto-generated method stub
		int maxNumKey = 0;
		Vector<Integer> maxNumKeys = new Vector<Integer>();
		for(int i=1;i<keys.length;i++){
			if(keys[maxNumKey]<=keys[i])
				maxNumKey = i;
		}
		for(int i=0;i<keys.length;i++){
			if(keys[maxNumKey]==keys[i])
				maxNumKeys.add(i);

		}
		if(maxNumKeys.size()==1)
			currentLowLevelHeuristicNO = maxNumKey;
		else
			currentLowLevelHeuristicNO = maxNumKeys.get(random.nextInt(maxNumKeys.size()));
		return hyperHeuristic.lowLevelHeuristics.get(currentLowLevelHeuristicNO);
	}

	void incrementScore (){
		// TODO Auto-generated method stub
			keys[currentLowLevelHeuristicNO]++;

	}

	void decrementScore(){
			keys[currentLowLevelHeuristicNO]--;

	}

}
