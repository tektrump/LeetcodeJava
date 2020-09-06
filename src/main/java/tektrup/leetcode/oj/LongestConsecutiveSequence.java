package tektrup.leetcode.oj;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class LongestConsecutiveSequence {
	public abstract int longestConsecutive(int[] nums);
	public static void main(String[] args) {
		LongestConsecutiveSequence instance = new SolutionI();
		int[] nums;
		int result;
		
		// 1
//		nums = new int[]{0, 0};
		
//		nums = new int[]{0, -1};
		
		nums = new int[]{-1, 1, 0};
		
		result = instance.longestConsecutive(nums);
		System.out.println("result=" + result);
	}
	
	
	static class SolutionII extends LongestConsecutiveSequence {
		public int longestConsecutive(int[] nums) {
	        if (nums == null || nums.length == 0)
	            return 0;
	        Set<Integer> visited = new HashSet<>();
	        Set<Integer> missingLeft = new HashSet<>();
	        Map<Integer, Integer> pairs = new HashMap<>();
	        for (int num : nums) {
	            if (visited.add(num)) {
	                if (!visited.contains(num - 1))
	                    missingLeft.add(num);
	                if (missingLeft.remove(num + 1))
	                    pairs.put(num, num+1);
	            }
	        }
	        int max = 0;
	        for (int head : missingLeft) {
	            Integer num = head;
	            int len = 1;
	            while ((num = pairs.get(num)) != null)
	                len++;
	            max = Math.max(max, len);
	        }
	        return max;
	    }
	}
	
	
	// Solution I: Logic Error
	static class SolutionI extends LongestConsecutiveSequence {
		public int longestConsecutive(int[] nums) {
	        if (nums == null || nums.length == 0)
	            return 0;
	        // each num and its target left neighbor
	        Map<Integer, Integer> numLeft = new HashMap<>();
	        // each num and its target right neighbor
	        Map<Integer, Integer> numRight = new HashMap<>();
	        // all found pairs
	        Map<Integer, Integer> pairs = new HashMap<>();
	        
	        for (int num : nums) {
	            // will num be some existing num's left?
	            if (numLeft.remove(num + 1) != null)
	                pairs.put(num, num+1);
	            else
	                numLeft.put(num, num-1);
	            // will num be some existing num's right
	            if (numRight.remove(num - 1) != null)
	                pairs.put(num-1, num);
	            else
	                numRight.put(num, num+1);
	        }
	        int max = 0;
	        for (int head : numLeft.keySet()) {
	            Integer num = head;
	            int len = 1;
	            while ((num = pairs.remove(num)) != null) {
	                len++;
	            }
	            max = Math.max(max, len);
	        }
	        return max;
	    }
	}
}
