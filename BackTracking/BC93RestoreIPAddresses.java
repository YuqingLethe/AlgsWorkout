package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 下一次用的方法:
 * 1. 直接查3個, 因爲每個section最多三個 https://leetcode.com/problems/restore-ip-addresses/discuss/1435944/Java-backtracking-solution
 * 2.
 */
public class BC93RestoreIPAddresses {
    class MyOwnTemplateSolution {
        List<String> results = new ArrayList<>();

        public List<String> restoreIpAddresses(String s) {
            ArrayList<StringBuilder> list = new ArrayList<>();
            checkRest(0, s, list, new StringBuilder());
            return results;
        }

        private void checkRest(int start, String s, ArrayList<StringBuilder> list, StringBuilder sb) {
            if (start == s.length() && !list.isEmpty() && list.size() == 4) {
                StringBuilder currS = new StringBuilder();
                currS.append(list.get(0)).append('.').append(list.get(1)).append('.').append(list.get(2)).append('.').append(list.get(3));
                results.add(currS.toString());
                return;
            }
            if (list.size() > 4) {
                return;
            }
            for (int i = start; i < s.length(); ++i) {
                sb.append(s.charAt(i));
                // 分成兩個部分, 一個是跟蹤 list 一個是跟蹤 sb. List超了remove, sb不能remove
                if (isNumberLessThan256(sb)) {
                    list.add(sb);
                    checkRest(i + 1, s, list, new StringBuilder());
                    list.remove(list.size() - 1);
                }
            }
        }

        private boolean isNumberLessThan256(StringBuilder s) {
            if (s.length() > 3 || (s.length() > 1 && s.charAt(0) == '0')) {
                return false;
            }
            int sum = 0;
            for (int i = 0; i < s.length(); ++i) {
                sum = sum*10 + (s.charAt(i) - '0');
            }
            if (sum > 255) {
                return false;
            }
            return true;
        }
    }
}
