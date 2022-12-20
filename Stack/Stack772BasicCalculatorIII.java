package Stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Stack772BasicCalculatorIII {
    /**
     * Nov2022 Crib answer
     * https://leetcode.com/problems/basic-calculator-iii/solutions/1727380/java-this-simple-template-can-be-used-for-basic-calculator-i-ii-iii/
     * 三个题目的模板都适用
     */
    class Template {
        public int calculate(String s) {
            // Write your code here
            if (s == null || s.isEmpty()) {
                return 0;
            }
            Queue<Character> q = new LinkedList<>();
            for (char c : s.toCharArray()) {
                q.offer(c);
            }
            System.out.println(q.toString());
            return calResultOfSubFomula(q);
        }
        private int calResultOfSubFomula(Queue<Character> q) {
            int num = 0;
            Stack<Integer> stack = new Stack<>();
            char prevOp = '+';
            while (!q.isEmpty()) {
                Character c = q.poll();
                if (Character.isDigit(c)) {
                    num = num * 10 + c - '0';
                } else if (c == '(') {
                    num = calResultOfSubFomula(q); //注意这里进入了小括号内的运算
                } else if ("+-*/".indexOf(c) != -1) {
                    evalPartialUpdateStack(stack, num, prevOp);
                    prevOp = c; //注意这里的符号是当前符号
                    num = 0;
                } else if (c == ')') {
                    break;
                }
            }
            evalPartialUpdateStack(stack, num, prevOp);
            // System.out.println("Before return, stack=" + stack.toString());
            return stack.stream().mapToInt(a -> a).sum();
        }
        private void evalPartialUpdateStack(Stack<Integer> stack, int num, char op) {
            // System.out.println("stack=" + stack.toString());
            // System.out.println("num="+ num);
            // System.out.println("op: " + op);
            switch(op) {
                case '+':
                    stack.push(num);
                    break;
                case '-':
                    stack.push(-num);
                    break;
                case '*':
                    int prevNum = stack.pop();
                    stack.push(prevNum*num);
                    break;
                case '/':
                    stack.push(stack.pop()/num);
                    break;
                default:
                    break;
            }
        }
    }
}
