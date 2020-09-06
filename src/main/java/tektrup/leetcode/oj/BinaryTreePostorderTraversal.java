package tektrup.leetcode.oj;


import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import tektrup.leetcode.util.TreeNode;
import tektrup.leetcode.util.annotations.Leetcode;
import tektrup.leetcode.util.annotations.Leetcode.Tags;

@Leetcode(date="2016-06-01", tags={Tags.TREE, Tags.POSTORDER}, 
	url="https://leetcode.com/problems/binary-tree-postorder-traversal/")
public abstract class BinaryTreePostorderTraversal {
	public abstract List<Integer> postorderTraversal(TreeNode root);
	public static void main(String[] args) {
		BinaryTreePostorderTraversal instance = new Solution();
		TreeNode root;
		List<Integer> results;
		
		root = TreeNode.deserialize("[3,null,1,2]");
		results = instance.postorderTraversal(root);
		System.out.println("results=" + results);
	}
	
	// Solution: Best
	// Iteration (without using flags)
	public static class Solution extends BinaryTreePostorderTraversal {
		public List<Integer> postorderTraversal(TreeNode root) {
	        Stack<TreeNode> stack = new Stack<>();
	        TreeNode node = root;
	        List<Integer> results = new LinkedList<>();
	        while (node != null || !stack.isEmpty()) {
	            while (node != null) {
	                results.add(0, node.val);
	                stack.push(node);
	                node = node.right;
	            } // --> 1: at this moment, node is null
	            
	            // ERROR: we must make sure if the popped node has no left child,
	            // node must be null.
	            while (!stack.isEmpty() && (node = stack.pop().left) == null);
	            // --> 1: so don't assign a new value to node until we encounter a left child
	        }
	        
	        return results;
	    }
	}
	
	// Solution I: Logic Error
	static class SolutionI extends BinaryTreePostorderTraversal {
		public List<Integer> postorderTraversal(TreeNode root) {
	        Stack<TreeNode> stack = new Stack<>();
	        TreeNode node = root;
	        List<Integer> results = new LinkedList<>();
	        while (node != null || !stack.isEmpty()) {
	            while (node != null) {
	                results.add(0, node.val);
	                stack.push(node);
	                node = node.right;
	            }
	            
	            while (!stack.isEmpty()) {
	                node = stack.pop(); // ERROR: input=[1], we'll finish while loop by node is not null
	                                    // thus lead to infinite loop
	                if (node.left != null) {
	                    node = node.left;
	                    break;
	                }
	            }
	        }
	        
	        return results;
	    }
	}
	
}
