package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class SubstringWithConcatenationOfAllWords {
	public abstract List<Integer> findSubstring(String s, String[] words);
	public static void main(String[] args) {
		SubstringWithConcatenationOfAllWords instance = new SolutionI();
		String s; String[] words;
		List<Integer> results;
		
		s = "barfoothefoobarman";
		words = new String[]{"foo","bar"};
		results = instance.findSubstring(s, words);
		System.out.println("results=" + results);
	}
	
	static class SolutionI extends SubstringWithConcatenationOfAllWords {
		public List<Integer> findSubstring(String s, String[] words) {
	        if (words.length == 0)
	            return Collections.emptyList();
	        int len = words[0].length();
	        long hash = 0;
	        Set<String> set = new HashSet<>();
	        for (String word : words) {
	            long whash = 0;
	            for (char ch : word.toCharArray())
	                whash = whash*26 + (ch - 'a');
	            hash += whash;
	            set.add(word);
	        }
	        
	        List<Integer> results = new ArrayList<>();
	        for (int i = 0; i + len*words.length <= s.length(); i++) {
	            long hash1 = 0;
	            int idx = i;
	            for (int j = 0; j < words.length; j++) {
	                long whash1 = 0;
	                for (int k = 0; k < len; k++) {
	                    whash1 = whash1*26 + (s.charAt(idx++) - 'a');
	                }
	                hash1 += whash1;
	            }
	            if (hash == hash1 && match(s, i, len, new HashSet<>(set)))
	                results.add(i);
	        }
	        return results;
	    }
	    
	    private boolean match(String s, int i, int len, Set<String> words) {
	        // termination
	        if (words.isEmpty())
	            return true;
	        if (i + len*words.size() > s.length())
	            return false;
	        String sub = s.substring(i, i+len);
	        if (!words.remove(sub))
	            return false;
	        else
	            return match(s, i+len, len, words);
	    }
	}

}
