package Stack;

import java.util.LinkedList;
import java.util.Queue;


public class Stack225ImplementStackUsingQueues {
    /**
     * Runtime: 115ms 2016/11/4.
     * Push() is inefficient
     */
    public class MyStackByPushInefficient {
        private Queue<Integer> q1 = new LinkedList<>();
        private Queue<Integer> q2 = new LinkedList<>();
        public void push(int x) {
            if (q1.isEmpty()) q1.add(x);
            else {
                while (!q1.isEmpty()) {
                    int temp = q1.poll();
                    q2.add(temp);
                }
                q1.add(x);
                while (!q2.isEmpty()) {
                    int temp = q2.poll();
                    q1.add(temp);
                }
            }
        }

        public void pop() {
            q1.poll();
        }

        public int top() {
            return q1.peek();
        }

        public boolean empty() {
            if(q1.isEmpty()) return true;
            else return false;
        }
    }

    /**
     * Runtime:
     */
    public static class StackByPopTopInefficient {
        private Queue<Integer> q1 = new LinkedList<>();
        private Queue<Integer> q2 = new LinkedList<>();

        public void push(int x) {
            q1.add(x);
        }
        public void pop() {
            if (q1.isEmpty()) {
                for (int i = 0; i < q2.size() - 1; i++) {
                    q1.add(q2.poll());
                }
                q2.poll();
            } else {
                for (int i = 0; i < q1.size() - 1; i++) {
                    q2.add(q1.poll());
                }
                q1.poll();
            }
        }
        public int top() {
            int answer;
            if (q1.isEmpty()) {
                for (int i = 0; i < q2.size() - 1; i++) {
                    q2.add(q1.poll());
                }
                answer = q2.peek();
            } else {
                for (int i = 0; i < q1.size() - 1; i++) {
                    q2.add(q1.poll());
                }
                answer = q1.peek();
            }
            return answer;
        }
        public boolean empty() {
            return q1.isEmpty() && q2.isEmpty();
        }
    }
    public static void main(String[] args) {
        int[] nums = {0,1,0,2,0,3,2};
        StackByPopTopInefficient stack = new StackByPopTopInefficient();
    }


    /**
     * Runtime:
     * Rotate the q whenver push new number
     */
    private class StackByOneQueue{
        private Queue<Integer> q1 = new LinkedList<>();
        public void push(int x) {
            q1.add(x);
            for (int i = 1; i < q1.size(); i++) {
                q1.add(q1.poll());
            }
        }
        //Come on such easy
        public boolean empty() {
            return q1.isEmpty();
        }
    }

//https://discuss.leetcode.com/topic/36189/java-solutions-about-three-ways-one-of-which-utilizes-one-queue-and-the-others-utilize-two-queues


}





