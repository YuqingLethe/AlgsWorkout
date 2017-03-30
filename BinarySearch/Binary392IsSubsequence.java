package BinarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Binary392IsSubsequence {
    /**
     * Runtime: 70ms  Use: 2min  3/30/2017
     */
    public boolean isSubsequenceGreedy(String s, String t) {
        if(s.length() == 0) return true;
        int idx = 0; //index of s
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == s.charAt(idx)) {
                idx++;
            }
            if (idx == s.length())
                return true;
        }
        return false;
    }

    /**
     * Runtime: 4ms  Use: 1min OneTime  3/30/2017
     * Optimize by (1) s t size  (2) _String.indexOf
     * _String.indexOf:
     * Returns the index within this string of the first occurrence of the specified character,
     * starting the search at the specified index. Or return -1 if not occur
     * https://discuss.leetcode.com/topic/83933/my-2ms-java-solution
     */
    public boolean isSubsequenceGreedyOptimize(String s, String t) {
        if (s.length() > t.length()) return false;
        if (s.length() == 0) return true;

        int curr = -1;
        for (int i = 0; i < s.length(); i++) {
            int tmp = t.indexOf(s.charAt(i), curr + 1);
            if ( tmp > curr) {
                curr = tmp;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Runtime:  13ms  Use: 2min  3/30/2017
     */
    public boolean isSubsequenceGreedyRecursive(String s, String t) {
        if (s.length() > t.length()) return false;
        if (s.length() == 0) return true;

        int idx = t.indexOf(s.charAt(0));
        if (idx > -1)
            return isSubsequenceGreedyRecursive(s.substring(1), t.substring(idx + 1));
        else
            return false;
    }



    /**
     * Runtime: 96ms  Use: 1hr debugging in helper method 3/30/2017
     */
    public static boolean isSubsequenceBinaryHM(String s, String t) {
        if (s.length() > t.length()) return false;
        if (s.length() == 0) return true;

        //Build the map where key is the character, list are the positions in String t.
        HashMap<Character, List<Integer>> hm = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!hm.containsKey(c)) {
                hm.put(c, new ArrayList<Integer>()); //instantiate the List
            }
            hm.get(c).add(i);
        }

        int idx = -1;
        for (int i = 0; i < s.length(); i++) {
            List tmp;
            char c = s.charAt(i);
            if (hm.containsKey(c)) {
                tmp = hm.get(c);
                idx = binaryReturnIndex(tmp, idx + 1);
                if (idx == -1) return false;
            } else {
                return false;
            }
        }
        return true;
    }
    //Return the index of String t, from List l elements
    private static int binaryReturnIndex(List<Integer> l, int target) {
        int i = 0, j = l.size() - 1;
        while(i <= j) {
            int mid = (j - i)/2 + i;
            if (target < l.get(mid))
                j = mid - 1;
            else if (target > l.get(mid))
                i = mid + 1;
            else
                return mid;
        }
        if (i < l.size() )
            return l.get(i);
        else
            return -1;
    }

    /**
     * Other's solution:  https://discuss.leetcode.com/topic/60450/java-clean-dp-solution
     * dp有四个值, 第一个值设为true, 在此之后不断遍历String s, 一旦t.charAt(j)== s.charAt(i), 且之前dp都为true就表示
     * i之前的字母在t的j之前都找到了归属, 因此这个dp[i]也设置为true
     * 总之是dp的思想, 把之前验证的结果存在dp中, 提供给后面验证用. runtime为301ms非常大
     */
    public static boolean isSequenceDP (String s, String t) {
        int m = s.length(), n = t.length();
        boolean preUp = true;
        boolean[]dp = new boolean[m+1];
        dp[0] = true;
        for(int j = 1; j <=n; j++){
            preUp = true;
            for(int i = 1; i <= m; i++){
                boolean tmp = dp[i];
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i] = preUp||dp[i];
                }
                preUp = tmp;
            }
            if(dp[m]) return true;
        }
        return dp[m];
    }

    public static void main(String[] args) {
        String s = "abc";
        String t = "bahgdcb";
        System.out.println(isSubsequenceBinaryHM(s, t));

        System.out.println(isSequenceDP(s,t));
        boolean[] b = new boolean[2];
        System.out.println(b[1]);
    }


}