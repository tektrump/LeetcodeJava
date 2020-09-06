package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tektrup.leetcode.util.UndirectedGraphNode;

public class CloneGraph {
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null)
            return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> nodeCopy = new HashMap<>();
        List<UndirectedGraphNode> list = new ArrayList<>();
        list.add(node);
        nodeCopy.put(node, new UndirectedGraphNode(node.label));
        while (!list.isEmpty()) {
            List<UndirectedGraphNode> nextList = new ArrayList<>();
            for (UndirectedGraphNode e : list) {
                List<UndirectedGraphNode> neighbors = e.neighbors;
                for (UndirectedGraphNode neighbor : neighbors) {
                    if (!nodeCopy.containsKey(e)) {
                        nodeCopy.put(neighbor, new UndirectedGraphNode(neighbor.label));
                        nextList.add(neighbor);
                    }
                }
            }
            list = nextList;
        }
        for (Map.Entry<UndirectedGraphNode, UndirectedGraphNode> entry : nodeCopy.entrySet()) {
            UndirectedGraphNode n = entry.getKey();
            List<UndirectedGraphNode> neighbors = n.neighbors;
            UndirectedGraphNode copy = entry.getValue();
            copy.neighbors = new ArrayList<>();
            for (UndirectedGraphNode neighbor : neighbors)
                copy.neighbors.add(nodeCopy.get(neighbor));
        }
        return nodeCopy.get(node);
    }
	
	public static void main(String[] args) {
//		UndirectedGraphNode instance = new UndirectedGraphNode();
		
		
	}

}
