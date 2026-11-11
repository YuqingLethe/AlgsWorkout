package Array;

/**
 * Created by Administrator on 2017/1/24.
 */
public class Array238ProductOfArrayExceptSelf {
    /**
     * Runtime: 2ms  Use: 15min   Debugging for zeros & multiple zeros
     */
    public int[] productExceptSelfByDivision(int[] nums) {
        int allProduct = 1;
        int indexOfZero = -1;
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (indexOfZero != -1) { //If more than one ZERO exist, ans should be all zero
                    allProduct = 0;
                    break;
                } else {
                    indexOfZero = i;
                    continue;
                }
            }
            allProduct *= nums[i];
        }
        int[] ans = new int[nums.length];
        if (indexOfZero != -1) {
            ans[indexOfZero] = allProduct;
            return ans;
        } else {
            for (int i = 0; i < nums.length; i++) {
                ans[i] = allProduct / nums[i];
            }
            return ans;
        }
    }

    class NoUseDivision2026 {
        /**
         * 思路：明知道两边array相乘就行，但是就没想起来用space complexity换time complexicy
         * 思路坑：1. 对于Left和Right  product array的1的处理
         * 2. left和right array得到以后不需要再整理left让全部向左移动，直接left[0] = 1的时候挺好用的
         *
         * 我错的点：1. 求left和right array的时候数组名字用错了，应该一个left一个nums
         * 2. 求Answer的index求懵了，其实用一个1234的简单数组一算就可以，这种都找最直观的，sorted升序的来整理思路
         * 3. Right array的for循环起始点错了
         *
         * single space complecity的没有意义不做了
         */
        public int[] productExceptSelf(int[] nums) {
            final int n = nums.length;
            // Build the profuct from left 1 1 2 6 24
            int[] left = new int[n + 1];
            left[0] = 1;
            for (int i = 1; i < n + 1 ; i ++) {
                left[i] = left[i - 1] * nums[i - 1];
            }

            // Build the right array
            int[] right = new int[n + 1];
            right[n] = 1;
            for (int i = n - 1; i >= 0; i --) {
                right[i] = right[i + 1] * nums[i];
            }

            // Build the final answer           24 24 12 4 1
            int[] answer = new int[n];
            for (int i = 0; i < n; i ++) {
                answer[i] = left[i] * right[i + 1];
            }
            // 1  1  2  6 24
            // 24 24 12 4 1
            return answer;
        }
    }
}
