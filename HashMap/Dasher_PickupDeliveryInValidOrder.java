package HashMap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/discuss/interview-question/846916/Validate-Orders-Path-(Doordash)
 *
 * Given a set list of pickups and deliveries for order, figure out if the given list is valid or not.
 * A delivery cannot happen for an order before pickup.
 *
 * check main function of input
 *
 * Follow up: Dasher_PickupDeliveryLongestValidSubarray
 * Follow up: Dasher_
 *
 */
public class Dasher_PickupDeliveryInValidOrder {
    static class Solution {
        public boolean isValid(List<String> orders) {
            Set<String> p_set = new HashSet<>();
            Set<String> d_set = new HashSet<>();
            int count = 0;
            for (String order : orders) {
                char task_type = order.charAt(0);
                String task_num = order.substring(1);
                if (task_type == 'P') {
                    if (p_set.contains(task_num)) {
                        return false;
                    }
                    p_set.add(task_num);
                    count++;
                } else {
                    if (p_set.contains(task_num) || !d_set.contains(task_num)) {
                        p_set.remove(task_num);
                        d_set.add(task_num);
                    } else {
                        return false;
                    }
                }
            }
            if (!p_set.isEmpty() || d_set.size() != count) {
                return false;
            }
            return true;
        }
    }

    static class Solution2 {
        public boolean isValid(List<String> orders) {
            Set<String> p_set = new HashSet<>();
            Set<String> d_set = new HashSet<>();
            for (String order : orders) {
                char task_type = order.charAt(0);
                String task_num = order.substring(1);
                if (task_type == 'P') {
                    if (p_set.contains(task_num)) {
                        return false;
                    }
                    p_set.add(task_num);
                } else {
                    if (!p_set.contains(task_num) || d_set.contains(task_num)) {
                        return false;
                    }
                    d_set.add(task_num);
                }
            }
            if (p_set.size() != d_set.size()) {
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
//        Solution obj = new Solution();s
        Solution2 obj = new Solution2();
        System.out.println(obj.isValid(Arrays.asList("P1", "P2", "D1", "D2"))); // true
        System.out.println(obj.isValid(Arrays.asList("P1", "D1", "P2", "D2"))); // true
        System.out.println(obj.isValid(Arrays.asList("P1", "D2", "D1", "P2"))); // false
        System.out.println(obj.isValid(Arrays.asList("P1", "D2"))); // false
        System.out.println(obj.isValid(Arrays.asList("P1", "D1", "D2"))); // false
        System.out.println(obj.isValid(Arrays.asList())); // true
        System.out.println(obj.isValid(Arrays.asList("P1", "P1", "D1"))); // false
        System.out.println(obj.isValid(Arrays.asList("P1", "D1", "P1", "D1"))); // false
    }
}
