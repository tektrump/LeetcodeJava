package tektrup.leetcode.oj;


import java.util.Stack;

import tektrup.leetcode.util.TreeNode;
import tektrup.leetcode.util.annotations.Leetcode;
import tektrup.leetcode.util.annotations.Leetcode.Tags;

/**
 * 2 different ways to convert a dfs from recursion into iteration.
 * good discussions: 
 * 	https://leetcode.com/discuss/43771/implemented-java-binary-search-order-iterative-recursive
 * @author Alex
 * @date May 3, 2016
 */
@Leetcode(date = "2016/05/03", tags={Tags.CLASSIC, Tags.RECURSION, Tags.TREE},
	url="https://leetcode.com/problems/kth-smallest-element-in-a-bst/")
public abstract class KthSmallestElementInABst {
	public abstract int kthSmallest(TreeNode root, int k);
	public static void main(String[] args) {
    	KthSmallestElementInABst instance = new Solution();
    	int k;
    	
    	TreeNode root = new TreeNode(2);
    	TreeNode left = new TreeNode(1);
    	root.left = left;
    	k = 2;
    	
    	int result = instance.kthSmallest(root, k);
    	System.out.println(result);
	}
	
	// Solution IV: Accepted
    // alternative to Solution II & III for iteration
	static class Solution extends KthSmallestElementInABst {
	    public int kthSmallest(TreeNode root, int k) {
	        Stack<TreeNode> nodes = new Stack<>();
	        TreeNode node = root;
	        // since node != null for first iteration; nodes not empty for all subsequent iterations
	        while (node != null || !nodes.isEmpty()) {
	            while (node != null) {
	                nodes.push(node);
	                node = node.left;
	            }
	            node = nodes.pop();
	            if (--k == 0)
	                return node.val;
	            node = node.right; // could be null
	        }
	        return 0; // unreachable
	    }
	}
	
	// small variation from 'Solution'
	static class SolutionA extends KthSmallestElementInABst {
	    public int kthSmallest(TreeNode root, int k) {
	        Stack<TreeNode> nodes = new Stack<>();
	        TreeNode node = root;
	        // since node != null for first iteration; nodes not empty for all subsequent iterations
	        while (node != null || !nodes.isEmpty()) {
	            while (node != null) {
	                nodes.push(node);
	                node = node.left;
	            }
	            while (!nodes.isEmpty()) {
	            	node = nodes.pop();
	            	if (--k == 0)
	            		return node.val;
	            	if (node.right != null) {
	            		node = node.right;
	            		break;
	            	}
	            }
	        }
	        return 0; // unreachable
	    }
	}
    
	// Solution III: Accepted
    // alternative to Solution II for iteration
	static class SolutionIII extends KthSmallestElementInABst {
	    public int kthSmallest(TreeNode root, int k) {
	        Stack<TreeNode> nodes = new Stack<>();
	        while (true) {
	            TreeNode node = root;
	            while (node != null) {
	                nodes.push(node);
	                node = node.left;
	            }
	            while (true) {
	                node = nodes.pop();
	                if (--k == 0)
	                    return node.val;
	                if (node.right != null) {
	                    node = node.right;
	                    while (node != null) {
	                        nodes.push(node);
	                        node = node.left;
	                    }
	                }
	            }
	        }
	    }
	}
    
    // Solution II: Accepted
    // iteration
	static class SolutionII extends KthSmallestElementInABst {
	    public int kthSmallest(TreeNode root, int k) {
	        Stack<TreeNode> nodes = new Stack<>();
	        nodes.push(root);
	        Stack<Integer> flags = new Stack<>();
	        flags.push(0);
	        int count = 0;
	        while (true) { // since k is guaranteed to be valid.
	            TreeNode node = nodes.pop();
	            Integer flag  = flags.pop();
	            if (flag == 0) {
	                nodes.push(node);
	                flags.push(1);
	                if (node.left != null) {
	                    nodes.push(node.left);
	                    flags.push(0);
	                }
	            } else { // you will only have 2 possible value for glag: 0 or 1.
	                if (++count == k)
	                    return node.val;
	                if (node.right != null) {
	                    nodes.push(node.right);
	                    flags.push(0);
	                }
	            }
	        }
	    }
	}
    
    // Solution Ib: Accepted
    // use array instead of member variable
	static class SolutionIb extends KthSmallestElementInABst {
	    public int kthSmallest(TreeNode root, int k) {
	        return recur(root, new int[]{k});    
	    }
	    
	    private Integer recur(TreeNode node, int[] a) {
	        Integer result = null;
	        if (node.left != null) {
	            result = recur(node.left, a);
	            if (result != null)
	                return result;
	        }
	        if (--a[0] == 0)
	            return node.val;
	        if (node.right != null)
	            result = recur(node.right, a);
	        return result;
	    }
	}
    
    // Solution Ia: Error
    // use Integer object
	static class SolutionIa extends KthSmallestElementInABst {
	    public int kthSmallest(TreeNode root, int k) {
	        return recur(root, new Integer(k));    
	    }
	    
	    private Integer recur(TreeNode node, Integer i) {
	        Integer result = null;
	        if (node.left != null) {
	            result = recur(node.left, i);
	            if (result != null)
	                return result;
	        }
	        if (--i == 0)
	            return node.val;
	        if (node.right != null)
	            result = recur(node.right, i);
	        return result;
	    }
	}
    
    // Solution I: Accepted
    // recursion
	static class SolutionI extends KthSmallestElementInABst {
	    // if it were C++, we could use &count;
	    // or, we can also use an Integer object.
	    private int count = 0; 
	    public int kthSmallest(TreeNode root, int k) {
	        return recur(root, k);
	    }
	    
	    private Integer recur(TreeNode node, int k) {
	        Integer result = null;
	        if (node.left != null) {
	            result = recur(node.left, k);
	            if (result != null)
	                return result;
	        }
	        if (++count == k)
	            return node.val;
	        if (node.right != null) {
	            result = recur(node.right, k);
	        }
	        return result;
	    }
	}

}
