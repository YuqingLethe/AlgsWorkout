package LinkedList;

public class LL21MergeTwoSortedLists {
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}
	
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {		
		if (l1 == null || l2 == null) {
			if (l1 == null && l2 != null)
				return l2;
			if (l1 == null && l2 == null)
				return null;
			if (l1 != null && l2 == null)
				return l1;
		}
		ListNode head = l1; //create the head of the result
		//Head ListNode
		if (l1.val > l2.val) {
			head = l2;
			l2 = l2.next;
		} else {
			l1 = l1.next;
		}
		//Merge until one is done
		ListNode index = head; //the pointer of insertion
        while (l1 != null && l2 != null) {
        	ListNode temp; //temporary ListNode
        	if (l1.val > l2.val) {
        		temp = l2;
        		index.next = l2;
        		l2 = temp.next;
        	} else {
        		temp = l1;
        		index.next = l1;
        		l1 = temp.next;
        	}
        	index = index.next;
        }
        //insert the rest LinkedList
        if (l2 != null) {
        	index.next = l2;
        }
        if (l1 != null) {
        	index.next = l1;
        }
        return head;
    }
	public ListNode Jan2022(ListNode list1, ListNode list2) {
		ListNode dummy = new ListNode(0);
		ListNode p1 = list1;
		ListNode p2 = list2;
		ListNode p3 = dummy;
		while (p1 != null || p2 != null) {
			if (p1 == null && p2 != null) {
				p3.next = p2;
				p2 = p2.next;
			} else if (p2 == null && p1 != null) {
				p3.next = p1;
				p1 = p1.next;
			} else if (p1.val <= p2.val) {
				p3.next = p1;
				p1 = p1.next;
			} else {
				p3.next = p2;
				p2 = p2.next;
			}
			p3 = p3.next;
		}
		return dummy.next;
	}
	
	public static void main (String[] args) {
		ListNode a = new ListNode(3);
		ListNode b = new ListNode(4);
		ListNode c = new ListNode(7);
		ListNode d = new ListNode(4);
		ListNode e = new ListNode(5);
		ListNode f = new ListNode(6);
		a.next = b;
		b.next = c;
		c.next = null;
		d.next = e;
		e.next = f;
		f.next = null;
		
		ListNode x = mergeTwoLists(c, f);
		while (x != null) {
			System.out.println(x.val);
			x = x.next;
		}
		
	}
}
