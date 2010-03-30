
public class SquareFunction extends Function{

	@Override
	double evaluate(int[] bit) {
		// TODO Auto-generated method stub
		return HyperHeuristic.bit2int(bit)*HyperHeuristic.bit2int(bit);
	}

	@Override
	String getName() {
		
		return "f(x)=x^2";
	}

	@Override
	double evaluateInteger(int x) {
		// TODO Auto-generated method stub
		return x*x;
	}

	
}
