package leetcode.oj;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import leetcode.util.ArrayUtil;

public abstract class GraphValidTree {
	public abstract boolean validTree(int n, int[][] edges);
	public static void main(String[] args) {
		GraphValidTree instance = new SolutionII();
		int n; int[][] edges;
		boolean res;
		
//		n = 5;
//		edges = ArrayUtil.str2int2DArray("[[0,1],[0,2],[2,3],[2,4]]");
		
		n = 5;
		edges = ArrayUtil.str2int2DArray("[[0,1],[2,1],[2,0],[2,4]]");
		
		res = instance.validTree(n, edges);
		System.out.println("result=" + res);
	}
	
	
	public static class SolutionIV extends GraphValidTree {
		public boolean validTree(int n, int[][] edges) {
	        int[] map = new int[n];
	        for (int i = 0; i < n; map[i] = i, i++);
	        for (int[] e : edges) {
	            int num1 = e[0], num2 = e[1];
	            int root = find(num1, map);
	            if (map[root] != num2) {
	                map[root] = num2;
	                n--;
	            }
	        }
	        return n == 1;
	    }
	    
	    private int find(int key, int[] roots) {
System.out.println("key=" + key + ", roots=" + Arrays.toString(roots));
	        int val = roots[key];
	        return val == key ? val : find(val, roots);
	    }
	}
	
	
	public static class SolutionII extends GraphValidTree {
		public boolean validTree(int n, int[][] edges) {
	        if (edges.length + 1 != n)
	            return false;
	        if (edges.length == 0)
	            return true;
	        Map<Integer, List<Integer>> nodeNbs = new HashMap<>();
	        for (int i = 0; i < n; nodeNbs.put(i++, new LinkedList<>()));
	        for (int[] e : edges) {
	            nodeNbs.get(e[0]).add(e[1]);
	            nodeNbs.get(e[1]).add(e[0]);
	        }
	        Set<Integer> used = new HashSet<>();
	        List<Integer> list = Arrays.asList(edges[0][0]);
	        used.add(edges[0][0]);
	        while (!list.isEmpty()) {
	            List<Integer> nextList = new LinkedList<>();
	            for (Integer node : list) {
	                List<Integer> nbs = nodeNbs.get(node);
	                for (Integer nb : nbs) {
	                    if (used.add(nb))
	                        nextList.add(nb);
	                }
	            }
	            list = nextList;
	        }
	        
	        return used.size() == n;
	    }
	}
}
