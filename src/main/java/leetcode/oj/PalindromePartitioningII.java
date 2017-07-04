package leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class PalindromePartitioningII {
	public abstract int minCut(String s);
	public static void main(String[] args) {
		PalindromePartitioningII instance = new SolutionV();
		String s;
		int result;
		long t1, t2;
		
		// 75
		s = "fifgbeajcacehiicccfecbfhhgfiiecdcjjffbghdidbhbdbfbfjccgbbdcjheccfbhafehieabbdfeigbiaggchaeghaijfbjhi";
		
		t1 = System.currentTimeMillis();
		result = instance.minCut(s);
		t2 = System.currentTimeMillis();
		System.out.println(String.format("result=%d, total time=%,dms", result, (t2-t1)));
	}
	
	
	static class SolutionV extends PalindromePartitioningII {
		public int minCut(String s) {
	        if (s == null || s.isEmpty())
	            return 0;
	        int len = s.length();
	        boolean[][] dp = new boolean[len][];
	        for (int r = 0; r < len; dp[r++] = new boolean[len]);
	        for (int r = 0; r < len; r++) {
	            for (int l = r; l >= 0; l--) {
	                if (s.charAt(l) == s.charAt(r) && (r-l <= 1 || dp[l+1][r-1]))
	                    dp[l][r] = true;
	            }
	        }
	        // bfs
	        int cut = 0;
	        List<int[]> ranges = new ArrayList<>();
	        ranges.add(new int[]{0, len-1});
	        while (true) {
	            List<int[]> nextRanges = new ArrayList<>();
	            for (int[] range : ranges) {
	                int l = range[0], r = range[1];
	                if (dp[l][r])
	                    return cut;
	                for (int d = l; d < r; d++) {
	                    if (dp[l][d])
	                        nextRanges.add(new int[]{d+1, r});
	                }
	            }
	            ranges = nextRanges;
	            cut++;
	        }
	    }
	}
	
	
	// Solution IV: Accepted
	static class SolutionIV extends PalindromePartitioningII {
		public int minCut(String s) {
	        if (s == null || s.isEmpty())
	            return 0;
	        int[] cuts = new int[s.length()];
	        Arrays.fill(cuts, Integer.MAX_VALUE);
	        boolean[][] dp = new boolean[s.length()][];
	        for (int r = 0; r < s.length(); dp[r++] = new boolean[s.length()]);
	        for (int r = 0; r < s.length(); r++) {
	            for (int l = r; l >= 0; l--) {
	                if (s.charAt(l) == s.charAt(r) && (r-l <= 1 || dp[l+1][r-1])) {
	                    dp[l][r] = true;
	                    if (l-1 >= 0)
	                        cuts[r] = Math.min(cuts[r], cuts[l-1] + 1);
	                    else
	                        cuts[r] = 0;
	                }
	            }
	        }
	        return cuts[cuts.length-1];
	    }
	}
}
