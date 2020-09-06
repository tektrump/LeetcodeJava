package tektrup.leetcode.oj;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class ThreeSum {
	public abstract List<List<Integer>> threeSum(int[] nums);
	public static void main(String[] args) {
		SolutionII instance = new SolutionII();
		int[] nums;
		List<List<Integer>> rets;
		
		// [-1, -1, 2], [-1, 0, 1]
		nums = new int[]{-1,0,1,2,-1,-4};
		
		rets = instance.threeSum(nums);
		System.out.println("results=" + rets);
	}
	
	static class SolutionII extends ThreeSum {
		public List<List<Integer>> threeSum(int[] nums) {
	        Arrays.sort(nums);
	        List<List<Integer>> rets = new LinkedList<>();
	        for (int i = 0; i < nums.length-2; i++) {
	            if (i - 1 >= 0 && nums[i] == nums[i-1])
	                continue;
	            int target = -nums[i];
	            int j = i+1, k = nums.length-1;
	            while (j < k) {
	                int sum = nums[j] + nums[k];
	                if (sum == target) {
	                    rets.add(Arrays.asList(nums[i], nums[j], nums[k]));
	                    j++;
	                    while (j <= k && nums[j] == nums[j-1])
	                    	j++;
	                    k--;
	                    while (j <= k && nums[k] == nums[k+1])
	                    	k--;
	                } else if (sum < target) {
	                	j++;
	                    while (j <= k && nums[j] == nums[j-1])
	                    	j++;
	                } else {
	                	k--;
	                    while (j <= k && nums[k] == nums[k+1])
	                    	k--;
	                }
	            }
	        }
	        
	        return rets;
	    }
	}
}
