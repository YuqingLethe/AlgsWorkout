package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-ii/discuss/1424140/Java-Easy-To-Understand
 */
public class BC40CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        getRemainings(results, list, candidates, target, 0);
        return results;
    }

    /**
     * 常犯錯誤: [2,5,2,1,2] target=8  [1 2 2 2 5]中有重複的125 125. 不能直接抹掉2, 有的情況需要幾個2.
     * if (i > p && candidates[i] == candidates[i - 1])
     * 這裏i>p保證了不會是子循環的首個被刪除, 只會循環到第二個以後的時候不加入重複元素.
     */
    private void getRemainings(List<List<Integer>> results, ArrayList<Integer> list, int[] candidates, int target, int p) {
        if (target == 0) {
            results.add(new ArrayList<>(list));
            return;
        }
        for (int i = p; i < candidates.length; i++) {
            if (i > p && candidates[i] == candidates[i - 1]) { //這裏爲什麼用i>p就可以跳過首
                continue;
            }
            if (candidates[i] <= target) {
                list.add(candidates[i]);
                getRemainings(results, list, candidates, target - candidates[i], i + 1);
                list.remove(list.size() - 1);
            } else {
                return;
            }
        }
    }
}
