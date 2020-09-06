package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public abstract class RemoveDuplicateLetters {
	public abstract String removeDuplicateLetters(String s);
	public static void main(String[] args) {
		RemoveDuplicateLetters instance = new SolutionII();
		String s;
		String result;
		long t1, t2;
		
//		s = "bcabc";	// abc
		
//		s = "cbacdcbc";	// acdb
		
		s = "ccacbaba";	// 
		
		t1 = System.currentTimeMillis();
		result = instance.removeDuplicateLetters(s);
		t2 = System.currentTimeMillis();
		System.out.println(String.format("result=%s, total time=%,dms", result, (t2 - t1)));
	}
	
	
	static class SolutionII extends RemoveDuplicateLetters {
		public String removeDuplicateLetters(String s) {
			Set<Character> chars = new HashSet<>();
	        int[] lasts = new int[26];  // last index of every char
	        Map<Character, List<Integer>> charIndices = new HashMap<>();
	        // char -> find indices >= minIdx, if it's less than every remaining char's last idx, pick it
	        for (int i = s.length()-1; i >= 0; i--) {
	            char ch = s.charAt(i);
	            chars.add(ch);
	            if (lasts[ch-'a'] == 0)
	                lasts[ch-'a'] = i;
	            List<Integer> indices = charIndices.get(ch);
	            if (indices == null) {
	                indices = new LinkedList<>();
	                charIndices.put(ch, indices);
	            }
	            indices.add(0, i);
	        }
	        StringBuilder builder = new StringBuilder();
	        // remaining chars to be placed, sorted
	        TreeSet<Character> tree = new TreeSet<>();
	        for (Character ch : chars)
	            tree.add(ch);
	        int minIdx = 0;
	        while (!tree.isEmpty()) {
	            Iterator<Character> itr = tree.iterator();
	            while (itr.hasNext()) {
	                boolean stop = false;
	                char ch = itr.next();
	                List<Integer> indices = charIndices.get(ch);
	                for (int idx : indices) {
	                    if (idx >= minIdx) {
	                        boolean foundAll = true;
	                        for (char c : tree) {
	                            if (lasts[c-'a'] < idx) {
	                                foundAll = false;
	                                break;
	                            }
	                        }
	                        if (foundAll) {
	                            builder.append(ch);
System.out.println(builder);
	                            minIdx = idx;
	                            itr.remove();
	                            stop = true;
	                            break;
	                        }
	                    }
	                }
	                if (stop)
	                    break;
	            }
	        }
	        return builder.toString();
	    }
	}
	
	
	// Solution I: Logic Error
	static class SolutionI extends RemoveDuplicateLetters {
		public String removeDuplicateLetters(String s) {
	        // unique chars
	        // sort chars to linked list
	        // map char ~ indices
	        // map index ~ # unique chars after
	        Set<Character> chars = new HashSet<>();
	        // index, and count of unique chars to right, inclusive
	        Map<Integer, Integer> indexCount = new HashMap<>();
	        Map<Character, List<Integer>> charIndices = new HashMap<>();
	        for (int i = s.length()-1; i >= 0; i--) {
	            char ch = s.charAt(i);
	            chars.add(ch);
	            indexCount.put(i, chars.size());
	            List<Integer> indices = charIndices.get(ch);
	            if (indices == null) {
	                indices = new ArrayList<>();
	                charIndices.put(ch, indices);
	            }
	            indices.add(i);
	        }
	        boolean[] targets = new boolean[26];
	        for (char ch : chars)
	            targets[ch - 'a'] = true;
	        StringBuilder builder = new StringBuilder();
	        int minIdx = -1;
	        while (!chars.isEmpty()) {
	            for (int i = 0; i < 26; i++) {
	                if (targets[i]) {
	                    char ch = (char)('a' + i);
	                    List<Integer> indices = charIndices.get(ch);
	                    for (int idx : indices) {
	                        if (idx > minIdx && indexCount.get(idx) >= chars.size()) {
	                            builder.append(ch);
	                            minIdx = idx;
	                            chars.remove(ch);
	                            targets[ch - 'a'] = false;
	                        }
	                    }
	                }
	            }
	        }
	        return builder.toString();
	    }
	}
}
