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
     * Runtime: 5ms   11/16/2016
     * If use Arrays.copyOf() only 4ms
     * https://discuss.leetcode.com/topic/59997/java-3ms-beats-95-33-using-binary-search
     */
    public static int[] intersectByBinarySearch(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int [] tmp = nums1;
            nums1 = nums2; //The shorter array
            nums2 = tmp; //The larger array
        }
        Arrays.sort(nums2);
        Arrays.sort(nums1);

        ArrayList<Integer> al = new ArrayList<>();
        int startIndex = 0;
        for (int i = 0; i < nums1.length; i++) {
            int locate = binarySearch(nums2, startIndex, nums2.length - 1, nums1[i]);
            if (locate != -1) {
                al.add(nums2[locate]);
                startIndex = locate + 1;
            }
        }

        int[] answer = new int[al.size()];
        for (int i = 0; i < al.size(); i++) {
            answer[i] = al.get(i);
        }
        return answer;
    }
    private static int binarySearch (int[] num, int start, int end, int target) {
        while(start <= end) {
            int mid = start + (end - start)/2;
            if (num[mid] == target ) {
                if (mid == start || (mid > start && num[mid - 1] != target)) return mid;
                else {
                    end = mid - 1;
                }
            } else if (num[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {1, 1};
        int[] answer = intersectByBinarySearch(nums1, nums2);
        for (int i : answer) {
            System.out.print(i + " ");
        }
    }
}
