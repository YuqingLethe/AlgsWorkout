package happynumber;

import java.util.ArrayList;


public class LL234PalindromeLinkedList {
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}
	
	public static boolean isPalindromeMethodOne(ListNode head) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        ListNode index = head;
        while (index != null) {
        	al.add(index.val);
        	index = index.next;
        }
        if (al.get(0) != al.get(0) ) {
        	System.out.println(al.get(0));
        	System.out.println(al.get(4));
        	System.out.println(al.get(0).compareTo(al.get(4)));
        }
        for (int i = 0; i < al.size()/2; i++) {
        	if (al.get(i) != al.get(al.size() - i - 1)) {
        		System.out.println("al.get(i) = " + al.get(i));
        		System.out.println("al.get(al.size() - i - 1) = " + al.get(al.size() - i - 1));
        		return false;     
        	}
        	  		
        }
        return true;
    }
//	public static boolean isPalindromeMethodTwo(ListNode head) {
        
//    }
	public static void main (String[] args) {
		ListNode a = new ListNode(-16557);
		ListNode b = new ListNode(-4);
		ListNode c = new ListNode(-7);
		ListNode d = new ListNode(-7);
		ListNode e = new ListNode(-4);
		ListNode f = new ListNode(-16557);
		a.next = b;
		b.next = c;
		c.next = e;
		e.next = f;
		f.next = null;
		System.out.println(isPalindromeMethodOne(a));
		System.out.println(Integer.MAX_VALUE);
	}
}
