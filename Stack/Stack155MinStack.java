package Stack;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Runtime 118ms   2016/11/4.
 */
public class Stack155MinStack {
    private class minStack {
        private Stack<Integer> si;
        private Integer min;
        /** initialize your data structure here. */
        public minStack() {
            si = new Stack<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            si.push(x);
            if (x < min) min = x;
        }

        public void pop() {
            Integer temp = si.pop();
            //Refind the min
            if(temp.equals(min)) {
                min = Integer.MAX_VALUE;
                for (Integer x : si) {
                    if (x.compareTo(min) < 0) min = x;
                }
            }
        }

        public int top() {
            return si.peek();
        }

        public int getMin() {
            return min;
        }
    }


    /**
     * Runtime: 144
     * Define new node () with val, min, and next node
     * Head will hold the min
     *
     * If so, the head will be the base of the stack
     * https://discuss.leetcode.com/topic/33199/clean-6ms-java-solution
     */

    private class MinStackByPrivateClass {
        private class node {
            int val;
            int min;
            node next;

            private node(int val, int min) {
                this(val, min, null);
            }
            public node (int val, int min, node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }

        private node head;
        public void push(int x) {
            if (head == null)
                head = new node(x, x);
            else
                head = new node(x, Math.min(x, head.min), head);
        }
        public void pop() {
            head = head.next;
        }
        public int top() {
            return head.val;
        }
        public int getMin() {
            return head.min;
        }
    }

    /**
     * Runtime: 153ms  Use: 15min debugging 11/16/2016
     * Don't forget to pop min as well as origin
     */
    private class minStackByTwoStacks {
        private Stack<Integer> origin;
        private Stack<Integer> min;
        public minStackByTwoStacks() {
            origin = new Stack<>();
            min = new Stack<>();
        }

        public void push(int x) {
            origin.push(x);
            if (min.isEmpty()) {
                min.push(x);
            } else {
                if (x > min.peek()) min.push(min.peek());
                else min.push(x);
            }
        }

        public void pop() {
            origin.pop();
        }

        public int top() {
            return origin.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }
}



//TODO: Give another stack as the minStack to solve this
//https://discuss.leetcode.com/topic/6339/java-solution-accepted/3

