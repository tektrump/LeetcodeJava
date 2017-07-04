package leetcode.oj;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class UglyNumber {
	public abstract boolean isUgly(int num);

    public static void main(String[] args) {
    	UglyNumber instance = new SolutionI();
    	int num;
    	
//    	num = 1;	// true
//    	System.out.println("result=" + instance.isUgly(num));
//    	
//    	num = 4;	// true
//    	System.out.println("result=" + instance.isUgly(num));
//    	
//    	num = 6;	// true
//    	System.out.println("result=" + instance.isUgly(num));
//    	
//    	num = 8;	// true
//    	System.out.println("result=" + instance.isUgly(num));
//    	
//    	num = 14;	// false
//    	System.out.println("result=" + instance.isUgly(num));
    	
//    	num = 937351770;
    	num = 93735177;
    	System.out.println("result=" + instance.isUgly(num));
	}
    
    
    static class SolutionI extends UglyNumber {
    	private Set<Integer> used = new HashSet<>();
        public boolean isUgly(int num) {
        	if (num == 1)
        		return true;
            if (used.add(num)) {
                if (isPrime(num)) {
                    if (num != 2 && num != 3 && num != 5)
                        return false;
                } else {
                    if (num > 2 && !isUgly(num-2))
                        return false;
                    if (num > 3 && !isUgly(num-3))
                        return false;
                    if (num > 5 && !isUgly(num-5))
                        return false;
                }
            }
            return true;
        }
        
        private Map<Integer, Boolean> cache = new HashMap<>();
        private boolean isPrime(int num) {
            if (num == 1)
                return true;
            Boolean result = cache.get(num);
            if (result != null)
                return result;
            result = true;
            for (int i = 2; num/i >= i; i++) {
                if (num % i == 0) {
                    result = false;
                    break;
                }
            }
            cache.put(num, result);
            return result;
        }
    }
}
