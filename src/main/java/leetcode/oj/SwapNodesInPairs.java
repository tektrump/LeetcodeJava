package leetcode.oj;


import leetcode.util.ListNode;
import leetcode.util.annotations.Leetcode;
import leetcode.util.annotations.Leetcode.Difficulty;
import leetcode.util.annotations.Leetcode.Tags;

@Leetcode(date="2016-06-09", diff=Difficulty.EASY, tags={Tags.LIST}, 
	url="https://leetcode.com/problems/swap-nodes-in-pairs/")
public abstract class SwapNodesInPairs {
	public abstract ListNode swapPairs(ListNode head);
	public static void main(String[] args) {
		SwapNodesInPairs instance = new SolutionV();
		ListNode head;
		
		head = ListNode.arrayToListNode(new int[]{1, 2, 3, 4});
		head = instance.swapPairs(head);
		System.out.print("result=");
		head.print();
	}
	
	// Solution V: Accepted
	// no dummy node, recursive solution
	static class SolutionV extends SwapNodesInPairs {
		public ListNode swapPairs(ListNode head) {
	        if (head == null || head.next == null)
	            return head;
	        ListNode next = head.next, next2 = next.next;
	        next.next = head;
	        head.next = swapPairs(next2);
	        return next;
	    }
	}
	
	
	// Solution IV: Accepted
    // use 1 dummy. pick 2 nodes at a time; append odd then even;
    // then continue with the next 2.
	static class SolutionIV extends SwapNodesInPairs {
		public ListNode swapPairs(ListNode head) {
	        ListNode dd = new ListNode(0), n = dd;
	        ListNode even = head, odd = null;
	        while (even != null) {
	            odd = even.next;
	            if (odd == null) {
	                n.next = even;
	                break;
	            }
	            ListNode next = odd.next;
	            n.next = odd;
	            n = odd;
	            n.next = even;
	            n = even;
	            n.next = null;
	            even = next;
	        }
	        
	        return dd.next;
	    }
	}
	
		
	// Solution III: Accepted
	// put even & odd nodes into 2 lists;
	// then merge 2 lists into 1, but picking odd first then even.
	// this example uses the very standard steps (some of them can be simplified) 
	// for dummy-based list operation:
	// 1. move node from 1 list to another.
	// 2. merge 2 lists into 1.
	static class SolutionIII extends SwapNodesInPairs {
		public ListNode swapPairs(ListNode head) {
	        ListNode dd1 = new ListNode(0), n1 = dd1;
	        ListNode dd2 = new ListNode(0), n2 = dd2;
	        int i = 0;
	        ListNode node = head;
	        while (node != null) {
	            ListNode next = node.next;
	            if (i%2 == 0) {
	                n1.next = node;
	                n1 = node;
	            } else {
	                n2.next = node;
	                n2 = node;
	            }
	            node.next = null; // extract
	            node = next;
	            i++;
	        }
	        ListNode dd = new ListNode(0), n = dd;
	        n1 = dd1.next;
	        n2 = dd2.next;
	        while (n1 != null || n2 != null) {
	            if (n2 != null) {
	                n.next = n2;
	                n = n2;
	                n2 = n2.next;
	            }
	            if (n1 != null) {
	                n.next = n1;
	                n = n1;
	                n1 = n1.next;
	            }
	        }
	        n.next = null;
	        return dd.next;
	    }
	}
	
	
	// Solution II: Accepted
	// maneuver without using dummy nodes; easy to make mistake with edge cases.
	static class SolutionII extends SwapNodesInPairs {
		public ListNode swapPairs(ListNode head) {
	        ListNode result = null;
	        ListNode prevTail = null;
	        ListNode curr = head;
	        while (curr != null) {
	            ListNode next = curr.next;
	            if (next == null) {
	                if (result == null)
	                    return curr;
	                else
	                    return result;
	            } else {
	                if (result == null) {
	                    result = next;
	                }
	            }
	            ListNode nextNext = next.next;
	            // reverse
	            if (prevTail != null) {
	                prevTail.next = next;
	            }
	            next.next = curr;
	            curr.next = nextNext;
	            prevTail = curr;
	            curr = nextNext;
	        }
	        return result;
	    }
	}
}
