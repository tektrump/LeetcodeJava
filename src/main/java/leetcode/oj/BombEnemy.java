package leetcode.oj;


import leetcode.util.ArrayUtil;

public abstract class BombEnemy {
	public abstract int maxKilledEnemies(char[][] grid);
	public static void main(String[] args) {
		BombEnemy instance = new SolutionI();
		char[][] grid;
		int result;
		
		grid = ArrayUtil.strArrayTo2DCharArray(new String[]{"0E00","E0WE","0E00"});
		
		result = instance.maxKilledEnemies(grid);
		System.out.println("result=" + result);
	}
	
	
	static class SolutionI extends BombEnemy {
		public int maxKilledEnemies(char[][] grid) {
	        if (grid == null || grid.length == 0 || grid[0].length == 0)
	            return 0;
	        int rows = grid.length, cols = grid[0].length;
	        int[][] lefts = new int[rows][], rights = new int[rows][];
	        int[][] ups = new int[rows][], downs = new int[rows][];
	        for (int r = 0; r < rows; r++) {
	            lefts[r] = new int[cols];
	            rights[r] = new int[cols];
	            ups[r] = new int[cols];
	            downs[r] = new int[cols];
	        }
	        for (int r = 0; r < rows; r++) {
	            int left = 0;
	            for (int c = 0; c < cols; c++) {
	                lefts[r][c] = left;
	                if (grid[r][c] == 'E')
	                    left++;
	                else if (grid[r][c] == 'W')
	                    left = 0;
	            }
	            int right = 0;
	            for (int c = cols-1; c >= 0; c--) {
	                rights[r][c] = right;
	                if (grid[r][c] == 'E')
	                    right++;
	                else if (grid[r][c] == 'W')
	                    right = 0;
	            }
	        }
	        for (int c = 0; c < cols; c++) {
	            int up = 0;
	            for (int r = 0; r < rows; r++) {
	                ups[r][c] = up;
	                if (grid[r][c] == 'E')
	                    up++;
	                else if (grid[r][c] == 'W')
	                    up = 0;
	            }
	            int down = 0;
	            for (int r = rows-1; r >= 0; r--) {
	                downs[r][c] = down;
	                if (grid[r][c] == 'E')
	                    down++;
	                else if (grid[r][c] == 'W')
	                    down = 0;
	            }
	        }
	        int max = 0;
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (grid[r][c] == '0') {
	                    int count = lefts[r][c] + rights[r][c] + ups[r][c] + downs[r][c];
	                    max = Math.max(max, count);
	                }
	            }
	        }
	        return max;
	    }
	}
}
