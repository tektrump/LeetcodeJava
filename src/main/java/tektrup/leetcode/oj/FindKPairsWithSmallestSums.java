package tektrup.leetcode.oj;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class FindKPairsWithSmallestSums {
	public abstract List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k);
	public static void main(String[] args) {
		FindKPairsWithSmallestSums instance = new SolutionII();
		int[] nums1, nums2; int k;
		List<int[]> results;
		
		// [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
//		nums1 = new int[]{1,1,2};
//		nums2 = new int[]{1,2,3};
//		k = 10;
		
		// [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
		nums1 = new int[]{1,7,11};
		nums2 = new int[]{2,4,6};
		k = 10;
		
		results = instance.kSmallestPairs(nums1, nums2, k);
		for (int[] result : results) {
			System.out.print(Arrays.toString(result));
			System.out.print(", ");
		}
		System.out.println();
	}
	
	static class SolutionII extends FindKPairsWithSmallestSums {
		public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
	        int rows = nums1.length, cols = nums2.length;
	        if (rows == 0 || cols == 0)
	            return Collections.emptyList();
	        boolean[][] dp = new boolean[rows][cols]; // not taken
	        List<int[]> results = new LinkedList<>();
	        int[] prev = {0, 0};
	        results.add(new int[]{nums1[0], nums2[0]});
	        dp[0][0] = true;
	        while (results.size() < k && (prev[0] != rows-1 || prev[1] != cols-1)) {
	            int[] curr = new int[2];
	            int r = prev[0], c = prev[1];
	            int min = Integer.MAX_VALUE;
	            if (r+1 < rows) {
	                for (int cc = 0; cc <= c; cc++) {
	                    if (!dp[r+1][cc]) {
	                        int sum = nums1[r+1] + nums2[cc];
	                        if (sum < min) {
	                            min = sum;
	                            curr = new int[]{r+1, cc};
	                        }
	                    }
	                }
	            }
	            if (c+1 < cols) {
	                for (int rr = 0; rr <= r; rr++) {
	                    if (!dp[rr][c+1]) {
	                        int sum = nums1[rr] + nums2[c+1];
	                        if (sum < min) {
	                            min = sum;
	                            curr = new int[]{rr, c+1};
	                        }
	                    }
	                }
	            }
	            results.add(new int[]{nums1[curr[0]], nums2[curr[1]]});
	            prev = curr;
	            dp[curr[0]][curr[1]] = true;
	        }
	        return results;
	    }
	}
}
