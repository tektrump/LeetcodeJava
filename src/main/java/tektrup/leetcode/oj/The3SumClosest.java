package tektrup.leetcode.oj;


import java.util.Arrays;

public abstract class The3SumClosest {
	public abstract int threeSumClosest(int[] nums, int target);
	public static void main(String[] args) {
		The3SumClosest instance = new SolutionII();
		int[] nums; int target;
		int result;
		
		nums = new int[]{1,1,1,0}; target = 100;
		
		result = instance.threeSumClosest(nums, target);
		System.out.println("result=" + result);
	}
	
	static class SolutionII extends The3SumClosest {
		public int threeSumClosest(int[] nums, int target) {
			Arrays.sort(nums);
	        int result = 0;
	        int minDiff = Integer.MAX_VALUE;
	        for (int l = 0; l < nums.length; l++) {
	            for (int r = nums.length-1; r > l + 1; r--) {
	                // ERROR: binary search range must exclude l & r
	                // int m = binary(nums, l, r, target-nums[l]-nums[r]);
	                int m = binary(nums, l+1, r-1, target-nums[l]-nums[r]);
	                if (m < 0)
	                    // m < 0: can't find a number >= target in range; pick the max of range
	                    m = r - 1;
	                int num = nums[m];
	                if (Math.abs(num - target) < minDiff) {
	                    minDiff = Math.abs(num - target);
	                    result = nums[l] + nums[m] + nums[r];
	                }
	            }
	        }
	        return result;
	    }
	    
	    // search for 1st number >= target
	    private int binary(int[] nums, int l, int r, int target) {
	        if (l > r)
	            return -1;
	        int m = (l + r)/2;
	        if (nums[m] >= target && (m-1 < l || nums[m-1] < target)) {
	            if (m-1 < l || target - nums[m-1] >= nums[m] - target)
	                return m;
	            else
	                return m-1;
	        } else if (m-1 >= l && nums[m-1] >= target)
	            return binary(nums, l, m-1, target);
	        else
	            return binary(nums, m+1, r, target);
	    }
	}
}
