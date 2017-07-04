package leetcode.oj;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import leetcode.util.TreeNode;

public abstract class BinaryTreeInorderTraversal {
	public abstract List<Integer> inorderTraversal(TreeNode root);
	
	public static void main(String[] args) {
		BinaryTreeInorderTraversal instance = new Solution();
		TreeNode root;
		List<Integer> results;
		
		root = new TreeNode(2);
		root.left = new TreeNode(1);
		root.right = new TreeNode(3);
		results = instance.inorderTraversal(root);
		System.out.println("results=" + results);
	}
	
	static class Solution extends BinaryTreeInorderTraversal {
		public List<Integer> inorderTraversal(TreeNode root) {
	        List<Integer> results = new ArrayList<>();
	        TreeNode node = root;
	        Stack<TreeNode> stack = new Stack<>();
	        while (node != null || !stack.isEmpty()) {
	            // left, root, right
	            while (node != null) {
	                stack.push(node);
	                node = node.left;
	            }
	            while (!stack.isEmpty()) {
	                node = stack.pop();
	                results.add(node.val);
	                // ERROR: we must guarantee if node.right is null,
	                // node is also null. otherwise, we may exist this while loop
	                // but with node != null (since we assigned node=stack.pop()),
	                // this will lead to infinity loop
	                if ((node = node.right) != null)
	                    break;
	            }
	        }
	        return results;
	    }
	}

	
	// Solution A: slight diff from soltion
	static class SolutionI extends BinaryTreeInorderTraversal {
		public List<Integer> inorderTraversal(TreeNode root) {
	        List<Integer> results = new ArrayList<>();
	        TreeNode node = root;
	        Stack<TreeNode> stack = new Stack<>();
	        while (node != null || !stack.isEmpty()) {
	            // left, root, right
	            while (node != null) {
	                stack.push(node);
	                node = node.left;
	            }
	            while (!stack.isEmpty()) {
	                node = stack.pop();
	                results.add(node.val);
	                if (node.right == null)
	                	node = null;
	                else {
	                	node = node.right;
	                	break;
	                }
	            }
	        }
	        return results;
	    }
	}

}
