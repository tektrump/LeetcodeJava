package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tektrup.leetcode.util.ArrayUtil;

public abstract class MaximalSquare {
	public abstract int maximalSquare(char[][] matrix);
	public static void main(String[] args) {
		MaximalSquare instance = new SolutionII();
		char[][] matrix;
		
//		matrix = new char[][]{
//			"10100".toCharArray(), 
//			"10111".toCharArray(), 
//			"11111".toCharArray(), 
//			"10010".toCharArray()};
		
		matrix = ArrayUtil.strArrayTo2DCharArray(new String[]{"1101","1101","1111"});
			
		int result = instance.maximalSquare(matrix);
		System.out.println("result=" + result);
	}
	
	
	static class SolutionII extends MaximalSquare {
		public int maximalSquare(char[][] matrix) {
	        int rows = matrix.length;
	        if (rows == 0)
	            return 0;
	        int cols = matrix[0].length;
	        if (cols == 0)
	            return 0;
	        int[][] hcounts = new int[rows][cols];
	        int[][] vcounts = new int[rows][cols];
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (matrix[r][c] == '1') {
	                    int prev = c-1 >= 0 ? hcounts[r][c-1] : 0;
	                    hcounts[r][c] = prev + 1;
	                }
	            }
	        }
	        for (int c = 0; c < cols; c++) {
	            for (int r = 0; r < rows; r++) {
	                if (matrix[r][c] == '1') {
	                    int prev = r-1 >= 0 ? vcounts[r-1][c] : 0;
	                    vcounts[r][c] = prev + 1;
	                }
	            }
	        }
ArrayUtil.printMatrix(hcounts);
System.out.println();
ArrayUtil.printMatrix(vcounts);
	        int max = 0;
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                int count = Math.min(hcounts[r][c], vcounts[r][c]);
	                max = Math.max(max, count);
	            }
	        }
	        return max*max;
	    }
	}
	
	
	// Solution I: Accepted
	static class SolutionI extends MaximalSquare {
		public int maximalSquare(char[][] matrix) {
	        int max = 0;
	        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
	            return 0;
	        int rows = matrix.length, cols = matrix[0].length;
	        List<List<Integer>> rects = new ArrayList<>();
	        Set<List<Integer>> visited = new HashSet<>();
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (matrix[r][c] == '1') {
	                    List<Integer> rect = new ArrayList<>(2);
	                    rect.add(cols*r + c);
	                    rect.add(cols*r + c);
	                    rects.add(rect);
	                    visited.add(rect);
	                }
	            }
	        }
	        while (!rects.isEmpty()) {
	            List<List<Integer>> nextRects = new ArrayList<>();
	            for (List<Integer> rect : rects) {
	                int r0 = rect.get(0) / cols, c0 = rect.get(0) % cols;
	                int r1 = rect.get(1) / cols, c1 = rect.get(1) % cols;
	                max = Math.max(max, (r1-r0+1)*(c1-c0+1));
	                // try expand
	                if (++r1 < rows && ++c1 < cols) {
	                    boolean allOnes = true;
	                    for (int r = r0; r <= r1; r++) {
	                        if (matrix[r][c1] != '1') {
	                            allOnes = false;
	                            break;
	                        }
	                    }
	                    if (allOnes) {
	                        for (int c = c0; c <= c1; c++) {
	                            if (matrix[r1][c] != '1') {
	                                allOnes = false;
	                                break;
	                            }
	                        }
	                    }
	                    if (allOnes) {
	                        List<Integer> nextRect = new ArrayList<>(rect);
	                        nextRect.set(1, cols*r1 + c1);
	                        if (visited.add(nextRect))
	                            nextRects.add(nextRect);
	                    }
	                }
	
	            }
	            rects = nextRects;
	        }
	        
	        return max;
	    }
	}
	
}
