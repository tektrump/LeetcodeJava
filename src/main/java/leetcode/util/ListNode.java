package leetcode.util;

import java.util.HashSet;
import java.util.Set;

public class ListNode {
	public int val;
	public ListNode next;
	public ListNode(int val) {
		this.val = val;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("value=").append(val).append(",next=");
		if (next == null)
			builder.append("null");
		else
			builder.append(next.val);
		return builder.toString();
	}
	
	public static void print(ListNode head) {
		if (head == null)
			return;
		boolean first = true;
		ListNode node = head;
		while(node != null) {
			if (first) {
				System.out.print(node.val);
				first = false;
			} else {
				System.out.print(", " + node);
			}
			node = node.next;
		}
		System.out.println();
	}
	
	public static void printAndCatchLoop(ListNode head) {
		Set<ListNode> nodes = new HashSet<ListNode>();
		if (head == null)
			return;
		boolean first = true;
		ListNode node = head;
		while(node != null) {
			if (first) {
				System.out.print(node.val);
				first = false;
			} else {
				System.out.print(", " + node);
				if (nodes.contains(node))
					System.out.println("!LOOP!");
			}
			node = node.next;
		}
		System.out.println();
	}
	
	public void print() {
		System.out.println(serialize(this));
	}
	
	public static ListNode arrayToListNode(int[] array) {
		if (array == null || array.length == 0)
			return null;
		ListNode head = null;
		ListNode prev = null, node = null;
		for (int val : array) {
			node = new ListNode(val);
			if (head == null)
				head = node;
			else
				prev.next = node;
			prev = node;
		}
		
		return head;
	}
	
	
	public static String serialize(ListNode head) {
		StringBuilder b = new StringBuilder();
		while (head != null) {
			if (b.length() > 0)
				b.append(",");
			b.append(head.val);
			head = head.next;
		}
		return b.toString();
	}
	
	public static ListNode deserialize(String s) {
		s = s.trim();
		if (s.charAt(0) == '[')
			s = s.substring(1, s.length()-1);
		if (s.isEmpty())
			return null;
		String[] strs = s.split(",");
		ListNode head = null, prev = null, curr = null;
		for (int i = 0; i < strs.length; i++) {
			curr = new ListNode(Integer.valueOf(strs[i]));
			if (head == null)
				head = curr;
			if (prev != null)
				prev.next = curr;
			prev = curr;
		}
		
		return head;
	}
	
	public boolean equals(Object n) {
		if (!(n instanceof ListNode))
			return false;
		return ((ListNode)n).val == this.val;
	}
}
