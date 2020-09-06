package tektrup.leetcode.util.annotations;

public @interface Leetcode {
	// tags
	public static enum Tags {
		CLASSIC, // worth memorizing
		BITWISE,
		LIST,
		MATH,
		RECURSION,
		RECURSION_VS_ITERATION,
		DP,
		BACKTRACKING,
		MEMOIZATION,
		TREE,
		BFS,
		DFS,
		BST,
		GRAPH,
		PREORDER,
		POSTORDER,
		INORDER,
		TABLE,
		TRIE,
		SERIALIZE,
		
		REMEMBER,	// just remember this problem, maybe applied in other cases
	};
	
	enum Difficulty {
		IGNORE,
		EASY,
		MEDIUM,
		HARD,
	}
	
	// date of completion
	String date();
	
	// array of tags; default is empty
	Tags[] tags() default {};
	
	// difficulty levels
	Difficulty diff() default Difficulty.EASY;
	
	// url
	String url() default "";
	
	// blog
	String blog() default "";
}
