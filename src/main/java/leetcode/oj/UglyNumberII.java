package leetcode.oj;


import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class UglyNumberII {
	public abstract int nthUglyNumber(int n);
    public static void main(String[] args) {
    	UglyNumberII instance = new SolutionIII();
    	int n;
    	int result;
    	long t1, t2;
    	
//    	n = 2; // 2
//    	n = 277; // 60000
//    	n = 379;
    	n = 1600;	// 1399680000

//    	t1 = System.currentTimeMillis();
//    	result = instance.nthUglyNumber(n);
//    	t2 = System.currentTimeMillis();
//    	System.out.println("result=" + result);
//    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
    	
    	for (int i = 1; i < 12; i++)
    		System.out.println(instance.nthUglyNumber(i));
//    	System.out.println(instance.nthUglyNumber(6));
	}
    
    
    static class SolutionIII extends UglyNumberII {
    	public int nthUglyNumber(int n) {
            int mult2 = 0, mult3 = 0, mult5 = 0;
            int num2 = 1, num3 = 1, num5 = 1;
            int result = 1, count = 0;
            while (count < n) {
                result = Math.min(Math.min(num2, num3), num5);
                count++;
                if (num2 == result) {
                    mult2++;
                    num2 = 2*mult2;
                }
                if (num3 == result) {
                    mult3++;
                    num3 = 3*mult3;
                }
                if (num5 == result) {
                    mult5++;
                    num5 = 5*mult5;
                }
            }
            return result;
        }
    }
    
    
    // Solution II: Accepted
    // fix solution I by using long instead of int to avoid integer overflow
    static class SolutionII extends UglyNumberII {
    	public int nthUglyNumber(int n) {
            Deque<Long> list = new LinkedList<>();
            list.add((long)1);
            long result = 1, count = 1;
            while (count < n) {
                // purge
                long last = list.getLast();
                while (list.getFirst()*5 <= last)
                    list.removeFirst();
                long min = Integer.MAX_VALUE;
                for (long i : list) {
                    if (i*2 > last)
                        min = Math.min(min, i*2);
                    if (i*3 > last)
                        min = Math.min(min, i*3);
                    if (i*5 > last)
                        min = Math.min(min, i*5);
                }
                result = min;
                count++;
                list.add(min);
            }
            
            return (int)result;
        }
    }
    
    
    // Solution I: Wrong due to integer overflow
    static class SolutionI extends UglyNumberII {
    	public int nthUglyNumber(int n) {
            Deque<Integer> list = new LinkedList<>();
            list.add(1);
            int result = 1, count = 1;
            while (count < n) {
                // purge
                int last = list.getLast();
                while (list.getFirst()*5 <= last)
                    list.removeFirst();
                int min = Integer.MAX_VALUE;
                for (int i : list) {
                    if (i*2 > last)
                        min = Math.min(min, i*2);
                    if (i*3 > last)
                        min = Math.min(min, i*3);
                    if (i*5 > last)
                        min = Math.min(min, i*5);
                }
                result = min;
                count++;
                list.add(min);
            }
            
            return result;
        }
    }

}
