package leetcode.oj;


import java.util.ArrayList;
import java.util.List;

public abstract class CountPrimes {
	public abstract int countPrimes(int n);
	public static void main(String[] args) {
		CountPrimes instance = new SolutionII();
		int n;
		int result;
		long t1, t2;
		
//		n = 499979; // 41537
		
		n = 3;
		
		t1 = System.currentTimeMillis();
		result = instance.countPrimes(n);
		t2 = System.currentTimeMillis();
		System.out.println(String.format("result=%d, time=%,dms", result, (t2-t1)));
	}
	
	
	static class SolutionII extends CountPrimes {
		public int countPrimes(int n) {
	        if (n <= 2)
	            return 0;
	        List<Integer> primes = new ArrayList<>();
	        for (int i = 2; i < n; i++) {
	if (i % 2 == 0)
	    continue;
	            boolean isPrime = true;
	            for (int prime : primes) {
	                if (i % prime == 0) {
	                    isPrime = false;
	                    break;
	                }
	            }
	            if (isPrime) {
	                primes.add(i);
	            }
	        }
	        return primes.size();
	    }
	}
	
int printCount = 50;	
	// Solution I: Accepted
	static class SolutionI extends CountPrimes {
		public int countPrimes(int n) {
	        if (n <= 2)
	            return 0;
	        int result = 0;
	        boolean[] primes = new boolean[n];
	        for (int i = 2; i < n; i++) {
	            if (primes[i])
	                continue;
if (printCount-- > 0)
	System.out.println(i);
	            result++;
	            for (int j = i; j < n; j += i)
	                primes[j] = true;
	        }
	        return result;
	    }
	}
}
