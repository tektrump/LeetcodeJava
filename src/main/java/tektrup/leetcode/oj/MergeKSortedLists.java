package tektrup.leetcode.oj;


import java.util.Comparator;
import java.util.PriorityQueue;

import tektrup.leetcode.util.ListNode;
import tektrup.leetcode.util.annotations.Leetcode;
import tektrup.leetcode.util.annotations.Leetcode.Difficulty;
import tektrup.leetcode.util.annotations.Leetcode.Tags;

@Leetcode(date="2016-08-17", diff=Difficulty.EASY, tags={Tags.LIST},
		url="https://leetcode.com/problems/merge-k-sorted-lists/")
public abstract class MergeKSortedLists {
	public abstract ListNode mergeKLists(ListNode[] lists);
	public static void main(String[] args) {
		MergeKSortedLists instance = new SolutionI();
		ListNode[] lists;
		ListNode res;
		
		// [1,1,1,2,3,3,3,4]
		lists = new ListNode[]{
				ListNode.deserialize("[]"), // null list
				ListNode.deserialize("[1,1]"),
				ListNode.deserialize("[3,3]"),
				ListNode.deserialize("[1,2,3,4]"),
		};
		
		res = instance.mergeKLists(lists);
		System.out.println("result=" + ListNode.serialize(res));
	}

	
	static class SolutionI extends MergeKSortedLists {
		public ListNode mergeKLists(ListNode[] lists) {
	        Comparator<ListNode> comp = new Comparator<ListNode>() {
	            @Override
	            public int compare(ListNode n1, ListNode n2) {
	                return n1.val - n2.val;
	            }
	        };
	        
	        PriorityQueue<ListNode> q = new PriorityQueue<>(comp);
	        for (ListNode head : lists) {
	            if (head != null) // ERROR: check if a list is null
	                q.add(head);
	        }
	        ListNode dd = new ListNode(0), n = dd;
	        while (!q.isEmpty()) {
	            ListNode curr = q.poll();
	            n.next = curr;
	            n = curr;
	            if (curr.next != null)
	                q.add(curr.next);
	        }
	        
	        return dd.next;
	    }
	}
}
