package leetcode.oj;


import leetcode.util.TreeNode;

public abstract class CountUnivalueSubtrees {
	public abstract int countUnivalSubtrees(TreeNode root);
	public static void main(String[] args) {
    	CountUnivalueSubtrees instance = new SolutionII();
    	TreeNode root;
    	
    	root = TreeNode.deserialize("[5,1,5,5,5,null,5]");
    	
    	int result = instance.countUnivalSubtrees(root);
    	System.out.println("result=" + result);
	}
	
	
	static class SolutionII extends CountUnivalueSubtrees {
		private int count = 0;
	    public int countUnivalSubtrees(TreeNode root) {
	        if (root == null)
	            return 0;
	        help(root);
	        return count;
	    }
	    
	    // root is not null
	    private Integer help(TreeNode root) {
	        if (root.left != null) {
	            Integer lval = help(root.left);
	            if (lval == null || lval != root.val)
	                return null;
	        }
	        if (root.right != null) {
	            Integer rval = help(root.right);
	            if (rval == null || rval != root.val)
	                return null;
	        }
	        count++;
	        return root.val;
	    }
	}
	
	
	static class SolutionI extends CountUnivalueSubtrees {
		public int countUnivalSubtrees(TreeNode root) {
	        if (root == null)
	            return 0;
	        int[] count = {0};
	        recur(root, count);
	        return count[0];
	    }
	    
	    private Integer recur(TreeNode node, int[] count) {
	        Integer val = new Integer(node.val);
	        if (node.left == null && node.right == null) {
	            count[0]++;
	            return node.val;
	        } else {
	        	boolean unique = true;
	            if (node.left != null && !val.equals(recur(node.left, count)))
	                unique = false;
	            if (node.right != null && !val.equals(recur(node.right, count)))
	                unique = false;
	            if (unique) {
	            	count[0]++;
	            	return val;
	            } else
	            	return null;
	        }
	    }
	}
    
}
