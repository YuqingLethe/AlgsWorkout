package LintCode.Binary.TwoPointers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LintTP607TwoSumDataStructureDesign {
    /**
     * HashMap + List 2017/7/11.
     * I think the hashmap + hashset also works
     */
    public class TwoSum {
        HashMap<Integer, Integer> hm;
        List<Integer> list;
        public TwoSum() {
            this.hm = new HashMap<>();
            this.list = new ArrayList<>();
        }
        // Add the number to an internal data structure.
        public void add(int number) {
            // Write your code here
            if (hm.containsKey(number)) {
                hm.put(number, hm.get(number) + 1);
            } else {
                hm.put(number, 1);
                list.add(number);
            }
        }
        // Find if there exists any pair of numbers which sum is equal to the value.
        public boolean find(int value) {
            for (int i = 0; i < list.size(); i++) {
                int num1 = list.get(i);
                int num2 = value - num1;
                if (hm.containsKey(num2))  {
                    if (num1 != num2) {
                        return true;
                    } else if (hm.get(num2) > 1) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
