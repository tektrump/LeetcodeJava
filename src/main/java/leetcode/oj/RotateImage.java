package leetcode.oj;


import java.util.Arrays;

public abstract class RotateImage {
	public abstract void rotate(int[][] matrix);
	public static void main(String[] args) {
		RotateImage instance = new SolutionIII();
		int[][] matrix;
		
		// ((3,1),(4,2))
//		matrix = new int[][]{{1,2}, {3,4}};
		
		// ((7,4,1),(8,5,2),(9,6,3))
//		matrix = new int[][]{{1,2, 3}, {4 ,5 ,6}, {7 ,8 ,9}};
		
		matrix = new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
		
		instance.rotate(matrix);
		for (int[] row : matrix)
			System.out.println(Arrays.toString(row));
	}
	
	
	static class SolutionIII extends RotateImage {
		public void rotate(int[][] matrix) {
	        if (matrix == null || matrix.length == 0)
	            return;
	        int n = matrix.length;
	        for (int l = 0; l < n/2; l++) {
	            int r = l;
	            for (int c = l; c < n-1-l; c++) {
	                int val = matrix[r][c];
	                val = move(val, matrix, c, n-1-r);
	                val = move(val, matrix, n-1-r, n-1-c);
	                val = move(val, matrix, n-1-c, r);
	                matrix[r][c] = val;
	            }
	        }
	    }
	    
	    private int move(int val, int[][] matrix, int r, int c) {
	        int old = matrix[r][c];
	        matrix[r][c] = val;
	        return old;
	    }
	}
	
	
	// Solution II: Logic Error
	// fail to rotate all the cells; only rotate part of them
	static class SolutionII extends RotateImage {
		public void rotate(int[][] matrix) {
	        if (matrix == null || matrix.length == 0)
	            return;
	        int n = matrix.length;
	        for (int r = 0; r <= (n - 1)/2; r++) {
	            for (int c = r; c <= (n - 1)/2; c++) {
	                int val = matrix[r][c];
	                val = move(val, matrix, c, n-1-r);
	                val = move(val, matrix, n-1-r, n-1-c);
	                val = move(val, matrix, n-1-c, r);
	                matrix[r][c] = val;
	            }
	        }
	    }
	    
	    private int move(int val, int[][] matrix, int r, int c) {
	        int old = matrix[r][c];
	        matrix[r][c] = val;
	        return old;
	    }
	}
}
