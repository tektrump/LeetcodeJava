package leetcode.oj;


public abstract class BasicCalculator {
	public abstract int calculate(String s);
	public static void main(String[] args) {
		BasicCalculator instance = new SolutionII();
		String s;
		int result;
		
//		s = "(1)"; 	// 1
//		s = "1-1";	// 0
//		s = "2-(5-6)";	// 3
//		s = "(3-(2-(5-(9-(4)))))";	// 1
		s = "(1+(4+5+2)-3)+(6+8)"; 	// 23
		
		result = instance.calculate(s);
		System.out.println("result=" + result);
	}
	
	static class SolutionII extends BasicCalculator {
		public int calculate(String s) {
		    return help(s, 0)[0];
	    }
	    
	    private int[] help(String s, int l) {
	        int res = 0, sign = 1;
	        int r = l;
	        while (r < s.length()) {
	            char ch = s.charAt(r);
	            if (ch == ' ')
	                r++;
	            else if (ch == '+') {
	                sign = 1;
	                r++;
	            } else if (ch == '-') {
	                sign = -1;
	                r++;
	            } else if (ch == '(') {
	                int[] subs = help(s, r+1);
	                int num = subs[0];
	                res += sign*num;
	                r = subs[1] + 1;
	            } else if (ch == ')')
	                break;
	            else {
	                int num = 0;
	                while (r < s.length() && Character.isDigit(s.charAt(r))) {
	                    num = num*10 + s.charAt(r) - '0';
	                    r++;
	                }
	                res += sign*num;
	            }
	        }
	        
	        return new int[]{res, r};
	    }
	}
}
