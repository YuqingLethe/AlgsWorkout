package Array;

import java.util.Stack;

public class CodilityTaskEqui {
    /**
     * 想偏了，在错误的道路上昂首挺胸走了一个小时也没回来2016/11/4
     */
    private static class node {
        int val;
        int sum;
        public node(int val, int sum) {
            this.val = val;
            this.sum = sum;
        }
    }
    public static int solution(int[] A) {
        if (A.length < 3) return -1;
        Stack<node> sumi = new Stack<>();
        Stack<node> sumj = new Stack<>();
        int i = 0, j = A.length - 1;
        sumi.push(new node(A[i], A[i]));
        sumj.push(new node(A[j], A[j]));

        while (j < 5) {
            int diff = sumi.peek().sum - sumj.peek().sum;
            if (Math.abs(diff + A[i + 1]) <= Math.abs(diff + A[j - 1])) {
                i++;
            }
        }
        return -1;
    }

    public static int solution2(int[] A) {
        int sum = 0, leftSum = 0;
        for (int x : A) {
            sum += x;
        }
        for (int i = 1; i < A.length; i++) {
            leftSum += A[i - 1];
            int rightSum = sum - leftSum - A[i];
            if (rightSum == leftSum) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] b = {3, 2, 3};
        int[] a = {-1, 3, -4, 5, 1, -6, 2,1};
        System.out.println(solution2(b));
    }
}