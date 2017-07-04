package leetcode.oj;


import java.util.ArrayList;
import java.util.List;

public abstract class TextJustification {
	public abstract List<String> fullJustify(String[] words, int maxWidth);
	public static void main(String[] args) {
		TextJustification instance = new SolutionII();
		String[] words; int maxWidth;
		List<String> results;
		
		words = new String[]{"a","b","c","d","e"}; maxWidth = 3;
		
		results = instance.fullJustify(words, maxWidth);
		System.out.println("results=" + results);
	}
	
	
	static class SolutionII extends TextJustification {
		public List<String> fullJustify(String[] words, int maxWidth) {
	        List<String> results = new ArrayList<>();
	        List<String> list = new ArrayList<>();
	        int len = 0;
	        for (String w : words) {
	            if (list.isEmpty()) {
	                list.add(w);
	                len = w.length();
	            } else {
	                if (len + 1 + w.length() <= maxWidth)
	                    list.add(w);
	                else {
	                    results.add(build(list, maxWidth));
	                    list.clear();
	                    list.add(w);
	                    len = w.length();
	                }
	            }
	        }
	        StringBuilder builder = new StringBuilder();
	        for (String w : list) {
	            if (builder.length() > 0)
	                builder.append(' ');
	            builder.append(w);
	        }
	        int padding = maxWidth - builder.length();
	        for (int i = 0; i < padding; i++)
	            builder.append(' ');
	        results.add(builder.toString());
	        return results;
	    }
	    
	    private String build(List<String> list, int width) {
	        int gaps = list.size() - 1;
	        int wordLen = 0;
	        for (String w : list)
	            wordLen += w.length();
	        int spaces = width - wordLen;
	        if (gaps > 1)
	            spaces = (width - wordLen)/gaps;
	        int extra = width - wordLen - spaces*gaps;
	        StringBuilder builder = new StringBuilder();
	        for (String w : list) {
	            if (builder.length() > 0) {
	                for (int i = 0; i < spaces; i++)
	                    builder.append(' ');
	                if (extra > 0) {
	                    builder.append(' ');
	                    extra--;
	                }
	            }
	            builder.append(w);
	        }
	        return builder.toString();
	    }
	}
}
