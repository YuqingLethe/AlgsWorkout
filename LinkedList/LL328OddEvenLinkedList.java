package LinkedList;

public class LL328OddEvenLinkedList {
    /**
     * 2017/7/13
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode oddTail = head;
        ListNode evenTail = head.next;
        ListNode idx = evenTail.next;
        boolean isOdd = true;
        while (idx != null) {
            ListNode tmp = idx; //注意了当自身的前后节点发生改变时, 应在发生改变之前先移动到下一位, 最后再移动就错了
            idx = idx.next;

            if (isOdd) {
                evenTail.next = tmp.next;
                tmp.next = oddTail.next;
                oddTail.next = tmp;
                oddTail = tmp;
            } else {
                evenTail = tmp;
            }

            isOdd = !isOdd;
        }
        return head;
    }
    //TODO: 一次变两个, evenTail和oddTail同时改变
}
