package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.List;

public class FlipGame {
	
	public List<String> generatePossibleNextMoves(String s) {
        List<String> results = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length-1; i++) {
            if (chars[i] == chars[i + 1]) {
                if (chars[i] == '+') {
                    chars[i] = '-';
                    chars[i + 1] = '-';
                    results.add(new String(chars));
                    chars[i] = '+';
                    chars[i + 1] = '+';
                } else {
                    chars[i] = '+';
                    chars[i + 1] = '+';
                    results.add(new String(chars));
                    chars[i] = '-';
                    chars[i + 1] = '-';
                }
            }
        }
        return results;
    }
	
	public static void main(String[] args) {
		FlipGame instance = new FlipGame();
		
		String s = "--";
		
		List<String> results = instance.generatePossibleNextMoves(s);
		System.out.println("results=" + results);
	}

}
