package tektrup.leetcode.oj;


public abstract class WildcardMatching {
	public abstract boolean isMatch(String s, String p);
	public static void main(String[] args) {
		WildcardMatching instance = new SolutionI();
		String s, p;
		boolean result;
		
		s = "aa"; p = "a"; // false
		
		s = "c"; p = "*?*"; // false
		
		result = instance.isMatch(s, p);
		System.out.println("result=" + result);
	}
	
	static class SolutionI extends WildcardMatching {
		public boolean isMatch(String s, String p) {
	        int rows = s.length()+1, cols = p.length()+1;
	        boolean[][] dp = new boolean[rows][cols];
	        dp[0][0] = true;
	        for (int r = 0; r < rows-1; r++) {
	            for (int c = 0; c < cols-1; c++) {
	                if (dp[r][c]) {
	                    char schar = s.charAt(r);
	                    char pchar = p.charAt(c);
	                    if (pchar == '*') {
	                        int rr = r + 1;
	                        while (rr < rows) {
	                            dp[rr++][c+1] = true;
	                        }
	                    } else if (pchar == '?' || schar == pchar)
	                        dp[r+1][c+1] = true;
	                }
	            }
	        }
	        // specal treatment of last row
	        int lastR = rows - 1;
	        for (int c = 0; c < p.length(); c++) {
	            if (dp[lastR][c] && p.charAt(c) == '*')
	                dp[lastR][c+1] = true;
	        }
	        return dp[rows-1][cols-1];
	    }
	}
}
