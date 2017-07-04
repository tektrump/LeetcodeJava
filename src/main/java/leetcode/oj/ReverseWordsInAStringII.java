package leetcode.oj;


import java.util.Arrays;

public abstract class ReverseWordsInAStringII {
	public abstract void reverseWords(char[] s);
	public static void main(String[] args) {
		ReverseWordsInAStringII instance = new SolutionI();
		char[] s;
		
		s = "+---...2x+--x--+x-+-x2...---+".toCharArray();
		
		instance.reverseWords(s);
		System.out.println("result=" + Arrays.toString(s));
	}
	
	static class SolutionI extends ReverseWordsInAStringII {
		public void reverseWords(char[] s) {
	        int l = 0, r = 0;
	        for (; r < s.length; r++) {
	            if (r+1 == s.length || s[r+1] == ' ') {
	                swap(s, l, r);
	                l = r + 2;
	                r++;
	            }
	        }
	        l = 0;
	        r = s.length-1;
	        while (l < r)
	            swap(s, l++, r--);
	    }
	    
	    private void swap(char[] chars, int l, int r) {
	        char temp = chars[l];
	        chars[l] = chars[r];
	        chars[r] = temp;
	    }
	}
}
