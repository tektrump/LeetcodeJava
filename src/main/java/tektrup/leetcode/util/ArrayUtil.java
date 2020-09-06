package tektrup.leetcode.util;

import java.util.Arrays;

public class ArrayUtil {
	
	/**
	 * print out elements of integer array separated by comma.
	 * @param array
	 */
	public static void printArray(int[] array) {
		boolean first = true;
		for (int i : array) {
			if (first) {
				System.out.print(i);
				first = false;
			} else {
				System.out.print(", " + i);
			}
		}
		System.out.println();
	}
	
	public static void printMatrix(int[][] array) {
		for (int[] row : array)
			System.out.println(Arrays.toString(row));
	}
	
	public static void printMatrix(char[][] array) {
		for (char[] row : array)
			System.out.println(Arrays.toString(row));
	}
	
	public static int[] str2intArray(String s) {
		s = s.trim();
		// remove square brackets
		s = s.substring(1, s.length()-1);
		String[] splits = s.split(",");
		int[] results = new int[splits.length];
		for (int i = 0; i < splits.length; i++)
			results[i] = Integer.valueOf(splits[i].trim());
		return results;
	}

	public static int[][] str2int2DArray(String s) {
		s = s.substring(1, s.length()-1);
		if (s.length() == 0)
			return new int[][]{};
		String[] splits = s.split("\\[");
		int[][] results = new int[splits.length - 1][];
		int r = 0;
		for (String split : splits) {
			split = split.trim();
			if (split.isEmpty())
				continue;
			if (split.charAt(split.length()-1) == ',')
				split = split.substring(0, split.length() - 2);
			else
				split = split.substring(0, split.length() - 1);
			String[] elements = split.split(",");
			int[] row = new int[elements.length];
			for (int i = 0; i < elements.length; i++) {
				row[i] = Integer.valueOf(elements[i].trim());
			}
			results[r++] = row;
		}
		return results;
	}
	
	/**
	 * Input like ["abc", "efg"]; each row is delimited by ",".
	 * @param s
	 * @return
	 */
	public static char[][] strArrayTo2DCharArray(String[] sArray) {
		char[][] results = new char[sArray.length][];
		for (int i = 0; i < sArray.length; i++) {
			results[i] = sArray[i].toCharArray();
		}
		return results;
	}
	
	public static char[][] str2int2DCharArray(String[] strs) {
		char[][] results = new char[strs.length][];
		int i = 0;
		for (String str : strs)
			results[i++] = str.trim().toCharArray();
		return results;
	}
	
	/**
	 * Input ["abc", "efg"] -> array with 2 strings.
	 * @param s
	 * @return
	 */
	public static String[] str2strArray(String s) {
		if (s == null || (s = s.trim()).isEmpty())
			return new String[0];
		s = trimParenthesis(s);
		String[] results = s.split(",");
		for (int i = 0; i < results.length; results[i] = trimQuotes(results[i++]));
		return results;
	}
	
	/**
	 * Input [["EZE","AXA"],["TIA","ANU"]] -> 2D string[][] array.
	 * @param s
	 * @return
	 */
	public static String[][] strTo2DStrArray(String s) {
		if (s == null || (s = s.trim()).isEmpty())
			return new String[0][];
		s = trimParenthesis(s);
		String[] splits = s.split("]");
		String[][] results = new String[splits.length][];
		int idx = 0;
		for (String split : splits) {
			results[idx++] = str2strArray(split);
		}
		return results;
	}
	
	/**
	 * Remove leading & trailing brackets.
	 * @param s
	 * @return
	 */
	public static String trimParenthesis(String s) {
		if (s == null || s.isEmpty())
			return s;
		StringBuilder builder = new StringBuilder(s);
		while (builder.length() > 0) {
			char head = builder.charAt(0);
			if (head == '(' || head == '[' || head == '{')
				builder.deleteCharAt(0);
			else
				break;
		}
		while (builder.length() > 0) {
			char tail = builder.charAt(builder.length() - 1);
			if (tail == ')' || tail == ']' || tail == '}')
				builder.deleteCharAt(builder.length() - 1);
			else
				break;
		}
		return builder.toString();
	}
	
	/**
	 * Trim leading/ trailing single or double quotes.
	 * @param s
	 * @return
	 */
	public static String trimQuotes(String s) {
		if (s == null || s.isEmpty())
			return s;
		StringBuilder builder = new StringBuilder(s);
		while (builder.length() > 0) {
			char head = builder.charAt(0);
			if (head == '\'' || head == '\"')
				builder.deleteCharAt(0);
			else
				break;
		}
		while (builder.length() > 0) {
			char tail = builder.charAt(builder.length() - 1);
			if (tail == '\'' || tail == '\"')
				builder.deleteCharAt(builder.length() - 1);
			else
				break;
		}
		return builder.toString();
	}
}
