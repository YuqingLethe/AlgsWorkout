package Stack;

import java.util.Stack;

public class Stack227BasicCalculatorII {
    /**
     * 60min Crib answer from official solution
     * 之前我自己打算两个stack, 加减是主stack, 乘除用一个辅助的算部分数值. 答案这个思路更好,
     *   因为乘除只关注前后两个值即可, 也就是辅助的stack最多就两个值. 因此直接用prevNum保存数值, 用prevOperation俩完成辅助stack的运算即可.
     *
     * 写码易错点:
     * 1. 没搞清楚prevNum和prevOperation的用法
     * 2. 忘记考虑10以上的数字了.
     * 3. 拿多位数字没必要用while得到整个数值, 可以继续在prevNum上做文章
     * 4. 何时push stack要想清楚
     * 5. reset prevNum别忘记
     * 6. 到了string结尾别忘记也要计算一下 i==N-1的判断很重要
     */
    class CribAnswer_OneStack {
        public int calculate(String s) {
            s = s.replaceAll("\\s", ""); //或者for循环的时候直接跳过
            Stack<Integer> stack = new Stack<>();
            final int N = s.length();
            char[] arr = s.toCharArray();
            int prevNum = 0;
            char prevOperation = '+';

            for (int i = 0; i < N; ++i) {
                char curr = arr[i];
                if (Character.isDigit(curr)) { // If digit, get the whole number
                    prevNum = prevNum*10 + (curr - '0');
                }
                if (!Character.isDigit(curr) || i == N - 1) { // If not digit, calculate previous part by current operation
                    int newNum = prevNum;
                    if (prevOperation == '*' || prevOperation == '/') {
                        int prev = stack.pop();
                        newNum = prevOperation == '*' ? prev*prevNum : prev/prevNum;
                    }
                    if (prevOperation == '-') {
                        newNum = -prevNum;
                    }
                    stack.push(newNum);
                    prevNum = 0;
                    prevOperation = curr;
                }
                // System.out.println("i=" + i + "curr=" + curr);
                // System.out.println(stack.toString());
            }

            int result = 0;
            while (!stack.isEmpty()) {
                result += stack.pop();
            }
            return result;
        }
    }

    public static void main(String[] args) {
        String s = "23+2*4";
        s = " 243 + 3 * 56 ";

    }
}
