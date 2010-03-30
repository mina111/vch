
public class LogarithmFunction extends Function{

	@Override
	double evaluate(int[] bit) {
		// TODO Auto-generated method stub
		return  Math.log(HyperHeuristic.bit2int(bit)+1);
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "f(x)=logx";
	}

	@Override
	double evaluateInteger(int x) {
		// TODO Auto-generated method stub
		return Math.log(1+x);
	}

}
