package LinkedList;

public class LL160IntersectionOfTwoLinkedLists {
	public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode currA = headA;
        ListNode currB = headB;
        while (currA != null) {
        	while (currB != null) {
        		if (currA == currB)       	
        			return currA;
        		currB = currB.next;
        	}
        	currA = currA.next;
        }
        return headA;
    }
	
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}
	
	public static void main (String[] args) {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		ListNode d = new ListNode(4);
		ListNode e = new ListNode(5);
		a.next = c;
		b.next = c;
		c.next = d;
		d.next = e;
		e.next = null;
		ListNode x = getIntersectionNode(a, b);
		if (a.next == b.next) {
			System.out.println("They same");
		}
		while (x != null) {
			System.out.println(x.val);
			x = x.next;
		}		
	}
}
