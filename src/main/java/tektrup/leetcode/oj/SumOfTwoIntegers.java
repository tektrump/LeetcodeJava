package tektrup.leetcode.oj;


public abstract class SumOfTwoIntegers {
	public abstract int getSum(int a, int b);
	public static void main(String[] args) {
		SumOfTwoIntegers instance = new SolutionI();
		int a, b;
		int result;
		
//		a = -1; b = 13; 
		a = -1; b = 1;
		
		result = instance.getSum(a, b);
		System.out.println("result=" + result);
	}
	
	
	static class SolutionI extends SumOfTwoIntegers {
		public int getSum(int a, int b) {
	        int sum = 0, c = 0;
	        int shift = 0;
	        for (int i = 0; i < 32; i++) {
	            int d1 = a & 1, d2 = b & 1;
	            sum |= ((d1^d2^c) << shift);
	            c = (d1&d2) | (d1&c) | (d2&c);
	            a >>>= 1;
	            b >>>= 1;
	            shift++;
	        }
	        return sum;
	    }
	}
}
