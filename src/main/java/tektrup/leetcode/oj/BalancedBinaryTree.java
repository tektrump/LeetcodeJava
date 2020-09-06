package tektrup.leetcode.oj;


import tektrup.leetcode.util.TreeNode;

public abstract class BalancedBinaryTree {
	public abstract boolean isBalanced(TreeNode root);
    public static void main(String[] args) {
    	BalancedBinaryTree instance = new SolutionII();
    	TreeNode root;
    	
//    	TreeNode node1 = new TreeNode(1);
//    	TreeNode node2 = new TreeNode(2);
//    	TreeNode node3 = new TreeNode(3);
//    	node1.right = node2;
//    	node2.right = node3;
//    	root = node1;
    	
    	// true
    	root = TreeNode.deserialize("[1,2,2,3,3,3,3,4,4,4,4,4,4,null,null,5,5]");
    	
    	boolean result = instance.isBalanced(root);
    	System.out.println("result=" + result);
	}
    
    
    // Solution II: Accepted
    // "depth of tree" is the max length from root to any leaf.
    static class SolutionII extends BalancedBinaryTree {
        public boolean isBalanced(TreeNode root) {
            return help(root) >= 0;
        }
        
        private int help(TreeNode node) {
            // termination
            if (node == null)
                return 0;
            int d1 = help(node.left);
            if (d1 < 0)
                return -1;
            int d2 = help(node.right);
            if (d2 < 0)
                return -1;
            if (Math.abs(d1 - d2) > 1)
                return -1;
            return Math.max(d1, d2) + 1;
        }
    }
    
    
    // Solution I: Misunderstand Problem
    static class SolutionI extends BalancedBinaryTree {
    	public boolean isBalanced(TreeNode root) {
            int[] range = new int[2];
            return help(root, range);
        }
        
        private boolean help(TreeNode node, int[] range) {
            // termination
            if (node == null)
                return true;
            int[] lrange = new int[2];
            help(node.left, lrange);
            if (lrange[1] - lrange[0] > 1)
                return false;
            int[] rrange = new int[2];
            help(node.right, rrange);
            if (rrange[1] - rrange[0] > 1)
                return false;
            range[0] = Math.min(lrange[0], rrange[0]) + 1;
            range[1] = Math.max(lrange[1], rrange[1]) + 1;
            return range[1] - range[0] <= 1; 
        }
    }
}
