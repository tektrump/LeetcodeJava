package tektrup.leetcode.oj;


import java.util.Stack;


public abstract class MiniParser {
	public abstract NestedInteger deserialize(String s);
	public static void main(String[] args) {
		MiniParser instance = new SolutionIII();
		String s;
		NestedInteger res;
		
//		s = "[123,456,[788,799,833],[[]],10,[]]";
		s = "[]";
		
		
		res = instance.deserialize(s);
		System.out.println("result=");
	}
	
	static class SolutionIII extends MiniParser {
		public NestedInteger deserialize(String s) {
	        if (s.isEmpty())
	            return null;
	        if (s.charAt(0) != '[')
	            return new NestedInteger(Integer.valueOf(s));
	            
	        NestedInteger ni = new NestedInteger();
	        help(s, 0, ni);
	        
	        return ni;
	    }
	    
	    // a substring starting with idx where charAt(idx-1) == '['
	    private int help(String s, int idx, NestedInteger curr) {
	        int l = idx+1, r = idx+1;
	        while (r < s.length()) {
	            char ch = s.charAt(r);
	            if (ch == '[') {
	                NestedInteger next = new NestedInteger();
	                r = help(s, l, next);
	                curr.add(next);
	            } else if (ch == ']') {
	                if (l != r) {
	                    curr.add(new NestedInteger(Integer.valueOf(s.substring(l, r))));
	                    break;
	                }
	            } else if (ch == ',') {
	                if (l != r) {
	                    curr.add(new NestedInteger(Integer.valueOf(s.substring(l, r))));
	                }
	            }
	            if (ch == '[' || ch == ']' || ch == ',')
	                l = r + 1;
	            r++;
	        }
	        
	        return r;
	    }
	}
	
	
	static class SolutionI extends MiniParser {
		public NestedInteger deserialize(String s) {
	        if (s.isEmpty())
	            return null;
	        Stack<NestedInteger> stack = new Stack<>();
	        NestedInteger curr = null;
	        int l = 0;
	        for (int r = 0; r < s.length(); r++) {
	            char ch = s.charAt(r);
	            if (ch == '[') {
	                if (curr != null) {
	                    stack.push(curr);
	                }
	                curr = new NestedInteger();
	                l = r+1;
	            } else if (ch == ']') {
	                String num = s.substring(l, r);
	                if (!num.isEmpty())
	                    curr.add(new NestedInteger(Integer.valueOf(num)));
	                if (!stack.isEmpty()) {
	                    NestedInteger pop = stack.pop();
	                    pop.add(curr);
	                    curr = pop;
	                }
	                l = r+1;
	            } else if (ch == ',') {
	            	if (s.charAt(r-1) != ']') {
	            		String num = s.substring(l, r);
System.out.println("num=" + num);
if (num.equals("799"))
	System.out.println();
	                	curr.add(new NestedInteger(Integer.valueOf(num)));
	            	}
	                l = r+1;
	            }
	        }
	        
	        return curr;
	    }
	}
}
