package leetcode.oj;


import leetcode.util.TreeNode;
import leetcode.util.annotations.Leetcode;
import leetcode.util.annotations.Leetcode.Tags;

@Leetcode(date="2016-06-05", tags={Tags.TREE}, url="leetcode.com/problems/binary-tree-maximum-path-sum")
public abstract class BinaryTreeMaximumPathSum {
	public abstract int maxPathSum(TreeNode root);
	public static void main(String[] args) {
    	BinaryTreeMaximumPathSum instance = new SolutionI();
    	TreeNode root;
    	int result;
    	
    	root = TreeNode.deserialize("[1,2,3]");
    	result = instance.maxPathSum(root);
    	System.out.println("result=" + result);
	}
	
	// Solution I: Best
	// time: O(n)
	// max(): update a global max value, and return the max sum
	// of a path starting from (and includes) root of current subtree.
	// should we chose not to make 'max' a member variable, we then have to return
	// an array of 2 integers, first being the max path at 1 leg, second being the max path with both legs.
	static class SolutionI extends BinaryTreeMaximumPathSum {
		private int max = Integer.MIN_VALUE; // ERROR: default max is NOT 0
	    public int maxPathSum(TreeNode root) {
	        if (root == null)
	            return 0; // shouldn't happen
	        max(root);
	        return max;
	    }
	    
	    private int max(TreeNode node) {
	        if (node == null) return 0;
	        int left = Math.max(0, max(node.left));
	        int right = Math.max(0, max(node.right));
	        max = Math.max(max, node.val + left + right);
	        return node.val + Math.max(left, right);
	    }
	}
    
    
}
