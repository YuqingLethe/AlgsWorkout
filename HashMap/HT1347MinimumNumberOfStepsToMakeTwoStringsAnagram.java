package HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given two strings of the same length s and t. In one step you can choose any character of t and replace it with another character.

 Return the minimum number of steps to make t an anagram of s.

 An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.



 Example 1:

 Input: s = "bab", t = "aba"
 Output: 1
 Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.
 Example 2:

 Input: s = "leetcode", t = "practice"
 Output: 5
 Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
 Example 3:

 Input: s = "anagram", t = "mangaar"
 Output: 0
 Explanation: "anagram" and "mangaar" are anagrams.


 Constraints:

 1 <= s.length <= 5 * 104
 s.length == t.length
 s and t consist of lowercase English letters only.

 Hint1
 Count the frequency of characters of each string.
 Hint2
 Loop over all characters if the frequency of a character in t is less than the frequency of the same character
   in s then add the difference between the frequencies to the answer.
 */
public class HT1347MinimumNumberOfStepsToMakeTwoStringsAnagram {
    /**
     * May 2022 看了答案題目思路作出的， 和我想的無異
     */
    class CountDiffForBothString {
        public int minSteps(String s, String t) {
            int[] charList = new int[26];
            for (int i = 0; i < t.length(); ++i) {
                int sIdx = s.charAt(i) - 'a';
                int tIdx = t.charAt(i) - 'a';
                charList[sIdx] = charList[sIdx] - 1; // charList[sIdx]++即可
                charList[tIdx] = charList[tIdx] + 1; //charList[tIdx]--即可
            }
            int count = 0;
            for (int i : charList) {
                count += Math.abs(i);
            }
            System.out.println(count);
            // 其實沒必要考慮模， 因爲一定是偶數
            // return count % 2 == 0 ? (count/2) : (count/2 + 1);
            return count/2;
        }
    }

    /**
     * 這個更直觀， 但更要充分理解兩個string之間diff， 一邊多一個另一邊就少一個
     */
    class OnlyFocusOnTString {
        public int minSteps(String s, String t) {
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); ++i) {
                map.put(s.charAt(i),
                        map.getOrDefault(s.charAt(i), 0) + 1
                );
            }
            // 這裏的主要原理是， 如果已有char，s數目比t多則無視， 只統計t多出來的部分
            int count = 0;
            for (int i = 0; i < t.length(); ++i) {
                Character c = t.charAt(i);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) - 1);
                    if (map.get(c) == 0) {
                        map.remove(c);
                    }
                } else {
                    count ++;
                }
            }
            return count;
        }
    }
}
