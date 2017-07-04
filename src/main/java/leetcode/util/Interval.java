package leetcode.util;

import java.util.Arrays;

public class Interval {
	public int start, end;
	public Interval() {
		start = 0;
		end = 0;
	}
	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public String toString() {
		int[] array = {start, end};
		return Arrays.toString(array);
	}
}
