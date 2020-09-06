package tektrup.leetcode.oj;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class WordPatternII {
	public abstract boolean wordPatternMatch(String pattern, String str);
	public static void main(String[] args) {
		
	}
	
	
	static class SolutionII extends WordPatternII {
		public boolean wordPatternMatch(String pattern, String str) {
	        Map<Character, String> ptStr = new HashMap<>();
	        Set<String> strs = new HashSet<>();
	        return bt(pattern, 0, str, 0, ptStr, strs);
	    }
	    
	    private boolean bt(String pattern, int pidx, String str, int sidx, Map<Character, String> ptStr, Set<String> strs) {
	        // termination'
	        if (pidx == pattern.length() && sidx == str.length())
	            return true;
	        else if (pidx == pattern.length() || sidx == str.length())
	            return false;
	        char pchar = pattern.charAt(pidx);
	        String s = ptStr.get(pchar);
	        if (s != null) {
	            int end = sidx + s.length();
	            return end <= str.length() && str.substring(sidx, end).equals(s) && bt(pattern, pidx+1, str, end, ptStr, strs);
	        } else {
	            for (int end = sidx+1; end <= str.length(); end++) {
	                String sub = str.substring(sidx, end);
	                if (!strs.contains(sub)) {
	                    ptStr.put(pchar, sub);  // modify
	                    strs.add(sub);          // modify
	                    if (bt(pattern, pidx+1, str, end, ptStr, strs))
	                        return true;
	                    ptStr.remove(pchar);    // restore
	                    strs.remove(sub);       // restore
	                }
	            }
	            return false;
	        }
	    }
	}
	
}
