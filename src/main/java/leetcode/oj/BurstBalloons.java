package leetcode.oj;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import leetcode.util.ArrayUtil;

public abstract class BurstBalloons {
	public abstract int maxCoins(int[] nums);
    public static void main(String[] args) {
    	BurstBalloons instance = new SolutionV();
    	int[] nums;
    	
    	// 167
    	nums = ArrayUtil.str2intArray("[3, 1, 5, 8]");
    	
    	// 9
//    	nums = ArrayUtil.str2intArray("[3, 2]");
    	
//    	nums = new int[]{7,9,8,0,7,1,3,5,5,2,3,3};
    	
//    	nums = new int[]{8,2,6,8,9,8,1,4,1,5,3,0,7,7,0,4,2,2,5};
    	
    	// 32
//    	nums = ArrayUtil.str2intArray("[8, 3]");
    	
//    	nums = ArrayUtil.str2intArray("[8,2,6,8,9,8,1,4,1,5,3,0,7,7,0,4,2,2]");
    	
//    	nums = ArrayUtil.str2intArray("[1,6,8,3,4,6,4,7,9,8,0,6,2,8]");
    	
    	long t1 = System.currentTimeMillis();
    	int result = instance.maxCoins(nums);
    	long t2 = System.currentTimeMillis();
    	System.out.println("result=" + result);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
    
    
    static class SolutionV extends BurstBalloons {
    	public int maxCoins(int[] nums) {
            if (nums == null || nums.length == 0)
                return 0;
            int[] array = new int[nums.length+2];
            for (int i = 0; i < nums.length; i++)
                array[1+i] = nums[i];
            array[0] = 1;
            array[array.length-1] = 1;
            int[][] dp = new int[array.length][array.length];
            for (int[] row : dp)
                Arrays.fill(row, -1);
            return dp(array, 1, array.length-2, dp);
        }
        
        private int dp(int[] array, int l, int r, int[][] dp) {
            if (dp[l][r] >= 0)
                return dp[l][r];
            if (l > r)
            	return 0;
            int max = 0;
            for (int m = l; m <= r; m++)
                max = Math.max(max, dp(array, l, m-1, dp) + dp(array, m+1, r, dp) + array[m]*array[l-1]*array[r+1]);
            dp[l][r] = max;
            return max;
        }
    }
    
    
    static class SolutionIV extends BurstBalloons {
    	public class DNode {
            DNode prev, next;
            int val;
            public DNode(int val) {
                this.val = val;
            }
            
            public void append(DNode node) {
                DNode next = this.next;
                this.next = node;
                node.prev = this;
                node.next = next;
                next.prev = node;
            }
            
            public void remove() {
                this.prev.next = this.next;
                this.next.prev = this.prev;
            }
        }
        
        public int maxCoins(int[] nums) {
            DNode dd = new DNode(1), n = dd;
            dd.next = dd;
            dd.prev = dd;
            for (int num : nums) {
                n.append(new DNode(num));
                n = n.next;
            }
            return bt(dd);
        }
        
        private Map<List<Integer>, Integer> cache = new HashMap<>();
        private int bt(DNode dd) {
            List<Integer> key = new ArrayList<>();
            DNode curr = dd.next;
            while (curr != dd) {
                key.add(curr.val);
                curr = curr.next;
            }
            Integer max = cache.get(key);
            if (max != null)
                return max;
            max = 0;
            
            curr = dd.next;
            while (curr != dd) {
                curr.remove(); // modify
                int val = curr.val*curr.prev.val*curr.next.val + bt(dd); // recursion
                max = Math.max(max, val);
                curr.prev.append(curr); // restore
                curr = curr.next;
            }
            cache.put(key, max);
            return max;
        }
    }
}
