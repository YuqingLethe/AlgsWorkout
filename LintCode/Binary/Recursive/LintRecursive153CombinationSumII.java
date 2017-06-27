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
            //６/25/2017:我们的目标是: 作为startIdx可以重复, 因为是查找后面元素. 但如果不是startIdx, 也就是之前元素已经被remove, 已经被consider过了, 则相等需要舍弃.
            // e.g.:[1,1,2,3]里面target = 3. [1,2,2,2,2], target = 5 的情况   无论startIdx是不是first element
            // 举首个例子模拟特殊情况. 主要是分清startIdx以及for loop的用途
            if (i != startIdx && num[i] == num[i - 1]) {
                continue;
            }
            subset.add(num[i]);
            search(num, target - num[i], results, subset, i + 1);
            subset.remove(subset.size() - 1);
        }
    }
}
