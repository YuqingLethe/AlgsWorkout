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
    public static boolean isValid (String s) {
        char[] charArray = s.toCharArray();
        Stack<Character> cs = new Stack<>();
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

    public static void main(String[] args) {
        // write your code here
        String s = "]";
        System.out.println(isValid(s));
    }
}
