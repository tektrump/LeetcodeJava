package leetcode.oj;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public abstract class RectangleArea {
	public abstract int computeArea(int A, int B, int C, int D, int E, int F, int G, int H);
	public static void main(String[] args) {
		RectangleArea instance = new SolutionI();
		long t1, t2;
		
//		t1 = System.currentTimeMillis();
//		System.out.println("result=" + instance.computeArea(-11, -7266, -1, -7264, -9, -7264, 8, -7259));
//		t2 = System.currentTimeMillis();
//		System.out.println(String.format("total time=%,dms", (t2 - t1)));
		
		
		System.out.println("result=" + instance.computeArea(-2, -2, 2, 2, -2, -2, 2, 2));
	}
	
	static class SolutionI extends RectangleArea {
		public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
	        int area = (C - A)*(D - B) + (G - E)*(H - F);
	        Comparator<List<Integer>> comp = new Comparator<List<Integer>>() {
	            public int compare(List<Integer> l1, List<Integer> l2) {
	                if (l1.get(0) == l2.get(0))
	                    return l1.get(1) - l2.get(1);
	                else
	                    return l1.get(0) - l2.get(0);
	            }
	        };
	        PriorityQueue<List<Integer>> q = new PriorityQueue<>(comp);
	        add(q, A, 0);
	        add(q, E, 0);
	        add(q, C, 1);
	        add(q, G, 1);
	        q.poll();
	        List<Integer> list = q.poll();
	        if (list.get(1) == 0) {
	            int x1 = list.get(0);
	            int x2 = q.poll().get(0);
	            q.clear();
	            add(q, F, 0);
	            add(q, B, 0);
	            add(q, H, 1);
	            add(q, D, 1);
	            q.poll();
	            list = q.poll();
	            if (list.get(1) == 0) {
	                int y1 = list.get(0);
	                int y2 = q.poll().get(0);
	                area -= (x2 - x1)*(y2 - y1);
	            }
	        }
	        return area;
	    }
	    
	    private void add(PriorityQueue<List<Integer>> q, int val, int type) {
	        List<Integer> list = new ArrayList<>(2);
	        list.add(val);
	        list.add(type);
	        q.add(list);
	    }
	}
}
