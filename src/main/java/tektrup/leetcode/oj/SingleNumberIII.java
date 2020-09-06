package tektrup.leetcode.oj;


import java.util.Arrays;

import tektrup.leetcode.util.MathUtils;

public abstract class SingleNumberIII {
	public abstract int[] singleNumber(int[] nums);
	
	public static void main(String[] args) {
		SingleNumberIII instance = new SolutionI();
		int[] nums;
		
//		nums = new int[]{0, 1};
		
		nums = new int[]{1,2,1,3,2,5};
		
		int[] results = instance.singleNumber(nums);
		System.out.println("results=" + Arrays.toString(results));
	}
	
	
	static class SolutionI extends SingleNumberIII {
		public int[] singleNumber(int[] nums) {
	        int xor = 0;
	        for (int num : nums) xor ^= num;
System.out.println(MathUtils.toBinary(xor));
	        
	        int mask = 1;
	        for (int i = 0; i < 32; i++) {
	            if ((xor & mask) > 0)
	                break;
	            mask <<= 1;
	        }
System.out.println(MathUtils.toBinary(mask));
	        
	        int num1 = 0, num2 = 0;
	        for (int num : nums) {
	            if ((num & mask) > 0)
	                num1 ^= num;
	            else
	                num2 ^= num;
	        }
	        return new int[]{num1, num2};
	    }
	}
}
