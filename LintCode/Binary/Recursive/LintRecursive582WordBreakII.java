package LintCode.Binary.Recursive;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/24.
 */
public class LintRecursive582WordBreakII {
    /**
     * 2017/7/24
     * 这个case TLE了, 其他简单case都没问题
     * 自己球出了wordDict的最长字符串长度, 但还是TLE
     * "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
     */
    public class SolutionTLE {
        public List<String> wordBreak(String s, Set<String> wordDict) {
            List<String> results = new ArrayList<>();
            if (wordDict == null || wordDict.size() == 0 || s == null) {
                return results;
            }

            //find largest length in wordDict
            Iterator iter = wordDict.iterator();
            int maxLen = 0;
            while (iter.hasNext()) {
                int thisLen = ((String) iter.next()).length();
                if (thisLen > maxLen) {
                    maxLen = thisLen;
                }
            }
            ArrayList<String> list = new ArrayList<>();
            helper(s, wordDict, 0, list, results, maxLen);
            return results;
        }
        private void helper(String s,
                            Set<String> wordDict,
                            int start,
                            ArrayList<String> list,
                            List<String> results,
                            int maxLen) {
            if (start == s.length()) {
                results.add(arrayToString(list));
                return;
            }
            for (int i = start; i < s.length(); i++) {
                String crt = s.substring(start, i + 1);
                if (crt.length() > maxLen || !wordDict.contains(crt)) {
                    continue;
                }
                list.add(crt);
                helper(s, wordDict, i + 1, list, results, maxLen);
                list.remove(list.size() - 1);
            }
        }
        private String arrayToString(ArrayList<String> list) {
            String  s = "";
            for (String str : list) {
                s += str;
                s += " ";
            }
            s = s.substring(0, s.length() - 1);
//        System.out.println(s);
            return s;
        }
    }
    /**
     * 看到有思路分享: 你只是普通的DFS搜索解法，答案用memo的map来存的，叫做记忆化搜索，就是不做重复的事情，
     * 既然这种情况我们已经处理过了，就没有必要再搜一遍了，是空间换时间的做法，所以效率高。
     */
}
