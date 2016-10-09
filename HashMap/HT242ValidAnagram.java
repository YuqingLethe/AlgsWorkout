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

    public static void main(String[] args) {
        String s = "aa";
        String t = "a";
        System.out.println(isAnagram(s, t));
    }
}
