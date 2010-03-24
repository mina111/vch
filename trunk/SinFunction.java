
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

}
