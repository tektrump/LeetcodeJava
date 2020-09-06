package tektrup.leetcode.oj;


import tektrup.leetcode.util.ArrayUtil;
import tektrup.leetcode.util.LeetcodeUtils;

public abstract class BestTimeToBuyAndSellStockIV {
	public abstract int maxProfit(int k, int[] prices);
	public static void main(String[] args) {
		BestTimeToBuyAndSellStockIV instance = new SolutionV();
		int k; int[] prices;
		int result;
		long t1, t2;
		
		// 0
//		k = 2;
//		prices = new int[]{};
		
		// 0
//		k = 0;
//		prices = new int[]{1, 3};
		
		// 7
//		k = 2;
//		prices = new int[]{6,1,3,2,4,7};
		
		// 1649061
		k = 1000000000;
		prices = ArrayUtil.str2intArray(LeetcodeUtils.readText(instance));
		
		t1 = System.currentTimeMillis();
		result = instance.maxProfit(k, prices);
		t2 = System.currentTimeMillis();
		System.out.println(String.format("result=%d, time=%,dms", result, (t2 - t1)));
	}
	
	
	static class SolutionV extends BestTimeToBuyAndSellStockIV {
		public int maxProfit(int k, int[] prices) {
	        if (prices == null || prices.length == 0 || k == 0)
	            return 0;
	        int n = prices.length;
	        if (k >= n/2)
	            return quick(prices);
	            
	        int[] curr = new int[n];
	        int max = Integer.MIN_VALUE;
	        int sum = 0, maxSum = Integer.MIN_VALUE;
	        for (int c = 1; c < n; c++) {
	            sum += prices[c] - prices[c-1];
	            if (sum < 0) sum = 0;
	            maxSum = Math.max(maxSum, sum);
	            curr[c] = maxSum;
	        }
	        for (int r = 1; r < k; r++) {
	            int[] next = new int[n];
	            max = Integer.MIN_VALUE;
	            // col=0 always has val=0; that is: dp[r][0]=0 for all r
	            for (int c = 1; c < n; c++) {
	                max = Math.max(max, curr[c-1] - prices[c-1]);
	                next[c] = Math.max(next[c-1], max + prices[c]);
	            }
	            curr = next;
	        }
	        return curr[n-1];
	    }
	    
	    private int quick(int[] prices) {
	        int profit = 0;
	        for (int i = 1; i < prices.length; i++)
	            profit += Math.max(0, prices[i]-prices[i-1]);
	        return profit;
	    }
	}
	
	
	// Solution IV: TLE with special case
	static class SolutionIV extends BestTimeToBuyAndSellStockIV {
		public int maxProfit(int k, int[] prices) {
	        if (prices == null || prices.length == 0 || k == 0)
	            return 0;
	        int n = prices.length;
	        int[] curr = new int[n];
	        int max = Integer.MIN_VALUE;
	        int sum = 0, maxSum = Integer.MIN_VALUE;
	        for (int c = 1; c < n; c++) {
	            sum += prices[c] - prices[c-1];
	            if (sum < 0) sum = 0;
	            maxSum = Math.max(maxSum, sum);
	            curr[c] = maxSum;
	        }
	        for (int r = 1; r < k; r++) {
	            int[] next = new int[n];
	            max = Integer.MIN_VALUE;
	            // col=0 always has val=0; that is: dp[r][0]=0 for all r
	            for (int c = 1; c < n; c++) {
	                max = Math.max(max, curr[c-1] - prices[c-1]);
	                next[c] = Math.max(next[c-1], max + prices[c]);
	            }
	            curr = next;
	        }
	        return curr[n-1];
	    }
	}
	
	
	// Solution III: TLE
	static class SolutionIII extends BestTimeToBuyAndSellStockIV {
		public int maxProfit(int k, int[] prices) {
	        if (prices == null || prices.length <= 1 || k <= 0)
	            return 0;
	        int len = prices.length;
	        int[][] maxes = new int[len][len];
	        for (int i = 0; i < prices.length; i++) {
	            int sum = 0, max = 0;
	            for (int j = i; j < prices.length; j++) {
	                if (j-1 < 0)
	                    continue;
	                sum += prices[j] - prices[j-1];
	                max = Math.max(max, sum);
	                maxes[i][j] = max;
	                sum = Math.max(0, sum);
	            }
	        }
	        int[] curr = new int[len];
	        for (int i = 0; i < len; i++)
	            curr[i] = maxes[0][i];
	        k--;
	        while (k > 0) {
	            int[] next = new int[len];
	            for (int i = 0; i < len; i++) {
	                int max = 0;
	                for (int j = 0; j+1 <= i; j++) {
	                    max = Math.max(max, curr[j] + maxes[j+1][i]);
	                }
	                next[i] = max;
	            }
	            curr = next;
	            k--;
	        }
	        return curr[curr.length-1];
	    }
	}
	
	
	// Solution II: Memory Limited Exceeded
	static class SolutionII extends BestTimeToBuyAndSellStockIV {
		public int maxProfit(int k, int[] prices) {
	        if (prices == null || prices.length == 0 || k == 0)
	            return 0;
	        int[] profits = new int[prices.length];
	        for (int i = 1; i < prices.length; profits[i] = prices[i] - prices[i-1], i++);
	        int[] maxes = new int[profits.length];
	        int sum = 0, max = Integer.MIN_VALUE;
	        for (int i = profits.length-1; i >= 0; i--) {
	            sum += profits[i];
	            max = Math.max(max, sum);
	            maxes[i] = max;
	            if (sum < 0)
	                sum = 0;
	        }
	        
	        int[] dp = new int[prices.length];
	        max = Integer.MIN_VALUE;
	        sum = 0;
	        for (int i = 0; i < profits.length; i++) {
	            sum += profits[i];
	            max = Math.max(max, sum);
	            dp[i] = Math.max(0, max);
	            if (sum < 0)
	                sum = 0;
	        }
	        
	        for (int r = 1; r < k; r++) {
	            int[] dp2 = new int[profits.length];
	            for (int c = 0; c < profits.length; c++) {
	                int prev = 0;
	                if (c > 0)
	                    prev = dp[c-1];
	                dp2[c] = prev;
	                if (c + 1 < maxes.length)
	                    dp2[c] += maxes[c+1];
	            }
	            dp = dp2;
	        }
	        
	        return dp[prices.length-1];
	    }
	}
}
