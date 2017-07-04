package leetcode.oj;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class IntersectionOfTwoArrays {
	public abstract int[] intersection(int[] nums1, int[] nums2);
	public static void main(String[] args) {
		
	}
	
	// Accepted
	static class SolutionI extends IntersectionOfTwoArrays {
		public int[] intersection(int[] nums1, int[] nums2) {
	        Arrays.sort(nums1);
	        Arrays.sort(nums2);
	        List<Integer> list = new LinkedList<>();
	        int idx1 = 0, idx2 = 0;
	        while (idx1 < nums1.length && idx2 < nums2.length) {
	            if (nums1[idx1] == nums2[idx2]) {
	                list.add(nums1[idx1]);
	                idx1 = next(idx1, nums1);
	                idx2 = next(idx2, nums2);
	            } else if (nums1[idx1] < nums2[idx2]) {
	                idx1 = next(idx1, nums1);
	            } else {
	                idx2 = next(idx2, nums2);
	            }
	        }
	        int[] rets = new int[list.size()];
	        for (int i = 0; i < rets.length; rets[i] = list.get(i), i++);
	        return rets;
	    }
	    
	    private int next(int i, int[] nums) {
	        int num = nums[i];
	        while (i < nums.length && nums[i] == num)
	            i++;
	        return i;
	    }
	}
}
