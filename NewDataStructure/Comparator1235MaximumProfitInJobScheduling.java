package NewDataStructure;

import java.util.*;

/**
 * Created by yuqing on 7/11/22.
 */
public class Comparator1235MaximumProfitInJobScheduling {

    /**
     * July 2022 自己想的， timeout failure
     *
     * 如果使用DP的話不需要在這基礎上加邏輯。 因爲DP必然是找出一定範圍的max profit， 問題本身就是答案。 應該順着大範圍到局部範圍這麼想。
     */
    static class BruteForthAllSolutions {
        static int maxProfit = 0;
        static List<int[]> jobs;
        static int startAll;
        static int endAll;

        public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            int N = startTime.length;

            // build jobs and find startAll and endAll time stamp
            jobs = new ArrayList<>();
            for (int i = 0; i < N; ++i) {
                endAll = Math.max(endAll, endTime[i]);
                int[] curr = {startTime[i], endTime[i], profit[i]};
                jobs.add(curr);
            }
            Collections.sort(jobs, (a, b) -> (a[0] - b[0]));
            startAll = jobs.get(0)[0];
            for (int i = 0; i < N; ++i) { //之前錯誤的地方， 這裏應該遍歷所有jobs，否則進入helper知識按照startTS找了
                helper(startAll, i, 0);
            }
            return maxProfit;

        }

        private static void helper(int startTS, int currJobIdx, int currProfit) {
            System.out.println(startTS + " " + currJobIdx + " " + currProfit);
            // 不需要這部分， 因爲這裏漏掉了一個case。
//            if (startTS >= endAll) {
//                this.maxProfit = Math.max(this.maxProfit, currProfit);
//                return;
//            }
            for (int i = currJobIdx; i < jobs.size(); ++i) {
                if (jobs.get(currJobIdx)[0] >= startTS) {
                    int nextProfit = currProfit + jobs.get(currJobIdx)[2];
                    int nextStartTS = jobs.get(currJobIdx)[1];
                    helper(nextStartTS, i + 1, nextProfit);
                    System.out.println("Back to loop: " + startTS + " " + currJobIdx + " " + currProfit);
                }
            }
            //這裏應該考慮到最後任務時間沒到endAll的時候， 但也沒有機會執行別的了。 因此只要落在這個地方都應該檢查更新maxProfit. 這個method開頭沒必要檢查了
            maxProfit = Math.max(maxProfit, currProfit);
            return;
        }

        private static void printInfo(int N) {
            System.out.println(startAll);
            System.out.println(endAll);
            for (int i = 0; i < N; ++i) {
                System.out.println(jobs.get(i)[0] + " " + jobs.get(i)[1] + " " + jobs.get(i)[2]);
            }
        }

    }

    /**
     * July2022 Crib solution from
     * https://leetcode.com/problems/maximum-profit-in-job-scheduling/discuss/733167/Thinking-process-Top-down-DP-Bottom-up-DP
     *
     * 分析下這個DP， 之前我卡住的地方是不知道怎樣得到特定範圍（從time0到time curr）的最大值。
     */
    class DP_TopDown_SortByStartTime {
        Map<Integer, Integer> dp;

        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            int N = startTime.length;
            dp = new HashMap<>();

            int[][] jobs = new int[startTime.length][3];
            for (int i = 0; i < N; ++i) {
                jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
            }
            Arrays.sort(jobs, (a, b) -> (a[0] - b[0]));

            return dfs(0, jobs);

        }
        private int dfs(int curr, int[][] jobs) {
            if (curr == jobs.length) {
                return 0;
            }
            if (dp.containsKey(curr))  {
                return dp.get(curr);
            }
            int next = findNext(curr, jobs);
            int includeProfit = jobs[curr][2] + (next == -1 ? 0 : dfs(next, jobs));
            int excludeProfit = dfs(curr + 1, jobs);

            dp.put(curr, Math.max(includeProfit, excludeProfit));
            return Math.max(includeProfit, excludeProfit);
        }

        /**
         * 這裏next = curr + 1其實應該寫爲next = curr
         * @param curr
         * @param jobs
         * @return
         */
        private int findNext(int curr, int[][] jobs) {
            for (int next = curr + 1; next < jobs.length; ++next) {
                if (jobs[curr][1] <= jobs[next][0]) {
                    return next;
                }
            }
            return -1;
        }

    }

    /**
     * July2022 Crib same solution of Top-down
     *
     * 這個bottom up簡單很多， 似乎也沒有很難想。
     * FindNext還可以用binary tree
     */
    class DP_BottomUp {

        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            int N = startTime.length;
            int[] dp = new int[N + 1];
            int[][] jobs = new int[N][3];
            for (int i = 0; i < N; ++i) {
                jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
            }
            Arrays.sort(jobs, (a, b) -> (a[0] - b[0]));
            dp[N - 1] = jobs[N - 1][2];

            for (int curr = N - 2; curr >= 0; --curr) {
                int next = findNext(curr, jobs);
                dp[curr] = Math.max(
                        jobs[curr][2] + (next == -1 ? 0 : dp[next]),
                        dp[curr + 1] //這個是一定存在的。。。。。
                );
            }
            return dp[0];

        }

        private int findNext(int cur, int[][] jobs) {
            for (int next = cur + 1; next < jobs.length; next++) {
                if (jobs[next][0] >= jobs[cur][1]) {
                    return next;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
//        int[] start = {1,2,3,4,6};
//        int[] end = {3,5,10,6,9};
//        int[] profit = {20,20,100,70,60};
//
        int[] start = {1,1,1};
        int[] end = {2,3,4};
        int [] profit = {5,6,4};

        int answer = BruteForthAllSolutions.jobScheduling(start, end, profit);

        System.out.println(answer);


    }
}
