package LintCode.Binary.Binary;

/**
 * Created by Administrator on 2017/6/1.
 */
public class LintBinary458LastPositionOfTarget {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return an integer
     */
    public int lastPosition(int[] nums, int target) {
        // Write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int lo = 0;
        int hi = nums.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] <= target) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        if (nums[hi] == target) {//普遍都是lo符合条件, 所以先比hi, 以防最右侧是target值的case[0,5,5], 5
            return hi;
        } else if (nums[lo] == target) {
            return lo;
        } else {
            return -1;
        }
    }
}
