package LinkedList;

import java.util.ArrayList;
import java.util.Stack;

public class LL160IntersectionOfTwoLinkedLists {

	/**
	 * 11/12/2016
	 * Dummy method, time exceed
	 */
	public static ListNode getIntersectionNodeTwoLoops(ListNode headA, ListNode headB) {
		ListNode ai = headA; //pointers of list A
		while (ai != null) {
			ListNode bi = headB; //pointer of list B
			while(bi != null) {
				if (ai == bi) return ai;
				bi = bi.next;
			}
			ai = ai.next;
		}
		return null;
	}

	/**
	 * After built the reversed lists, cannot return origial nodes
	 */
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

	/**
	 * TODO: optimize above one
	 * https://discuss.leetcode.com/topic/33291/recommend-for-beginners-clean-c-implementation-with-detailed-explaination
	 */
	public static ListNode getIntersectionNodeByLength2(ListNode headA, ListNode headB) {
		return null;
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
		ListNode x = getIntersectionNodeByLength(e, b);
		while (x != null) {
			System.out.print(" " + x.val);
			x = x.next;
		}		
	}
}
