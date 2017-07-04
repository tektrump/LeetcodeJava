package leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class FlipGameII {
	public abstract boolean canWin(String s);
	public static void main(String[] args) {
    	FlipGameII instance = new SolutionII();
    	String s;
    	
    	s = "++++";
    	
    	boolean result = instance.canWin(s);
    	System.out.println("result=" + result);
	}
	
	
	static class SolutionII extends FlipGameII {
		public boolean canWin(String s) {
	        return help(s, true);
	    }
	    
	    private Map<Boolean, Map<String, Boolean>> cache = new HashMap<>();
	    private boolean help(String s, boolean turn) {
if (!turn && s.equals("--++"))
	System.out.println();
	        Map<String, Boolean> map = cache.get(turn);
	        if (map == null) {
	            map = new HashMap<>();
	            cache.put(turn, map);
	        }
	        Boolean result = map.get(s);
	        if (result != null)
	            return result;
	        result = !turn; // termination
	        char[] chars = s.toCharArray();
	        for (int i = 1; i < chars.length; i++) {
	            if (chars[i] == '+' && chars[i-1] == '+') {
if (turn && i == 2)
	System.out.println();
	                flip(chars, i-1, i);    // modify
	                if (turn && help(new String(chars), !turn)) {
	                    result = true;
	                    flip(chars, i-1, i); // restore
	                    break;
	                } else if (!turn && !help(new String(chars), !turn)) {
	                    result = false;
	                    flip(chars, i-1, i); // restore
	                    break;
	                }
	                flip(chars, i-1, i); // restore
	            }
	        }
	        
	        map.put(s, result);
	        return result;
	    }
	    
	    private void flip(char[] chars, int i, int j) {
	        char newCh = chars[i] == '+' ? '-' : '+';
	        chars[i] = newCh;
	        chars[j] = newCh;
	    }
	}
	
	
	static class SolutoinI extends FlipGameII {
		private Set<List<Object>> visited = new HashSet<>();
	    public boolean canWin(String s) {
	        return dfs(s.toCharArray(), true);
	    }
	    
	    private boolean dfs(char[] chars, boolean turn) {
	        boolean found = false;
	        List<Object> params = new ArrayList<>(2);
	        params.add(new String(chars));
	        params.add(turn);
	//        if (!visited.add(params))
	//            return false;
	        for (int i = 0; i < chars.length-1; i++) {
	            if (chars[i] == '+' && chars[i+1] == '+') {
	                found = true;
	                chars[i] = '-';
	                chars[i+1] = '-';
	                boolean sub = dfs(chars, turn^true);
	                if (sub && turn)
	                    return true;
	                else if (!sub && !turn)
	                    return false;
	                chars[i] = '+';
	                chars[i+1] = '+';
	            }
	        }
	        // termination
	        if (!found)
	            return !turn;
	        else
	            return !turn; // unreachable
	    }
	}
    
}
