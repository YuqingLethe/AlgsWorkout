package LintCode.Binary.Recursive;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * Given a list of numbers that may has duplicate numbers, return all possible subsets

 Notice

 Each element in a subset must be in non-descending order.
 The ordering between two subsets is free.
 The solution set must not contain duplicate subsets.
 Have you met this question in a real interview? Yes
 Example
 If S = [1,2,2], a solution is:

 [
 [2],
 [1],
 [1,2,2],
 [2,2],
 [1,2],
 []
 ]
 */
public class LintRecursive18SubSetII {
    /**
     * helper里面的recursion每写一遍就深入思考一遍. 这里注意是先把上一层的list
     * add to the results, 然后再进行下一层的运算.
     * 而最担心的重复问题, 可以print出来看一下
     * 7/24/2017 2nd time
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] nums) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }
        if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }
        Arrays.sort(nums);
        ArrayList<Integer> refSet = new ArrayList<>();
        helper(refSet, nums, 0, results);
        return results;
    }
    private void helper(ArrayList<Integer> refSet,
                        int[] nums,
                        int start,
                        ArrayList<ArrayList<Integer>> results) {
        results.add(new ArrayList<Integer>(refSet));
        for (int i = start; i < nums.length; i++) {
            if (i != start && nums[i] == nums[i - 1]) {
                continue;
            }
            refSet.add(nums[i]);
            helper(refSet, nums, i + 1, results);
            refSet.remove(refSet.size() - 1);
        }
    }
    /**
     * 上面版本的过程打印
     */
    class SolutionPrint {
        public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
            ArrayList<ArrayList<Integer>> results = new ArrayList<>();
            if (nums == null) {
                return results;
            }
            if (nums.length == 0) {
                results.add(new ArrayList<Integer>());
                return results;
            }
            for (int i = 0; i < (1 << nums.length); i++) {
                ArrayList<Integer> subset = new ArrayList<Integer>();
                for (int j = 0; j < nums.length; j++) {
                    if ((i & (1 << j)) != 0) {
                        System.out.println("i:        " + i + "             -> " + intToString(i, 4));
                        System.out.println("j: 1 move " + j + " steps left  -> " + intToString(1 << j, 4));

                        subset.add(nums[j]);
                        printList(subset);
                    }
                }
                results.add(subset);
            }
            return results;
        }
        private void printList(ArrayList<Integer> list) {
            System.out.print("subset ====== ");
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println("=========== ");
        }
        private String intToString(int number, int groupSize) {
            StringBuilder result = new StringBuilder();

            for(int i = 7; i >= 0 ; i--) {
                int mask = 1 << i;
                result.append((number & mask) != 0 ? "1" : "0");

                if (i % groupSize == 0)
                    result.append(" ");
            }
            result.replace(result.length() - 1, result.length(), "");

            return result.toString();
        }
    }
}
