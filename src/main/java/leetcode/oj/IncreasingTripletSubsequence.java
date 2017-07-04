package leetcode.oj;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

import leetcode.util.annotations.Leetcode;
import leetcode.util.annotations.Leetcode.Tags;

@Leetcode(date="2016-06-06", tags={Tags.REMEMBER, Tags.MATH}, 
	url="https://leetcode.com/problems/increasing-triplet-subsequence/")
public abstract class IncreasingTripletSubsequence {
	public abstract boolean increasingTriplet(int[] nums);
	public static void main(String[] args) {
		IncreasingTripletSubsequence instance = new SolutionIV();
		int[] nums;
		boolean result;
		long t1, t2;
		
//		nums = new int[]{0,4,2,1,0,-1,-3};
		
//		nums = new int[]{2,1,5,0,4,6};	// true
		
//		nums = new int[]{1,2,-10,-8,-7};	// true
		
//		nums = new int[]{5,1,5,5,2,5,4};	// true
		
		nums = new int[]{1,2,1,2,1,2,1,2,1,2};	// false
		
		t1 = System.currentTimeMillis();
		result = instance.increasingTriplet(nums);
		t2 = System.currentTimeMillis();
		System.out.println(String.format("result=%s, tota time=%,dms", result, (t2 - t1)));
	}
	
	
	// Solution V: Best
	// A generic solution with O(n*k) time and O(k) space.
	// Idea: keep a list with K-1 elements, where list(0) is the smallest number encountered,
	// and list(i) is the smallest number encountered after list(i-1) and is bigger than list(i-1).
	// we keep updating the numbers 0 ~ K-1, preparing for the moment when we can add the last
	// element to list and return true.
	// Improvement from solution IV: instead of keeping all lists with diff size, 
	// only keep 1 list with size=K-1.
	static class SolutionV extends IncreasingTripletSubsequence {
		private static final int K = 3;
	    public boolean increasingTriplet(int[] nums) {
	        List<Integer> list = new ArrayList<>();
	        for (int i = 0; i < K-1; list.add(Integer.MAX_VALUE), i++);
	        for (int num : nums) {
	            int i = 0;
	            while (i < K-1 && num > list.get(i)) i++;
	            if (i == K-1) // found the new element to complete the list, done.
	                return true;
	            else if (num < list.get(i)) // updating previous elements
	                list.set(i, num);
	        }
	        return false;
	    }
	}

	
	// Solution IV: Accepted
	// a generic solution for detecting increasing sequence with length K;
	// time is O(n*K), space is O(K^2).
	static class SolutionIV extends IncreasingTripletSubsequence {
		private static final int K = 3;
	    public boolean increasingTriplet(int[] nums) {
	        Set<List<Integer>> lists = new HashSet<>();
	        Map<Integer, List<Integer>> lenList = new TreeMap<>();
	        for (int num : nums) {
	            // new list
	            if (!lenList.containsKey(1)) {
	                List<Integer> list0 = new ArrayList<>();
	                list0.add(num);
	                lists.add(list0);
	                lenList.put(1, list0);
	            }
	            for (List<Integer> list : lists) {
	                if (num > list.get(list.size()-1)) {
	                    list.add(num);
	                    if (list.size() == K)
	                        return true;
	                    lenList.remove(list.size()-1);
	                    lenList.put(list.size(), list);
	                } else if (num < list.get(list.size()-1) && (list.size() == 1 || num > list.get(list.size()-2)))
	                    list.set(list.size()-1, num);
	            }
	            
	        }

	        return false;
	    }
	}
	
	// Solution III: Logic Error
	// over complicated using TreeMap.
	static class SolutionIII extends IncreasingTripletSubsequence {
		private static final int K = 3;
	    public boolean increasingTriplet(int[] nums) {
	        TreeMap<Integer, List<Integer>> endList = new TreeMap<>();
	        Map<Integer, List<Integer>> lenList = new TreeMap<>();
	        for (int num : nums) {
	        	// add a new list
	            if (!lenList.containsKey(1)) {
	                List<Integer> list = new ArrayList<>();
	                list.add(num);
	                endList.put(num, list);
	                lenList.put(1, list);
	            }
                // expand
                Map.Entry<Integer, List<Integer>> lowerEntry = endList.lowerEntry(num);
                if (lowerEntry != null) {
                    List<Integer> expanded = new ArrayList<>(lowerEntry.getValue());
                    expanded.add(num);
                    if (expanded.size() == K)
                    	return true;
                    endList.put(num, expanded);
                    List<Integer> old = lenList.put(expanded.size(), expanded);
                    if (old != null)
                        endList.remove(old.get(old.size()-1));
                }
                // replace
                List<Integer> replace = null;
                while (!endList.isEmpty()) {
                    List<Integer> last = endList.lastEntry().getValue();
                    if (last.get(last.size()-1) > num && (last.size() == 1 || last.get(last.size()-2) < num)) {
                        if (replace == null)
                            replace = last;
                        lenList.remove(endList.pollLastEntry().getValue().size());
                    } else
                        break;
                }
                if (replace != null) {
                    replace.set(replace.size()-1, num);
                    endList.put(num, replace);
                    lenList.put(replace.size(), replace);
                }
            }
	        return false;
	    }
	}
	
	
	// Solution II: Accepted
	// keep 2 lists, 1 with 2 elements and the other with 1 element
	// try to expand (if 2nd is expanded, it will replace the 1st one).
	static class SolutionII extends IncreasingTripletSubsequence {
		public boolean increasingTriplet(int[] nums) {
	        List<Integer> list2 = new LinkedList<>(), list1 = new LinkedList<>();
	        for (int num : nums) {
	            // replace last only
	            replace(list2, num);
	            replace(list1, num);
	            // try expand, from longest list to shortest; once expanded, promote the expanded list
	            if (!list2.isEmpty() && num > list2.get(list2.size()-1)) {
	                list2.add(num);
	                if (list2.size() == 3)
	                    return true;
	            }
	            else if (list1.isEmpty() || num > list1.get(list1.size()-1)) {
	                list1.add(num);
	                if (list1.size() == 2) { // promote
	                    list2 = list1;
	                    list1 = new LinkedList<>();
	                }
	            }
	            
	            if (!list1.isEmpty() && !list2.isEmpty() 
	                && list1.get(list1.size()-1) == list2.get(list2.size()-1))
	                list1 = new LinkedList<>();
	        }
	        return false;
	    }
	    
	    private void replace(List<Integer> list, int num) {
	        if (!list.isEmpty() && num < list.get(list.size()-1)) {
	            if (list.size() == 1 || list.get(list.size()-2) < num) {
	                list.remove(list.size()-1);
	                list.add(num);
	            }
	        }
	    }
	}
	
	
	// Solution I: Logic Error
	static class SolutionI extends IncreasingTripletSubsequence {
		public boolean increasingTriplet(int[] nums) {
	        Stack<Integer> mins = new Stack<>();
	        for (int num : nums) {
	            while (!mins.isEmpty() && num <= mins.peek())
	                mins.pop();
	            mins.push(num);
	            if (mins.size() == 3)
	                return true;
	        }
	        return false;
	    }
	}
}
