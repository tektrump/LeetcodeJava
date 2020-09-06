package tektrup.leetcode.oj;


import java.util.HashMap;
import java.util.Map;

public class WordPattern {
	
	public boolean wordPattern(String pattern, String str) {
        String[] splits = str.split(" ");
        if (pattern.length() != splits.length)
            return false;
            
        Map<Character, String> charWord = new HashMap<>();
        char[] chars = pattern.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            System.out.println(c);
            String word = charWord.get(c);
            if (word != null) {
            	System.out.println("word=" + word);
                if (!word.equals(splits[i]))
                    return false;
            } else {
                charWord.put(c, splits[i]);
            }
        }
        
        return true;
    }
	
	/*
	public boolean wordPattern(String pattern, String str) {
        Map<Character, String> charWord = new HashMap<>();
        char[] chars = pattern.toCharArray();
        return wordPattern(chars, 0, str, 0, charWord);
    }
    
    private boolean wordPattern(char[] chars, int cIndex, String s, int sIndex, Map<Character, String> charWord) {
        if (cIndex == chars.length && sIndex == s.length())
            return true;
        else if (cIndex == chars.length || sIndex == s.length())
            return false;
        char c = chars[cIndex];
        String word = charWord.get(c);
        if (word != null) {
            int end = sIndex + word.length();
            if (end > s.length())
                return false;
            String sub = s.substring(sIndex, end);
            if (!sub.equals(word))
                return false;
            else
                return wordPattern(chars, cIndex+1, s, sIndex+word.length(), charWord);
        } else {
            for (int len = 1; sIndex + len <= s.length(); len++) {
                word = s.substring(sIndex, sIndex + len);
                charWord.put(c, word);
                if (wordPattern(chars, cIndex+1, s, sIndex+len, charWord))
                    return true;
                charWord.remove(c);
            }
            return false;
        }
    }
    */

    public static void main(String[] args) {
    	WordPattern instance = new WordPattern();
    	String pattern, str;
    	
//    	pattern = "abba"; str = "dog cat cat dog"; // true
    	pattern = "abba"; str = "dog cat cat fish"; // true
    	
    	boolean result = instance.wordPattern(pattern, str);
    	
    	System.out.println("result = " + result);
	}

}
