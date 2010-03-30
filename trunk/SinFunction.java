
public class SinFunction extends Function{

	@Override
	double evaluate(int[] bit) {
		// TODO Auto-generated method stub
		return Math.sin(HyperHeuristic.bit2int(bit));
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "f(x)=sinx";
	}
	double evaluateInteger(int x) {
		// TODO Auto-generated method stub
		return Math.sin((Math.PI*x)/10000.0);
	}
}
