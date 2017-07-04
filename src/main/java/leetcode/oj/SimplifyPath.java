package leetcode.oj;


import java.util.Stack;

public abstract class SimplifyPath {
	public abstract String simplifyPath(String path);
	public static void main(String[] args) {
		SimplifyPath instance = new SolutionI();
		String path;
		String result;
		
		path = "/...";
		
		result = instance.simplifyPath(path);
		System.out.println("result=" + result);
	}
	
	static class SolutionI extends SimplifyPath {
		public String simplifyPath(String path) {
	        Stack<String> stack = new Stack<>();
	        String[] splits = path.split("/");
	        for (int i = 0; i < splits.length; i++) {
	        	if (splits[i].isEmpty())
	        		continue;
	            if (splits[i].equals("."))
	                continue;
	            else if (splits[i].equals("..")) {
	                if (!stack.isEmpty())
	                    stack.pop();
	            } else
	                stack.push(splits[i]);
	        }
	        if (stack.isEmpty())
	            return "/";
	        StringBuilder builder = new StringBuilder();
	        while (!stack.isEmpty()) {
	            builder.insert(0, stack.pop());
	            builder.insert(0, "/");
	        }
	        return builder.toString();
	    }
	}
}
