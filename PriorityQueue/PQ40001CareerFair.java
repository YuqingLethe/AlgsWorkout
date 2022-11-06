package PriorityQueue;

import java.util.Arrays;

/**
 * Problem Description: https://leetcode.com/discuss/interview-question/374846/Twitter-or-OA-2019-or-University-Career-Fair
 */
public class PQ40001CareerFair {
    /**
     * Nov 2022 Crib Answer
     * 一开始又模板思维先行, 想用backtracking来着. 其实不用, 因为每个invention开始时间都是固定的. 因为直接最大化数目,
     * 也不存在组合的情况了, 只要开始时间最近, 用时最短就是最优解.
     *
     * 得到最优解的两个条件, 开始时间和用时, 题目直接给出来了. 所以简单排序或者PQ就比较合理.
     *
     * 有因为判断开始时间需要用到上一轮的结束时间, 因此在构建interval[][]的时候稍加优化,直接使用结束时间, 更直接.
     */
    static class Greedy {
        public int getMaxCareerFair (int[] arrival, int[] duration) {
            final int N = arrival.length;
            final int maxInvention = 4;

            // Get start and end time of invention
            int[][] intervals = new int[N][2];
            for (int i = 0; i < N; ++i) {
                intervals[i][0] = arrival[i];
                intervals[i][1] = arrival[i] + duration[i];
            }

            // sort by end time
            Arrays.sort(intervals, (a, b) -> (a[1] - b[1]));

            // Find maximum solution
            int currTime = intervals[0][1];
            int count = 1;
            for (int i = 1; i < N; ++i) {
                if (intervals[i][0] >= currTime) {
                    currTime = intervals[i][1];
                    count ++;
                }
            }
            return count;
        }
    }
    public static void main(String[] args) {
        Greedy solution = new Greedy();
        int[] arrival1 = {1,3,3,5,7};
        int[] duration1 = {2,2,1,2,1};
        System.out.println(solution.getMaxCareerFair(arrival1, duration1));
        int[] arrival2 = {1,3,3,5,7, 6, 9};
        int[] duration2 = {2,2,1,2,1, 10, 2};
        System.out.println(solution.getMaxCareerFair(arrival2, duration2));
        int[] arrival = {1,3,35,7};
        int[] duration = {2,2,1,2,1};
        System.out.println(solution.getMaxCareerFair(arrival, duration));
    }
}
