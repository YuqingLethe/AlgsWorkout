package LintCode.Binary.Recursive;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/6/29.
 *
 *
 * >> vs >>>
 * 正数右移，高位用0补，负数右移，高位用1补，当负数使用无符号右移时，用0进行部位(自然而然的，就由负数变成了正数了)
 * 注意：笔者在这里说的是右移，高位补位的情况。正数或者负数左移，低位都是用0补。(自行测试)
 */
public class LintRecursive17Subsets {
    /**
     * Non-recursive 6/29/2017 不会做 看答案做的
     * 2017/6/29
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
                if ((i & (1 << j)) != 0) { // 1 << n is 2^n 注意括号
                    al.add(nums[j]);
                }
            }
            results.add(al);
        }
        return results;
    }
}
