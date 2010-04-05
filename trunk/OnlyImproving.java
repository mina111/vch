
public class OnlyImproving extends AcceptanceMethod {

	HyperHeuristic hyperHeuristic;
	public OnlyImproving(HyperHeuristic hyperHeuristic) {
		// TODO Auto-generated constructor stub
		this.hyperHeuristic = hyperHeuristic;
	}

	@Override
	boolean checkIfAcceptance(int[] candidate_Soluation, int[] new_Soluation) {
		// TODO Auto-generated method stub
		if(hyperHeuristic.function.evaluate(candidate_Soluation)<=hyperHeuristic.function.evaluate(new_Soluation))
			return false;
		return true;
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Only Improving";
	}

}