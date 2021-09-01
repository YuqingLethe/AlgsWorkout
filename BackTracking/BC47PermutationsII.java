package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BC47PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        ArrayList<Integer> array = new ArrayList<>(); // helper array
        Arrays.sort(nums);
        for (int num : nums) {
            array.add(num);
        }

        int n = nums.length;
        getPermutationsFromPAndPush(results, array, n, 0);
        return results;
    }
    /**
     * 沒想清楚的:
     * 1. backtracking的時候是i+1還是p+1? 定義到底是什麼, getPermutationsFromPAndPush目的是什麼?
     *     是爲了查找第二部分, 也就是[1,1,2]的[1] + [1,2]中的第二部分的permutations, 所以要用P+1
     * 2. 怎樣避免重複? 是nums[i]和nums[i-1]比較還是nums[i] nums[p]比較?
     * 3. 爲什麼call helper的時候要new新的helper list?
     */
    private void getPermutationsFromPAndPush(List<List<Integer>> results,
                                             ArrayList<Integer> array,
                                             int n,
                                             int p) {
        if (n == p) {
            results.add(new ArrayList<Integer>(array));
        } else {

            for (int i = p; i < n; ++i) {
                //要比較nums[i] 和nums[p]而不是i和i-1, 因爲第二部分不一定是sorted order
                if (i != p && array.get(i) == array.get(p)) {
                    continue;
                }
                Collections.swap(array, p, i);
                // 注意這裏要new一個array 不能用之前的!
                getPermutationsFromPAndPush(results, new ArrayList<>(array), n, p + 1); //注意這裏是P+1或者說first + 1, 不是i
            }
        }
    }

    /**
     * Other's solution 主要參考的答案!
     * https://leetcode.com/problems/permutations-ii/discuss/1427135/Java-solution-(swap-approach)-with-detailed-explanation-(easy-to-understand)
     */
    private void helper(int start, ArrayList<Integer> numList, List<List<Integer>> res) {
        if (start == numList.size()) {
            res.add(new ArrayList<>(numList));
        } else {
            for (int i = start; i < numList.size(); ++i) {
                if (i == start || !numList.get(start).equals(numList.get(i))) { // Deduplicate: skip swapping the same element to the start index;
                    Collections.swap(numList, start, i);  // Swap the element with the start index element, this will maintain the rest elements in an ascending order;
                    helper(start + 1, new ArrayList<>(numList), res); // We need to keep separate references for the following recursion;
                    // Collections.swap(numList, start, i);
                    // Note: we cannot revert it back to the original status otherwise there will be duplicates:
                    //      In this case, same elements will swap with the start index element, which results in duplicated brunches;
                    //      If we check (!numList.get(i - 1).equals(numList.get(i))) instead of (!numList.get(start).equals(numList.get(i))),
                    //          it will cause another issue which is after some backtracking, the second part of the array might not be in sorted order;
                    //          this will introduce duplicate brunches.
                }
            }
        }
    }
}
