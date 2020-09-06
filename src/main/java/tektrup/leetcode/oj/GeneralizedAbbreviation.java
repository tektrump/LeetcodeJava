package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class GeneralizedAbbreviation {
	public abstract List<String> generateAbbreviations(String word);
	public static void main(String[] args) {
		GeneralizedAbbreviation instance = new SolutionIII();
		String word;
		List<String> results;
		
//		word = "word";
		
		word = "aaa";
		
		results = instance.generateAbbreviations(word);
		System.out.println("results=" + results);
	}
	
	
	static class SolutionIV extends GeneralizedAbbreviation {
		public List<String> generateAbbreviations(String word) {
	        if (word.isEmpty())
	            return Collections.singletonList("");
	            
	        List<StringBuilder> list = new LinkedList<>();
	        bt(new StringBuilder(), word, 0, list);
	        List<String> rets = new LinkedList<>();
	        for (StringBuilder b : list) {
	            int i = 0;
	            while (i + 1 < b.length()) {
	                char curr = b.charAt(i), next = b.charAt(i+1);
	                if (Character.isDigit(curr) && Character.isDigit(next)) {
	                    b.deleteCharAt(i);
	                    b.deleteCharAt(i);
	                    String num = String.valueOf((curr-'0') + (next-'0'));
	                    b.insert(i, num);
	                }
	                i++;
	            }
	            rets.add(b.toString());
	        }
	        
	        return rets;
	    }
	    
	    private void bt(StringBuilder b, String word, int idx, List<StringBuilder> list) {
	        if (idx == word.length()) {
	            list.add(new StringBuilder(b));
	        } else {
	            b.append(word.charAt(idx)); // modify
	            bt(b, word, idx+1, list);
	            b.deleteCharAt(b.length()-1);   // restore
	            b.append(1); // modify
	            bt(b, word, idx+1, list);
	            b.deleteCharAt(b.length()-1);   // restore
	        }
	    }
	}
	
	
	static class SolutionIII extends GeneralizedAbbreviation {
		public List<String> generateAbbreviations(String word) {
	        if (word == null || word.isEmpty())
	            return Collections.emptyList();
	        List<String> rets = new LinkedList<>();
	        Deque<String> q = new LinkedList<>();
	        q.add(word);
	        Set<String> used = new HashSet<>();
	        used.add(word); // just to follow the convention
	        while (!q.isEmpty()) {
	            String s = q.removeFirst();
if (s.equals("a2"))
	System.out.println();
	            rets.add(s);
	            for (int m = 0; m < s.length(); m++) {
	                int num = 1;
	                StringBuilder left  = new StringBuilder(s.substring(0, m));
	                StringBuilder right = new StringBuilder(s.substring(m+1, s.length()));
	                StringBuilder lnum = new StringBuilder();
	                while (left.length() > 0 && Character.isDigit(left.charAt(left.length()-1))) {
	                    lnum.insert(0, left.charAt(left.length()-1));
	                    left.deleteCharAt(left.length()-1);
	                }
	                if (lnum.length() > 0)
	                    num += Integer.valueOf(lnum.toString());
	                StringBuilder rnum = new StringBuilder();
	                while (right.length() > 0 && Character.isDigit(right.charAt(0))) {
	                    rnum.append(right.charAt(0));
	                    right.deleteCharAt(0);
	                }
	                if (rnum.length() > 0)
	                    num += Integer.valueOf(rnum.toString());
	                String nextS = new StringBuilder(left).append(num).append(right).toString();
	                if (used.add(nextS))
	                    q.add(nextS);
	            }
	        }
	        
	        return rets;
	    }
	}
	
	
	static class SolutionII extends GeneralizedAbbreviation {
		public List<String> generateAbbreviations(String word) {
	        List<String> list = new ArrayList<>();
	        list.add("");
	        for (int i = 0; i < word.length(); i++) {
	            List<String> nextList = new ArrayList<>();
	            char ch = word.charAt(i);
	            for (String abbr : list) {
	                nextList.add(abbr + ch);
	                if (!abbr.isEmpty() && Character.isDigit(abbr.charAt(abbr.length()-1))) {
	                    int j = abbr.length()-1;
	                    while (j >= 0 && Character.isDigit(abbr.charAt(j)))
	                        j--;
	                    String head = abbr.substring(0, j+1);
	                    nextList.add(head + (1 + Integer.valueOf(abbr.substring(j+1, abbr.length()))));
	                } else
	                    nextList.add(abbr + "1");
	            }
	            list = nextList;
	        }
	        return list;
	    }
	}
}
