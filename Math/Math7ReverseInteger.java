package Math;

public class Math7ReverseInteger {
	/**
	 * Method 1: 2ms for 1032 test cases<br>
	 * Negative change to positive, and then 
	 * change back to negative.
	 * @param x
	 * @return
	 */
	public static int reverse(int x) {		
		long result = 0; //store the result
		boolean neg = false; //if negative
		
		if (x < 0) {//negative integers
			x = -x;
			neg = true;
			System.out.println("Negative input");
		}		
		while(x/10 != 0) {//Normally reverse
			result = result*10 + x%10;
			x = x/10;
		}
		result = result*10 + x%10;
		System.out.println("After normally result = " + result);
		if (neg)//negative add symbal
			result = -result;
		if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
			System.out.println("overflow");
			return 0;
		}
		return (int)result;
	}
	
	
	public static void main(String [] args) {
		System.out.println(reverse(534236469));
	}
}
