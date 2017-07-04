package leetcode.oj;


public class PalindromeNumber {
	
	// Solution II: BEST
	// without using 2 pointers
	public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        // CATCH: edge case: last digit is 0;
        // must deal with separately from the main loop.
        if (x != 0 && x%10 == 0)
            return false;
            
        int y = x, z = 0;
        while (y > 0) {
            z = z*10 + y%10;
            y /= 10;
        }
        
        return x == z;
    }
	
	// Solution I: Accepted
	/*
	public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        if (x == 0)
            return true;
            
        int digits = 0;
        int y = x;
        while (y > 0) {
            y /= 10;
            digits++;
        }
        int p1 = 0, p2 = digits;
        int d1 = (int)Math.pow(10, digits-1);
        int d2 = 1;
        while (p1 < p2) {
            if (x/d1%10 != x/d2%10)
                return false;
            d1 /= 10;
            p1++;
            d2 *= 10;
            p2--;
        }
        return true;
    }
    */
	
	public static void main(String[] args) {
		PalindromeNumber instance = new PalindromeNumber();
		int x;
		
		// true
//		x = 1;
		
		x = 121;
		boolean result = instance.isPalindrome(x);
		
		System.out.println("result: " + result);
	}

}
