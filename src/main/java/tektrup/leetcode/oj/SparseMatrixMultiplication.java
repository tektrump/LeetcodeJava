package tektrup.leetcode.oj;


import java.util.Arrays;

public abstract class SparseMatrixMultiplication {
	public abstract int[][] multiply(int[][] A, int[][] B);
	public static void main(String[] args) {
		SparseMatrixMultiplication instance = new SolutionI();
		int[][] A, B;
		
		A = new int[][]{{1,0,0}, {-1,0,3}};
		B = new int[][]{{7,0,0}, {0,0,0}, {0,0,1}};
		
		int[][] results = instance.multiply(A, B);
		for (int[] row : results)
			System.out.println(Arrays.toString(row));
	}
	
	
	static class SolutionI extends SparseMatrixMultiplication {
		public int[][] multiply(int[][] A, int[][] B) {
	        int rowA = A.length, rowB = B.length;
	        int colA = A[0].length, colB = B[0].length;
	        // colA == rowB
	        int[][] P = new int[rowA][colB];
	        for (int rA = 0; rA < rowA; rA++) {
	            for (int cA = 0; cA < colA; cA++) {
	                if (A[rA][cA] != 0) {
	                    for (int cB = 0; cB < colB; cB++)
	                        P[rA][cB] += A[rA][cA] * B[cA][cB];
	                }
	            }
	        }
	        
	        return P;
	    }
	}
}
