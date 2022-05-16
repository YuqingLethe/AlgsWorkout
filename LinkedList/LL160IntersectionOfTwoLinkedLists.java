package LinkedList;
import java.util.Stack;

/**
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
 *   If the two linked lists have no intersection at all, return null.
 *
 * For example, the following two linked lists begin to intersect at node c1:
 * The test cases are generated such that there are no cycles anywhere in the entire linked structure.
 * Note that the linked lists must retain their original structure after the function returns.
 *
 * Custom Judge:
 * The inputs to the judge are given as follows (your program is not given these inputs):
 * intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
 * listA - The first linked list.
 * listB - The second linked list.
 * skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
 * skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
 * The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program.
 *   If you correctly return the intersected node, then your solution will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * Output: Intersected at '8'
 * Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 *
 * Example 2:
 *
 * Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * Output: Intersected at '2'
 * Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
 *
 * Example 3:
 *
 * Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * Output: No intersection
 * Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
 * Explanation: The two lists do not intersect, so return null.
 *
 *
 * Constraints:
 *
 * The number of nodes of listA is in the m.
 * The number of nodes of listB is in the n.
 * 1 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA < m
 * 0 <= skipB < n
 * intersectVal is 0 if listA and listB do not intersect.
 * intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.
 */
public class LL160IntersectionOfTwoLinkedLists {
	/**
	 * April 2022 Crib the answer
	 */
	public class TwoPointers {
		public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
			// Traverse the common length part, 这就是公有list的长度
			ListNode currA = headA;
			ListNode currB = headB;
			while (currA != currB) {
				currA = currA == null? headB : currA.next;
				currB = currB == null? headA : currB.next;
			}

			return currA;
		}
	}
	// April 2022
	public class HashSet {

	}

	public static class ReverseList {
		public static ListNode getIntersectionNodeReverseList(ListNode headA, ListNode headB) {
			//Build the reverse list of listB. rpB is at the end of the list B
			ListNode pointerB = headB;
			Stack<Integer> stackB = new Stack<>();
			while(pointerB != null) {
				stackB.push(pointerB.val);
			}
			ListNode reverseB = new ListNode(stackB.pop());
			ListNode rpB = reverseB;
			while (!stackB.isEmpty()) {
				int tmp = stackB.pop();
				ListNode tmpLL = new ListNode(tmp);
				rpB.next = tmpLL;
				rpB = tmpLL;
			}

			//Build the reverse list of listA. rpA is at the end of the list A
			ListNode pointerA = headA;
			Stack<Integer> stackA = new Stack<>();
			while(pointerB != null) {
				stackA.push(pointerB.val);
			}
			ListNode reverseA = new ListNode(stackA.pop());
			ListNode rpA = reverseA;
			while (!stackA.isEmpty()) {
				int tmp = stackA.pop();
				rpA.next = new ListNode(tmp);
				rpA = rpA.next;
			}

			//Find the intersection
			while(rpA.next == rpB.next) {
				rpA = rpA.next;
				if (rpA != rpB.next) return rpB;
				else rpB = rpB.next;
			}
			return null;
		}
	}

	/**
	 * 即便April 2022做这也是第一直觉
	 */
	static class Find_Common_By_Traverse {
		/**
		 * Runtime: 2ms 11/12/2016  Use: 60min
		 */
		public static ListNode getIntersectionNodeByLength(ListNode headA, ListNode headB) {
			ListNode pa = headA, pb = headB;
			if (pa == null || pb == null) return null;
			if (pa == pb) return pa; //redundant
			while(pa != null && pb!= null) {
				pa = pa.next;
				pb = pb.next;
			}
			ListNode pshort, plong;
			if (pa == null && pb == null) { //same length
				pshort = headA;
				plong = headB;
			} else if (pa == null) {//List A is shorter
				pa = headB;
				while(pb.next != null) {//Stop at the last element of ListB, instead of the null
					pb = pb.next;
					pa = pa.next;
				}
				plong = pa.next;
				pshort = headA;
			} else { //if (pb == null)
				pb = headA;
				while(pa.next != null) {
					pa = pa.next;
					pb = pb.next;
				}
				plong = pb.next;
				pshort = headB;
			}
			if (plong == pshort) return plong; //Don't forget this
			while(plong != pshort && plong.next != null) {
				if(plong.next == pshort.next) return plong.next;
				plong = plong.next;
				pshort = pshort.next;
			}
			return null;
		}
	}
	
	public static void main (String[] args) {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		ListNode d = new ListNode(4);
		ListNode e = new ListNode(5);
		a.next = null;
		b.next = c;
		c.next = d;
		d.next = e;
		e.next = null;
		ListNode x = Find_Common_By_Traverse.getIntersectionNodeByLength(e, b);
		while (x != null) {
			System.out.print(" " + x.val);
			x = x.next;
		}		
	}
}
