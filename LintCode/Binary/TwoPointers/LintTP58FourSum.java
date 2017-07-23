package LintCode.Binary.TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/7/22.
 */
public class LintTP58FourSum {
    /**
     * 在debug的时候要注意问题有可能出在哪里, Time Limit也是有溢出情况的
     */
    public ArrayList<ArrayList<Integer>> fourSum(int[] numbers, int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 3; i++) {
            if (i != 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < numbers.length - 2; j++) {
                if (j != i + 1 && numbers[j] == numbers[j - 1]) {
                    continue;
                }
                int prevSum = numbers[i] + numbers[j];
                int lo = j + 1;
                int hi = numbers.length - 1;
                while(lo < hi) {
                    int twoSum = numbers[lo] + numbers[hi];
                    if (twoSum == target - prevSum) {
                        ArrayList<Integer> tmp = new ArrayList<>();
                        tmp.add(numbers[i]);
                        tmp.add(numbers[j]);
                        tmp.add(numbers[lo]);
                        tmp.add(numbers[hi]);
                        ans.add(tmp);
                        //不要忘了移动坐标, 要移动正确, 别翻了......
                        lo++;
                        hi--;
                        //这个重复也经常用, 要记住, 在下次开始的时候做不方便
                        while(lo < hi && numbers[lo] == numbers[lo - 1]) {
                            lo++;
                        }
                        while(lo < hi && numbers[hi] == numbers[hi + 1]) {
                            hi--;
                        }
                    } else if (twoSum < target - prevSum) {
                        lo++;
                    } else {
                        hi--;
                    }
                }

            }
        }
        return ans;

    }
}
