package Greedy;

import java.util.Arrays;

/**
 * You are given an array of positive integers arr. Perform some operations (possibly none) on arr so that it satisfies these conditions:
 *
 * The value of the first element in arr must be 1.
 * The absolute difference between any 2 adjacent elements must be less than or equal to 1. In other words, abs(arr[i] - arr[i - 1]) <= 1 for each i where 1 <= i < arr.length (0-indexed). abs(x) is the absolute value of x.
 * There are 2 types of operations that you can perform any number of times:
 *
 * Decrease the value of any element of arr to a smaller positive integer.
 * Rearrange the elements of arr to be in any order.
 * Return the maximum possible value of an element in arr after performing the operations to satisfy the conditions.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,2,1,2,1]
 * Output: 2
 * Explanation:
 * We can satisfy the conditions by rearranging arr so it becomes [1,2,2,2,1].
 * The largest element in arr is 2.
 * Example 2:
 *
 * Input: arr = [100,1,1000]
 * Output: 3
 * Explanation:
 * One possible way to satisfy the conditions is by doing the following:
 * 1. Rearrange arr so it becomes [1,100,1000].
 * 2. Decrease the value of the second element to 2.
 * 3. Decrease the value of the third element to 3.
 * Now arr = [1,2,3], which satisfies the conditions.
 * The largest element in arr is 3.
 * Example 3:
 *
 * Input: arr = [1,2,3,4,5]
 * Output: 5
 * Explanation: The array already satisfies the conditions, and the largest element is 5.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 105
 * 1 <= arr[i] <= 109
 */
public class Greedy1846MaximumElementAfterDecreasingAndRearranging {
    /**
     * Sep 2022 // 7:23-7:45
     */
    static class Sort_Greedy {
        public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
            // Deep copy input array
            int N = arr.length;
            int[] array = new int[N];
            for (int i = 0; i < N; ++i) {
                array[i] = arr[i];
            }
            // Re-arrange elements to be accending order
            Arrays.sort(array);

            // Build increments of 1 if next element value is larger than the previous element.
            int prev = 1;
            for (int i = 1; i < N; ++i) {
                if (array[i] > prev) {
                    array[i] = prev + 1;
                    prev = prev + 1;
                }
            }
            return N > 1 ? array[N - 1] : 1; //忘记考虑arr.length == 1的情况了!!!!
        }
    }
    public static void main(String[] args) {
        int[] arr;

        arr = new int[]{2,2,1,2,1};
        printResult(1, arr, 2);

        arr = new int[]{91205};
        printResult(5, arr, 2);

        arr = new int[]{100,0,1000};
        printResult(2, arr, 3);

        arr = new int[]{1,1,1000};
        printResult(3, arr, 2);

        arr = new int[]{};
        printResult(4, arr, -1);
    }
    private static void printResult(int testCase, int[] input, int expect) {
        Sort_Greedy solution = new Sort_Greedy();
        int mine = solution.maximumElementAfterDecrementingAndRearranging(input);

        System.out.println("Answer: " + mine + " Test Case " + testCase);;
        System.out.println("Expect: " + expect);
    }
}
