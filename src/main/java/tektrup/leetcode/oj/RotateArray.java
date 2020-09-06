package tektrup.leetcode.oj;


import java.util.Arrays;

import tektrup.leetcode.util.ArrayUtil;

public abstract class RotateArray {
	public abstract void rotate(int[] nums, int k);
	public static void main(String[] args) {
		RotateArray instance = new SolutionII();
		int[] nums; int k;
		
		// 2,1
//		nums = ArrayUtil.str2intArray("[1,2]");
//		k = 1;
		
		nums = ArrayUtil.str2intArray("[1,2,3,4,5,6]");
		k = 2;
		instance.rotate(nums, k);
		System.out.println("result=" + Arrays.toString(nums));
	}
	
	
	// Solution II: Logic Error
	// this logic works if all elements in nums are non-negative
	static class SolutionII extends RotateArray {
		public void rotate(int[] nums, int k) {
	        for (int i = 0; i < nums.length; i++) {
	            int val1 = nums[i];
	            int idx1 = i;
	            while (val1 >= 0) {
	                int idx2 = (idx1 + k)%nums.length;
	                int val2 = nums[idx2];
	                nums[idx2] = -val1;
	                val1 = val2;
	                idx1 = idx2;
	            }
	        }
	        for (int i = 0; i < nums.length; i++)
	            nums[i] = -nums[i];
	    }
	}
	
	
	// Solution I: Logic Error
	static class SolutionI extends RotateArray {
		public void rotate(int[] nums, int k) {
	        if (k > 0) {
	            int count = 0;
	            int val1 = nums[0]; // current val to be shifted
	            int idx1 = 0;       // current idx to be shifted
	            while (count < nums.length) {
	                int idx2 = (idx1 + k) % nums.length;
	                int val2 = nums[idx2];
	                nums[idx2] = val1;
	                count++;
	                // shift
	                val1 = val2;
	                idx1 = idx2;
	            }
	        }
	    }
	}
}
