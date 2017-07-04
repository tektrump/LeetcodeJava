package leetcode.oj;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class PermutationSequence {
	public abstract String getPermutation(int n, int k);
	public static void main(String[] args) {
		PermutationSequence instance = new SolutionIII();
		int n, k;
		String result;
		long t1, t2;
		
//		n = 3; k = 2;	// 132
//		n = 3; k = 3;	// 213
		
		n = 8; k = 8590;
		
//		n = 2; k = 1;
		
		t1 = System.currentTimeMillis();
		result = instance.getPermutation(n, k);
		t2 = System.currentTimeMillis();
		System.out.println(String.format("result=%s, total time=%,dms", result, (t2-t1)));
	}
	
	
	static class SolutionIII extends PermutationSequence {
		public String getPermutation(int n, int k) {
	        k--;    // adjust k to 0 based index
	        int[] sizes = new int[n+1];
	        sizes[0] = 1;
	        for (int i = 1; i <= n; sizes[i] = i*sizes[i-1], i++);
	        StringBuilder b = new StringBuilder();
	        List<Integer> nums = new ArrayList<>(n);
	        for (int i = 1; i <= n; nums.add(i++));
	        for (int i = n-1; i >= 0; i--) {
	            int size = sizes[i];
	            int idx = k/size;
	            b.append(nums.remove(idx));
	            k %= size;
	        }
	        
	        return b.toString();
	    }
	}
	
	// SolutionII: Accepted
	static class SolutionII extends PermutationSequence {
		private int[] facts;
	    public String getPermutation(int n, int k) {
	        facts = new int[n];
	        k--; // convert to 0 based index
	        StringBuilder builder = new StringBuilder();
	        List<Integer> list = new LinkedList<>();
	        for (int i = 1; i <= n; list.add(i++));
	        help(list, n, k, builder);
	        return builder.toString();
	    }
	    
	    private void help(List<Integer> list, int n, int k, StringBuilder builder) {
	        // termination
	        if (n == 1)
	            builder.append(list.get(0));
	        else {
	            int fact = factorial(n-1);
	            int idx = k/fact;
	            builder.append(list.remove(idx));
	            help(list, n-1, k%fact, builder);
	        }
	    }
	    
	    private int factorial(int n) {
	        int res = facts[n];
	        if (res > 0)
	            return res;
	        if (n == 0 || n == 1)
	        	return 1;
	        else
	        	return n*factorial(n-1);
	    }
	}
	
	
	static class SolutionI extends PermutationSequence {
		public String getPermutation(int n, int k) {
	        List<Integer> nums = new LinkedList<>();
	        int perm = 1;
	        for (int i = 1; i <= n; i++) {
	            nums.add(i);
	            perm *= i;
	        }
	        char[] chars = new char[n];
	        k--;
	        for (int i = 0; i < n; i++) {
	            perm /= nums.size();
	            int idx = 0;
	            if (perm > 0)
	                idx = k/perm;
	            k %= perm;
	            chars[i] = (char)('0' + nums.remove(idx));
	        }
	        return new String(chars);
	    }
	}
}
