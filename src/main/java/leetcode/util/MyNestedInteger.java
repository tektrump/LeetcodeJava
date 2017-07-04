package leetcode.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MyNestedInteger implements INestedInteger {
	
	private Object o;
	
	public MyNestedInteger(Integer i) {
		o = i;
	}
	
	public MyNestedInteger(List<INestedInteger> list) {
		o = list;
	}

	@Override
	public boolean isInteger() {
		return o instanceof Integer;
	}

	@Override
	public Integer getInteger() {
		return (Integer)o;
	}

	@Override
	public List<INestedInteger> getList() {
		return (List<INestedInteger>)o;
	}
	
	@Override
	public String toString() {
		if (this.isInteger())
			return this.getInteger().toString();
		else
			return this.getList().toString();
	}
	
	public static List<INestedInteger> str2list(String s) {
		List<INestedInteger> result = new ArrayList<>();
		String[] splits = s.split(",");
		Stack<List<INestedInteger>> stack = new Stack<>();
		for (String split : splits) {
			int i = 0;
			while (i < split.length()) {
				char c = split.charAt(i);
				if (c == '[') {
					List<INestedInteger> list = new ArrayList<>();
					stack.push(list);
					i++;
				} else if (c == ']') {
					List<INestedInteger> list = stack.pop();
					if (stack.isEmpty())
						result = list;
					else
						stack.peek().add(new MyNestedInteger(list));
					i++;
				} else {
					// digit
					int start = i;
					while (i < split.length() && split.charAt(i) >= '0' && split.charAt(i) <= '9')
						i++;
					int num = Integer.valueOf(split.substring(start, i));
					stack.peek().add(new MyNestedInteger(num));
				}
			}
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		String s = "[[1,1],2,[1,1]]";
		List<INestedInteger> nestedList = MyNestedInteger.str2list(s);
		System.out.println(nestedList);
	}
}
