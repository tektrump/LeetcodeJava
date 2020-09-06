package tektrup.leetcode.oj;


import java.util.Arrays;

public class CompareVersionNumbers {
	
	public int compareVersion(String version1, String version2) {
        String[] splits1 = version1.split("\\.");
System.out.println(Arrays.toString(splits1));
        if (splits1.length == 0) {
            splits1 = new String[]{version1};
        }
        String[] splits2 = version2.split("\\.");
        if (splits2.length == 0) {
            splits2 = new String[]{version2};
        }
        int i = 0, j = 0;
        while (i < splits1.length && j < splits2.length) {
            int i1 = Integer.valueOf(splits1[i]);
            int i2 = Integer.valueOf(splits2[i]);
            if (i1 < i2)
                return -1;
            else if (i1 > i2)
                return 1;
            i++;
            j++;
        }
        if (i < splits1.length)
            return 1;
        else if (j < splits2.length)
            return -1;
        else
            return 0;
    }
	
	public static void main(String[] args) {
		CompareVersionNumbers instance = new CompareVersionNumbers();
		
		String version1, version2;
		
		version1 = "1.0"; version2 = "1.1";

		int result = instance.compareVersion(version1, version2);
		System.out.println("result=" + result);
	}

}
