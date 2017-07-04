package leetcode.oj;


import java.util.HashMap;
import java.util.Map;

public class NimGame {
	private Map<Integer, Boolean> cache1 = new HashMap<>();
    private Map<Integer, Boolean> cache2 = new HashMap<>();
    public boolean canWinNim(int n) {
        return helper(n, true);
    }
    
    private boolean helper(int n, boolean turn) {
        if (n <= 3)
            return turn;
            
        Boolean result;
        if (turn)
            result = cache1.get(n);
        else
            result = cache2.get(n);
        if (result != null)
            return result;
        
        if (turn)
            result = helper(n-1, false) || helper(n-2, false) || helper(n-3, false);
        else
            result = helper(n-1, true) && helper(n-2, true) && helper(n-3, true);
            
        if (turn)
            cache1.put(n, result);
        else
            cache2.put(n, result);
        
        return result;
    }
    
    public static void main(String[] args) {
    	NimGame instance = new NimGame();
    	int n;
    	
//    	n = 4; // false
    	
    	n = 5; // true
    	
//    	n= 1348820612;
    	
    	for (int i = 1; i < 50; i++)
    		System.out.println(i + ".\t" + instance.canWinNim(i));
    			
    	long t1 = System.currentTimeMillis();
    	boolean result = instance.canWinNim(n);
    	long t2 = System.currentTimeMillis();
    	System.out.println("result=" + result);
    	System.out.println(String.format("totla time=%,dms", (t2 - t1)));
	}

}
