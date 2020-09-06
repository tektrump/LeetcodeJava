package tektrup.leetcode.oj;


import java.util.HashSet;
import java.util.Set;

public abstract class HappyNumber {
	public abstract boolean isHappy(int n);
	public static void main(String[] args) {
		HappyNumber instance = new SolutionI();
		int n;
		boolean result;
		
		n = 11;
		
		result = instance.isHappy(n);
		System.out.println("result=" + result);
	}
	
	
	static class SolutionI extends HappyNumber {
		public boolean isHappy(int n) {
	        Set<Set<Integer>> visited = new HashSet<>();
	        while (n != 1) {
	            Set<Integer> digits = new HashSet<>();
	            while (n > 0) {
	                digits.add(n % 10);
	                n /= 10;
	            }
	            if (!visited.add(digits))
	                return false;
	            n = 0;
	            for (int digit : digits)
	                n += digit*digit;
	        }
	        return true;
	    }
	}
}
