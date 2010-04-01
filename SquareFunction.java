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
 *  the class about square function which extends from function
 */
public class SquareFunction extends Function{
	/**
	 * evaluate the binary representing value by applying the function
	 */
	@Override
	double evaluate(int[] bit) {
		// TODO Auto-generated method stub
		return HyperHeuristic.bit2int(bit)*HyperHeuristic.bit2int(bit);
	}
	/**
	 * return the name as "f(x)=logx"
	 */
	@Override
	String getName() {
		
		return "f(x)=x^2";
	}
	/**
	 * evaluate the integer value by applying the function
	 */
	@Override
	double evaluateInteger(int x) {
		// TODO Auto-generated method stub
		return x*x;
	}

	
}
