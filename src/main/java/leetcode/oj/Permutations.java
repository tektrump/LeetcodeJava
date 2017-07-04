package leetcode.oj;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Permutations {
	public abstract List<List<Integer>> permute(int[] nums);
	public static void main(String[] args) {
		Permutations instance = new SolutionII();
		int[] nums;
		List<List<Integer>> results;
		
		nums = new int[]{1,2,3};
		results = instance.permute(nums);
		System.out.println("results=" + results);
	}
	
	static class SolutionII extends Permutations {
		private Map<List<Integer>, List<List<Integer>>> cache = new HashMap<>();
	    public List<List<Integer>> permute(int[] nums) {
	        return recur(nums, 0, nums.length-1);
	    }
	    
	    private List<List<Integer>> recur(int[] nums, int l, int r) {
	        // termination
	        if (l > r) {
	            // instead of returning an empty list, return a lists with 1 list, which is empty;
	            // this will make subsequent coding easier.
	            List<Integer> empty = new ArrayList<>();
	            List<List<Integer>> results = new ArrayList<>();
	            results.add(empty);
	            return results;
	        }
	        // memoization
	        List<Integer> key = new ArrayList<>(2);
	        key.add(l);
	        key.add(r);
	        List<List<Integer>> results = cache.get(key);
	        if (results != null)
	            return results;
	        // main logic
	        results = new ArrayList<>();
	        boolean allEqual = true;
	        for (int i = l+1; i <= r; i++) {
	            if (nums[i] != nums[i-1]) {
	                allEqual = false;
	                break;
	            }
	        }
	        if (allEqual) {
	            List<Integer> result = new ArrayList<>();
	            for (int i = l; i <= r; i++)
	                result.add(nums[i]);
	            results.add(result);
	        } else if (l + 1 == r) {
	            List<Integer> result1 = new ArrayList<>();
	            result1.add(nums[l]);
	            result1.add(nums[r]);
	            results.add(result1);
	            List<Integer> result2 = new ArrayList<>();
	            result2.add(nums[r]);
	            result2.add(nums[l]);
	            results.add(result2);
	        } else {
	            for (int i = l; i <= r; i++) {
	                List<List<Integer>> lsubs = recur(nums, l, i-1);
	                List<List<Integer>> rsubs = recur(nums, i+1, r);
	                for (List<Integer> lsub : lsubs) {
	                    for (List<Integer> rsub : rsubs) {
	                        List<Integer> result1 = new ArrayList<>();
	                        result1.add(nums[i]);
	                        result1.addAll(lsub);
	                        result1.addAll(rsub);
	                        results.add(result1);
	                        if (!lsub.isEmpty() && !rsub.isEmpty()) {
		                        List<Integer> result2 = new ArrayList<>();
		                        result2.add(nums[i]);
		                        result2.addAll(rsub);
		                        result2.addAll(lsub);
		                        results.add(result2);
	                        }
	                    }
	                }
	            }
	        }
System.out.println("key=" + key + ", results=" + results);
	        cache.put(key, results);
	        return results;
	    }
	}
}
