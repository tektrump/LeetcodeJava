package leetcode.oj;


import java.util.Arrays;

public abstract class ThreeSumSmaller {
	public abstract int threeSumSmaller(int[] nums, int target);
	public static void main(String[] args) {
		ThreeSumSmaller instance = new SolutionI();
		int[] nums; int target;
		int res;
		
		nums = new int[]{0, 0, 0};
		target = 0;
		
		res = instance.threeSumSmaller(nums, target);
		System.out.println("result=" + res);
	}
	
	static class SolutionI extends ThreeSumSmaller {
		public int threeSumSmaller(int[] nums, int target) {
	        int count = 0;
	        Arrays.sort(nums);
	        for (int i = 0; i < nums.length; i++) {
	            int j = i+1, k = nums.length-1;
	            while (j < k) {
	                int sum = nums[i] + nums[j] + nums[k];
	                if (sum < target) {
	                    count++;
	                    j++;
	                } else
	                    k--;
	            }
	        }
	        
	        return count;
	    }
	}
}
