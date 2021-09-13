package BinarySearch;

public class Binary702SearchInASortedArrayOfUnknownSize {
    /**
     * Define boundaries:
     *
     * Initiate left = 0 and right = 1.
     * While target is on the right to the right boundary: reader.get(right) < target:
     * Set left boundary equal to the right one: left = right.
     * Extend right boundary: right *= 2. To speed up, use right shift instead of multiplication: right <<= 1.
     * Now the target is between left and right boundaries.
     */
    /**
     * // This is ArrayReader's API interface.
     * // You should not implement it, or speculate about its implementation
     * interface ArrayReader {
     *     public int get(int index) {}
     * }
     */

    class Solution {
        public int search(ArrayReader reader, int target) {
            if (reader.get(0) == target) {
                return 0;
            }
            int left = 1;
            int right = 2;
            while (reader.get(right) < target) {
                left = right;
                right <<= 1;
            }

            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (reader.get(mid) == target) {
                    return mid;
                } else if (reader.get(mid) < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }
    }
}
