package Stack;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 *
 * Input: s = ""
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 3 * 104
 * s[i] is '(', or ')'.
 */
public class Stack32LongestValidParentheses {
    /**
     * Crib answer August 2022
     * 一个思考技巧是, 如果都是))则无脑push, 因为我们只要最后)的idx帮助长度计算
     * O(n)
     */
    class StackSolution {
        public int longestValidParentheses(String s) {
            int res = 0;
            int N = s.length();
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);

            for (int i = 0; i < N; ++i) {
                char curr = s.charAt(i);
                if (curr == '(') {
                    stack.push(i);
                } else {
                    stack.pop(); //直接和(前面的index比较, 其实就是 rightIdx - leftIdx + 1 变成 rightIdx - (leftIdx - 1)
                    if (stack.isEmpty()) {
                        stack.push(i);
                    } else {
                        res = Math.max(res, i - stack.peek());
                    }
                }


            }
            return res;
        }
    }

    /**
     * Crib answer Aug 2022
     * 抓住了成对的特点, 巧妙从左到右, 从有到做计算有效对儿,并且排除无效对儿
     */
    class LeftRightTraversal {
        public int longestValidParentheses(String s) {
            int maxLen = 0;
            int leftP = 0, rightP = 0, currLen = 0; //其实不需要currLen也能做, currLen更不需要reset成0.
            int N = s.length();
            // From left to right
            for (int i = 0; i < N; ++i) {
                char c = s.charAt(i);
                if (c == '(') {
                    leftP ++;
                } else {
                    rightP ++;
                }
                if (leftP == rightP) {
                    currLen = leftP + rightP;
                    maxLen = Math.max(maxLen, currLen);
                } else if (leftP < rightP) {
                    currLen = 0;
                    leftP = rightP = 0;// 简单写法
                }
            }
            // From right to left
            leftP = rightP = 0;
            currLen = 0;
            for (int i = N - 1; i >= 0; --i) {
                char c = s.charAt(i);
                if (c == '(') {
                    leftP ++;
                } else {
                    rightP ++;
                }
                if (leftP == rightP) {
                    currLen = leftP + rightP;
                    maxLen = Math.max(maxLen, currLen);
                } else if (leftP > rightP) {
                    currLen = 0;
                    leftP = rightP = 0;
                }
            }

            return maxLen;
        }
    }
}
