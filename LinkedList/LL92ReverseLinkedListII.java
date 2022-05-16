package LinkedList;

/**
 * Given the head of a singly linked list and two integers left and right where left <= right,
 *    reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 *
 * Example 2:
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 *
 * Constraints:
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 */
public class LL92ReverseLinkedListII {
    /**
     * https://leetcode.com/problems/reverse-linked-list-ii/discuss/30673/Why-nobody-does-it-with-recursion-shouldn't-the-code-be-simpler
     */
    class Recursive {

        private boolean stop;
        private ListNode left;

        public ListNode reverseBetween(ListNode head, int left, int right) {
            this.left = head;
            this.stop = false;
            this.recurseAndReverse(head, left, right);
            return head;
        }

        private void recurseAndReverse(ListNode right, int m, int n) {
            if (n == 1) {
                return;
            }
            // 这一步是 right 和left一起走, 移动m-1次一直到第m个节点
            right = right.next;
            // 这是 right 再继续走n-m次, 一直到n==1以后, 也就是 right 走到第n节点
            if (m > 1) {
                this.left = this.left.next;
            }
            this.recurseAndReverse(right, m - 1, n - 1);

            //等两节点相遇, 停止. 然后保持这个状态, 一直backtracking(回退)到结束
            // (结束就是从相遇的点一直回退到最初头节点)
            if (this.left == right || right.next == this.left) {
                this.stop = true;
            }

            if (!this.stop) {
                int t = this.left.val;
                this.left.val = right.val;
                right.val = t;
            }
            //每次只走left了, 因为 right 向前移动backtracking就帮忙做了
            this.left = this.left.next;
        }
    }

    class Iterative_Link_Reversal {
        public ListNode reverseBetween_April2022_Self(ListNode head, int left, int right) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            // Find the Node before leftNode and the rightNode
            ListNode prevLeft = dummy;
            for (int i = 0; i < left - 1; ++i) {
                prevLeft = prevLeft.next;
            }
            ListNode rightNode = prevLeft.next;
            for (int i = left; i < right; ++i) {
                rightNode = rightNode.next;
            }

            // Reverse
            ListNode curr = prevLeft.next;
            for (int i = 0; i < right - left; ++i) {
                prevLeft.next = curr.next;
                curr.next = rightNode.next;
                rightNode.next = curr;
                curr = prevLeft.next;
            }
            return dummy.next;
        }

        public ListNode reverseBetween_StandardAnswer(ListNode head, int m, int n) {
            // Empty list
            if (head == null) {
                return null;
            }

            // Move the two pointers until they reach the proper starting point
            // in the list.
            ListNode cur = head, prev = null;
            while (m > 1) {
                prev = cur;
                cur = cur.next;
                m--;
                n--;
            }

            // The two pointers that will fix the final connections.
            ListNode con = prev, tail = cur;

            // Iteratively reverse the nodes until n becomes 0.
            ListNode third = null;
            while (n > 0) {
                third = cur.next;
                cur.next = prev;
                prev = cur;
                cur = third;
                n--;
            }

            // Adjust the final connections as explained in the algorithm
            if (con != null) {
                con.next = prev;
            } else {
                head = prev;
            }

            tail.next = cur;
            return head;
        }

        /**
         * Created by Administrator on 2017/7/6.
         * 这个把上面两个后面的for循环结合在了一起.
         * TODO: 下次做这个方法
         */
        public ListNode reverseBetween_twoLoop(ListNode head, int m, int n) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode prevM = dummy;
            for (int i = 1; i < m; i++) {
                if (prevM == null) { //这个case别忘了
                    return null;
                }
                prevM = prevM.next;
            }

            //reverse
            ListNode premNode = prevM;
            ListNode mNode = premNode.next;
            ListNode nNode = mNode, postnNode = mNode.next;
            // 每次将postnNode放在nNode前面即可. nNode一直指向断链首节点
            //  premNode -> mNode -> nNode -> postnNode
            for (int i = m; i < n; i++) {
                if (postnNode == null) { //这里处理m==n的情况
                    return null;
                }
                ListNode temp = postnNode.next;
                postnNode.next = nNode;
                nNode = postnNode;
                postnNode = temp;
            }
            mNode.next = postnNode; //for loop里面不用和头尾连在一起
            postnNode.next = nNode;
            return dummy.next;
        }
    }
}
