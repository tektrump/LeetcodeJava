package tektrup.cookbooks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlReader {
	
	public static void main(String[] args) {
		final String urlStr = "http://google.com";	// must include "http" or "https"
		
		readUrl(urlStr);
	}

	/**
	 * Read content from given URL and print out.
	 * @param urlStr
	 */
	public static void readUrl(String urlStr) {
		BufferedReader br = null;
		try {
			URL url = new URL(urlStr);
			// openStream() returns an InputStream; wrap it with InputStreamReader;
			// then wrap InputStreamReader with BufferedReader to read line by line.
			br = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
