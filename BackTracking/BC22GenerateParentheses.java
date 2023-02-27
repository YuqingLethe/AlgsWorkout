package BackTracking;

import java.util.List;
import java.util.ArrayList;

public class BC22GenerateParentheses {
    /**
     * Feb 2022
     */
    class CribAnswer {
        public List<String> generateParenthesis(int n) {
            List<String> ans = new ArrayList();
            backtrack(ans, new StringBuilder(), 0, 0, n);
            return ans;

        }

        private void backtrack(List<String> ans, StringBuilder sb, int open, int close, int max) {
            if (sb.length() == max*2) {
                ans.add(sb.toString());
                return;
            }
            if (open < max) {
                sb.append("(");
                backtrack(ans, sb, open + 1, close, max);
                sb.deleteCharAt(sb.length() - 1);
            }
            if (close < open) {
                sb.append(")");
                backtrack(ans, sb, open, close + 1, max);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
