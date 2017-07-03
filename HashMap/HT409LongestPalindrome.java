package HashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/2.
 */
public class HT409LongestPalindrome {
    /**
     * @param s a string which consists of lowercase or uppercase letters
     * @return the length of the longest palindromes that can be built
     */
    public int longestPalindrome(String s) {
        if (s == null) {
            return 0;
        }
        //Build the hashmap of times it occurs
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        //Accumulate occur times of paired characters and check whether we have single character left
        int count = 0;
        boolean single = false;
        for (Character c : map.keySet()) {
            int mod = map.get(c) % 2;
            int div = map.get(c) / 2;
            if (mod != 0 && !single) {
                single = true;
            }
            count += div * 2;
        }

        if (single) {
            return count + 1;
        }
        return count;
    }

    /**
     * 别人的方法, 和上面思路一样, 但巧妙 time: O(n)
     * space: worst case O(n)
     * n is length of s
     * record how many letter has odd number counts
     * palindrome only allow one letter with odd number counts, so the result should minus the times of other odd number
     */
    public int longestPalindrome1(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) set.remove(c);
            else set.add(c);
        }

        int odd = set.size();
        return s.length() - (odd == 0 ? 0 : odd - 1);
    }

}
