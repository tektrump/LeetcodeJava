package leetcode.oj;


import java.util.HashMap;
import java.util.Map;

public class TrailingZeros {
	
	private static final Map<Integer, Integer> numFives = new HashMap<>();
    public long trailingZeros(long n) {
        return 0;
    }
    
	
	public static void main(String[] args) {
		TrailingZeros instance = new TrailingZeros();
		long n;
		
//		n = 11; // 2
		
//		n = 105;	// 25
		
		n = 1001171717;	// 250292920
		
		long t1 = System.currentTimeMillis();
		long result = instance.trailingZeros(n);
		long t2 = System.currentTimeMillis();
		
		System.out.println("result=" + result);
		System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}

}
