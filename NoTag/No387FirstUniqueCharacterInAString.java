package NoTag;

import java.util.*;

/**
 * Created by Administrator on 2016/11/11.
 */
public class No387FirstUniqueCharacterInAString {
    /**
     * Runtime: 44ms  By built-in method O(n^2)
     * https://discuss.leetcode.com/topic/55827/my-4-lines-java-solution
     */
    public int firstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            if(s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i)))
                return i;
        }
        return -1;
    }

    /**
     * Runtime: 22ms Want to optimize the following algs 11/12/2016
     * It takes O(n) and goes through the string twice:
     * 1. Get the frequency of each character.
     * 2. Get the first character that has a frequency of one.
     * https://discuss.leetcode.com/topic/55148/java-7-lines-solution-29ms
     */
    public static int firstUniqCharByFreq(String s) {
        int[] freq = new int[256];
        for (char x : s.toCharArray()) {
            freq[x]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i)] == 1) return i;
        }
        return -1;
    }

    /**
     * Runtime: 51ms  11/12/2016
     * Optimize the freq one. Very wise solution
     * https://discuss.leetcode.com/topic/55230/java-two-pointers-slow-and-fast-solution-18-ms
     */
    public static int firstUniqCharTwoPointers(String s) {
        if (s.length() == 0 || s == null) return -1;
        if (s.length() == 1) return 0;
        int[] freq = new int[256]; //The frequency of characters by ASCII
        int slow = 0;
        freq[s.charAt(slow)]++; //add the first character
        for(int fast = 1; fast < s.length(); fast++) {
            freq[s.charAt(fast)]++;
            //put slow on the next unique char
            while (slow < s.length() && freq[s.charAt(slow)] > 1) {
                slow++;
            }
            if (slow >= s.length()) return -1;
            //If slow is faster than the fast, which means all characters before have been found duplicates
            if (freq[s.charAt(slow)] == 0) {
                fast = slow;
                freq[s.charAt(fast)] ++;
            }
        }
        return slow;
    }

    /**
     * Runtime: 97ms Use map 11/12/2016
     * Use frequency as value
     */
    public static int firstUniqCharByMap(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            Integer i = map.get(c);
            if (i == null) map.put(c, 1);
            else map.put(c, i + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) return i;
        }
        return -1;
    }

    /**
     * Runtime: 148ms Use: 20min 12/22/2016
     * Use the map to store the unique char and use set to find if existed. Direct think
     */
    public static int firstUniqCharByMapSet1(String s) {
        Set<Character> set = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!set.contains(c)) {
                set.add(c);
                map.put(c, i);
            } else {
                map.remove(c);
                map.put(c, i);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c) && map.get(c) == i) return i;
            else if (set.contains(c)){
                set.remove(c);
            }
        }

        return -1;
    }

    /**
     * Runtime: 89ms
     * Optimize of previous one. LinkedMapSet returns value by the input order!
     * https://discuss.leetcode.com/topic/55488/java-one-pass-solution-with-linkedhashmap/2
     */
    public static int firstUniqCharByMapSet2(String s) {
        Set<Character> set = new HashSet<>();
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                if (map.containsKey(c)) {
                    map.remove(c);
                }
            } else {
                map.put(c, i);
                set.add(c);
            }
        }

        return map.size() == 0 ? -1 : map.entrySet().iterator().next().getValue();
    }

    public static void main(String[] args) {
        String s = "daacdad";
        System.out.println(firstUniqCharByMapSet2(s));
    }
}
