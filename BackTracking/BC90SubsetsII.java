package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 8/29/21.
 */
public class BC90SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(nums); //忘记sort
        addListStartWithLastPosition(results, list, 0, nums);
        return results;

    }

    private void addListStartWithLastPosition(List<List<Integer>> results, ArrayList<Integer> list, int p, int[] nums) {
        results.add(new ArrayList<Integer>(list)); // 忘记new

        // restuls [[], [1], [1,2], [1,2,2], [2], [2,2] ]
        // list [2(1)]
        // p = 0, i = 2
        for (int i = p; i < nums.length; i++) {
            if (i != p && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            addListStartWithLastPosition(results, list, i + 1, nums); // 1) push to results 2) continue add to worker list
            list.remove(list.size() - 1);
        }
    }
}
