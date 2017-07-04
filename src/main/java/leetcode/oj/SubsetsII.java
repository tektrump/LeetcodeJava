package leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class SubsetsII {
	public abstract List<List<Integer>> subsetsWithDup(int[] nums);
	public static void main(String[] args) {
		SubsetsII instance = new SolutionII();
		int[] nums;
		List<List<Integer>> results;
		
//		nums = new int[]{1,2,2};
		
		nums = new int[]{4,4,4,1,4};
		
		results = instance.subsetsWithDup(nums);
		System.out.println("results=" + results);
	}
	
	
	static class SolutionII extends SubsetsII {
		public List<List<Integer>> subsetsWithDup(int[] nums) {
			Arrays.sort(nums);
	        List<List<Integer>> results = new ArrayList<>();
	        List<List<Integer>> lists = new ArrayList<>();
	        List<Integer> list0 = new ArrayList<>();
	        lists.add(list0);
	        List<Integer> ends = new ArrayList<>();
	        ends.add(-1);
	        while (!lists.isEmpty()) {
	            List<List<Integer>> nextLists = new ArrayList<>();
	            List<Integer> nextEnds = new ArrayList<>();
	            results.addAll(lists);
	            for (int i = 0; i < lists.size(); i++) {
	                List<Integer> list = lists.get(i);
	                int end = ends.get(i);
	                Set<Integer> visited = new HashSet<>();
	                for (int j = end+1; j < nums.length; j++) {
	                    int num = nums[j];
	                    if (visited.add(num)) {
	                        List<Integer> nextList = new ArrayList<>(list);
	                        nextList.add(num);
	                        nextLists.add(nextList);
	                        nextEnds.add(j);
	                    }
	                }
	            }
	            lists = nextLists;
	            ends  = nextEnds;
	        }
	        return results;
	    }
	}
	
}
