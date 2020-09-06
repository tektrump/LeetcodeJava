package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PalindromePermutationII {

	public List<String> generatePalindromes(String s) {
        Map<Character, Integer> charCount = new HashMap<>();
        int odd = 0;
        for (char c : s.toCharArray()) {
            Integer count = charCount.get(c);
            if (count == null)
                count = 0;
            charCount.put(c, ++count);
            if (count % 2 == 0)
                odd--;
            else
                odd++;
        }
        if (odd > 1) // no permutation available
            return Collections.emptyList();
            
        List<StringBuilder> builders = new ArrayList<>();
        dp(new StringBuilder(), charCount, builders, s.length());
        List<String> results = new ArrayList<>();
        for (StringBuilder builder : builders)
            results.add(builder.toString());
        return results;
    }
    
    private void dp(StringBuilder builder, Map<Character, Integer> charCount, List<StringBuilder> builders, int len) {
        if (builder.length() == len) {
            builders.add(builder);
        } else if (builder.length() == len - 1) {
            StringBuilder next = new StringBuilder(builder);
            Character c = charCount.keySet().iterator().next();
            next.insert(len/2, c);
            builders.add(next);
        } else {
            // ConcurrentModificationException
//            for (Character c : charCount.keySet()) {
//        	for (Character c : new HashSet<>(charCount.keySet())) {
        	Iterator<Map.Entry<Character, Integer>> itr = charCount.entrySet().iterator();
        	while (itr.hasNext()) {
        		Map.Entry<Character, Integer> entry = itr.next();
        		char c = entry.getKey();
                Integer count = entry.getValue();
                if (count >= 2) {
                    StringBuilder next = new StringBuilder(builder);
                    next.insert(0, c);
                    next.append(c);
                    // modify
                    count -= 2;
                    if (count == 0)
                        itr.remove();
                    else
                        entry.setValue(count);
                    dp(next, charCount, builders, len);
                    // restore
                    charCount.put(c, count + 2);
                }
            }
        }
    }
    
    public static void main(String[] args) {
    	PalindromePermutationII instance = new PalindromePermutationII();
    	String s;
    	
//    	s = "aabb"; // "abba", "baab"
    	
//    	s = "abc";	// empty
    	
    	s = "aaa";
    	
    	List<String> results = instance.generatePalindromes(s);
    	System.out.println("results=" + results);
	}
}
