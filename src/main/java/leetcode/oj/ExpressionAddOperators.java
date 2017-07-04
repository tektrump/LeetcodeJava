package leetcode.oj;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public abstract class ExpressionAddOperators {
	public abstract List<String> addOperators(String num, int target);
	
	public static void main(String[] args) {
		ExpressionAddOperators instance = new SolutionV();
		String num; int target;
		List<String> results;
		
//		num = "123"; target = 6;
		
		// 2*3+2, 2+2*3
		num = "232"; target = 8;
		
		// 1*0 + 5, 10-5
//		num = "2147483648"; target = -2147483648;
		
		// 10+5, 1+0+5
//		num = "105"; target = 5;
		
		
		results = instance.addOperators(num, target);
		System.out.println("results=" + results);
	}
	
	
	static class SolutionV extends ExpressionAddOperators {
		public List<String> addOperators(String num, int target) {
	        if (num.isEmpty())
	            return Collections.emptyList();
	        List<String> list = new LinkedList<>();
	        List<String> rets = new LinkedList<>();
	        bt(list, num, 0, rets, target);
	        return rets;
	    }
	    
	    private static final String[] ops = {"+", "-", "*"};
	    private void bt(List<String> list, String num, int l, List<String> rets, int target) {
	        if (l == num.length()) {
	            if (eval(list) == target) {
	                StringBuilder b = new StringBuilder();
	                for (String s : list)
	                    b.append(s);
	                rets.add(b.toString());
	            }
	        } else {
	            for (int r = l+1; r <= num.length(); r++) {
	                String next = num.substring(l, r);
	                if (Long.valueOf(next) > Integer.MAX_VALUE)
	                    break;
	                if (list.isEmpty()) { // ERROR: special case
	                    list.add(next); // modify
	                    bt(list, num, r, rets, target);
	                    list.remove(list.size()-1); // restore
	                } else {
	                    for (String op : ops) {
	                        list.add(op);
	                        list.add(next);  // modify
	                        bt(list, num, r, rets, target);
	                        // list = list.subList(0, list.size()-2);
	                        list.remove(list.size()-1);
	                        list.remove(list.size()-1); // restore
	                    }
	                }
	                if (num.charAt(l) == '0')
	                    break;
	            }
	        }
	    }
	    
	    private int eval(List<String> list) {
System.out.println("list=" + list);
	        String op = null;
	        Queue<Integer> q = new LinkedList<>();
	        for (String s : list) {
	            char ch = s.charAt(0);
	            if (ch >= '0' && ch <= '9') {
	                if (op == null || op.equals("+"))
	                    q.add(Integer.valueOf(s));
	                else if (op.equals("-"))
	                    q.add(-Integer.valueOf(s));
	                else
	                    q.add(q.remove()*Integer.valueOf(s));
	            } else 
	                op = s;
	        }
	        int ret = 0;
	        for (Integer num : q)
	            ret += num;
	        return ret;
	    }
	}
	
	
	static class SolutionIV extends ExpressionAddOperators {
		private Map<List<Integer>, List<String>> cache = new HashMap<>();
	    private static final char[] ops = {'+', '-', '*'};
	    public List<String> addOperators(String num, int target) {
	        if (num == null || num.isEmpty())
	            return Collections.emptyList();
	        List<String> results = new ArrayList<>();
	        List<String> strs = build(num, 0, num.length()-1);
	        for (String str : strs) {
	            if (eval(str) == target)
	                results.add(str);
	        }
	        return results;
	    }
	    
	    private List<String> build(String num, int l, int r) {
	        if (l > r)
	            return null;
	        List<Integer> key = new ArrayList<>(2);
	        key.add(l);
	        key.add(r);
	        List<String> results = cache.get(key);
	        if (results != null)
	            return results;
	         results = new ArrayList<>();
	        // results.add(num.substring(l, r+1)); // ERROR: must skip format like "05"
	        for (int d = l+1; d <= r+1; d++) {
	            String left = num.substring(l, d);
	            List<String> subs = build(num, d, r);
	            if (subs == null)
	                results.add(left);
	            else {
	                for (String sub : subs) {
	                    for (char op : ops) {
	                        StringBuilder builder = new StringBuilder();
	                        builder.append(left).append(op).append(sub);
	                        results.add(builder.toString());
	                    }
	                }
	            }
	            if (left.equals("0"))
	                break;
	        }
	        cache.put(key, results);
	        return results;
	    }
	    
	    private int eval(String s) {
	        char op = '+';
	        Stack<Integer> stack = new Stack<>();
	        int num = 0;
	        for (int i = 0; i < s.length(); i++) {
	            char ch = s.charAt(i);
	            if (ch >= '0' && ch <= '9')
	                num = num*10 + (ch - '0');
	            // finish a number
	            if (i == s.length()-1 || ch < '0' || ch > '9') {
	                if (op == '+')
	                    stack.push(num);
	                else if (op == '-')
	                    stack.push(-num);
	                else
	                    stack.push(stack.pop()*num);
	                num = 0;
	                op = ch; // not ideal for last digit, but it's the last one
	            }
	        }
	        int result = 0;
	        while (!stack.isEmpty())
	            result += stack.pop();
	        return result;
	    }
	}
	
	
	static class SolutionIII extends ExpressionAddOperators {
		private static final char[] ops = {'+', '-', '*'};
	    public List<String> addOperators(String num, int target) {
	        if (num == null || num.isEmpty())
	            return Collections.emptyList();
	        List<StringBuilder> paths = build(num.toCharArray(), 0, num.length()-1);
	        List<String> results = new ArrayList<>();
	        for (StringBuilder path : paths) {
	            if (eval(path) == target)
	                results.add(path.toString());
	        }
	        return results;
	    }
	    
	    private Map<List<Integer>, List<StringBuilder>> cache = new HashMap<>();
	    private List<StringBuilder> build(char[] chars, int l, int r) {
	        List<Integer> key = new ArrayList<>(2);
	        key.add(l);
	        key.add(r);
	        List<StringBuilder> results = cache.get(key);
	        if (results != null)
	            return results;
	        results = new ArrayList<>();
	        if (chars[l] != '0' || l == r) {
		        StringBuilder result0 = new StringBuilder();
		        for (int i = l; i <= r; result0.append(chars[i++]));
		        results.add(result0);
	        }
	        for (int i = l; i < r; i++) {
	            List<StringBuilder> lsubs = build(chars, l, i);
	            List<StringBuilder> rsubs = build(chars, i+1, r);
	            for (StringBuilder lsub : lsubs) {
	                for (StringBuilder rsub : rsubs) {
	                    for (char op : ops) {
	                        StringBuilder result = new StringBuilder();
	                        results.add(result.append(lsub).append(op).append(rsub));
	                    }
	                }
	            }
	        }
	        cache.put(key, results);
	        return results;
	    }
	    
	    private int eval(StringBuilder path) {
	        Deque<Integer> nums = new LinkedList<>();
	        int[] array = getNum(path, 0);
	        nums.add(array[0]);
	        int i = array[1];
	        while (i < path.length()) {
	            char op = path.charAt(i);
	            array = getNum(path, i+1);
	            int num = array[0];
	            i = array[1];
	            if (op == '+')
	                nums.add(num);
	            else if (op == '-')
	                nums.add(-num);
	            else
	                nums.add(nums.removeLast()*num);
	        }
	        int result = 0;
	        while (!nums.isEmpty())
	            result += nums.removeLast();
	        return result;
	    }
	    
	    private int[] getNum(StringBuilder path, int i) {
	        int num = 0;
	        while (i < path.length()) {
	            char ch = path.charAt(i);
	            if (ch >= '0' && ch <= '9') {
	                num = num*10 + (ch - '0');
	                i++;
	            } else
	                break;
	        }
	        return new int[]{num, i};
	    }
	}
	
	
	// Solution II: Misunderstand Problem
    static class SolutionII extends ExpressionAddOperators {
	    private static final char[] ops = {'+', '-', '*'};
	    public List<String> addOperators(String num, int target) {
	        if (num == null || num.isEmpty())
	            return Collections.emptyList();
	        // bfs build all possible combinations of nums & operators
	        List<StringBuilder> paths = new ArrayList<>();
	        StringBuilder path0 = new StringBuilder();
	        path0.append(num.charAt(0));
	        paths.add(path0);
	        for (int i = 1; i < num.length(); i++) {
	            List<StringBuilder> nextPaths = new ArrayList<>();
	            int curr = num.charAt(i) - '0';
	            for (StringBuilder path : paths) {
	                for (char op : ops) {
	                    StringBuilder nextPath = new StringBuilder(path);
	                    nextPath.append(op).append(curr);
	                    nextPaths.add(nextPath);
	                }
	            }
	            paths = nextPaths;
	        }
	        List<String> results = new ArrayList<>();
	        for (StringBuilder path : paths) {
	            if (eval(path) == target)
	                results.add(path.toString());
	        }
	        return results;
	    }
	    
	    private int eval(StringBuilder path) {
	        Stack<Integer> stack = new Stack<>();
	        stack.push(path.charAt(0) - '0');
	        int i = 1;
	        while (i < path.length()) {
	            char op = path.charAt(i);
	            int num = path.charAt(i+1) - '0';
	            if (op == '+')
	                stack.push(num);
	            else if (op == '-')
	                stack.push(-num);
	            else
	                stack.push(stack.pop()*num);
	            i += 2;
	        }
	        int result = 0;
	        while (!stack.isEmpty())
	            result += stack.pop();
	        return result;
	    }
    }
    
    
    /*
    private static final char[] ops = {'+', '-', '*'};
    public List<String> addOperators(String num, int target) {
        if (num == null || num.isEmpty())
            return Collections.emptyList();
        char[] chars = num.toCharArray();
        List<StringBuilder> builders = new ArrayList<>();
        StringBuilder builder0 = new StringBuilder();
        builder0.append(chars[0]);
        builders.add(builder0);
        int idx = 1;
        while (idx < num.length()) {
            List<StringBuilder> nextBuilders = new ArrayList<>();
            char ch = chars[idx++];
            for (StringBuilder builder : builders) {
                for (char op : ops) {
                    StringBuilder nextBuilder = new StringBuilder(builder);
                    nextBuilder.append(op).append(ch);
                    nextBuilders.add(nextBuilder);
                }
            }
            builders = nextBuilders;
        }
        
        List<String> results = new ArrayList<>();
        for (StringBuilder builder : builders) {
            Deque<Character> q = new LinkedList<>();
            for (int i = 0; i < builder.length(); i++) {
                char ch = builder.charAt(i);
                if (ch == '*')
                    q.addLast((char)((q.pollLast() - '0')*(builder.charAt(++i) - '0') + '0'));
                else
                    q.addLast(ch); // SYNTAX: no add() for Deque, only addFirst() & addLast()
            }
            int val = q.pollFirst() - '0';
            while (!q.isEmpty()) {
                char op = q.pollFirst();
                int val2 = q.pollFirst() - '0';
                if (op == '+')
                    val += val2;
                else
                    val -= val2;
            }
            if (val == target)
                results.add(builder.toString());
        }
        
        return results;
    }
    */
	

	static class SolutionI extends ExpressionAddOperators {
		private static final char[] ops = {'+', '-', '*'};
	    public List<String> addOperators(String num, int target) {
	    	if (num == null || num.length() == 0)
	    		return Collections.emptyList();
	        char[] chars = num.toCharArray();
	        List<StringBuilder> builders = new ArrayList<>();
	        StringBuilder builder0 = new StringBuilder();
	        builder0.append(chars[0]);
	        builders.add(builder0);
	        int idx = 1;
	        while (idx < num.length()) {
	            List<StringBuilder> nextBuilders = new ArrayList<>();
	            char ch = chars[idx++];
	            for (StringBuilder builder : builders) {
	                for (char op : ops) {
	                    StringBuilder nextBuilder = new StringBuilder(builder);
	                    nextBuilder.append(op).append(ch);
	                    nextBuilders.add(nextBuilder);
	                }
	            }
	            builders = nextBuilders;
	        }
	        
	        List<String> results = new ArrayList<>();
	        for (StringBuilder builder : builders) {
	            Deque<Character> q = new LinkedList<>();
	            for (int i = 0; i < builder.length(); i++) {
	                char ch = builder.charAt(i);
	                if (ch == '*')
	                    q.addLast((char)((q.pollLast() - '0')*(builder.charAt(++i) - '0') + '0'));
	                else
	                    q.addLast(ch); // SYNTAX: no add() for Deque, only addFirst() & addLast()
	            }
	            int val = q.pollFirst() - '0';
	            while (!q.isEmpty()) {
	                char op = q.pollFirst();
	                int val2 = q.pollFirst() - '0';
	                if (op == '+')
	                    val += val2;
	                else
	                    val -= val2;
	            }
	            if (val == target)
	                results.add(builder.toString());
	        }
	        
	        return results;
	    }
	}
}
