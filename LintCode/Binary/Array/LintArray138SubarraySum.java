package LintCode.Binary.Array;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/8.
 */
public class LintArray138SubarraySum {
    /**
     * 标准答案版本, 用preSum来帮助
     */
    public class Solution {

        public ArrayList<Integer> subarraySum(int[] nums) {
            // write your code here
            HashMap<Integer, Integer> preSum = new HashMap<>();
            ArrayList<Integer> ans = new ArrayList<>();

            int sum = 0;
            preSum.put(0, -1);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (preSum.containsKey(sum))  {
                    ans.add(preSum.get(sum) + 1); //返回下一个index, 因为从下一个起,和才为0
                    ans.add(i);
                    return ans;
                }

                preSum.put(sum, i);
            }

            return ans;
        }
    }
}
