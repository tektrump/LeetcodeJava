package leetcode.oj;


import java.util.Arrays;

public abstract class NextPermutation {
	public abstract void nextPermutation(int[] nums);
	public static void main(String[] args) {
		NextPermutation instance = new SolutionI();
		int[] nums;
		
//		nums = new int[]{1, 1}; // [1, 1]
		
		nums = new int[]{1,3,4,4,2}; // [1, 4, 2, 3, 4]
		
		instance.nextPermutation(nums);
		System.out.println("result=" + Arrays.toString(nums));
	}
	
	static class SolutionI extends NextPermutation {
		public void nextPermutation(int[] nums) {
	        if (nums.length <= 1)
	            return;
	        
	        for (int i = nums.length-2; i >= 0; i--) {
	            // from right to left, num should be in ascending order;
	            // find first index breaks the order.
	            if (nums[i] < nums[i+1]) {
	                int ceiling = Integer.MAX_VALUE, idx = -1;
	                for (int j = i+1; j < nums.length; j++) {
	                    if (nums[j] > nums[i]) {
	                        if (nums[j] <= ceiling) { // include equals
	                            ceiling = nums[j];
	                            idx = j;
	                        }
	                    }
	                }
	                swap(nums, i, idx);
	                reverse(nums, i+1, nums.length-1);
	                return;
	            }
	        }
	        reverse(nums, 0, nums.length-1);
	    }
	    
	    private void reverse(int[] nums, int l, int r) {
	        while (l < r)
	            swap(nums, l++, r--);
	    }
	    
	    private void swap(int[] nums, int l, int r) {
	        nums[l] = nums[l] ^ nums[r];
	        nums[r] = nums[l] ^ nums[r];
	        nums[l] = nums[l] ^ nums[r];
	    }
	}
}
