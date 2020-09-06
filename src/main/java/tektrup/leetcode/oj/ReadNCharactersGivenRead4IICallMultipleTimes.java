package tektrup.leetcode.oj;


import java.util.Arrays;

public class ReadNCharactersGivenRead4IICallMultipleTimes {
	public static void main(String[] args) {
		ReadNCharactersGivenRead4IICallMultipleTimes instance = new ReadNCharactersGivenRead4IICallMultipleTimes();
		instance.setString("ab");
		
		char[] buf = new char[4];
		instance.read(buf, 1);
		System.out.println(Arrays.toString(buf));
		instance.read(buf, 2);
		System.out.println(Arrays.toString(buf));
		
	}
	
	private String s;
	private int i = 0;
	public final void setString(String s) {
		this.s = s;
	}
	
	public final int read4(char[] buf) {
		buf = new char[4];
		int total = 0;
		for (int j = 0; j < 4; j++) {
			if (i < s.length()) {
				buf[j] = s.charAt(i++);
				total++;
			} else
				break;
		}
//System.out.println("...buf=" + Arrays.toString(buf));
		return total;
	}
	
	private char[] myBuf = new char[4];
    private int idx = 0, count = 0;
    public int read(char[] buf, int n) {
        int total = 0;
        while (idx < count && total < n)
            buf[total++] = myBuf[idx++];
        while (total < n) {
            count = read4(myBuf);
            idx = 0;
            if (count == 0)
                break;
            for (; idx < count && total < n; idx++)
                buf[total++] = myBuf[idx++];
        }
        
        return total;
    }
    
}
