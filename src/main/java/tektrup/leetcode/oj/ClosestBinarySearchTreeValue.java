package tektrup.leetcode.oj;


import tektrup.leetcode.util.TreeNode;

public abstract class ClosestBinarySearchTreeValue {
	public abstract int closestValue(TreeNode root, double target);
	public static void main(String[] args) {
		ClosestBinarySearchTreeValue instance = new SolutionIII();
		TreeNode root; double target;
		int result;
		
//		root = TreeNode.deserialize("2147483647"); target = 0.0;
		
		root = TreeNode.deserialize("[41,37,44,24,39,42,48,1,35,38,40,null,43,46,49,0,2,30,36,null,null,null,null,null,null,45,47,null,null,null,null,null,4,29,32,null,null,null,null,null,null,3,9,26,null,31,34,null,null,7,11,25,27,null,null,33,null,6,8,10,16,null,null,null,28,null,null,5,null,null,null,null,null,15,19,null,null,null,null,12,null,18,20,null,13,17,null,null,22,null,14,null,null,21,23]");
		target = 3.285714;
		
		result = instance.closestValue(root, target);
		System.out.println("result=" + result);
	}
	
	
	// solution III: Accepted
	static class SolutionIII extends ClosestBinarySearchTreeValue {
		public int closestValue(TreeNode root, double target) {
	        TreeNode[] turns = new TreeNode[2];  // 0: src of last left turn; 1: src of last right turn
	        TreeNode res = search(root, target, turns);
	        if (res != null)
	            return res.val;
	        else if (turns[0] == null && turns[1] == null)
	            return root.val;
	        else if (turns[0] == null)
	            return turns[1].val;
	        else if (turns[1] == null)
	            return turns[0].val;
	        else if (Math.abs(target - turns[0].val) > Math.abs(target- turns[1].val))
	            return turns[1].val;
	        else
	            return turns[0].val;
	    }
	    
	    private TreeNode search(TreeNode root, double target, TreeNode[] turns) {
	        if (root == null)
	            return null;
	        if (target == root.val)
	            return root;
	        else if (target < root.val) {
	            turns[0] = root;
	            return search(root.left, target, turns);
	        } else {
	            turns[1] = root;
	            return search(root.right, target, turns);
	        }
	    }
	}
	
	static class SolutionI extends ClosestBinarySearchTreeValue {
		public int closestValue(TreeNode root, double target) {
	        TreeNode node = root;
	        int bigger = 0, smaller = 0;
	        while (node != null) {
	            if (node.val == target) {
	                return node.val;
	            } else if (target < node.val) {
	                bigger = node.val;
	                node = node.left;
	            } else {
	                smaller = node.val;
	                node = node.right;
	            }
	        }
	        return Math.abs(target - bigger) < Math.abs(target - smaller) ? bigger : smaller;
	    }
	}
}
