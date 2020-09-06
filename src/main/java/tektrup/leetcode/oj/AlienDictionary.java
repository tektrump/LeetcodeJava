package tektrup.leetcode.oj;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class AlienDictionary {
	public abstract String alienOrder(String[] words);
	public static void main(String[] args) {
		AlienDictionary instance = new SolutionI();
		String[] words;
		String result;
		
		// wertf
//		words = new String[]{"wrt","wrf","er","ett","rftt"};
		
		// ""
		words = new String[]{"a", "b", "a"};
		
		result = instance.alienOrder(words);
		System.out.println("result=" + result);
	}
	
	
	static class SolutionI  extends AlienDictionary {
		
		public class TrieNode {
	        char last;
	        Map<Character, TrieNode> map = new HashMap<>();
	    }
	    
	    public String alienOrder(String[] words) {
	        Map<Character, Set<Character>> charPres = new HashMap<>();
	        TrieNode root = new TrieNode();
	        for (String w : words) {
	            TrieNode curr = root;
	            for (char ch : w.toCharArray()) {
	                TrieNode next = curr.map.get(ch);
	                if (next == null) {
	                    // add all existing chars as predecessors of ch
	                    Set<Character> pres = charPres.get(ch);
	                    if (pres == null) {
	                        pres = new HashSet<>();
	                        charPres.put(ch, pres);
	                    }
	                    pres.addAll(curr.map.keySet());
	                    next = new TrieNode();
	                    curr.map.put(ch, next);
	                    curr.last = ch;
	                } else {
	                    if (curr.last != ch)
	                        return "";
	                }
	                curr = next;
	            }
	        }
	        
	        StringBuilder builder = new StringBuilder();
	        Set<Character> used = new HashSet<>();
	        boolean stop = false;
	        while (!charPres.isEmpty() && !stop) {
	            stop = true;
	            Iterator<Map.Entry<Character, Set<Character>>> itr = charPres.entrySet().iterator();
	            while (itr.hasNext()) {
	                Map.Entry<Character, Set<Character>> entry = itr.next();
	                char ch = entry.getKey();
	                Set<Character> pres = entry.getValue();
	                pres.removeAll(used);
	                if (pres.isEmpty()) {
	                    builder.append(ch);
	                    used.add(ch);
	                    itr.remove();
	                    stop = false;
	                }
	            }
	        }
	        if (charPres.isEmpty())
	            return builder.toString();
	        else
	            return "";
	    }
	}
}
