package BinarySearch;

/**
 * Created by Administrator on 2017/6/6.
 */
public class Binary81SearchInRotatedSortedArrayII {
    /**
     * 不会做! 6/5/2017
     * https://discuss.leetcode.com/topic/25487/neat-java-solution-using-binary-search
     * 主要是根据lo mid hi的关系, 找到万全的条件来移动lo和hi
     * 1. 先想好以mid分界, 哪边一定是sorted array,
     * 2. 再根据target与sorted array的关系, 找lo hi移动的条件
     * 3. 单独提出来nums[lo] == nums[mid] == nums[hi]很不容易!
     */
    public boolean searchFakeBinary(int[] nums, int target) {
        if (nums == null || nums.length == 0)  {
            return false;
        }
        int lo = 0;
        int hi = nums.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[lo] > nums[mid] || nums[mid] < nums[hi]) { //right side must be sorted
                if (nums[mid] < target && target <= nums[hi]) {//注意<=的=别忘了啊!
                    lo = mid;
                } else {
                    hi = mid;
                }
            } else if (nums[lo] < nums[mid] || nums[mid] > nums[hi]) { //left side must be sorted
                if (nums[lo] <= target && target < nums[mid]) {
                    hi = mid;
                } else {
                    lo = mid;
                }
            } else { //three values equals!
                hi--;
            }
        }
        if (nums[lo] == target || nums[hi] == target)  {
            return true;
        }
        return false;
    }

    /**
     * 7/27/2017
     * 先把与分割点重复的数字做个处理, 掐头去尾
     * 再做类似于无重复的SearchInRotatedSortedArrayI来处理.
     * 因为即便nums[index] == target 也是lo或者hi从此固定在target的值那里,最后会判断的
     */
    public boolean search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int lo = 0;
        int hi = nums.length - 1;
        int last = nums[nums.length - 1];
        while (hi > 0 && nums[hi - 1] == last) { //[1], 0 只要nums[Idx - 1]就要添加条件!
            hi --;
        }
        while (lo < hi && nums[lo] == last) {
            lo ++;
        }
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] > last) {
                if (nums[mid] < target || target <= last) {
                    lo = mid;
                } else  {
                    hi = mid;
                }
            } else {
                if (nums[mid] > target || target > last) {
                    hi = mid;
                } else {
                    lo = mid;
                }
            }
        }
        if (nums[lo] == target) {
            return true;
        }
        if (nums[hi] == target) {
            return true;
        }
        return false;
    }
}
