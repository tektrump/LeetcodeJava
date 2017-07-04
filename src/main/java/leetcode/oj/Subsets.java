package leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subsets {
	
	public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
		Arrays.sort(nums);
        return recur(nums, 0);
    }
    
    private ArrayList<ArrayList<Integer>> recur(int[] nums, int s) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        if (s == nums.length - 1) {
            results.add(new ArrayList<>());
            ArrayList<Integer> result = new ArrayList<>(1);
            result.add(nums[s]);
            results.add(result);
        } else {
            ArrayList<ArrayList<Integer>> subResults = recur(nums, s + 1);
            results.addAll(subResults);
            for (ArrayList<Integer> subResult : subResults) {
                ArrayList<Integer> result = new ArrayList<>();
                result.add(nums[s]);
                result.addAll(subResult);
                results.add(result);
            }
        }
        return results;
    }
	
	/*
	private Map<Integer, List<List<Integer>>> cache = new HashMap<>();
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = recur(0, nums);
        results.add(Collections.emptyList());
        return results;
    }
    
    private List<List<Integer>> recur(int index, int[] nums) {
        List<List<Integer>> results = cache.get(index);
        if (results != null)
            return results;
        results = new ArrayList<>();
        for (int i = index; i < nums.length; i++) {
            if (index == nums.length - 1) {
                List<Integer> result = new ArrayList<>();
                result.add(i);                results.add(result);
            } else {
                List<List<Integer>> subs = recur(index+1, nums);
                results.addAll(subs);
                for (List<Integer> sub : subs) {
                    List<Integer> result = new ArrayList<>();
                    result.add(i);
                    result.addAll(sub);
                    results.add(result);
                }
            }
        }
        
        cache.put(index, results);
        return results;
    }
    */
    
    public static void main(String[] args) {
    	Subsets instance = new Subsets();
    	int[] nums;
    	
//    	nums = new int[]{1,2,3};
    	nums = new int[]{4, 1, 0};
//    	nums = new int[]{[1,2,3,4,5,6,7,8,10,0]};
    	
    	ArrayList<ArrayList<Integer>> results = instance.subsets(nums);
    	System.out.println(results);
	}
}
