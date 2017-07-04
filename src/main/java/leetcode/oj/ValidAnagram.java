package leetcode.oj;


import leetcode.util.annotations.Leetcode;

@Leetcode(date="2016/05/04", url="https://leetcode.com/problemset/algorithms/")
public class ValidAnagram {
	
	// Solution III: Best
	// instead of map, use an array with 26 integers as the most efficient way to store counts.
    public boolean isAnagram(String s, String t) {
    	// this is only possible since problems states all chars are lower-case alphabets
        int[] counts = new int[26];
        for (char c : s.toCharArray()) counts[c - 'a']++;
        for (char c : t.toCharArray()) counts[c - 'a']--;
        for (int count : counts)
            if (count != 0) 
                return false;
        return true;
    }
    
    // Solution II: Accepted
    // improve memory usage from solution I: use only 1 map
    /*
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            Integer count = charCount.get(c);
            if (count == null)
                count = 0;
            charCount.put(c, ++count);
        }
        for (char c : t.toCharArray()) {
            Integer count = charCount.get(c);
            if (count == null)
                return false;
            if (count == 1)
                charCount.remove(c);
            else
                charCount.put(c, --count);
        }
        return charCount.isEmpty();
    }
    */
    
    // Solution I: Accepted
    /*
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> charCount1 = new HashMap<>();
        for (char c : s.toCharArray()) {
            Integer count = charCount1.get(c);
            if (count == null)
                count = 0;
            charCount1.put(c, ++count);
        }
        Map<Character, Integer> charCount2 = new HashMap<>();
        for (char c : t.toCharArray()) {
            Integer count = charCount2.get(c);
            if (count == null)
                count = 0;
            charCount2.put(c, ++count);
        }
        return charCount1.equals(charCount2);
    }
    */
    
    public static void main(String[] args) {
    	ValidAnagram instance = new ValidAnagram();
    	String s, t;
    	
    	// true
//    	s = "anagram";
//    	t = "nagaram";
    	
    	// false
    	s = "anagram";
    	t = "";
    	
    	boolean result = instance.isAnagram(s, t);
    	System.out.println("results=" + result);
	}

}
