package tektrup.leetcode.oj;


import java.util.Arrays;

public class SearchA2dMatrixII {
	public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return false;
        int[] rows = getRows(matrix, target, 0, matrix.length-1);
System.out.println(">>> rows=" + Arrays.toString(rows));

        if (rows.length == 0)
            return false;
        for (int row : rows) {
System.out.println("searching row=" + Arrays.toString(matrix[row]));
            if (binary(matrix[row], target, 0, matrix[0].length-1))
                return true;
        }
        return false;
    }
    
    private int[] getRows(int[][]matrix, int target, int i, int j) {
        int row = getRow(matrix, target, i, j);
        if (row < 0)
            return new int[0];
        int[] rows = new int[2];
        for (int r = row; r >= 0 && matrix[r][matrix[0].length-1] >= target; rows[0] = r--);
        for (int r = row; r < matrix.length && matrix[r][0] <= target; rows[1] = r++);
System.out.println("row=" + row + ",rows=" + Arrays.toString(rows));
        return rows;
    }
    
    private int getRow(int[][] matrix, int target, int i, int j) {
        if (i > j)
            return -1;
        int m = (i + j)/2;
        if (matrix[m][0] <= target && matrix[m][matrix[0].length-1] >= target)
            return m;
        else if (target < matrix[m][0])
            return getRow(matrix, target, i, m-1);
        else
            return getRow(matrix, target, m+1, j);
    }
    
    private boolean binary(int[] array, int target, int start, int end) {
        if (start > end)
            return false;
        int mid = (start + end)/2;
        if (array[mid] == target)
            return true;
        else if (target < array[mid])
            return binary(array, target, start, mid-1);
        else
            return binary(array, target, mid+1, end);
    }

    public static void main(String[] args) {
    	SearchA2dMatrixII instance = new SearchA2dMatrixII();
    	int[][] matrix;
    	int target;
    	
    	matrix = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
    	target = 5;
    	
    	boolean result = instance.searchMatrix(matrix, target);
    	System.out.println("result=" + result);
	}
}
