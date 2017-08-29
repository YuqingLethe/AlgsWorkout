package LintCode.Binary.Array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/8/28.
 */
public class LintArray548IntersectionOfTwoArraysII {
    /**
     * 8/28/2017 O( min(m,n));
     */
    public class Solution {

        public int[] intersection(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
                return new int[0];
            }
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            ArrayList<Integer> results = new ArrayList<>();
            int a = 0;
            int b = 0;
            while (a < nums1.length && b < nums2.length) {
                if (nums1[a] == nums2[b]) {
                    results.add(nums1[a]);
                    a++;
                    b++;
                } else if (nums1[a] > nums2[b]) {
                    b++;
                } else {
                    a++;
                }
            }
            int[] answer = new int[results.size()];
            for (int i = 0; i < results.size(); i++) {
                answer[i] = results.get(i);
            }
            return answer;
        }
    }
}
