package DynamicProgramming;

/**
 * Created by Administrator on 2016/11/4.
 */
public class DP198HouseRobber {
    /**
     * Runtime 1ms
     * https://discuss.leetcode.com/topic/28369/the-correct-dp-solution
     */
    public static int rob(int[] nums) {
        if (nums.length == 0) return 0;
        int[] house = new int[nums.length];

        house[0] = nums[0];
        if (nums.length > 1) {
            house[1] = nums[0] >= nums[1] ? nums[0] : nums[1];
        }

        for (int i = 2; i < nums.length; i++) {
            house[i] = Math.max(house[i - 2] + nums[i], house[i - 1]);
        }
        return house[nums.length - 1];
    }

    /**
     * Runtime: 0ms Finally figure out the meaning. Simplified solution, see the link below.
     * We don't need to store money sum by each houses
     * https://leetcode.com/problems/house-robber/
     * my post: https://discuss.leetcode.com/topic/66402/java-o-n-runtime-and-o-1-space-before-simplified-version-after-simplified-version
     */
    public static int rob2(int[] nums) {
        int currYes = 0, currNo = 0;
        for (int x : nums) {
            int prevNo = currNo;
            currNo = Math.max(prevNo, currYes);
            currYes = prevNo + x;
        }
        return Math.max(currNo, currYes);
    }
    public static void main(String[] args) {
        int[] nums = {1};
        System.out.println(rob(nums));
    }
}
