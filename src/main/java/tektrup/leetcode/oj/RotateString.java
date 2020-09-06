package tektrup.leetcode.oj;


import java.util.Arrays;

public class RotateString {
	
	public void rotateString(char[] str, int offset) {
		
	}
	
	// Solution I: Logic Error
	/*
	public void rotateString(char[] str, int offset) {
		if (offset == 0 || str.length == 0 || offset % str.length == 0)
            return;
        offset %= str.length;
        char curr = str[0], next;
        int count = 0;
        int i = 0;
        while (count++ < str.length) {
            int j = (i + offset) % str.length;
            next = str[j];
            str[j] = curr;
            curr = next;
            i = j;
        }
    }
    */
	
	public static void main(String[] args) {
		RotateString instance = new RotateString();
		char[] str;
		int offset;
		
		// efgabcd
//		str = "abcdefg".toCharArray();
//		offset = 3;
		
		// cbaabc
//		str = "abccba".toCharArray();
//		offset = 3;
		
		// cdeab
		str = "abcde".toCharArray();
		offset = 3;
		
		instance.rotateString(str, offset);
		System.out.println(Arrays.toString(str));
	}

}
