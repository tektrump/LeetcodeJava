package leetcode.oj;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import leetcode.util.Interval;

public class SummaryRanges {
	private List<Interval> intervals;
    private TreeMap<Integer, Interval> startInterval;
    private TreeMap<Integer, Interval> endInterval;
    public SummaryRanges() {
        intervals = new ArrayList<>();
        startInterval = new TreeMap<>();
        endInterval = new TreeMap<>();
    }
    
    public void addNum(int val) {
        Interval left = null, right = null;
        Map.Entry<Integer, Interval> floor = startInterval.floorEntry(val);
        if (floor != null)
            left = floor.getValue();
        Map.Entry<Integer, Interval> ceiling = endInterval.ceilingEntry(val);
        if (ceiling != null)
            right = ceiling.getValue();
            
        // included
        if ((left != null && left.end >= val) || (right != null && right.start <= val))
            return;
        else {
            // link up
        	if (left != null && left.end + 1 == val && right != null && right.start - 1 == val) {
                // remove right interval and expand left interval to include both
                remove(left);
                remove(right);
                add(left.start, right.end);
            } else {
                // expand left
                if (left != null && left.end + 1 == val) {
                    endInterval.remove(left.end);
                    left.end = val;
                    endInterval.put(val, left);
                } else if (right != null && val + 1 == right.start) { // expand right
                    startInterval.remove(right.start);
                    right.start = val;
                    startInterval.put(val, right);
                } else {
                	add(val, val);
                }
            }
        }
    }
    
    private void add(int start, int end) {
        Interval interval = new Interval(start, end);
        intervals.add(interval);
        startInterval.put(start, interval);
        endInterval.put(end, interval);
    }
    
    private void remove(Interval interval) {
        intervals.remove(interval);
        startInterval.remove(interval.start);
        endInterval.remove(interval.end);
    }
    
    public List<Interval> getIntervals() {
        return new ArrayList<>(startInterval.values());
    }
    
    public static void main(String[] args) {
    	SummaryRanges instance = new SummaryRanges();
    	instance.addNum(1);
    	System.out.println(instance.getIntervals());
    	
    	instance.addNum(3);
    	System.out.println(instance.getIntervals());
    	
    	instance.addNum(7);
    	System.out.println(instance.getIntervals());
    	
    	instance.addNum(2);
    	System.out.println(instance.getIntervals());
    	
    	instance.addNum(6);
    	System.out.println(instance.getIntervals());
	}
}
