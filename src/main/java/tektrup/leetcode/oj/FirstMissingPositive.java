package tektrup.leetcode.oj;


import tektrup.leetcode.util.ArrayUtil;

public abstract class FirstMissingPositive {
	public abstract int firstMissingPositive(int[] nums);
	public static void main(String[] args) {
		FirstMissingPositive instance = new SolutionIII();
		int[] nums;
		int result;
		
//		nums = ArrayUtil.str2intArray("[3,4,-1,1]");
		
//		nums = ArrayUtil.str2intArray("[2]");
		
		
		nums = ArrayUtil.str2intArray("[0,-1,3,1]");
		
		result = instance.firstMissingPositive(nums);
		System.out.println("result=" + result);
	}
	
	
	static class SolutionIII extends FirstMissingPositive {
		public int firstMissingPositive(int[] nums) {
	        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	        for (int i = 0; i < nums.length; i++) {
	            int num = nums[i];
	            if (num > 0) {
	                min = Math.min(min, num);
	                max = Math.max(max, num);
	            } else if (num < 0)
	                nums[i] = 0;
	        }
	        if (min > 1)
	            return 1;
	            
	        for (int num : nums) {
	            if (num != 0) {
	                num = Math.abs(num);
	                int idx = num - min;
	                if (idx < nums.length && nums[idx] > 0)
	                    nums[idx] = -nums[idx];
	            }
	        }
	        for (int num = min; num <= max; num++) {
	            int idx = num - min;
	            if (nums[idx] > 0)
	                return num;
	        }
	        return max+1;
	    }
	}
	
	
	// Solution II: Accepted
    // slight improvement from solution I
	static class SolutionII extends FirstMissingPositive {
		public int firstMissingPositive(int[] nums) {
	        for (int i = 0; i < nums.length; i++) {
	            int num = nums[i];
	            if (num > 0 && i != num - 1) {
	                nums[i] = 0;
	                while (true) {
	                    int idx = num - 1;
	                    if (idx >= nums.length || nums[idx] == num)
	                        break;
	                    if (nums[idx] <= 0) {
	                        nums[idx] = num;
	                        break;
	                    } else {
	                        int next = nums[idx];
	                        nums[idx] = num;
	                        num = next;
	                    }
	                }
	            }
	        }
	        int idx = 0;
	        while (idx < nums.length && nums[idx] > 0) idx++;
	        return idx + 1;
	    }
	}

	
	// Solution I: Accepted
	static class SolutionI extends FirstMissingPositive {
		public int firstMissingPositive(int[] nums) {
	        for (int i = 0; i < nums.length; i++) {
	            int num = nums[i];
	            if (num > 0 && i != num-1) {
	                nums[i] = 0;
	                while (true) { // give num, place it to the righ idx
	                    int idx = num-1;
	                    if (idx >= nums.length || nums[idx] == num)
	                        break;
	                    else if (nums[idx] <= 0) {
	                        nums[idx] = num;
	                        break;
	                    } else {
	                        int next = nums[idx];
	                        nums[idx] = num;
	                        num = next;
	                    }
	                }
	            }
	        }
	        int idx = 0;
	        while (idx < nums.length && nums[idx] > 0)
	            idx++;
	        return idx+1;
	    }
	}
}
