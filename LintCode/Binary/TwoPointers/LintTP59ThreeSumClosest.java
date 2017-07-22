package LintCode.Binary.TwoPointers;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/7/22.
 */
public class LintTP59ThreeSumClosest {
    /**
     * 2017/7/22
     * 又翻了丢失全局观, 在牛角尖里出不来的毛病. 既然这个题只让返回sum, 就没必要存储最小差值
     * 不要被2Sum Closest影响
     */
    public int threeSumClosest(int[] numbers, int target) {
        if (numbers == null || numbers.length <= 2) {
            return -1;
        }
        Arrays.sort(numbers);
        int ans = numbers[0] + numbers[1] + numbers[2];
        for (int i = 0; i < numbers.length; i++) {
            int lo = i + 1;
            int hi = numbers.length - 1;
            while(lo < hi) {
                int sum = numbers[i] + numbers[lo] + numbers[hi];
                if (Math.abs(sum - target) < Math.abs(ans - target)) {
                    ans = sum;
                }
                if (sum > target) {
                    hi --;
                } else if (sum < target){
                    lo ++;
                } else {
                    return sum;
                }
            }
        }
        return ans;
    }
}
