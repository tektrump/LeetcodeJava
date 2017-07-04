package leetcode.oj;


import leetcode.util.ListNode;
import leetcode.util.TreeNode;

public class ConvertSortedListToBinarySearchTree {

	public TreeNode sortedListToBST(ListNode head) {
        return recur(head, null);
    }
    
    private TreeNode recur(ListNode head, ListNode tail) {
        ListNode slow = head, fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        TreeNode root = new TreeNode(slow.val);
        root.left = recur(head, slow);
        root.right = recur(slow.next, tail);
        return root;
    }
    
    public static void main(String[] args) {
    	ConvertSortedListToBinarySearchTree instance = new ConvertSortedListToBinarySearchTree();
    	instance.sortedListToBST(null);
	}
	
}
