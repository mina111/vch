/**
 *  A Visualisation Tool for 
 *  Selection Hyper-Heuristics                                    <br>   
 *  															  <br>
 *  http://code.google.com/p/vch/   							  <br>
 *  															  <br>
 *  Module:                  G52GRP, University of Nottingham     <br>
 *  															  <br>
 *  Group:        gp09-exo    						  			  <br>
 * @author 	   	Lao Jingqi (jxl29u)
 * @author	   	Zhang Chao (cxz09u)
 * @author		Thomas Barton (txb18u)
 * @author		Ben Jenkinson (bxj08u)
 * @author	   	Alexander Jermstad (asj08u) 
 * 			
 */

/**
 *  This class plays the role to produce the benchmark function
 */
public class FunctionFactory {
	/**
	 * produce the instance of function with specified string and instance of hyper-heuristic
	 * @param name
	 * @return the instance of function
	 */
	public static Function createFunction(String name){
		if(name.equals("f(x)=x^2"))
			return new SquareFunction();
		else if(name.equals("f(x)=sinx"))
			return new SinFunction();
		else if(name.equals("f(x)=logx"))
			return new LogarithmFunction();
		else 
			return null;

	}
}
