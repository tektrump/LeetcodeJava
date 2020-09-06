package tektrup.leetcode.oj;


import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class SlidingWindowMaximum {
	
	public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0)
            return new int[0];
        PriorityQueue<Integer> q = new PriorityQueue<>(k, Collections.reverseOrder());
        int[] results = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            q.add(nums[i]);
            if (q.size() == k) {
                results[index++] = q.peek();
                q.remove(nums[i - k + 1]);
            }
        }
        return results;
    }
	
	public static void main(String[] args) {
		SlidingWindowMaximum instance = new SlidingWindowMaximum();
		int[] nums;
		int k;
		
		nums = new int[]{9, 11};
		k = 2;
		
		int[] results = instance.maxSlidingWindow(nums, k);
		System.out.println("results=" + Arrays.toString(results));
	}
}
