package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tektrup.leetcode.util.ArrayUtil;

public abstract class PaintHouseII {
	public abstract int minCostII(int[][] costs);
	public static void main(String[] args) {
		PaintHouseII instance = new SolutionI();
		int[][] costs;
		int result;
		long t1, t2;
		
		// 136?
		costs = ArrayUtil.str2int2DArray("[[3,2,6,20,9,20,15,17,10,3,8,5,3,18,15,7,6],[20,18,13,6,14,14,1,8,17,18,20,19,3,17,11,3,3],[17,12,2,17,14,19,14,10,6,10,3,3,14,9,15,10,5],[2,11,8,3,12,6,18,1,3,15,13,19,12,8,11,14,9],[4,16,15,10,4,10,15,13,17,17,9,15,5,10,9,17,13],[12,6,14,18,3,11,17,7,4,5,5,5,2,12,6,8,8],[10,20,13,6,20,11,4,16,8,1,4,8,18,4,1,12,11],[5,5,8,7,17,2,5,18,2,15,18,18,13,17,10,11,15],[12,10,15,12,6,1,2,7,15,1,9,15,14,7,2,18,15],[10,7,8,13,11,12,2,10,13,14,4,16,12,2,15,18,7],[13,12,11,14,5,5,7,4,16,20,20,9,8,5,19,12,6],[6,19,3,14,19,5,6,7,20,8,5,12,14,3,4,7,10],[9,6,12,18,3,13,4,1,1,11,7,13,14,14,4,19,1],[4,13,7,4,6,1,17,18,1,15,15,10,18,19,3,17,10],[8,19,8,5,2,16,18,12,12,8,7,4,1,7,2,2,3],[19,13,16,20,15,6,17,4,5,11,15,14,11,13,20,7,12],[14,7,20,7,10,13,16,12,3,7,5,1,19,1,17,7,13],[12,20,1,7,12,8,1,7,17,5,10,1,11,2,9,12,11],[20,7,3,12,9,14,1,2,12,15,12,2,7,7,15,2,17],[16,18,6,1,19,18,16,11,16,1,11,1,6,15,6,12,15],[17,19,13,10,2,14,6,7,4,12,9,10,9,7,19,16,10],[8,5,11,3,6,11,9,20,9,9,14,10,15,14,5,17,2],[10,11,2,10,11,14,4,7,16,2,3,2,18,5,8,9,6],[12,19,17,11,16,6,20,4,18,17,2,9,11,19,19,2,11],[20,10,15,4,15,4,14,17,10,4,15,11,15,13,2,4,14],[20,18,14,8,16,16,6,6,8,17,10,18,8,8,4,1,14],[19,12,6,14,1,12,14,11,20,5,12,13,12,1,15,12,4],[15,2,10,11,2,11,12,13,2,7,4,20,15,4,15,13,20],[4,17,11,19,2,17,2,5,19,4,4,4,2,16,16,19,15],[9,3,10,20,6,11,15,17,16,14,16,10,4,19,2,6,2],[10,5,11,18,6,17,19,2,14,13,16,4,12,5,16,1,18],[17,10,2,2,16,1,7,1,3,6,17,19,11,12,12,17,8],[16,12,10,6,5,3,10,19,17,8,15,7,16,11,2,9,1],[5,10,15,11,13,11,11,1,3,11,14,13,3,19,2,18,9],[4,20,20,8,12,20,16,5,2,7,20,10,2,12,20,10,19],[2,6,10,11,14,3,5,9,1,18,4,10,4,12,13,6,14],[17,16,4,6,18,10,19,14,15,18,18,11,7,2,10,14,14],[3,2,18,3,7,15,19,14,18,13,10,11,2,2,18,3,4],[6,18,10,8,15,4,18,16,2,6,16,19,5,3,4,11,2],[20,2,6,16,11,2,17,8,19,15,2,7,18,15,19,14,20],[5,10,10,16,13,6,14,12,17,20,9,20,12,3,8,3,11],[5,15,6,17,9,15,17,11,20,13,11,16,1,12,19,19,4],[1,16,15,3,10,18,6,2,3,3,5,17,12,9,3,4,14],[16,4,15,10,2,3,17,8,17,7,2,13,9,11,18,6,14],[13,20,6,13,16,7,5,14,8,7,3,8,1,19,20,8,10],[6,13,3,17,4,4,11,4,11,11,12,5,17,14,7,14,15],[18,11,2,18,16,14,4,17,3,14,6,19,17,8,16,11,5],[8,10,1,9,15,14,15,20,9,11,4,14,15,15,15,6,4],[13,7,9,7,18,5,18,9,9,19,5,14,10,12,4,5,9],[8,3,7,10,18,10,12,10,7,9,6,19,4,18,2,13,7],[3,8,2,1,2,13,19,20,13,19,19,19,7,13,20,6,14],[8,19,9,19,17,9,7,14,11,3,7,11,12,9,17,5,7],[15,9,5,4,4,8,19,7,16,20,10,14,15,2,14,6,14],[3,9,16,18,18,7,20,6,11,11,13,14,5,9,10,2,15],[14,8,11,6,8,18,12,14,6,14,11,9,11,1,18,12,18],[20,1,19,15,11,5,8,12,6,19,19,6,14,6,17,8,7],[9,15,4,4,4,1,9,14,8,9,14,12,8,4,5,6,19],[3,10,17,16,3,12,11,9,12,12,19,11,18,13,19,9,13],[12,12,3,1,13,6,17,13,1,4,12,4,2,6,3,8,19],[6,14,19,5,19,4,3,7,1,3,15,8,13,20,15,10,11],[6,2,5,17,11,10,13,13,13,12,9,4,11,5,12,9,8],[8,17,20,15,5,3,19,20,19,13,8,5,5,16,17,17,1],[12,13,9,2,15,3,16,14,10,7,20,15,16,12,13,11,14],[4,18,1,8,3,14,6,20,14,5,16,20,2,15,10,12,12],[20,8,10,6,12,20,17,9,8,17,4,9,10,3,3,4,6],[11,2,3,14,17,1,13,16,3,16,13,20,11,12,15,11,12],[13,2,2,5,2,4,3,11,18,9,15,20,4,20,15,3,5],[16,20,18,12,2,13,10,14,4,10,2,20,6,19,10,14,14],[1,17,9,1,1,17,11,12,11,7,9,8,20,10,13,6,19],[7,9,5,8,5,4,9,6,14,12,16,12,15,12,15,14,1],[18,12,5,4,11,9,14,2,18,16,18,19,10,16,20,20,20],[2,14,5,17,5,11,16,12,7,13,7,7,17,4,12,13,17],[7,11,3,14,9,16,19,7,12,19,1,16,6,4,14,5,15],[10,5,4,3,15,20,13,6,20,10,16,17,7,15,19,12,14],[17,9,20,14,12,18,7,20,16,8,14,11,5,5,19,10,16],[6,14,15,20,7,16,20,15,20,10,8,18,19,12,2,14,11]]");
		t1 = System.currentTimeMillis();
		result = instance.minCostII(costs);
		t2 = System.currentTimeMillis();
		System.out.println(String.format("result=%d, total time=%,dms", result, (t2 - t1)));
	}
	
	
	static class SolutionII extends PaintHouseII {
		public int minCostII(int[][] costs) {
	        if (costs == null || costs.length == 0 || costs[0].length == 0)
	            return 0;
	        int n = costs.length, k = costs[0].length;
	        int[][] mins = new int[costs.length][];
	        int[] minColors = new int[costs.length];
	        for (int i = 0; i < n; i++) {
	            int[] min = {Integer.MAX_VALUE, Integer.MAX_VALUE};
	            for (int j = 0; j < k; j++) {
	                int cost = costs[i][j];
	                if (cost < min[0]) {
	                    min[0] = cost;
	                    minColors[i] = j;
	                } else if (cost < min[1]) {
	                    min[1] = cost;
	                }
	            }
	            mins[i] = min;
	        }
	        
	        int[][] dp = new int[n][];
	        for (int i = 0; i < n; dp[n] = new int[n], i++);
	        List<Integer> list0 = new ArrayList<>();
	        list0.add(null);
	        list0.add(null);
	        Map<List<Integer>, Integer> map = new HashMap<>();
	        map.put(list0, 0);
	        int m = 0;
	        while (m < n) {
	            Map<List<Integer>, Integer> nextMap = new HashMap<>();
	            for (Map.Entry<List<Integer>, Integer> entry : map.entrySet()) {
	            	List<Integer> list = entry.getKey();
	            	int cost = entry.getValue();
	                Integer repeat = null;
	                if (list.get(0) == list.get(1))
	                    repeat = list.get(0);
	                for (int color = 0; color < k; color++) {
	                    List<Integer> nextList = new ArrayList<>(2);
	                    nextList.add(color); // curr
	                    nextList.add(list.get(0)); // prev
	                    int nextCost;
	                    // if (minColors[m] != repeat) // SYNTAX ERROR
	                    if (repeat == null || minColors[m] != repeat)
	                        nextCost = cost + mins[m][0];
	                    else
	                        nextCost = cost + mins[m][1];
	                    Integer prev = nextMap.get(nextList);
	                    if (prev == null || prev > nextCost)
	                        nextMap.put(nextList, nextCost); // keep the min cost for each key
	                }
	            }
	            map = nextMap;
	            m++;
	        }
	        int max = 0;
	        for (int cost : map.values())
	            max = Math.max(max, cost);
	        return max;
	    }
	}
	
	
	// Solution I: TLE
	static class SolutionI extends PaintHouseII {
		public int minCostII(int[][] costs) {
	        if (costs == null || costs.length == 0 || costs[0].length == 0)
	            return 0;
	        int n = costs.length, k = costs[0].length;
	        int[][] mins = new int[costs.length][];
	        int[] minColors = new int[costs.length];
	        for (int i = 0; i < n; i++) {
	            int[] min = {Integer.MAX_VALUE, Integer.MAX_VALUE};
	            for (int j = 0; j < k; j++) {
	                int cost = costs[i][j];
	                if (cost < min[0]) {
	                    min[0] = cost;
	                    minColors[i] = j;
	                } else if (cost < min[1]) {
	                    min[1] = cost;
	                }
	            }
	            mins[i] = min;
	        }
	        
	        List<Integer> list0 = new ArrayList<>();
	        list0.add(null);
	        list0.add(null);
	        list0.add(0);
	        List<List<Integer>> lists = new ArrayList<>();
	        lists.add(list0);
	        int m = 0;
	        while (m < n) {
	            List<List<Integer>> nextLists = new ArrayList<>();
	            Map<List<Integer>, Integer> map = new HashMap<>();
	            for (List<Integer> list : lists) {
	                Integer repeat = null;
	                if (list.get(0) == list.get(1))
	                    repeat = list.get(0);
	                int cost = list.get(2);
	                for (int color = 0; color < k; color++) {
	                    List<Integer> key = new ArrayList<>(2);
	                    key.add(color); // curr
	                    key.add(list.get(0)); // prev
	                    int nextCost;
	                    // if (minColors[m] != repeat) // SYNTAX ERROR
	                    if (repeat == null || minColors[m] != repeat)
	                        nextCost = cost + mins[m][0];
	                    else
	                        nextCost = cost + mins[m][1];
	                    Integer val = map.get(key);
	                    if (val == null || val > nextCost)
	                        map.put(key, nextCost); // keep the min cost for each key
	                }
	            }
	            for (Map.Entry<List<Integer>, Integer> entry : map.entrySet()) {
	                List<Integer> nextList = new ArrayList<>(entry.getKey());
	                nextList.add(entry.getValue());
	                nextLists.add(nextList);
	            }
	            lists = nextLists;
	            m++;
	        }
	        
	        int max = 0;
	        for (List<Integer> list : lists)
	            max = Math.max(max, list.get(2));
	        return max;
	    }
	}
}
