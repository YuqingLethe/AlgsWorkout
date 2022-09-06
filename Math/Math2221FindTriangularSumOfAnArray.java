package Math;

public class Math2221FindTriangularSumOfAnArray {
    /**
     * Sep 15min O(N!)
     */
    static class Greedy {
        public int triangularSum(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int N = nums.length; // Length of array for every round
            //DeepCopy
            int[] array = new int[N];
            for (int i = 0; i < N; ++i) {
                array[i] = nums[i];
            }

            while (N > 1) {
                for (int i = 0; i < N - 1; ++i) {
                    int newValue = (array[i] + array[i + 1]) % 10;
                    // System.out.print(newValue + " ");
                    array[i] = newValue;
                }
                N --;
                System.out.println();
            }
            return array[0];
        }
    }
    public static void main(String []argh)
    {
        int[] arr;

         arr = new int[]{2, 2, 5, 6, 9, 2, 11};
         printResult(1, arr, 7);

         arr = new int[]{1,2,3,4};
         printResult(2, arr, 0);

         arr = new int[]{1,2,3,4,5};
         printResult(3, arr, 8);

        arr = new int[]{1};
        printResult(4, arr, 1);

        arr = new int[]{1, 2};
        printResult(5, arr, 3);

        arr = new int[]{};
        printResult(6, arr, 0);

    }

    /**
     * https://leetcode.com/problems/find-triangular-sum-of-an-array/discuss/1907380/O(n)-time-O(1)-space-Python-189-ms-C%2B%2B-12-ms-Java-4-ms
     * TODO: low priority
     */
    static class Math {

    }
    private static void printResult(int testNum, int[] power, int expect) {
        Greedy solution = new Greedy();
        int mine = solution.triangularSum(power);
        System.out.println("------------ Test Case " + testNum + " ------------");
        System.out.println("Answer: " + mine);
        System.out.println("Expect: " + expect);
    }
}
