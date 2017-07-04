package leetcode.oj;


public abstract class DivideTwoIntegers {
	public abstract int divide(int dividend, int divisor);
	public static void main(String[] args) {
		DivideTwoIntegers instance = new SolutionII();
		int dividend, divisor;
		
//		dividend = -1010369383; divisor = -2147483648; // 0
//		dividend = -9; divisor = -2;	// 4
		dividend = -2147483648; divisor = -1; // 0
		System.out.println("result=" + instance.divide(dividend, divisor));
	}
	
	
	static class SolutionII extends DivideTwoIntegers {
		public int divide(int dividend, int divisor) {
	        if (divisor == 0)
	            return Integer.MAX_VALUE;
	        boolean isNeg = false;
	        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
	            isNeg = true;
	        long d1 = Math.abs((long)dividend), d2 = Math.abs((long)divisor);
	        long q = help(d1, d2);
	        if (isNeg)
	            return -(int)q;
	        else
	            return (int)q;
	    }
	    
	    private long help(long d1, long d2) {
	        // termination
	        if (d1 == d2)
	            return 1;
	        else if (d1 < d2)
	            return 0;
	        
	        // recursion
	        long q = 1;
	        long d = d2;
	        while (d1 - d > d) {
	            d += d;
	            q *= 2;
	        }
	        return q + help(d1 - d, d2);
	    }
	}
	
	
	// Solution I: Logic Error
	static class SolutionI extends DivideTwoIntegers {
		public int divide(int dividend, int divisor) {
	        if (divisor == 0)
	            return Integer.MAX_VALUE;
	        boolean isNeg = false;
	        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
	            isNeg = true;
	        long d1 = Math.abs((long)dividend), d2 = Math.abs((long)divisor);
	        int q = help(d1, d2);
	        if (isNeg)
	            return -q;
	        else
	            return q;
	    }
	    
	    private int help(long d1, long d2) {
	        // termination
	        if (d1 == d2)
	            return 1;
	        else if (d1 < d2)
	            return 0;
	        
	        // recursion
	        int q = 1;
	        long d = (long)d2;
	        while (d1 - d > d) {
	            d += d;
	            q *= 2;
	        }
	        return q + help(d1 - d, d2);
	    }
	}
}
