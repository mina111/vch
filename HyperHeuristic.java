import java.util.Vector;


public class HyperHeuristic {
	 static final int DIGIT_NUM = 15;
	 Function function;
	 AcceptanceMethod acceptanceMethod;
	 Vector<LowLevelHeuristic> lowLevelHeuristics = new Vector<LowLevelHeuristic>();
	 HeuristicsSelection heuristicsSelection;

	 public static int bit2int(int[] bit){
			double intNum = 0;
			for(int i=0; i<bit.length; i++){
				intNum = intNum + bit[i] * Math.pow(2, (bit.length-1-i));
			}
			return (int)intNum;
	 }

}
