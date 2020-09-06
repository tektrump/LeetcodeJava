package tektrup.leetcode.oj;


import java.util.HashSet;
import java.util.Set;

public abstract class WordBreak {
	public abstract boolean wordBreak(String s, Set<String> wordDict);
	public static void main(String[] args) {
		WordBreak instance = new SolutionI();
		String s; Set<String> wordDict = new HashSet<>();
		boolean res;
		
		s = "cars";
		wordDict.add("car"); wordDict.add("ca"); wordDict.add("rs");
		
		res = instance.wordBreak(s, wordDict);
		System.out.println("result=" + res);
	}
	
	static class SolutionI extends WordBreak {
		public boolean wordBreak(String s, Set<String> wordDict) {
	        boolean[] used = new boolean[s.length()];
	        return help(s, 0, wordDict, used);
	    }
	    
	    private boolean help(String s, int idx, Set<String> dict, boolean[] used) {
	        if (idx == s.length())
	            return true;
	        if (used[idx])
	            return false;
	        used[idx] = true;
	        for (String w : dict) {
	            if (idx + w.length() <= s.length() && s.substring(idx, idx+w.length()).equals(w) && help(s, idx+w.length(), dict, used))
	                return true;
	        }
	        return false;
	    }
	}
}
