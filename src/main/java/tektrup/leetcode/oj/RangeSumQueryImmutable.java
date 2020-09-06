package tektrup.leetcode.oj;


public class RangeSumQueryImmutable {

	public class NumArray {

	    private int sum;
	    private int[] lsums;
	    private int[] rsums;
	    public NumArray(int[] nums) {
	        lsums = new int[nums.length];
	        rsums = new int[nums.length];
	        for (int num : nums)
	            sum += num;
	        int lsum = 0, rsum = 0;
	        for (int i = 0; i < nums.length; i++) {
	            lsums[i] = lsum;
	            lsum += nums[i];
	            rsums[nums.length - 1 - i] = rsum;
	            rsum += nums[nums.length - 1 - i];
	        }
	    }

	    public int sumRange(int i, int j) {
	        return sum - lsums[i] - rsums[j];
	    }
	}
	
	public static void main(String[] args) {
		int[] nums;
		
		nums = new int[]{-2,0,3,-5,2,-1};
		
		NumArray numArray = new RangeSumQueryImmutable(). new NumArray(nums);
		System.out.println(numArray.sumRange(0,2));
		System.out.println(numArray.sumRange(2,5));
		System.out.println(numArray.sumRange(0,5));
	}
}
