package leetcode.oj;


import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class DesignSnakeGame {
	
	public static void main(String[] args) {
		SnakeGame instance = new SnakeGame(3, 3, new int[][]{{2, 0}, {0, 0}});
		System.out.println(instance.move("D"));
		System.out.println(instance.move("D"));
		System.out.println(instance.move("U"));
	}
	
	static class SnakeGame {

	    /** Initialize your data structure here.
	        @param width - screen width
	        @param height - screen height 
	        @param food - A list of food positions
	        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
	    private int width, height;
	    int[][] food;
	    private int h, w;
	    private int score;
	    private int idx;
	    private Deque<Integer> snake;
	    private Set<Integer> body;
	    public SnakeGame(int width, int height, int[][] food) {
	        this.width = width;
	        this.height = height;
	        this.food = food;
	        h = 0;
	        w = 0;
	        score = 0;
	        idx = 0;
	        snake = new LinkedList<>();
	        snake.addLast(0);
	        body = new HashSet<>();
	        body.add(0);
	    }
	    
	    /** Moves the snake.
	        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
	        @return The game's score after the move. Return -1 if game over. 
	        Game over when snake crosses the screen boundary or bites its body. */
	    public int move(String direction) {
	        if (direction.equals("U"))
	            h--;
	        else if (direction.equals("D"))
	            h++;
	        else if (direction.equals("L"))
	            w--;
	        else // "R"
	            w++;
	        if (w < 0 || w == width || h < 0 || h == height)
	            return -1;
	        int head = h*width + w; // head moves
	        if (!body.add(head)) // hit body
	            return -1;
	        snake.addFirst(head);
	        if (idx < food.length && h == food[idx][0] && w == food[idx][1]) {
	            score++; // and still keep the tail
	            idx++;
	        } else
	            body.remove(snake.removeLast()); // tail moves
	        return score;
	    }
	}
}
