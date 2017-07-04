package leetcode.oj;


public class GameOfLife {
	public void gameOfLife(int[][] board) {
        int numRows = board.length;
        int numCols = board[0].length;
        int[][] counts = new int[numRows][numCols];
        int[][] moves = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                for (int[] move : moves) {
                    int row2 = row + move[0];
                    int col2 = col + move[1];
                    if (board[row][col] == 1 && row2 >= 0 && row2 < numRows && col2 >= 0 && col2 < numCols)
                        counts[row2][col2]++;
                }
            }
        }
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int count = counts[row][col];
                if (count < 2 || count > 3)
                    board[row][col] = 0;
                else if (count == 3)
                    board[row][col] = 1;
            }
        }
    }
	
	public static void main(String[] args) {
		GameOfLife instance = new GameOfLife();
		int[][] board = {{0,0,0,0},{0,1,1,0},{0,1,1,0},{0,0,0,0}};
		
		instance.gameOfLife(board);
	}

}
