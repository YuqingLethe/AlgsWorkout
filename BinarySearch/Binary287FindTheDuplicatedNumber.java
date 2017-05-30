package BinarySearch;

/**
 * Created by Administrator on 2017/3/26.
 */
public class Binary287FindTheDuplicatedNumber {
    /**
     * Runtime: 101ms   Use: 1min  3/26/2017
     * Brute Force
     */
    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j])
                    return nums[i];
            }
        }
        return -1;
    }

    /**
     * See findDuplicateByCycleSelf
     * https://discuss.leetcode.com/topic/25913/my-easy-understood-solution-with-o-n-time-and-o-1-space-without-modifying-the-array-with-clear-explanation/2
     * https://discuss.leetcode.com/topic/25685/java-o-n-time-and-o-1-space-solution-similar-to-find-loop-in-linkedlist
     */

    /**
     * Runtime:   1ms  Use: 3ms 4/1/2017
     * Brilliant idea
     */
    public int findDuplicateByCycleSelf(int[] nums) {
        int latter = nums[0];
        int former = nums[nums[0]];

        while(latter != former) {
            latter = nums[latter];
            former = nums[nums[former]];
        }
        former = 0;
        while(latter != former) {
            former = nums[former];
            latter = nums[latter];
        }
        return former;
    }

    /**
     * Runtime:  5ms  Use:  4ht   4/1/2017
     * https://discuss.leetcode.com/topic/25580/two-solutions-with-explanation-o-nlog-n-and-o-n-time-o-1-space-without-changing-the-input-array
     *
     * https://discuss.leetcode.com/topic/25580/two-solutions-with-explanation-o-nlog-n-and-o-n-time-o-1-space-without-changing-the-input-array/11
     */
    public static int findDuplicateBinary(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high) {
            int mid = (high - low)/2 + low;
            //Find the counts less than the nums[mis]
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid)
                count++;
            }

            if (count <= mid) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] nums1 = {2,1,1};
        int[] nums2 = {1,1};
        int[] nums3 = {1,2,2};
        int[] nums4 = {1,3,4,2,2};
        System.out.println(findDuplicateBinary(nums1));
        System.out.println(findDuplicateBinary(nums2));
        System.out.println(findDuplicateBinary(nums3));
        System.out.println(findDuplicateBinary(nums4));
    }
}
