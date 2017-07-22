package LintCode.Binary.LinkedList;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Administrator on 2017/7/19.
 */
public class LintLL139SubarraySumClosest {
    /**
     * solution解法 2017/7/19
     * 怎样让HashMap可以排序或者pop出最接近值?
     * 把这个关系设为一个class, 然后存储城array, 直接Arrays.sort()!!
     */
    public class Solution {

        class Pair {
            int sum;
            int idx;
            public Pair(int a, int b) {
                sum = a;
                idx = b;
            }
        }
        public int[] subarraySumClosest(int[] nums) {
            if (nums == null || nums.length <= 1) {  //Failed: [2147483647]
                return new int[2];
            }
            // write your code here
            int sum = 0;
            int len = nums.length;
            Pair[] pairs = new Pair[len + 1];
            pairs[0] = new Pair(0, 0);
            for (int i = 1; i <= len; i++) {
                sum += nums[i - 1];
                Pair p = new Pair(sum, i);
                pairs[i] = p;
            }

            Arrays.sort(pairs, new Comparator<Pair>() {
                public int compare(Pair a, Pair b) {
                    return a.sum - b.sum;
                }
            });

            int min = Integer.MAX_VALUE;
            int[] ans = new int[2];
            for (int i = 1; i <= len; i++) {
                int diff = pairs[i].sum - pairs[i - 1].sum;
                if (diff < min) {
                    min = diff;
                    ans[0] = pairs[i].idx - 1; //注意这里, 起止idx虽然是0但取的sum值是之前的idx所以应该+ 1
                    ans[1] = pairs[i - 1].idx - 1;
                }

            }
            Arrays.sort(ans);
            ans[0] += 1; //不能在前面+ 1 一定要拍完序再+ 1
            return ans;
        }
    }
}
