package tektrup.leetcode.oj;

import tektrup.leetcode.util.ListNode;

public abstract class ReverseLinkedListII {
	public abstract ListNode reverseBetween(ListNode head, int m, int n);
	public static void main(String[] args) {
		ReverseLinkedListII instance = new SolutionI();
		ListNode head; int m, n;
		ListNode res;
		
		head = ListNode.deserialize("[3,5]");
		m = 1; n = 2;
		
		res = instance.reverseBetween(head, m, n);
		System.out.println("result=" + ListNode.serialize(res));
	}
	
	static class SolutionI extends ReverseLinkedListII {
		public ListNode reverseBetween(ListNode head, int m, int n) {
	        ListNode dd = new ListNode(0), prev = dd, next = null, first = null, last = null;
	        dd.next = head;
	        int idx = 1;
	        ListNode curr = head;
	        while (curr != null) {
	            if (idx == m-1)
	                prev = curr;
	            else if (idx == n+1) {
	                next = curr;
	                break;
	            }
	            if (idx == m)
	                first = curr;
	            if (idx == n)
	                last = curr;
	            curr = curr.next;
	            idx++;
	        }
	        
	        // extract [m, n] section
	        prev.next = null;
	        last.next = null;
	        // reverse [m, n] section
	        reverse(first);
	        // link up with left & right subs
	        prev.next = last;
	        first.next = next;
	        
	        return dd.next;
	    }
	    
	    private void reverse(ListNode head) {
	        ListNode dd = new ListNode(0);
	        ListNode curr = head;
	        while (curr != null) {
	            ListNode next = curr.next;
	            curr.next = dd.next;
	            dd.next = curr;
	            curr = next;
	        }
	        dd.next = null; // clean up
	    }
	}
}
