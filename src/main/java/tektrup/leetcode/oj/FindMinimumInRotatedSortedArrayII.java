package tektrup.leetcode.oj;


public abstract class FindMinimumInRotatedSortedArrayII {
	public abstract int findMin(int[] nums);
	public static void main(String[] args) {
		FindMinimumInRotatedSortedArrayII instance = new SolutionI();
		int[] nums;
		int res;
		
		// 1
//		nums = new int[]{10, 1, 10, 10, 10};
		
		// 1
		nums = new int[]{3, 3, 1, 3};
		
		res = instance.findMin(nums);
		System.out.println("result=" + res);
	}
	
	
	static class SolutionI extends FindMinimumInRotatedSortedArrayII {
		public int findMin(int[] nums) {
	        return binary(nums, 0, nums.length-1);
	    }
	    
	    private int binary(int[] nums, int l, int r) {
System.out.println("l=" + l + ", r=" + r);
	        if (l == r)
	            return nums[l];
	        if (nums[l] < nums[r])
	            return nums[l];
	        if (nums[l] == nums[r]) {
	            if (!isRotated(nums, l, r))
	                return nums[l];
	        }
	        
	        // current sub is rotated
	        int m = (l + r)/2;
	        if (nums[l] > nums[m] || (nums[l] == nums[m] && isRotated(nums, l, m)))
	            return binary(nums, l, m);
	        else
	            return binary(nums, m+1, r);
	    }
	    
	    private boolean isRotated(int[] nums, int l, int r) {
	        for (int i = l+1; i <= r; i++) {
	            if (nums[i] != nums[i-1])
	                return true;
	        }
	        return false;
	    }
	}
	
}
