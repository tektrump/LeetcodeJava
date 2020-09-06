package tektrup.leetcode.oj;


import tektrup.leetcode.util.ListNode;

public abstract class SortList {
	public abstract ListNode sortList(ListNode head);
	public static void main(String[] args) {
		SortList instance = new SolutionI();
		ListNode head;
		ListNode res;
		
//		head = ListNode.deserialize("[2,1]");
		
		head = ListNode.deserialize("[3,2,4]");
		
		res = instance.sortList(head);
		System.out.println("result=" + ListNode.serialize(res));
	}
	
	
	static class SolutionI extends SortList {
		public ListNode sortList(ListNode head) {
System.out.println(">>> input=" + ListNode.serialize(head));
	        if (head == null || head.next == null)
	            return head;
	        ListNode slow = head, fast = head.next;
	        while (fast != null) {
	            if (fast.next == null)
	                break;
	            fast = fast.next.next;
	            slow = slow.next;
	        }
	        ListNode head1 = slow.next;
	        slow.next = null;
	        sortList(head);
	        sortList(head1);
	        ListNode dd = new ListNode(0), n = dd;
System.out.println("head=" + ListNode.serialize(head));
System.out.println("head1=" + ListNode.serialize(head1));
	        while (head != null && head1 != null) {
	            if (head.val < head1.val) {
	                n.next = head;
	                n = head;
	                head = head.next;
	            } else {
	                n.next = head1;
	                n = head1;
	                head1 = head1.next;
	            }
	        }
	        if (head != null)
	            n.next = head;
	        else if (head1 != null)
	            n.next = head1;
System.out.println("merged=" + dd.next);
	        return dd.next;
	    }
	}
}
