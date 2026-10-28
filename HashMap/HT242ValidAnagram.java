package HashMap;

import java.util.HashMap;
import java.util.Map;

public class HT242ValidAnagram {
    /**
     * RunTime: 34ms  10/8/2016
     * https://discuss.leetcode.com/topic/57604/accepted-java-solution-using-hashmap-26-of-java-submissions
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            int count = map.getOrDefault(c, 0) + 1;
            map.put(c, count);
        }
        for (char c : t.toCharArray()) {
            int count = map.getOrDefault(c, 0) - 1;
            if (count < 0) return false;
            else map.put(c, count);
        }
        return true;
    }

    /**
     * 2026 第二个for我感觉有好的办法但是没有想到。
     * 基础题目，学习用toCharArray()和getOrDefault，熟悉map methods和用-1代表不存在的灵活处理
     */
    public boolean isAnagram2026 (String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> set = new HashMap<>();
        for (int i = 0; i < s.length(); i ++) {
            Character ch = s.charAt(i);
            set.put(ch, set.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); i ++) {
            Character ch = t.charAt(i);
            if (set.containsKey(ch) && set.get(ch) > 1) {
                set.put(ch, set.get(ch) - 1);
            } else if (set.containsKey(ch)) {
                set.remove(ch);
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aa";
        String t = "a";
        System.out.println(isAnagram(s, t));
    }
}
