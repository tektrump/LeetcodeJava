package tektrup.leetcode.oj;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import tektrup.leetcode.util.ArrayUtil;

public class NumberOfConnectedComponentsInAnUndirectedGraph {
	public int countComponents(int n, int[][] edges) {
        Map<Integer, Set<Integer>> nodeConn = new HashMap<>();
        for (int[] edge : edges) {
            Integer n1 = edge[0], n2 = edge[1];
            Set<Integer> conn1 = nodeConn.get(n1);
            Set<Integer> conn2 = nodeConn.get(n2);
            if (conn1 == null && conn2 == null) {
                Set<Integer> conn = new HashSet<>();
                conn.add(n1);
                conn.add(n2);
                nodeConn.put(n1, conn);
                nodeConn.put(n2, conn);
            } else if (conn1 == null) {
                conn2.add(n1);
                nodeConn.put(n1, conn2);
            } else if (conn2 == null) {
                conn1.add(n2);
                nodeConn.put(n2, conn1);
            } else {
                conn1.addAll(conn2);
                nodeConn.put(n2, conn1);
            }
        }
        Set<Set<Integer>> conns = new HashSet<>();
        for (Map.Entry<Integer, Set<Integer>> entry : nodeConn.entrySet()) {
            conns.add(entry.getValue());
        }
        return conns.size();
    }
	
	public static void main(String[] args) {
		NumberOfConnectedComponentsInAnUndirectedGraph instance = new NumberOfConnectedComponentsInAnUndirectedGraph();
		int n;
		int[][] edges;
		
		// 2
//		n = 5; edges = ArrayUtil.str2int2DArray("[[0, 1], [1, 2], [3, 4]]");
		
		n = 5; edges = ArrayUtil.str2int2DArray("[[0, 1], [1, 2], [2, 3], [3, 4]]");
		
		int result = instance.countComponents(n, edges);
		System.out.println("result=" + result);
	}
}
