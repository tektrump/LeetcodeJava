package leetcode.oj;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Combinations {
	private Map<List<Integer>, List<List<Integer>>> cache = new HashMap<>();
    public List<List<Integer>> combine(int n, int k) {
        return recur(1, n, k);
    }
    
    private List<List<Integer>> recur(int start, int n, int k) {
        // boundary
        if (k == 0 || n - start + 1 < k)
            return Collections.emptyList();
        
        List<Integer> key = new ArrayList<>(2);
        key.add(start);
        key.add(k);
        List<List<Integer>> results = cache.get(key);
        if (results != null)
            return results;
        results = new ArrayList<>();
        for (int i = start; i <= n; i++) {
        	List<Integer> left = new ArrayList<>();
        	left.add(i);
            // boundary
            if (k == 1) {
                results.add(left);
            } else {
                List<List<Integer>> subs = recur(i+1, n, k-1);
                for (List<Integer> sub : subs) {
                	List<Integer> result = new ArrayList<>();
                	result.addAll(left);
                    result.addAll(sub);
                    results.add(result);
                }
            }
        }
        cache.put(key, results);
        return results;
    }
    
    public static void main(String[] args) {
    	Combinations instance = new Combinations();
    	int n, k;

//    	n = 4; k = 2;
    	// [[1,2,3],[1,2,4],[1,3,4],[2,3,4]]
    	n = 4; k = 3;
//    	n = 2; k = 2; // [[1,2]]
//    	n = 10; k = 7;
    	
    	long t1 = System.currentTimeMillis();
    	List<List<Integer>> results = instance.combine(n, k);
    	long t2 = System.currentTimeMillis();
    	System.out.println("results=" + results);
    	System.out.println(String.format("total time=%,dms", (t2 -t1)));
	}
}
