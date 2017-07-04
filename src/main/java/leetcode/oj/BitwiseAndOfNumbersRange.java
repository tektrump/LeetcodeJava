package leetcode.oj;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class BitwiseAndOfNumbersRange {
	
	public int rangeBitwiseAnd(int m, int n) {
        Set<Integer> ones = new HashSet<>();
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((m & mask) != 0)
                ones.add(i);
            else {
                if (!ones.isEmpty() && i < 31) {
                    int x = ((-1 << i) & m) + (1 << i);
                    if (x <= n)
                        ones.clear();
                }
            }
            mask <<= 1;
        }
        int result = 0;
        for (Integer one : ones)
            result += (1 << one);
        return result;
    }
	
	// Solution I: TLE
	/*
	public int rangeBitwiseAnd(int m, int n) {
        Set<Integer> masks = new HashSet<>();
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            masks.add(mask);
            mask <<= 1;
        }
        for (int i = m; i <= n; i++) {
            if (masks.isEmpty())
                break;
            Iterator<Integer> itr = masks.iterator();
            while (itr.hasNext()) {
                if ((i & itr.next()) == 0)
                    itr.remove();
            }
        }
        int result = 0;
        for (int mk : masks)
            result |= mk;
        return result;
    }
	*/
	
	public static void main(String[] args) {
		BitwiseAndOfNumbersRange instance = new BitwiseAndOfNumbersRange();
		int m, n;
		
//		m = 5; n = 7;
		
		// 64
//		m = 90; n = 118;
		
		m = 20000; n = 2147483647;
		
		long t1 = System.currentTimeMillis();
		int result = instance.rangeBitwiseAnd(m, n);
		long t2 = System.currentTimeMillis();
		System.out.println("result=" + result);
		System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
}
