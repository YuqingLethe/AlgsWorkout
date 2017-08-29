package LintCode.Binary.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    /**
     * 8/28/2017
     */
    public class SolutionHashSet {

        public int[] intersection(int[] nums1, int[] nums2) {
            // Write your code here
            if (nums1 == null || nums2 == null) {
                return new int[0];
            }
            Set<Integer> hash = new HashSet<>();
            for (int i = 0; i < nums1.length; i++) {
                if (!hash.contains(nums1[i])) {
                    hash.add(nums1[i]);
                }
            }

            Arrays.sort(nums2);
            ArrayList<Integer> results = new ArrayList<>();

            for (int i = 0; i < nums2.length; i++) {
                if (i != 0 && nums2[i] == nums2[i - 1]) {
                    continue;
                }
                if (hash.contains(nums2[i])) {
                    results.add(nums2[i]);
                }
            }

            int[] ans =  new int[results.size()];
            for (int i = 0; i < results.size(); i++) {
                ans[i] = results.get(i);
            }
            return ans;
        }
    }


    public class SolutionBinarySearch {
        /**
         * Don't know when did it
         */
        public int[] intersection(int[] nums1, int[] nums2) {
            // Write your code here
            if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
                return new int[0];
            }
            int[] shorter, longer;
            if (nums1.length <= nums2.length) {
                shorter = nums1;
                longer = nums2;
            } else {
                shorter = nums2;
                longer = nums1;
            }

            Arrays.sort(shorter);

            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < longer.length; i++) {
                int crt = longer[i];
                if (binarySearch(shorter, crt) && !set.contains(crt)) {
                    set.add(crt);
                }
            }

            int[] ans = new int[set.size()];
            int j = 0;
            for (Integer i : set) {
                ans[j++] = i;
            }
            return ans;
        }

        private boolean binarySearch(int[] nums, int target) {
            int lo = 0;
            int hi = nums.length - 1;
            while (lo + 1 < hi) {
                int mid = lo + (hi - lo) / 2;
                if (nums[mid] < target) {
                    lo = mid;
                } else if (nums[mid] > target) {
                    hi = mid;
                } else {
                    return true;
                }
            }
            if (nums[hi] == target || nums[lo] == target) {
                return true;
            }
            return false;
        }
    }
}
