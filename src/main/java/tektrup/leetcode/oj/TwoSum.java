package tektrup.leetcode.oj;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode: https://leetcode.com/problems/two-sum/
 * @author Alex
 * @date Apr 18, 2016
 */
public abstract class TwoSum {
	public abstract int[] twoSum(int[] nums, int target);
	public static void main(String[] args) {
		TwoSum instance = new SolutionI();
		int[] nums; int target;
		int[] rets;
		
		nums = new int[]{2, 2};
		target = 4;
		
		rets = instance.twoSum(nums, target);
		System.out.println("results=" + Arrays.toString(rets));
	}
	
	
	static class SolutionI extends TwoSum {
		public int[] twoSum(int[] nums, int target) {
	        // key: right value to pair up; value: left index
	        Map<Integer, Integer> rightLeft = new HashMap<>();
	        for (int i = 0; i < nums.length; i++) {
	            int num = nums[i];
	            Integer lidx = rightLeft.get(num);
	            if (lidx != null)
	                return new int[]{lidx, i};
	            rightLeft.put(target - num, i);
	        }
	        return null; // unreachable
	    }
	}
	
}
