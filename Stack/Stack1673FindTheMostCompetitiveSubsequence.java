package Stack;

public class Stack1673FindTheMostCompetitiveSubsequence {
    /**
     * 20210121 40min timeout for 1 2 3 4 5......
     * @param nums
     * @param k
     * @return
     */
    public int[] mostCompetitiveTimeout(int[] nums, int k) {
        // 11:46
        if (k == 0 || nums == null) {
            return null;
        }
        if (k == 1 && nums.length == 1) {
            return nums;
        }
        int[] res = new int[k];
        return findNextBestSubsequence(nums, res, 0, k);

    }
    private int[] findNextBestSubsequence(int[] nums, int[] res, int from, int k) {
        if (k == 0) {
            return res;
        }
        int smallest = from;
        for (int i = nums.length - k; i >= from; i --) {
            if (nums[i] <= nums[smallest]) { //注意这里是<= 因为要取最左边的！
                smallest = i;
            }
        }
        res[res.length - k] = nums[smallest];
        return findNextBestSubsequence(nums, res, smallest + 1, k - 1);

    }

    public static void main(String[] args) {
        int[] nums1 = {1,1,2,3,1,1};
        int[] nums2 = {3,5,2,6};
        int[] nums3 = {2,4,3,3,5,4,9,6};
        int k = 4;
        int[] ans3 = {2,3,3,4};
        Stack1673FindTheMostCompetitiveSubsequence myClass = new Stack1673FindTheMostCompetitiveSubsequence();
        myClass.mostCompetitiveTimeout(nums1, 1);
    }
}