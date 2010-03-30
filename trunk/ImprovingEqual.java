
public class ImprovingEqual extends AcceptanceMethod {
	HyperHeuristic hyperHeuristic;
	public ImprovingEqual(HyperHeuristic hyperHeuristic) {
		// TODO Auto-generated constructor stub
		this.hyperHeuristic = hyperHeuristic;
	}

	@Override
	boolean checkIfAcceptance(int[] candidate_Solution, int[] new_Solution) {
		// TODO Auto-generated method stub
		if(hyperHeuristic.function.evaluate(candidate_Solution)<hyperHeuristic.function.evaluate(new_Solution))
			return false;
		return true;
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Improving or Equal";
	}

}
