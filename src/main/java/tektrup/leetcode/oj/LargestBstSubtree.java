package tektrup.leetcode.oj;


import tektrup.leetcode.util.TreeNode;

public abstract class LargestBstSubtree {
	public abstract int largestBSTSubtree(TreeNode root);
	public static void main(String[] args) {
    	LargestBstSubtree instance = new SolutionVI();
    	TreeNode root;
    	
    	// 1
//    	root = TreeNode.deserialize("[1,2]");
    	
//    	root = TreeNode.deserialize("1");
    	
    	// 3
//    	root = TreeNode.deserialize("[10,5,15,1,8,null,7]");
    	
    	// 2
    	root = TreeNode.deserialize("[3,1,null,2,null,null,4]");
    	
//    	root = TreeNode.deserialize("[3,null,2,1,4]");
    	
    	int result = instance.largestBSTSubtree(root);
    	System.out.println("result=" + result);
	}
	
	
	static class SolutionVI extends LargestBstSubtree {
		private int gmax = 0;
	    public int largestBSTSubtree(TreeNode root) {
	        help(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	        return gmax;
	    }
	    
	    private int help(TreeNode root, int min, int max) {
	        if (root == null)
	            return 0;
	        int count = 0;
	        int lcount = help(root.left, min, root.val);
	        int rcount = help(root.right, root.val, max);
if (root.val == 2)
	System.out.println();
	        if (lcount >= 0 && rcount >= 0) { // current subtree is valid BST
	            count = 1 + lcount + rcount;
	            gmax = Math.max(gmax, count);
	            if (root.val > min && root.val < max) // AND, current subtree can be expanded to parent
	                return count;
	        }
	        return -1; // either current subtree is invalid or it cannot be expanded with parent
	    }
	}
	
	
	static class SolutionV extends LargestBstSubtree {
		private int globalMax = 0;
	    public int largestBSTSubtree(TreeNode root) {
	        if (root == null)
	            return 0;
	        help(root, new int[2]);
	        return globalMax;
	    }
	    
	    private int help(TreeNode root, int[] range) {
	        boolean valid = true;
	        int min = root.val, max = root.val;
	        int lcount = 0, rcount = 0;
	        if (root.left != null) {
	            int[] lrange = new int[2];
	            if ((lcount = help(root.left, lrange)) < 0 || root.val <= lrange[1])
	                valid = false;
	            else
	                min = lrange[0];
	        }
	        if (root.right != null) {
	            int[] rrange = new int[2];
	            if ((rcount = help(root.right, rrange)) < 0 || root.val >= rrange[0])
	                valid = false;
	            else
	                max = rrange[1];
	        }
	        if (!valid)
	            return -1;
	        int count = 1 + lcount + rcount;
	        globalMax = Math.max(globalMax, count);
	        return count;
	    }
	}
	
	
	static class SolutionIV extends LargestBstSubtree {
		private int maxCount = 0;
	    public int largestBSTSubtree(TreeNode root) {
	        if (root == null)
	            return 0;
	        help(root, new int[2]);
	        return maxCount;
	    }
	    
	    private int help(TreeNode root, int[] range) {
	        int count = 1;
	        int min = root.val, max = root.val;
	        boolean success = true;
	        if (root.left != null) {
	            int[] lrange = new int[2];
	            int lcount = help(root.left, lrange);
	            if (lcount == 0 || root.val < lrange[1])
	                success = false;
	            count += lcount;
	            min = lrange[0];
	        }
	        if (root.right != null) {
	            int[] rrange = new int[2];
	            int rcount = help(root.right, rrange);
	            if (rcount == 0 || root.val > rrange[0])
	                success = false;
	            count += rcount;
	            max = rrange[1];
	        }
	        range[0] = min;
	        range[1] = max;
	        if (!success)
	            return 0;
	        else {
	            maxCount = Math.max(maxCount, count);
	            return count;
	        }
	    }
	}
	
	
	static class SolutionI extends LargestBstSubtree {
		private int largest = 0;
	    public int largestBSTSubtree(TreeNode root) {
	        if (root != null)
	            recur(root, true);
	        return largest;
	    }
	    
	    // array [size, min, max, largest]; size=-1 indicating invalid BST
	    private int[] recur(TreeNode node, boolean left) {
	        int[] results = new int[3];
	        int lmin = Integer.MAX_VALUE, lmax = Integer.MIN_VALUE;
	        int rmin = Integer.MAX_VALUE, rmax = Integer.MIN_VALUE;
	        if (node.left != null) {
	            int[] lresults = recur(node.left, true);
	            if (lresults[0] < 0) {
	                results[0] = -1;
	                return results;
	            } else {
	                results[0] += lresults[0];
	                lmin = lresults[1];
	                lmax = lresults[2];
	            }
	        }
	        if (node.right != null) {
	            int[] rresults = recur(node.right, false);
	            if (rresults[0] < 0) {
	                results[0] = -1;
	                return results;
	            } else {
	                results[0] += rresults[0];
	                rmin = rresults[1];
	                rmax = rresults[2];
	            }
	        }
	        if (node.val >= lmax && node.val <= rmin) {
	            results[0]++;
	            results[1] = Math.min(node.val, lmin);
	            results[2] = Math.max(node.val, rmax);
	            largest = Math.max(largest, results[0]);
	        } else {
	            results[0] = -1;
	        }
	        return results;
	    }
	}
    
}
