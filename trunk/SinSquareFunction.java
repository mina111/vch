
public class SinSquareFunction extends Function{

	@Override
	double evaluate(int[] bit) {
		// TODO Auto-generated method stub
		return Math.sin(HyperHeuristic.bit2int(bit)*HyperHeuristic.bit2int(bit));
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "f(x)=sin(x^2)";
	}

	@Override
	double evaluateInteger(int x) {
		// TODO Auto-generated method stub
		return Math.sin(x*x);
	}

}
