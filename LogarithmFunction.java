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
 *  the class about logarithm function which extends from function
 */
public class LogarithmFunction extends Function{
	/**
	 * evaluate the binary representing value by applying the function
	 */
	@Override
	double evaluate(int[] bit) {
		// TODO Auto-generated method stub
		return  Math.log(HyperHeuristic.bit2int(bit)+1);
	}
	/**
	 * return the name as "f(x)=logx"
	 */
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "f(x)=logx";
	}
	/**
	 * evaluate the integer value by applying the function
	 */
	@Override
	double evaluateInteger(int x) {
		// TODO Auto-generated method stub
		return Math.log(1+x);
	}

}
