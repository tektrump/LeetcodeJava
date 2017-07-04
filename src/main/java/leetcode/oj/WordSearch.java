package leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import leetcode.util.ArrayUtil;

public abstract class WordSearch {
	public abstract boolean exist(char[][] board, String word);
	public static void main(String[] args) {
		WordSearch instance = new SolutionIII();
		char[][] board; String word;
		boolean result;
		
		// true
//		board = new char[][] {{'a', 'b'}, {'c', 'd'}};
//		word = "acdb";
		
		// true
//		board = ArrayUtil.str2int2DCharArray(new String[]{"CAA","AAA","BCD"});
//		word = "AAB";
		
		// true
		board = ArrayUtil.str2int2DCharArray(new String[]{"ABCE","SFCS","ADEE"});
		word = "ABCCED";
		
		result = instance.exist(board, word);
		System.out.println("result=" + result);
	}
	
	
	// Solution IV: TLE (expected)
	// to demonstrate the BFS solution, which is much less efficient than DFS.
	static class SolutionIV extends WordSearch {
		private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    public boolean exist(char[][] board, String word) {
	        if (board.length == 0 || board[0].length == 0 || word == null || word.isEmpty())
	            return false;
	        
	        int rows = board.length, cols = board[0].length;
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (board[r][c] != word.charAt(0))
	                    continue;
	                // bfs
	                // from the tail of the path, we can search 4 neighbors for next move.
	                Deque<List<Integer>> tails = new LinkedList<>();
	                // each 'path' is a set tracking visited vertices along this path.
	                Deque<Set<List<Integer>>> paths = new LinkedList<>();
	                List<Integer> root = Arrays.asList(r, c);
	                tails.add(root);
	                Set<List<Integer>> path0 = new HashSet<>();
	                path0.add(root);
	                paths.add(path0);
	                while (!paths.isEmpty()) {
	                    List<Integer> tail = tails.pollFirst();
	                    Set<List<Integer>> path = paths.pollFirst();
	                    if (path.size() == word.length())
	                        return true;
	                    int r1 = tail.get(0), c1 = tail.get(1);
	                    for (int[] dir : dirs) {
	                        int r2 = r1 + dir[0];
	                        int c2 = c1 + dir[1];
	                        if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < cols && board[r2][c2] == word.charAt(path.size())) {
	                            List<Integer> nextP = Arrays.asList(r2, c2);
	                            if (!path.contains(nextP)) {
	                                tails.add(nextP);
	                                // to extend, we must make a full copy of path first; this is very expensive
	                                Set<List<Integer>> nextPath = new HashSet<>(path);
	                                nextPath.add(nextP);
	                                paths.add(nextPath);
	                            }
	                        }
	                    }
	                }
	            }
	        }
	        
	        return false;
	    }
	}
	
	
	// Solution III: Accepted
	// using DFS with backtracking.
	static class SolutionIII extends WordSearch {
		public boolean exist(char[][] board, String word) {
	        if (board == null || board.length == 0 || board[0].length == 0)
	            return false;
	        int rows = board.length, cols = board[0].length;
	        // for backtracking, we just need 1 copy of 'used' because 
	        // it will stay intact in the end, and can be reused for next [r,c].
	        boolean[][] used = new boolean[rows][cols];
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (dfs(r, c, word, 0, used, board))
	                    return true;
	            }
	        }
	        
	        return false;
	    }
	    
	    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	    private boolean dfs(int r, int c, String w, int idx, boolean[][] used, char[][] board) {
	        used[r][c] = true; // modify
	        char ch = w.charAt(idx);
	        if (ch == board[r][c]) {
	            if (idx == w.length()-1)
	                return true;
	            for (int[] dir : dirs) {
	                int r1 = r + dir[0], c1 = c + dir[1];
	                if (r1 >= 0 && r1 < board.length && c1 >= 0 && c1 < board[0].length && !used[r1][c1]) {
	                    if (dfs(r1, c1, w, idx+1, used, board))
	                        return true;
	                }
	            }
	        }
	        used[r][c] = false; // restore
	        
	        return false;
	    }
	}
	
	
	// Solution I: Logic Error
	// BFS, but we are not searching for a node, but a path. See my note for more details.
	static class SolutionI extends WordSearch {
		private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    public boolean exist(char[][] board, String word) {
	        if (board == null || board.length == 0 || board[0].length == 0)
	            return false;
	        // word is not null or empty
	        int rows = board.length, cols = board[0].length;
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (bfs(board, r, c, word))
	                    return true;
	            }
	        }
	        return false;
	    }
	    
	    private boolean bfs(char[][] board, int r, int c, String word) {
	        int rows = board.length, cols = board[0].length;
	        Set<Integer> used = new HashSet<>();
	        List<Integer> ps = new ArrayList<>();
	        int p0 = cols*r + c;
	        ps.add(p0);
	        used.add(p0);
	        for (int i = 0; i < word.length(); i++) {
	            char ch = word.charAt(i);
	            List<Integer> nextPs = new ArrayList<>();
	            boolean found = false;
	            for (int p : ps) {
	                int r1 = p / cols, c1 = p % cols;
	                if (board[r1][c1] == ch) {
	                    found = true;
	                    for (int[] dir : dirs) {
	                        int r2 = r1 + dir[0], c2 = c1 + dir[1];
	                        if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < cols) {
	                            int nextP = cols*r2 + c2;
	                            if (used.add(nextP))
	                                nextPs.add(nextP);
	                        }
	                    }
	                }
	            }
	            if (!found)
	                return false;
	            ps = nextPs;
	        }
	        return true;
	    }
	}
}
