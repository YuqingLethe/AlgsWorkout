package Stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * on 7/17/22.
 */
public class Stack224BasicCalculator {
    class StackNoReversal {
        public int calculate(String s) {
            Stack<Integer> stack = new Stack<>();
            int operand = 0;
            int result = 0;
            int sign = 1;

            for (int i = 0; i < s.length(); ++i) {
                char ch = s.charAt(i);
                if (Character.isDigit(ch)) {
                    operand = 10 * operand + (int) (ch - '0');
                } else if (ch == '+') {
                    result += sign * operand;
                    sign = 1;
                    operand = 0;
                } else if (ch == '-') {
                    result += sign * operand;
                    sign = -1;
                    operand = 0;
                } else if (ch == '(') {
                    stack.push(result);
                    stack.push(sign);
                    sign = 1;
                    result = 0;
                } else if (ch == ')') {
                    result += sign * operand;
                    result *= stack.pop();
                    result += stack.pop();
                    operand = 0;
                }
                System.out.println("operand=" + operand + " sign=" + sign + " result=" + result);
                System.out.println(stack.toString());
            }
            return result + (sign * operand);
        }
    }

    class StackReverse {

        private int evaluateExpr(Stack<Object> stack) {
            if (stack.empty() || !(stack.peek() instanceof Integer)) {
                stack.push(0);
            }
            int res = (int) stack.pop();
            System.out.println("res=" + res);
            System.out.println(stack.toString());
            while(!stack.empty() &&
                    !((char) stack.peek() == ')')
                    ) {
                char sign = (char) stack.pop();
                System.out.println(sign);
                if (sign == '+') {
                    res += (int) stack.pop();
                } else {
                    res -= (int) stack.pop();
                }
            }
            return res;
        }
        public int calculate(String s) {
            Stack<Object> stack = new Stack<>();
            int operand = 0;
            int n = 0;

            for (int i = s.length() - 1; i >= 0; --i) {
                char ch = s.charAt(i);
                System.out.println("main ch=" + ch);
                if (Character.isDigit(ch)) {
                    operand = (int) Math.pow(10, n) * (int)(ch - '0') + operand;
                    n += 1;
                } else if (ch != ' ') {
                    if (n != 0) {
                        stack.push(operand);
                        n = 0;
                        operand = 0;
                    }
                    if (ch == '(') {
                        int res = evaluateExpr(stack);
                        stack.pop();
                        stack.push(res);
                    } else {
                        stack.push(ch);
                    }
                }
                // System.out.println("operand=" + operand + " sign=" + sign + " result=" + result);
                // System.out.println(stack.toString());
            }
            if (n != 0) {
                stack.push(operand);
            }
            return evaluateExpr(stack);
        }
    }

    class CalculatorMuban {
        public int calculate(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            Queue<Character> q = new LinkedList<>();
            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                if (c == ' ') {
                    continue;
                }
                q.add(c);
            }
            return calculateSubExpression(q);
        }
        private int calculateSubExpression(Queue<Character> q) {
            Stack<Long> stack = new Stack<>();
            long num = 0;
            char preOperand = '+';
            while (!q.isEmpty()) {
                char ch = q.poll();
                if (Character.isDigit(ch)) {
                    num = num*10 + ch - '0';
                } else if ("+-*/".indexOf(ch) != -1) {
                    addToStack(stack, num, preOperand);
                    preOperand = ch;
                    num = 0;
                } else if (ch == '(') {
                    num = calculateSubExpression(q);
                } else {
                    break;
                }
            }
            addToStack(stack, num, preOperand);
            int result = 0;
            while (!stack.isEmpty()) {
                result += stack.pop();
            }
            return result;
        }

        private void addToStack(Stack<Long> stack, Long num, char preOperand) {
            switch(preOperand) {
                case '+':
                    stack.push(num);
                    break;
                case '-':
                    stack.push(-num);
                    break;
                default:
                    break;

            }
        }
    }

    public static void main(String[] args) {
        String s1 = "(1+(4+5+2)-3)+(6+8)";
    }
}
