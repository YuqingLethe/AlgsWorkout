package LintCode.Binary.Recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */
public class Recursive136PalindromePartitioning {
    /**
     * 6/10/2017
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        List<List<String>> results = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return results;
        }
        search(s, results, new ArrayList<String>(), 0);
        return results;
    }
    private void search (String s,
                         List<List<String>> results,
                         ArrayList<String> substring,
                         int startIdx) {
        if (startIdx == s.length()) {
            results.add(new ArrayList<String>(substring)); //6/25/2017 又忘了deep copy
            return;
        }
        for (int i = startIdx; i < s.length(); i++) {
            String curr = s.substring(startIdx, i + 1); //注意从哪里开始算!
            if (!isPalindrome(curr)) {
                continue;
            }
            substring.add(curr);
            search(s, results, substring, i + 1);
            substring.remove(substring.size() - 1);
        }
    }
    private boolean isPalindrome (String x) {
        if (x == null || x.length() == 0)  {
            return false;
        }
        int len = x.length();
        for (int i = 0; i < len/2; i++)  {
            if (x.charAt(i) != x.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
