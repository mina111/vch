import java.util.HashMap;
import java.util.Random;
import java.util.Vector;
/**
 *  A Visualisation Tool for 
 *  Selection Hyper-Heuristics                                    <br>   
 *  															  <br>
 *  http://code.google.com/p/vch/   							  <br>
 *  															  <br>
 *  Module:                  G52GRP, University of Nottingham     <br>
 *  															  <br>
 *  Group:        gp09-exo    						  			  <br>
 * @author 	   	Lao Jingqi (jxl29u)
 * @author	   	Zhang Chao (cxz09u)
 * @author		Thomas Barton (txb18u)
 * @author		Ben Jenkinson (bxj08u)
 * @author	   	Alexander Jermstad (asj08u) 
 * 			
 */

/**
 *  the class is  the low-level heuristics Steepest Gradient which extends from LowLevelHeuristic
 */

public class ReinforcementLearning extends HeuristicsSelection {
	/**
	 *  the instance of hyper-heuristic
	 */
	HyperHeuristic hyperHeuristic;
	/**
	 *   the key for each low-level heuristic
	 */
	int[] keys;
	/**
	 *  the instance of Random
	 */
	Random random = new Random();
	/**
	 *  the no of current low level heuristic
	 */
	int currentLowLevelHeuristicNO;
	
	
	/**
	 * Defines an ReinforcementLearning object with a instance of HyperHeuristic
	 * @param hyperHeuristic
	 */
	public ReinforcementLearning(HyperHeuristic hyperHeuristic) {
		this.hyperHeuristic = hyperHeuristic;
		keys = new int[hyperHeuristic.lowLevelHeuristics.size()];
		for(int i=0;i<hyperHeuristic.lowLevelHeuristics.size();i++){
			keys[i] = 0;
		}
	}
	
	/**
	 *  return the name as "Reinforcement Learning"
	 */
	String getName() {
		// TODO Auto-generated method stub
		return "Reinforcement Learning";
	}

	/**
	 * Override the method to get a low-level heuristic by reinforcement learning
	 */
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

	/**
	 *  if new solution accepted, increase its key
	 */
	void incrementScore (){
		// TODO Auto-generated method stub
			keys[currentLowLevelHeuristicNO]++;
			     
	}
	/**
	 *  if new solution rejected, decrease its key
	 */
	void decrementScore(){
			keys[currentLowLevelHeuristicNO]--;
		
	}

}
