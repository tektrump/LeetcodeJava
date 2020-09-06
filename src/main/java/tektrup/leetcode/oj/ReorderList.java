package tektrup.leetcode.oj;


import tektrup.leetcode.util.ArrayUtil;
import tektrup.leetcode.util.ListNode;
import tektrup.leetcode.util.annotations.Leetcode;
import tektrup.leetcode.util.annotations.Leetcode.Tags;

/**
 * This problem is an excellent example for Singly Linked List.
 * It demonstrates several routine operations:
 * list cut, reverse, merge.
 * These operations can be done using dummy nodes (recommended) or not.
 * @author Alex
 * @date May 27, 2016
 */
@Leetcode(date = "2016-05-26", tags={Tags.LIST}, url="https://leetcode.com/problems/reorder-list/")
public class ReorderList {
	
	// Solution II: Accepted
    // try alternatives avoid using dummy nodes.
    public void reorderList1(ListNode head) {
        // CASE: break list into 2 using slow/ fast pointers
        if (head == null || head.next == null) // special case: simplify all subsequent operations.
            return;
        ListNode slow = head, fast = head.next; // head & head.next is guaranteed not equal to null.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode head2 = slow.next;
        slow.next = null; // extract left sublist.
        
        // CASE: reverse second sublist without dummy.
        ListNode curr = head2; // head2 is not null, as we handle special cases.
        ListNode prev = null;  // NOTE: previous head, initialized to an imaginary head null, useful trick.
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            head2 = curr;
            prev = head2;
            curr = next;
            // NOTE: no need to set head.next; it should be set already at last iteration,
            // and the fist head is an imaginary head 'null'.
        }
        
        // merge 2 sublists without dummy: insert node2 after each node on left sublist.
        ListNode node = head;
        ListNode node2 = head2;
        while (node2 != null) { // <-- we know size of left sublist >= right sublist.
            ListNode next = node.next;
            ListNode next2 = node2.next;
            node2.next = next;
            node.next = node2;
            node = next;
            node2 = next2;
        }
    }
	
	// Solution I: Best
	// calculate total number of nodes.
    // divide into 2 sublists:
    // move first half under list1, second half under list2 and reversed.
    // in the end, merge 2 lists, and handle special cases where sublist could be empty.
    public void reorderList(ListNode head) {
        int size = 0;
        ListNode node = head;
        while (node != null) {
            size++;
            node = node.next;
        }
        ListNode dd1 = new ListNode(0), dd2 = new ListNode(0);
        ListNode n1 = dd1, n2 = dd2;
        node = head;
        // add first half to first sublist
        for (int i = 0; i < size/2; i++) { 	// <-- 1: here, if list has odd size, the mid will be included in 1st sublist;
        									//	it actually doesn't matter if the mid is added to 1st or 2nd sublist.
            n1.next = node;
            n1 = node;
            ListNode next = node.next;
            node.next = null;   // reset
            node = next;
        } 									// <-- 2: alternatively, we can also use fast/ slow pointer to identify mid point.
        									// but this approach using dummy nodes is the most intuitive and easy to understand approach.
        // add rest to second sublist, in reverse order
        while (node != null) {
            ListNode next = node.next;
            node.next = dd2.next;
            dd2.next = node;
            node = next;
        }
        // merge 2 sublists
        ListNode dd3 = new ListNode(0);
        ListNode n3 = dd3;
        n1 = dd1.next;
        n2 = dd2.next;
        while (n1 != null || n2 != null) {
            if (n1 != null) {
                n3.next = n1;
                n3 = n1;
                ListNode next = n1.next;
                n1.next = null; // reset
                n1 = next;
            }
            if (n2 != null) {
                n3.next = n2;
                n3 = n2;
                ListNode next = n2.next;
                n2.next = null;
                n2 = next;
            }
        }
        // head = dd3.next;  // <-- 3: ERROR: whatever assigned to head, no effect outside the func!
                                // actually, the original head is also the head of the result list. this is in-place change.
    }

	public static void main(String[] args) {
		ReorderList instance = new ReorderList();
		ListNode head;
		
		head = ListNode.arrayToListNode(ArrayUtil.str2intArray("[1,2,3,4]"));
		instance.reorderList(head);
		head.print();
		
		head = ListNode.arrayToListNode(ArrayUtil.str2intArray("[1,2,3]"));
		instance.reorderList(head);
		head.print();
	}
}
