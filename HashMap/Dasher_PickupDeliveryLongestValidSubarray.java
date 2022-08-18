package HashMap;

import java.util.*;

/**
 * Follow up from Dasher_PickupDeliveryInValidOrder: Find longest valid subarray
 */
public class Dasher_PickupDeliveryLongestValidSubarray {
    /**
     * Sliding Window
     * https://leetcode.com/discuss/interview-question/914113/Longest-valid-orders-path-(Doordash)/1155416
     */
    static class solution {
        public static List<String> getLongest(String[] orders) {
            int n = orders.length;
            Map<String, Integer> map = new HashMap<>();
            int[] presence = new int[n];
            // Build presence[] to find all valid pickups and delivers.
            // Invalid pickup or delivers will be marked as 0.
            for (int i = 0; i < n; ++i) {
                char task_type = orders[i].charAt(0);
                String task_num = orders[i].substring(1);

                if(task_type == 'P') {
                    map.put(orders[i], i);
                } else {
                    String pickup = 'P' + task_num;
                    Integer pickupIdx = map.get(pickup);
                    if (map.containsKey(pickup)) {
                        presence[pickupIdx] = 1; // find pickup idx and set to 1
                        presence[i] = -1; // delivery idx
                        map.remove(pickup);
                    }
                }
            }
            // Find the longest valid subarray using sliding window
            int maxLen = -1; // mark maxLen of checked tasks.
            int left = -1, right = -1; // longest window left and right
            int sum = 0; // help check pickup-delivery balance
            int l = 0; // current window start from

            for (int i = 0; i < n; ++i) {
                if (i == 0) { // First task is valid, pick + 1. Otherwise do nothing.
                    if (presence[0] == 1) {
                        sum = 1;
                    }
                } else if (presence[i] != 0) { // current step involves valid tasks (valid task means all pickup got delivered)
                    if (presence[i - 1] == 0) {// prev is invalid, restart the window, reset pickup-delivery balance.
                        l = i;
                        sum = presence[i];
                    } else { // prev is valid, calculate pickup-delivery balance
                        sum += presence[i];
                    }
                    if (sum == 0) { // balance = 0, all pickup delivered, we check length;
                        if ((i - l + 1) > maxLen) {
                            maxLen = i - l + 1;
                            left = l;
                            right = i;
                        }
                    }
                }
            }

            List<String> res = new ArrayList<>();
            if (left != -1 && right != -1) {
                for (int i = left; i <= right; ++i) {
                    res.add(orders[i]);
                }
                return res;
            }
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        solution obj = new solution();
        String[] orders1 = new String[]{"P1", "P2", "D1", "D2"};
        String[] orders2 = new String[]{"P1", "D1", "P2", "D2"};
        String[] orders3 = new String[]{"P1", "P1", "D1"};
        String[] orders4 = new String[]{"P1", "P1", "D1", "D1"};

//        System.out.println(obj.getLongest(orders1).toString()); // P1, P2, D1, D2
//        System.out.println(obj.getLongest(orders2)); // P1, D1, P2, D2
//        System.out.println(obj.getLongest(orders3)); // P1, D1
//        System.out.println(obj.getLongest(orders4)); // P1, D1
        System.out.println(obj.getLongest(new String[]{"P1, P2, D1, P3, D2"}));
    }
}
