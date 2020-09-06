package tektrup.leetcode.oj;


import tektrup.leetcode.util.TreeNode;

public abstract class FlatternBinaryTreeToLinkedList {
	public abstract void flatten(TreeNode root);
	public static void main(String[] args) {
		FlatternBinaryTreeToLinkedList instance = new SolutionI();
		TreeNode root;
		
		root = TreeNode.deserialize("1,2,5,3,4,#,6");
		instance.flatten(root);
		System.out.println("result=" + TreeNode.serialize(root));
	}
	
	static class SolutionI extends FlatternBinaryTreeToLinkedList {
		public void flatten(TreeNode root) {
	        help(root);
	    }
	    
	    private void help(TreeNode node) {
	        // termination
	        if (node == null || (node.left == null && node.right == null))
	            return;
	        TreeNode left = node.left, right = node.right;
	        if (left != null) {
	        	node.left = null;
	            node.right = left;
	            rightmost(left).right = right;
	        }
	        help(node.right);
	    }
	    
	    private TreeNode rightmost(TreeNode root) {
	        while (root.right != null)
	            root = root.right;
	        return root;
	    }
	}
}
