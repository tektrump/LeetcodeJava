package tektrup.leetcode.oj;


/**
 * 
 * @author Alex
 * @date Jul 4, 2017
 */
public abstract class AndroidUnlockPatterns {
	public abstract int numberOfPatterns(int m, int n);
    public static void main(String[] args) {
    	AndroidUnlockPatterns instance = new SolutionI();
    	int m, n;
    	
    	// 9
    	m = 1; n = 1;
    	
    	// 56
    	m = 2; n = 2;
    	
    	int result = instance.numberOfPatterns(m, n);
    	System.out.println("result=" + result);
	}
    
    
    static class SolutionI extends AndroidUnlockPatterns {
    	// vertical or horizontal
    	private static final int[][] dirs1 = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    	// diagonal
    	private static final int[][] dirs2 = {{1, 1}, {1, -1}, {-1, 1}, {-1, 0}};
        private int count = 0;
        public int numberOfPatterns(int m, int n) {
            boolean[][] used = new boolean[3][3];
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++)
                    dfs(r, c, used, 1, m, n);
            }
            
            return count;
        }
        
        // r, c is always valid
        private void dfs(int r, int c, boolean[][] used, int len, int m, int n) {
            if (len > n)
                return;
            if (len >= m)
                count++;
            // make 1 move
            move1(r, c, dirs1, used, len, m, n);
            move1(r, c, dirs2, used, len, m, n);
            
            // make 2 moves
            move2(r, c, dirs1, dirs1, used, len, m, n);
            move2(r, c, dirs2, dirs2, used, len, m, n);
            move2(r, c, dirs1, dirs2, used, len, m, n);
        }
        
        private void move1(int r, int c, int[][] dirs, boolean[][] used, int len, int m, int n) {
        	for (int[] dir : dirs) {
        		int r1 = r + dir[0];
        		int c1 = c + dir[1];
        		if (r1 >= 0 && r1 < 3 && c1 >= 0 && c1 < 3 && !used[r1][c1]) {
        			used[r1][c1] = true;
        			dfs(r1, c1, used, len+1, m, n);
        		}
        	}
        }
        
        private void move2(int r, int c, int[][] dirs1, int[][] dirs2, boolean[][] used, int len, int m, int n) {
//        	for (int[] dir1 : dirs1) {
//        		// used
//        		for (int[] dir2 : dir1) {
//        			// used
//        			 dfs(r, c, dir1 + dir2)
//        		}
//        	}
        }
    }
}
