package tektrup.leetcode.oj;


import java.util.Arrays;

public abstract class MaximumGap {
	public abstract int maximumGap(int[] nums);
	public static void main(String[] args) {
		MaximumGap instance = new SolutionI();
		int[] nums;
		int res;
		
//		nums = new int[]{1, 3, 2, 4};
		
		nums = new int[]{100, 3, 2, 1};
		
		res = instance.maximumGap(nums);
		System.out.println("result=" + res);
	}
	
	
	static class SolutionI extends MaximumGap {
		public int maximumGap(int[] nums) {
	        if (nums.length < 2)
	            return 0;
	        sort(nums, 0);
System.out.println("sorted=" + Arrays.toString(nums));
	        int max = 0;
	        for (int i = 1; i < nums.length; i++)
	            max = Math.max(max, Math.abs(nums[i] - nums[i-1]));
	            
	        return max;
	    }
	    
	    private void sort(int[] nums, int order) {
	        if (order == 32) // termination
	            return;
	        int mask = 1 << order;
	        int i = 0, j = 0;
	        while (j < nums.length) {
	            if ((nums[j] & mask) == 0)
	                swap(nums, i++, j);
	            j++;
	        }
	        sort(nums, order+1);
	    }
	    
	    private void swap(int[] nums, int l, int r) {
	        if (l != r) {
	            nums[l] = nums[l] ^ nums[r];
	            nums[r] = nums[l] ^ nums[r];
	            nums[l] = nums[l] ^ nums[r];
	        }
	    }
	}

}
