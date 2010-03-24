
public class ImprovingEqual extends AcceptanceMethod {

	@Override
	boolean checkIfAcceptance(int[] candidate_Soluation, int[] new_Soluation) {
		// TODO Auto-generated method stub
		if(HyperHeuristic.function.evaluate(candidate_Soluation)<HyperHeuristic.function.evaluate(new_Soluation))
			return false;
		return true;
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Improving or Equal";
	}

}
