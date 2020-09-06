package tektrup.leetcode.oj;


import java.util.Arrays;

public class MinimumSizeSubarraySum {
	
	public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] lefts = new int[nums.length], rights = new int[nums.length];
        int sum = 0, left = 0, right = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            lefts[i] = left;
            left += nums[i];
            rights[nums.length-1-i] = right;
            right += nums[nums.length-1-i];
        }
System.out.println("lefts: " + Arrays.toString(lefts));
System.out.println("rights: " + Arrays.toString(rights));
        for (int len = 1; len <= nums.length; len++) {
            for (int i = 0; i + len - 1 < nums.length; i++) {
System.out.println("i=" + i + ", j=" + (i + len - 1) + ", sum=" + (sum - lefts[i] - rights[i + len - 1]));
                if (sum - lefts[i] - rights[i + len - 1] >= s)
                    return len;
            }
        }
        return 0;
    }
	
	public static void main(String[] args) {
		MinimumSizeSubarraySum instance = new MinimumSizeSubarraySum();
		int s;
		int[] nums;
		
		s = 15;
		nums = new int[]{5,1,3 ,5,10,7,4,9,2,8};
		System.out.println("result=" + instance.minSubArrayLen(s, nums));
	}

}
