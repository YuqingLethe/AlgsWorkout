package LinkedList;

import java.util.ArrayList;

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
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode pointer = head;
        for (int i = 1; i < arr.length; i++) {
            ListNode tmp = new ListNode(arr[i]);
            pointer.next = tmp;
            pointer = tmp;
        }
        return head;
    }

    public static void printLinkedList(ListNode head) {
        ArrayList<Integer> arr = new ArrayList<>();
        while (head != null) {
            arr.add(head.val);
            head = head.next;
        }
        System.out.println(arr.toString());
    }

    public static void printListNode(ListNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        System.out.println(head.val);
    }

}
