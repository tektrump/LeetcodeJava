package leetcode.oj;


public abstract class ExcelSheetColumnNumber {
	public abstract int titleToNumber(String s);
	public static void main(String[] args) {
		ExcelSheetColumnNumber instance = new SolutionI();
		String s;
		int result;
		
		s = "AA";
		
		result = instance.titleToNumber(s);
		System.out.println("result=" + result);
	}
	
	static class SolutionI extends ExcelSheetColumnNumber {
		public int titleToNumber(String s) {
	        int num = 0;
	        for (int i = s.length()-1; i >= 0; i--) {
	            num = 26*num + (s.charAt(i) - 'A' + 1);
	        }
	        return num;
	    }
	}
}
