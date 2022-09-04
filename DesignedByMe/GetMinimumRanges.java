package DesignedByMe;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 题目: 给出几组范围组合来定义一个或多个大范围, 删掉一些组合, 用最少组合数目达到同样地范围划分
 *
 * Example 1:
 * Input: intervals = [[1,2],[2,3],[3,5],[4,5]]
 * Output: [[1,2],[2,3],[3,5]]
 * 原范围表达的总范围是1-5, [4， 5] 被删掉， 所以是结果是[[1,2],[2,3],[3,5]]依旧可以表达1-5
 *
 * Example 2:
 * arr = new int[][]{{1,10},{8,12}, {10,13}};
 * expect = new int[][]{{1,10},{10,13}}; [8,12]不需要了
 *
 * Example 3:
 * arr = new int[][]{{1,10},{8,12}, {10,13}, {100, 103}};
 * expect = new int[][]{{1,10},{10,13},{100, 103}}; [8,12]不需要了
 *
 * TODO: 还没写完
 *
 * 扩展:
 * 类似亚麻的 interval的题目
 */
public class GetMinimumRanges {

    // 11;25 - 12:11 -> 50min..... not all test pass....
    static class SortByLowerBoundAndRange_DoubleTraversal {
        // TODO: Yoki问 Yoki 倒序再来那么一遍就能过test case 3 6 7么
        public int mergeIntervals(int[][] arr) {
            if (arr == null || arr.length <= 1) {
                return 0;
            }
            // Sort the array, by lower bound first, then by range
            Arrays.sort(arr, new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    if (a[0] == b[0]) {
                        return (b[1] - b[0]) - (a[1] - a[0]);
                    }
                    return a[0] - b[0];
                }
            });
            int count = 0;

            // Every group, the first element of first pair is leftmost edge.
            int rangeLeft = arr[0][0];
            int rangeRight = arr[0][1];
            for (int i = 1; i < arr.length; ++i) {
                int left = arr[i][0];
                int right = arr[i][1];
                if (left >= rangeLeft && right <= rangeRight) { // already covered
                    System.out.println("Removed: " + left + " " + right);
                    count++;
                } else if (left > rangeRight) { //Start a new range cluster
                    rangeLeft = left;
                    rangeRight = right;
                } else { // rangeLeft <= Left <= rangeRight, right > rangeRight
                    rangeRight = right;
                }
            }
            // group intervals: If another range begin without intersection, all previous are good
            return count;
        }

    }
    public static void main(String[] args) {
        int[][] arr;

        arr = new int[][]{{1,2},{2,3},{3,5},{4,5}};
        printResult(1, arr, 1);

        arr = new int[][]{{1,2},{1,3},{3,5}};
        printResult(5, arr, 1);

        arr = new int[][]{{1,2},{1,3},{3,5},{4,5}};
        printResult(2, arr, 2);

        arr = new int[][]{{1,10},{11,13},{8,12}};
        printResult(3, arr, 1);

        arr = new int[][]{{1,10},{8,12}, {10,13}};
        printResult(6, arr, 1);

        arr = new int[][]{{1,10},{2,11}, {11,13}};
        printResult(7, arr, 1);

        arr = new int[][]{};
        printResult(4, arr, 0);

        arr = new int[][]{{}};
        printResult(5, arr, 0);
    }
    private static void printResult(int testCase, int[][] input, int expect) {
        SortByLowerBoundAndRange_DoubleTraversal solution = new SortByLowerBoundAndRange_DoubleTraversal();
        int mine = solution.mergeIntervals(input);

//        Solution solution = new Solution();
//        int mine = solution.merge(input);
        System.out.println("Answer: " + mine + " Test Case " + testCase);;
        System.out.println("Expect: " + expect);
        System.out.println("-----------------");
    }

}
