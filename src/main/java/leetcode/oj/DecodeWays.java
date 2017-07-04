package leetcode.oj;


public abstract class DecodeWays {
	public abstract int numDecodings(String s);
	public static void main(String[] args) {
		DecodeWays instance = new SolutionII();
		String s;
		int res;
		
		s = "100";
		
		res = instance.numDecodings(s);
		System.out.println("result=" + res);
	}
	
	
	static class SolutionII extends DecodeWays {
		public int numDecodings(String s) {
	        if (s == null || s.isEmpty() || s.charAt(0) == '0')
	            return 0;
	        int prev2 = 1;
	        if (s.length() == 1) {
	            if (!s.equals("0"))
	                return 1;
	        }
	        int prev1 = 1;
	        int curr = 0;
	        for (int i = 1; i < s.length(); i++) {
if (i == 2)
	System.out.println();
	            char ch = s.charAt(i), prevCh = s.charAt(i-1);
	            if (ch != '0')
	                curr += prev1;
	            int val = (prevCh-'0')*10 + ch - '0';
	            if (val > 0 && val <= 26)
	                curr += prev2;
	            prev2 = prev1;
	            prev1 = curr;
	            if (prev1 == 0 && prev2 == 0)
	                return 0;
	        }
	        
	        return curr;
	    }
	}
}
