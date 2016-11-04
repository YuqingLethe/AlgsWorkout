package BinarySearch;

public class Binary278FirstBadVersion {
    /**
     * Runtime: 19ms
     */
    public static int firstBadVersion(int n) {
        int l = 1, h = n;
        while(l <= h) {
            int mid = l + (h - l)/2;
            if (isBadVersion(mid)) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * Runtime: 17ms
     * https://discuss.leetcode.com/topic/38135/a-good-warning-to-me-to-use-start-end-start-2-to-avoid-overflow/2
     */
    public static int firstBadVersion2(int n) {
        int start = 1, end = n;
        while(end - start > 1) {
            int mid = start + (end - start)/2;
            if (isBadVersion(mid)) end = mid - 1;
            else start = mid + 1;
        }
        return start;
    }
    private static boolean isBadVersion(int version) {
        if (version < 5) return false;
        else return true;
    }

    public static void main(String[] args) {
        System.out.println(firstBadVersion(6));
    }
}
