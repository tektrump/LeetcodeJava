package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

public abstract class CountOfSmallerNumbersAfterSelf {
	public abstract List<Integer> countSmaller(int[] nums);
	public static void main(String[] args) {
		CountOfSmallerNumbersAfterSelf instance = new SolutionVI();
		int[] nums;
		List<Integer> res;
		
		// 1,0
//		nums = new int[]{5, 2};
		
		// 0,0
//		nums = new int[]{2, 5};
		
		// 2,1,1,0
		nums = new int[]{5, 2, 6, 1};

		
//		String s = LeetcodeUtils.readText(instance);
//		nums = ArrayUtil.str2intArray(s);
		
		
		long t1 = System.currentTimeMillis();
		res = instance.countSmaller(nums);
		long t2 = System.currentTimeMillis();
		System.out.println("results=" + res);
		System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
	
	
	// Solution VI: Accepted
	// merge sort
	// convert each number to an array with number and its index; then merge sort them
	// from high to low by value. during the merge, if we pick a pair from left half,
	// then count arrays on the right half that have smaller value.
	// NOTE: this work because we do this in sequence: split -> sort subs -> count -> merge;
	// so by the time we count, we haven't mixed left half and right half yet: their relative
	// position in the original array still holds.
	static class SolutionVI extends CountOfSmallerNumbersAfterSelf {
		public List<Integer> countSmaller(int[] nums) {
	        if (nums.length == 0)
	            return Collections.emptyList();
	            
	        List<Integer> res = new ArrayList<>();
	        int[][] pairs = new int[nums.length][2];
	        for (int i = 0; i < nums.length; i++) {
	            res.add(0);
	            pairs[i] = new int[]{nums[i], i};
	        }
	        mergeSort(pairs, 0, pairs.length-1, res);
	        return res;
	    }
	    
	    private void mergeSort(int[][] pairs, int l, int r, List<Integer> res) {
	        if (l >= r)
	            return;
	            
	        int m = (l + r) >> 1;
	        mergeSort(pairs, l, m, res);
	        mergeSort(pairs, m+1, r, res);
	        int[][] temp = new int[r-l+1][2];
	        int i = l, j = m+1, k = 0;
	        while (i <= m || j <= r) {
	            if (j > r || (i <= m && pairs[i][0] > pairs[j][0])) {
	                temp[k++] = pairs[i];
	                int count = r - j + 1;
	                res.set(pairs[i][1], res.get(pairs[i][1]) + count);
	                i++;
	            } else {
	                temp[k++] = pairs[j++];
	            }
	        }
	        for (int x = l; x <= r; pairs[x] = temp[x-l], x++);
	    }
	}
	
	
	// Solution V: Logic Error
	// convert each number to an array with number and its index.
	// quick sort from high to low: pick a pivot, and count numbers that are smaller than pivot.
	// ERROR: as we swap, we mess up the original order of the array: 
	// numbers on the left or right of pivot will be swapped. To fix this, we must use merge sort.
	static class SolutionV extends CountOfSmallerNumbersAfterSelf {
		public List<Integer> countSmaller(int[] nums) {
	        if (nums.length == 0)
	            return Collections.emptyList();
	            
	        List<Integer> res = new ArrayList<>(nums.length);
	        int[][] arrays = new int[nums.length][2];
	        for (int i = 0; i < nums.length; i++) {
	            res.add(0);
	            arrays[i] = new int[]{nums[i], i};
	        }
	        quickSort(arrays, 0, nums.length-1, res);
	        
	        return res;
	    }
	    
	    private void quickSort(int[][] arrays, int l, int r, List<Integer> res) {
	        if (l > r)
	            return;
	        int m = (l + r) >> 1;
	        int[] pivot = arrays[m];
	        res.set(pivot[1], res.get(pivot[1]) + arrays.length-1-r);
	        swap(arrays, m, r);
	        int i = l, j = l;
	        for (; j < r; j++) {
	            int[] array = arrays[j];
	            if (array[0] >= pivot[0]) {
	                swap(arrays, i++, j);
	            } else {
	                // num is less than pivot, and if idx>pivot, then bump counter of pivot
	                if (array[1] > pivot[1])
	                    res.set(pivot[1], res.get(pivot[1]) + 1);
	            }
	        }
	        // recursion
	        swap(arrays, i, r);
	        quickSort(arrays, l, i-1, res);
	        quickSort(arrays, i+1, r, res);
	    }
	    
	    private void swap(int[][] arrays, int l, int r) {
	        if (l != r) {
	            int[] temp = arrays[l];
	            arrays[l] = arrays[r];
	            arrays[r] = temp;
	        }
	    }
	}
	
	
	// Solution IV: Accepted but slow
    // add num from right to left insert into sorted position (insertion sort).
    // for each number, binary search the index of inclusive ceiling, andd insert num at that index,
	// which will shift all subsequent elements to right. overall time complexity: O(n^2).
    // shouldn't be accepted.
	static class SolutionIV extends CountOfSmallerNumbersAfterSelf {
	    public List<Integer> countSmaller(int[] nums) {
	        List<Integer> result = new LinkedList<>();
	        List<Integer> list = new ArrayList<>();
	        for (int i = nums.length-1; i >= 0; i--) {
	            int num = nums[i];
	            int idx = binary(list, 0, list.size()-1, num);
	            if (idx < 0)
	                idx = list.size();
	            result.add(0, idx);
	            list.add(idx, num); // O(n)
	        }
	        return result;
	    }
	    
	    private int binary(List<Integer> list, int lt, int rt, int target) {
	        if (lt > rt)
	            return -1;
	        int m = (lt + rt)/2;
	        if (list.get(m) >= target && (m-1 < lt || list.get(m-1) < target))
	            return m;
	        else if (m-1 >= lt && list.get(m-1) >= target)
	            return binary(list, lt, m-1, target);
	        else
	            return binary(list, m+1, rt, target);
	    }
	}
	
	
	// Solution III: TLE
    // O(nlogn). sort numbers from high to low, then poll from q (larger num polled first, then smaller);
	// add polled number to a tree sorted by index; since existing numbers are all smaller than new one,
	// we just need to find out how many of the existing numbers are to the right of new number.
    // must deal with duplicates.
	static class SolutionIII extends CountOfSmallerNumbersAfterSelf {
		public List<Integer> countSmaller(int[] nums) {
	        // list: idx, val
	        Comparator<List<Integer>> comp = new Comparator<List<Integer>>(){
	            public int compare(List<Integer> l1, List<Integer> l2) {
	                return l2.get(1) == l1.get(1) ? l2.get(0) - l1.get(0) : l2.get(1) - l1.get(1);
	            }
	        };
	        PriorityQueue<List<Integer>> q = new PriorityQueue<>(comp);
	        for (int i = 0; i < nums.length; i++) {
	            List<Integer> list = new ArrayList<>(2);
	            list.add(i);
	            list.add(nums[i]);
	            q.add(list);
	        }
	        TreeSet<Integer> indices = new TreeSet<>();
	        Map<Integer, Integer> valDups = new HashMap<>();
	        List<Integer> results = new ArrayList<>();
	        for (int i = 0; i < nums.length; i++)
	            results.add(0);
	        while (!q.isEmpty()) {
	            List<Integer> list = q.poll();
	            int index = list.get(0), val = list.get(1);
	            indices.add(index);
	            Integer dups = valDups.get(val);
	            if (dups == null)
	                dups = 0;
	            valDups.put(val, dups+1);
	            int count = indices.headSet(index, false).size() - dups; // for dups, i always add higher index first
	            results.set(index, count);
	        }
	        return results;
	    }
	}
	
	
	// Solution II: TLE
	// to avoid duplicate, we create a TreeMap of int, where first val is the num
	// and second is its index in the given array. associated, we also provide a
	// customized comparator.
	// we then add elements from right to left, and search for headSet() and count its size.
	// CATCH: it takes O(logn) to find floor/ ceiling, but 
	// however, to count the size of sub set, it will take O(n) time, which makes over-all time
	// complexity of this solution O(n^2), thus leads to TLE.
	static class SolutionII extends CountOfSmallerNumbersAfterSelf {
		public List<Integer> countSmaller(int[] nums) {
	        if (nums.length == 0)
	            return Collections.emptyList();
	        
	        TreeSet<int[]> tree = new TreeSet<>(new Comparator<int[]>(){
	            public int compare(int[] a1, int[] a2) {
	                return a1[0] != a2[0] ? a1[0] - a2[0] : a1[1] - a2[1];
	            }
	        });
	        
	        List<Integer> res = new ArrayList<>();
	        for (int i = 0; i < nums.length; res.add(0), i++);
	        for (int i = nums.length-1; i >= 0; i--) {
	            int[] pair = {nums[i], i};
	            int count = tree.headSet(pair, false).size();
	            res.set(i, count);
	            tree.add(pair);
	        }
	        
	        return res;
	    }
	}
	
	
	// SolutionI: TLE
	static class SolutionI extends CountOfSmallerNumbersAfterSelf {
		public List<Integer> countSmaller(int[] nums) {
	        TreeMap<Integer, List<Integer>> numIndices = new TreeMap<>();
	        for (int i = 0; i < nums.length; i++) {
	            int num = nums[i];
	            List<Integer> indices = numIndices.get(num);
	            if (indices == null) {
	                indices = new LinkedList<>();
	                numIndices.put(num, indices);
	            }
	            indices.add(i);
	        }
	        List<Integer> results = new LinkedList<>();
	        for (int i = 0; i < nums.length; i++)
	            results.add(0);
	        TreeSet<Integer> sortedIndices = new TreeSet<>();
	        Iterator<Map.Entry<Integer, List<Integer>>> itr = numIndices.entrySet().iterator();
	        while (itr.hasNext()) {
	            Map.Entry<Integer, List<Integer>> entry = itr.next();
	            for (Integer index : entry.getValue()) {
	                results.set(index, sortedIndices.tailSet(index).size());
	                sortedIndices.add(index);
	            }
	        }
	        return results;
	    }
	}

}
