package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tektrup.leetcode.util.ArrayUtil;

public abstract class CreateMaximumNumber {
	public abstract int[] maxNumber(int[] nums1, int[] nums2, int k);
	public static void main(String[] args) {
		CreateMaximumNumber instance = new SolutionIII();
		int[] nums1, nums2; int k;
		int[] result;
		long t1, t2;
		
		// 9,8,6,5,3
		nums1 = ArrayUtil.str2intArray("[3, 4, 6, 5]");
		nums2 = ArrayUtil.str2intArray("[9, 1, 2, 5, 8, 3]");
		k = 5;
		
		// 6,7,6,0,4
//		nums1 = ArrayUtil.str2intArray("[6, 7]");
//		nums2 = ArrayUtil.str2intArray("[6, 0, 4]");
//		k = 5;
		
		// 9,8,9
//		nums1 = ArrayUtil.str2intArray("[3, 9]");
//		nums2 = ArrayUtil.str2intArray("[8, 9]");
//		k = 3;
		
		// [7,6,7,5,1,0,2,1,0,1,0,5,6,0,5,0,1,0,3,9,1,2,8,0,9,8,1,4,7,3,0]
//		nums1 = ArrayUtil.str2intArray("[5,0,2,1,0,1,0,3,9,1,2,8,0,9,8,1,4,7,3]");
//		nums2 = ArrayUtil.str2intArray("[7,6,7,1,0,1,0,5,6,0,5,0]");
//		k = 31;
		
		nums1 = ArrayUtil.str2intArray("[8,9,7,3,5,9,1,0,8,5,3,0,9,2,7,4,8,9,8,1,0,2,0,2,7,2,3,5,4,7,4,1,4,0,1,4,2,1,3,1,5,3,9,3,9,0,1,7,0,6,1,8,5,6,6,5,0,4,7,2,9,2,2,7,6,2,9,2,3,5,7,4,7,0,1,8,3,6,6,3,0,8,5,3,0,3,7,3,0,9,8,5,1,9,5,0,7,9,6,8,5,1,9,6,5,8,2,3,7,1,0,1,4,3,4,4,2,4,0,8,4,6,5,5,7,6,9,0,8,4,6,1,6,7,2,0,1,1,8,2,6,4,0,5,5,2,6,1,6,4,7,1,7,2,2,9,8,9,1,0,5,5,9,7,7,8,8,3,3,8,9,3,7,5,3,6,1,0,1,0,9,3,7,8,4,0,3,5,8,1,0,5,7,2,8,4,9,5,6,8,1,1,8,7,3,2,3,4,8,7,9,9,7,8,5,2,2,7,1,9,1,5,5,1,3,5,9,0,5,2,9,4,2,8,7,3,9,4,7,4,8,7,5,0,9,9,7,9,3,8,0,9,5,3,0,0,3,0,4,9,0,9,1,6,0,2,0,5,2,2,6,0,0,9,6,3,4,1,2,0,8,3,6,6,9,0,2,1,6,9,2,4,9,0,8,3,9,0,5,4,5,4,6,1,2,5,2,2,1,7,3,8,1,1,6,8,8,1,8,5,6,1,3,0,1,3,5,6,5,0,6,4,2,8,6,0,3,7,9,5,5,9,8,0,4,8,6,0,8,6,6,1,6,2,7,1,0,2,2,4,0,0,0,4,6,5,5,4,0,1,5,8,3,2,0,9,7,6,2,6,9,9,9,7,1,4,6,2,8,2,5,3,4,5,2,4,4,4,7,2,2,5,3,2,8,2,2,4,9,8,0,9,8,7,6,2,6,7,5,4,7,5,1,0,5,7,8,7,7,8,9,7,0,3,7,7,4,7,2,0,4,1,1,9,1,7,5,0,5,6,6,1,0,6,9,4,2,8,0,5,1,9,8,4,0,3,1,2,4,2,1,8,9,5,9,6,5,3,1,8,9,0,9,8,3,0,9,4,1,1,6,0,5,9,0,8,3,7,8,5]");
		nums2 = ArrayUtil.str2intArray("[7,8,4,1,9,4,2,6,5,2,1,2,8,9,3,9,9,5,4,4,2,9,2,0,5,9,4,2,1,7,2,5,1,2,0,0,5,3,1,1,7,2,3,3,2,8,2,0,1,4,5,1,0,0,7,7,9,6,3,8,0,1,5,8,3,2,3,6,4,2,6,3,6,7,6,6,9,5,4,3,2,7,6,3,1,8,7,5,7,8,1,6,0,7,3,0,4,4,4,9,6,3,1,0,3,7,3,6,1,0,0,2,5,7,2,9,6,6,2,6,8,1,9,7,8,8,9,5,1,1,4,2,0,1,3,6,7,8,7,0,5,6,0,1,7,9,6,4,8,6,7,0,2,3,2,7,6,0,5,0,9,0,3,3,8,5,0,9,3,8,0,1,3,1,8,1,8,1,1,7,5,7,4,1,0,0,0,8,9,5,7,8,9,2,8,3,0,3,4,9,8,1,7,2,3,8,3,5,3,1,4,7,7,5,4,9,2,6,2,6,4,0,0,2,8,3,3,0,9,1,6,8,3,1,7,0,7,1,5,8,3,2,5,1,1,0,3,1,4,6,3,6,2,8,6,7,2,9,5,9,1,6,0,5,4,8,6,6,9,4,0,5,8,7,0,8,9,7,3,9,0,1,0,6,2,7,3,3,2,3,3,6,3,0,8,0,0,5,2,1,0,7,5,0,3,2,6,0,5,4,9,6,7,1,0,4,0,9,6,8,3,1,2,5,0,1,0,6,8,6,6,8,8,2,4,5,0,0,8,0,5,6,2,2,5,6,3,7,7,8,4,8,4,8,9,1,6,8,9,9,0,4,0,5,5,4,9,6,7,7,9,0,5,0,9,2,5,2,9,8,9,7,6,8,6,9,2,9,1,6,0,2,7,4,4,5,3,4,5,5,5,0,8,1,3,8,3,0,8,5,7,6,8,7,8,9,7,0,8,4,0,7,0,9,5,8,2,0,8,7,0,3,1,8,1,7,1,6,9,7,9,7,2,6,3,0,5,3,6,0,5,9,3,9,1,1,0,0,8,1,4,3,0,4,3,7,7,7,4,6,4,0,0,5,7,3,2,8,5,1,4,5,8,5,6,7,5,7,3,3,9,6,8,1,5,1,1,1,0,3]");
		k = 500;
		
		t1 = System.currentTimeMillis();
		result = instance.maxNumber(nums1, nums2, k);
		t2 = System.currentTimeMillis();
		System.out.println(String.format("result=%s, total time=%,dms", Arrays.toString(result), (t2 - t1)));
	}
	
	
	static class SolutionV extends CreateMaximumNumber {
		public int[] maxNumber(int[] nums1, int[] nums2, int k) {
	        int[] result = new int[k];
	        List<int[]> pairs = new LinkedList<>();
	        int[] pair0 = {0, 0};
	        pairs.add(pair0);
	        for (int i = 0; i < k; i++) {
	            List<int[]> nextPairs = new LinkedList<>();
	            int maxDigit = -1;
System.out.println("========= " + i + " =========");
	            for (int[] pair : pairs) {
//System.out.println(Arrays.toString(pair));
System.out.println("size=" + pairs.size());
	                int idx1 = pair[0], idx2 = pair[1];
	                boolean found1 = false;
	                for (int j = idx1; j < nums1.length && nums1.length-j+nums2.length-idx2 >= k-i; j++) {
	                    if (nums1[j] > maxDigit) {
	                        maxDigit = nums1[j];
	                        nextPairs.clear();
	                        nextPairs.add(new int[]{j+1, idx2});
	                        found1 = true;
	                    } else if (nums1[j] == maxDigit && !found1) {
	                        nextPairs.add(new int[]{j+1, idx2});
	                        found1 = true;
	                    }
	                }
	                boolean found2 = false;
	                for (int j = idx2; j < nums2.length && nums2.length-j+nums1.length-idx1 >= k-i; j++) {
	                    if (nums2[j] > maxDigit) {
	                        maxDigit = nums2[j];
	                        nextPairs.clear();
	                        nextPairs.add(new int[]{idx1, j+1});
	                        found2 = true;
	                    } else if (nums2[j] == maxDigit && !found2) {
	                        nextPairs.add(new int[]{idx1, j+1});
	                        found2 = true;
	                    }
	                }
	            }
	            result[i] = maxDigit;
	            pairs = nextPairs;
	        }
	        return result;
	    }
	}
	
	// Solution IV: Logic Error
	static class SolutionIV extends CreateMaximumNumber {
		public int[] maxNumber(int[] nums1, int[] nums2, int k) {
	        int[] result = new int[k];
	        List<int[]> pairs = new LinkedList<>();
	        int[] pair0 = {0, 0};
	        pairs.add(pair0);
	        for (int i = 0; i < k; i++) {
	            List<int[]> nextPairs = new LinkedList<>();
	            int maxDigit1 = -1, maxDigit2 = -1;
	            int[] nextPair1 = new int[2], nextPair2 = new int[2];
System.out.println("------- " + i + " -------");
	            for (int[] pair : pairs) {
System.out.println(Arrays.toString(pair));
	                int idx1 = pair[0], idx2 = pair[1];
	                for (int j = idx1; j < nums1.length && nums1.length-j+nums2.length-idx2 >= k-i; j++) {
	                    if (nums1[j] > maxDigit1) {
	                        maxDigit1 = nums1[j];
	                        nextPair1 = new int[]{j+1, idx2};
	                    } else if (nums1[j] == maxDigit1) {
	                        nextPair1[0] = Math.min(nextPair1[0], j+1);
	                    }
	                }
	                for (int j = idx2; j < nums2.length && nums2.length-j+nums1.length-idx1 >= k-i; j++) {
	                    if (nums2[j] > maxDigit2) {
	                        maxDigit2 = nums2[j];
	                        nextPair2 = new int[]{idx1, j+1};
	                    } else if (nums2[j] == maxDigit2) {
	                        nextPair2[1] = Math.min(nextPair2[1], j+1);
	                    }
	                }
	            }
	            result[i] = Math.max(maxDigit1, maxDigit2);
	            if (maxDigit1 >= maxDigit2)
	                nextPairs.add(nextPair1);
	            if (maxDigit1 <= maxDigit2)
	                nextPairs.add(nextPair2);
	            pairs = nextPairs;
	        }
	        return result;
	    }
	}
	
	
	// Solution III: Accepted
	static class SolutionIII extends CreateMaximumNumber {
		public int[] maxNumber(int[] nums1, int[] nums2, int k) {
	        if (k == 0)
	            return new int[0];
	        int[] result = new int[k];
	        List<int[]> pairs = new LinkedList<>();
	        int[] pair0 = {0, 0};
	        pairs.add(pair0);
	        for (int i = 0; i < k; i++) {
	            List<int[]> nextPairs = new LinkedList<>();
	            int maxDigit = -1;
System.out.println("------- " + i + " -------");
	            for (int[] pair : pairs) {
//System.out.println(Arrays.toString(pair));
System.out.println("size=" + pairs.size());
	                int idx1 = pair[0], idx2 = pair[1];
	                for (int j = idx1; j < nums1.length && nums1.length-j+nums2.length-idx2 >= k-i; j++) {
	                    if (nums1[j] > maxDigit) {
	                        maxDigit = nums1[j];
	                        nextPairs.clear();
	                        nextPairs.add(new int[]{j+1, idx2});
	                    }
	                }
	                boolean found = false;
	                for (int j = idx2; j < nums2.length && nums1.length-idx1+nums2.length-j >= k-i; j++) {
	                    if (nums2[j] > maxDigit) {
	                        found = true;
	                        maxDigit = nums2[j];
	                        nextPairs.clear();
	                        nextPairs.add(new int[]{idx1, j+1});
	                    } else if (nums2[j] == maxDigit && !found) {
	                        found = true;
	                        nextPairs.add(new int[]{idx1, j+1});
	                    }
	                }
	            }
	            result[i] = maxDigit;
	            pairs = nextPairs;
	        }
	        return result;
	    }
	}
	
	
	static class SolutionII extends CreateMaximumNumber {
		public int[] maxNumber(int[] nums1, int[] nums2, int k) {
	        return dp(nums1, 0, nums2, 0, k);
	    }
	    
	    private Map<List<Integer>, int[]> cache = new HashMap<>();
	    // guarantee k > 0; guarnatee i & j are valid
	    private int[] dp(int[] nums1, int i, int[] nums2, int j, int k) {
	        List<Integer> key = new ArrayList<>();
	        key.add(i);
	        key.add(j);
	        key.add(k);
	        int[] result = cache.get(key);
	        if (result != null)
	            return result;
	        result = new int[k];
	        int maxDigit = 0;
	        List<int[]> maxMoves = new ArrayList<>();
	        maxDigit = maxDigit(nums1, i, nums2, j, k, maxDigit, maxMoves, true);
	        maxDigit = maxDigit(nums2, j, nums1, i, k, maxDigit, maxMoves, false);
	        result[0] = maxDigit;
	        if (k > 1) {
	            int[] maxSub = new int[k-1];
	            for (int[] move : maxMoves) {
	                int[] sub = dp(nums1, i+move[0], nums2, j+move[1], k-1);
	                if (bigger(sub, maxSub))
	                    maxSub = sub;
	            }
	            for (int x = 1; x < result.length; x++)
	                result[x] = maxSub[x-1];
	        }
	        
	        cache.put(key, result);
	        return result;
	    }
	    
	    private int maxDigit(int[] nums1, int i, int[] nums2, int j, int k, int maxDigit, List<int[]> maxMoves, boolean turn) {
	        int d = 0;
	        while (i+d < nums1.length && nums1.length-(i+d) + nums2.length-j >= k) {
	            int digit = nums1[i+d];
	            if (digit > maxDigit) {
	                maxDigit = digit;
	                maxMoves.clear();
	                if (turn)
	                    maxMoves.add(new int[]{d+1, 0});
	                else
	                    maxMoves.add(new int[]{0, d+1});
	            } else if (digit == maxDigit) {
	                if (turn)
	                    maxMoves.add(new int[]{d+1, 0});
	                else
	                    maxMoves.add(new int[]{0, d+1});
	            }
	            d++;
	        }
	        return maxDigit;
	    }
	    
	    private boolean bigger(int[] a1, int[] a2) {
	        for (int i = 0; i < a1.length; i++) {
	            if (a1[i] > a2[i])
	                return true;
	            else if (a1[i] < a2[i])
	                return false;
	        }
	        return false;
	    }
	}
	
	
	// Solution I: Logic Error
	static class SolutionI extends CreateMaximumNumber {
		// nums1.length-i+nums2.length-i >= k
	    // i+j <= nums1.length + nums2.length - k 
	    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
	        int maxSum = nums1.length + nums2.length - k;
	        return help(nums1, 0, nums2, 0, k, maxSum);
	    }

	    private Map<List<Integer>, int[]> cache = new HashMap<>();    
	    private int[] help(int[] nums1, int i, int[] nums2, int j, int k, int maxSum) {
	        List<Integer> key = new ArrayList<>(3);
	        key.add(i);
	        key.add(j);
	        key.add(k);
	        int[] result = cache.get(key);
	        if (result != null)
	            return result;
	        result = new int[k];
	        int maxDigit = Integer.MIN_VALUE;
	        List<List<Integer>> maxRanges = new ArrayList<>();
	        for (int x = i; x < nums1.length && x + j <= maxSum; x++) {
	            if (nums1[x] > maxDigit) {
	                maxDigit = nums1[x];
	                maxRanges.clear();
	                addRange(maxRanges, x+1, j);
	            } else if (nums1[x] == maxDigit) {
	                addRange(maxRanges, x+1, j);
	            }
	        }
	        for (int y = j; y < nums2.length && i + y <= maxSum; y++) {
	            if (nums2[y] > maxDigit) {
	                maxDigit = nums2[y];
	                maxRanges.clear();
	                addRange(maxRanges, i, y+1);
	            } else if (nums2[y] == maxDigit) {
	                addRange(maxRanges, i, y+1);
	            }
	        }
	        result[0] = maxDigit;
	        // termination & recursion
	        if (k > 1) {
	            int[] maxSub = new int[k-1];
	            Arrays.fill(maxSub, Integer.MIN_VALUE);
	            for (List<Integer> maxRange : maxRanges) {
	            	if (maxRange.get(0) + maxRange.get(1) <= maxSum)
	            		maxSub = max(maxSub, help(nums1, maxRange.get(0), nums2, maxRange.get(1), k-1, maxSum));
	            }
	            for (int x = 1; x < k; x++)
	                result[x] = maxSub[x-1];
	        }
	        cache.put(key, result);
	        return result;
	    }
	    
	    private void addRange(List<List<Integer>> ranges, int i, int j) {
	        List<Integer> range = new ArrayList<>(2);
	        range.add(i);
	        range.add(j);
	        ranges.add(range);
	    }
	    
	    private int[] max(int[] array1, int[] array2) {
	        for (int i = 0; i < array1.length; i++) {
	            if (array1[i] > array2[i])
	                return array1;
	            else if (array1[i] < array2[i])
	                return array2;
	        }
	        return array1;  // equal
	    }
	}
}
