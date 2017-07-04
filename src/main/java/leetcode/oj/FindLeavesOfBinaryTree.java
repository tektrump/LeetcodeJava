package leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import leetcode.util.TreeNode;
import leetcode.util.annotations.Leetcode;
import leetcode.util.annotations.Leetcode.Tags;

@Leetcode(date="2016-07-15", tags={Tags.TREE, Tags.REMEMBER}, 
url="https://leetcode.com/problems/find-leaves-of-binary-tree/")
public abstract class FindLeavesOfBinaryTree {
	public abstract List<List<Integer>> findLeaves(TreeNode root);
	public static void main(String[] args) {
		FindLeavesOfBinaryTree instance = new SolutionII();
		TreeNode root;
		List<List<Integer>> rets;
		
		root = TreeNode.deserialize("[1,2,3,4,5]");
		
		rets = instance.findLeaves(root);
		System.out.println("results=" + rets);
	}
	
	// Solution V: Best
    // DFS (backtracking) instead of topological traversal.
    // group nodes at the same "node height" (distance from leaves).
	static class SolutionV extends FindLeavesOfBinaryTree {
	    public List<List<Integer>> findLeaves(TreeNode root) {
	        if (root == null)
	            return Collections.emptyList();
	        List<List<Integer>> rets = new ArrayList<>();
	        dfs(root, rets);
	        return rets;
	    }
	    
	    // calculate height; add root to its height; root is not null
	    private int dfs(TreeNode root, List<List<Integer>> rets) {
	        if (root == null)
	            return -1;  // compatible with recursion logic
	        int lh = dfs(root.left, rets);
	        int rh = dfs(root.right, rets);
	        int h = Math.max(lh, rh) + 1;
	        if (rets.size() == h)
	            rets.add(new LinkedList<>());
	        rets.get(h).add(root.val);
	        return h;
	    }
	}
	
	
	// Solution IV: Accepted
	// Topology search style with 2 maps: parent~child & child~parent.
    // pick a leaf and check its parent;
    // update count of remain children parent, if falls to 0, pick parent as next leaf.
    // O(n) as we visit each node once or twice.
    // NOTE: its much faster to track count of remaining children, other than using a set
    // to track visited nodes, and check if both children are contained in that set.
	static class SolutionIV extends FindLeavesOfBinaryTree {
	    public List<List<Integer>> findLeaves(TreeNode root) {
	        if (root == null)
	            return Collections.emptyList();
	        Map<TreeNode, TreeNode> nodeParent = new HashMap<>();
	        Map<TreeNode, Integer> nodeCount = new HashMap<>();
	        List<TreeNode> list = Arrays.asList(root);
	        List<TreeNode> leaves = new LinkedList<>();
	        while (!list.isEmpty()) {
	            List<TreeNode> nextList = new LinkedList<>();
	            for (TreeNode node : list) {
	                int count = 0;
	                if (node.left == null && node.right == null)
	                    leaves.add(node);
	                else {
	                    if (node.left != null) {
	                        nodeParent.put(node.left, node);
	                        nextList.add(node.left);
	                        count++;
	                    }
	                    if (node.right != null) {
	                        nodeParent.put(node.right, node);
	                        nextList.add(node.right);
	                        count++;
	                    }
	                    nodeCount.put(node, count);
	                }
	            }
	            list = nextList;
	        }
	        List<List<Integer>> rets = new LinkedList<>();
	        while (!leaves.isEmpty()) {
	            List<TreeNode> nextLeaves = new LinkedList<>();
	            List<Integer> ret = new LinkedList<>();
	            for (TreeNode leaf : leaves) {
	                ret.add(leaf.val);
	                TreeNode parent = nodeParent.remove(leaf);
	                if (parent != null) {
	                    Integer count = nodeCount.get(parent);
	                    if (count == 1) {
	                        nextLeaves.add(parent);
	                        nodeCount.remove(parent);
	                    } else  // must be 2
	                        nodeCount.put(parent, 1);
	                }
	            }
	            rets.add(ret);
	            leaves = nextLeaves;
	        }
	        
	        return rets;
	    }
	}
	
	
	// Solution III: Accepted
	// Topology search style. 
	// build a node ~ parent map;
    // starting from leaf nodes, add removed node's parent as candidates for next leaves;
    // iterate through candidates, if its both children have been picked, add as leaf.
    // bfs; at end of each loop, purge picked leaves from map.
    // NOTE: one trick is: avoid iterating through all nodes and find those qualified for leaves; too expensive;
    // instead, our logic narrow down candidates as parent of previous leaves, which greatly reduce the candidates to check.
	static class SolutionIII extends FindLeavesOfBinaryTree {
		public List<List<Integer>> findLeaves(TreeNode root) {
	        // logic covers if root == null
	        List<List<Integer>> results = new ArrayList<>();
	        Map<TreeNode, TreeNode> nodeParent = new HashMap<>();
	        Set<TreeNode> nodes = new HashSet<>();
	        visit(root, null, nodeParent, nodes);
	        while (!nodes.isEmpty()) {
	            Set<TreeNode> nextNodes = new HashSet<>();
	            List<TreeNode> leaves = new ArrayList<>();
	            List<Integer> result = new ArrayList<>();
	            for (TreeNode node : nodes) {
	                if (!nodeParent.containsKey(node.left) && !nodeParent.containsKey(node.right)) {
	                    result.add(node.val);
	                    leaves.add(node);
	                    TreeNode parent = nodeParent.get(node);
	                    if (parent != null) // ERROR: must watch out as root's parent is null.
	                        nextNodes.add(parent);
	                } else {
	                    nextNodes.add(node);
	                }
	            }
	            // purge those newly picked leaves
	            for (TreeNode leaf : leaves) 
	                nodeParent.remove(leaf); // ERROR: only purge after complete the entire iteration!
	            results.add(result);
	            nodes = nextNodes;
	        }
	        return results;
	    }
	    
	    private void visit(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> nodeParent, Set<TreeNode> leaves) {
	        if (node != null) {
	            nodeParent.put(node, parent);
	            if (node.left == null && node.right == null)
	                leaves.add(node);
	            else {
	                visit(node.left,  node, nodeParent, leaves);
	                visit(node.right, node, nodeParent, leaves);
	            }
	        }
	    }
	}
	
	
	// Solution II: Logic Error
	// see comments
	static class SolutionII extends FindLeavesOfBinaryTree {
		public List<List<Integer>> findLeaves(TreeNode root) {
	        if (root == null)
	            return Collections.emptyList();
	        List<List<Integer>> results = new ArrayList<>();
	        Map<TreeNode, TreeNode> nodeParent = new HashMap<>();
	        Set<TreeNode> nodes = new HashSet<>();
	        visit(root, null, nodeParent, nodes);
	        while (!nodes.isEmpty()) {
	            Set<TreeNode> nextNodes = new HashSet<>();
	            List<Integer> result = new ArrayList<>();
	            for (TreeNode node : nodes) {
	                if (!nodeParent.containsKey(node.left) && !nodeParent.containsKey(node.right)) {
	                    result.add(node.val);
	                    TreeNode parent = nodeParent.remove(node); // ERROR: remove too early, see fix in Solution III
	                    if (parent != null)
	                        nextNodes.add(parent);
	                } else {
	                    nextNodes.add(node);
	                }
	            }
	            results.add(result);
	            nodes = nextNodes;
	        }
	        return results;
	    }
	    
	    private void visit(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> nodeParent, Set<TreeNode> leaves) {
	        if (node != null) {
	            nodeParent.put(node, parent);
	            if (node.left == null && node.right == null)
	                leaves.add(node);
	            else {
	                visit(node.left,  node, nodeParent, leaves);
	                visit(node.right, node, nodeParent, leaves);
	            }
	        }
	    }
	}

	
	// Solution I: Logic Error
    // ERROR: if a node is picked, its parent may not immediately become next leaf;
    // must check if a node's both children have already been picked; if so, it becomes next leaf.
	static class SolutionI extends FindLeavesOfBinaryTree {
	    public List<List<Integer>> findLeaves(TreeNode root) {
	        if (root == null)
	            return Collections.emptyList();
	        List<List<Integer>> results = new ArrayList<>();
	        Map<TreeNode, TreeNode> nodeParent = new HashMap<>();
	        Set<TreeNode> leaves = new HashSet<>();
	        visit(root, null, nodeParent, leaves);
	        while (!leaves.isEmpty()) {
	            Set<TreeNode> nextLeaves = new HashSet<>();
	            List<Integer> result = new ArrayList<>();
	            for (TreeNode leaf : leaves) {
	                if (leaf != null) {
	                    result.add(leaf.val);
	                    nextLeaves.add(nodeParent.remove(leaf));
	                }
	            }
	            results.add(result);
	            leaves = nextLeaves;
	        }
	        return results;
	    }
	    
	    private void visit(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> nodeParent, Set<TreeNode> leaves) {
	        if (node != null) {
	            nodeParent.put(node, parent);
	            if (node.left == null && node.right == null)
	                leaves.add(node);
	            else {
	                visit(node.left,  node, nodeParent, leaves);
	                visit(node.right, node, nodeParent, leaves);
	            }
	        }
	    }
	}
}
