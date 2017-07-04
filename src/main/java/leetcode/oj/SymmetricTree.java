package leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import leetcode.util.TreeNode;

public abstract class SymmetricTree {
	public abstract boolean isSymmetric(TreeNode root);
	public static void main(String[] args) {
		SymmetricTree instance = new SolutionI();
		TreeNode root;
		
		root = TreeNode.deserialize("[1,2,2,3,4,4,3]");
		
		boolean result = instance.isSymmetric(root);
		System.out.println("result=" + result);
	}
	
	
	static class SolutionI extends SymmetricTree {
		public boolean isSymmetric(TreeNode root) {
	        if (root == null)
	            return true;
	        List<TreeNode> nodes = Arrays.asList(root);
	        while (!nodes.isEmpty()) {
	            List<TreeNode> nextNodes = new ArrayList<>();
	            int l = 0, r = nodes.size()-1;
	            while (l < r) {
	                TreeNode ln = nodes.get(l++), rn = nodes.get(r--);
	                if (ln == null && rn == null)
	                    continue;
	                if (ln != null && rn != null && ln.val == rn.val)
	                    continue;
	                return false;
	            }
	            for (TreeNode node : nodes) {
	                if (node != null) {
	                    nextNodes.add(node.left);
	                    nextNodes.add(node.right);
	                } else {
	                    nextNodes.add(null);
	                    nextNodes.add(null);
	                }
	            }
	            nodes = nextNodes;
	        }
	        return true;
	    }
	}
}
