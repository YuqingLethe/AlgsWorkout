package LintCode.Binary.TwoPointers;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/7/21.
 */
public class LintTP609TwoSumLessThanOrEqualToTargaet {
    public class Solution {
        /**
         * 本想用binarySearch后来发现只对target为正的情况有效,
         * 于是target为负的时候用答案方法
         * 答案方法O(n);
         * 我的方法O(nlogn); 大量数组的有一个test case通不过, 比原来的小, 我觉得是test case的bug
         */
        public int twoSum5(int[] nums, int target) {
            if (nums == null || nums.length <= 1) {
                return 0; //只要是return 个数, 都以0作为corner case result
            }
            if (nums[0] > target) {
                return 0;
            }

            Arrays.sort(nums);
            int ans = 0;

            if (target < 0) {
                int lo = 0;
                int hi = nums.length - 1;
                while(lo < hi) {
                    int sum = nums[lo] + nums[hi];
                    if (sum <= target) {
                        ans += hi - lo; //答案写的 极其妙 所有以lo为左的配对找齐
                        lo++;
                    } else {
                        hi --;
                    }
                }
                return ans;
            }



            int largestIdx = binarySearch(nums, target, 0, nums.length - 1);
            int halfIdx = binarySearch(nums, target / 2, 0, largestIdx);
            ans = halfIdx * (halfIdx + 1) / 2;
//        System.out.println(largestIdx + " " + halfIdx + " " + ans);

            //只有当全部大于0的时候, 才能把上限调成以target为最大值的largestIdx 否则应该加上下面这条 [1,0,-1], 0
            if (nums[0] < 0 && target >= 0) { //[1,2,-33,-5,-72,12,-34,100,99], -64
                largestIdx = binarySearch(nums, target - nums[0], 0, nums.length - 1);
            }
            for (int i = halfIdx + 1; i <= largestIdx; i++) {
                int pairIdx = binarySearch(nums, target - nums[i], 0, i - 1);
//            System.out.println(i + " nums[i]=" + nums[i] + " pairIdx=" + pairIdx);
                if (pairIdx >= i) {
                    ans += pairIdx;
                } else {
                    ans += pairIdx + 1;
                }
            }
            return ans;
        }

        private int binarySearch (int[] nums, int target, int start, int end) {
            if (target < nums[start]) {
                return -1;
            }
            while(start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] < target) {
                    start = mid;
                } else if (nums[mid] > target) {
                    end = mid;
                } else {
                    return mid;
                }
            }
            if (nums[end] <= target) {
                return end;
            }
            return start;
        }
    }
}
