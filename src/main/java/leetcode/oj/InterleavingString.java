package leetcode.oj;


import java.util.Arrays;

public abstract class InterleavingString {
	public abstract boolean isInterleave(String s1, String s2, String s3);
	public static void main(String[] args) {
		InterleavingString instance = new SolutionIII();
		String s1, s2, s3;
		boolean ret;
		
		s1 = "ab";
		s2 = "bc";
		s3 = "bbac";
		
		ret = instance.isInterleave(s1, s2, s3);
		System.out.println("result=" + ret);
	}
	
	static class SolutionIII extends InterleavingString {
		public boolean isInterleave(String s1, String s2, String s3) {
	        if (s1.length() + s2.length() != s3.length())
	            return false;
	        int rows = s1.length() + 1;
	        int cols = s2.length() + 1;
	        boolean[][] dp = new boolean[rows][cols];
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (r == 0 && c == 0) {
	                    dp[r][c] = true;
	                    continue;
	                }
	                boolean upper = r-1 >= 0 ? dp[r-1][c] : false;
	                boolean left  = c-1 >= 0 ? dp[r][c-1] : false;
	                char ch3 = s3.charAt(r+c-1);
if (r == 0 && c == 1)
	System.out.println();
	                if ((upper || left) && ((r-1 >= 0 && s1.charAt(r-1) == ch3) || (c-1 >= 0 && s2.charAt(c-1) == ch3)))
	                    dp[r][c] = true;
	            }
	        }
	        
for (boolean[] row : dp)
	System.out.println(Arrays.toString(row));
	        return dp[rows-1][cols-1];
	    }
	}
}
