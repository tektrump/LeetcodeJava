package leetcode.oj;


import leetcode.util.ArrayUtil;

public abstract class MaximalRectangle {
	public abstract int maximalRectangle(char[][] matrix);
	public static void main(String[] args) {
		MaximalRectangle instance = new SolutionII();
		char[][] matrix;
		int max;
		
		
//		matrix = ArrayUtil.strArrayTo2DCharArray(new String[]{"01", "01"}); // 2
		matrix = ArrayUtil.strArrayTo2DCharArray(new String[]{"11111111","11111110","11111110","11111000","01111000"}); // 21
		
		max = instance.maximalRectangle(matrix);
		System.out.println("max=" + max);
	}
	
	
	static class SolutionII extends MaximalRectangle {
		public int maximalRectangle(char[][] matrix) {
	        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
	            return 0;
	        int rows = matrix.length, cols = matrix[0].length;
	        int[][] dp = new int[rows][cols];
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                int prev = 0;
	                if (c - 1 >= 0)
	                    prev = dp[r][c-1];
	                if (matrix[r][c] == '1')
	                    dp[r][c] = prev + 1;
	            }
	        }
	        int max = 0;
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (dp[r][c] > 0 && (c+1 == cols || dp[r][c+1] == 0)) { // local max
	                    int area = dp[r][c];
	                    int rr = r;
	                    while (rr-1 >= 0 && dp[rr-1][c] >= dp[r][c]) {
	                        area += dp[r][c];
	                        rr--;
	                    }
	                    rr = r;
	                    while (rr+1 < rows && dp[rr+1][c] >= dp[r][c]) {
	                        area += dp[r][c];
	                        rr++;
	                    }
	                    max = Math.max(max, area);
	                }
	            }
	        }
	        return max;
	    }
	}
	
	
	static class SolutionI extends MaximalRectangle {
		public int maximalRectangle(char[][] matrix) {
	        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
	            return 0;
	        int rows = matrix.length, cols = matrix[0].length;
	        int[][] counts = new int[rows][];
	        for (int r = 0; r < rows; counts[r++] = new int[cols]);
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (matrix[r][c] == '1') {
	                    if (c == 0)
	                        counts[r][c] = 1;
	                    else
	                        counts[r][c] = counts[r][c-1] + 1;
	                }
	            }
	        }
	        int max = 0;
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (r-1 < 0 || counts[r][c] > counts[r-1][c]) {
	                    int w = Integer.MAX_VALUE, h = 0;
	                    int currR = r;
	                    while (currR < rows && (w = Math.min(w, counts[currR][c])) > 0) {
	                        h++;
	                        max = Math.max(max, w*h);
	                        currR++;
	                    }
	                }
	            }
	        }
	        return max;
	    }
	}
}
