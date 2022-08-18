package HashMap;

import java.util.*;

public class Dasher_PickupDeliveryGeneratesNCombinations {
    static class Backtracking {
        List<List<String>> res;
        public List<List<String>> generateDeliveries (int n) {
            res = new ArrayList<>();
            if (n == 0) {
                return res;
            }
            if (n == 1) {
                res.add(Arrays.asList("P1", "D1"));
                return res;
            }

            Set<Integer> pickups = new HashSet<>();
            Set<Integer> deliveries = new HashSet<>();
            List<String> currCombineList = new ArrayList<>();
            findCombination(pickups, deliveries, n, currCombineList);
            return res;
        }
        private void findCombination(Set<Integer> pickups, Set<Integer> deliveries, int n, List<String> list) {
            if (list.size() == 2 * n) {
                res.add(new ArrayList<>(list));
                return;
            }
            for (int i = 1; i <= n; ++i) { //注意从1开始到n
                if (pickups.contains(i)) {
                    continue;
                }
                pickups.add(i);
                list.add("P" + i);
                findCombination(pickups, deliveries, n, list);
                list.remove(list.size() - 1); //不要忘了这个也要-1
                pickups.remove(i);
            }

            for (int i = 1; i <= n; ++i) {
                if (deliveries.contains(i) || !pickups.contains(i)) {
                    continue;
                }
                deliveries.add(i);
                list.add("D" + i);
                findCombination(pickups, deliveries, n, list);
                list.remove(list.size() - 1);
                deliveries.remove(i);
            }
        }
    }
    public static void main(String[] args) {
        Backtracking s = new Backtracking();
        for (List<String> list : s.generateDeliveries(2)) {
            System.out.println(list);
        }
        return;
    }
}
