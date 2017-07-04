package leetcode.oj;


import leetcode.util.ArrayUtil;
import leetcode.util.ListNode;

public abstract class ReverseNodesInKGroup {
	public abstract ListNode reverseKGroup(ListNode head, int k);
	public static void main(String[] args) {
		ReverseNodesInKGroup instance = new SolutionII();
		ListNode head, result; int k;
		
		head = ListNode.arrayToListNode(ArrayUtil.str2intArray("[1,2,3,4]"));
		k = 2;
		result = instance.reverseKGroup(head, k);
		result.print();
		
	}
	
	static class SolutionII extends ReverseNodesInKGroup {
		public ListNode reverseKGroup(ListNode head, int k) {
	        if (k <= 1)
	            return head;
	        ListNode dd = new ListNode(0), n = dd;
	        ListNode curr = head;
	        int i = 0;
	        while (curr != null) {
	            ListNode next = curr.next;
	            ListNode dd1 = new ListNode(0), n1 = dd1;
	            while (curr != null && i < k) {
	                next = curr.next;
	                curr.next = null;
	                n1.next = curr;
	                n1 = curr;
	                i++;
	                curr = next;
	            }
dd1.next.print();	            
	            if (i == k) {
	                ListNode dd2 = new ListNode(0);
	                reverse(dd1, dd2);
	                n.next = dd2.next;
	                n = dd1.next;
	                i = 0;
	            } else {
	                n.next = dd1.next;
	                break;
	            }
	        }
	        return dd.next;
	    }
	    
	    private void reverse(ListNode dd1, ListNode dd2) {
	        ListNode curr = dd1.next;
	        while (curr != null) {
	            ListNode next = curr.next;
	            curr.next = dd2.next;
	            dd2.next = curr;
	            curr = next;
	        }
	    }
	}
	
	// Solution I: Accepted
	static class SolutionI extends ReverseNodesInKGroup {
		public ListNode reverseKGroup(ListNode head, int k) {
	        if (k <= 1)
	            return head;
	        ListNode dd = new ListNode(0), n = dd;
	        ListNode dd1 = new ListNode(0), n1 = dd1;
	        ListNode dd2 = new ListNode(0);
	        ListNode curr = head;
	        int i = 0;
	        while (curr != null) {
	            // first, move node to dd1, in original order
	            // extract
	            ListNode next = curr.next;
	            curr.next = null;
	            // append
	            n1.next = curr;
	            n1 = curr;
	            
	            if (++i == k) {
	                // reverse sublist on dd1, and append reversed nodes to dd2
	                reverse(dd1, dd2);
	                n.next = dd2.next;
	                n = dd1.next; // dd1.next points to the tail of reversed sublist
	                // reset subset dd1
	                i = 0;
	                dd1.next = null;
	                n1 = dd1;
	                dd2.next = null;
	            }
	            curr = next;
	        }
	        // CATCH: if we have sublist on dd1 but not enough for reverse yet
	        if (i != 0)
	            n.next = dd1.next;
	        return dd.next;
	    }
	    
	    private void reverse(ListNode dd1, ListNode dd2) {
	        ListNode curr = dd1.next;
	        while (curr != null) {
	            ListNode next = curr.next;
	            curr.next = dd2.next;    // extract & append
	            dd2.next = curr;
	            curr = next;
	        }
	    }
	}
}
