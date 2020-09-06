package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tektrup.leetcode.util.ArrayUtil;

public abstract class CourseScheduleII {
	public abstract int[] findOrder(int numCourses, int[][] prerequisites);
	public static void main(String[] args) {
    	CourseScheduleII instance = new SolutionIII();
    	int numCourses;
    	int[][] prerequisites;
    	
//    	numCourses = 2;
//    	prerequisites = ArrayUtil.str2int2DArray("[[1,0]]");
    	
//    	numCourses = 4;
//    	prerequisites = ArrayUtil.str2int2DArray("[[0,1],[3,1],[1,3],[3,2]]");
    	
    	numCourses = 2;
    	prerequisites = ArrayUtil.str2int2DArray("[[1,0], [0,1]]");
    	
    	int[] results = instance.findOrder(numCourses, prerequisites);
    	System.out.println("results=" + Arrays.toString(results));
	}
	
	
	static class SolutionIII extends CourseScheduleII {
		public int[] findOrder(int numCourses, int[][] prerequisites) {
	        int[] rets = new int[numCourses];
	        int[] status = new int[numCourses];
	        List<List<Integer>> cPres = new ArrayList<>();
	        for (int i = 0; i < numCourses; i++)
	            cPres.add(new LinkedList<>());
	        for (int[] p : prerequisites)
	            cPres.get(p[0]).add(p[1]);
	        int idx = 0;
	        for (int c = 0; c < numCourses; c++)
	            idx = dfs(c, idx, rets, status, cPres);
	        return rets;
	    }
	    
	    // return next idx to fill
	    private int dfs(int c, int idx, int[] rets, int[] status, List<List<Integer>> cPres) {
System.out.println("c=" + c + ", idx=" + idx + ", status=" + Arrays.toString(status));
	        if (status[c] == 2)
	            return idx;
	        if (status[c] == 1) // loop
	            return -1;
	        status[c] = 1;
	        List<Integer> pres = cPres.get(c);
	        for (int pre : pres) {
	            idx = dfs(pre, idx, rets, status, cPres);
	            if (idx < 0)
	                return -1;
	        }
	        rets[idx++] = c;
	        status[c] = 2;
	        
	        return idx;
	    }
	}
	
	
	static class SolutionII extends CourseScheduleII {
		public int[] findOrder(int numCourses, int[][] prerequisites) {
	        Map<Integer, List<Integer>> cPres = new HashMap<>();
	        for (int c = 0; c < numCourses; c++)
	            cPres.put(c, new LinkedList<>());
	        for (int[] p : prerequisites) {
	            cPres.get(p[0]).add(p[1]);
	        }
	        int[] rets = new int[numCourses];
	        int idx = 0;
	        Set<Integer> used = new HashSet<>();
	        for (int c = 0; c < numCourses; c++) {
	            if ((idx = dfs(c, cPres, new HashSet<>(), used, rets, idx)) < 0)
	                return new int[0];
	        }
	        
	        return rets;
	    }
	    
	    private int dfs(Integer c, Map<Integer, List<Integer>> cPres, Set<Integer> pending, 
	        Set<Integer> used, int[] rets, int idx) {
	        if (used.contains(c))
	            return idx;
	        if (!pending.add(c))    // loop detected
	            return -1;
	        List<Integer> pres = cPres.get(c);
	        for (Integer pre : pres) {
	            idx = dfs(pre, cPres, pending, used, rets, idx);
	            if (idx < 0)
	                return -1;
	        }
	        rets[idx] = c;
	        used.add(c);
	        pending.remove(c);
	        
	        return idx+1;
	    }
	}
	
	static class SolutionI extends CourseScheduleII{
		public int[] findOrder(int numCourses, int[][] prerequisites) {
	        Set<Integer> roots = new HashSet<>();
	        for (int i = 0; i < numCourses; roots.add(i), i++);
	        Map<Integer, Set<Integer>> coursePres = new HashMap<>();
	        Map<Integer, Set<Integer>> preCourses = new HashMap<>();
	        for (int[] pair : prerequisites) {
	            int course = pair[0];
	            int pre = pair[1];
	            roots.remove(course);
	            add(course, pre, coursePres);
	            add(pre, course, preCourses);
	        }
	        
	        int[] results = new int[numCourses];
	        int idx = 0;
	        List<List<Integer>> paths = new ArrayList<>();
	        for (int root : roots) {
	            List<Integer> path = new ArrayList<>();
	            path.add(root);
	            paths.add(path);
	        }
	        while (!paths.isEmpty()) {
	            List<List<Integer>> nextPaths = new ArrayList<>();
	            for (List<Integer> path : paths) {
	                Integer pre = path.get(path.size() - 1);
	                results[idx++] = pre;
	                Set<Integer> courses = preCourses.get(pre);
	                if (courses != null) {
	                    for (Integer course : courses) {
	                        Set<Integer> pres = coursePres.get(course);
	                        pres.remove(pre);
	                        if (pres.isEmpty()) {
	                            List<Integer> nextPath = new ArrayList<>(path);
	                            nextPath.add(course);
	                            nextPaths.add(nextPath);
	                            coursePres.remove(course);
	                        }
	                    }
	                }
	            }
	            paths = nextPaths;
	        }
	        
	        if (!coursePres.isEmpty())
	            return new int[0];
	        else
	            return results;
	    }
	    
	    private void add(Integer key, Integer val, Map<Integer, Set<Integer>> map) {
	        Set<Integer> vals = map.get(key);
	        if (vals == null) {
	            vals = new HashSet<>();
	            map.put(key, vals);
	        }
	        vals.add(val);
	    }
	}
    
}
