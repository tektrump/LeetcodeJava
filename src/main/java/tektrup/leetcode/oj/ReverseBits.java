package tektrup.leetcode.oj;


import java.util.HashMap;
import java.util.Map;

import tektrup.leetcode.util.MathUtils;

public abstract class ReverseBits {
	public abstract int reverseBits(int n);
	public static void main(String[] args) {
		ReverseBits instance = new SolutionII();
		int n;
		int result;
		
		n = 1;
		
		result = instance.reverseBits(n);
		System.out.println("result=" + MathUtils.toBinary(result));
	}
	
	
	static class SolutionII extends ReverseBits {
		private Map<Integer, Integer> cache = new HashMap<>();
	    public int reverseBits(int n) {
	        int result = 0;
	        int mask = (1 << 8) - 1;
	        for (int i = 0; i < 4; i++) {
	            int reverse = reverse(n & mask);
	            result += (reverse << 8 * (3-i));
	            n >>>= 8;
	        }
	        return result;
	    }
	    
	    private int reverse(int n) {
	        Integer result = cache.get(n);
	        int m = n;
	        if (result != null)
	            return result;
	        result = 0;
	        for (int i = 0; i < 8; i++) {
	            result <<= 1;
	            result += (m & 1);
	            m >>>= 1;
	        }
	        cache.put(n, result);
	        return result;
	    }
	}
}
