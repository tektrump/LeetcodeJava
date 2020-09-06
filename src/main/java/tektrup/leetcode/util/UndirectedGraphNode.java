package tektrup.leetcode.util;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraphNode {
	public int label;
	public List<UndirectedGraphNode> neighbors;
	public UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<>();
	}
	
//	public static UndirectedGraphNode str2graph(String s) {
//		UndirectedGraphNode root = null;
//		Map<UndirectedGraphNode, List<UndirectedGraphNode>> nodeNeighbors = new HashMap<>();
//		String[] splits = s.split("#");
//		for (String split : splits) {
//			String[] labels = split.split(",");
//			UndirectedGraphNode node = new UndirectedGraphNode(Integer.valueOf(labels[0]));
//			if (root == null)
//				root = node;
//			List<UndirectedGraphNode> neighbors = nodeNeighbors.get(node);
//			if (neighbors == null) {
//				neighbors = new ArrayList<>();
//				nodeNeighbors.put(node, neighbors);
//			}
//			for (int i = 1; i < labels.length; i++) {
//				UndirectedGraphNode neighbor = new UndirectedGraphNode(Integer.valueOf(labels[i]));
//				neighbors.add(neighbor);
//				
//			}
//		}
//	}
}
