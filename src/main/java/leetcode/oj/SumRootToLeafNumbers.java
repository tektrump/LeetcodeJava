package leetcode.oj;


import java.util.ArrayList;
import java.util.List;

import leetcode.util.TreeNode;

public class SumRootToLeafNumbers {

	public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        List<StringBuilder> nums = getNums(root);
        int sum = 0;
        for (StringBuilder num : nums)
            sum += Integer.valueOf(num.toString());
        return sum;
    }
    
    private List<StringBuilder> getNums(TreeNode node) {
        List<StringBuilder> results = new ArrayList<>();
        if (node.left == null && node.right == null) {
            StringBuilder builder = new StringBuilder();
            builder.append(node.val);
            results.add(builder);
            return results;
        }
        List<StringBuilder> subResults = new ArrayList<>();
        if (node.left != null)
            subResults.addAll(getNums(node.left));
        if (node.right != null)
            subResults.addAll(getNums(node.right));
        for (StringBuilder subResult : subResults) {
            StringBuilder builder = new StringBuilder();
            builder.append(node.val).append(subResult);
            results.add(builder);
        }
        return results;
    }
    
    public static void main(String[] args) {
    	SumRootToLeafNumbers instance = new SumRootToLeafNumbers();
    	TreeNode root;
    	
//    	root = TreeNode.deserialize("9"); // 9
    	
    	root = TreeNode.deserialize("5, 3, 2, 7, 0, 6, null, null, null, 0"); // 9
    	
    	int result = instance.sumNumbers(root);
    	System.out.println("result=" + result);
	}
    
}
