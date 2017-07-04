package leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class PermutationsII {
	public abstract List<List<Integer>> permuteUnique(int[] nums);
	public static void main(String[] args) {
		PermutationsII instance = new SolutionII();
		int[] nums;
		List<List<Integer>> results;
		
		// [[1, 1, 2], [1, 2, 1], [2, 1, 1]]
//		nums = new int[]{1,1,2};
		
		// [[1, 1, 1]]
//		nums = new int[]{1,1,1};
		
		
		nums = new int[]{1,1,2,3};
		
		results = instance.permuteUnique(nums);
		System.out.println("results=" + results);
	}
	
	
	static class SolutionIII extends PermutationsII {
		public List<List<Integer>> permuteUnique(int[] nums) {
	        if (nums.length == 0)
	            return Collections.emptyList();
	        return dp(nums, 0, nums.length-1);
	    }
	    
	    private Map<List<Integer>, List<List<Integer>>> cache = new HashMap<List<Integer>, List<List<Integer>>>();
	    private List<List<Integer>> dp(int[] nums, int l, int r) {
	        // termination
	        if (l > r) {
	            // not null, not empty list, but a list containing an empty list;
	            // compatible with recursion logic
	            return Collections.singletonList(Collections.emptyList());
	        }
	            
	        // memoization
	        List<Integer> key = Arrays.asList(l, r);
	        List<List<Integer>> results = cache.get(key);
	        if (results != null)
	            return results;
	        for (int m = l; m <= r; m++) {
	            List<List<Integer>> lresults = dp(nums, l, m-1);
	            List<List<Integer>> rresults = dp(nums, m+1, r);
	            for (List<Integer> lres : lresults) {
	                for (List<Integer> rres : rresults) {
	                    List<Integer> result = new LinkedList<>();
	                    result.addAll(lres);
	                    result.add(nums[m]);
	                    result.addAll(rres);
	                    results.add(result);
	                    if (!lres.equals(rres)) {
	                        result = new LinkedList<>();
	                        result.addAll(rres);
	                        result.add(nums[m]);
	                        result.addAll(lres);
	                        results.add(result);
	                    }
	                }
	            }
	        }
	        cache.put(key, results);
	        return results;
	    }
	}
	
	
	static class SolutionII extends PermutationsII {
		public List<List<Integer>> permuteUnique(int[] nums) {
	        Arrays.sort(nums);
	        List<List<Integer>> results = new ArrayList<>();
	        bt(nums, 0, results);
	        return results;
	    }
	    
	    private void bt(int[] nums, int idx, List<List<Integer>> results) {
if (nums[0] == 3 && nums[1] == 1)
	System.out.println("31, idx=" + idx);
//if (nums[0] == 2 && nums[1] == 1)
//	System.out.println("21, idx=" + idx);
	        // termination
	        if (idx == nums.length-1) {
	            List<Integer> result = new ArrayList<>(nums.length);
	            for (int num : nums)
	                result.add(num);
	            results.add(result);
	        } else {
	            for (int i = idx; i < nums.length; i++) {
	                if (i-1 >= idx && nums[i] == nums[i-1])
	                    continue;
	                swap(nums, idx, i); // modify
	                bt(nums, idx+1, results);
	                swap(nums, idx, i); // restore
	            }
	        }
	    }
	    
	    private void swap(int[] nums, int l, int r) {
	        if (l != r) {
	            nums[l] = nums[l] ^ nums[r];
	            nums[r] = nums[l] ^ nums[r];
	            nums[l] = nums[l] ^ nums[r];
	        }
	    }
	}
	
	
	// Solution I: Logic Error
	static class SolutionI extends PermutationsII {
		public List<List<Integer>> permuteUnique(int[] nums) {
	        if (nums == null || nums.length == 0)
	            return Collections.emptyList();
	        List<List<Integer>> lists = new ArrayList<>();
	        List<Integer> list0 = new ArrayList<>();
	        list0.add(nums[0]);
	        lists.add(list0);
	        for (int i = 1; i < nums.length; i++) {
	            List<List<Integer>> nextLists = new ArrayList<>();
	            for (List<Integer> list : lists) {
	                for (int j = 0; j <= list.size(); j++) {
	                    if (j-1 < 0 || list.get(j-1) == nums[i])
	                        continue;
	                    else {
	                        List<Integer> nextList = new ArrayList<>(list);
	                        nextList.add(j, nums[i]);
	                        nextLists.add(nextList);
	                    }
	                }
	            }
	            lists = nextLists;
	        }
	        return lists;
	    }
	}
}
