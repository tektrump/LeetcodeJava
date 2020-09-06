package tektrup.leetcode.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeNode {
	public TreeNode left;
	public TreeNode right;
	public int val;
	public TreeNode(int val) {
		this.val = val;
	}
	
	@Override
	public String toString() {
		return "val=" + val;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof TreeNode)
			return ((TreeNode)o).val == this.val;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		return this.val;
	}
	
	public static String serialize(TreeNode root) {
		return serialize(root, "#");
	}
	
	/**
	 * Serialize to string, where nullString can be optionally provided by user. Default is "#".
	 * Serialization with "#" matches the format used on Leetcode.
	 * Optimization: 
	 * 1. if a node is null, then its children will not be included.
	 * 2. do not include "trailing" nulls: 1,#,2,#,# -> 1,#,2
	 * @param root
	 * @param nullStr
	 * @return
	 */
	public static String serialize(TreeNode root, String nullStr) {
		if (root == null)
			return "";
		StringBuilder builder = new StringBuilder();
		List<TreeNode> nodes = new ArrayList<>();
		nodes.add(root);
		TreeNode last = root;
		while (!nodes.isEmpty()) {
			List<TreeNode> children = new ArrayList<>();
			for (TreeNode node : nodes) {
				if (builder.length() > 0)
					builder.append(",");
				if (node == null) {
					builder.append(nullStr);
				} else {
					builder.append(node.val);
					if (node.left != null || node.right != null) {
						children.add(node.left);
						children.add(node.right);
						if (node.right != null)
							last = node.right;
						else
							last = node.left;
					}
					// optimization: avoid trailing #s.
					if (node == last)
						return builder.toString();
				}
			}
			nodes = children;
		}
		return builder.toString();
	}
	
	public static TreeNode deserialize (String s) {
		if (s == null)
			return null;
		s = s.trim();
		// remove "[]", if any
		if (s.charAt(0) == '[')
			s = s.substring(1, s.length());
		if (s.charAt(s.length()-1) == ']')
			s = s.substring(0, s.length() - 1);
		if (s.isEmpty())
			return null;
		
		String[] splits = s.split(",");
		if (splits.length == 1 && !isDigit(s))
			return null;
		TreeNode root = new TreeNode(Integer.valueOf(splits[0]));
		List<TreeNode> nodes = new LinkedList<>();
		nodes.add(root);
		int i = 1;
		while (i < splits.length) {
			List<TreeNode> children = new LinkedList<>();
			for (TreeNode node : nodes) {
				if (isDigit(splits[i])) {
					TreeNode left = new TreeNode(Integer.valueOf(splits[i]));
					node.left = left;
					children.add(left);
				}
				if (++i == splits.length) // have to check here because we do not include trailing nulls
					break;
				if (isDigit(splits[i])) {
					TreeNode right = new TreeNode(Integer.valueOf(splits[i]));
					node.right = right;
					children.add(right);
				}
				if (++i == splits.length) // have to check here because we do not include trailing nulls
					break;
			}
			nodes = children;
		}
		return root;
	}
	
	private static boolean isDigit(String s) {
		char ch = s.charAt(0);
		if (ch == '-') { // negative sign
			if (s.length() == 1)
				return false;
			ch = s.charAt(1);
		}
		return ch >= '0' && ch <= '9';
	}
	
//	public static String serialize(TreeNode root) {
//		if (root == null)
//			return "";
//		StringBuilder builder = new StringBuilder();
//		List<TreeNode> list = new LinkedList<>();
//		list.add(root);
//		TreeNode last = root;
//		boolean stop = false;
//		while (!list.isEmpty()) {
//			List<TreeNode> nextList = new LinkedList<>();
//			for (TreeNode node : list) {
//				if (node == null) {
//					builder.append("null,");
//					nextList.add(null);
//					nextList.add(null);
//				} else {
//					builder.append(node.val).append(",");
//					nextList.add(node.left);
//					nextList.add(node.right);
//					
//					if (node.right != null)
//						last = node.right;
//					else if (node.left != null)
//						last = node.left;
//					
//					if (node == last) {
//						stop = true;
//						break;
//					}
//				}
//			}
//			if (stop)
//				break;
//			list = nextList;
//		}
//		builder.delete(builder.length()-1, builder.length());
//		return builder.toString();
//	}
	
	/**
	 * null node can be represented by "null" or "#".
	 * @param s
	 * @return
	 */
	/*
	public static TreeNode deserialize (String s) {
		s = s.trim();
		if (s.charAt(0) == '[') { // remove square parenthesis, if any
			s = s.substring(1, s.length()-1);
		}
		if (s.isEmpty() || s.equals("null"))
			return null;
		String[] splits = s.split(",");
		List<TreeNode> prev = new ArrayList<>();
		TreeNode root = new TreeNode(Integer.valueOf(splits[0].trim()));
		prev.add(root);
		int idx = 1;
		List<TreeNode> curr = new ArrayList<>();
		boolean left = true;
		while (idx < splits.length) {
			TreeNode child = null;
			String val = splits[idx++].trim();
			if (!val.equals("null") && !val.equals("#"))
				child = new TreeNode(Integer.valueOf(val));
			curr.add(child);
			if (left) {
				prev.get(0).left = child;
			} else {
				prev.get(0).right = child;
				prev.remove(0);
				if (prev.isEmpty()) {
					prev = curr;
					curr = new ArrayList<>();
				}
			}
			left ^= true;
		}
		return root;
	}
	*/
	
	
	public static void main(String[] args) {
//		String s = "5,1,5,5,5,#,5";
//		TreeNode root = TreeNode.deserialize(s);
//		String s2 = TreeNode.serialize(root);
//		System.out.println("serialized=" + s2);
//		System.out.println("success: " + s2.equals(s));
		
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		n1.right = n2;
		n2.left = n3;
		System.out.println("serialized=" + TreeNode.serialize(n1));
	}
}
