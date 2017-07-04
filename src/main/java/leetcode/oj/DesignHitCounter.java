package leetcode.oj;


import java.util.TreeMap;

public class DesignHitCounter {
	
	public static void main(String[] args) {
		HitCounter instance = new HitCounter();
		instance.hit(1);
		instance.hit(2);
		instance.hit(3);
		System.out.println("total=" + instance.getHits(4));
		instance.hit(300);
		System.out.println("total=" + instance.getHits(300));
		System.out.println("total=" + instance.getHits(301));
	}
	
	public static class HitCounter {

	    private int total;
	    TreeMap<Integer, Integer> timeCount;
	    
	    /** Initialize your data structure here. */
	    public HitCounter() {
	        total = 0;
	        timeCount = new TreeMap<>();
	    }
	    
	    /** Record a hit.
	        @param timestamp - The current timestamp (in seconds granularity). */
	    public void hit(int timestamp) {
	        purge(timestamp);
	        Integer count = timeCount.get(timestamp);
	        if (count == null)
	            count = 0;
	        timeCount.put(timestamp, count+1);
	        total++;
	    }
	    
	    /** Return the number of hits in the past 5 minutes.
	        @param timestamp - The current timestamp (in seconds granularity). */
	    public int getHits(int timestamp) {
if (timestamp == 301)
	System.out.println();
	        purge(timestamp);
	        return total;
	    }
	    
	    private void purge(int timestamp) {
	        Integer oldest;
	        while (!timeCount.isEmpty() && (oldest = timeCount.firstKey()) < timestamp - 300)
	            total -= timeCount.remove(oldest);
	    }
	}
}
