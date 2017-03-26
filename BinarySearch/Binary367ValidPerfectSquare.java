package BinarySearch;

public class Binary367ValidPerfectSquare {
    /**
     * Runtime: 0ms Use: 10min 3/26/2017
     * range: 0 to 46340, which is the sqrt of Integer.MAX_VALUE
     */
    public boolean isPerfectSquare(int num) {
        if (num > Integer.MAX_VALUE || num < Integer.MIN_VALUE) {
            return false;
        }
        int l = 0, h = 46340;
        while(l <= h) {
            int mid = (h - l)/2 + l;
            if (mid*mid ==  num) {
                return true;
            } else if (mid*mid < num) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return false;
    }

    /**
     * Runtime: 0ms  Use: 1min 3/26/2017
     * The range is optimized to 0 ~ num. consider the mid*mid may be larger than max integer.
     */
    public boolean isPerfectSquareOptimize(int num) {
        int l = 0, h = num;
        while(l <= h) {
            int mid = (h - l)/2 + l;
            if (mid*mid ==  num) {
                return true;
            } else if (mid*mid < num) {
                l = (int) mid + 1;
            } else {
                h = (int) mid - 1;
            }
        }
        return false;
    }

    /**
     * Runtime: 4ms  Use:  15mid debugging the long type  3/26/2017
     * Use Math Method
     */
    public boolean isPerfectSquareMath(int num) {
        long sum = 0;
        for (int i = 1; i <= num/2 + 1; i++) {
            if (i%2 != 0)
                sum += i;
            if (sum == num)
                return true;
            if (sum > num)
                return false;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Math.sqrt((double)Integer.MAX_VALUE));
    }
}
