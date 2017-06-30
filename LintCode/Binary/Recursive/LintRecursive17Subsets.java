package LintCode.Binary.Recursive;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/6/29.
 */
public class LintRecursive17Subsets {
    /**
     * Non-recursive 6/29/2017 不会做 看答案做的
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        Arrays.sort(nums); //不要忘了
        int n = nums.length;
        for (int i = 0; i < (1 << n); i++) {
            ArrayList<Integer> al = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // check whether the jth digit in i's binary representation is 1这里还要好好理解
                if ((i & (1 << j)) != 0) {
                    al.add(nums[j]);
                }
            }
            results.add(al);
        }
        return results;
    }
}
