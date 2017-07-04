package leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class RemoveInvalidParentheses {
	public abstract List<String> removeInvalidParentheses(String s);
	public static void main(String[] args) {
		RemoveInvalidParentheses instance = new SolutionI();
		String s;
		List<String> res;
		
//		s = "()())()";
		
		s = ")()(";
		
		res = instance.removeInvalidParentheses(s);
		System.out.println("result=" + res);
	}
	
	
	static class SolutionI extends RemoveInvalidParentheses {
		public List<String> removeInvalidParentheses(String s) {
	        if (s.isEmpty())
	            // return Collections.emptyList();
	            return Collections.singletonList("");
	            
	        List<int[]> list = new ArrayList<>();
	        Deque<int[]> q = new LinkedList<>();
	        for (int i = 0; i < s.length(); i++) {
	            char ch = s.charAt(i);
	            int[] a = {ch-'a', i};
	            if (ch == '(') {
	                a[0] = -1;
	                q.add(a);
	            } else if (ch == ')') {
	                a[0] = -2;
	                if (!q.isEmpty() && q.peekLast()[0] == -1)
	                    q.removeLast();
	                else
	                    q.add(a);
	            }
	            list.add(a);
	        }
	        
	        List<List<int[]>> lists = new LinkedList<>();
	        lists.add(list);
for (int[] a : q)
	System.out.println(Arrays.toString(a));
	        while (!q.isEmpty()) {
	            List<List<int[]>> nextLists = new LinkedList<>();
	            int[] a = q.removeFirst();
	            int side = a[0], idx = a[1];
	            for (List<int[]> l : lists) {
	                for (int i = 0; i < l.size(); i++) {
	                    if (l.get(i)[0] == side && l.get(i)[1] <= idx) {
	                        List<int[]> nextList = new ArrayList<>();
	                        nextList.addAll(l.subList(0, i));
	                        nextList.addAll(l.subList(i+1, l.size()));
	                        nextLists.add(nextList);
	                    }
	                }
	            }
	            lists = nextLists;
	        }
	        
	        Set<String> res = new HashSet<>();
	        for (List<int[]> l : lists) {
	            StringBuilder b = new StringBuilder();
	            for (int[] a : l) {
	                if (a[0] == -1)
	                    b.append('(');
	                else if (a[0] == -2)
	                    b.append(')');
	                else
	                    b.append((char)('a' + a[0]));
	            }
	            res.add(b.toString());
	        }
	        
	        return new ArrayList<>(res);
	    }
	}
}
