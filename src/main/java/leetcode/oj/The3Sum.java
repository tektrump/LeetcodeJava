package leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class The3Sum {
	public abstract List<List<Integer>> threeSum(int[] nums);
    public static void main(String[] args) {
    	The3Sum instance = new SolutionIV();
    	int[] nums;
    	
    	nums = new int[]{-1,0,1,2,-1,-4};
    	
    	// [[-4, -2, 6], [-2, -2, 4], [-4, 2, 2], [-4, 1, 3], [-4, 0, 4], [-2, 0, 2]]
//    	nums = new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
    	
    	// [[-11, -1, 12], [-7, -1, 8], [-3, -1, 4], [-8, -3, 11], [-4, -3, 7], [-9, -5, 14], [-5, -5, 10], [-12, 6, 6], [-13, 4, 9], [-9, 4, 5], [-14, 2, 12], [-10, 2, 8], [-6, 2, 4], [-11, 0, 11], [-7, 0, 7], [-3, 0, 3], [-12, -2, 14], [-8, -2, 10], [-4, -2, 6], [-9, -4, 13], [-5, -4, 9], [-6, -6, 12], [-13, 5, 8], [-14, 3, 11], [-10, 3, 7], [-6, 3, 3], [-15, 1, 14], [-11, 1, 10], [-7, 1, 6], [-3, 1, 2], [-12, -1, 13], [-8, -1, 9], [-4, -1, 5], [-9, -3, 12], [-5, -3, 8], [-6, -5, 11], [-7, -7, 14], [-13, 6, 7], [-14, 4, 10], [-10, 4, 6], [-15, 2, 13], [-11, 2, 9], [-7, 2, 5], [-12, 0, 12], [-8, 0, 8], [-4, 0, 4], [0, 0, 0], [-9, -2, 11], [-5, -2, 7], [-10, -4, 14], [-6, -4, 10], [-7, -6, 13], [-14, 5, 9], [-10, 5, 5], [-15, 3, 12], [-11, 3, 8], [-7, 3, 4], [-12, 1, 11], [-8, 1, 7], [-4, 1, 3], [-13, -1, 14], [-9, -1, 10], [-5, -1, 6], [-1, -1, 2], [-10, -3, 13], [-6, -3, 9], [-7, -5, 12], [-14, 6, 8], [-15, 4, 11], [-11, 4, 7], [-12, 2, 10], [-8, 2, 6], [-4, 2, 2], [-13, 0, 13], [-9, 0, 9], [-5, 0, 5], [-1, 0, 1], [-10, -2, 12], [-6, -2, 8], [-2, -2, 4], [-7, -4, 11], [-8, -6, 14], [-14, 7, 7], [-15, 5, 10], [-11, 5, 6], [-12, 3, 9], [-8, 3, 5], [-13, 1, 12], [-9, 1, 8], [-5, 1, 4], [-10, -1, 11], [-6, -1, 7], [-2, -1, 3], [-11, -3, 14], [-7, -3, 10], [-8, -5, 13], [-15, 6, 9], [-12, 4, 8], [-8, 4, 4], [-13, 2, 11], [-9, 2, 7], [-5, 2, 3], [-14, 0, 14], [-10, 0, 10], [-6, 0, 6], [-2, 0, 2], [-11, -2, 13], [-7, -2, 9], [-3, -2, 5], [-8, -4, 12], [-4, -4, 8], [-15, 7, 8], [-12, 5, 7], [-13, 3, 10], [-9, 3, 6], [-14, 1, 13], [-10, 1, 9], [-6, 1, 5], [-2, 1, 1]]
//    	nums = new int[]{7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,-4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,
//    					-5,2,1,6,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6,-15,1,5,-15,10,14,
//    					9,-8,-6,4,-6,11,12,-15,7,-1,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,
//    					12,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,-2,6,-11,12,13,-7,-12,8,6,-13,14,-2,
//    					-5,-11,1,3,-6};
    	
    	long startTime = System.currentTimeMillis();
    	List<List<Integer>> results = instance.threeSum(nums);
    	long endTime = System.currentTimeMillis();
    	System.out.println(results);
    	System.out.println(String.format("total time: %,dms", (endTime - startTime)));
	}
	
	static class SolutionIV extends The3Sum {
		public List<List<Integer>> threeSum(int[] nums) {
	        Arrays.sort(nums);
	        List<List<Integer>> results = new ArrayList<>();
	        for (int i = 0; i < nums.length; i++) {
	        	if (i - 1 >= 0 && nums[i] == nums[i-1])
	        		continue;
	            for (int j = i+1; j < nums.length; j++) {
	                if (j - 1 > i && nums[j] == nums[j-1])
	                    continue;
	                int k = binary(nums, j+1, nums.length-1, -nums[i]-nums[j]);
	                if (k >= 0) {
	                    List<Integer> result = new ArrayList<>(3);
	                    result.add(nums[i]);
	                    result.add(nums[j]);
	                    result.add(nums[k]);
	                    results.add(result);
	                }
	            }
	        }
	        return results;
	    }
	    
	    private int binary(int[] nums, int l, int r, int target) {
	        if (l > r)
	            return -1;
	        int m = (l + r)/2;
	        if (target == nums[m])
	            return m;
	        else if (target < nums[m])
	            return binary(nums, l, m-1, target);
	        else
	            return binary(nums, m+1, r, target);
	    }
	}
    
}
