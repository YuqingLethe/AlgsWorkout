package Math;

public class Math326PowerOfThree {
	/**
	 * Method 1<br>
	 * Count the pwoer of three one by one and compare
	 */
	public boolean isPowerOfThree1(int n) {
        double exp = 1.0;
        int power = 0;
        while (exp <= n) {
            if (exp == n){
                return true;
            } else {
                exp = java.lang.Math.pow(3,++power);
            }
        }
        return false;
    }
	
	/**
	 * Method 2.1:<br>
	 * Do division directly
	 * @param n
	 * @return
	 */
	public boolean isPowerOfThree21(int n) {
        while (n/3 > 0) {
            if (n%3 != 0)
                return false;
            n = n/3;
        }
        if (n == 1)
            return true;
        else if (n%3 != 0 || n == 0 || n < 0)
            return false;
        return true;
	}
	/**
	 * Method 2.2:<br>
	 * Optimize 2.1, by myself
	 * @param n
	 * @return
	 */
	public boolean isPowerOfThree22(int n) {
        if (n == 0) return false;
        if (n == 1) return true;
        while (n%3 == 0) {
            n = n/3;
            if (n == 1) //Actually this is just for the situation when n == 3. So can be put at first
                return true;
        }
        return false;
    }
	/**
	 * Method 2.3
	 * OTher's Simpler one:
	 * @param n
	 * @return
	 */
	public boolean isPowerOfThree23(int n) {
        while(n % 3 == 0 && n > 1) {
            n = n / 3;
        }
        return n == 1;
    }
	/**
	 * Method 3<br>
	 * Using log, no loop
	 * @param n
	 * @return
	 */
	public boolean isPowerOfThree3(int n) {
        double r = Math.log10(n)/Math.log10(3.0);
        if (r%1 == 0)
            return true;
        else
            return false;
    }
	
}
