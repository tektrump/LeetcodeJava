package leetcode.oj;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class WaterAndJugProblem {
	public abstract boolean canMeasureWater(int x, int y, int z);
	public static void main(String[] args) {
		WaterAndJugProblem instance = new SolutionI();
		int x, y, z;
		boolean result;
		long t1, t2;
		
//		x = 2; y = 6; z = 4; // true
		
//		x = 0; y = 0; z = 1; // false
		
		x = 104597; y = 104623; z = 123;
		
		t1 = System.currentTimeMillis();
		result = instance.canMeasureWater(x, y, z);
		t2 = System.currentTimeMillis();
		System.out.println(String.format("result=%s, total time=%,dms", result, (t2 - t1)));
	}
	
	
	static class SolutionI extends WaterAndJugProblem {
		public boolean canMeasureWater(int x, int y, int z) {
	        if (x == z || y == z)
	            return true;
	        if (x == 0 || y == 0)
	        	return false;
	        Set<List<Integer>> vols = new HashSet<>();
	        vols.add(Arrays.asList(0, 0));
	        vols.add(Arrays.asList(x, 0));
	        vols.add(Arrays.asList(0, y));
	        vols.add(Arrays.asList(x, y));
	        Set<List<Integer>> used = new HashSet<>();
	        used.addAll(vols);
	        while (!vols.isEmpty()) {
	            Set<List<Integer>> nextVols = new HashSet<>();
	            for (List<Integer> vol : vols) {
	                int v1 = vol.get(0), v2 = vol.get(1);
	                if (v1 == z || v2 == z)
	                    return true;
	                // fill up 1
	                add(v1, y, used, nextVols);
	                add(x, v2, used, nextVols);
	                // empty 1
	                add(v1, 0, used, nextVols);
	                add(0, v2, used, nextVols);
	                // pour left to right
	                pourLR(v1, v2, x, y, used, nextVols);
	                // pour right to left
	                pourRL(v1, v2, x, y, used, nextVols);
	            }
	            vols = nextVols;
	        }
	        return false;
	    }
	    
	    private void add(int x, int y, Set<List<Integer>> used, Set<List<Integer>> nextVols) {
	        List<Integer> vol = Arrays.asList(x, y);
	        if (used.add(vol))
	            nextVols.add(vol);
	    }
	    
	    private void pourLR(int v1, int v2, int x, int y, Set<List<Integer>> used, Set<List<Integer>> nextVols) {
	        while (v1 + v2 <= y) {
	            add(0, v1+v2, used, nextVols);
	            add(x, v1+v2, used, nextVols);
	            v1 = x;
	            v2 = v1 + v2;
	        }
	        add(x-(y-v2), y, used, nextVols);
	        add(x-(y-v2), 0, used, nextVols);
	    }
	    
	    private void pourRL(int v1, int v2, int x, int y, Set<List<Integer>> used, Set<List<Integer>> nextVols) {
	        while (v1 + v2 <= x) {
	            add(v1+v2, 0, used, nextVols);
	            add(v1+v2, y, used, nextVols);
	            v1 = v1 + v2;
	            v2 = y;
	        }
	        add(x, y-(x-v1), used, nextVols);
	        add(0, y-(x-v1), used, nextVols);
	    }
	}
}
