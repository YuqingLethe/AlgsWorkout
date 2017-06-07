package LintCode.Binary.Recursive;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * Given a list of numbers that may has duplicate numbers, return all possible subsets

 Notice

 Each element in a subset must be in non-descending order.
 The ordering between two subsets is free.
 The solution set must not contain duplicate subsets.
 Have you met this question in a real interview? Yes
 Example
 If S = [1,2,2], a solution is:

 [
 [2],
 [1],
 [1,2,2],
 [2,2],
 [1,2],
 []
 ]
 */
public class LintRecursive18SubSetII {
    /**
     * @param nums: A set of numbers. 2017/6/6.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] nums) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }
        if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }
        Arrays.sort(nums);
        ArrayList<Integer> refSet = new ArrayList<>();
        helper(refSet, nums, 0, results);
        return results;
    }
    private void helper(ArrayList<Integer> refSet,
                        int[] nums,
                        int start,
                        ArrayList<ArrayList<Integer>> results) {
        results.add(new ArrayList<Integer>(refSet));
        for (int i = start; i < nums.length; i++) {
            if (i != start && nums[i] == nums[i - 1]) {
                continue;
            }
            refSet.add(nums[i]);
            helper(refSet, nums, i + 1, results);
            refSet.remove(refSet.size() - 1);
        }
    }
}
