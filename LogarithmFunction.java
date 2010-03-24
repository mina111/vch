
public class LogarithmFunction extends Function{

	@Override
	double evaluate(int[] bit) {
		// TODO Auto-generated method stub
		return  Math.log(HyperHeuristic.bit2int(bit)*HyperHeuristic.bit2int(bit));
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "f(x)=logx";
	}

}
