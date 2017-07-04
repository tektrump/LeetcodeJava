package leetcode.oj;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public abstract class VerifyPreorderSerializationOfABinaryTree {
	public abstract boolean isValidSerialization(String preorder);
	public static void main(String[] args) {
		VerifyPreorderSerializationOfABinaryTree instance = new SolutionII();
		String preorder;
		
		preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
		
		// true
//		preorder = "9,#,#";
		
		long t1 = System.currentTimeMillis();
		boolean result = instance.isValidSerialization(preorder);
		long t2 = System.currentTimeMillis();
		
		System.out.println("result=" + result);
		System.out.println(String.format("total time=%,dms", (t2 - t1)));
		
		String s = "";
		s = s + 'a';
	}
	
	
	static class SolutionII extends VerifyPreorderSerializationOfABinaryTree {
		public boolean isValidSerialization(String preorder) {
	        // for each height, a list of counts: each count corresponds to a node, val is # of children to be found
	        Map<Integer, List<Integer>> hCounts = new HashMap<>();
	        int h = 0;
	        int numNodes = 1, nextNumNodes = 0;
	        String[] splits = preorder.split(",");
	        for (int i = 0; i < splits.length; i++) {
	            String s = splits[i];
	            numNodes--;
	            if (s.equals("#")) {
	                update(h, hCounts);
	            } else {
	                List<Integer> counts = hCounts.get(h);
	                if (counts == null) {
	                    counts = new LinkedList<>();
	                    hCounts.put(h, counts);
	                }
	                counts.add(2);
	                nextNumNodes += 2;
	            }
	            if (numNodes == 0) {
	                numNodes = nextNumNodes;
	                nextNumNodes = 0;
	                h++;
	            }
	        }
	        return hCounts.isEmpty();
	    }
	    
	    private boolean update(int childH, Map<Integer, List<Integer>> hCounts) {
	    	if (childH == 0)
	    		return true;
	        int parentH = childH - 1;
	        List<Integer> counts = hCounts.get(parentH);
	        if (counts == null)
	            return false;
	        if (counts.get(0) == 2)
	            counts.set(0, 1);
	        else {
	            counts.remove(0); // found both children for parent, then update grandparent, recursively
	            if (!update(parentH, hCounts))
	                return false;
	        }
	        if (counts.isEmpty())
	            hCounts.remove(parentH);
	        return true;
	    }
	}
	
	
	// Solution I:
	static class SolutionI extends VerifyPreorderSerializationOfABinaryTree {
		public boolean isValidSerialization(String preorder) {
	        // edge case
	        if (preorder.length() == 0)
	            return true;
	        
	        String[] splits = preorder.split(",");
	        Stack<String> nodes = new Stack<>();
	        Stack<Integer> flags = new Stack<>();
	        // flags: all initialized to 0
	        // pop node and flag;
	        // if flag is 0 or 1, continue to push new element from array to nodes
	        // (if reaching end of array, return false).
	        // if flag is 2 or node is "#", drop curr node and pop next node from nodes/ flags.
	        // (if stack is empty, break the loop; return true if we also reached end of array; otherwise false).
	        nodes.push(splits[0]);
	        flags.push(0);
	        int index = 0;
	        while (!nodes.empty()) {
	            String node = nodes.pop();
	            Integer flag = flags.pop();
	            // if curr node is null or we have already found its left & right subtrees, 
	            // move on to check its parent (pop at next loop).
	            if (node.equals("#") || flag == 2) {
	                continue;
	            } else { // if 0 or 1, check its left or right subtree (pushing next split onto stack).
	                // keep the node but bump its flag
	                nodes.push(node);
	                flags.push(flag + 1);
	                // expect more elements on array as curr node should have subtree
	                if (index == splits.length - 1)
	                    return false;
	                else {
	                    nodes.push(splits[++index]);
	                    flags.push(0);
	                }
	            }
	        }
	        
	        // if index is the last element, that means we have checked ALL the nodes;
	        // otherwise, we have unexpected redundant node, which is invalid.
	        return index == splits.length - 1;
	    }
	}
	
}
