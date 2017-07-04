package leetcode.oj;


public abstract class HouseRobberII {
	public abstract int rob(int[] nums);
	public static void main(String[] args) {
    	HouseRobberII instance = new SolutionII();
    	int[] nums;
    	
    	nums = new int[]{4,1,2,7,5,3,1};
    	
    	int result = instance.rob(nums);
    	System.out.println("result=" + result);
	}
	
	
	static class SolutionII extends HouseRobberII {
		public int rob(int[] nums) {
	        // edge cases
	        if (nums == null || nums.length == 0)
	            return 0;
	        if (nums.length == 1)
	            return nums[0];
	            
	        // rob first house (rob 0, skip 1, start loop at 2; must skip last house)
	        int prev2 = nums[0], prev1 = Math.max(prev2, nums[1]), curr = prev2;
	        for (int i = 2; i < nums.length-1; i++) {
	            curr = Math.max(prev2 + nums[i], prev1);
	            prev2 = prev1;
	            prev1 = curr;
	        }
	        int sum1 = curr;
	        
	        // skip first house (skip 0, start loop at 1; may rob last house)
	        prev2 = 0;
	        prev1 = 0;
	        curr = 0;
	        for (int i = 1; i < nums.length; i++) {
	            curr = Math.max(prev2 + nums[i], prev1);
	            prev2 = prev1;
	            prev1 = curr;
	        }
	        int sum2 = curr;
	        
	        return Math.max(sum1, sum2);
	    }
	}
	
	
	static class SolutionI extends HouseRobberII {
		private int max = 0;
	    public int rob(int[] nums) {
	        if (nums == null || nums.length == 0)
	            return 0;
	        boolean[] states = new boolean[nums.length];
	        dp(nums, states, 0, 0, nums.length);
	        return max;
	    }
	    
	    private void dp(int[] nums, boolean[] states, int idx, int total, int numRemains) {
	        int next = (idx + 1) % nums.length, prev = (nums.length + idx - 1) % nums.length;
	        if (states[idx] || states[next] || states[prev]) {
	            numRemains--;
	            // termination
	            if (numRemains == 0)
	                return;
	            else
	                dp(nums, states, next, total, numRemains);
	        } else { // this house is rob-able
	            // not rob
	            dp(nums, states, next, total, numRemains);
	            // rob
	            total += nums[idx];
	            states[idx] = true;
	            numRemains--;
	            max = Math.max(max, total);
	            dp(nums, states, next, total, numRemains);
	            // restore
	            states[idx] = false;
	        }
	    }
	}

}
