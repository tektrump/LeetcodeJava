package leetcode.oj;


import leetcode.util.ListNode;
import leetcode.util.annotations.Leetcode;
import leetcode.util.annotations.Leetcode.Difficulty;
import leetcode.util.annotations.Leetcode.Tags;

@Leetcode(date="2016-08-17", tags={Tags.LIST}, diff=Difficulty.EASY,
		url="https://leetcode.com/problems/merge-two-sorted-lists/")
public abstract class MergeTwoSortedLists {
	public abstract ListNode mergeTwoLists(ListNode l1, ListNode l2);
	public static void main(String[] args) {
		MergeTwoSortedLists instance = new SolutionII();
		ListNode l1, l2;
		ListNode res;
		
		// [1,2,3]
		l1 = ListNode.deserialize("[]");	// null
		l2 = ListNode.deserialize("[1,2,3]");
		
		// [1,2,2,2,3,3,3,4,5]
		l1 = ListNode.deserialize("[2,2,3,3]");	// null
		l2 = ListNode.deserialize("[1,2,3,4,5]");
		
		res = instance.mergeTwoLists(l1, l2);
		System.out.println("result=" + ListNode.serialize(res));
	}
	
	
	// Solution II: Accepted
	// more concise logic to select l1 or l2 than solution I;
	// again, leverage dummy node.
	static class SolutionII extends MergeTwoSortedLists {
		public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	        ListNode dd = new ListNode(0), n = dd;
	        while (l1 != null || l2 != null) {
	            // select l1
	            if (l2 == null || (l1 != null && l1.val <= l2.val)) {
	                ListNode next = l1.next;
	                n.next = l1;
	                n = l1;
	                n.next = null;
	                l1 = next;
	            } else { // select l2
	                ListNode next = l2.next;
	                n.next = l2;
	                n = l2;
	                n.next = null;
	                l2 = next;
	            }
	        }
	        
	        return dd.next;
	    }
	}
	
	
	// Solution I: Accepted
	// leverage dummy node
	static class SolutionI extends MergeTwoSortedLists {
		public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	        ListNode dd = new ListNode(0), n = dd;
	        while (l1 != null || l2 != null) {
	            if (l1 == null) {
	                n.next = l2;
	                n = l2;
	                l2 = l2.next;
	            } else if (l2 == null) {
	                n.next = l1;
	                n = l1;
	                l1 = l1.next;
	            } else {
	                if (l1.val < l2.val) {
	                    n.next = l1;
	                    n = l1;
	                    l1 = l1.next;
	                } else {
	                    n.next = l2;
	                    n = l2;
	                    l2 = l2.next;
	                }
	            }
	        }
	        return dd.next;
	    }
	}
}
