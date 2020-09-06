package tektrup.leetcode.util;

import java.util.ArrayList;
import java.util.List;

public class TreeLinkNode {
	public int val;
	public TreeLinkNode left, right, next;

	public TreeLinkNode(int x) {
		val = x;
	}
	
	public void print() {
		StringBuilder builder = new StringBuilder();
		List<TreeLinkNode> list = new ArrayList<TreeLinkNode>();
		list.add(this);
		while (!list.isEmpty()) {
			List<TreeLinkNode> nextList = new ArrayList<TreeLinkNode>();
			for (TreeLinkNode node : list) {
				builder.append(node.val).append(",");
				if (node.left != null)
					nextList.add(node.left);
				if (node.right != null)
					nextList.add(node.right);
			}
			list = nextList;
		}
		String s = builder.substring(0, builder.length()-1);
		System.out.println(s);
	}
}
