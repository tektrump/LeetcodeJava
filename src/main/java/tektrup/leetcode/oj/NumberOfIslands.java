package tektrup.leetcode.oj;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tektrup.leetcode.util.ArrayUtil;

public abstract class NumberOfIslands {
	public abstract int numIslands(char[][] grid);
	public static void main(String[] args) {
    	NumberOfIslands instance = new SolutionIV();
    	char[][] grid;
    	
    	// 1
//    	grid = ArrayUtil.strArrayTo2DCharArray(new String[]{"11110", "11110", "11110", "00000"});
    	
//    	grid = ArrayUtil.strArrayTo2DCharArray(new String[]{"11110", "11110", "11110", "00000"});
    	
    	// 1
//    	grid = ArrayUtil.strArrayTo2DCharArray(new String[]{"11110", "11010", "11000", "00000"});
    	
    	// 3
//    	grid = ArrayUtil.strArrayTo2DCharArray(new String[]{"11000", "11000", "00100", "00011"});
    	
    	// 1
    	grid = ArrayUtil.strArrayTo2DCharArray(new String[]{"11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"});
    	
    	// 1
//    	grid = ArrayUtil.strArrayTo2DCharArray(new String[]{"11111011111111101011","01111111111110111110","10111001101111111111","11110111111111111111","10011111111111111111","10111111011101110111","01111111111101101111","11111111111101111011","11111111110111111111","11111111111111111111","01111111011111111111","11111111111111111111","11111111111111111111","11111011111110111111","10111110111011110111","11111111111101111110","11111111111110111100","11111111111111111111","11111111111111111111","11111111111111111111"});
    	
    	// 3
//    	grid = ArrayUtil.strArrayTo2DCharArray(new String[]{"11111110111111111111","11111101110101011111","11111111111000111100","10101111010101101111","10111111011001110111","01111101101001110111","11111111011101011111","11111011111010101011","01111111010110111001","01000111111011101010","01111111110000111111","10110111111111110101","11111101011010111111","11111111111011111110","11010111011111111111","11111101110111011001","10111111111100111111","10011100110111001111","11101100010011110011","11111111011111110101"});
    	
    	long t1 = System.currentTimeMillis();
    	int result = instance.numIslands(grid);
    	long t2 = System.currentTimeMillis();
    	System.out.println("result=" + result);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
	
	
	static class SolutionIV extends NumberOfIslands {
		private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    public int numIslands(char[][] grid) {
	        if (grid == null || grid.length == 0 || grid[0].length == 0)
	            return 0;
	        
	        int rows = grid.length, cols = grid[0].length;
	        int[][] table = new int[rows][cols];
	        Map<Integer, Integer> roots = new HashMap<>();
	        int root = 1;
	        
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (grid[r][c] == '1') {
	                    table[r][c] = root;
	                    roots.put(root, root);
	                    for (int[] dir : dirs) {
	                        int r1 = r + dir[0];
	                        int c1 = c + dir[1];
	                        if (r1 >= 0 && r1 < rows && c1 >= 0 && c1 < cols && table[r1][c1] > 0) {
	                            int root1 = find(table[r1][c1], roots);
	                            if (root1 != root)
	                                roots.put(root1, root); // union root1 to current root
	                        }
	                    }
	                    root++;
	                }
	            }
	        }
	        
	        int count = 0;
	        for (Map.Entry<Integer, Integer> entry : roots.entrySet()) {
	            if (entry.getKey().equals(entry.getValue()))
	                count++;
	        }
	        
	        return count;
	    }
	    
	    private int find(int key, Map<Integer, Integer> roots) {
	        int val = roots.get(key);
	        if (val == key)
	            return val;
	        else
	            return find(val, roots);
	    }
	}
	
	
	static class SolutionIII extends NumberOfIslands {
		public int numIslands(char[][] grid) {
	        if (grid == null || grid.length == 0 || grid[0].length == 0)
	            return 0;
	        int rows = grid.length, cols = grid[0].length;
	        Set<Integer> ids = new HashSet<>();
	        int id = 1;
	        int[][] dp = new int[rows][cols];
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
//System.out.println("r=" + r + ", c=" + c);
	                if (grid[r][c] == '1') {
	                    int id1 = 0, id2 = 0;
	                    if (r-1 >= 0)
	                        id1 = dp[r-1][c];
	                    if (c-1 >= 0)
	                        id2 = dp[r][c-1];
	                    if (id1 > 0)
	                        dp[r][c] = id1;
	                    else if (id2 > 0)
	                        dp[r][c] = id2;
	                    else {
	                        dp[r][c] = id;
	                        ids.add(id);
	                        id++;
	                    }
	                    if (id1 > 0 && id2 > 0 && id1 != id2)
	                        ids.remove(id2);
	                }
	            }
	        }
	        
System.out.println("ids=" + ids);
	        return ids.size();
	    }
	}
	
	
	static class SolutionII extends NumberOfIslands {
		private static final int[][] moves = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
	    public int numIslands(char[][] grid) {
	        int rows = grid.length;
	        if (rows == 0)
	            return 0;
	        int cols = grid[0].length;
	        if (cols == 0)
	            return 0;
	            
	        int count = 0;
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (grid[r][c] == '1') {
	                    count++;
	                    List<Integer> list = new LinkedList<>();
	                    list.add(cols*r + c);
	                    while (!list.isEmpty()) {
	                        List<Integer> nextList = new LinkedList<>();
	                        for (Integer e : list) {
	                            int r1 = e / cols;
	                            int c1 = e % cols;
	                            for (int[] move : moves) {
	                                int r2 = r1 + move[0];
	                                int c2 = c1 + move[1];
	                                if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < cols && grid[r2][c2] == '1') {
	                                	grid[r2][c2] = '0';
	                                    nextList.add(cols*r2 + c2);
	                                }
	                            }
	                        }
	                        list = nextList;
	                    }
	                }
	            }
	        }
	        
	        return count;
	    }
	}
	
    
	// Solution I: Accepted
	static class SolutionI extends NumberOfIslands {
	    private static final int[][] moves = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
	    public int numIslands(char[][] grid) {
	        int count = 0;
	        int rows = grid.length;
	        if (rows == 0)
	            return 0;
	        int cols = grid[0].length;
	        if (cols == 0)
	            return 0;
	            
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (grid[r][c] == '1') {
	                    count++;
	                    List<Integer> list = new LinkedList<>();
	                    list.add(cols*r + c);
	                    update(grid, rows, cols, list);
	                }
	            }
	        }
	        
	        return count;
	    }
	    
	    private void update(char[][] grid, int rows, int cols, List<Integer> list) {
	        while (!list.isEmpty()) {
	            List<Integer> nextList = new LinkedList<>();
	            for (Integer e : list) {
	                int r = e / cols;
	                int c = e % cols;
	                for (int[] move : moves) {
	                    int r2 = r + move[0];
	                    int c2 = c + move[1];
	                    if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < cols && grid[r2][c2] == '1') {
	                        grid[r2][c2] = '0';
	                        nextList.add(cols*r2 + c2);
	                    }
	                }
	            }
	            list = nextList;
	        }
	    }
	}
    
}
