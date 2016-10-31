package HashMap;

import java.util.*;

public class HT349IntersectionOfTwoArray {
    /**
     * Runtime: 19ms   Use: 20min  10/31/2016
     */
    public static int[] intersectionByHash(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) { return new int[0];}
        Set<Integer> s = new HashSet<>();
        int[] longNums = nums1.length >= nums2.length ? nums1 : nums2;
        int[] shortNums = nums1.length < nums2.length ? nums1 : nums2;

        for (int i = 0; i < shortNums.length; i++) {
            if (s.contains(shortNums[i])) continue;
            for ( int j = 0; j < longNums.length; j++) {
                if (shortNums[i] == longNums[j]) {
                    s.add(shortNums[i]);
                }
            }
        }
        int[] result = new int[s.size()];
        Iterator<Integer> it = s.iterator();
        int index = 0;
        while (it.hasNext()) {
            result[index++] = it.next();
        }
        return result;
    }

    /**
     * Runtime: 5ms Use: 30min 10/31/2016
     * One optimization inside the while loop, see comments
     */
    public static int[] intersectionBySort(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] longArray = nums1.length >= nums2.length ? nums1 : nums2;
        int[] shortArray = nums1.length < nums2.length ? nums1 : nums2;
        ArrayList<Integer> result = new ArrayList<>();

        int i = 0, j = 0; //index of two arrays
        while(i < shortArray.length && j < longArray.length) {
            if (shortArray[i] == longArray[j] ) {
                if (result.size() == 0
                    || (result.size() != 0 && shortArray[i] != result.get(result.size() - 1))
                    ){
                    //Check last element in result can be replaced by use SET to hold result numbers
                    //But will use 7ms, slower
                    result.add(shortArray[i]);
                }
                i++;
                j++;
            }
            else if (shortArray[i] < longArray[j]) {
                i++;
            }
            else if (shortArray[i] > longArray[j]) {
                j++;
            }
        }
        int[] r = new int[result.size()];
        int k = 0;
        for (int x : result) {
            r[k++] = x;
        }
        return r;
    }

    /**
     * RunTime: 7.s  Use:   10/31/2016
     * https://discuss.leetcode.com/topic/45685/three-java-solutions/2
     */
    public static int[] intersectionByBinarySearch(int[] nums1, int[] nums2) {
        Set<Integer> result = new HashSet<>();
        int[] longArray = nums1.length >= nums2.length ? nums1 : nums2;
        int[] shortArray = nums1.length < nums2.length ? nums1 : nums2;
        Arrays.sort(longArray);
        for (int i = 0; i < shortArray.length; i++) {
            if (binarySearch(longArray, shortArray[i])) {
                result.add(shortArray[i]);
            }
        }
        int i = 0;
        int[] r = new int[result.size()];
        for (Integer num : result)
            r[i++] = num;
        return r;
    }
    //Use: 3min - 20min 10/31/2016
    public static boolean binarySearch(int[] nums, int target) {
        int l = 0, h = nums.length - 1;
        while(l <= h) {
            int mid = (l + h)/2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                h = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
//        int[] nums1 = {0,2,2,3,3,8,13};
//        int[] nums2 = {3,2,8};
        int[] nums1 = {1};
        int[] nums2 = {1};
        int[] result = intersectionByBinarySearch(nums1, nums2);
        for (int x : result)
            System.out.print(x + " ");
        System.out.println(binarySearch(nums1, 3));
    }
}
