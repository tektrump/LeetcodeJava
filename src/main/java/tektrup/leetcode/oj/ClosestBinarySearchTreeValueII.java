package tektrup.leetcode.oj;


import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import tektrup.leetcode.util.TreeNode;

public abstract class ClosestBinarySearchTreeValueII {
	public abstract List<Integer> closestKValues(TreeNode root, double target, int k);
	public static void main(String[] args) {
		ClosestBinarySearchTreeValueII instance = new SolutionV();
		TreeNode root; double target; int k;
		List<Integer> res;
		
		// 2, 1
//		root = TreeNode.deserialize("[2,1]");
//		target = 4.142857;
//		k = 2;
		
		root = TreeNode.deserialize("[21,19,24,1,20,23,28,0,15,null,null,22,null,27,34,null,null,11,16,null,null,26,null,29,37,8,13,null,18,25,null,null,32,35,43,7,9,12,14,17,null,null,null,31,33,null,36,39,45,6,null,null,10,null,null,null,null,null,null,30,null,null,null,null,null,38,42,44,49,2,null,null,null,null,null,null,null,41,null,null,null,48,null,null,5,40,null,47,null,4,null,null,null,46,null,3]");
		target = 2.000000;
		k = 13;
		
		res = instance.closestKValues(root, target, k);
		System.out.println("res=" + res);
	}
	
	static class SolutionIV extends ClosestBinarySearchTreeValueII {
		public List<Integer> closestKValues(TreeNode root, double target, int k) {
	        Stack<TreeNode> preds = new Stack<>(), succs = new Stack<>();
	        search(root, target, preds, succs);
System.out.println("preds=" + preds);
System.out.println("succs=" + succs);
	        List<Integer> res = new LinkedList<>();
	        TreeNode p = nextPred(preds), s = nextSucc(succs);
	        if (p == s)
	        	s = nextSucc(succs);
	        while (res.size() < k) {
	            if (p == null) {
	                res.add(s.val);
	                s = nextSucc(succs);
	            } else if (s == null) {
	                res.add(p.val);
	                p = nextPred(preds);
	        } else {
	                if (Math.abs(target-p.val) < Math.abs(s.val-target)) {
	                    res.add(p.val);
	                    p = nextPred(preds);
	                } else {
	                    res.add(s.val);
	                    s = nextSucc(succs);
	                }
	            }
	        }
	        return res;
	    }
	    
	    private void search(TreeNode root, double target, Stack<TreeNode> preds, Stack<TreeNode> succs) {
	        while (root != null) {
	            if (target == root.val) {
	                preds.push(root);
	                succs.push(root);
	                break;
	            } else if (target < root.val) {
	                succs.push(root);
	                root = root.left;
	            } else {
	                preds.push(root);
	                root = root.right;
	            }
	        }
	    }
	    
	    private TreeNode nextPred(Stack<TreeNode> preds) {
	        if (preds.isEmpty())
	            return null;
	        TreeNode res = preds.pop();
	        TreeNode next = res.left;
	        while (next != null) {
	            preds.push(next);
	            next = next.right;
	        }
	        return res;
	    }
	    
	    private TreeNode nextSucc(Stack<TreeNode> succs) {
	        if (succs.isEmpty())
	            return null;
	        TreeNode res = succs.pop();
	        TreeNode next = res.right;
	        while (next != null) {
	            succs.push(next);
	            next = next.left;
	        }
	        return res;
	    }
	}
	
	// from comments
	static class SolutionV extends ClosestBinarySearchTreeValueII {
		public List<Integer> closestKValues(TreeNode root, double target, int k) {
	        List<Integer> ret = new LinkedList<>();
	        Stack<TreeNode> succ = new Stack<>();
	        Stack<TreeNode> pred = new Stack<>();
	        initializePredecessorStack(root, target, pred);
	        initializeSuccessorStack(root, target, succ);
System.out.println("pred=" + pred);
System.out.println("succ=" + succ);
	        if(!succ.isEmpty() && !pred.isEmpty() && succ.peek().val == pred.peek().val) {
	            getNextPredecessor(pred);
	        }
	        while(k-- > 0) {
	            if(succ.isEmpty()) {
	                ret.add(getNextPredecessor(pred));
	            } else if(pred.isEmpty()) {
	                ret.add(getNextSuccessor(succ));
	            } else {
	                double succ_diff = Math.abs((double)succ.peek().val - target);
	                double pred_diff = Math.abs((double)pred.peek().val - target);
	                if(succ_diff < pred_diff) {
	                    ret.add(getNextSuccessor(succ));
	                } else {
	                    ret.add(getNextPredecessor(pred));
	                }
	            }
	        }
	        return ret;
	    }

	    private void initializeSuccessorStack(TreeNode root, double target, Stack<TreeNode> succ) {
	        while(root != null) {
	            if(root.val == target) {
	                succ.push(root);
	                break;
	            } else if(root.val > target) {
	                succ.push(root);
	                root = root.left;
	            } else {
	                root = root.right;
	            }
	        }
	    }

	    private void initializePredecessorStack(TreeNode root, double target, Stack<TreeNode> pred) {
	        while(root != null){
	            if(root.val == target){
	                pred.push(root);
	                break;
	            } else if(root.val < target){
	                pred.push(root);
	                root = root.right;
	            } else{
	                root = root.left;
	            }
	        }
	    }
	    
	    private int getNextSuccessor(Stack<TreeNode> succ) {
	        TreeNode curr = succ.pop();
	        int ret = curr.val;
	        curr = curr.right;
	        while(curr != null) {
	            succ.push(curr);
	            curr = curr.left;
	        }
	        return ret;
	    }

	    private int getNextPredecessor(Stack<TreeNode> pred) {
	        TreeNode curr = pred.pop();
	        int ret = curr.val;
	        curr = curr.left;
	        while(curr != null) {
	            pred.push(curr);
	            curr = curr.right;
	        }
	        return ret;
	    }
	}
}
