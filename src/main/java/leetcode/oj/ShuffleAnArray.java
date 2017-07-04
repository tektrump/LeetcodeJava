package leetcode.oj;


import java.util.Arrays;
import java.util.Random;

public class ShuffleAnArray {
	
	static class Solution {
		
		private static final Random random = new Random();    
	    private int[] nums;
	    public Solution(int[] nums) {
	        this.nums = nums;
	    }
	    
	    public int[] reset() {
	        return nums;
	    }
	    
	    public int[] shuffle() {
	        int[] res = Arrays.copyOf(nums, nums.length);
	        for (int i = 0; i < res.length; i++) {
	            swap(res, i, random.nextInt(res.length));
	        }
	        return res;
	    }
	    
	    private void swap(int[] nums, int i, int j) {
	        if (i != j) {
	            nums[i] = nums[i] ^ nums[j];
	            nums[j] = nums[i] ^ nums[j];
	            nums[i] = nums[i] ^ nums[j];
	        }
	    }
	}
}
