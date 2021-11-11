package Helper;

import java.util.Stack;

public class PrintDataStructure {
    public static void printIntegerStack(Stack<Integer> s ) {
        Stack<Integer> stack = (Stack)s.clone();
        StringBuilder sb = new StringBuilder("Integer Stack backwards: ");
        while (!stack.isEmpty()) {
            Integer curr = stack.pop();
            sb.append(curr + " ");
        }
        System.out.println(sb.toString());
    }
}
