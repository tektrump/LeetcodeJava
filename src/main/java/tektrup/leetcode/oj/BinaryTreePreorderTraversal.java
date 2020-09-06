package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import tektrup.leetcode.util.TreeNode;

public abstract class BinaryTreePreorderTraversal {
	
	public abstract List<Integer> preorderTraversal(TreeNode root);
	
	public static void main(String[] args) {
		BinaryTreePreorderTraversal instance = new Solution();
		List<Integer> results;
		TreeNode root;
		
		root = TreeNode.deserialize("1,#,2,3"); // 1,2,3
		results = instance.preorderTraversal(root);
		System.out.println("results=" + results);
	}
	
	
	// Solution III: Best
	// a created iteration without using flags or modifying tree structure.
	public static class Solution extends BinaryTreePreorderTraversal {
		public List<Integer> preorderTraversal(TreeNode root) {
			List<Integer> results = new ArrayList<>();
			TreeNode node = root;
			Stack<TreeNode> nodes = new Stack<>();
			while (node != null || !nodes.isEmpty()) {
				// visit this and left; keep adding nodes to stack.
				while (node != null) {
					results.add(node.val);
					nodes.push(node);
					node = node.left;
				}
				
				// look for a node with right child; once found, 
				// will add right to stack next iteration.
				while (!nodes.isEmpty() && (node = nodes.pop().right) == null);
			}
			return results;
		}
	}
	
	
	// Solution II: Accepted 
	// convert recursion to iteration with flags.
	public static class SolutionII extends BinaryTreePreorderTraversal {
		public List<Integer> preorderTraversal(TreeNode root) {
			if (root == null)
				return Collections.emptyList();
			List<Integer> results = new ArrayList<>();
			Stack<TreeNode> nodes = new Stack<>();
			nodes.push(root);
			Stack<Integer> flags = new Stack<>();
			flags.push(0);
			while (!nodes.isEmpty()) {
				TreeNode node = nodes.peek();
				Integer flag = flags.pop();
				if (flag == 0) {
					results.add(node.val);
					flags.push(1);
				} else if (flag == 1) {
					flags.push(2);
					if (node.left != null) {
						nodes.push(node.left);
						flags.push(0);
					}
				} else {
					nodes.pop();
					if (node.right != null) {
						nodes.push(node.right);
						flags.push(0);
					}
				}
			}
			return results;
		}
	}
	

	// Solution I: Accepted
	// trivial recursion solution.
	public static class SolutionI extends BinaryTreePreorderTraversal {
	    public List<Integer> preorderTraversal(TreeNode root) {
	        List<Integer> results = new ArrayList<>();
	        recur(root, results);
	        return results;
	    }
	    
	    private void recur(TreeNode node, List<Integer> results) {
	        if (node != null) {
	            results.add(node.val);
	            recur(node.left, results);
	            recur(node.right, results);
	        }
	    }
	}
	
}


