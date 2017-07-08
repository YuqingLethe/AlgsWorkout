package LintCode.Binary.Array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/7/7.
 */
public class LintArray547IntersectionOfTwoArrays {
    /**
     * 2017/7/7
     * Sort & Merge, 里面有一个ArrayList去重小技巧, 原创
     */
    public class Solution {

        public int[] intersection(int[] nums1, int[] nums2) {
            // Write your code here
            if (nums1.length == 0 || nums2.length == 0) {
                return new int[0]; //不能return null
            }
            ArrayList<Integer> list = new ArrayList<>();
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int i = 0, j = 0;
            while(i != nums1.length && j != nums2.length) {
                if (nums1[i] == nums2[j]) {
                    //这个验证去重, 方式也巧妙, 原创
                    if (list.size() == 0 || list.get(list.size() - 1) != nums1[i]) {
                        list.add(nums1[i]);
                    }
                    i++;
                    j++;
                } else if (nums1[i] < nums2[j]) {
                    i++;
                } else {
                    j++;
                }
            }
            int[] ans = new int[list.size()];
            for (int k = 0; k < list.size(); k++) {
                ans[k] = list.get(k);
            }
            return ans;
        }
    }

    //Todo: HashMap

    //TODO: sort一个数组用来做binary search
}
