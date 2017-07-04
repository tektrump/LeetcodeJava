package leetcode.oj;


import leetcode.util.annotations.Leetcode;

/**
 * Note very useful. Just a small trick.
 * @author Alex
 * @date May 4, 2016
 */
@Leetcode(date="2016/05/04", url="https://leetcode.com/problems/add-digits/")
public class AddDigits {
	// Solution II: Accepted
    // second version by improving from solution I.
    public int addDigits(int num) {
        return (num - 1)%9 + 1;
    }
    
    // Solution I: Accepted
    // first version directly from observation
    /*
    public int addDigits(int num) {
        if (num == 0)
            return 0;
        int res = num % 9;
        return res == 0 ? 9 : res;
    }
    */
    
    public static void main(String[] args) {
    	AddDigits instance = new AddDigits();
    	int num;
    	
    	// 2
    	num = 38;
    	
    	int result = instance.addDigits(num);
    	System.out.println("result=" + result);
	}

}
