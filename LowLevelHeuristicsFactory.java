
public class LowLevelHeuristicsFactory {

	public static LowLevelHeuristic createLowLevelHeuristic(String name){
		if(name.equals("Reverse"))
			return new Reverse();
		else if(name.equals("Inverse"))
			return new Inverse();
		else if(name.equals("Shift"))
			return new Shift();
		else if(name.equals("Flip One Bit"))
			return new FlipOneBit();
		else if(name.equals("Steepest Gradient"))
			return new SteepestGradient();
		else 
			return null;

	}

}
