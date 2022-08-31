package DynamicProgramming;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * You are an delivery and you have some boxes that you have to deliver, but there are some conditions -
 *
 * You can take 2 boxes of same weight in one round
 * you can take 3 boxes of same weight in one round
 * You have to find the minimum number of rounds to deliver the boxes or -1 if it is not possible to deliver them.
 *
 * Input: boxes - [2, 2, 3, 3, 2, 4, 4, 4, 4, 4]
 * Output: 4
 * Explanation: 3 boxes of weight 2 in 1st round, 2 boxes of weight 3 in 2nd round, 3 boxes of wt 4 in 3rd and 2 boxes of wt 4 in 4th round.
 *
 * Input: boxes - [2, 3, 3]
 * Output: -1
 * Explanation: There is only one box with weight 2 and we can only take either 2 or 3 boxes in one round not lesser.
 *
 * https://leetcode.com/discuss/interview-question/1733741/Amazon-OA-or-SDE-Intern
 */
public class SHE_DeliverBoxesWithMinimalCarry {
    static class Sort_DP {
        private HashMap<Integer, Integer> cache;
        public int getOptimizedDelivery(int[] arr) throws Exception{
            if (arr == null || arr.length == 1) {
                return -1;
            }
            List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
            int result = 0;
            Collections.sort(list);
            System.out.println(list.toString());

            this.cache = new HashMap<>();
            int startIdxOfCurrBox = 0;
            for (int i = 0; i < arr.length; ++i) { //看计数怎么计算决定到哪里为止.
                int count;
                if (i == arr.length - 1 && list.get(i) == list.get(startIdxOfCurrBox)) {
                    count = i - startIdxOfCurrBox + 1;
                } else if (i == arr.length  - 1 && list.get(i) != list.get(i - 1)) {
                    return -1;
                } else if (list.get(i) == list.get(startIdxOfCurrBox)) {
                    continue;
                } else {
                    count = i - startIdxOfCurrBox;
                }
                // Get minimal delivery of the current box
                int currDelivery = getMinimalRoundsByBoxNumber(count);
                if (currDelivery == -1) { //这一步不要忘了. 写代码之前就要想清楚, 哪里判断count == -1才是正理.
                    return -1;
                } else {
                    result += currDelivery;
                }
                startIdxOfCurrBox = i;
            }
            return result;
        }
        private int getMinimalRoundsByBoxNumber(int count) throws Exception{
            if (count == 1) {
                return -1;
            }
            if (cache.containsKey(count)) {
                return cache.get(count);
            }
            if (count == 2) {
                cache.put(2, 1);
                return 1;
            }
            if (count == 3) {
                cache.put(3, 1);
                return 1;
            }
            if (count == 4) {
                cache.put(4, 2);
                return 2;
            }
            // If using math result = ceil(count/3)
            int roundsOfMinusTwo = getMinimalRoundsByBoxNumber(count - 2);
            int roundsOfMinusThree = getMinimalRoundsByBoxNumber(count - 3);
            int finalRounds = Math.min(roundsOfMinusTwo, roundsOfMinusThree) + 1;
            cache.put(count, finalRounds);
            return finalRounds;
        }
    }

    public static void main(String[] args) throws Exception{
        Sort_DP solution = new Sort_DP();
        int[] test;
        int ans;
//        test = new int[]{2, 2, 3, 3, 2, 4, 4, 4, 4, 5};
//        ans = solution.getOptimizedDelivery(test);
//        if (ans == -1) {
//            System.out.println("Test 1 passed: " + ans);
//        }

//        test = new int[]{2, 3, 3};
//        ans = solution.getOptimizedDelivery(test);
//        if (ans == -1) {
//            System.out.println("Test 2 passed: " + ans);
//        }
//
//        test = new int[] {2,2,3,3,2,4,4,4,4,4};
//        ans = solution.getOptimizedDelivery(test);
//        if (ans == 4) {
//            System.out.println("Test 3 passed: " + ans);
//        }

//        test = new int[] {2,2,2,2,2,2,2};
//        ans = solution.getOptimizedDelivery(test);
//        if (ans == 3) {
//            System.out.println("Test 4 passed: " + ans);
//        }

//        test = new int[] {2,4,6,6,4,2,4};
//        ans = solution.getOptimizedDelivery(test);
//        if (solution.getOptimizedDelivery(test) == 3) {
//            System.out.println("Test 5 passed: " + ans);
//        }
//        test = new int[] {2,4,6,6,4,2,4,99};
//        ans = solution.getOptimizedDelivery(test);
//        if (solution.getOptimizedDelivery(test) == -1) {
//            System.out.println("Test 6 passed: " + ans);
//        }
//        test = new int[] {2};
//        ans = solution.getOptimizedDelivery(test);
//        if (solution.getOptimizedDelivery(test) == -1) {
//            System.out.println("Test 7 passed: " + ans);
//        }

//        test = new int[]{2, 3, 3, 4, 5, 5};
//        ans = solution.getOptimizedDelivery(test);
//        if (ans == -1) {
//            System.out.println("Test 8 passed: " + ans);
//        }

//        test = new int[]{3, 3, 3, 4, 5, 5};
//        ans = solution.getOptimizedDelivery(test);
//        if (ans == -1) {
//            System.out.println("Test 9 passed: " + ans);
//        }

        test = new int[]{3, 3, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
        ans = solution.getOptimizedDelivery(test);
        if (ans == -1) {
            System.out.println("Test 10 passed: " + ans);
        }

    }

}
