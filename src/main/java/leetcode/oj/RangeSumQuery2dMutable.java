package leetcode.oj;


import leetcode.util.ArrayUtil;

public abstract class RangeSumQuery2dMutable {
	public static void main(String[] args) {
		int[][] matrix = ArrayUtil.str2int2DArray("[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]");
		NumMatrix instance = new NumMatrix(matrix);
		
		System.out.println("sum=" + instance.sumRegion(2, 1, 4, 3));
	}
	
	
	// Solution I:
	public static class NumMatrix {
	    // colSums[r][c]: sum of a column strip between row 0 and row r, inclusive.
	    // when update (O(n)): update the strip of at just that column, but for rows equal or larger than 'row'.
	    // when sum region (O(n^2)): 
	    //  sum col strips of column c1 -> c2 with row = r2 as S1;
	    //  sum col strips of column c1 -> c2 wiht row = r1 as S2;
	    //  region sum = S1 - S2
	    private int[][] matrix;
	    private int[][] colSums;
	    private int rows, cols;
	    public NumMatrix(int[][] matrix) {
	        this.matrix = matrix;
	        if (matrix != null && matrix.length > 0 && matrix[0].length > 0) {
	            rows = matrix.length;
	            cols = matrix[0].length;
	            colSums = new int[rows][cols];
	            // init colSums
	            for (int r = 0; r < rows; r++) {
	                for (int c = 0; c < cols; c++) {
	                    int upper = 0;
	                    if (r - 1 >= 0)
	                        upper = colSums[r-1][c];
	                    colSums[r][c] = upper + matrix[r][c]; // row depends on row-1; row moves from 0 to max. good.
	                }
	            }
	        }
	    }

	    public void update(int row, int col, int val) {
	        int diff = val - matrix[row][col];
	        for (int r = row; r < rows; colSums[r][col] += diff, r++);
	        matrix[row][col] = val;
	    }

	    public int sumRegion(int row1, int col1, int row2, int col2) {
	        int sum = 0;
	        for (int c = col1; c <= col2; c++) {
	            sum += colSums[row2][c] - colSums[row1][c];
	        }
	        return sum;
	    }
	}
	
}
