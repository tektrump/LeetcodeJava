package leetcode.oj;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class GroupAnagrams {
	public abstract List<List<String>> groupAnagrams(String[] strs);
	public static void main(String[] args) {
		GroupAnagrams instance = new SolutionIII();
		String[] strs;
		List<List<String>> results;
		
		// [["ate", "eat","tea"],["nat","tan"],["bat"]]
		strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
		results = instance.groupAnagrams(strs);
		System.out.println("results=" + results);
		
		// [["and"],["dan"]]
		strs = new String[]{"and", "dan"};
		results = instance.groupAnagrams(strs);
		System.out.println("results=" + results);
		
		strs = new String[]{"tea","and","ate","eat","den"};
		results = instance.groupAnagrams(strs);
		System.out.println("results=" + results);
	}
	
	public static class SolutionIII extends GroupAnagrams {
		public List<List<String>> groupAnagrams(String[] strs) {
	        final List<Integer> countList = new ArrayList<>(26);
	        for (int i = 0; i < 26; countList.add(0), i++);
	        
	        Map<List<Integer>, List<String>> countsList = new HashMap<>();
	        for (String str : strs) {
	            List<Integer> counts = new ArrayList<>(countList);
	            for (int i = 0; i < str.length(); i++) {
	                int idx = str.charAt(i) - 'a';
	                counts.set(idx, counts.get(idx) + 1);
	            }
	            List<String> list = countsList.get(counts);
	            if (list == null) {
	                list = new LinkedList<>();
	                countsList.put(counts, list);
	            }
	            list.add(str);
	        }
	        List<List<String>> results = new LinkedList<>();
	        for (List<String> list : countsList.values()) {
	            Collections.sort(list);
	            results.add(list);
	        }
	        return results;
	    }
	}
	
	// Solution II: Wrong
	// cannot use array as key, equals() will not compare contents of array!
	public static class SolutionII extends GroupAnagrams {

		@Override
		public List<List<String>> groupAnagrams(String[] strs) {
			Map<int[], List<String>> arrayList = new HashMap<>();
	        for (String str : strs) {
	            int[] counts = new int[26];
	            for (int i = 0; i < str.length(); i++)
	                counts[str.charAt(i) - 'a']++;
	            List<String> list = arrayList.get(counts);
	            if (list == null) {
	                list = new LinkedList<>();
	                arrayList.put(counts, list);
	            }
	            list.add(str);
	        }
	        return new LinkedList<>(arrayList.values());
		}
		
	}
	
	// Solution I: TLE, Logic Correct
	public static class SolutionI extends GroupAnagrams {

		@Override
		public List<List<String>> groupAnagrams(String[] strs) {
			List<List<String>> results = new ArrayList<>();
	        Map<Map<Character, Integer>, List<String>> mapStrs = new HashMap<>();
	        for (String str : strs) {
	            Map<Character, Integer> charCount = new HashMap<>();
	            for (int i = 0; i < str.length(); i++) {
	                char ch = str.charAt(i);
	                Integer count = charCount.get(ch);
	                if (count == null)
	                    count = 0;
	                charCount.put(ch, count + 1);
	            }
	            List<String> list = mapStrs.get(charCount);
	            if (list == null) {
	                list = new ArrayList<>();
	                mapStrs.put(charCount, list);
	            }
	            list.add(str);
	        }
	        for (List<String> list : mapStrs.values())
	            results.add(list);
	        return results;
		}
		
	}
}
