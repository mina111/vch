import java.util.*;

/**
 * @author Lao
 * @version 1.0  
 * In this version, I implement a simple example of HyperHeuristic. Aiming at choosing a 10-bit binary represent number making the value of f(x) = x^2 as little as possible 
 * Firstly, the HyperHeuristic choose one of the low-level heuristics randomly and get a new number, then we compare the original number and the new number and choose the suitable one
 * the program do this many times and we may get most suitable result.
 */

public class HyperHeuristic {
	/**
	 * the binary represent number
	 */
	public int [] bit = new int[10];
	/**
	 *  the instance of  the class random for producing two random position and choosing a lower-level heuristic
	 */
	Random rd = new Random();
	/**  
	 * the two random positions for three heuristics
	 */
	public int postion1, postion2;
	public static void main (String[] args){
		new HyperHeuristic().run();
	}
		
	/**
	 * Start to run
	 */	
	private void run(){
		System.out.println("the first random number is");
		for(int i=0; i<10; i++){
			bit[i] = rd.nextInt(2);
			System.out.print(bit[i]);
		}
		System.out.println("");
		for(int i=0; i<100; i++){      
			randomChooseHeuristic();
			for(int j=0; j<10; j++){
				System.out.print(bit[j]);				
			}
			System.out.println("");
		}
		System.out.println("the final result is");		
		for(int i=0; i<10; i++)
			System.out.print(bit[i]);
		

	}
	
	/**
	 * the HyperHeuristic choose a lower level heuristic randomly
	 */
	private void randomChooseHeuristic(){
		 double randomNumber = rd.nextDouble();;
		 if (randomNumber >= 0 && randomNumber< 1.0/3)  
		 {
			 method1();			 
		 }
		 else if (randomNumber >= (1.0/3) && randomNumber < (1.0-1.0/3))
		 {
			 method2();		 
		 }
		 else  
		 {
			 method3();		 
			 
		 }
	}
	
	/**
	 * the first heuristic: choose two positions randomly, then change their values.
	 */
	private void method1(){
		/**
		 * the new binary number produced by method1
		 */
		int [] bit1 = new int [10];
		for(int i=0; i<10; i++){
			bit1[i] = bit[i];
		}
		get2Postions();
		if(postion1!=postion2){
			if(bit1[postion1]==0){
		
				bit1[postion1]=1;
	
			}else{
				bit1[postion1]=0;
			}
			if(bit1[postion2]==0){
				bit1[postion2]=1;
			}else{
				bit1[postion2]=0;
			}
		}else{
			if(bit1[postion1]==0){
				
				bit1[postion1]=1;
	
			}else{
				bit1[postion1]=0;
			}
		}
		System.out.println("invoke method1");		
		compare(bit1);
	}
	
	/**
	 * the second heuristic: choose two positions randomly, then swap their values.
	 */
	private void method2(){
		/**
		 * the new binary number produced by method1
		 */
		int [] bit2 = new int [10];
		int temp1,temp2;
		for(int i=0; i<10; i++){
			bit2[i] = bit[i];
		}
		get2Postions();
		temp1 = bit[postion1];
		temp2 = bit[postion2];
		bit2[postion1] = temp2;
		bit2[postion2] = temp1;
		System.out.println("invoke method2");	
		compare(bit2);
	}
	
	/**
	 * the third heuristic: choose two positions randomly,then  swap their values and change the values of number between them.
	 */
	private void method3(){
		/**
		 * the new binary number produced by method1
		 */
		int [] bit3 = new int [10];
		int temp1,temp2;
		get2Postions();
		for(int i=0; i<10; i++){
			bit3[i] = bit[i];
		}
		temp1 = bit[postion1];
		temp2 = bit[postion2];
		bit3[postion1] = temp2;
		bit3[postion2] = temp1; 
		if(postion1<=postion2){
			for(int i=(postion1+1); i<postion2;i++){
				if(bit3[i]==0){
					bit3[i]=1;
				}else{
					bit3[i]=0;
				}
			}
		}else{
			for(int i=(postion2+1); i<postion1;i++){
				if(bit3[i]==0){
					bit3[i]=1;
				}else{
					bit3[i]=0;
				}
			}
		}
		System.out.println("invoke method3");	
		compare(bit3);
	}
	
	private void compare(int[] bit4){	
		double oldNum, newNum;
		oldNum = decode(bit);
		newNum = decode(bit4);
		if(function(oldNum)>function(newNum)){
			for(int i=0; i<10; i++){
				bit[i]=bit4[i];
			}
			System.out.println("Because function(old number)="+"function("+oldNum+")="+function(oldNum)+">function(new number)"+"=function("+newNum+")="+function(newNum));	
			System.out.println("the number becomes");
		}else{
			System.out.println("Because function(old number)="+"function("+oldNum+")="+function(oldNum)+"<=function(new number)"+"=function("+newNum+")="+function(newNum));
			System.out.println("there is no need to change, the number is still");
		}
		
	}
	/**
	 *  get the two positions for lower level heuristic
	 */
	private void get2Postions(){
		postion1 = rd.nextInt(10);
		postion2 = rd.nextInt(10);
	}
	
	/**
	 * 
	 * @param   bit5 the binary represent number
	 * @return  the corresponding  integer
	 */
	private double decode(int[] bit5){
		double intNum = 0;
		for(int i=0; i<10; i++){
			intNum = intNum + bit5[i] * Math.pow(2, (9-i));
		}
		return intNum;
	}
	/**
	 * 
	 * @param x
	 * @return the value f(x)
	 *  the domain of question
	 */
	private double function(double x){
		return Math.pow(x, 2);
	}
	
}
