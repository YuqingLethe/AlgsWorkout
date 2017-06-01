package LintCode.Binary;

/**
 * Created by Administrator on 2017/5/30.
 */
public class LintBinary14FirstPositionOfTarget {
    /**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public int binarySearch1(int[] nums, int target) {
        //write your code here
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else if (nums[mid] > target
                    || (nums[mid] == target
                    && mid != 0
                    && nums[mid - 1] == target)
                    ) {
                hi = mid - 1;
            } else if (nums[mid] == target
                    && (mid == 0 || nums[mid - 1] < target)) {
                return mid;
            }
        }
        return -1;
    }

    public static int binarySearch2(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo)/2;
            if (nums[mid] < target) {
                lo = mid;
            } else if (nums[mid] > target) {
                hi = mid;
            } else {
                hi = mid;
            }
        }
        if (nums[lo] == target) //这个想法很好, 至少一个点会落在first position上. 同理LastPositionOfTarget, 先检测非普遍的那侧
            return lo;
        if (nums[hi] == target)
            return hi;
        return -1;
    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 4, 5, 10};
        System.out.println(binarySearch2(nums, 3));
    }
}
