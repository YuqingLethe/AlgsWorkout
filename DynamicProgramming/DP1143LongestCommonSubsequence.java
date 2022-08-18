package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DP1143LongestCommonSubsequence {
    class DP_recursive {
        private int[][] memo;
        private String text1;
        private String text2;

        public int longestCommonSubsequence(String text1, String text2) {
            if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
                return 0;
            }
            // Find the common letters of two strings -> Get one subsequence string with shared letters
            // 这个不是必须的 可以不加
            String shorter = text1.length() > text2.length() ? text2 : text1;
            String longer = text1.length() > text2.length() ? text1 : text2;
            Set<Character> shorterStringChars = new HashSet<>();
            List<Character> list = new ArrayList<>();
            // Build the set of all characters in shorter string
            for (int i = 0; i < shorter.length(); ++i) {
                shorterStringChars.add(shorter.charAt(i));
            }
            // Find subsequenced common characters in longer string
            for (int i = 0; i < longer.length(); ++i) {
                char currChar = longer.charAt(i);
                if (shorterStringChars.contains(currChar)) {
                    list.add(currChar);
                }
            }

            this.text1 = shorter;
            StringBuilder sb = new StringBuilder();
            for (Character c : list) {
                sb.append(c);
            }
            this.text2 = sb.toString(); // 如果不加common character findings, 就直接this.text2 = text2;

            // 注意如果private member和function parameters相同名字, 优先是lower scope
            this.memo = new int[this.text1.length() + 1][this.text2.length() + 1]; //这个必须+1
            for (int i = 0; i < this.text1.length(); ++i) {
                for (int j = 0; j < this.text2.length(); ++j) {
                    this.memo[i][j] = -1;
                }
            }
            return memoSolve(0, 0);
        }

        private int memoSolve(int p1, int p2) {
            if (memo[p1][p2] != -1) {
                return memo[p1][p2];
            }
            int option1 = memoSolve(p1 + 1, p2);
            int option2 = 0;
            int firstOccurance = text2.indexOf(text1.charAt(p1), p2); //还有这个方法不要忘记!
            if (firstOccurance != -1) {
                option2 = 1 + memoSolve(p1 + 1, firstOccurance + 1);
            }

            memo[p1][p2] = Math.max(option1, option2);
            return memo[p1][p2];
        }
    }

    class DP_Advanced {
        private int[][] memo;
        private String text1;
        private String text2;

        public int longestCommonSubsequence(String text1, String text2) {
            if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
                return 0;
            }
            this.text1 = text1;
            this.text2 = text2;
            this.memo = new int[this.text1.length() + 1][this.text2.length() + 1]; //这个必须+1
            for (int i = 0; i < this.text1.length(); ++i) {
                for (int j = 0; j < this.text2.length(); ++j) {
                    this.memo[i][j] = -1;
                }
            }
            return memoSolve(0, 0);
        }

        private int memoSolve(int p1, int p2) {
            if (memo[p1][p2] != -1) {
                return memo[p1][p2];
            }
            int answer = 0;
            if (text1.charAt(p1) == text2.charAt(p2)) {
                answer = 1 + memoSolve(p1 + 1, p2 + 1);
            } else {
                answer = Math.max(memoSolve(p1 + 1, p2), memoSolve(p1, p2 + 1));
            }
            memo[p1][p2] = answer;
            return memo[p1][p2];
        }
    }
}
