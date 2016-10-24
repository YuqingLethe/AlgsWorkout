package HashMap;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by yoki on 10/23/16.
 */
public class HT290WordPattern {
    /**
     * Runtime:2ms Use: 20min Debug takes time
     */
    public static boolean wordPatternByHash(String pattern, String str) {
        String[] strArr = str.split(" ");
        if (pattern.length() != strArr.length) return false;

        HashMap<Character, String> map = new HashMap<>();
        for (int i = 0; i < strArr.length; i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(strArr[i])) return false;
            } else {
                //If the same words
                if (map.containsValue(strArr[i])) return false;
                map.put(c, strArr[i]);
            }
        }
        return true;
    }

    /**
     * Runtime: 2ms
     * Make index as the value to simplify the if logic
     * https://discuss.leetcode.com/topic/26339/8-lines-simple-java
     */
    public static boolean wordPatternByDoubleHashSize(String pattern, String str) {
        String[] strArr = str.split(" ");
        if (pattern.length() != strArr.length) return false;

        HashMap<Object, Integer> map = new HashMap<>();
        for (int i = 0; i < strArr.length; ++i) {
            if (!Objects.equals(map.put(pattern.charAt(i), i), map.put(strArr[i], i)))
                return false;
        }
        return true;
    }

    /**
     * Runtime: 1ms  Refer:
     * https://discuss.leetcode.com/topic/30646/1ms-simple-java-solution-without-hashmap
     */
    public static boolean wordPatternByStringArray(String pattern, String str) {
        String[] words = str.split(" ");
        String[] table = new String[26];

         if (pattern.length() != words.length) return false;

        for (int i = 0; i < pattern.length(); ++i) {
            int diff = pattern.charAt(i) - 'a';
            if (table[diff] == null) {
                for(String s : table) {
                    if (words[i].equals(s)) return false;
                }
                table[diff] = words[i];

            } else {
                if (!table[diff].equals(words[i])) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String pattern = "abbac";
        String str = "d x x d sfssf";
        System.out.println(wordPatternByStringArray(pattern, str));
    }
}
