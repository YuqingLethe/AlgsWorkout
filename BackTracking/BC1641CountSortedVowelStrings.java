package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
 *
 * A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 5
 * Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
 * Example 2:
 *
 * Input: n = 2
 * Output: 15
 * Explanation: The 15 sorted strings that consist of vowels only are
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
 * Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
 * Example 3:
 *
 * Input: n = 33
 * Output: 66045
 *
 *    Hide Hint #1
 * For each character, its possible values will depend on the value of its previous character, because it needs to be not smaller than it.
 *    Hide Hint #2
 * Think backtracking. Build a recursive function count(n, last_character) that counts the number of valid strings of length n and whose first characters are not less than last_character.
 *    Hide Hint #3
 * In this recursive function, iterate on the possible characters for the first character, which will be all the vowels not less than last_character, and for each possible value c, increase the answer by count(n-1, c).
 */
public class BC1641CountSortedVowelStrings {
    /**
     * 20200117 50min
     * Hint看了俩。重温backtracking自己写对了
     * 卡住的地方是，u和e比要多两个possibility，只需要多加("u", c - 1) and ("o", c - 1)两种情况即可，而不是所有的c-1还要乘以5
     * 主要是想明白当前位置LastString和前面possibilities的关系。
     *
     * Runtime: 111 ms, faster than 12.46% of Java online submissions for Count Sorted Vowel Strings.
     * Memory Usage: 35.7 MB, less than 76.53% of Java online submissions for Count Sorted Vowel Strings.
     */
    public int countVowelStrings(int n) {
        return availLexiBySpacesBeforeLastString("u", n);
    }

    private int availLexiBySpacesBeforeLastString (String lastString, int spacesBefore) {
        if (spacesBefore == 1) {
            switch (lastString) {
                case "a": return 1;
                case "e": return 2;
                case "i": return 3;
                case "o": return 4;
                case "u": return 5;
            }
        }
        int res = 0;
        switch (lastString) {
            case "u":
                res += availLexiBySpacesBeforeLastString("u", spacesBefore - 1);
            case "o":
                res += availLexiBySpacesBeforeLastString("o", spacesBefore - 1);
            case "i":
                res += availLexiBySpacesBeforeLastString("i", spacesBefore - 1);
            case "e":
                res += availLexiBySpacesBeforeLastString("e", spacesBefore - 1);
            case "a":
                res += availLexiBySpacesBeforeLastString("a", spacesBefore - 1);
        };
        return res;
    }

    public static void main(String[] args) {
        int n = 4;
    }
}
