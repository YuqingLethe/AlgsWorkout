package LintCode.Binary.TwoPointers;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/7/10.
 */
public class LintTP521RemoveDuplicateNumbersInArray {
    /**
     * 本来想用左右指针, 后来发现无order限制其实可以随意变换nums的值, 只要从一个方向就够了.
     * 原理都是一个指向可以改变(已经重复)的值, 一个指向新的值
     * @param nums an array of integers
     * @return the number of unique integers
     */
    public int deduplication(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int i = 0, j = 0;
        for (; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j]; //这里如果i先加就对了, 处理了[1,2,3]的起始情况
            }
        }
        return i + 1;

    }
}
