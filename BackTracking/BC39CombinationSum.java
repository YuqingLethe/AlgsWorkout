package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BC39CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        findRemainings(results, candidates, list, target, 0);
        return results;
    }

    public void findRemainings(List<List<Integer>> results, int[] candidates, ArrayList<Integer> list, int target, int p) {
        if (target == 0) {
            results.add (new ArrayList<>(list));
            return;
        }
        for (int i = p; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                list.add(candidates[i]);
                findRemainings(results, candidates, list, target-candidates[i], i);
                list.remove(list.size() - 1);
            } else {
                return;
            }
        }
    }
}
