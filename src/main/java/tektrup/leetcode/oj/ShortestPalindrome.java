package tektrup.leetcode.oj;


public abstract class ShortestPalindrome {
	public abstract String shortestPalindrome(String s);
	
    public static void main(String[] args) {
    	ShortestPalindrome instance = new SolutionI();
    	String s;
    	
//    	s = "aba";	// aba
    	
//    	s = "ba";	// aba
    	
//    	s = "abbacd";	// 
    	
//    	s = "aacecaaa"; // aaacecaaa
    	
//    	s = "abcd"; // dcbabcd
    	
    	s = "abb";
    	
    	String result = instance.shortestPalindrome(s);
    	System.out.println("result=" + result);
	}
    
    
    static class SolutionII extends ShortestPalindrome {
    	public String shortestPalindrome(String s) {
            if (s.isEmpty())
                return s;
                
            int idx = 0;
            if (s.length()%2 == 0) {
                int m = (s.length() - 1)/2;
                for (int i = m; i >= 0; i--) {
                    if ((idx = match(s, i, i+1)) >= 0 ||
                        (idx = match(s, i-1, i+1)) >= 0)
                        break;
                }
            } else {
                int m = s.length()/2;
                for (int i = m; i >= 0; i--) {
                    if ((idx = match(s, i-1, i+1)) >= 0 || (idx = match(s, i-1, i)) >= 0)
                        break;
                }
            }
            
            StringBuilder b = new StringBuilder(s);
            while (idx < s.length())
                b.insert(0, s.charAt(idx++));
                
            return b.toString();
        }
        
        private int match(String s, int l, int r) {
            while (l >= 0) {
                if (s.charAt(l) != s.charAt(r))
                    break;
                else {
                    l--;
                    r++;
                }
            }
            if (l < 0)
                return r;
            else
                return -1;
        }
    }
    
    static class SolutionI extends ShortestPalindrome {
    	public String shortestPalindrome(String s) {
            if (s.isEmpty())
                return "";
            int l = 0;
            for (int r = s.length()-1; r >= 0; r--) {
                if (s.charAt(l) == s.charAt(r))
                    l++;
            }
            if (l == s.length())
                return s;
                
            String right = s.substring(l, s.length());
            String reverse = new StringBuffer(right).reverse().toString();
            return reverse + shortestPalindrome(s.substring(0, l)) + right;
        }
    }
}
