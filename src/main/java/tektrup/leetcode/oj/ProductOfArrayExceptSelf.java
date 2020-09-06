package tektrup.leetcode.oj;


import java.util.Arrays;

import tektrup.leetcode.util.annotations.Leetcode;

public class ProductOfArrayExceptSelf {

	// Solution II: Best
    // improvement from solution I, use only 1 extra array instead of 2
	// and leave the original nums argument intact.
	@Leetcode(date="2016/05/04",
			url="https://leetcode.com/problems/product-of-array-except-self/")
    public int[] productExceptSelf(int[] nums) {
        int[] results = new int[nums.length];
        // the trick is to deal with leftmost and rightmost elements
        int p = 1;
        for (int i = 0; i < nums.length; i++) {
            results[i] = p;
            p *= nums[i];
        }
        p = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            results[i] *= p;
            p *= nums[i];
        }
        return results;
    }
    
    // Solution I: Accepted
    /*
    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        int p1 = 1, p2 = 1;
        for (int i = 0; i < nums.length; i++) {
            left[i] = p1;
            p1 *= nums[i];
            right[nums.length-1-i] = p2;
            p2 *= nums[nums.length-1-i];
        }
        for (int i = 0; i < nums.length; i++)
            nums[i] = left[i] * right[i];
        return nums;
    }
    */
    
    public static void main(String[] args) {
    	ProductOfArrayExceptSelf instance = new ProductOfArrayExceptSelf();
    	int[] nums;
    	
    	nums = new int[]{1,2,3,4};
    	
    	int[] results = instance.productExceptSelf(nums);
    	System.out.println("results=" + Arrays.toString(results));
	}
}
