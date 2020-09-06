package tektrup.leetcode.oj;


public abstract class KthLargestElementInAnArray {
	public abstract int findKthLargest(int[] nums, int k);
	public static void main(String[] args) {
		KthLargestElementInAnArray instance = new SolutionII();
		int[] nums; int k;
		int res;
		
//		nums = new int[]{1, 2, 2, 2, 1, 3};
//		k = 2;
		
		nums = new int[]{99, 99};
		k = 1;
		
		res = instance.findKthLargest(nums, k);
		System.out.println("result=" + res);
	}
	
	
	static class SolutionII extends KthLargestElementInAnArray {
		public int findKthLargest(int[] nums, int k) {
	        return help(nums, 0, nums.length-1, k);
	    }
	    
	    private int help(int[] nums, int l, int r, int k) {
	        int m = l + (r-l)/2;
	        int pivot = nums[m];
System.out.println("m=" + m + ", r=" + r);
	        swap(nums, m, r);
	        int i = l, j = l;
	        while (j < r) {
	            if (nums[j] >= pivot)
	                swap(nums, i++, j);
	            j++;
	        }
	        swap(nums, i, r);
	        if (i == k-1)
	            return nums[i];
	        else if (i < k-1)
	            return help(nums, i+1, r, k);
	        else
	            return help(nums, l, i-1, k);
	    }
	    
	    private void swap(int[] nums, int l, int r) {
	        if (l != r) {
	            nums[l] = nums[l] ^ nums[r];
	            nums[r] = nums[l] ^ nums[r];
	            nums[l] = nums[l] ^ nums[r];
	        }
	    }
	}
	
	
	static class SolutionI extends KthLargestElementInAnArray {
		public int findKthLargest(int[] nums, int k) {
	        return sort(nums, 0, nums.length-1, k);
	    }
	    
	    private int sort(int[] nums, int l, int r, int k) {
//	        int pidx = l + new Random().nextInt((r-l+1));
//	        swap(nums, pidx, r);
	        int pivot = nums[r];
	        int i = l; // i points to the leftmost num <= target
	        for (int j = i; j < r; j++) {
	            if (nums[j] > pivot) {
	                swap(nums, i, j);
	                i++;
	            }
	        }
	        swap(nums, i, r);
	        if (i == k-1)
	            return nums[i];
	        else if (i < k-1)
	            return sort(nums, i+1, r, k);
	        else
	            return sort(nums, l, i-1, k);
	    }
	    
	    private void swap(int[] nums, int l, int r) {
	        if (l != r) {
	            nums[l] = nums[l] ^ nums[r];
	            nums[r] = nums[l] ^ nums[r];
	            nums[l] = nums[l] ^ nums[r];
	        }
	    }
	}
}
