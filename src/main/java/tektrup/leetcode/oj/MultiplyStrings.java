package tektrup.leetcode.oj;


import java.util.HashMap;
import java.util.Map;

public abstract class MultiplyStrings {
    public abstract String multiply(String num1, String num2);
    public static void main(String[] args) {
    	MultiplyStrings instance = new SolutionI();
    	String num1, num2;
    	String res;
    	
//    	num1 = "2"; num2 = "3";
    	
    	num1 = "1"; num2 = "21";
    	
    	res = instance.multiply(num1, num2);
    	System.out.println("result=" + res);
	}
    
    
    static class SolutionI extends MultiplyStrings {
    	public String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0"))
                return "0";
            int negs = 0;
            if (num1.charAt(0) == '-') {
                num1 = num1.substring(1, num1.length());
                negs++;
            }
            if (num2.charAt(0) == '-') {
                num2 = num2.substring(1, num2.length());
                negs++;
            }
            String ret = dp(num1, num2);
            if (negs == 1)
                return "-" + ret;
            else
                return ret;
        }
        
        private Map<String, String> cache = new HashMap<>();
        private String dp(String num1, String num2) {
            String ret = cache.get(num2);
            if (ret != null)
                return ret;
            
            if (num2.equals("1")) { // termination
                return num1;
            } else {
                int lastDigit = num2.charAt(num2.length()-1) - '0';
if (num2.equals("00"))
	System.out.println();
                String sub = dp(num1, half(num2));
                ret = add(sub, sub);
                if (lastDigit % 2 == 1)
                    ret = add(ret, num1);
            }
            
            cache.put(num2, ret);
            return ret;
        }
        
        private String half(String num) {
            StringBuilder b = new StringBuilder();
            int r = 0;
            for (int i = 0; i < num.length(); i++) {
                int n = r*10 + (num.charAt(i) - '0');
                b.append(n/2);
                r = n%2;
            }
System.out.println("half of " + num + "=" + b);           
            while (b.charAt(0) == '0')
            	b.deleteCharAt(0);
            return b.toString();
        }
        
        private String add(String num1, String num2) {
            StringBuilder b = new StringBuilder();
            int i1 = num1.length()-1, i2 = num2.length()-1;
            int c = 0;
            while (i1 >= 0 || i2 >= 0) {
                int d1 = 0, d2 = 0;
                if (i1 >= 0)
                    d1 = num1.charAt(i1--) - '0';
                if (i2 >= 0)
                    d2 = num2.charAt(i2--) - '0';
                int sum = d1 + d2 + c;
                b.insert(0, sum%10);
                c = sum/10;
            }
            if (c == 1)
                b.insert(0, '1');
                
            return b.toString();
        }
    }
}
