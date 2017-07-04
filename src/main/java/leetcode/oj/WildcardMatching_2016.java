package leetcode.oj;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class WildcardMatching_2016 {
	public abstract boolean isMatch(String s, String p);
	
	public static void main(String[] args) {
		WildcardMatching_2016 instance = new SolutionIII();
		String s, p;
		boolean result;
		long t1, t2;
		
//		s = "aab"; p = "c*a*b"; // false
//		result = instance.isMatch(s, p);
//		System.out.println("result=" + result);
		
//		s = "ab"; p = "?*"; // true
//		result = instance.isMatch(s, p);
//		System.out.println("result=" + result);
		
//		s = "aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba"; p = "a*******b"; // true
//		t1 = System.currentTimeMillis();
//		result = instance.isMatch(s, p);
//		t2 = System.currentTimeMillis();
//		System.out.println("result=" + result);
//		System.out.println(String.format("total time=%,dms", (t2 - t1)));
		
//		s = "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb"; 
//		p = "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb";
//		t1 = System.currentTimeMillis();
//		result = instance.isMatch(s, p);
//		t2 = System.currentTimeMillis();
//		System.out.println("result=" + result);
//		System.out.println(String.format("total time=%,dms", (t2 - t1)));
		
		s = "aabbbbaababbabababaabbbbabbabbaabbbabbbabaabbaaaababababbababbabbbbabaaabaaabaabbaaaabbbbabaaabbbbbabbbaabbbbbabaabababaaabaaababaababbaaabaabbabaababbabababaaababbabbabaabbbbabbbbabaabbaababaaabababbab"; 
		p = "a*b*a*b*aaaa*abaaa**b*a***b*a*bb****ba*ba*b******a********a**baba*ab***a***bbba*b**a*b*ba*a*aaaa*ab**";
		t1 = System.currentTimeMillis();
		result = instance.isMatch(s, p);
		t2 = System.currentTimeMillis();
		System.out.println("result=" + result);
		System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
	
	public static class SolutionIII extends WildcardMatching_2016 {
		private Set<List<Integer>> cache = new HashSet<>();
		public boolean isMatch(String s, String p) {
	        if (s == null && p == null)
	            return true;
	        else if (s == null || p == null)
	            return false;
	        StringBuilder builder = new StringBuilder();
	        for (int i = 0; i < p.length(); i++) {
	            char ch = p.charAt(i);
	            if (ch == '*' && i-1 >= 0 && p.charAt(i-1) == '*')
	                continue;
	            else
	                builder.append(ch);
	        }
System.out.println(builder);
	        return recur(s, 0, builder.toString(), 0);
	    }
	    
	    private boolean recur(String s, int i, String p, int j) {
	    	List<Integer> list = new ArrayList<>(2);
	    	list.add(i);
	    	list.add(j);
	    	if (!cache.add(list))
	    		return false;
	    	
	        // termination
	        if (i == s.length()) {
	            if (j == p.length())
	                return true;
	            else {
	                for (int x = j; x < p.length(); x++) {
	                    if (p.charAt(x) != '*')
	                        return false;
	                }
	                return true;
	            }
	        } else if (j == p.length()) { // p reaches end but s doesn't
	            return false;
	        }
	        
	        char schar = s.charAt(i), pchar = p.charAt(j);
	        if (pchar == '?') {
	            return recur(s, i+1, p, j+1);
	        } else if (pchar == '*') {
	            for (int x = i; x <= s.length(); x++) { // must include x = s.length()
	                if (recur(s, x, p, j+1))
	                    return true;
	            }
	            return false;
	        } else {
	            if (pchar != schar)
	                return false;
	            else
	                return recur(s, i+1, p, j+1);
	        }
	    }
	}
	
	
	// Solution: TLE
	// merge continuous * into 1, improved certain cases but still TLE
	public static class SolutionII extends WildcardMatching_2016 {
		public boolean isMatch(String s, String p) {
	        if (s == null && p == null)
	            return true;
	        else if (s == null || p == null)
	            return false;
	        StringBuilder builder = new StringBuilder();
	        for (int i = 0; i < p.length(); i++) {
	            char ch = p.charAt(i);
	            if (ch == '*' && i-1 >= 0 && p.charAt(i-1) == '*')
	                continue;
	            else
	                builder.append(ch);
	        }
	        return recur(s, 0, builder.toString(), 0);
	    }
	    
	    private boolean recur(String s, int i, String p, int j) {
	        // termination
	        if (i == s.length()) {
	            if (j == p.length())
	                return true;
	            else {
	                for (int x = j; x < p.length(); x++) {
	                    if (p.charAt(x) != '*')
	                        return false;
	                }
	                return true;
	            }
	        } else if (j == p.length()) { // p reaches end but s doesn't
	            return false;
	        }
	        
	        char schar = s.charAt(i), pchar = p.charAt(j);
	        if (pchar == '?') {
	            return recur(s, i+1, p, j+1);
	        } else if (pchar == '*') {
	            for (int x = i; x <= s.length(); x++) { // must include x = s.length()
	                if (recur(s, x, p, j+1))
	                    return true;
	            }
	            return false;
	        } else {
	            if (pchar != schar)
	                return false;
	            else
	                return recur(s, i+1, p, j+1);
	        }
	    }
	}
	
	
	// Solution I: TLE, Logic Correct
	public static class SolutionI extends WildcardMatching_2016 {

		public boolean isMatch(String s, String p) {
	        if (s == null && p == null)
	            return true;
	        else if (s == null || p == null)
	            return false;
	        return recur(s, 0, p, 0);
	    }
	    
	    private boolean recur(String s, int i, String p, int j) {
	        // termination
	        if (i == s.length()) {
	            if (j == p.length())
	                return true;
	            else {
	                for (int x = j; x < p.length(); x++) {
	                    if (p.charAt(x) != '*')
	                        return false;
	                }
	                return true;
	            }
	        } else if (j == p.length()) { // p reaches end but s doesn't
	            return false;
	        }
	        
	        char schar = s.charAt(i), pchar = p.charAt(j);
	        if (pchar == '?') {
	            return recur(s, i+1, p, j+1);
	        } else if (pchar == '*') {
	            for (int x = i; x <= s.length(); x++) { // must include x = s.length()
	                if (recur(s, x, p, j+1))
	                    return true;
	            }
	            return false;
	        } else {
	            if (pchar != schar)
	                return false;
	            else
	                return recur(s, i+1, p, j+1);
	        }
	    }
		
	}
}
