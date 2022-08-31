package Utility;

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
    public static void printArray(int[] arr, String arrayName) {
        System.out.print(arrayName + ": ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static void printMatrix(int[][] arr, String arrayName) {
        System.out.println(arrayName + ": ");
        for (int i = 0; i < arr.length; ++i) {
            for (int j : arr[i]) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
