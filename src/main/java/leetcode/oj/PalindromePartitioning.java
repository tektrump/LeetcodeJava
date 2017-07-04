package leetcode.oj;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class PalindromePartitioning {
	public abstract List<List<String>> partition(String s);
	public static void main(String[] args) {
    	PalindromePartitioning instance = new SolutionV();
    	String s;
    	
    	// [["a","a","b"],["aa","b"]]
    	s = "aab";
    	
//    	s = "ab";
    	
    	s = "aabbccdd";
    	
    	long t1 = System.currentTimeMillis();
    	List<List<String>> results = instance.partition(s);
    	long t2 = System.currentTimeMillis();
    	System.out.println("results=" + results);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
	
	
	// Solution V: 
	// use backtracking.
	static class SolutionV extends PalindromePartitioning {
		public List<List<String>> partition(String s) {
	        List<List<String>> results = new LinkedList<>();
	        List<String> list = new LinkedList<>();
	        bt(s, 0, list, results);
	        return results;
	    }
	    
	    private void bt(String s, int l, List<String> list, List<List<String>> results) {
	        if (l == s.length())
	            results.add(new LinkedList<>(list));
	        else {
	            for (int r = l; r < s.length(); r++) {
	                if (isPalindrome(s, l, r)) {
	                    list.add(s.substring(l, r+1));  // modify
	                    bt(s, r+1, list, results);
	                    list.remove(list.size()-1); // restore
	                }
	            }
	        }
	    }
	    
	    private boolean isPalindrome(String s, int l, int r) {
	        while (l < r) {
	            if (s.charAt(l++) != s.charAt(r--))
	                return false;
	        }
	        return true;
	    }
	}
	
	
	static class SolutionIV extends PalindromePartitioning {
		private Map<List<Integer>, List<List<int[]>>> cache = new HashMap<>();
	    public List<List<String>> partition(String s) {
	        if (s == null || s.isEmpty())
	            return Collections.emptyList(); 
	        List<List<int[]>> ranges = dp(s, 0, s.length()-1);
	        List<List<String>> results = new ArrayList<>();
	        for (List<int[]> range : ranges) {
	            List<String> result = new ArrayList<>();
	            for (int[] r : range) {
	                result.add(s.substring(r[0], r[1]+1));
	                results.add(result);
	            }
	        }
	        return results;
	    }
	    
	    // guarantee i & j are valid
	    private List<List<int[]>> dp(String s, int i, int j) {
	        List<Integer> key = new ArrayList<>(2);
	        key.add(i);
	        key.add(j);
	        List<List<int[]>> results = cache.get(key);
	        if (results != null)
	            return results;
	        results = new ArrayList<>();
	        // m: the end of left substring
	        for (int m = i; m <= j; m++) {
	            if (isPalindrome(s, i, m)) {
	                if (m == j) {
	                    List<int[]> result = new ArrayList<>();
	                    result.add(new int[]{i, m});
	                    results.add(result);
	                } else {
	                    List<List<int[]>> subs = dp(s, m+1, j);
	                    for (List<int[]> sub : subs) {
	                        List<int[]> result = new ArrayList<>();
	                        result.add(new int[]{i, m});
	                        result.addAll(sub);
	                        results.add(result);
	                    }
	                }
	            }
	        }
	        cache.put(key, results);
	        return results;
	    }
	    
	    private boolean isPalindrome(String s, int i, int j) {
	        while (i < j) {
	            if (s.charAt(i) != s.charAt(j))
	                return false;
	            i++;
	            j--;
	        }
	        return true;
	    }
	}
	
	
	// Solution III Accepted
    // dp + memoization.
    // improvement from solutionI: use indices instead of substring as state variables
	static class SolutionIII extends PalindromePartitioning {
		private Map<List<Integer>, List<List<String>>> cache = new HashMap<>();
	    public List<List<String>> partition(String s) {
	        if (s == null || s.isEmpty())
	            return Collections.emptyList();
	        return recur(s, 0, s.length()-1);
	    }
	    
	    // guarantee l & r are valid
	    private List<List<String>> recur(String s, int l, int r) {
	        List<Integer> key = new ArrayList<>();
	        key.add(l);
	        key.add(r);
	        List<List<String>> results = cache.get(key);
	        if (results != null)
	            return results;
	        results = new ArrayList<>();
	        if (l == r) {
	            List<String> result = new ArrayList<>();
	            result.add(s.substring(l, r+1));
	            results.add(result);
	        } else {
	            for (int d = l; d <= r; d++) {
	                if (isPalindrome(s, l, d)) {
	                    if (d == r) {
	                        List<String> result = new ArrayList<>();
	                        result.add(s.substring(l, r+1));
	                        results.add(result);
	                    } else {
	                        List<List<String>> subs = recur(s, d+1, r);
	                        for (List<String> sub : subs) {
	                            List<String> result = new ArrayList<>();
	                            result.add(s.substring(l, d+1));
	                            result.addAll(sub);
	                            results.add(result);
	                        }
	                    }
	                }
	            }
	        }
	        cache.put(key, results);
	        return results;
	    }
	    
	    private boolean isPalindrome(String s, int i, int j) {
	        while (i < j) {
	            if (s.charAt(i) != s.charAt(j))
	                return false;
	            i++;
	            j--;
	        }
	        return true;
	    }
	}
	
	
	// Solution II: TLE
	static class SolutionII extends PalindromePartitioning {
		 private final Map<String, List<List<String>>> cache = new HashMap<>();
		    private final Map<String, Boolean> palindromes = new HashMap<>();
		    public List<List<String>> partition(String s) {
		        List<List<String>> results = cache.get(s);
		        if (results != null)
		            return results;
		        results = new ArrayList<>();
		        for (int l = 0; l < s.length(); l++) {
		            for (int m = l + 1; m <= s.length(); m++) {
		                String s1 = s.substring(l, m);
		                if (isPalindrome(s1)) {
		                    // boundary
		                    if (m == s.length()) {
		                        List<String> result = new ArrayList<>();
		                        result.add(s1);
		                        results.add(result);
		                    } else {
		                        String s2 = s.substring(m, s.length());
		                        List<List<String>> subResults = partition(s2);
		                        for (List<String> subResult : subResults) {
		                            List<String> result = new ArrayList<>();
		                            result.add(s1);
		                            result.addAll(subResult);
		                            results.add(result);
		                        }
		                    }
		                }
		            }
		        }
		        cache.put(s, results);
		        return results;
		    }
		    
		    private boolean isPalindrome(String s) {
		        Boolean result = palindromes.get(s);
		        if (result != null)
		            return result;
		        result = true;
		        int i = 0, j = s.length()-1;
		        while (i < j) {
		            if (s.charAt(i++) != s.charAt(j--)) {
		                result = false;
		                break;
		            }
		        }
		        palindromes.put(s, result);
		        return result;
		    }
	}
	
	
	// Solution I: Old
	static class SolutionI extends PalindromePartitioning {
		private Map<Integer, List<List<Integer>>> startRanges = new HashMap<>();
	    public List<List<String>> partition(String s) {
	        for (int i = 0; i < s.length(); i++)
	            addPalindromes(s, i);
	        List<List<String>> results = new ArrayList<>();
	        List<List<Integer>> list = new ArrayList<>();
	        list.addAll(startRanges.get(0));
	        while (!list.isEmpty()) {
	            List<List<Integer>> nextList = new ArrayList<>();
	            for (List<Integer> range : list) {
	                int end = range.get(1);
	                if (end == s.length() - 1) {
	                    results.add(getResult(s, range));
	                } else {
	                    List<List<Integer>> subRanges = startRanges.get(end+1);
	                    if (subRanges != null) {
	                        for (List<Integer> subRange : subRanges) {
	                            List<Integer> nextRange = new ArrayList<>(range);
	                            nextRange.addAll(subRange);
	                            nextList.add(nextRange);
	                        }
	                    }
	                }
	            }
	            list = nextList;
	        }
	        return results;
	    }
	    
	    private void addPalindromes(String s, int idx) {
	        // include curr idx
	        int l = idx, r = idx + 1;
	        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {addRange(l--, r++);}
	        // exclude curr idx
	        l = idx; r = idx;
	        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {addRange(l--, r++);}
	    }
	    
	    private void addRange(int start, int end) {
	        List<List<Integer>> ranges = startRanges.get(start);
	        if (ranges == null) {
	            ranges = new ArrayList<>();
	            startRanges.put(start, ranges);
	        }
	        List<Integer> range = new ArrayList<>(2);
	        range.add(start);
	        range.add(end);
	        ranges.add(range);
	    }
	    
	    private List<String> getResult(String s, List<Integer> range) {
	        List<String> results = new ArrayList<>();
	        int i = 0;
	        while (i < range.size()) {
	            results.add(s.substring(range.get(i-1), range.get(i)+1));
	            i++;
	        }
	        return results;
	    }
	}
    
}
