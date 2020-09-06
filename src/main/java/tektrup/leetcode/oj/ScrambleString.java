package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrambleString {
	
	private final Map<List<Integer>, Boolean> cache = new HashMap<>();
    public boolean isScramble(String s1, String s2) {
        return match(s1, 0, s1.length(), s2, 0, s2.length());    
    }
    
    private boolean match(String str1, int s1, int e1, String str2, int s2, int e2) {
        List<Integer> key = new ArrayList<>(4);
        key.add(s1);
        key.add(e1);
        key.add(s2);
        key.add(e2);
        Boolean result = cache.get(key);
        if (result != null)
            return result;
        result = true;
        for (int i = 0; i < e1 - s1; i++) {
            if (str1.charAt(s1 + i) != str2.charAt(s2 + i)) {
                result = false;
                break;
            }
        }
        if (!result) {
            for (int len = 1; len < e1 - s1; len++) {
                int d1 = s1 + len, d2 = s2 + len;
                if (match(str1, s1, d1, str2, s2, d2) &&
                    match(str1, d1, e1, str2, d2, e2)) {
                    result = true;
                    break;
                }
                d2 = e2 - len;
                if (match(str1, s1, d1, str2, d2, e2) &&
                    match(str1, d1, e1, str2, s2, d2)) {
                    result = true;
                    break;
                }
            }
        }
        cache.put(key, result);
        return result;
    }
	
    // Solution I: TLE
	/*	
 	private final Map<List<String>, Boolean> cache = new HashMap<>();
    public boolean isScramble(String s1, String s2) {
        List<String> key = new ArrayList<>(2);
        key.add(s1);
        key.add(s2);
        Boolean result = cache.get(key);
        if (result != null)
            return result;
        if(s1.equals(s2))
            result = true;
        else {
            result = false;
            for (int len = 1; len <= s1.length()-1; len++) {
                String l1, r1, l2, r2;
                l1 = s1.substring(0, len);
                r1 = s1.substring(len, s1.length());
                if (isScramble(l1, s2.substring(0, len))) {
                    r2 = s2.substring(len, s2.length());
                    if (isScramble(r1, r2)) {
                        result = true;
                        break;
                    }
                } else if (isScramble(l1, s2.substring(s2.length() - len, s2.length()))) {
                    l2 = s2.substring(0, s2.length() - len);
                    if (isScramble(r1, l2)) {
                        result = true;
                        break;
                    }
                }
            }
        }
        cache.put(key, result);
        return result;
    }
    */
    
    public static void main(String[] args) {
    	ScrambleString instance = new ScrambleString();
    	String s1, s2;
    	
    	// true
//    	s1 = "great";
//    	s2 = "rgeat";
    	
    	// true
//    	s1 = "abab";
//    	s2 = "baba";
    	
    	s1 = "pcighfdjnbwfkohtklrecxnooxyipj";
    	s2 = "npodkfchrfpxliocgtnykhxwjbojie";
    	
    	long t1 = System.currentTimeMillis();
    	boolean result = instance.isScramble(s1, s2);
    	long t2 = System.currentTimeMillis();
    	System.out.println("result=" + result);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
}
