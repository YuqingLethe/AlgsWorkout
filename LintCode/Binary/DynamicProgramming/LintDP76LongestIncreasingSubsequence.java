package LintCode.Binary.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on .
 */
public class LintDP76LongestIncreasingSubsequence {
    /**
     * 2017/7/17 自己没想到要用这么多辅助数组, 但存储的东西多, 的确需要这么干, 要不就resulttype
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int[] pre = new int[len]; //track the previous factor
        int[] f = new int[len]; //record the length of current subset
        f[0] = 1;
        pre[0] = -1;
        for (int i = 1; i < len; i++) {
            f[i] = 1;
            pre[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] == nums[i]) {
                    continue;
                }
                if (nums[i] % nums[j] == 0) {
                    pre[i] = j;
                    f[i] = f[j] + 1;
                    break; //遇到最大因子就停止, 因此应该break
                }
//                System.out.println("-- j=" + j + " f[j]=" + f[j]);
            }
//            System.out.println("i=" + i + " f[i]=" + f[i] + " pre[i]=" + pre[i]);
        }
        //find the largest subset
        int startIdx = -1;
        int max = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (max < f[i]) {
                max = f[i];
                startIdx = i;
            }
        }
        //insert into the answer list
        List<Integer> ans = new ArrayList<>();
        while(startIdx >= 0) {
            ans.add(nums[startIdx]);
            startIdx = pre[startIdx];
        }
        Collections.reverse(ans);
        return ans;
    }

}
