package Stack;

import java.util.Stack;

/**
 * Write a function that returns true if all the parathesis, brakets and quotes are properly closed.
 * Anything that is between a set of quotes should be ignored (including parathesis or brackets).
 * <p>
 * // To make sure you understand the problem, which of these strings are well formed?
 * // 1. adv(dff)[ddf[fff(fff)]]. true () [[()]]
 * // 2. (()[)](). false
 * // 3. fff"(fdfd"()fdfdf[]. true
 * // 4. fff"(fdfd")()fdfdf[]. false
 * // 5. fdfd()[[]]"erere. false
 */
public class Stack60001ValidParentheseWithQuotes {
    /**
     * March 2023 看清每一个例子.
     */
    static class StackWithFlag {
        public boolean validParenthese(String s) {
            boolean unclosedQuote = false;
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); ++i) {
                Character c = s.charAt(i);
                if (c == '"' && unclosedQuote) { // closing quote
                    unclosedQuote = false;
                } else if (c == '"') { // starting quote
                    unclosedQuote = true;
                } else if (unclosedQuote) { // characters between quotes
                    continue;
                } else if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else if (c == ')' && (stack.empty() || stack.pop() != '(')) { //注意别忘了empty
                    return false;
                } else if (c == ']' && (stack.empty() || stack.pop() != '[')) {
                    return false;
                } else if (c == '}' && (stack.empty() || stack.pop() != '{')) {
                    return false;
                }

            }
            if (unclosedQuote) { //注意仔细看给的所有例子
                return false;
            }
            return true;
        }
    }
    public static void main(String[] args) {
        StackWithFlag s = new StackWithFlag();
        String str;
        str = "adv(dff)[ddf[fff(fff)]]";
        System.out.println(s.validParenthese(str));
        str = "(()[)]()";
        System.out.println(s.validParenthese(str));
        str = "fff\"(fdfd\"()fdfdf[]";
        System.out.println(s.validParenthese(str));
        str = "fff\"(fdfd\")()fdfdf[]";
        System.out.println(s.validParenthese(str));
        str = "fdfd()[[]]\"erere";
        System.out.println(s.validParenthese(str));
    }
}
