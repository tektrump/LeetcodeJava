package leetcode.oj;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class LongestIncreasingSubsequence {
	public int lengthOfLIS(int[] nums) {
		int max = 0;
		Comparator<List<Integer>> comp = new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> l1, List<Integer> l2) {
				return l1.get(0) - l2.get(0);
			}
		};
		PriorityQueue<List<Integer>> q = new PriorityQueue<>(comp);
		for (int i = 0; i < nums.length; i++) {
			List<Integer> pair = new ArrayList<>(2);
			pair.add(nums[i]);
			pair.add(i);
			q.add(pair);
		}
		TreeSet<Integer> indices = new TreeSet<>();
		while (!q.isEmpty()) {
			List<Integer> pair = q.poll();
			int index = pair.get(1);
			indices.add(index);
			max = Math.max(max, indices.headSet(index, true).size());
		}
		return max;
	}

	public static void main(String[] args) {
		LongestIncreasingSubsequence instance = new LongestIncreasingSubsequence();
		int[] nums;

		nums = new int[] { 10, 9, 2, 5, 3, 7, 101, 18 };

		int result = instance.lengthOfLIS(nums);
		System.out.println("result=" + result);
	}
}
