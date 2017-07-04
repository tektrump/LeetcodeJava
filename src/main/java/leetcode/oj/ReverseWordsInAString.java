package leetcode.oj;


public abstract class ReverseWordsInAString {
	public abstract String reverseWords(String s);
	public static void main(String[] args) {
		ReverseWordsInAString instance = new SolutionI();
		String s;
		String res;
		
//		s = "";
		
		s = "a";
		
		res = instance.reverseWords(s);
		System.out.println("result=" + res);
	}
	
	static class SolutionI extends ReverseWordsInAString {
		public String reverseWords(String s) {
	        char[] chars = s.toCharArray();
	        int l = 0, r = 0;
	        boolean space = true;
	        while (r < s.length()) {
	            char ch = chars[r];
	            if (ch == ' ') {
	                if (!space)
	                    swap(chars, l++, r);
	                space = true;
	            } else {
	                swap(chars, l++, r);
	                space = false;
	            }
	            r++;
	        }
	        // l points to end+1 of valid str
	        int len = l;
	        if (len == 0)
	            return "";
	        if (chars[len-1] == ' ') // remove possible trailing space
	            len--;
	        
	        l = 0;
	        r = 0;
	        while (r < len) {
	            char ch = chars[r];
	            if (ch == ' ') {
	                reverse(chars, l, r-1);
	                l = r+1;
	            }
	            r++;
	        }
	        reverse(chars, 0, len-1);
	        
	        return new String(chars).substring(0, len);
	    }
	    
	    private void reverse(char[] chars, int l, int r) {
	        while (l < r) {
	            swap(chars, l++, r--);
	        }
	    }
	    
	    private void swap(char[] chars, int l, int r) {
	    	if (l != r) {
		        chars[l] = (char)(chars[l] ^ chars[r]);
		        chars[r] = (char)(chars[l] ^ chars[r]);
		        chars[l] = (char)(chars[l] ^ chars[r]);
	    	}
	    }
	}
}
