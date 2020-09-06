package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RepeatedDNASequences {
	
	private static final Map<Character, Integer> charNum = new HashMap<>();
    static {
        charNum.put('A', 0);
        charNum.put('C', 1);
        charNum.put('G', 2);
        charNum.put('T', 3);
    }
    private static final Map<Integer, Set<String>> hashStrs = new HashMap<>();
    private static final Map<Integer, List<Integer>> hashRange = new HashMap<>();
    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s.length() < 10)
            return Collections.emptyList();
        Set<String> resultsSet = new HashSet<>();
        int hash = 0, high = (int)Math.pow(4, 10);
        int i = 0;
        while (i < 9)
            hash = hash*4 + charNum.get(s.charAt(i++));
        while (i < s.length()) {
            hash = (hash % high)*4 + charNum.get(s.charAt(i));
            List<Integer> range = hashRange.get(hash);
            if (range == null) {
                range = new ArrayList<>(2);
                range.add(i - 9);
                range.add(i);
                hashRange.put(hash, range);
            } else {
                String sub = s.substring(i-9, i+1);
                if (exist(sub, hash, s))
                	resultsSet.add(sub);
            }
            i++;
        }
        return new ArrayList<>(resultsSet);
    }
    
    private boolean exist(String sub, int hash, String s) {
        Set<String> strs = hashStrs.get(hash);
        if (strs == null) {
            strs = new HashSet<>();
            hashStrs.put(hash, strs);
            List<Integer> range = hashRange.get(hash);
            strs.add(s.substring(range.get(0), range.get(1)+1));
        }
        return !strs.add(sub);
    }
    
    public static void main(String[] args) {
    	RepeatedDNASequences instance = new RepeatedDNASequences();
    	String s;
    	
//    	s = "AAAAAAAAAAA";
    	
    	s = "GAGAGAGAGAGA";
    	
    	List<String> results = instance.findRepeatedDnaSequences(s);
    	System.out.println("results=" + results);
	}

}
