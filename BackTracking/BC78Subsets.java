package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 8/29/21.
 */
public class BC78Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        subsetHelper(result, list, nums, 0);
        return result;
    }

    private void subsetHelper(List<List<Integer>> result,
                              ArrayList<Integer> list,
                              int[] nums,
                              int p) {
        result.add(new ArrayList<>(list));
        for (int i = p; i < nums.length; i++) {
            list.add(nums[i]);
            subsetHelper(result, list, nums, i + 1); // 把所有以i+1开头的子集都放在result里面， 不要想怎么完成的
            list.remove(list.size() - 1); //backtracking, 结合上一步进行下一个子集的查找， 比如【12】循环完毕开始【13】
        }
    }

    /**
     * https://leetcode.com/problems/subsets/solution/ Solution 3
     * The idea is that we map each subset to a bitmask of length n, where 1 on the ith position in bitmask means the presence of nums[i] in the subset, and 0 means its absence.
     *
     *
     * Non-recursive 6/29/2017 不会做 看答案做的
     * 2017/6/29
     *  >> vs >>>
     * 正数右移，高位用0补，负数右移，高位用1补，当负数使用无符号右移时，用0进行部位(自然而然的，就由负数变成了正数了)
     * 注意：笔者在这里说的是右移，高位补位的情况。正数或者负数左移，低位都是用0补。(自行测试)
     */
    public ArrayList<ArrayList<Integer>> SubsetsByBinary(int[] nums) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        Arrays.sort(nums); //不要忘了
        int n = nums.length;
        for (int i = 0; i < (1 << n); i++) {
            ArrayList<Integer> al = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // check whether the jth digit in i's binary representation is 1这里还要好好理解
                if ((i & (1 << j)) != 0) { // 1 << n is 2^n 注意括号
                    al.add(nums[j]);
                }
            }
            results.add(al);
        }
        return results;
    }

    public static void main(String[] args) {
         System.out.println(0&(1<<3));
    }
}
