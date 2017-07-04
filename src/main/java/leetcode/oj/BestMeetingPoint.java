package leetcode.oj;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import leetcode.util.ArrayUtil;

public abstract  class BestMeetingPoint {
	public abstract int minTotalDistance(int[][] grid);
    public static void main(String[] args) {
    	BestMeetingPoint instance = new SolutionIII();
    	int[][] grid;
    	
    	// 6
    	grid = new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
    	// 1
//    	grid = new int[][]{{1}, {1}};
    	// 39
//    	grid = ArrayUtil.str2int2DArray("[[0,1,0,0,0,0,0,0,0],[0,1,0,0,0,1,0,0,0],[1,1,1,0,1,0,1,0,0],[0,0,1,0,0,0,0,1,0],[1,0,1,1,0,1,0,0,0]]");
    	// 2070
    	grid = ArrayUtil.str2int2DArray("[[0,0,0,0,0,0,1,0,0,0,1,1,0,1,0,0],[0,0,0,1,0,0,1,0,0,0,0,0,0,1,0,0],[0,0,1,1,0,1,0,0,0,0,1,0,0,1,0,0],[0,0,0,0,1,1,0,0,1,0,0,0,0,0,1,0],[1,0,0,0,0,1,0,0,1,0,1,0,0,1,0,0],[0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,0],[0,0,1,0,0,1,0,1,1,1,1,0,0,1,1,1],[0,0,0,0,0,0,0,0,1,0,1,0,0,0,1,0],[1,0,0,0,0,1,0,0,1,1,0,1,0,1,0,0],[0,0,1,0,1,1,0,0,0,0,0,0,1,0,0,0],[1,0,1,1,0,0,0,0,0,1,0,0,0,1,0,0],[0,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0],[1,0,1,0,1,0,0,0,1,1,0,0,1,0,0,0],[0,1,1,0,0,0,0,1,0,0,0,1,0,1,0,0],[0,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0],[1,1,0,0,1,0,0,0,0,0,0,1,0,1,0,0],[0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0],[1,0,0,0,0,1,1,1,0,0,0,1,0,0,0,0],[1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0],[0,0,1,1,0,1,1,0,0,0,1,0,0,0,0,0],[0,0,1,1,1,1,0,1,0,0,0,0,1,0,0,0],[1,0,0,1,0,0,0,1,0,1,1,0,1,0,0,0],[1,0,0,1,0,1,0,0,0,0,0,0,0,1,0,1],[0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0],[1,0,1,0,0,0,1,1,0,1,1,0,1,0,0,1],[1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,1,1,0,1,1,0,1,0,0,0,1,0,1,1],[0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0],[0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0],[0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1],[0,0,0,1,0,1,1,0,0,0,0,1,1,0,1,0],[0,0,1,0,1,0,1,0,0,0,0,1,1,0,1,0],[0,1,0,1,0,0,1,0,0,1,0,0,0,1,1,0],[0,1,0,0,1,0,0,0,0,0,0,0,1,0,0,1]]");
    	
    	long t1 = System.currentTimeMillis();
    	int result = instance.minTotalDistance(grid);
    	long t2 = System.currentTimeMillis();
    	System.out.println("result=" + result);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}

    
    static class SolutionIII extends BestMeetingPoint {
    	private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        public int minTotalDistance(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0)
                return 0;
            int rows = grid.length, cols = grid[0].length;
            int[][] counts = new int[rows][cols];
            Map<Integer, Set<Integer>> posIds = new HashMap<>();
            int ones = 0, id = 0;
            List<int[]> ps = new ArrayList<>();
            List<Integer> ids = new ArrayList<>();
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid[r][c] == 1) {
                        ones++;
                        ps.add(new int[]{r, c});
                        ids.add(id);
                        Set<Integer> set = new HashSet<>();
                        set.add(id);
                        posIds.put(r*cols + c, set);
                        id++;
                    }
                }
            }
            
            int h = 0, result = Integer.MAX_VALUE;
            while (!ps.isEmpty()) {
                List<int[]> nextPs = new ArrayList<>();
                List<Integer> nextIds = new ArrayList<>();
                h++;
                for (int i = 0; i < ps.size(); i++) {
                    int[] p = ps.get(i);
                    id = ids.get(i);
                    int r1 = p[0], c1 = p[1];
                    int key = r1*cols + c1;
                    Set<Integer> set = posIds.get(key);
                    if (set != null && set.size() == ones) {
                        result = Math.min(result, counts[r1][c1]);
                    }
                    // find next positions
                    for (int[] dir : dirs) {
                        int r2 = r1 + dir[0], c2 = c1 + dir[1];
                        if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < cols /* && grid[r2][c2] == 0 */) { // ?? can we pass through a house?
                            int key2 = r2*cols + c2;
                            Set<Integer> set2 = posIds.get(key2);
                            if (set2 == null) {
                            	set2 = new HashSet<>();
                                posIds.put(key2, set2);
                            }
                            if (set2.add(id)) {
                                nextPs.add(new int[]{r2, c2});
                                nextIds.add(id);
                                counts[r2][c2] += h;
                            }
                        }
                    }
                }
                ps = nextPs;
                ids = nextIds;
            }
            // unreachable
            return result;
        }
    }
    
    
    // Solution I: Logic Error
    static class SolutionI extends BestMeetingPoint {
    	private static final int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        public int minTotalDistance(int[][] grid) {
            // special
            if (grid == null || grid.length == 0 || grid[0].length == 0)
                return 0;
            int rows = grid.length, cols = grid[0].length;
            int ones = 0;
            List<Integer> ps = new ArrayList<>();
            List<Set<Integer>> visitedList = new ArrayList<>();
            List<Integer> counts = new ArrayList<>();
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    int p = cols*r + c;
                    ps.add(p);
                    Set<Integer> visited = new HashSet<>();
                    visited.add(p);
                    visitedList.add(visited);
                    if (grid[r][c] == 1) {
                        ones++;
                        counts.add(1);
                    } else {
                        counts.add(0);
                    }
                }
            }
            int result = 0;
            while (!ps.isEmpty()) {
                List<Integer> nextPs = new ArrayList<>();
                List<Set<Integer>> nextVisitedList = new ArrayList<>();
                List<Integer> nextCounts = new ArrayList<>();
                for (int i = 0; i < ps.size(); i++) {
                    Integer p = ps.get(i);
                    Set<Integer> visited = visitedList.get(i);
                    Integer count = counts.get(i);
                    if (count == ones)
                        return result; // greedy
                    int r = p/cols, c = p%cols;
                    for (int[] move : moves) {
                        int r1 = r + move[0], c1 = c + move[1];
                        int p1 = cols*r1 + c1;
                        // NOTE: we must not do visited.add(); visited is shared and cannot be modified.
                        if (r1 >= 0 && r1 < rows && c1 >= 0 && c1 < cols && !visited.contains(p1)) {
                            nextPs.add(p1);
                            Set<Integer> nextVisited = new HashSet<>(visited); // must make a copy, then add p1
                            nextVisited.add(p1);
                            nextVisitedList.add(nextVisited);
                            if (grid[r1][c1] == 1)
                                nextCounts.add(count + 1); // can't do count++, as count is shared
                            else
                                nextCounts.add(count);
                        }
                    }
                }
                ps = nextPs;
                visitedList = nextVisitedList;
                counts = nextCounts;
                result++;
            }
            // unreachable
            return -1;
        }
    }
    
    
    static class SolutionII extends BestMeetingPoint {
    	private static final int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        public int minTotalDistance(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0)
                return 0;
            int rows = grid.length, cols = grid[0].length;
            List<List<Integer>> psList = new ArrayList<>();
            List<Set<Integer>> visitedList = new ArrayList<>();
            List<Integer> dists = new ArrayList<>();
            List<Integer> counts = new ArrayList<>();
            int ones = 0;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    int p = cols*r + c;
                    List<Integer> ps = new ArrayList<>();
                    ps.add(p);
                    psList.add(ps);
                    Set<Integer> visited = new HashSet<>();
                    visited.add(p);
                    visitedList.add(visited);
                    dists.add(0);
                    if (grid[r][c] == 1) {
                        counts.add(1);
                        ones++;
                    } else {
                        counts.add(0);
                    }
                }
            }
            
            int h = 1;
            while (!psList.isEmpty()) {
                List<List<Integer>> nextPsList = new ArrayList<>();
                List<Set<Integer>> nextVisitedList = new ArrayList<>();
                List<Integer> nextDists = new ArrayList<>();
                List<Integer> nextCounts = new ArrayList<>();
                for (int i = 0; i < psList.size(); i++) {
                    List<Integer> ps = psList.get(i);
                    Set<Integer> visited = visitedList.get(i);
                    int dist = dists.get(i);
                    int count = counts.get(i);
                    if (count == ones)
                        return dist; // greedy
                    List<Integer> nextPs = new ArrayList<>();
                    for (int p : ps) {
                        int r = p/cols, c = p%cols;
                        for (int[] move : moves) {
                            int r1 = r + move[0];
                            int c1 = c + move[1];
                            int p1 = cols*r1 + c1;
                            if (r1 >= 0 && r1 < rows && c1 >= 0 && c1 < cols && visited.add(p1)) {
                                nextPs.add(p1);
                                if (grid[r1][c1] == 1) {
                                    count++;
                                    dist += h;
                                }
                            }
                        }
                    }
                    nextPsList.add(nextPs);
                    nextVisitedList.add(visited);
                    nextDists.add(dist);
                    nextCounts.add(count);
                }
                psList = nextPsList;
                visitedList = nextVisitedList;
                dists = nextDists;
                counts = nextCounts;
                h++;
            }
            
            // unreachable
            return -1;
        }
    }
}
