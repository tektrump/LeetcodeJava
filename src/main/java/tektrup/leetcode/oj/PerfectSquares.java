package tektrup.leetcode.oj;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class PerfectSquares {
	
	// Solution I: TLE
    private Set<Integer> roots;
    public int numSquares(int n) {
        initRoots(n);
        List<Integer> list = new LinkedList<Integer>();
        list.add(n);
        Set<Integer> visited = new HashSet<>();
        visited.add(n);
        int count = 1;
        while (!list.isEmpty()) {
            List<Integer> nextList = new LinkedList<>();
            for (int i : list) {
                if (roots.contains(i))
                    return count;
                for (int root : roots) {
                    int next = i - root;
                    if (visited.add(next))
                        nextList.add(next);
                }
            }
            list = nextList;
            count++;
        }
        // unreachable
        return 0;
    }
    
    private void initRoots(int n) {
        roots = new HashSet<>();
        int root = 1;
        while(n/root >= root) {
            roots.add(root*root);
            root++;
        }
System.out.println(roots);
    }
    
    public static void main(String[] args) {
    	PerfectSquares instance = new PerfectSquares();
    	int n;
    	
//    	n = 12; // 3
    	
    	n = 8609;
    	
    	long t1 = System.currentTimeMillis();
    	int result = instance.numSquares(n);
    	long t2 = System.currentTimeMillis();
    	System.out.println("result=" + result);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}

}
