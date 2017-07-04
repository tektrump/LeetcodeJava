package leetcode.oj;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import leetcode.util.LeetcodeUtils;

public abstract class RearrangeStringKDistanceApart {
	public abstract String rearrangeString(String str, int k);
	public static void main(String[] args) {
		RearrangeStringKDistanceApart instance = new SolutionII();
		String str; int k;
		String result;
		
		// a
//		str = "a";
//		k = 0;
		
		// aa
//		str = "aa";
//		k = 2;
		
		str = "aabbcc";
		k = 2;
		
		
//		str = LeetcodeUtils.readText(instance);
//		k = 26;
		
		result = instance.rearrangeString(str, k);
		System.out.println("result=" + result);
	}
	
	
	// Solution II: Accepted
	// optimize: sort chars by remaining occurrences; pick the one with most occurrences that
	// is also not repetitive with previous k-1 chars.
	// no more recursion, always pick 1 char for each index!
	static class SolutionII extends RearrangeStringKDistanceApart {
		public String rearrangeString(String str, int k) {
	        if (k <= 1)
	            return str;
	        Map<Character, Integer> charCount = new HashMap<>();
	        for (int i = 0; i < str.length(); i++) {
	            char ch = str.charAt(i);
	            Integer count = charCount.get(ch);
	            if (count == null)
	                count = 0;
	            charCount.put(ch, count+1);
	        }
	        Comparator<Map.Entry<Character, Integer>> comp = new Comparator<Map.Entry<Character, Integer>>() {
	            @Override
	            public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
	                return entry1.getValue() - entry2.getValue();
	            }
	        };
	        
	        PriorityQueue<Map.Entry<Character, Integer>> q = new PriorityQueue<>(Collections.reverseOrder(comp));
	        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) q.add(entry);
	        StringBuilder builder = new StringBuilder();
	        Set<Character> used = new HashSet<>();
	        while (!q.isEmpty()) {
	            if (builder.length() - k >= 0)
	                used.remove(builder.charAt(builder.length()-k));
	            boolean found = false;
	            List<Map.Entry<Character, Integer>> list = new ArrayList<>();
	            while (!q.isEmpty() && !found) {
	                Map.Entry<Character, Integer> entry = q.poll();
	                char ch = entry.getKey();
	                int count = entry.getValue();
	                if (used.add(ch)) {
	                    found = true;
	                    builder.append(ch);
	                    if (--count > 0) {
	                        entry.setValue(count);
	                        q.add(entry);
	                    }
	                } else {
	                    list.add(entry);
	                }
	            }
	            if (!found)
	                return "";
	            for (Map.Entry<Character, Integer> entry : list)
	                q.add(entry);
	        }
	        return builder.toString();
	    }
	    
	}
	
	
	// Solution I: Stack Overflow
	static class SolutionI extends RearrangeStringKDistanceApart {
		private Map<Integer, Set<Set<Character>>> idxVisited = new HashMap<>();
	    public String rearrangeString(String str, int k) {
	        k = k % str.length();
	        if (k == 1)
	            return str;
	        Set<Character> chars = new HashSet<>();
	        for (int i = 0; i < str.length(); chars.add(str.charAt(i++)));
	        StringBuilder builder = new StringBuilder();
	        if (dp(0, chars, str, k, builder))
	            return builder.toString();
	        else
	            return "";
	    }
	    
	    private boolean dp(int idx, Set<Character> chars, String str, int k, StringBuilder builder) {
	        if (builder.length() - k >= 0)
	            chars.add(builder.charAt(builder.length() - k));
	        if (chars.isEmpty())
	            return false;
	        Set<Set<Character>> visited = idxVisited.get(idx);
	        if (visited == null) {
	            visited = new HashSet<>();
	            idxVisited.put(idx, visited);
	        }
	        Set<Character> copy = new HashSet<>(chars);
	        if (visited.add(copy)) {
	            for (char ch : copy) {
	                builder.append(ch);     // modify
	                chars.remove(ch);       // modify
	                if (idx == str.length()-1) { // termination
	                    return true;
	                } if (!dp(idx+1, chars, str, k, builder)) { // recursion
	                    builder.deleteCharAt(builder.length()-1);   // restore
	                    chars.add(ch);                              // restore
	                } else {
	                    return true;
	                }
	            }
	        }
	        return false;
	    }
	}
}
