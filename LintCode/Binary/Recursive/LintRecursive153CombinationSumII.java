package LintCode.Binary.Recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */
public class LintRecursive153CombinationSumII {
    /**
     * 2017/6/9. 做完Combination Sum 1 这个小菜一碟
     * @param num: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (num == null || num.length == 0) {
            return results;
        }
        Arrays.sort(num);
        search(num, target, results, new ArrayList<Integer>(), 0);
        return results;
    }
    private void search(int[] num,
                        int target,
                        List<List<Integer>> results,
                        ArrayList<Integer> subset,
                        int startIdx) {
        if (target == 0) {
            results.add(new ArrayList<Integer>(subset));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = startIdx; i < num.length; i++) {
            if (num[i] > target) {
                break;
            }
            //Failed case: [7,1,2,5,1,6,10] 8 -> [1,7][1,7][1,2,5][1,2,5]都出现两次
            //所以这个判断条件很有必要!!
            if (i != startIdx && num[i] == num[i - 1]) {
                continue;
            }
            subset.add(num[i]);
            search(num, target - num[i], results, subset, i + 1);
            subset.remove(subset.size() - 1);
        }
    }
}
