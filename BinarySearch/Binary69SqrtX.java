package BinarySearch;

/**
 * Created by Administrator on 2017/3/26.
 * 這道題的技巧:
 * 1. hi可以設置成 x/2+1
 * 2. 比較時候如果要乘法就需要強制類型轉換(long)
 * 3. 可用除法代替類型強轉, 但要注意被除數是0的情況 (mySqrt2)
 */
public class Binary69SqrtX {
    /**
     * Runtime: 2ms   USe: 20min  3/26/2017
     * The mid*mid have exceed borders
     * if h = x, the runtime will increase 1ms
     */
    public static int mySqrt(int x) {
        if (x == 1 || x == 0) return x;
        int l = 0, h = x/2 + 1;
        while(l <= h) {
            int mid = (h - l)/2 + l;
            System.out.println(mid + " -> " + x/mid);
            if (x/mid > mid)
                l = mid + 1;
            else if (x/mid == mid)
                return mid;
            else
                h = mid - 1;
        }
        return l - 1;
    }

    /**
     * 7/27/2017
     * 另一种处理被除数是0的方法
     */
    public int mySqrt2(int x) {
        int lo = 0;
        int hi = x;
        if (x == 0) {
            return 0;
        }
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (mid == 0 || mid < x / mid) { //Failed: 0
                lo = mid;
            } else if (mid > x / mid) {
                hi = mid;
            } else {
                return mid;
            }
        }
        if (hi <= x / hi) {
            return hi;
        }
        if (lo <= x / lo) {
            return lo;
        }
        return -1;
    }

    class daling2021 {
        // Start from 1:13pm - 1:52
        public int mySqrt(int x) {
            if (x <= 1) {
                return x;
            }
            int lo = 1, hi = x;
            while (lo + 1 < hi) {
                int mid = lo + ((hi - lo) >> 1);
                System.out.println("lo=" + lo + " hi=" + hi);
                if ((long)mid * mid <= x) {
                    lo = mid;
                    System.out.println("lo = mid -->" + lo);
                } else {
                    hi = mid - 1; // 因爲這個hi=mid-1, 而終止條件是lo + 1 < hi, 所以hi可能會走過一步, 也就是lo停止的位置有可能還沒到答案
                    System.out.println("hi = mid -1 -->" + hi);
                }

            }
            if ((long)hi * hi < x) {
                System.out.println("hi = " + hi + "while hi*hi=" + hi*hi);
                return hi;
            }
            return lo;
        }

        public int mySqrt2(int x) {
            if (x == 0) {
                return 0;
            }
            int lo = 1, hi = x;
            while ( lo + 1 < hi) {
                int mid = lo + ((hi - lo) / 2);
                if ((long)mid * mid <= x) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }
            return lo;
        }
    }

    public static void main(String[] args) {
//        int x = 1025;
//        int x = 2147395600; // 乘法用到
        int x = 2147395599;
        System.out.println(mySqrt(x));
        System.out.println(65537*65537);
    }
}
