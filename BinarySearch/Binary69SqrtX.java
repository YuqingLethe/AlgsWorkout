package BinarySearch;

/**
 * Created by Administrator on 2017/3/26.
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

    public static void main(String[] args) {
        int x = 1025;
        System.out.println(mySqrt(x));
        System.out.println(65537*65537);
    }
}
