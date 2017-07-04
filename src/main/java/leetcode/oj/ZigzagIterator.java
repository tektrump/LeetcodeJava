package leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class ZigzagIterator {
	public ZigzagIterator(List<Integer> v1, List<Integer> v2) {;}
	public abstract int next();
	public abstract boolean hasNext();
	
	public static void main(String[] args) {
		List<Integer> v1 = new ArrayList<>(), v2 = new ArrayList<>();
		
		v1 = Arrays.asList(new Integer[]{1,2});
		v2 = Arrays.asList(new Integer[]{2,4,5,6});
		ZigzagIterator instance = new SolutionI(v1, v2);
		while (instance.hasNext())
			System.out.println(instance.next());
	}
	
	static class SolutionI extends ZigzagIterator {
		private Iterator<Integer> itr1, itr2;
	    private boolean one;
		public SolutionI(List<Integer> v1, List<Integer> v2) {
			super(v1, v2);
			itr1 = v1.iterator();
	        itr2 = v2.iterator();
	        one = true;
		}
		
		public int next() {
			int next;
	        if (!itr2.hasNext() || one)
	            next = itr1.next();
	        else
	            next = itr2.next();
	        one ^= true;
	        return next;
		}

		public boolean hasNext() {
			return itr1.hasNext() || itr2.hasNext();
		}
	}
}
