package tektrup.leetcode.oj;


import tektrup.leetcode.util.ListNode;
import tektrup.leetcode.util.annotations.Leetcode;
import tektrup.leetcode.util.annotations.Leetcode.Difficulty;
import tektrup.leetcode.util.annotations.Leetcode.Tags;

@Leetcode(date="2016/08/17", diff=Difficulty.EASY, tags={Tags.LIST}, 
	url="https://leetcode.com/problems/remove-nth-node-from-end-of-list/")
public abstract class RemoveNthNodeFromEndOfList {
	public abstract ListNode removeNthFromEnd(ListNode head, int n);
	public static void main(String[] args) {
		RemoveNthNodeFromEndOfList instance = new SolutionI();
		ListNode head; int n;
		ListNode res;
		
		head = ListNode.deserialize("[1]"); n = 1;
		
		res = instance.removeNthFromEnd(head, n);
		System.out.println("result=" + res);
	}
	
	
	/**
	 * Solution II: Accepted
	 * using dummy node; in this case we always return dd.next regardless 
	 * if the new list becomes empty or has a new head.
	 * @author Alex
	 * @date Aug 17, 2016
	 */
	static class SolutionII extends RemoveNthNodeFromEndOfList {
		public ListNode removeNthFromEnd(ListNode head, int n) {
	        int len = 0;
	        ListNode node = head;
	        while (node != null) {
	            len++;
	            node = node.next;
	        }
	        int idx = 0;
	        ListNode dd = new ListNode(0), tail = dd;	// dummy for singly linked list
	        ListNode curr = head;
	        while (true) {
	            if (idx == len-n) {
	                tail.next = curr.next;  // skip removed node
	                break;
	            }
	            tail.next = curr;
	            tail = curr;
	            curr = curr.next;
	            idx++;
	        }
	        
	        return dd.next;
	    }
	}
	
	
	/**
	 * Solution I: Accepted
	 * Directly remove the node at idx=len-n without using dummy node.
	 * Note this requires special treatment of edge case where the removed node
	 * is the original head; we must update head to head.next.
	 * @author Alex
	 * @date Aug 17, 2016
	 */
	static class SolutionI extends RemoveNthNodeFromEndOfList {
		public ListNode removeNthFromEnd(ListNode head, int n) {
	        int len = 0;
	        ListNode node = head;
	        while (node != null) {
	            len++;
	            node = node.next;
	        }
	        int idx = 0;
	        ListNode prev = null, curr = head;
	        while (true) {
	            if (idx == len-n) {
	                if (prev == null) // removed head
	                    head = curr.next;
	                else
	                    prev.next = curr.next;
	                break;
	            }
	            prev = curr;
	            curr = curr.next;
	            idx++;
	        }
	        return head;
	    }
	}
}
