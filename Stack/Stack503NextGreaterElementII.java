package Stack;

import java.util.Stack;

public class Stack503NextGreaterElementII {
    /**
     * Sep 2022 40min没写出看答案. 自己看完答案结合496写的从左边开始的循环
     * 为何自己没想到这个solution:
     * 1. 自己没想到的点: 最多2次循环一定能找完, 所以没必要纠结第二次循环在哪里停. 应该直接复制array或者就两个循环
     * 2. 答案的stack比较简单是用了Stack<int[]>这种结构, 而避免了numStack和idxStack这种比较复杂的判断.
     *    当然同时也是自己之前用idx想找到终止循环的点, 而其实没必要. 因此idxStack就只有一个记录index的功能, 因此可以合并为一个stack
     *    还可以完全只存idx其实, 因为value可以通过数组找到.
     *
     * 这个解法更简洁的书写:https://leetcode.com/problems/next-greater-element-ii/discuss/98270/JavaC%2B%2BPython-Loop-Twice
     * 这个解法有个问题是, 第二次循环会有重复的while再次发生.
     */
    class Stack_FromLeft {
        public int[] nextGreaterElements(int[] nums) {
            final int N = nums.length;
            int[] result = new int[N];
            if (nums == null || nums.length == 0) {
                return result;
            }
            // Initialize all elements as -1 in result array
            for (int i = 0; i < N; ++i) {
                result[i] = -1;
            }

            // Find the next Grater and upate result array
            Stack<int[]> stack = new Stack<>();
            for (int i = 0; i < 2*N; ++i) {
                int currIdx = i % N;
                int currVal = nums[currIdx];
                while (!stack.isEmpty() && stack.peek()[0] < currVal) { // If find next greater
                    int[] top = stack.pop();
                    if (result[top[1]] == -1) { // If already exist, must be the next greater. So skip it.
                        result[top[1]] = currVal;
                    }
                }
                int[] curr = new int[]{currVal, currIdx};
                stack.push(curr);
            }
            return result;
        }
    }

    /**
     * 答案的解法,
     * loop from back to start could be even faster, since we don't need to store index and avoid getting number from index each time.
     * TODO: priority high 10/5之前写. 从后往前, 只存value试试 / 其实只存indx也可, 但没有value高效.
     */
    class Stack_fromEnd {

    }
}
