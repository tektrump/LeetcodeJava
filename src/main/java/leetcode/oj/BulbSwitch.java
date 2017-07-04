package leetcode.oj;


public class BulbSwitch {
	public int bulbSwitch(int n) {
		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;
        int result = 0;
        for (int i = 1; i < n; i++) {
            int toggle = 0;
            for (int j = 1; j <= n; j++)
                if (i % j == 0)
                    toggle++;
            if (toggle % 2 == 1)
                result++;
        }
        return result;
    }
	
	public static void main(String[] args) {
		BulbSwitch instance = new BulbSwitch();
		int n;
		
		n = 3; // 1
		
		n = 99999;
		
		long t1 = System.currentTimeMillis();
		int result = instance.bulbSwitch(n);
		long t2 = System.currentTimeMillis();
		System.out.println("result=" + result);
		System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
}
