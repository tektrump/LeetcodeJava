package leetcode.oj;


import java.util.Arrays;

public class RangeSumQueryMutable {
	
	public static void main(String[] args) {
//		NumArray instance = new RangeSumQueryMutable(). new NumArray(new int[]{0,9,5,7,3});
//		System.out.println(instance.sumRange(4,4));
//		System.out.println(instance.sumRange(2,4));
//		System.out.println(instance.sumRange(3,3));
//		instance.update(4,5);
		

		NumArray instance = new RangeSumQueryMutable(). new NumArray(new int[]{-28,-39,53,65,11,-56,-65,-39,-43,97});
		System.out.println(instance.sumRange(5,6));
		instance.update(9,27);
		System.out.println(instance.sumRange(2,3));
		
		
//		instance.update(1,7);
//		instance.update(0,8);
//		instance.sumRange(1,2);
//		instance.update(1,9);
//		instance.sumRange(4,4);
//		instance.update(3,4);
		
		
//		NumArray instance = new RangeSumQueryMutable(). new NumArray(new int[]{1,3,5});
//		System.out.println(instance.sumRange(0, 2)); // 9
//		instance.update(1, 2);
//		System.out.println(instance.sumRange(0, 2)); // 8
		
		
//		NumArray instance = new RangeSumQueryMutable(). new NumArray(new int[]{9,-8});
//		instance.update(0, 3);
//		System.out.println(instance.sumRange(1, 1));
	}
	
	// Solution IV: Accepted
    // implement using array; very complex index relationship but improved performance.
	public class NumArray {
	    private int[] nums;
	    private int[] tree;
	    public NumArray(int[] nums) {
	        this.nums = nums;
	        if (nums.length > 0) {
	            int len = nums.length;
	            int shift = 0;
	            while (len > 1) {
	                len >>= 1;
	                shift++;
	            }
	            while (shift > 0) {
	                len <<= 1;
	                shift--;
	            }
	            if (len < nums.length)
	                len <<= 1;
	            tree = new int[2*len - 1];
	            build(tree, 0, nums, 0, nums.length-1);
	        }
	    }
	    
	    // NOTE: each node on tree has either 2 children or no children!
	    private void build(int[] tree, int idx, int[] nums, int l, int r) {
	        if (l > r)
	            return;
	        if (l == r)
	            tree[idx] = nums[l];
	        else {
	            int m = (l + r) >> 1;
	            build(tree, idx*2+1, nums, l, m);
	            build(tree, idx*2+2, nums, m+1, r);
	            tree[idx] = tree[idx*2+1] + tree[idx*2+2];   
	        }
	    }
	    
	    void update(int i, int val) {
	         updateTree(tree, 0, 0, nums.length-1, i, val);
	    }
	    
	    private void updateTree(int[] tree, int idx, int l, int r, int i, int val) {
	        if (i < l || i > r) // i is out or range
	            return;
	        tree[idx] += val - nums[i];
	        if (l == r)
	        	return;
	        int m = (l + r) >> 1;
	        updateTree(tree, idx*2+1, l, m, i, val);
	        updateTree(tree, idx*2+2, m+1, r, i, val);
	        nums[i] = val;
	    }
	    
	    public int sumRange(int i, int j) {
	        return sumTree(tree, 0, 0, nums.length-1, i, j);
	    }
	    
	    private int sumTree(int[] tree, int idx, int l, int r, int i, int j) {
	        if (i <= l && j >= r) // total overlap
	            return tree[idx];
	        else if (i > r || j < l) // no overlap
	            return 0;
	        else {
	            int m = (l + r) >> 1;
	            return sumTree(tree, 2*idx+1, l, m, i, j) + sumTree(tree, 2*idx+2, m+1, r, i, j);
	        }
	    }
	}
	
	
	
	// Solution III: Accepted
	// canonical Segment Tree implementation: left & right
	/*
	public class NumArray {
	    
	    public class SegTreeNode {
	        int sum, lidx, ridx;
	        SegTreeNode left, right;
	        SegTreeNode(int sum) {
	            this.sum = sum;
	        }
	    }

	    private int[] nums;    
	    private SegTreeNode root;
	    public NumArray(int[] nums) {
	        this.nums = nums;
	        this.root = build(nums, 0, nums.length-1);
	    }
	    
	    private SegTreeNode build(int[] nums, int l, int r) {
	        if (l > r)
	            return null;
	        if (l == r)
	            return new SegTreeNode(nums[l]);
	        int m = (l + r) >> 1;
	        SegTreeNode root = new SegTreeNode(0);
	        SegTreeNode left = build(nums, l, m);
	        root.left = left;
	        if (left != null)
	            root.sum += left.sum;
	        SegTreeNode right = build(nums, m+1, r);
	        root.right = right;
	        if (right != null)
	            root.sum += right.sum;
	        return root;
	    }
	    
	    void update(int i, int val) {
	        updateTree(i, val, root);
	    }
	    
	    private void updateTree(int i, int val, SegTreeNode node) {
	        if (node == null || i < node.lidx || i > node.ridx)
	            return;
	        node.sum += val - nums[i];
	        updateTree(i, val, node.left);
	        updateTree(i, val, node.right);
	        nums[i] = val; // NOTE: update array AFTER updating the tree
	    }
	    
	    public int sumRange(int i, int j) {
	        return sumTree(i, j, root);
	    }
	    
	    private int sumTree(int i, int j, SegTreeNode node) {
	        // no overlap
	        if (node == null || j < node.lidx || i > node.ridx)
	            return 0;   // this subtree is irrelevant
	        // complete overlap
	        else if (node.lidx >= i && node.ridx <= j)
	            return node.sum;
	        else // partial overlap
	            return sumTree(i, j, node.left) + sumTree(i, j, node.right);
	    }
	}
	}
	
	// Solution II: Accepted
	// Segmentation tree using "3 part" (left, mid, right); not recommended.
	/*
	class SegTreeNode {
        int lidx, ridx, sum;
        SegTreeNode left, right;
        public SegTreeNode(int lidx, int ridx) {
            this.lidx = lidx;
            this.ridx = ridx;
        }
    }
    
    private int[] nums;
    private SegTreeNode root;
    public NumArray(int[] nums) {
        this.nums = nums;
        root = build(nums, 0, nums.length-1);
    }
    
    private SegTreeNode build(int[] nums, int l, int r) {
        if (l > r)
            return null;
        int m = l + (r - l)/2;
        SegTreeNode root = new SegTreeNode(l, r);
        root.sum = nums[m];
        root.left = build(nums, l, m-1);
        root.right = build(nums, m+1, r); // put m into right sub
        if (root.left != null)
            root.sum += root.left.sum;
        if (root.right != null)
            root.sum += root.right.sum;
        return root;
    }
    
    // O(logn)
    void update(int i, int val) {
        updateTree(root, i, val);
        nums[i] = val;  // ERROR: don't foget to update array; and only update after updateTree,
                        // because updateTree relies on old value vs. new val to calculate delta.
    }
    
    private void updateTree(SegTreeNode root, int idx, int val) {
        root.sum += val - nums[idx];
        int m = (root.lidx + root.ridx)/2; 
        if (idx < m)
            updateTree(root.left, idx, val);
        else if (idx > m)
            updateTree(root.right, idx, val);
    }

    public int sumRange(int i, int j) {
        return getSum(root, i, j);
    }
    
    // O(logn)
    private int getSum(SegTreeNode root, int l, int r) {
        if (root.lidx == l && root.ridx == r)
            return root.sum;
        if (l > r) // ERROR: must check as m-1 and m+1 can introduce bad range
        	return 0;
        if (l == r)
        	return nums[l];
        int m = (root.lidx + root.ridx)/2;
        
        if (r < m)
            return getSum(root.left, l, r);
        else if (l > m)
            return getSum(root.right, l, r);
        else
	        return getSum(root.left, l, m-1) + getSum(root.right, m+1, r) + nums[m];
    }
	*/
	
	// Solution I: Accepted
	/*
	public class NumArray {
		class SegTreeNode {
	        int lidx, ridx, sum;
	        SegTreeNode left, right;
	        public SegTreeNode(int lidx, int ridx) {
	            this.lidx = lidx;
	            this.ridx = ridx;
	        }
	        
	        @Override
	        public String toString() {
	        	return String.format("[%d,%d] sum=%d", lidx, ridx, sum);
	        }
	    }
	    
	    private int[] nums;
	    private SegTreeNode root;
	    public NumArray(int[] nums) {
	        this.nums = nums;
	        root = build(nums, 0, nums.length-1);
	    }
	    
	    private SegTreeNode build(int[] nums, int l, int r) {
	        if (l > r)
	            return null;
	        int m = l + (r - l)/2;
	        SegTreeNode root = new SegTreeNode(l, r);
	        root.sum = nums[m];
	        root.left = build(nums, l, m-1);
	        root.right = build(nums, m+1, r); // put m into right sub
	        if (root.left != null)
	            root.sum += root.left.sum;
	        if (root.right != null)
	            root.sum += root.right.sum;
	        return root;
	    }
	    
	    // O(logn)
	    void update(int i, int val) {
	    	nums[i] = val;
	        updateTree(root, i, val);
	    }
	    
	    private void updateTree(SegTreeNode root, int idx, int val) {
	        root.sum += val - nums[idx];
	        int m = (root.lidx + root.ridx)/2; 
	        if (idx < m)
	            updateTree(root.left, idx, val);
	        else if (idx > m)
	            updateTree(root.right, idx, val);
	    }

	    public int sumRange(int i, int j) {
	        return getSum(root, i, j);
	    }
	    
	    private int getSum(SegTreeNode root, int l, int r) {
	        if (root.lidx == l && root.ridx == r)
	            return root.sum;
	        if (l > r)
	        	return 0;
	        if (l == r)
	        	return nums[l];
	        int m = (root.lidx + root.ridx)/2;
	        
	        if (r < m)
	            return getSum(root.left, l, r);
	        else if (l > m)
	            return getSum(root.right, l, r);
	        else {
	        	if (l == m)
		        	return getSum(root.right, l+1, r) + nums[m];
		        else if (r == m)
		        	return getSum(root.left, l, m-1) + nums[m];
		        else
		        	return getSum(root.left, l, m-1) + getSum(root.right, m+1, r) + nums[m];
	        }
	    }
	}
	*/
}
