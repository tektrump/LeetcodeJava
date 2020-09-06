package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tektrup.leetcode.util.ArrayUtil;

public abstract  class ShortestDistanceFromAllBuildings {
	public abstract int shortestDistance(int[][] grid);
    public static void main(String[] args) {
    	ShortestDistanceFromAllBuildings instance = new SolutionIII();
    	int[][] grid;
    	
    	// 7
//    	grid = new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
    	
    	// 2
//    	grid = new int[][]{{2,0,0},{0,1,0},{1,0,0}};
    	
    	// 88
//    	grid = ArrayUtil.str2int2DArray("[[1,1,1,1,1,0],[0,0,0,0,0,1],[0,1,1,0,0,1],[1,0,0,1,0,1],[1,0,1,0,0,1],[1,0,0,0,0,1],[0,1,1,1,1,0]]");
    	
    	// -1
//    	grid = new int[][]{{1}};
    	
    	String s = "[[0,2,2,0,0,0,0,2,0,0,2,2,0,2,2,0,0,0,0,0,0,0,2,0,0,0,2,0,2,2,0,0,0,2,0,0,2,0,0,0,2,0,0,2,2,0],[2,2,0,0,0,2,0,0,0,0,0,2,0,2,2,0,2,2,2,2,0,0,0,2,0,0,0,0,0,0,0,0,2,2,0,2,2,0,0,0,0,0,2,2,2,0],[0,0,0,0,2,0,0,0,0,0,1,2,2,0,1,0,0,2,0,0,0,0,2,0,0,0,0,1,2,2,0,1,2,0,0,0,1,0,0,0,0,0,0,2,0,0],[2,0,0,2,0,0,2,0,0,1,2,0,2,2,0,2,0,0,0,0,2,0,0,2,0,0,0,2,2,0,0,2,0,2,0,1,2,0,0,0,0,0,2,2,2,0],[2,0,2,0,1,0,0,0,2,0,0,2,2,0,2,2,2,0,0,1,2,2,0,0,1,0,2,1,2,0,2,2,0,1,0,0,0,0,2,0,0,2,0,2,0,0],[2,2,2,2,2,2,0,0,0,0,0,2,0,0,1,0,2,0,0,2,0,0,2,0,2,2,0,0,2,2,2,0,0,2,2,2,2,0,1,2,0,0,2,0,0,0],[2,2,0,0,0,0,2,0,0,0,0,0,0,2,0,2,2,2,1,0,0,0,2,2,0,2,0,0,0,2,0,2,2,2,2,0,0,0,0,0,0,2,1,2,0,0],[2,0,2,2,0,0,0,0,2,0,0,0,2,0,0,0,0,0,1,0,2,1,0,2,2,0,0,0,0,2,1,0,0,0,0,2,0,2,2,0,2,0,2,2,2,0],[0,0,2,2,2,2,2,1,2,2,0,2,0,0,0,0,0,2,0,2,0,0,0,0,0,2,0,2,0,2,1,0,0,0,2,2,0,0,2,1,0,0,0,0,0,0],[0,0,0,2,0,2,0,2,0,2,2,2,0,0,0,0,0,0,0,0,2,0,2,2,1,0,2,2,0,2,2,2,2,0,2,2,0,2,0,0,0,0,0,0,0,0],[2,0,0,1,2,2,1,0,2,2,0,0,2,0,2,1,0,0,2,0,0,0,2,0,2,1,0,0,0,0,2,0,0,2,0,0,1,0,0,0,2,0,1,2,2,0],[2,1,2,1,2,2,0,0,0,0,0,2,0,0,2,0,2,2,1,2,0,2,1,2,2,0,0,0,0,0,0,0,0,0,2,0,0,0,2,2,0,0,0,0,2,2],[0,0,0,0,0,1,0,0,2,0,0,2,0,2,0,2,2,0,2,0,1,2,2,2,0,2,2,0,0,0,2,2,2,0,2,0,0,0,2,0,0,0,2,0,0,2],[0,0,0,0,0,0,2,0,0,2,0,0,0,2,0,1,0,0,2,0,0,0,2,0,1,0,2,0,1,0,2,2,1,0,0,1,2,0,2,0,0,0,2,0,0,0],[2,2,0,0,0,0,0,2,2,0,2,2,2,2,0,0,0,2,0,0,0,0,2,0,2,2,2,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,1,2,0,0],[2,1,2,2,2,1,2,0,2,0,0,2,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,2,0,2,0,2,0,2,2],[0,2,0,0,1,0,1,0,1,0,2,2,2,0,2,2,2,2,0,2,0,0,2,2,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,2,2,0],[2,2,0,2,0,0,2,2,2,0,0,0,0,0,2,2,2,0,0,0,1,0,2,0,2,0,0,1,0,0,0,0,0,2,0,0,0,1,0,0,2,0,0,0,0,0],[0,0,2,0,0,2,0,0,2,0,2,2,0,2,0,2,0,2,2,0,2,0,2,2,2,0,0,0,0,2,0,0,2,0,0,0,0,0,0,2,0,0,0,2,0,0],[1,2,0,0,2,0,0,0,0,1,0,2,2,0,2,1,1,2,0,0,2,1,2,0,0,0,2,0,0,2,0,2,2,2,2,2,2,0,2,0,1,2,2,0,2,2],[0,0,0,0,2,0,0,0,1,0,2,2,0,0,2,0,0,0,0,2,0,0,0,0,0,0,0,0,2,1,2,0,2,2,2,0,1,2,2,1,0,2,2,0,2,0],[2,0,2,0,0,2,2,0,0,2,0,2,0,0,0,0,0,0,0,0,0,2,0,0,2,2,0,2,0,0,0,2,0,2,0,2,2,0,0,0,0,0,0,0,0,2],[0,2,0,0,0,0,2,0,0,0,0,0,0,0,0,0,1,2,0,0,0,2,0,0,0,2,0,0,0,2,2,0,2,0,0,0,2,2,0,2,0,1,0,1,0,1],[2,1,0,2,2,0,2,0,0,2,0,0,1,2,0,0,2,0,2,0,0,2,2,0,2,0,0,0,1,0,0,0,2,0,1,0,0,2,0,2,2,2,0,0,1,2],[2,0,2,0,2,0,0,0,0,0,0,0,0,0,0,2,0,0,2,0,0,2,2,0,2,2,2,0,0,2,0,0,2,0,2,0,0,0,2,1,2,2,0,2,0,2],[0,2,2,0,0,2,0,0,0,2,0,0,0,2,0,2,0,0,2,1,2,0,2,0,0,2,2,0,0,0,0,0,1,0,0,0,2,0,2,1,0,0,0,2,1,0],[1,2,0,2,0,0,0,2,0,2,2,1,0,0,1,2,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,2,2,0,0,2,0,2,0,0,2,0,0,0,0,0],[0,2,0,2,2,2,2,0,0,0,0,0,2,2,2,2,0,1,2,2,2,0,0,0,0,0,0,0,0,0,0,1,0,2,2,0,0,2,0,0,0,2,0,1,0,0],[2,2,2,0,2,0,2,2,2,0,2,0,2,2,0,2,0,2,2,0,2,1,0,0,1,0,2,0,0,0,2,0,0,0,0,0,1,2,2,0,0,0,0,0,2,0],[0,0,0,0,0,0,0,0,2,2,0,0,0,0,2,0,2,2,0,2,0,0,2,0,0,2,2,0,2,0,0,0,0,0,0,0,0,2,2,0,0,0,0,2,0,2],[0,1,0,2,0,0,0,0,0,2,2,2,0,1,0,0,2,2,0,0,0,0,2,0,2,0,0,1,0,1,0,0,2,2,0,2,0,2,0,0,0,1,0,0,0,2],[0,0,0,2,0,0,2,0,2,0,0,0,0,2,2,0,2,0,0,0,0,0,0,0,0,0,0,2,0,0,0,2,0,0,0,0,0,0,2,0,0,0,2,2,1,2],[2,1,2,2,0,0,2,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,0,0,2,1,0,0,2,2,0,2,2,0,0,2,2,2,0,0,0,2,0,0,0],[1,1,0,0,0,2,2,0,0,0,2,2,2,2,0,2,1,1,0,2,0,0,2,0,0,0,2,2,0,0,2,2,2,0,2,0,0,0,0,2,2,0,2,1,0,2],[2,2,0,2,0,0,2,0,2,0,0,2,0,0,0,2,0,0,2,0,2,0,2,0,0,0,0,0,0,0,0,0,0,0,1,2,0,2,0,0,0,0,2,0,2,2],[2,1,2,0,0,0,0,0,2,1,0,1,0,0,2,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,2,2,0,0,2,0,2,0,0,2,0,0],[0,0,0,0,1,2,1,2,0,0,2,0,0,2,1,0,2,2,0,0,2,0,2,0,2,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,2,0,0,2,1,0],[1,0,0,0,1,2,2,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,2,0,2,2,0,2,2,0,2,2,0,2,0,0,0,2,0,0],[0,2,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,2,0,0,1,0,2,0,2,2,0,0,0,0,0,0,0,0,0,0,2,2,0,0,2,2,0,2],[2,2,0,0,0,0,2,2,1,2,2,2,0,1,0,2,0,2,2,0,1,0,2,0,0,2,0,2,0,1,0,0,0,0,2,2,2,0,0,2,2,1,2,2,1,0],[2,0,0,2,2,0,0,0,0,0,0,0,2,0,0,0,0,0,0,2,2,0,2,2,2,0,0,2,0,0,0,2,0,0,0,1,0,2,2,2,0,0,2,2,0,0],[2,0,1,2,0,0,0,0,2,2,0,0,2,0,0,2,0,0,0,1,2,0,0,2,0,2,2,0,2,0,1,2,0,0,0,2,2,0,2,0,0,2,0,0,2,0]]";
    	grid = ArrayUtil.str2int2DArray(s);
    	
    	long t1 = System.currentTimeMillis();
    	int result = instance.shortestDistance(grid);
    	long t2 = System.currentTimeMillis();
    	System.out.println("result=" + result);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
    
    
    // Solution III: Accepted, slow
    static class SolutionIII extends ShortestDistanceFromAllBuildings {
    	private int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        public int shortestDistance(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0)
                return -1;
            int rows = grid.length, cols = grid[0].length;
            int ones = 0;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++)
                    if (grid[r][c] == 1)
                        ones++;
            }
            int id = -1;
            int min = Integer.MAX_VALUE;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid[r][c] <= 0) {
                        int count = bfs(r, c, id--, ones, grid);
                        if (count >= 0)
                        	min = Math.min(min, count);
                    }
                }
            }
            if (min == Integer.MAX_VALUE)
            	return -1;
            else
            	return min;
        }
        
        private int bfs(int r, int c, int id, int ones, int[][] grid) {
            int rows = grid.length, cols = grid[0].length;
            Set<Integer> pOnes = new HashSet<>();
            int count = 0, h = 0;
            List<Integer> list = new ArrayList<>();
            list.add(r*cols + c);
            grid[r][c] = id;
            while (!list.isEmpty()) {
                List<Integer> nextList = new ArrayList<>();
                for (int p : list) {
                    int r1 = p/cols, c1 = p%cols;
                    for (int [] move : moves) {
                        int r2 = r1 + move[0], c2 = c1 + move[1];
                        // if reachable
                        if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < cols) {
                            if (grid[r2][c2] <= 0 && grid[r2][c2] != id) {
                                grid[r2][c2] = id; // marked as visited
                                nextList.add(r2*cols + c2);
                            } else if (grid[r2][c2] == 1) { // but this 1 could have been visited
                                if (pOnes.add(r2*cols + c2)) {
                                    count += h + 1;
                                    if (pOnes.size() == ones)
                                        return count;
                                }
                            }
                        }
                    }
                }
                list = nextList;
                h++;
            }
            return -1; // couldn't reach all ones
        }
    }
    
    
    static class SolutionII extends ShortestDistanceFromAllBuildings {
    	private int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        public int shortestDistance(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0)
                return -1;
            int rows = grid.length, cols = grid[0].length;
            int ones = 0;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++)
                    if (grid[r][c] == 1)
                        ones++;
            }
            int id = -1;
            int min = Integer.MAX_VALUE;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid[r][c] <= 0) {
                        min = bfs(r, c, id--, ones, grid, min);
                        if (min < 0)
                            return -1;
                    }
                }
            }
            return min;
        }
        
        private int bfs(int r, int c, int id, int ones, int[][] grid, int min) {
            int rows = grid.length, cols = grid[0].length;
            int h = 0, count = 0;
            List<Integer> list = new ArrayList<>();
            list.add(cols*r + c);
            while (!list.isEmpty()) {
                List<Integer> nextList = new ArrayList<>();
                for (int p : list) {
                    int r1 = p/cols, c1 = p%cols;
                    for (int[] move : moves) {
                        int r2 = r1 + move[0], c2 = c1 + move[1];
                        if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < cols) {
                            int val = grid[r2][c2];
                            if (val != 2 && val != id) { // reachable
                            	count += ones;
                                if (count == min)
                                    return min; // no need to continue
                                if (val == 1 && --ones == 0)
                                    return count;
                                else {
                                    int nextP = cols*r2 + c2;
                                    nextList.add(nextP);
                                    grid[r2][c2] = id;
                                }
                            }
                        }
                    }
                }
                list = nextList;
            }
            return -1; // couldn't reach all ones
        }
    }
	
	
	// Solution I: TLE
	static class SolutionI extends ShortestDistanceFromAllBuildings {
		public int shortestDistance(int[][] grid) {
	        int rows = grid.length, cols = grid[0].length;
	        int ones = 0;
	        List<Integer> zeros = new ArrayList<>();
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (grid[r][c] == 1)
	                    ones++;
	                else if (grid[r][c] == 0)
	                    zeros.add(cols*r + c);
	            }
	        }
	        int min = -1;
	        for (int zero : zeros) {
	            int count = dist(zero, grid, rows, cols, ones);
	            if (count > 0) {
	                if (min < 0)
	                    min = Integer.MAX_VALUE;
	                min = Math.min(min, count);
	            }
	        }
	        return min;
	    }
	    
	    private int dist(int zero, int[][] grid, int rows, int cols, int ones) {
	        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	        List<Integer> list = new ArrayList<>();
	        list.add(zero);
	        Set<Integer> visited = new HashSet<>();
	        visited.add(zero);
	        int dist = 0, h = 1;
	        while (!list.isEmpty()) {
	            List<Integer> nextList = new ArrayList<>();
	            for (int p : list) {
	                int r1 = p / cols;
	                int c1 = p % cols;
	                for (int[] move : moves) {
	                    int r2 = r1 + move[0];
	                    int c2 = c1 + move[1];
	                    if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < cols && visited.add(cols*r2 + c2)) {
	                        if (grid[r2][c2] == 1) {
	                            dist += h;
	                            if (--ones == 0)
	                                return dist;
	                        } else if (grid[r2][c2] == 0) {
	                            nextList.add(cols*r2 + c2);
	                        }
	                    }
	                }
	            }
	            list = nextList;
	            h++;
	        }
	        return dist; // not reachable but required by syntax
	    }
	}
	
	
}
