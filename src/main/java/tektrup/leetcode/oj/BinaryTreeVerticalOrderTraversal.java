package tektrup.leetcode.oj;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import tektrup.leetcode.util.TreeNode;

public abstract class BinaryTreeVerticalOrderTraversal {
	public abstract List<List<Integer>> verticalOrder(TreeNode root);
	public static void main(String[] args) {
		BinaryTreeVerticalOrderTraversal instance = new SolutionII();
		TreeNode root;
		List<List<Integer>> rets;
		
		root = TreeNode.deserialize("[3,9,20,null,null,15,7]");
		
		rets = instance.verticalOrder(root);
		System.out.println("results=" + rets);
	}
	
	static class SolutionII extends BinaryTreeVerticalOrderTraversal {
		public List<List<Integer>> verticalOrder(TreeNode root) {
	        if (root == null)
	            return Collections.emptyList();
	        int minCol = Integer.MAX_VALUE;
	        Map<Integer, List<Integer>> colVals = new HashMap<>();
	        int col = 0;
	        Stack<TreeNode> stack = new Stack<>();
	        while (root != null || !stack.isEmpty()) {
	            while (root != null) {
	                stack.push(root);
	                root = root.left;
	                if (root != null)
	                    col--;
	                minCol = Math.min(minCol, col);
	            }
	            while (!stack.isEmpty()) {
	                TreeNode pop = stack.pop();
	                List<Integer> vals = colVals.get(col);
	                if (vals == null) {
	                    vals = new LinkedList<>();
	                    colVals.put(col, vals);
	                }
	                vals.add(pop.val);
	                col++;
	                if (pop.right != null) {
	                    root = pop.right;
	                    break;
	                }
	            }
	        }
	        
	        List<List<Integer>> rets = new LinkedList<>();
	        col = minCol;
System.out.println(colVals);
	        while (!colVals.isEmpty()) {
	            List<Integer> vals = colVals.remove(col++);
	            if (vals != null)
	                rets.add(vals);
	        }
	        
	        return rets;
	    }
	}
}
