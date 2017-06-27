package LintCode.Binary.Recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/26.
 */
public class LintRecursive15Permutation {
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }
        if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }

        ArrayList<Integer> subset = new ArrayList<>();
        helper (subset, nums, results);
        return results;
    }
    private void helper (ArrayList<Integer> subset,
                         int[] nums,
                         List<List<Integer>> results) {
        if (subset.size() == nums.length) { //只有凑够三个才add
            results.add(new ArrayList<Integer>(subset));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(subset.contains(nums[i])){
                continue;
            }
            subset.add(nums[i]);
            helper(subset, nums, results);
            subset.remove(subset.size() - 1);
        }
    }
}
