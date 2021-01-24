package Stack;

import java.util.Stack;

public class Stack20ValidParenthese {
    /**
     * Method 1: 1ms 50% <br>
     * Using stack<Character>
     * <p>
     * Failures<br>
     * 1. Corner Case "]"
     */
    public static boolean isValidByToCharArray (String s) {
        char[] charArray = s.toCharArray();
        Stack<Character> cs = new Stack<Character>();
        for (int i = 0; i < charArray.length; i++) {
            if (isLeftHalf(charArray[i])) {
                cs.push(charArray[i]);
            } else {
                if (cs.size() != 0 && isPair(cs.peek(), charArray[i])) {
                    cs.pop();

                } else
                    return false;
            }
        }
        if (cs.size() != 0)
            return false;
        else
            return true;
    }
    private static boolean isLeftHalf (char c) {
        if (c == '(' || c == '[' || c == '{')
            return true;
        else
            return false;
    }
    private static boolean isPair(Character ch, char c) {
        if (ch == 40 && c == 41)
            return true;
        if (ch == 91 && c == 93)
            return true;
        if (ch ==123 && c == 125)
            return true;
        return false;
    }

    /**
     * 20210120 15min
     * Runtime: 1 ms, faster than 98.75% of Java online submissions for Valid Parentheses.
     * Memory Usage: 37.1 MB, less than 62.68% of Java online submissions for Valid Parentheses.
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        //11am
        if (s == null || s.length() == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i ++) {
            Character c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{' ) {
                stack.push(s.charAt(i));
            } else {
                if (stack.size() == 0) {
                    return false;
                }
                Character curr = stack.peek();
                if ((curr == '(' && c == ')')
                        || (curr == '[' && c == ']')
                        || (curr == '{' && c =='}')
                ) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        // write your code here
        String s = "]";

    }
}
