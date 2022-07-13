package NewDataStructure;

import javafx.util.Pair;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 *
 You have n jobs and m workers. You are given three arrays: difficulty, profit, and worker where:

 difficulty[i] and profit[i] are the difficulty and the profit of the ith job, and
 worker[j] is the ability of jth worker (i.e., the jth worker can only complete a job with difficulty at most worker[j]).
 Every worker can be assigned at most one job, but one job can be completed multiple times.

 For example, if three workers attempt the same job that pays $1, then the total profit will be $3. If a worker cannot complete any job, their profit is $0.
 Return the maximum profit we can achieve after assigning the workers to the jobs.



 Example 1:

 Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 Output: 100
 Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get a profit of [20,20,30,30] separately.
 Example 2:

 Input: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
 Output: 0


 Constraints:

 n == difficulty.length
 n == profit.length
 m == worker.length
 1 <= n, m <= 104
 1 <= difficulty[i], profit[i], worker[i] <= 105

 *
 *
 * 自己最開始想的是用hashmap來創建jobs， 但是不行的。
 * 1. 不好sort， hashmap本身沒有順序， 即便寫了comparator也沒法按順序取值
 * 2. （最致命）不好解決duplicate的問題， 一開始就不應該想用hashmap
 * 牢記這兩個two values的數據結構！
 */
public class Comparator826MostProfitAssigningWork {
    /**
     * July 2022 Crib answer
     * https://leetcode.com/problems/most-profit-assigning-work/discuss/127031/C%2B%2BJavaPython-Sort-and-Two-pointer
     */
    class UsePair {
        public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
            List<Pair<Integer, Integer>> jobsSortByDifficulty = new ArrayList<>();
            int N = difficulty.length, res = 0;
            for (int i = 0; i < N; ++i) {
                jobsSortByDifficulty.add(new Pair<Integer, Integer>(difficulty[i], profit[i]));
            }
            // 這裏爲什麼不需要給 profit 排序？
            // 因爲這個profit和最後的result相關， 和best相關。 只要求出maximum best即可， 因此無需再排序了
            Collections.sort(jobsSortByDifficulty, Comparator.comparing(Pair::getKey));
            Arrays.sort(worker);
            int best = 0;
            int currDifficulty = 0;
            for (int skill: worker) {
                // Should not reset 'best', if profit decreases, should do the previous job.
                while (currDifficulty < N && skill >= jobsSortByDifficulty.get(currDifficulty).getKey()) {
                    best = Math.max(best, jobsSortByDifficulty.get(currDifficulty).getValue());
                    currDifficulty++;
                }
                res += best;
            }
            return res;

        }
    }

    /**
     * Crib answer from Leetcode standard answer. July 2022
     * To the end any two values data structure works!
     */
    class usePoint {
        public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
            int N = difficulty.length;
            Point[] jobs = new Point[N];
            for (int i = 0; i < N; ++i) {
                jobs[i] = new Point(difficulty[i], profit[i]);
            }
            Arrays.sort(jobs, (a, b) -> (a.x - b.x));

            Arrays.sort(worker);
            int result = 0, best = 0, currMostProfitableSkill = 0;

            for (int skill : worker) {
                while (currMostProfitableSkill < N && skill >= jobs[currMostProfitableSkill].x) {
                    best = Math.max(best, jobs[currMostProfitableSkill].y);
                    currMostProfitableSkill++;
                }
                result += best;
            }
            return result;

        }
    }
}
