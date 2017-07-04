package leetcode.oj;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegerToEnglishWords {
	private final static String[] units = {"", "Thousand", "Million", "Billion"};
    private final static Map<Integer, String> nWord = new HashMap<>();
    static {
        nWord.put(1, "One");
        nWord.put(2, "Two");
        nWord.put(3, "Three");
        nWord.put(4, "Four");
        nWord.put(5, "Five");
        nWord.put(6, "Six");
        nWord.put(7, "Seven");
        nWord.put(8, "Eight");
        nWord.put(9, "Nine");
        nWord.put(10, "Ten");
        nWord.put(11, "Eleven");
        nWord.put(12, "Twelve");
        nWord.put(13, "Thirteen");
        nWord.put(14, "Fourteen");
        nWord.put(15, "Fifteen");
        nWord.put(16, "Sixteen");
        nWord.put(17, "Seventeen");
        nWord.put(18, "Eighteen");
        nWord.put(19, "Nineteen");
        nWord.put(20, "Twenty");
        nWord.put(30, "Thirty");
        nWord.put(40, "Forty");
        nWord.put(50, "Fifty");
        nWord.put(60, "Sixty");
        nWord.put(70, "Seventy");
        nWord.put(80, "Eighty");
        nWord.put(90, "Ninety");
    }
    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";
        List<Integer> list = new ArrayList<>();
        while (num > 0) {
            list.add(num % 1000);
            num /= 1000;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = list.size()-1; i >= 0; i--) {
        	if (builder.length() > 0)
        		builder.append(" ");
            builder.append(getWord(list.get(i))).append(" ").append(units[i]);
        }
        while (builder.charAt(builder.length()-1) == ' ')
            builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }
    
    private StringBuilder getWord(int i) {
        StringBuilder builder = new StringBuilder();
        if (i >= 100)
            builder.append(nWord.get(i/100)).append(" Hundred");
        if (i % 100 != 0) {
            if (builder.length() > 0)
                builder.append(" ");
            String word = nWord.get(i % 100);
            if (word != null)
            	builder.append(word);
        }
        return builder;
    }
    
    public static void main(String[] args) {
    	IntegerToEnglishWords instance = new IntegerToEnglishWords();
    	
    	System.out.println("123: " + instance.numberToWords(123));
    	System.out.println("12345: " + instance.numberToWords(12345));
    	System.out.println("1234567: " + instance.numberToWords(1234567));
	}
}
