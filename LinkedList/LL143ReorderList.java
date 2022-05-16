package LinkedList;

public class LL143ReorderList {
    public static class LintLL99ReorderList {
        /**
         * 自己不会做, 看得答案. 还得重写. 可以复习LL的基本模块比如reverse, merge等等
         * 2017/7/6
         */
        public class Solution {
            public void reorderList(ListNode head) {
                if (head == null || head.next == null) { //head.next也要考虑啊!
                    return;
                }

                ListNode middle = findMiddle(head);
                ListNode reverseHead = reverse(middle.next); //这个方法真是太巧妙了
                middle.next = null;

                merge(head, reverseHead);
            }
            //这个方法妙, merge的时候不用想何时停!!!
            private void merge (ListNode head1, ListNode head2) {
                int idx = 0;
                ListNode dummy = new ListNode(0);
                while(head1 != null && head2 != null) {
                    if (idx % 2 == 0) {
                        dummy.next = head1;
                        head1 = head1.next;
                    } else {
                        dummy.next = head2;
                        head2 = head2.next;
                    }
                    dummy = dummy.next;
                    idx++;
                }
                if (head1 != null) {
                    dummy.next = head1;
                } else {
                    dummy.next = head2;
                }
            }

            private ListNode reverse(ListNode head) {
                ListNode prev = null; //让队列最后无需跟null.相当于第一个节点是null直接翻转
                while (head != null) {
                    ListNode tmp = head.next;
                    head.next = prev;
                    prev = head;
                    head = tmp;
                }
                return prev;
            }
            private ListNode findMiddle(ListNode head) {
                ListNode fast = head;
                ListNode slow = head;
                while(fast != null && fast.next != null) { //只需要确保fast.next不为null即可~
                    fast = fast.next.next;
                    slow = slow.next;
                }
                return slow;
            }
        }

    }

    /**
     * May2022 自己做了1.5hrs
     */
    static class Reverse_Second_Half_And_Combine {
        public static void reorderList(ListNode head) {
            //注意下面的条件都是first list至少有2个元素, 所以这里corner case用head.next.next
            if (head == null || head.next == null || head.next.next == null) {
                return;
            }
            // Get the middle node
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            // Cut the list and reverse the second half
            ListNode newHead = slow.next;
            slow.next = null;
            ListNode.printLinkedList(head);
            ListNode.printLinkedList(newHead);

            newHead = reverseList(newHead);
            ListNode.printLinkedList(newHead);

            combineLists(head, newHead);
        }
        public static ListNode combineLists(ListNode front, ListNode back) {
            ListNode head = front;
            ListNode first = front;
            ListNode second = back;
            while (second != null) {
                ListNode curr1 = first.next;
                ListNode curr2 = second.next;
                first.next = second;
                second.next = curr1;
                first = curr1;
                second = curr2;
            }
            return head;
        }

        private static ListNode reverseList(ListNode head) {
            ListNode front = head;
            ListNode rear = head.next;
            while (rear != null) {
                front.next = rear.next;
                rear.next = head;
                head = rear;
                rear = front.next;
            }
            return head;
        }
    }
    public static void main(String[] args) {
        int[] a = {1,2,3};
        int[] b = {6,5,4};
        int[] c = {1,2,3,4,5,6};
        ListNode nodeA = ListNode.listBuilder(a);
        ListNode nodeC = ListNode.listBuilder(c);
        Reverse_Second_Half_And_Combine.reorderList(nodeC);
        ListNode.printLinkedList(nodeC);
    }
}
