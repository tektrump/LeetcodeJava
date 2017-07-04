package leetcode.oj;


public abstract class LongestSubstringWithAtMostKDistinctCharacters {
	public abstract int lengthOfLongestSubstringKDistinct(String s, int k);
	public static void main(String[] args) {
		LongestSubstringWithAtMostKDistinctCharacters instance = new SolutionI();
		String s; int k;
		int result;
		
		s = "eceba";
		k = 2;
		result = instance.lengthOfLongestSubstringKDistinct(s, k);
		System.out.println("result=" + result);
	}
	
	static class SolutionI extends LongestSubstringWithAtMostKDistinctCharacters {
		public int lengthOfLongestSubstringKDistinct(String s, int k) {
	        if (k == 0) return 0;
	        int[] counts = new int[256];
	        int l = 0, num = 0, max = 0;
	        for (int r = 0; r < s.length(); r++) {
	            if (counts[s.charAt(r)]++ == 0) num++;
	            while (num > k) {
	                char lchar = s.charAt(l++);
	                if (counts[lchar] > 0)
	                    if (--counts[lchar] == 0) {
	                    	num--;
	                    	break;
	                    }
	            }
System.out.println("num=" + num + ", l=" + l + ", r=" + r);
	            if (num == k) max = Math.max(max, r - l + 1);
	        }
	        return max;
	    }
	}
}
