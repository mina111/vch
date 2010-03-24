
public class FunctionFactory {

	public static Function createFunction(String name){
		if(name.equals("f(x)=x^2"))
			return new SquareFunction();
		else if(name.equals("f(x)=sinx"))
			return new SinFunction();
		else if(name.equals("f(x)=sin(x^2)"))
			return new SinSquareFunction();
		else if(name.equals("f(x)=logx"))
			return new LogarithmFunction();
		else 
			return null;

	}
}
