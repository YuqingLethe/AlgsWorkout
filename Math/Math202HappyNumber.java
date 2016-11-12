package Math;

import java.util.ArrayList;

public class  Math202HappyNumber {
	/**
	 * Method 1 66ms for 401 test cases<br>
	 * Original thought. Store all numbers for endless check
	 * @param n
	 * @return
	 */
	public static boolean isHappy(int n) {
        ArrayList<Integer> nlist = new ArrayList<Integer>();
        if (n == 1) //f
            return true;
        while (n != 1) {
            double temp = 0;
            while (n != 0) {
                temp = temp + Math.pow(n%10,2);
                n = n/10;
            }
            n = (int) temp;
            System.out.println(n);
            if (nlist.contains(n))//check if endless loop
                return false;
            else {
                nlist.add(n);
            }
        }
        return true;
    }
	
	public static void main(String[] args) {
		System.out.println(isHappy(7));
	}
}
