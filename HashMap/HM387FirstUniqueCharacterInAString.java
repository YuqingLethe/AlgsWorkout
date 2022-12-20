package HashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.



 Example 1:

 Input: s = "leetcode"
 Output: 0

 Example 2:

 Input: s = "loveleetcode"
 Output: 2

 Example 3:

 Input: s = "aabb"
 Output: -1


 Constraints:

 1 <= s.length <= 105
 s consists of only lowercase English letters.
 */
public class HM387FirstUniqueCharacterInAString {
    class Set_HashMap {
        public int firstUniqChar(String s) {
            if (s.length() <= 1) {
                return 0;
            }
            Set<Character> repeatedChar = new HashSet<>();
            HashMap<Character, Integer> hm = new HashMap<>();

            for (int i = 0; i < s.length(); ++i) {
                Character c = s.charAt(i);
                if (repeatedChar.contains(c)) {
                    continue;
                }
                if (hm.containsKey(c)) {
                    hm.remove(c);
                    repeatedChar.add(c);
                } else {
                    hm.put(c, i);
                }
            }

            // Find smallest index of non-repeating char
            int minIdx = Integer.MAX_VALUE;
            for (Integer i : hm.values()) {
                minIdx = Math.min(minIdx, i);
            }
            return minIdx == Integer.MAX_VALUE ? -1 : minIdx;
        }
    }

    /**
     * TODO:
     * LC solution, use HashMap to contain char and frequency.
     */
    class HashMap_FindIdx {

    }
}
