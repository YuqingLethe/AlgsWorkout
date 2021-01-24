package Stack;
import java.util.Stack;
public class Stack232ImplementQueueUsingStacks {
    private Stack<Integer> si = new Stack<Integer>();

    // Push element x to the back of queue.
    public void push(int x) {
        si.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        Stack<Integer> temp = new Stack<Integer>();
        while (si.size() != 0 && si.size() > 1) {
            temp.push(si.pop());
        }
        si.pop();
        while (temp.size() > 0) {
            si.push(temp.pop());
        }


    }

    // Get the front element.
    public int peek() {
        Stack<Integer> temp = new Stack<Integer>();
        while (si.size() != 0 && si.size() > 1) {
            temp.push(si.pop());
        }
        System.out.println("si.peek() = " + si.peek());
        int result = si.peek();
        while (temp.size() > 0) {
            si.push(temp.pop());
        }
        return result;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return si.empty();
    }

    public static void main(String[] args) {
        Stack232ImplementQueueUsingStacks longname = new Stack232ImplementQueueUsingStacks();
        longname.push(23);
        longname.push(0);
        longname.pop();
        System.out.println(longname.peek());
    }
}
