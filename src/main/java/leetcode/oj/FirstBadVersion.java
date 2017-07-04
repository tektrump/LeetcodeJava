package leetcode.oj;


public class FirstBadVersion {
	
	public int firstBadVersion(int n) {
        int i = 0, j = n;
        while (i <= j) {
            int m = i + (j - i)/2;
            if (isBadVersion(m)) {
                if (m == 0 || !isBadVersion(m-1))
                    return m;
                else
                    j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return -1;
    }
	
	public int badVersion;
	private boolean isBadVersion(int n) {
		return n >= badVersion;
	}

	public static void main(String[] args) {
		FirstBadVersion instance = new FirstBadVersion();
		int n;
		
		 n = 2126753390; instance.badVersion = 1702766719;
		
//		n = 1000; instance.badVersion = 100;
		
		int result = instance.firstBadVersion(n);
		System.out.println("result=" + result);
	}
}
