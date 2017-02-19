package NoTag;

import java.util.HashMap;

public class No525ContiguousArray {
    /**
     * First Thought: Didn't notice Subarray, thought all array start from the head.....
     * 2017/2/18.
     */
    public static int findMaxLengthFromHead(int[] nums) {
        int ans = 0, zero = 0, one = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zero++;
            } else {
                one++;
            }
            if (i%2 != 0 && zero == one) ans = i;
        }
        if (ans == 0) return ans;
        else return ans + 1;
    }

    /**
     * Runtime: 90ms  Use: 1hr 2017/2/18.
     * Build the array to store the difference of accumulated 0s and 1s.
     * Hint: https://discuss.leetcode.com/topic/79932/java-one-pass-o-n-solution-with-explanation
     */
    public static int findMaxLength(int[] nums) {
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] diff = new int[nums.length + 1];
        map.put(0, 0); //If nums is empty, the length is 0 and diff is 0
        for (int i = 1; i < nums.length + 1; i++) {
            diff[i] = diff[i - 1] + (nums[i - 1] == 0 ? -1 : 1);
            if (map.containsKey(diff[i])) {
                int currLen = i - map.get(diff[i]);
                ans = currLen > ans ? currLen : ans;
            } else {
                map.put(diff[i], i);
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        System.out.println(0%2);
        int[] nums = {0,0,1,0,0,0,1,1};
        int[] nums1 = {0,1};
        System.out.println(findMaxLength(nums));
    }
}
