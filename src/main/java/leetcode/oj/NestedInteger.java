package leetcode.oj;


import java.util.LinkedList;
import java.util.List;

public class NestedInteger {

	boolean isInteger = false;
	Integer intVal = null;
	List<NestedInteger> list;

	// Constructor initializes an empty nested list.
	public NestedInteger() {
		list = new LinkedList<>();
	}

	// Constructor initializes a single integer.
	public NestedInteger(int value) {
		intVal = value;
		isInteger = true;
	}

	// @return the single integer that this NestedInteger holds, if it holds a
	// single integer
	// Return null if this NestedInteger holds a nested list
	public Integer getInteger() {
		return intVal;
	}

	// Set this NestedInteger to hold a single integer.
	public void setInteger(int value) {
		intVal = value;
	}

	// Set this NestedInteger to hold a nested list and adds a nested integer to
	// it.
	public void add(NestedInteger ni) {
		list.add(ni);
	}

	// @return the nested list that this NestedInteger holds, if it holds a
	// nested list
	// Return null if this NestedInteger holds a single integer
	public List<NestedInteger> getList() {
		return list;
	}
}
