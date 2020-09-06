package tektrup.leetcode.oj;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class FourSum {
	public abstract List<List<Integer>> fourSum(int[] nums, int target);
	public static void main(String[] args) {
		FourSum instance = new SolutionI();
		int[] nums; int target;
		List<List<Integer>> rets;
		
		nums = new int[]{0, 0, 0, 0}; target = 0;
		
		rets = instance.fourSum(nums, target);
		System.out.println("results=" + rets);
	}
	
	static class SolutionI extends FourSum {
		public List<List<Integer>> fourSum(int[] nums, int target) {
	        if (nums.length < 4)
	            return Collections.emptyList();
	        Arrays.sort(nums);
	        List<List<Integer>> rets = new LinkedList<>();
	        for (int a = 0; a < nums.length; a++) {
	            if (a-1 >= 0 && nums[a] == nums[a-1])
	                continue;
	            for (int b = a+1; b < nums.length; b++) {
	                if (b-1 >= a+1 && nums[b] == nums[b-1])
	                    continue;
	                int target1 = target - nums[a] - nums[b];
	                int c = b+1, d = nums.length-1;
	                while (c < d) {
	                    int sum = nums[c] + nums[d];
	                    if (sum == target1) {
	                        rets.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
	                        c++;
	                        d--;
	                    } else if (sum > target1)
	                        d--;
	                    else
	                        c++;
	                }
	            }
	        }
	        
	        return rets;
	    }
	}
}
