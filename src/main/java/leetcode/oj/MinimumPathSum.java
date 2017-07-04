package leetcode.oj;


import java.util.Arrays;

import leetcode.util.ArrayUtil;

public abstract class MinimumPathSum {
	public abstract int minPathSum(int[][] grid);
	public static void main(String[] args) {
		MinimumPathSum instance = new SolutionII();
		int[][] grid;
		int res;
		
		grid = ArrayUtil.str2int2DArray("[[1,2],[1,1]]");
		
		res = instance.minPathSum(grid);
		System.out.println("result=" + res);
	}
	
	static class SolutionII extends MinimumPathSum {
		public int minPathSum(int[][] grid) {
	        if (grid == null || grid.length == 0 || grid[0].length == 0)
	            return 0;
	        int rows = grid.length, cols = grid[0].length;
	        int[] prev = new int[cols];
	        Arrays.fill(prev, Integer.MAX_VALUE);
	        for (int r = 0; r < rows; r++) {
if (r == 1)
	System.out.println();
	            int[] curr = new int[cols];
	            int left = Integer.MAX_VALUE;
	            for (int c = 0; c < cols; c++) {
	                curr[c] = Math.min(prev[c], left) + grid[r][c];
	                left = curr[c];
	            }
	            prev = curr;
System.out.println(Arrays.toString(curr));
	        }
	        
	        return prev[cols-1];
	    }
	}
}
