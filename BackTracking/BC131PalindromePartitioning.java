package BackTracking;

import java.util.ArrayList;
import java.util.List;

public class BC131PalindromePartitioning {
    /**
     *  1. 讀題不仔細, 是partition, 不是找出所有palindrome substring
     *  2. 發現讀題不對的時候有仔細思考定義, 怎樣backtrack
     *  3. 因爲我的定義是傳入剩下的substring, 所以一定是從第一個開始搜索, 所以一定從0開始搜索, 不需要傳index;
     *  換句話說我選擇傳入substring就已經包含了index, 所以不需要多餘的P了
     *
     */
    class MyOwnStandardSolution {
        private List<List<String>> results = new ArrayList<>();

        public List<List<String>> partition(String s) {
            ArrayList<String> list = new ArrayList<>();
            findPalindromeAndAdd(s, list, new StringBuilder());
            return results;
        }

        private void findPalindromeAndAdd(String s, ArrayList<String> list, StringBuilder sb) {
            if (s.length() == 0 && !list.isEmpty()) {
                results.add(new ArrayList<>(list));
                return;
            }
            for (int i = 0; i < s.length(); ++i) {
                sb.append(s.charAt(i)); //之前寫成了charAt(p)
                if (isPalindrome(sb)) {
                    list.add(sb.toString());
                    findPalindromeAndAdd(s.substring(i + 1), list, new StringBuilder());
                    list.remove(list.size() - 1);
                }
            }
        }
        private boolean isPalindrome(StringBuilder sb) {
            for (int i = 0, j = sb.length() - 1; i < j; ++i, --j) {
                if (sb.charAt(i) != sb.charAt(j)) {
                    return false;
                }
            }
            return true;
        }
    }
}
