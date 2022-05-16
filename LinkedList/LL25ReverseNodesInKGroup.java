package LinkedList;

import javafx.util.Pair;

/**
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 *
 * Example 2:
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 *
 * Constraints:
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 *
 * Follow-up: Can you solve the problem in O(1) extra memory space?
 */
public class LL25ReverseNodesInKGroup {
    /**
     * May 2022 Self
     */
    class Iterative_SpaceComplexityO1 {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (k == 1 || head == null || head.next == null) {
                return head;
            }
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode curr = dummy;
            while(curr.next != null) {
                Pair<ListNode, ListNode> tmpRestList = reverseNextK(curr, k);
                ListNode left = tmpRestList.getKey();
                ListNode right = tmpRestList.getValue();
                if (right == null) {
                    break;
                }
                curr.next = left;
                curr = right;
            }
            return dummy.next;
        }
        private Pair<ListNode, ListNode> reverseNextK(ListNode prev, int k) {
            ListNode head = prev.next;
            ListNode end = prev;
            // Make sure rest of list has more than k nodes, otherwise return as is.
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                return new Pair(head, null);
            }
            // Reverse the k nodes start from prev.next;
            ListNode b = end;
            while (end != prev.next) {
                ListNode currHead = prev.next;
                prev.next = prev.next.next;
                currHead.next = b.next;
                b.next = currHead;
            }
            return new Pair(end, head);
        }
    }

    /**
     * 这个reverse比较简单的原因是, 我有忘记了linkedlist完全可以维护/拆开成为, 两个lists!!! 不需要do everything in place!
     * 只要保证O(1)即可!!!
     */
    class Iterative_CribAnswer {
        public ListNode reverseLinkedList(ListNode head, int k) {
            // Reverse k nodes of the given linked list.
            // This function assumes that the list contains at least k nodes.
            ListNode new_head = null;
            ListNode ptr = head;
            while (k > 0) {

                // Keep track of the next node to process in the
                // original list
                ListNode next_node = ptr.next;

                // Insert the node pointed to by "ptr" at the beginning of the reversed list
                ptr.next = new_head;
                new_head = ptr;
                // Move on to the next node
                ptr = next_node;
                // Decrement the count of nodes to be reversed by 1
                k--;
            }
            // Return the head of the reversed list
            return new_head;

        }

        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode ptr = head;
            ListNode ktail = null;
            // Head of the final, moified linked list
            ListNode new_head = null;

            // Keep going until there are nodes in the list
            while (ptr != null) {
                int count = 0;
                // Start counting nodes from the head
                ptr = head;
                // Find the head of the next k nodes
                while (count < k && ptr != null) {
                    ptr = ptr.next;
                    count += 1;
                }
                // If we counted k nodes, reverse them
                if (count == k) {
                    // Reverse k nodes and get the new head
                    ListNode revHead = this.reverseLinkedList(head, k);
                    // new_head is the head of the final linked list
                    if (new_head == null)
                        new_head = revHead;
                    // ktail is the tail of the previous block of reversed k nodes
                    if (ktail != null)
                        ktail.next = revHead;

                    ktail = head;
                    head = ptr;
                }
            }

            // attach the final, possibly un-reversed portion
            if (ktail != null)
                ktail.next = head;

            return new_head == null ? head : new_head;
        }
    }

    class Recursive_CribAnswer {
        public ListNode reverseLinkedList(ListNode head, int k) {
            // Reverse k nodes of the given linked list.
            // This function assumes that the list contains at least k nodes.
            ListNode new_head = null;
            ListNode ptr = head;
            while (k > 0) {
                // Keep track of the next node to process in the original list
                ListNode next_node = ptr.next;
                // Insert the node pointed to by "ptr" at the beginning of the reversed list
                ptr.next = new_head;
                new_head = ptr;
                // Move on to the next node
                ptr = next_node;
                // Decrement the count of nodes to be reversed by 1
                k--;
            }
            // Return the head of the reversed list
            return new_head;

        }

        public ListNode reverseKGroup(ListNode head, int k) {
            int count = 0;
            ListNode ptr = head;
            // First, see if there are atleast k nodes left in the linked list.
            while (count < k && ptr != null) {
                ptr = ptr.next;
                count++;
            }
            // If we have k nodes, then we reverse them
            if (count == k) {
                // Reverse the first k nodes of the list and get the reversed list's head.
                ListNode reversedHead = this.reverseLinkedList(head, k);

                // Now recurse on the remaining linked list. Since
                // our recursion returns the head of the overall processed
                // list, we use that and the "original" head of the "k" nodes
                // to re-wire the connections.
                head.next = this.reverseKGroup(ptr, k);
                return reversedHead;
            }
            return head;
        }
        class SelfCribSolutin_May2022 {
            public ListNode reverseKGroup(ListNode head, int k) {
                ListNode ptr = head;
                int count = 0;
                while(count < k && ptr != null) {
                    ptr = ptr.next;
                    ++count;
                }
                if (count == k) {
                    ListNode rest = this.reverseNextK(head, k);
                    head.next = this.reverseKGroup(ptr, k);
                    return rest;
                }
                return head;
            }
            private ListNode reverseNextK(ListNode head, int k) {
                ListNode newHead = null;
                ListNode ptr = head;

                while (k > 0) {
                    ListNode nextHead = ptr.next;
                    ptr.next = newHead;
                    newHead = ptr;
                    ptr = nextHead;
                    -- k;
                }
                return newHead; //k后面的node不关心, 在主function里被track
            }
        }
    }
}
