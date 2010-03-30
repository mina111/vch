public abstract class AcceptanceMethod {

	abstract String getName();

	abstract boolean checkIfAcceptance(int[] candidate_Solution, int[] new_Solution);

}