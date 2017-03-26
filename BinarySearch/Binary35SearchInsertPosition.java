package BinarySearch;

public class Binary35SearchInsertPosition {
    /**
     * Runtime: 6ms Use: 20min 3.26.3017
     * thinking when low < 0 condition .
     */
    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while(low <= high) {
            int mid = low + (high - low)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high > low ? high : low;
    }
    //123
}
