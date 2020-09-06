package tektrup.leetcode.util;

public class MathUtils {
	
	public static String toBinary(int num) {
		char[] chars = new char[32];
		for (int i = 0; i < 32; i++) {
			chars[i] = (char)('0' + ((num >>> (31-i)) & 1));
		}
		return new String(chars);
	}
	
	
	public static void main(String[] args) {
		System.out.println(toBinary(Integer.MAX_VALUE));
		System.out.println(toBinary(Integer.MIN_VALUE));
	}

}
