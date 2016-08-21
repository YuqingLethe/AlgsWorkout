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
