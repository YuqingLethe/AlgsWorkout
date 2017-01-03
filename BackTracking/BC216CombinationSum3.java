package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/31.
 */
public class BC216CombinationSum3 {
    /**
     * Runtime: 1ms Use: 1hr 12/26/2016
     * https://discuss.leetcode.com/topic/37962/fast-easy-java-code-with-explanation/2
     */
    public static List<List<Integer>> combinationSum3ByRecursive(int k, int n) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (n > 9*k || n < k) return ans;
        findTheOthers(k, n, ans, new ArrayList<Integer>(), 1);
        return ans;
    }
    private static void findTheOthers (int k, int n, List<List<Integer>> ans, List<Integer> list, int start) {
        if (k == 0 && n == 0) {
            List<Integer> li = new ArrayList<Integer>(list);
            ans.add(li);
        }
        for (int i = start; i < 10 && n > 0 && k > 0; i++) {
            list.add(i);
            findTheOthers(k - 1, n - i, ans, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * TODO: understand this one. Where is the stopping condition?
     * https://discuss.leetcode.com/topic/26351/simple-and-clean-java-code-backtracking/2
     */
    public static List<List<Integer>> combinationSum3ByRecursive2(int k, int n) {
        return null;
    }
    public static void main(String[] args) {
        System.out.println(combinationSum3ByRecursive(3, 9));
    }
}
