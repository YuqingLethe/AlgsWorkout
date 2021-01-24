package DynamicProgramming;

/**
 * Created by me on 1/19/21.
 *
 * Given a string s, return the longest palindromic substring in s.



 Example 1:

 Input: s = "babad"
 Output: "bab"
 Note: "aba" is also a valid answer.
 Example 2:

 Input: s = "cbbd"
 Output: "bb"
 Example 3:

 Input: s = "a"
 Output: "a"
 Example 4:

 Input: s = "ac"
 Output: "a"


 Constraints:

 1 <= s.length <= 1000
 s consist of only digits and English letters (lower-case and/or upper-case),
 */
public class DP5LongestPalindromicSubstring {

    /**
     * 20210119 80min没做出来直接看答案
     * https://leetcode.com/problems/longest-palindromic-substring/discuss/1024440/Java-Step-by-step-whiteboard-easy-peasy-detailed-explanation
     * 1. 压根没想起来还能用双loop得到所有的palindrome。只想到了左右双指针。应该联想，数组里面取string必然是两个指针，那么double loop就可行！
     *
     * 176 / 176 test cases passed.
     * Status: Accepted
     * Runtime: 23 ms
     Memory Usage: 38.9 MB
     * @param s
     * @return
     */
    private static int longest = 1;
    private static int startIndex = 0;

    public String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            findExtendedPalindrome(s, i, i);
            findExtendedPalindrome(s, i, i + 1);
        }
        return s.substring(startIndex, startIndex + longest);
    }
    private void findExtendedPalindrome(String s, int i, int j) {
        while (i >= 0 && j < s.length() && i <= j && s.charAt(i) == s.charAt(j)) {
            i --;
            j ++;
        }
        if (longest < j - i - 1) {
            longest = j - i - 1;
            startIndex = i + 1;
        }
    }

    /**
     * Approach 4: Expand Around Center      In fact, we could solve it in O(n^2)O(n2) time using only constant space.
     * We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center, and there are only 2n - 1 such centers.
     * You might be asking why there are 2n −1 but not n centers? The reason is the center of a palindrome can be in between two letters. Such palindromes have even number of letters (such as "abba") and its center are between the two 'b's.
     *
     * 中心思想：1. 把判断palindrome的那个第三个循环，变成第二个循环，从而减少一个循环变为O(n^2)
     * 2.
     *
     * @param s
     * @return
     */
    public String OthersBestSolution(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String[] args) {
        String s1 = "babad";
        String s2 = "cbbd";
        String s3 = "a";
        DP5LongestPalindromicSubstring thisClass = new DP5LongestPalindromicSubstring();
        System.out.println(thisClass.longestPalindrome(s2));

    }
}
