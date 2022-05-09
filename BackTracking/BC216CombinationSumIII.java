package BackTracking;

import java.util.ArrayList;
import java.util.List;

public class BC216CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> results = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        getRestNumbers(results, list, k, n, 1);
        return results;
    }

    private void getRestNumbers(List<List<Integer>> results, ArrayList<Integer> list, int k, int target, int p) {
        if (target == 0 && k == 0) {
            results.add(new ArrayList<>(list));
            return;
        }
        if (k < 0 || p > 9) {
            return;
        }
        for (int i = p; i <= 9; ++i) {
            if (i <= target) {
                list.add(i);
                getRestNumbers(results, list, k - 1, target - i, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
}
