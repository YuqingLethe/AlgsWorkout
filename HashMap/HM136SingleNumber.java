package HashMap;

import java.util.LinkedList;

public class HM136SingleNumber {
    //Time Exceeded 9/28/2016
    private static int singleNumberByMemory(int[] nums) {
        boolean[] code = new boolean[nums.length]; //Has found the duplicate or not
        for (int i = 0; i < nums.length; i++) {
            if (code[i]) { continue; }
            for (int j = i + 1; j < nums.length; j++) {
                if (!code[j]  && nums[i] == nums[j]) {
                    code[i] = true;
                    code[j] = true;
                }
            }
            if (!code[i]) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * Runtime: 1ms Use: 1min
     * @param nums
     * @return
     */
    private static int singleNumberByBit(int[] nums) {
        int diff = 0;
        for (int n: nums) {
            diff = diff^n;
        }
        return diff;
    }


    public static void main(String[] args) {
        int[] x = {3,3,1,2,1,4,4,2,6};
        System.out.println(singleNumberByBit(x));
    }
}
