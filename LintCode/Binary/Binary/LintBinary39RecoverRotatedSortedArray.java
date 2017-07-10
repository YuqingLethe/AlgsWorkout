package LintCode.Binary.Binary;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/2. 关联题目LintBinary159
 * Given a rotated sorted array, recover it to sorted array in-place.
 *  the orginal array is [1,2,3,4], The rotated array of it can be [1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]
 * [4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
 */
public class LintBinary39RecoverRotatedSortedArray {
    /**
     * @param nums: The rotated sorted array
     * @return: void
     */
    public void recoverRotatedSortedArray(ArrayList<Integer> nums) {
        // if (nums.isEmpty() || nums.size() == 0) {
        //     return nums;
        // }
        //Find the minimum value as the start of the original array
        int lo = 0;
        int hi = nums.size() - 1;
        int min = -1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            //注意全部是同hi位置的值比较, 不是数组最末值!!!
            //为什么这里不能用nums.get(0)参与比较? [1,1,-1,1,1]
            //可以用nums.get(lo)参与比较吗?
            if (nums.get(hi) == nums.get(mid)) {
                hi--;
            } else if (nums.get(mid) < nums.get(hi)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        if (nums.get(lo) > nums.get(hi)) {
            min = hi;
        } else {
            min = lo;
        }
        //Rotate the array: Challenge要求不会做
        int partlen = nums.size() - min;
        int[] tmpArr = new int[partlen];
        for (int i = 0; i < partlen; i++) {
            tmpArr[i] = nums.get(min);//这里有个bug 每次remove nums变短所以应该一直nums.get(min)
            nums.remove(min);
        }
        for (int i = partlen - 1; i >= 0; i--) {
            nums.add(0, tmpArr[i]);
        }
    }

    /**
     * 7/10/2017Two pointers, each time move one step
     */
    public void recoverRotatedSortedArrayTP(ArrayList<Integer> nums) {
        if (nums == null || nums.size() < 2) {
            return;
        }
        int i = 0;
        //注意这里i + 1考虑溢出  以及 <= 而非 <
        while(i < nums.size() - 1 && nums.get(i) <= nums.get(i + 1)) {
            i++;
        }

        int restLen = nums.size() - i - 1;

        while(i >= 0) {
            int crt = nums.get(i);
            for (int j = 0; j < restLen; j++) {
                nums.set(i + j, nums.get(i + j + 1));
            }
            nums.set(i + restLen, crt);
            i--;
        }
    }
}
