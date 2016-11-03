package BinarySearch;

public class Binary441ArrangingCoins {
    /**
     * Runtime: 61ms - 59ms  11/2/2016
     */
    public static int arrangeCoins(int n) {
//        int sum = n;
//        int i = 1;
//        for (; sum >= i; i++) {
//            sum -= i;
//        }
//        return i - 1;
        int row = 0;
        while(n > row) {
            n -= ++row;
        }
        return row;
    }

    /**
     * Run： 61ms Use sum of 等比数列  11/2/2016
     * https://discuss.leetcode.com/topic/65879/easy-to-understand-o-1-java/3
     */
    public static int arrangeCoinsByMath(int n) {
        int x =(int) Math.sqrt(2L*n);
        return x*(x+1L)<=2L*n? x : x-1;
    }

    /**
     * Runtime: 62ms   11/2/2016
     * https://discuss.leetcode.com/topic/65593/java-clean-code-with-explanations-and-running-time-2-solutions
     */
    public static int arrangeCoinsByBinary(int n) {
        int l = 0, h = n;
        while(l <= h) {
            int mid = (l + h)/2;
            if ((0.5*mid*mid + 0.5*mid) <= n) {
                l = mid + 1;
            } else if ((0.5*mid*mid + 0.5*mid) > n) {
                h = mid - 1;
            }
        }
        return l - 1;
    }

    public static void main(String[] args) {
        System.out.println(1L);
        System.out.println(arrangeCoinsByBinary(5));
    }
}
