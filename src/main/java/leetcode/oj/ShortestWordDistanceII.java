package leetcode.oj;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ShortestWordDistanceII {
	
	public static void main(String[] args) {
		String[] words = {"a", "b"};
		
		
		WordDistance instance = new WordDistance(words);
		System.out.println(instance.shortest("a", "b"));
		System.out.println(instance.shortest("b", "a"));
	}

	// Solution I
	static public class WordDistance {
		private Map<Set<String>, Integer> cache;
	    private String[] words;
	    public WordDistance(String[] words) {
	        cache = new HashMap<>();
	        this.words = words;
	    }
	
	    public int shortest(String word1, String word2) {
	        Set<String> key = new HashSet<>();
	        key.add(word1);
	        key.add(word2);
	        Integer res = cache.get(key);
	        if (res != null)
	            return res;
	            
	        res = Integer.MAX_VALUE;
	        List<Integer> indices1 = new LinkedList<>();
	        TreeSet<Integer> indices2 = new TreeSet<>();
	        for (int i = 0; i < words.length; i++) {
	            String w = words[i];
	            if (w.equals(word1))
	                indices1.add(i);
	            else if (w.equals(word2))
	                indices2.add(i);
	        }
	        for (int idx1 : indices1) {
	            Integer floor = indices2.floor(idx1);
	            if (floor != null)
	                res = Math.min(res, idx1 - floor);
	            Integer ceiling = indices2.ceiling(idx1);
	            if (ceiling != null)
	                res = Math.min(res, ceiling - idx1);
	        }
	        
	        cache.put(key, res);
	        return res;
	    }
	}
}
