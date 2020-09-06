package tektrup.leetcode.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LeetcodeUtils {

	public static String readText(Object instance) {
		// use enclosing class (parent) name as path
		String clazz = instance.getClass().getEnclosingClass().getSimpleName().toString();
		File file = new File("./text/" + clazz);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			return reader.readLine().trim();
		} catch (IOException e) {
			System.err.println("failed to read text from file [" + file + "].");
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {;}
			}
		}
		return null;
	}
}
