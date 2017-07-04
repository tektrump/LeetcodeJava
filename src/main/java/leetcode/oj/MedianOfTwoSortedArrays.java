package leetcode.oj;


public abstract class MedianOfTwoSortedArrays {
	public abstract double findMedianSortedArrays(int[] nums1, int[] nums2);
	public static void main(String[] args) {
		MedianOfTwoSortedArrays instance = new SolutionII();
		int[] nums1, nums2;
		double result;
		
		// 2.0
//		nums1 = new int[]{1,2}; nums2 = new int[]{3};
		
		nums1 = new int[]{1}; nums2 = new int[]{1};
		
		// 2.0
//		nums1 = new int[]{1,3}; nums2 = new int[]{2};
		
		// 2.5
//		nums1 = new int[]{1,2}; nums2 = new int[]{3,4};
		
		// 3.0
//		nums1 = new int[]{1,2}; nums2 = new int[]{3,4,5};
		
		// 1.0
//		nums1 = new int[]{1}; nums2 = new int[]{1};
		
//		nums1 = new int[]{1}; nums2 = new int[]{1};
		
		// 1.0
//		nums1 = new int[]{1}; nums2 = new int[]{1};
		
		// 2.0
//		nums1 = new int[]{1,2,2}; nums2 = new int[]{1,2,3};
		
		result = instance.findMedianSortedArrays(nums1, nums2);
		System.out.println("result=" + result);
	}
	
	
	static class SolutionII extends MedianOfTwoSortedArrays {
		public double findMedianSortedArrays(int[] nums1, int[] nums2) {
	        int len = nums1.length + nums2.length;
	        if (len % 2 == 1)
	            return findkth(nums1, nums2, len/2);
	        else
	            return(findkth(nums1, nums2, len/2-1) + findkth(nums1, nums2, len/2))/2.0;
	    }
	    
	    private int findkth(int[] nums1, int[] nums2, int k) {
	        int idx1 = binary(nums1, 0, nums1.length-1, nums2, k);
	        if (idx1 >= 0)
	            return nums1[idx1];
	        int idx2 = binary(nums2, 0, nums2.length-1, nums1, k);
	        return nums2[idx2];
	    }
	    
	    private int binary(int[] nums1, int l, int r, int[] nums2, int k) {
	        if (l > r)
	            return -1;
	        int m = (l + r)/2;
	        int count1 = m;
	        int count2 = countLess(nums2, 0, nums2.length-1, nums1[m]); // number of elements < nums1[m]
	        if (count1+count2 == k)
	            return m;
	        else if (count1+count2 < k)
	            return binary(nums1, m+1, r, nums2, k);
	        else
	            return binary(nums1, l, m-1, nums2, k);
	    }
	    
	    // find idx of first element >= target;
	    // that is, find number of elements < target
	    private int countLess(int[] nums, int l, int r, int target) {
System.out.println("l=" + l + ", r=" + r + ", target=" + target);
	        if (nums.length == 0 || target > nums[r])
	            return r+1;
	        int m = (l + r)/2;
	        if (nums[m] >= target && (m-1 < l || nums[m-1] < target))
	            return m;
	        else if (m-1 >= l && nums[m-1] >= target)
	            return countLess(nums, l, m-1, target);
	        else
	            return countLess(nums, m+1, r, target);
	    }
	}
	
	
	static class SolutionI extends MedianOfTwoSortedArrays {
		public double findMedianSortedArrays(int[] nums1, int[] nums2) {
	        int len1 = nums1.length, len2 = nums2.length;
	        int k = ((len1 + len2) >> 1);
	        if (((len1 + len2) & 1) == 1)
	            return getk(nums1, 0, len1-1, nums2, k);
	        else
	            return (getk(nums1, 0, len1-1, nums2, k) + getk(nums1, 0, len1-1, nums2, k-1))/2.0;
	    }
	    
	    // pick m from nums1, find idx of exclusive floor on nums2; 
	    // stop if count of numbers on left == k.
	    private int getk(int[] nums1, int l, int r, int[] nums2, int k) {
	    	if (k == 0) {
	    		if (nums1.length == 0)
	    			return nums2[0];
	    		else if (nums2.length == 0)
	    			return nums1[0];
	    		else
	    			return Math.min(nums1[0], nums2[0]);
	    	}
	    	if (l > r) {	// target not on nums1
	    		return getk(nums2, 0, nums2.length-1, nums1, k);
	    	} else {
	            int m = (l + r)/2;
	            // find idx of floor on nums2
	            int idx = binary(nums2, 0, nums2.length-1, nums1[m]);
	            int count = m + (idx + 1);  // handles if idx=-1, i.e., all nums2 >= nums1[k].
	            if (count == k)
	                return nums1[m];
	            int n = idx + 1;
	            while (n < nums2.length && nums2[n] == nums1[m]) {
	            	if (m + n + 1 == k) {
	            		return nums1[m];
	            	}
	            	n++;
	            }
	            if (count < k) // too few on left, move to right sub
	                return getk(nums1, m+1, r, nums2, k);
	            else // count > k, too many on left, move to left sub
	                return getk(nums1, l, m-1, nums2, k);
	        }
	    }
	    
	    // find floor (last num < target)
	    private int binary(int[] nums, int l, int r, int target) {
	        if (l > r)
	            return -1;
	        int m = (l + r)/2;
	        if (nums[m] < target && (m+1 > r || nums[m+1] >= target))
	            return m;
	        else if (m+1 <= r && nums[m+1] < target)
	            return binary(nums, m+1, r, target);
	        else
	            return binary(nums, l, m-1, target);
	    }	
	}
}
