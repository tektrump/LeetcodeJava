package leetcode.oj;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LargestNumber {

	public String largestNumber(int[] nums) {
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                String s1 = i1.toString() + i2.toString();
                String s2 = i2.toString() + i1.toString();
                return s1.compareTo(s2);
            }
        };
        List<Integer> list = new ArrayList<>();
        for (int num : nums)
        	list.add(num);
        Collections.sort(list, comp);
        StringBuilder builder = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            builder.append(list.get(i));
        }
        return builder.toString();
    }
	
	public static void main(String[] args) {
		LargestNumber instance = new LargestNumber();
		int[] nums;
		
//		nums = new int[]{3, 30, 34, 5, 9};
		nums = new int[]{999999998,999999997,999999999};
		
		String result = instance.largestNumber(nums);
		System.out.println("result=" + result);
	}
	
}
