package LintCode.Binary.LinkedList;

/**
 * Created by Administrator on 2017/7/6.
 */
public class LintLL36ReverselinkedListII {
    /**
     * 2017/7/6.
     * @param ListNode head is the head of the linked list
     * @oaram m and n
     * @return: The head of the reversed ListNode
     */
    public class Solution {
        public ListNode reverseBetween(ListNode head, int m , int n) {
            // write your code
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode prevM = dummy;
            for (int i = 1; i < m; i++)  {
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
