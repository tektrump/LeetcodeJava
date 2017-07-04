package leetcode.oj;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import leetcode.util.ArrayUtil;

public abstract class SpiralMatrix {
	public abstract List<Integer> spiralOrder(int[][] matrix);
	public static void main(String[] args) {
		SpiralMatrix instance = new SolutionI();
		int[][] matrix;
		List<Integer> res;
		
		matrix = ArrayUtil.str2int2DArray("[[1,2],[3,4]]");
		
		res = instance.spiralOrder(matrix);
		System.out.println("result=" + res);
	}
	
	static class SolutionI extends SpiralMatrix {
		public List<Integer> spiralOrder(int[][] matrix) {
	        if (matrix.length == 0 || matrix[0].length == 0)
	            return Collections.emptyList();
	        List<Integer> rets = new LinkedList<>();
	        int rows = matrix.length, cols = matrix[0].length;
	        int dir = 0;
	        int minR = 0, maxR = rows-1;
	        int minC = 0, maxC = cols-1;
	        int r = 0, c = 0;
	        while (rets.size() < rows*cols) {
	            if (dir == 0) { // move right
	                while (c <= maxC) {
	                    rets.add(matrix[r][c]);
	                    if (c == maxC) {
	                        r++;
	                        minR++;
	                        dir = 1;
	                        break;
	                    } else
	                        c++;
	                }
	            } else if (dir == 1) { // move down
	                while (r <= maxR) {
	                    rets.add(matrix[r][c]);
	                    if (r == maxR) {
	                        c--;
	                        maxC--;
	                        dir = 2;
	                        break;
	                    } else
	                        r++;
	                }
	            } else if (dir == 2) { // move left
	                while (c >= minC) {
	                	rets.add(matrix[r][c]);
	                    if (c == minC) {
	                        r--;
	                        maxR--;
	                        dir = 3;
	                        break;
	                    } else
	                        c--;
	                }
	            } else { // move up
	                while (r >= minR) {
	                	rets.add(matrix[r][c]);
	                    if (r == minR) {
	                        c++;
	                        minC++;
	                        dir = 0;
	                        break;
	                    } else
	                        r--;
	                }
	            }
	        }
	        
	        return rets;
	    }
	}
}
