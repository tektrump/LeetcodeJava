package leetcode.oj;


import java.util.HashMap;
import java.util.Map;

public abstract class IntegerToRoman {
	public abstract String intToRoman(int num);
	public static void main(String[] args) {
		IntegerToRoman instance = new SolutionI();
		int num;
		String result;
		
//		num = 13; // XIII
		
		num = 1400;
		
		result = instance.intToRoman(num);
		System.out.println("result=" + result);
	}
	
	static class SolutionI extends IntegerToRoman {
		private static final Map<Integer, Character> intChar = new HashMap<>();
	    static {
	        intChar.put(1, 'I');
	        intChar.put(5, 'V');
	        intChar.put(10, 'X');
	        intChar.put(50, 'L');
	        intChar.put(100, 'C');
	        intChar.put(500, 'D');
	        intChar.put(1000, 'M');
	    }
	    private static final Map<Character, Character> charFiller = new HashMap<>();
	    static {
	        charFiller.put('V', 'I');
	        charFiller.put('X', 'I');
	        charFiller.put('L', 'X');
	        charFiller.put('C', 'X');
	        charFiller.put('D', 'C');
	        charFiller.put('M', 'C');
	    }
	    public String intToRoman(int num) {
	        StringBuilder builder = new StringBuilder();
	        int base = 1000;
	        while (base > 1) {
	        	if (base < 1000) {
		            if (num > base*5) {
		                add(num, base*5, base*10, base/10, builder);
		                num -= base*5;
		            }
	        	}
	        	int fill = base*5;
	        	if (base == 1000)
	        		fill = 100;
	            if (num > base) {
	                add(num, base, fill, base/10, builder);
	                num -= base*(num/base);
	            }
	            base /= 10;
	        }
	        return builder.toString();
	    }
	    
	    private void add(int num, int base, int prev, int fill, StringBuilder builder) {
	    	if (num == 0)
	    		return;
	        int q = (num%base)/fill;
	        if (q == 0) {
	            builder.append(intChar.get(base));
	        } else if (q <= 3) {
	        	builder.append(intChar.get(base));
	            while (q > 0) {
	                builder.append(intChar.get(fill));
	                q--;
	            }
	        } else {
	            char prevFillChar = charFiller.get(intChar.get(prev));
	            builder.append(prevFillChar).append(intChar.get(prev));
	        }
System.out.println("num=" + num + ", builder=" + builder);
	    }
	}
}
