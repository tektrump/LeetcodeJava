package leetcode.oj;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class LetterCombinationsOfAPhoneNumber {
	
	private static final Map<Character, List<Character>> dLetters = new HashMap<>();
    static {
        // NOTE: statically initialize the map; 
        // variable dLetters and function initMap() both have to be static.
        initMap(); 
    }
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty())
            return Collections.emptyList();
        List<String> results = new ArrayList<>();
        char[] chars = new char[digits.length()];
        Stack<List<Character>> stack = new Stack<>();
        stack.push(dLetters.get(digits.charAt(0)));
        Stack<Integer> counts = new Stack<>();
        counts.push(stack.peek().size()-1);
        Stack<Integer> indices = new Stack<>();
        indices.push(0);
        while (!stack.isEmpty()) {
            List<Character> letters = stack.peek();
            int count = counts.pop();
            int index = indices.peek();
            if (count < 0) {
                stack.pop();
                indices.pop();
            } else {
System.out.println(letters);
                char letter = letters.get(count--);
                counts.push(count);
                chars[index] = letter;
                if (index == digits.length() - 1)
                    results.add(new String(chars));
                else {
                    stack.push(dLetters.get(digits.charAt(index+1)));
                    counts.push(stack.peek().size()-1);
                    indices.push(index+1);
                }
            }
        }
        return results;
    }
    
    // NOTE: programmatically build a map between digits and letters; 
    // save a lot of typing compared to hand-coded map!
    private static void initMap() {
        char letter = 'a';
        for (char d = '2'; d <= '9'; d++) {
            List<Character> letters = new ArrayList<>();
            if (d == '7' || d == '9') {
                for (int i = 0; i < 4; letters.add(letter++), i++);
            } else {
                for (int i = 0; i < 3; letters.add(letter++), i++);
            }
            dLetters.put(d, letters);
        }
        List<Character> letters = new ArrayList<>(1);
        letters.add(' ');
        dLetters.put('0', letters);
    }

    public static void main(String[] args) {
    	LetterCombinationsOfAPhoneNumber instance = new LetterCombinationsOfAPhoneNumber();
    	String digits;
    	
    	digits = "22";
    	
    	System.out.println("results=" + instance.letterCombinations(digits));
	}
}
