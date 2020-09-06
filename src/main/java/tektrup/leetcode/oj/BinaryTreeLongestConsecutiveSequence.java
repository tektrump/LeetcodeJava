package tektrup.leetcode.oj;


import tektrup.leetcode.util.TreeNode;

public abstract class BinaryTreeLongestConsecutiveSequence {
	public abstract int longestConsecutive(TreeNode root);
	public static void main(String[] args) {
		BinaryTreeLongestConsecutiveSequence instance = new SolutionIII();
		TreeNode root;
		int result;
		
		root = TreeNode.deserialize("[1,null,3,2,4,null,null,null,5]");
		
		result = instance.longestConsecutive(root);
		System.out.println("result=" + result);
	}
	
	
	static class SolutionIII extends BinaryTreeLongestConsecutiveSequence {
		private int gmax = 0;
	    public int longestConsecutive(TreeNode root) {
	        help(root);
	        return gmax;
	    }
	    
	    private int help(TreeNode root) {
	        if (root == null)
	            return 0;
	        int len = 1;
	        if (root.left != null && root.val+1 == root.left.val)
	            len = Math.max(len, 1 + help(root.left));
	        if (root.right != null && root.val+1 == root.right.val)
	            len = Math.max(len, 1 + help(root.right));
	        gmax = Math.max(gmax, len);
System.out.println("root=" + root.val + ", len=" + len);
	        return len;
	    }
	}
	
	
	static class SolutionII extends BinaryTreeLongestConsecutiveSequence {
		private int max = 0;
	    public int longestConsecutive(TreeNode root) {
	        help(root);
	        return max;
	    }
	    
	    private int help(TreeNode root) {
	        if (root == null)
	            return 0;
	        int lcount = 1, rcount = 1;
	        if (root.left != null && root.val+1 == root.left.val)
	            lcount += help(root.left);
	        if (root.right != null && root.val+1 == root.right.val)
	            rcount += help(root.right);
	        int count = Math.max(lcount, rcount);
	        max = Math.max(max, count);
	        System.out.println("root=" + root + ", count=" + count);
	        return count;
	    }
	}
}
