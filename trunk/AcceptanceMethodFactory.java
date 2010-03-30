
public class AcceptanceMethodFactory {

	public static AcceptanceMethod createAcceptanceMethod(String name,HyperHeuristic hyperHeuristic){
		if(name.equals("All Moves Accepted"))
			return new AllMovesAccepted();
		else if(name.equals("Only Improving"))
			return new OnlyImproving(hyperHeuristic);
		else if(name.equals("Improving or Equal"))
			return new ImprovingEqual(hyperHeuristic);
		else
			return null;

	}
}
