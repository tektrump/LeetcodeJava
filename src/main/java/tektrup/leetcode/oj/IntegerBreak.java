package tektrup.leetcode.oj;



public abstract class IntegerBreak {
	public abstract int integerBreak(int n);
    public static void main(String[] args) {
    	IntegerBreak instance = new SolutionIII();
    	int n;
    	
    	// 1
//    	n = 2;
    	// 18
    	n = 8;
    	// 2
//    	n = 3;
    	// 36
//    	n = 10;
    	// 1
//    	n = 28;
    	
    	long t1 = System.currentTimeMillis();
    	int result = instance.integerBreak(n);
    	long t2 = System.currentTimeMillis();
    	System.out.println(result);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
    
    
    static class SolutionIII extends IntegerBreak {
    	public int integerBreak(int n) {
            int[] ps = new int[n+1];
            return dp(n, ps);
        }
        
        private int dp(int n, int[] ps) {
            int p = ps[n];
            if (p > 0)
                return p;
            if (n == 1)
                return 1;
            for (int i = 1; n-i >= i; i++) {
                p = Math.max(p, i * (n-i));
                p = Math.max(p, i * dp(n-i, ps));
            }
            ps[n] = p;
            return p;
        }
    }

}
