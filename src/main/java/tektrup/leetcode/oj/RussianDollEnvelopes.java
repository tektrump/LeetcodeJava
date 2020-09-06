package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import tektrup.leetcode.util.ArrayUtil;

public abstract class RussianDollEnvelopes {
	public abstract int maxEnvelopes(int[][] envelopes);
	public static void main(String[] args) {
		RussianDollEnvelopes instance = new SolutionI();
		int[][] envelopes;
		int result;
		long t1, t2;
		
		envelopes = ArrayUtil.str2int2DArray("[[5,4],[6,4],[6,7],[2,3]]");
		t1 = System.currentTimeMillis();
		result = instance.maxEnvelopes(envelopes);
		t2 = System.currentTimeMillis();
		System.out.println(String.format("result=%d, total time=%,dms", result, (t2 - t1)));
	}
	
	
	// Solution I: TLE
	static class SolutionI extends RussianDollEnvelopes {
		public int maxEnvelopes(int[][] envelopes) {
	        List<Integer> root = new ArrayList<>(2);
	        root.add(Integer.MAX_VALUE);
	        root.add(Integer.MAX_VALUE);
	        Map<List<Integer>, List<List<Integer>>> parentChildren = new HashMap<>();
	        for (int[] envelope : envelopes) {
	            List<Integer> list = new ArrayList<>(2);
	            list.add(envelope[0]);
	            list.add(envelope[1]);
	            insert(root, list, parentChildren);
	        }
	        return height(root, parentChildren);
	    }
	    
	    private void insert(List<Integer> parent, List<Integer> curr, Map<List<Integer>, List<List<Integer>>> parentChildren) {
	        // termination
	        List<List<Integer>> children = parentChildren.get(parent);
	        if (children == null) {
	            children = new ArrayList<>();
	            parentChildren.put(parent, children);
	            children.add(curr);
	            return;
	        }
	        boolean newChild = false;
	        Iterator<List<Integer>> itr = children.iterator();
	        while (itr.hasNext()) {
	            List<Integer> child = itr.next();
	            if (curr.get(0) > child.get(0) && curr.get(1) > child.get(1)) {
	                newChild = true;
	                itr.remove();
	                add(curr, child, parentChildren);
	            } else if (curr.get(0) < child.get(0) && curr.get(1) < child.get(1)) {
	                insert(child, curr, parentChildren);
	            } else {
	                newChild = true;
	            }
	        }
	        if (newChild)
	            children.add(curr);
	    }
	    
	    // add diffs from insert as it doesn't check insertion recursively; all children added will stay on the same level
	    private void add(List<Integer> p, List<Integer> c, Map<List<Integer>, List<List<Integer>>> pChildren) {
	        List<List<Integer>> children = pChildren.get(p);
	        if (children == null) {
	            children = new ArrayList<>();
	            pChildren.put(p, children);
	        }
	        children.add(c);
	    }
	    
	    private int height(List<Integer> root, Map<List<Integer>, List<List<Integer>>> pChildren) {
	        int h = 0;
	        List<List<Integer>> lists = new ArrayList<>();
	        lists.add(root);
	        while (!lists.isEmpty()) {
	            List<List<Integer>> nextLists = new ArrayList<>();
	            for (List<Integer> list : lists) {
	                List<List<Integer>> children = pChildren.get(list);
	                if (children != null)
	                    nextLists.addAll(children);
	            }
	            lists = nextLists;
	            h++;
	        }
	        return h - 1;
	    }
	}
}
