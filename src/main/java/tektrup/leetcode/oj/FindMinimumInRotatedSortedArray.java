package tektrup.leetcode.oj;


public abstract class FindMinimumInRotatedSortedArray {
	public abstract int findMin(int[] nums);
    public static void main(String[] args) {
    	FindMinimumInRotatedSortedArray instance = new SolutionVI();
    	int[] nums;
    	int result;
    	
    	// 1
//    	nums = new int[]{3,4,5,1,2};
    	// 1
//    	nums = new int[]{2, 1};
    	// 1
//    	nums = new int[]{4,5,6,1,2,3};
    	// 1
    	nums = new int[]{2,3,1};
    	
    	result = instance.findMin(nums);
    	System.out.println("result=" + result);
	}
    
    
    static class SolutionVI extends FindMinimumInRotatedSortedArray {
    	public int findMin(int[] nums) {
            return doFind(nums, 0, nums.length-1);
        }
        
        private int doFind(int[] nums, int l, int r) {
            if (nums[l] <= nums[r])
                return nums[l];
            int m = (l + r) >> 1;
            if (m-1 >= 0 && nums[m] < nums[m-1])
                return nums[m];
            // if left sorted & right sorted, return first on right
            // if right rotated, go right
            // if left rotated, go left
            // conclusion: only if left is rotated, we go left;
            // otherwise, go right
            if (l <= m-1 && nums[l] > nums[m-1])
            	return doFind(nums, l, m-1);
            else
            	return doFind(nums, m+1, r);
        }
    }
    
    
    static class SolutionV extends FindMinimumInRotatedSortedArray {
    	public int findMin(int[] nums) {
            return doFind(nums, 0, nums.length-1);
        }
        
        private int doFind(int[] nums, int l, int r) {
            if (nums[l] <= nums[r])
                return nums[l];
            int m = (l + r) >> 1;
            if (m-1 >= 0 && nums[m] < nums[m-1])
                return nums[m];
            // if right exists and is rotated, recurse to right
            // FUCKED HERE: if only 2 elements [2,1], then right 
            // is a single element sub, which we considered "sorted".
            // but it shouldn't.
            if (m+1 <= r && nums[m+1] > nums[r]) {
                return doFind(nums, m+1, r);
            } else {
                // right not exists, or right is sorted
                // if left is sorted, return head; if left is rotated, recurse to left
                if (l <= m-1 && nums[l] <= nums[m-1])
                    return nums[l];
                else
                    return doFind(nums, l, m-1);
            }
        }
    }
	
	
	static class SolutionIV extends FindMinimumInRotatedSortedArray {
		// keep dropping the sub that is sorted until we reach a rotated sub with only 2 elements;
	    // in that case, we return the second element.
	    public int findMin(int[] nums) {
	        // if nums is not rotated, or if only 1 element.
	        if (nums.length == 1)
	            return nums[0];
	        return nums[help(nums, 0, nums.length-1)];
	    }
	    
	    // sub must have at least 2 elements; sub may or may not be rotated
	    private int help(int[] nums, int l, int r) {
	        if (nums[l] < nums[r])
	            return l;
	        if (r - l == 1)
	            return r;
	        int m = (l + r)/2;
	        if (nums[l] < nums[m])
	            return help(nums, m+1, r);
	        else
	            return help(nums, l, m);
	    }
	}
	
	
	/// Solution I (Passed): improved O(logn) solution. ///
    /**
     * Keep dividing into an subarray where left > right,
     * stop when subarray size == 2, return second element.
     * NOTE: refer to https://oj.leetcode.com/problems/find-minimum-in-rotated-sorted-array/solution/
     * for the diagram, which makes the problem very clear.
     */
	static class SolutionI extends FindMinimumInRotatedSortedArray {
	    public int findMin(int[] num) {
	        // edge case
	        if (num == null || num.length == 0)
	            return Integer.MIN_VALUE; // or whatever default value
	        if (num.length == 1)
	            return num[0];
	        
	        // pivot is the last element, rotation has no impact
	        if (num[0] < num[num.length - 1])
	            return num[0];
	        
	        // divide into sub-arrays and keep the one whose left > right,
	        // keep dividing until sub-array size == 2.
	        int left = 0, right = num.length - 1;
	        while (right - left > 1) { // stop when right - left == 1, i.e. sub-array size = 2
	            int mid = (left + right)/2;
	            if (num[left] > num[mid]) {
	                right = mid;
	            } else { // num[mid] > num[right]
	                left = mid;
	            }
	        }
	        
	        return num[right];
	    }
	}
    
    /// Solution II (Passed): O(logn) ///
    /**
     * if (num[left] < num[right])
     *  return num[left] --> rotated at end, no impact
     * 1. pick a mid point,
     * 2. divide into [left, mid], [mid, right].
     * 3. skip if num[left] < num[mid].
     * 4. repeat the same for the other subinterval.
     * 5. stop when interval size == 2, return the latter.
     */
	static class SolutionII extends FindMinimumInRotatedSortedArray {
	    public int findMin(int[] num) {
	        // edge case
	        if (num == null || num.length == 0)
	            return Integer.MIN_VALUE;
	        if (num.length == 1)
	            return num[0];
	            
	        // rotated at last element, no impact
	        if (num[0] < num[num.length - 1])
	            return num[0];
	            
	        int index = binaryFindMin(num, 0, num.length - 1);
	        return num[index];
	    }
	    
	    private int binaryFindMin(int[] num, int start, int end) {
	        int left = num[start];
	        int right = num[end];
	        if (left < right)
	            return -1;
	        else { // left > right, this interval contains rotated point
	            if (end - start == 1)
	                return end;
	            int mid = (start + end)/2;
	            int index1 = binaryFindMin(num, start, mid);
	            if (index1 >= 0)
	                return index1;
	            return binaryFindMin(num, mid, end);
	        }
	    }
	}
    
	
	/**
     * Solution III (Passed), O(n).
     * This is NOT the best solution; this is cheating.
     */
    static class SolutionIII extends FindMinimumInRotatedSortedArray {
	    public int findMin(int[] num) {
	        if (num == null || num.length == 0)
	            return Integer.MIN_VALUE;
	        if (num.length == 1)
	            return num[0];
	        for (int i = 1; i < num.length; i++) {
	            if (num[i] < num[i - 1])
	                return num[i];
	        }
	        
	        return num[0];
	    }
    }


}
