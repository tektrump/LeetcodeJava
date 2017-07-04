package leetcode.oj;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class TopKFrequentElements {
	
	public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numCount = new HashMap<>();
        for (int num : nums) {
            Integer count = numCount.get(num);
            if (count == null)
                count = 0;
            numCount.put(num, count + 1);
        }
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for (Integer count : numCount.values())
            q.add(count);
        Set<Integer> topK = new HashSet<>();
        int l = 0;
        while (l++ < k)
            topK.add(q.poll());
        List<Integer> results = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : numCount.entrySet())
            if (topK.contains(entry.getValue()))
                results.add(entry.getKey());
        return results;
    }
	
	/*
	public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numCount = new HashMap<>();
        for (int num : nums) {
            Integer count = numCount.get(num);
            if (count == null)
                count = 0;
            numCount.put(num, count + 1);
        }
        List<Integer> counts = new ArrayList<>(numCount.values());
        Collections.sort(counts, Collections.reverseOrder());
        Set<Integer> topK = new HashSet<Integer>();
        for (int i = 0; i < k; i++)
            topK.add(counts.get(i));
            
        List<Integer> results = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : numCount.entrySet()) {
            if (topK.contains(entry.getValue()))
                results.add(entry.getKey());
        }
        
        return results;
    }
    */

	public static void main(String[] args) {
		TopKFrequentElements instance = new TopKFrequentElements();
		int[] nums;
		int k;
		
//		nums = new int[]{1,2};
//		k = 2;
		
//		[1,2,3]
//		nums = new int[]{1,1,1,2,2,2,3,3,3};
//		k = 3;
		
//		nums = new int[]{1,1,1,2,2,3};
//		k = 2;
		
		// [-1, 2]
		nums = new int[]{4,1,-1,2,-1,2,3};
		k = 2;
		
		List<Integer> results = instance.topKFrequent(nums, k);
		System.out.println("results=" + results);
	}
}
