package tektrup.leetcode.oj;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import tektrup.leetcode.util.TreeNode;

public abstract class PathSumII {
	public abstract List<List<Integer>> pathSum(TreeNode root, int sum);
	public static void main(String[] args) {
		PathSumII instance = new SolutionII();
		TreeNode root; int sum;
		List<List<Integer>> results;
		
		root = TreeNode.deserialize("[5,4,8,11,null,13,4,7,2,null,null,5,1]");
		sum = 22;
		
		results = instance.pathSum(root, sum);
		System.out.println("results=" + results);
	}
	
	
	static class SolutionII extends PathSumII {
		public List<List<Integer>> pathSum(TreeNode root, int sum) {
	        if (root == null)
	            return Collections.emptyList();
//	        List<TreeNode> list = Arrays.asList(root);
	        List<TreeNode> list = new LinkedList<>();
	        list.add(root);
	        List<List<Integer>> results = new LinkedList<>();
	        bt(list, root.val, sum, results);
	        return results;
	    }
	    
	    private void bt(List<TreeNode> list, int val, int sum, List<List<Integer>> rets) {
	        // termination: leaf
	        TreeNode last = list.get(list.size()-1);
System.out.println("last=" + last);
	        if (last.left == null && last.right == null) {
	            if (val == sum) {
	                List<Integer> ret = new LinkedList<>();
	                for (TreeNode node : list)
	                    ret.add(node.val);
	                rets.add(ret);
	            }
	        } else {
	            if (last.left != null) {
	                list.add(last.left); // modify
	                bt(list, val+last.left.val, sum, rets);
	                list.remove(list.size()-1); // restore
	            }
	            if (last.right != null) {
	                list.add(last.right); // modify
	                bt(list, val+last.right.val, sum, rets);
	                list.remove(list.size()-1); // restore
	            }
	        }
	    }
	}
}
