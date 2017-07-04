package leetcode.oj;


public abstract class MissingNumber {
	public abstract int missingNumber(int[] nums);
	public static void main(String[] args) {
		MissingNumber instance = new SolutionI();
		int[] nums;
		int res;
		
		// 0
//		nums = new int[]{1};
		
		// 1
//		nums = new int[]{2, 0};
		
//		nums = new int[]{1, 0};
		
		nums = new int[]{0};
		
		res = instance.missingNumber(nums);
		System.out.println("result=" + res);
	}
	
	
	static class SolutionI extends MissingNumber {
		public int missingNumber(int[] nums) {
	        boolean zero = false;
	        for (int i = 0; i < nums.length; i++) {
	            int num = nums[i];
	            if (num == 0) {
	            	zero = true;
	                swap(nums, 0, i);
	                break;
	            }
	        }
	        if (!zero)
	            return 0;
	            
	        for (int num : nums) {
	            num = Math.abs(num);
	            int idx = num;
	            if (idx < nums.length && nums[idx] > 0)
	                nums[idx] = -nums[idx];
	        }
	        
	            
	        for (int i = 1; i < nums.length; i++) {
	            if (nums[i] >= 0)
	                return i;
	        }
	        
	        return nums.length;
	    }
	    
	    private void swap(int[] nums, int i, int j) {
	        int temp = nums[i];
	        nums[i] = nums[j];
	        nums[j] = temp;
	    }
	}
}
