package String;

/**
 * Given two strings s and goal, return true if you can swap two letters in s so the result is equal to goal, otherwise, return false.

 Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at s[i] and s[j].

 For example, swapping at indices 0 and 2 in "abcd" results in "cbad".


 Example 1:

 Input: s = "ab", goal = "ba"
 Output: true
 Explanation: You can swap s[0] = 'a' and s[1] = 'b' to get "ba", which is equal to goal.

 Example 2:

 Input: s = "ab", goal = "ab"
 Output: false
 Explanation: The only letters you can swap are s[0] = 'a' and s[1] = 'b', which results in "ba" != goal.

 Example 3:

 Input: s = "aa", goal = "aa"
 Output: true
 Explanation: You can swap s[0] = 'a' and s[1] = 'a' to get "aa", which is equal to goal.


 Constraints:

 1 <= s.length, goal.length <= 2 * 104
 s and goal consist of lowercase letters.
 */
public class String859BuddyStrings {
    /**
     * 主要是自己吧所有的corner case考慮到
     * May2022 自己寫提交三次都fail
     */
    class Solution {
        public boolean buddyStrings(String s, String goal) {
            if (s.length() != goal.length()) {
                return false;
            }
            // 当s和goal完全相同的时候，
            if (s.equals(goal)) { //这个case完全忘记了， 主要是没有认真分析例子
                int count[] = new int[26];
                for (int i = 0; i < s.length(); ++i) {
                    count[s.charAt(i) - 'a'] ++;
                }
                for (int c : count) {
                    if (c > 1) {
                        return true;
                    }
                }
                return false;
            }


            int a = -1, b = -1;
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) != goal.charAt(i)) {
                    if (a == -1) {
                        a = i;
                    } else if (b == -1) {
                        b = i;
                    } else {
                        return false; //這個地方也忘記考慮了， 一旦有多個不一樣的， 肯定不符合
                    }
                }
            }
            if (a == -1 && b == -1) { //这个corner case不要忘了
                return true;
            } else if (a == -1 | b == -1) {
                return false;
            }
            if (s.charAt(a) == goal.charAt(b) && s.charAt(b) == goal.charAt(a)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
