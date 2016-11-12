package LinkedList;

/**
 * Created by Administrator on 2016/11/12.
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
    public static ListNode listBuilder(int[] arr) {
        ListNode head = new ListNode(arr[0]);
        ListNode pointer = head;
        for (int i = 1; i < arr.length; i++) {
            ListNode tmp = new ListNode(arr[i]);
            pointer.next = tmp;
            pointer = tmp;
        }
        return head;
    }
}
