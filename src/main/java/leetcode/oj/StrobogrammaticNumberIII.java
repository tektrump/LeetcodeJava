package leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class StrobogrammaticNumberIII {
	public abstract int strobogrammaticInRange(String low, String high);
	public static void main(String[] args) {
		StrobogrammaticNumberIII instance = new SolutionII();
		String low, high;
		int result;
		long t1, t2;
		
		// 3
//		low = "50"; high = "100";
		
		// 125
//		low = "10000001"; high = "20000000";
		
		// 1
//		low = "0"; high = "0";
		
		// 21
		low = "0"; high = "1680";
		
		
		t1 = System.currentTimeMillis();
		result = instance.strobogrammaticInRange(low, high);
		t2 = System.currentTimeMillis();
		System.out.println(String.format("result=%d, total time=%,dms", result, (t2 - t1)));
	}
	
	
	static class SolutionIII extends StrobogrammaticNumberIII {
		private static final int[][] pairs = {{1, 1}, {8, 8}, {6, 9}, {9, 6}};
	    public int strobogrammaticInRange(String low, String high) {
	        if (compare(low, high) > 0)
	            return 0;
	        
	        int len1 = low.length(), len2 = high.length();
	        List<List<StringBuilder>> lists = new ArrayList<>(len2+2);
	        List<StringBuilder> list0 = Arrays.asList(new StringBuilder());
	        List<StringBuilder> list1 = new ArrayList<>();
	        list1.add(new StringBuilder("0"));
	        list1.add(new StringBuilder("1"));
	        list1.add(new StringBuilder("8"));
	        lists.add(list0);
	        lists.add(list1);
	        for (int len = 2; len <= len2; len++) {
	            List<StringBuilder> curr = new LinkedList<>();
	            List<StringBuilder> prev = lists.get(len-2);
	            for (StringBuilder prevB : prev) {
	                for (int[] pair : pairs) {
	                    StringBuilder b = new StringBuilder(prevB.length()+2);
	                    b.append(pair[0]).append(prevB).append(pair[1]);
	                    curr.add(b);
	                }
	            }
	            lists.add(curr);
	        }
System.out.println(lists);
	        
	        int count = 0;
	        if (len1 == len2) {
	            for (StringBuilder b : lists.get(len1)) {
	                if (compare(b.toString(), low) >= 0 && compare(b.toString(), high) <= 0)
	                    count++;
	            }
	        } else {
	            for (int len = len1+1; len <= len2-1; len++)
	                count += lists.get(len).size();
	            for (StringBuilder b : lists.get(len1)) {
	                if (compare(b.toString(), low) >= 0)
	                    count++;
	            }
	            for (StringBuilder b : lists.get(len2)) {
	                if (compare(b.toString(), high) <= 0)
	                    count++;
	            }
	        }
	        
	        return count;
	    }
	    
	    private static int compare(String b, String s) {
	        if (b.length() != s.length())
	            return b.length() - s.length();
	        for (int i = 0; i < b.length(); i++) {
	            char ch1 = b.charAt(i), ch2 = s.charAt(i);
	            if (ch1 != ch2)
	                return ch1 - ch2;
	        }
	        return 0;
	    }
	}
	
	
	static class SolutionII extends StrobogrammaticNumberIII {
		private char[] symmetrics = {'0', '1', '8'};
	    private char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
	    public int strobogrammaticInRange(String low, String high) {
	        List<String> nums = new ArrayList<>();
	        for (int len = low.length(); len <= high.length(); len++) {
	            nums.addAll(getStros(len));
	        }
	        int count = 0;
	        for (String num : nums) {
	            if (between(num, low, high)) {
System.out.println(num);
	                count++;
	            }
	        }
	        return count;
	    }
	    
	    private List<String> getStros(int len) {
	        List<String> results = new ArrayList<>();
	        char[] chars = new char[len];
	        buildStro(chars, 0, len, results);
	        return results;
	    }
	    
	    private void buildStro(char[] chars, int idx, int len, List<String> results) {
	        // termination
	        if (idx == len/2) {
	            if (len  % 2 == 0) {
	                results.add(new String(chars));
	            } else {
	                for (char ch : symmetrics) {
	                    chars[idx] = ch;
	                    results.add(new String(chars));
	                }
	            }
	        } else { // recursion
	            for (char[] pair : pairs) {
	                if (idx == 0 && pair[0] == '0')
	                    continue;
	                chars[idx] = pair[0];
	                chars[len-1-idx] = pair[1];
	                buildStro(chars, idx+1, len, results);
	            }
	        }
	    }
	    
	    private boolean between(String num, String low, String high) {
	        return compare(num, low) >= 0 && compare(num, high) <= 0;
	    }
	    
	    private int compare(String num1, String num2) {
	        return num1.length() != num2.length() ? num1.length() - num2.length() : num1.compareTo(num2);
	    }
	}
	
	
	// Solution I: TLE
	static class SolutionI extends StrobogrammaticNumberIII {
		public int strobogrammaticInRange(String low, String high) {
	        int count = 0;
	        for (int num = Integer.valueOf(low); num <= Integer.valueOf(high); num++) {
	            if (isStro(num))
	                count++;
	        }
	        return count;
	    }
	    
	    private boolean isStro(int num) {
	        if (num == 0)
	            return true;
	        List<Integer> digits = new ArrayList<>();
	        while (num > 0) {
	            digits.add(num % 10);
	            num /= 10;
	        }
	        if (digits.size() % 2 == 1) {
	            if (!isSymmetric(digits.get(digits.size()/2)))
	                return false;
	        }
	        int l = 0, r = digits.size()-1;
	        while (l < r) {
	            if (!isPair(digits.get(l), digits.get(r)))
	                return false;
	            l++;
	            r--;
	        }
	        return true;
	    }
	    
	    private boolean isPair(int l, int r) {
	        return (l == r && isSymmetric(l)) || (l == 6 && r == 9) || (l == 9 && r == 6);
	    }
	    
	    private boolean isSymmetric(int n) {
	        return n == 0 || n == 1 || n == 8;
	    }
	}
}
