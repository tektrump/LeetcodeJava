package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class SelfCrossing {
	public abstract boolean isSelfCrossing(int[] x);
	public static void main(String[] args) {
		SelfCrossing instance = new SolutionI();
		int[] x;
		boolean result;
		
		x = new int[]{1,1,2,2,3,1,1};
		result = instance.isSelfCrossing(x);
		System.out.println("result=" + result);
	}
	
	static class SolutionI extends SelfCrossing {
		public boolean isSelfCrossing(int[] x) {
	        Set<List<Integer>> visited = new HashSet<>();
	        int r = 0, c = 0;
	        add(r, c, visited);
	        for (int i = 0; i < x.length; i++) {
System.out.println(i);
	            switch (i%4) {
	                case 0: // north
	                    for (int m = 1; m <= x[i]; m++) {
	                        if (!add(++r, c, visited))
	                            return true;
	                    }
	                    break;
	                case 1: // west
	                    for (int m = 1; m <= x[i]; m++) {
	                        if (!add(r, --c, visited))
	                            return true;
	                    }
	                    break;
	                case 2: // south
	                    for (int m = 1; m <= x[i]; m++) {
	                        if (!add(--r, c, visited))
	                            return true;
	                    }
	                    break;
	                default: // east
	                    for (int m = 1; m <= x[i]; m++) {
	                        if (!add(r, ++c, visited))
	                            return true;
	                    }
	                    break;
	            }
	        }
System.out.println("r=" + r + ", c=" + c);
	        return false;
	    }
	    
	    private boolean add(int r, int c, Set<List<Integer>> visited) {
	        List<Integer> p = new ArrayList<>(2);
	        p.add(r);
	        p.add(c);
	        return visited.add(p);
	    }
	}
}
