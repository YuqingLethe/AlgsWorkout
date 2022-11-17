package Utility;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

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
    public static void printTreeMapIntegers(TreeMap<Integer, Integer> myMap) {
        for (Map.Entry<Integer, Integer> entry : myMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ". Value: " + entry.getValue());
        }
    }

    private void printListInDoubleArray(List<int[]> t) {
        for (int i = 0; i < t.size(); ++i) {
            System.out.print(t.get(i)[0] + "-" + t.get(i)[1] + ", ");
        }
        System.out.println();
    }
}
