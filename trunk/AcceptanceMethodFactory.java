
public class AcceptanceMethodFactory {

	public static AcceptanceMethod createAcceptanceMethod(String name){
		if(name.equals("All Moves Accepted"))
			return new AllMovesAccepted();
		else if(name.equals("Only Improving"))
			return new OnlyImproving();
		else if(name.equals("Improving Equal"))
			return new ImprovingEqual();
		else 
			return null;

	}
}
