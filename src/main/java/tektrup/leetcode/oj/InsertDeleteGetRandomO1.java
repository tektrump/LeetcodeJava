package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class InsertDeleteGetRandomO1 {
	public static void main(String[] args) {
		RandomizedSet instance = new RandomizedSet();
		
//		instance.insert(0);
//		instance.insert(1);
//		instance.remove(0);
//		instance.insert(2);
//		instance.remove(1);
//		System.out.println(instance.getRandom()); // 2
		
		
		instance.insert(0);
		instance.remove(0);
		instance.insert(-1);
		instance.remove(0);
		System.out.println(instance.getRandom());
		System.out.println(instance.getRandom());
		System.out.println(instance.getRandom());
		System.out.println(instance.getRandom());
	}

	static class RandomizedSet {
	    private int idx; // point to last valid element+1
	    private Map<Integer, Integer> valIdx;
	    private List<Integer> list;
	    private static final Random random = new Random();
	    
	    /** Initialize your data structure here. */
	    public RandomizedSet() {
	        idx = 0;
	        valIdx = new HashMap<>();
	        list = new ArrayList<>();
	    }
	    
	    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	    public boolean insert(int val) {
	        Integer prevIdx = valIdx.get(val);
	        if (prevIdx == null) {
	            if (idx == list.size()) {
	                list.add(val);
	                valIdx.put(val, idx++);
	            } else { // idx < list.size; some val was swapped to end and invalidate; we can simply overwrite it
	                list.set(idx, val);
	                valIdx.put(val, idx++);
	            }
	            return true;
	        } else {// if val already exist, do nothing
	            return false;
	        }
	    }
	    
	    /** Removes a value from the set. Returns true if the set contained the specified element. */
	    public boolean remove(int val) {
	        Integer prevIdx = valIdx.remove(val);
	        if (prevIdx == null) // val does not exist
	            return false;
	        if (prevIdx != idx-1) {
	        	swap(list, prevIdx, idx-1); // swap with last valid val on list
	        	valIdx.put(list.get(prevIdx), prevIdx);
	        }
	        idx--; // idx points to the last valid element + 1
	        return true;
	    }
	    
	    /** Get a random element from the set. */
	    public int getRandom() {
	        return list.get(random.nextInt(idx));
	    }
	    
	    private void swap(List<Integer> list, int i, int j) {
	        if (i != j) {
	            int temp = list.get(i);
	            list.set(i, list.get(j));
	            list.set(j, temp);
	        }
	    }
	}
}
