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
}
