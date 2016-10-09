package HashMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HT350IntersactionOfTwoArrays2 {
    /**
     * Runtime: 9ms 10/8/2016
     * https://discuss.leetcode.com/topic/45920/ac-solution-using-java-hashmap
     */
    public int[] intersectByHashmap(int[] nums1, int[] nums2) {
        int[] longArray = nums1.length >= nums2.length ? nums1 : nums2;
        int[] shortArray = nums1.length < nums2.length ? nums1 : nums2;
        ArrayList<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : longArray) {
            int count = map.getOrDefault(x, 0) + 1;
            map.put(x, count);
        }
        for (int x : shortArray) {
            if (map.containsKey(x) && map.get(x)> 0) {
                result.add(x);
                int count = map.get(x);
                map.put(x, count - 1);
            }
        }
        int[] r = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            r[i] = result.get(i);
        }
        return r;
    }

    /**
     * Runtime: 6ms  10/9/2016
     */
    public int[] intersectBySort(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] longArray = nums1.length >= nums2.length ? nums1 : nums2;
        int[] shortArray = nums1.length < nums2.length ? nums1 : nums2;
        ArrayList<Integer> result = new ArrayList<>();

        int i = 0, j = 0; //indexes of two arrays
        while (i < longArray.length && j < shortArray.length) {
            if (longArray[i] == shortArray[j]) {
                result.add(longArray[i]);
                i++;
                j++;
            } else if (longArray[i] > shortArray[j]) {
                j++;
            } else {
                i++;
            }
        }
        int[] r = new int[result.size()];
        for (int k = 0; k < result.size(); k++) {
            r[k] = result.get(k);
        }
        return r;
    }
    /**
     * TODO: Use BinarySearch to solve the problem
     * https://discuss.leetcode.com/topic/59997/java-3ms-beats-95-33-using-binary-search
     */
    public int[] intersectByBinarySearch(int[] nums1, int[] nums2) {
        int[] r = new int[1];
        return r;
    }
}
