package leetcode.oj;


import java.util.Arrays;

import leetcode.util.ArrayUtil;

public abstract class PaintHouse {
    public abstract int minCost(int[][] costs);
    public static void main(String[] args) {
    	PaintHouse instance = new SolutionI();
    	int[][] costs;
    	int res;
    	
    	costs = ArrayUtil.str2int2DArray("[[7,6,2]]");
    	
    	res = instance.minCost(costs);
    	System.out.println("result=" + res);
	}

    static class SolutionI extends PaintHouse {
    	public int minCost(int[][] costs) {
            if (costs.length == 0)
                return 0;
            int[][] cache = new int[costs.length][3];
            for (int[] row : cache)
                Arrays.fill(row, -1);
            return dp(-1, 0, costs, cache);
        }
        
        private int dp(int prevK, int i, int[][] costs, int[][] cache) {
            if (i == costs.length) // termination
                return 0;
                
            int ret = -1;
            if (prevK >= 0)
                ret = cache[i][prevK];
            if (ret >= 0)
                return ret;
                
            ret = Integer.MAX_VALUE;
            for (int k = 0; k < 3; k++) {
                if (k != prevK) {
                    int cost = costs[i][k];
                    cost += dp(k, i+1, costs, cache);
                    ret = Math.min(ret, cost);
                }
            }
            if (prevK >= 0)
                cache[i][prevK] = ret;
            return ret;
        }
    }
}
