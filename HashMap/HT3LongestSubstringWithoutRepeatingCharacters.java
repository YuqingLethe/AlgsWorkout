package HashMap;

import java.util.HashSet;

public class HT3LongestSubstringWithoutRepeatingCharacters {
    class Jan2022 {
        public int lengthOfLongestSubstring(String s) {
            if (s.isEmpty())  {
                return 0;
            }
            int maxLen = 1;
            return helper(s, maxLen, 0);
        }

        private int helper(String s, int maxLen, int startIdx) {
            if (startIdx == s.length() || (s.length() - startIdx + 1) < maxLen) {
                return maxLen;
            }
            HashSet<Character> hs = new HashSet<>();
            for (int i = startIdx; i < s.length(); i++) {
                Character curr = s.charAt(i);
                if (hs.contains(curr)) {
                    break;
                } else {
                    hs.add(curr);
                }
            } // 直在遇到dup拿個length, 忘記遍歷完所有char也應該拿個length了,
            if (hs.size() > maxLen) {
                maxLen = hs.size();
            }
            return helper(s, maxLen, startIdx + 1);
        }
    }
}
