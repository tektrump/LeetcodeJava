package leetcode.oj;


public abstract class PatchingArray {
	public abstract int minPatches(int[] nums, int n);
	public static void main(String[] args) {
		PatchingArray instance = new SolutionI();
		int[] nums; int n;
		int result;
		
		nums = new int[]{1,2,2}; n = 15;
		
		result = instance.minPatches(nums, n);
		System.out.println("result=" + result);
	}
	
	
	static class SolutionI extends PatchingArray {
		public int minPatches(int[] nums, int n) {
	        int patch = 0, idx = 0;
	        long max = 0;
	        while (max < n) {
	            if (idx < nums.length && nums[idx] <= max + 1) {
	                max += nums[idx];
	                idx++;
	            } else { // patch a new number=max
System.out.println("patch number: " + (max + 1));
	                patch++;
	                max += max + 1;
	            }
	        }
	        return patch;
	    }
	}
}
