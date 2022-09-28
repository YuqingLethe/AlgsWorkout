package Stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
 *
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 *
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
 *
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * Output: [-1,3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 *
 * Example 2:
 *
 * Input: nums1 = [2,4], nums2 = [1,2,3,4]
 * Output: [3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
 * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * All integers in nums1 and nums2 are unique.
 * All the integers of nums1 also appear in nums2.
 *
 *
 * Follow up: Could you find an O(nums1.length + nums2.length) solution?
 */
public class Stack496NextGreaterElementI {
    /**
     * Sep 2022 14min Crib Solution
     * 这个stack很巧妙, 可以学起来
     * 后面用getOrDefault就省了一个while循环把没找到next greater的放进map里面了
     *
     * Time O(m*n)
     * Space O(m)
     */
    class Stack_HashMap {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            final int N = nums1.length;
            final int N2 = nums2.length;
            int[] result = new int[N];
            if (nums1 == null || nums1.length == 0) {
                return result;
            }

            // Build the nextGreater to find the next greater number of every element in nums2
            Stack<Integer> stack = new Stack<>();
            HashMap<Integer, Integer> nextGreater = new HashMap<>();
            for (int i = 0; i < N2; ++i) {
                int val = nums2[i];
                // If greater than previous element, log to the hashmap. Otherwise keep push
                while (!stack.isEmpty() && stack.peek() < val) {
                    int prev = stack.pop();
                    nextGreater.put(prev, val);
                }
                stack.push(val);
            }
            // while (!stack.isEmpty()) {
            //     nextGreater.put(stack.pop(), -1);
            // }

            // Find the result by searching nextGreater for nums1's elements
            for (int i = 0; i < N; ++i) {
                result[i] = nextGreater.getOrDefault(nums1[i], -1);
            }
            return result;
        }
    }

    /**
     * 自己本来想的binary search没想到hashmap
     * code19min Sep 2022
     * 这里result[i]预设成-1可以减少一个condition的查看. 否则第二个for loop里面还要
     *   if (j == nums2.length)
     *
     * Time: O(n)
     * Space: O(n)
     */
    class HashMapSolution {
         public int[] nextGreaterElement(int[] nums1, int[] nums2) {
             int[] result = new int[nums1.length];
             if (nums1 == null || nums1.length == 0) {
                 return result;
             }

             HashMap<Integer, Integer> hm = new HashMap<>();
             for (int i = 0; i < nums2.length; ++i) {
                 hm.put(nums2[i], i);
             }
             for (int i = 0; i < nums1.length; ++i) {
                 int nums2Idx = hm.get(nums1[i]);
                 result[i] = -1; // Set to not find and update if find the greater element
                 for (int j = nums2Idx; j < nums2.length; ++j) {
                     if (nums2[j] > nums1[i]) { // find next greater
                         result[i] = nums2[j];
                         break;
                     }
                 }
             }
             return result;
        }
    }

}
