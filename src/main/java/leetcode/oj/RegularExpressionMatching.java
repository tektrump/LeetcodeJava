package leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class RegularExpressionMatching {
	public abstract boolean isMatch(String s, String p);
	public static void main(String[] args) {
		RegularExpressionMatching instance = new SolutionI();
		String s, p;
		boolean result;
		
//		s = "aa"; p = "a"; // false
		
//		s = "aab"; p = "c*a*b"; // true
		
//		s = "abcd"; p = "d*"; // false
		
//		s = "aaa"; p = "a.a"; // true
		
		s = "a"; p = "ab*"; // true
		
		result = instance.isMatch(s, p);
		System.out.println("result=" + result);
	}
	
	
	static class SolutionI extends RegularExpressionMatching {
		public boolean isMatch(String s, String p) {
	        List<Character> pchars = new ArrayList<>();
	        List<Boolean> wilds = new ArrayList<>();
	        for (int i = 0; i < p.length(); i++) {
	            char ch = p.charAt(i);
	            pchars.add(ch);
	            if (i + 1 < p.length() && p.charAt(i+1) == '*') {
	                wilds.add(true);
	                i++;
	            } else {
	                wilds.add(false);
	            }
	        }
	        int rows = pchars.size() + 1, cols = s.length() + 1;
	        // dp[r][c] ~ s.charAt(r-1), p.charAt(c-1)
	        // if dp[r][c] is true, try to find next matches
	        Boolean[][] dp = new Boolean[rows][cols];
	        dp[0][0] = true;
	        for (int r = 0; r < rows-1; r++) {
	            for (int c = 0; c < cols; c++) {
if (r == 2 && c == 1)
	System.out.println();
	                if (dp[r][c] != null && dp[r][c]) {
	                    char pchar = pchars.get(r); // next pchar
	                    boolean wild = wilds.get(r);
	                    if (wild) {
	                        dp[r+1][c] = true;
	                        int cc = c + 1;
	                        while (cc < cols && (pchar == '.' || s.charAt(cc-1) == pchar)) {
	                            dp[r+1][cc] = true;
	                            cc++;
	                        }
	                    } else {
	                        if (c + 1 < cols)
	                            dp[r+1][c+1] = (pchar == '.' || pchar == s.charAt(c));
	                    }
	                }
	            }
	        }
for (Boolean[] row : dp)
	System.out.println(Arrays.toString(row));
	        return dp[rows-1][cols-1] != null && dp[rows-1][cols-1];
	    }
	}
}
