package leetcode.oj;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import leetcode.util.TreeNode;

public abstract class LowestCommonAncestorOfABinaryTree {
	public abstract TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q);
	public static void main(String[] args) {
		LowestCommonAncestorOfABinaryTree instance = new SolutionV();
		TreeNode root, p, q;
		
		// 1
//		root = TreeNode.deserialize("[1,2,3]");
//		p = new TreeNode(2);
//		q = new TreeNode(3);
		
		// 3
//		root = TreeNode.deserialize("[3,5,1,6,2,0,8,#,#,7,4]");
//		p = new TreeNode(5);
//		q = new TreeNode(1);
		
		
//		root = TreeNode.deserialize("[37,-34,-48,null,-100,-100,48,null,null,null,null,-54,null,-71,-22,null,null,null,8]");
//		p = new TreeNode(-100);
//		q = new TreeNode(-71);
		
		
		root = TreeNode.deserialize("[1,2,3,null,4]");
		p = root.left.right;	// 4
		q = root.right;	// 3
		
		TreeNode result = instance.lowestCommonAncestor(root, p, q);
		System.out.println("result=" + result);
	}
	
	
	// Solution V: Logic Error
	static class SolutionV extends LowestCommonAncestorOfABinaryTree {
		public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	        Stack<TreeNode> s = new Stack<>();
	        int found = 0;
	        Stack<TreeNode> stack = new Stack<>();
	        while (root != null || !stack.isEmpty()) {
	            while (root != null) {
	                if (found == 0)
	                    s.push(root);
	                if (root == p || root == q) {
	                    if (++found == 2)
	                        return s.peek();
	                }
	                stack.push(root);
	                root = root.left;
	            }
	            while (!stack.isEmpty()) {
	                TreeNode pop = stack.pop();
	                if (found == 1 && pop == s.peek()) {
	                    TreeNode last = s.pop();
	                    if (s.isEmpty())
	                        return last;
	                }
	                if (pop.right != null) {
	                    root = pop.right;
	                    break;
	                }
	            }
	        }
	        return null;    // unreachable
	    }
	}
	
	
	static class SolutionIV extends LowestCommonAncestorOfABinaryTree {
		public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	        List<TreeNode> path1 = new ArrayList<>(), path2 = new ArrayList<>();
	        if (!search(root, p, path1) || !search(root, q, path2))
	            return null;
	        for (int i = 0; i < Math.max(path1.size(), path2.size()); i++) {
	            if (i == path1.size() || i == path2.size() || path1.get(i).val != path2.get(i).val)
	                return path1.get(i-1);
	        }
	        return null; // unreachable
	    }
	    
	    private boolean search(TreeNode root, TreeNode target, List<TreeNode> path) {
	        if (root == null)
	            return false;
	        path.add(root); // modify
	        if (root.val == target.val)
	            return true;
	        if (!search(root.left, target, path) && !search(root.right, target, path)) {
	            path.remove(path.size()-1); // restore
	            return false;
	        } else
	            return true;
	    }
	}
	
	
	// Solution II: Logic Error
	static class SolutionII extends LowestCommonAncestorOfABinaryTree {
		public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	        return help(root, p, q, new HashSet<>());
	    }
	    
	    private TreeNode help(TreeNode root, TreeNode p, TreeNode q, Set<TreeNode> nodes) {
	        if (root == null)
	            return null;
	        TreeNode lresult = help(root.left, p, q, nodes);
	        if (lresult != null)
	            return lresult;
	        TreeNode rresult = help(root.right, p, q, nodes);
	        if (rresult != null)
	            return rresult;
	        nodes.add(root);
	        if (nodes.contains(p) && nodes.contains(q))
	            return root;
	        else
	            return null;
	    }
	}
	
	
	// Solution I: old
	static class SolutionI extends LowestCommonAncestorOfABinaryTree {
		public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	        if (root == null)
	            return null;
	        Stack<TreeNode> nodes = new Stack<>();
	        nodes.push(root);
	        Stack<Integer> flags = new Stack<>();
	        flags.push(0);
	        Stack<Set<TreeNode>> subtrees = new Stack<>();
	        subtrees.push(new HashSet<>());
	        Set<TreeNode> prevSubtree = new HashSet<>();
	        while (!nodes.isEmpty()) {
	            TreeNode node = nodes.pop();
	            Integer flag  = flags.pop();
	            Set<TreeNode> subtree = subtrees.pop();
	            if (flag == 0) { // check left subtree
	                if (node != null) {
	                    nodes.push(node);
	                    flags.push(1);
	                    subtrees.push(subtree);
	                    // left sub
	                    nodes.push(node.left);
	                    flags.push(0);
	                    subtrees.push(new HashSet<>());
	                } else {
	                    prevSubtree = subtree; // done with this subtree, it's an empty subtree
	                }
	            } else if (flag == 1) { // done with left, check right subtree
	                nodes.push(node);
	                flags.push(2);
	                subtree.addAll(prevSubtree);    // add all nodes on left subtree
	                subtrees.push(subtree);
	                // right sub
	                nodes.push(node.right);
	                flags.push(0);
	                subtrees.push(new HashSet<>());
	            } else { // done with right subtree
	                subtree.addAll(prevSubtree); // add all nodes on right subtree
	                subtree.add(node);
	                if (subtree.contains(p) && subtree.contains(q))
	                    return node;
	                prevSubtree = subtree;
	            }
	        }
	        return null;
	    }
	}

}
