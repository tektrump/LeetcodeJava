package leetcode.oj;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import leetcode.util.INestedInteger;
import leetcode.util.MyNestedInteger;

public class FlattenNestedListIterator {

	public class NestedIterator implements Iterator<Integer> {
	    
	    private Integer nextInt;
	    private Iterator<INestedInteger> itr;
	    private Stack<List<INestedInteger>> stack;
	    public NestedIterator(List<INestedInteger> nestedList) {
	        nextInt = null;
	        itr = nestedList.iterator();
	        stack = new Stack<>();
	        getNext();
	    }

	    // find next value and update curr
	    @Override
	    public Integer next() {
	        Integer result = nextInt;
	        getNext();
	        return result;
	    }

	    @Override
	    public boolean hasNext() {
	        return nextInt != null;
	    }
	    
	    private void getNext() {
	    	nextInt = null;
	        while (nextInt == null && (!stack.isEmpty() || itr.hasNext())) {
	            if (!stack.isEmpty()) {
	                while (!stack.isEmpty()) {
	                    if (stack.peek().isEmpty())
	                        stack.pop();
	                    else {
	                        INestedInteger i = stack.peek().remove(0);
	                        if (i.isInteger()) {
	                            nextInt = i.getInteger();
	                            break;
	                        } else
	                            stack.push(i.getList());
	                    }
	                }
	            } else {
	                INestedInteger i = itr.next();
	                if (i.isInteger())
	                    nextInt = i.getInteger();
	                else
	                    stack.push(i.getList());
	            }
	        }
	    }
	}
	
	// Solution I: Accepted
	/*
	public class NestedIterator implements Iterator<Integer> {

	    private List<Integer> nums;
	    public NestedIterator(List<NestedInteger> nestedList) {
	        nums = new ArrayList<>();
	        Iterator<NestedInteger> itr = nestedList.iterator();
	        Stack<List<NestedInteger>> stack = new Stack<>();
	        while (!stack.isEmpty() || itr.hasNext()) {
	            while (!stack.isEmpty()) {
	                List<NestedInteger> peek = stack.peek();
	                if (peek.isEmpty()) {
	                    stack.pop();
	                } else {
	                    NestedInteger i = peek.remove(0);
	                    if (i.isInteger()) {
	                        nums.add(i.getInteger());
	                    } else {
	                        stack.push(i.getList());
	                    }
	                }
	            }
	            // stack is empty
	            if (itr.hasNext()) {
		            NestedInteger next = itr.next();
		            if (next.isInteger()) {
		                nums.add(next.getInteger());
		            } else {
		                stack.push(next.getList());
		            }
	            }
	        }
	    }

	    // find next value and update curr
	    @Override
	    public Integer next() {
	        return nums.remove(0);
	    }

	    @Override
	    public boolean hasNext() {
	        return !nums.isEmpty();
	    }
	}
	*/
	
	public static void main(String[] args) {
		FlattenNestedListIterator instance = new FlattenNestedListIterator();
		String s = "[[1,1],2,[1,1]]";
		List<INestedInteger> nestedList = MyNestedInteger.str2list(s);
		NestedIterator i = instance.new NestedIterator(nestedList);
		while (i.hasNext()) 
			System.out.println(i.next());
	}
}
