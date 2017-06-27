package LintCode.Binary.Recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 The same repeated number may be chosen from C unlimited number of times.

 Notice:
 All numbers (including target) will be positive integers.
 Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 The solution set must not contain duplicate combinations.

 Example:
 Given candidate set [2,3,6,7] and target 7, a solution set is:
 [7]
 [2, 2, 3]
 */
public class LintRecursive135CombinationSum {
    /**
     * 6/9/2017
     1. 只要不从剩余数字里取值, 而是从原数组里取值, 就可以满足重复使用数字这个要求了!
     2. 实现起来, 如果不把startIdx作为参数传递的话, 有可能有同样的数组作为解, 比如[2,2,3][2,3,2][3,2,2].
     解决办法有两个
        2.1 一个是在结果输出前用一个method把重复解去掉
        2.2 另一个是更改传递参数, 让i作为startIdx传递, 这样每次可以i重复使用, 而i之前的不能重复使用. (注意考虑candidates里面自带重复值的情况!)
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return results;
        }
        Arrays.sort(candidates); // 看清题意, 不要忘了sort
        ArrayList<Integer> subset = new ArrayList<>();
        search(candidates, target, results, subset, 0);
        return results;
    }
    private void search(int[] candidates,
                        int target, //target是可以变的诶!
                        List<List<Integer>> results,
                        ArrayList<Integer> subset,
                        int startIdx) {
        if (target == 0) {
            results.add(new ArrayList<Integer>(subset)); //6.25忘记deep copy
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = startIdx; i < candidates.length; i++) {
            if (candidates[i] > target) {
                continue;
            }
            //注意这里, 因为传递参数时已经考虑了重复, 所以这种candidates里面自带重复值的也要去掉
            //6/25 第二次忘记!
            if (i!= startIdx && candidates[i] == candidates[i - 1]) {
                continue;
            }
            subset.add(candidates[i]);
            search(candidates, target - candidates[i], results, subset, i); //这里的startIdx只要从i开始就可以了
            subset.remove(subset.size() - 1);
        }
    }
    private int sum(ArrayList<Integer> subset) {
        int ans = 0;
        for (Integer i : subset) {
            ans += i;
        }
        return ans;
    }
}
