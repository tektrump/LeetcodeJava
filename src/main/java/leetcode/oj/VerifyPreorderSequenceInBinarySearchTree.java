package leetcode.oj;


public abstract class VerifyPreorderSequenceInBinarySearchTree {
	public abstract boolean verifyPreorder(int[] preorder);
	public static void main(String[] args) {
		VerifyPreorderSequenceInBinarySearchTree instance = new SolutionIII();
		int[] preorder;
		boolean res;
		
		preorder = new int[]{2, 1};
		
		res = instance.verifyPreorder(preorder);
		System.out.println("result=" + res);
	}
	
	
	static class SolutionIII extends VerifyPreorderSequenceInBinarySearchTree {
		public boolean verifyPreorder(int[] preorder) {
	        sort(preorder, 0, preorder.length-1);
	        // confirm 'preorder' is in sorted order
	        for (int i = 1; i < preorder.length; i++) {
	            if (preorder[i] < preorder[i-1])
	                return false;
	        }
	        return true;
	    }
	    
	    private void sort(int[] pre, int l, int r) {
	        if (l >= r)
	            return;
	            
	        int root = pre[l];
	        int m = l, i = l + 1; // m points to last num <= root
	        while (i <= r && pre[i] < root) {
	            m = i;
	            i++;
	        }
	        swap(pre, l, m);
	        sort(pre, l, m-1);
	        sort(pre, m+1, r);
	    }
	    
	    private void swap(int[] nums, int l, int r) {
	        int temp = nums[l];
	        nums[l] = nums[r];
	        nums[r] = temp;
	    }
	}
	
	
	static class SolutionII extends VerifyPreorderSequenceInBinarySearchTree {
		public boolean verifyPreorder(int[] preorder) {
	        return verify(preorder, 0, preorder.length-1);
	    }
	    
	    private boolean verify(int[] preorder, int l, int r) {
	        if (l >= r)
	            return true;
	        int root = preorder[l];
	        int m = -1;
	        for (int i = l+1; i <= r; i++) {
	            if (preorder[i] > root) {
	                if (m < 0)
	                    m = i;
	            } else { // num <= root
	                if (m >= 0)
	                    return false;
	            }
	        }
	        if (m < 0)
	        	m = r+1;
	        
	        return verify(preorder, l+1, m-1) && verify(preorder, m, r);
	    }
	}
	
	static class SolutionI extends VerifyPreorderSequenceInBinarySearchTree {
		public boolean verifyPreorder(int[] preorder) {
	        return verify(preorder, 0, preorder.length-1);
	    }
	    
	    private boolean verify(int[] preorder, int l, int r) {
	        if (l >= r)
	            return true;
	        int root = preorder[l];
	        int m = -1;
	        for (int i = l+1; i <= r; i++) {
	            if (preorder[i] > root) {
	                if (m < 0)
	                    m = i;
	            } else { // num <= root
	                if (m >= 0)
	                    return false;
	            }
	        }
	        if (m < 0)
	        	m = r+1;
	        
	        return verify(preorder, l+1, m-1) && verify(preorder, m, r);
	    }
	}
}
