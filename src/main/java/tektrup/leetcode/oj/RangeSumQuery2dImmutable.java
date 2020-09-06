package tektrup.leetcode.oj;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tektrup.leetcode.util.ArrayUtil;

public class RangeSumQuery2dImmutable {
	public class NumMatrix {

	    private Map<List<Integer>, Integer> rectArea;
	    public NumMatrix(int[][] matrix) {
	        rectArea = new HashMap<>();
	        for (int r = 0; r < matrix.length; r++) {
	        	int rsum = 0;
	            for (int c = 0; c < matrix[0].length; c++) {
	                List<Integer> rect = new LinkedList<>();
	                rect.add(r);
	                rect.add(c);
	                rectArea.put(rect, getArea(r-1, c) + rsum + matrix[r][c]);
	                rsum += matrix[r][c];
	            }
	        }
	    }
	    
	    private int getArea(int r, int c) {
	        List<Integer> rect = new LinkedList<>();
	        rect.add(r);
	        rect.add(c);
	        Integer area = rectArea.get(rect);
	        return area == null ? 0 : area;
	    }
	 
	    public int sumRegion(int row1, int col1, int row2, int col2) {
	        return getArea(row2, col2) - getArea(row1-1, col2) - getArea(row2, col1-1) + getArea(row1-1, col1-1);
	    }
	}
	
	public static void main(String[] args) {
		int[][] matrix;
		
		matrix = ArrayUtil.str2int2DArray("[[3, 0, 1, 4, 2],[5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]");
		
		NumMatrix instance = new RangeSumQuery2dImmutable().new NumMatrix(matrix);
		System.out.println(instance.sumRegion(2, 1, 4, 3));
		System.out.println(instance.sumRegion(1, 1, 2, 2));
		System.out.println(instance.sumRegion(1, 2, 2, 4));
	}
}
