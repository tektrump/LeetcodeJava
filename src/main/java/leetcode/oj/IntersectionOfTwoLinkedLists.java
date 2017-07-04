package leetcode.oj;


import leetcode.util.ListNode;

public abstract class IntersectionOfTwoLinkedLists {
	public abstract ListNode getIntersectionNode(ListNode headA, ListNode headB);
	public static void main(String[] args) {
		IntersectionOfTwoLinkedLists instance = new SolutionIII();
		ListNode headA, headB;
		ListNode ret;
		
		headA = ListNode.deserialize("3");
		headB = ListNode.deserialize("2,3");
		
		ret = instance.getIntersectionNode(headA, headB);
		System.out.println("result=" + ret);
	}
	
	
	static class SolutionIII extends IntersectionOfTwoLinkedLists {
		public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	        if (headA == null || headB == null)
	            return null;
	        ListNode result = null;
	        ListNode tail = null;
	        ListNode nodeA = headA;
	        while (nodeA != null) {
	            tail = nodeA;
	            nodeA = nodeA.next;
	        }
	        tail.next = headB;
	        ListNode slow = headA, fast = headA;
	        while (fast != null) {
	            if ((fast = fast.next) == null)
	                break;
	            fast = fast.next;
	            slow = slow.next;
	            if (fast.equals(slow))
	                break;
	        }
	        if (fast != null) {
	            fast = headA;
	            while (!slow.equals(fast)) {
	                slow = slow.next;
	                fast = fast.next;
	            }
	            result = slow;
	        }
	        tail.next = null;
	        return result;
	    }
	}
}
