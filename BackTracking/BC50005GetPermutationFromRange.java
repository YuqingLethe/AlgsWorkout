package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.1point3acres.com/bbs/thread-937676-1-1.html
 *
 * get permutation based on range list.
 * e.g: [[2, 3], [3, 5]]
 * return ['23', '24', '25', '33', '34', '35']
 * 开始说的是0～9的数字，所以backtracking就行啦
 * follow up是会不会有repeated number，我说不‍‍‍‍‌‌‌‌‍‌‌‌‍‌‍‍会有
 * 又说假如range数字>9会不会有，我说会有。
 *
 * TODO: High priority 11/8
 */
public class BC50005GetPermutationFromRange {

    static class Self {
        private List<String> result;
        public List<String> getPermutationFromRangeList(int[][] rangeList) {
            result = new ArrayList<>();
            List<Integer> currPermutation = new ArrayList<>();
            helper(0, currPermutation, rangeList);
            return result;
        }
        private void helper (int listIdx, List<Integer> currPermutation, int[][] rangeList) {
            if (listIdx == rangeList.length) {
                StringBuilder sb = new StringBuilder();
                for (Integer i : currPermutation) {
                    sb.append(i);
                }
                result.add(sb.toString());
                return;
            }

            int[] range = rangeList[listIdx];
            for (int i = range[0]; i <= range[1]; ++i) {
                currPermutation.add(i);
                helper(listIdx + 1, currPermutation, rangeList);
                currPermutation.remove(currPermutation.size() - 1);
            }
        }
    }

    public static void main (String[] args) {
//        int[][] rangeList = new int[][]{{2,3}, {3,5}};
//        int[][] rangeList = new int[][]{{2,3}, {3,5}, {0, 9}};
        int[][] rangeList = new int[][]{{0,0}, {3,5}, {0,9}};

        Self solution = new Self();
        System.out.println(solution.getPermutationFromRangeList(rangeList));
    }

}
