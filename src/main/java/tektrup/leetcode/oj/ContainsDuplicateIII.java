package tektrup.leetcode.oj;


import java.util.TreeMap;

public abstract class ContainsDuplicateIII {
	public abstract boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t);
	public static void main(String[] args) {
		ContainsDuplicateIII instance = new SolutionII();
		int[] nums; int k, t;
		boolean res;
		
//		nums = new int[]{-1, -1};
//		k = 1; t = 0;
		
		// false
//		nums = new int[]{1};
//		k = 1; t = 1;
		
		// false
		nums = new int[]{-3, 3};
		k = 2; t = 4;
		
		res = instance.containsNearbyAlmostDuplicate(nums, k, t);
		System.out.println("result=" + res);
	}
	
	
	static class SolutionII extends ContainsDuplicateIII {
		public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
	        if (k == 0)
	            return false;
	        
	        TreeMap<Integer, Integer> numCount = new TreeMap<>();
	        for (int i = 0; i < nums.length; i++) {
	            int num = nums[i];
	            Integer count = numCount.get(num);
	            if (count == null)
	                count = 0;
	            numCount.put(num, count+1);
	            if (i-k-1 >= 0) {
	                int oldest = nums[i-k-1];
	                count = numCount.get(oldest);
	                if (count == 1)
	                    numCount.remove(oldest);
	                else
	                    numCount.put(oldest, count-1);
	            }
	            if (i > 0 && Math.abs(numCount.lastKey() - numCount.firstKey()) <= t)
	                return true;
	        }
	        return false;
	    }
	}
	
	
	/*
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 0 || t < 0)
            return false;
        TreeMap<Long, Integer> numCount = new TreeMap<>();
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            if (size == k + 1) {
                Long old = (long)nums[i-1-k];
                int count = numCount.get(old) - 1;
                if (count == 0)
                    numCount.remove(old);
                else
                    numCount.put(old, count);
                size--;
            }
            
            int num = nums[i];
            if (!numCount.subMap((long)num-t, true, (long)num+t, true).isEmpty())
                return true;
            
            Integer count = numCount.get((long)num);
            if (count == null)
                count = 0;
            numCount.put((long)num, count + 1);
            size++;
        }
        return false;
    }
	*/
	
	// Solution I: TLE
	/*
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            for (int d = 1; d <= k && i + d < nums.length; d++) {
            	int diff = nums[i + d] - nums[i];
                if (diff <= t)
                    return true;
            }
        }
        return false;
    }
    */
	
}
