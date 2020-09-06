package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class TheSkylineProblem {
	public List<int[]> getSkyline(int[][] buildings) {
        TreeSet<Integer> edges = new TreeSet<>();
        TreeMap<Integer, List<List<Integer>>> leftRects = new TreeMap<>();
        TreeMap<Integer, List<List<Integer>>> rightRects = new TreeMap<>();
        Map<List<Integer>, Integer> rectHeight = new HashMap<>();
        for (int[] building : buildings) {
            int left = building[0];
            int right = building[1];
            List<Integer> rect = new ArrayList<>(2);
            rect.add(left);
            rect.add(right);
            int height = building[2];
            edges.add(left);
            edges.add(right);
            List<List<Integer>> rects;
            rects = leftRects.get(left);
            if (rects == null) {
                rects = new ArrayList<>();
                leftRects.put(left, rects);
            }
            rects.add(rect);
            rects = rightRects.get(right);
            if (rects == null) {
                rects = new ArrayList<>();
                rightRects.put(right, rects);
            }
            rects.add(rect);
            Integer prevH = rectHeight.put(rect, height);
            if (prevH != null && prevH > height)
                rectHeight.put(rect, prevH);
        }
        
        List<int[]> results = new ArrayList<>();
        int currH = 0;
        Iterator<Integer> itr = edges.iterator();
        while (itr.hasNext()) {
            int edge = itr.next();
            Set<List<List<Integer>>> leftSubs = new HashSet<>(leftRects.headMap(edge, true).values());
            Set<List<List<Integer>>> rightSubs = new HashSet<>(rightRects.tailMap(edge, false).values());
            int maxH = 0;
            for (List<List<Integer>> sub : leftSubs) {
                if (rightSubs.contains(sub)) {
                    for (List<Integer> r : sub)
                        maxH = Math.max(maxH, rectHeight.get(r));
                }
            }
            if (maxH != currH) {
                int[] result = {edge, maxH};
                currH = maxH;
                results.add(result);
            }
        }
        
        return results;
    }
	
	public static void main(String[] args) {
		TheSkylineProblem instance = new TheSkylineProblem();
		int[][] buildings;
		
		buildings = new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
		
		List<int[]> results = instance.getSkyline(buildings);
		for (int[] result : results) {
			System.out.println(Arrays.toString(result));
		}
	}
}
