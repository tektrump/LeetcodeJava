package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DifferentWaysToAddParentheses {
	private final Map<String, List<Integer>> cache = new HashMap<>();
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> results = cache.get(input);
        if (results != null)
            return results;
        results = new ArrayList<>();
        if (input.length() == 1)
            results.add(Integer.valueOf(input));
        else {
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i < input.length(); i += 2) {
                List<Integer> leftVals = diffWaysToCompute(input.substring(0, i));
                char sign = input.charAt(i);
                List<Integer> rightVals = diffWaysToCompute(input.substring(i+1, input.length()));
                for (int leftVal : leftVals) {
                    for (int rightVal : rightVals) {
                        switch (sign) {
                            case '+':
                                set.add(leftVal + rightVal);
                                break;
                            case '-':
                                set.add(leftVal - rightVal);
                                break;
                            default:
                                set.add(leftVal * rightVal);
                                break;
                        }
                    }
                }
            }
            for (Integer v : set)
                results.add(v);
        }
        cache.put(input, results);
        return results;
    }
    
    public static void main(String[] args) {
		
	}
}
