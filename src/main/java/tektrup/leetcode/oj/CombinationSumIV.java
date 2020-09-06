package tektrup.leetcode.oj;


import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class CombinationSumIV {
	public abstract int combinationSum4(int[] nums, int target);
	public static void main(String[] args) {
		CombinationSumIV instance = new SolutionIV();
		
		int[] nums; int target;
		int res;
		
//		nums = new int[]{1,2,3}; target = 4;	// 7
		
		nums = new int[]{4,2,1}; target = 32;	// 
		
//		nums = new int[]{1,2}; target = 2;
		
		res = instance.combinationSum4(nums, target);
		System.out.println("result=" + res);
	}
	
	
	static class SolutionIV extends CombinationSumIV {
	    public int combinationSum4(int[] nums, int target) {
	    	int count = 0;
	        Arrays.sort(nums);
	        List<Integer> list = Arrays.asList(target);
	        while (!list.isEmpty()) {
System.out.println(list);
	        	List<Integer> nextList = new LinkedList<>();
	        	for (int t : list) {
	        		if (t == 0)
	        			count++;
	        		else {
	        			for (int num : nums) {
	        				int nextT = t - num;
	        				if (nextT >= 0)
	        					nextList.add(nextT);
	        			}
	        		}
	        	}
	        	list = nextList;
	        }
	        
	        return count;
	    }
		
	}
	
	
	static class SolutionIII extends CombinationSumIV {
		private int gcount = 0;
	    public int combinationSum4(int[] nums, int target) {
	        Arrays.sort(nums);
	        LinkedList<Integer> list = new LinkedList<>();
	        for (int i = 0; i < nums.length; i++)
	            bt(nums, i, list, target);
	        return gcount;
	    }
	    
	    // target > 0
	    private void bt(int[] nums, int idx, LinkedList<Integer> list, int target) {
	        if (idx == nums.length)
	            return;
	        target -= nums[idx];
	        int count = 0;
	        list.add(nums[idx]);
	        count++;
	        while (target > 0) {
	            for (int i = idx+1; i < nums.length; i++) {
	                if (target >= nums[i])
	                    bt(nums, i, list, target);
	                else
	                    break;
	            }
	            target -= nums[idx];
	            list.add(nums[idx]);
	            count++;
	        }
	        
	        	
	        if (target == 0) {
	        	System.out.println(list);
	            gcount++;
	        }
	        for (int i = 0; i < count; i++)
	        	list.removeLast();
	    }
	    
	}
	
	
	static class SolutionII extends CombinationSumIV {
		private int gcount = 0;
	    public int combinationSum4(int[] nums, int target) {
	        Arrays.sort(nums);
	        LinkedList<Integer> list = new LinkedList<>();
	        bt(nums, 0, list, target);
	        return gcount;
	    }
	    
	    private void bt(int[] nums, int idx, LinkedList<Integer> list, int target) {
	        if (target == 0) {
	        	System.out.println(list);
	            gcount++;
	        } else if (idx < nums.length && target >= nums[idx]) {
	        	list.add(nums[idx]);
	        	bt(nums, idx, list, target-nums[idx]);
	        	list.removeLast();
	            for (int i = idx; i < nums.length; i++) {
	            	list.add(nums[i]);
	                bt(nums, i+1, list, target-nums[i]);
	                list.removeLast();
	            }
	        }
	    }
	}
	
	static class SolutionI extends CombinationSumIV {
		public int combinationSum4(int[] nums, int target) {
	        Arrays.sort(nums);
	        LinkedList<Integer> list = new LinkedList<>(); 
	        return help(nums, 0, target, list);
	    }
	    
	    private Map<List<Integer>, Integer> cache = new HashMap<>();
	    private int help(int[] nums, int idx, int target, LinkedList<Integer> list) {
	        if (target == 0) {
System.out.println("list=" + list);
	            return 1;
	        }
	        if (idx == nums.length || nums[idx] > target)
	            return 0;
	        
//	        List<Integer> key = Arrays.asList(idx, target);
//	        Integer res = cache.get(key);
//	        if (res != null)
//	            return res;
if (list.size() == 2 && list.get(0) == 1 && list.get(1) == 1 && target == 2)
	System.out.println();
	        int count1 = help(nums, idx+1, target, list);
	        list.add(nums[idx]);
	        int count2 = help(nums, idx, target-nums[idx], list);
	        int count3 = help(nums, idx+1, target-nums[idx], list);
	        list.removeLast();
	        int res = count1 + count2 + count3;
//	        cache.put(key, res);
	        
	        return res;
	    }
	}

}
