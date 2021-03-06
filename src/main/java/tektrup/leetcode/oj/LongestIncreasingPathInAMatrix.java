package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tektrup.leetcode.util.ArrayUtil;
import tektrup.leetcode.util.annotations.Leetcode;
import tektrup.leetcode.util.annotations.Leetcode.Tags;

@Leetcode(date="2016-06-06", tags={Tags.TABLE, Tags.DFS}, url="https://leetcode.com/problems/longest-increasing-path-in-a-matrix/")
public abstract class LongestIncreasingPathInAMatrix {
	public abstract int longestIncreasingPath(int[][] matrix);
    public static void main(String[] args) {
    	LongestIncreasingPathInAMatrix instance = new SolutionIV();
    	int[][] matrix;
    	
    	// 4
//    	matrix = new int[][]{{3,4,5}, {3,2,6}, {2,2,1}};
    	
    	// 10
//    	String s = "[[17,7,10,8,3,13,17,19,12,8,5,18,9,10,19,15,3,14,15,10,4,8,13,15,6,9,19,16,1,17,10,16,7,5,7,14,18,5,1,8,1,18,0,7,11,10,1,8,5,0,19,9,7,4,9,18,7,17,4,4,5,5,16,0],[19,6,0,13,1,18,6,7,2,1,12,7,6,15,14,16,11,17,18,4,16,18,2,9,4,19,8,0,1,0,11,0,9,11,10,17,9,18,11,11,17,8,10,5,4,2,4,6,18,3,11,14,7,14,15,19,6,5,8,17,8,3,19,1],[0,11,15,5,17,15,2,8,3,17,5,0,10,13,12,18,4,7,18,19,13,17,17,1,10,5,19,13,3,1,10,2,10,4,17,7,18,16,4,7,4,2,16,10,18,10,1,17,14,17,19,19,6,6,0,19,18,12,16,5,9,6,13,12],[13,12,7,2,15,2,16,8,16,4,5,10,7,12,17,11,16,13,6,18,16,1,3,7,3,14,13,9,7,6,3,14,15,12,17,14,0,4,7,2,11,18,19,4,2,0,9,19,17,6,0,8,18,3,7,16,0,18,2,14,12,14,10,17],[6,12,3,19,6,10,9,18,13,18,7,8,14,13,5,2,11,10,16,13,5,10,0,0,11,17,1,10,0,9,11,9,19,8,17,17,1,12,6,19,18,10,19,2,12,15,0,15,1,7,9,17,15,12,1,3,15,3,11,11,4,11,14,13],[10,15,3,12,11,7,17,10,5,11,9,2,3,10,19,3,6,13,13,12,0,13,7,10,8,4,13,7,3,4,18,13,15,18,6,5,18,2,2,2,12,4,3,4,3,11,1,9,0,10,19,10,6,19,14,5,9,4,2,0,5,17,4,18],[14,9,9,10,18,17,7,16,17,11,15,4,5,1,4,10,1,0,16,3,4,0,1,15,0,2,2,4,11,5,15,12,3,0,19,16,14,1,14,11,10,14,4,6,12,0,17,5,10,12,18,16,7,19,9,8,19,0,5,5,9,5,0,6],[13,18,3,15,2,8,12,4,1,18,3,2,0,4,3,7,11,14,11,3,1,5,1,2,7,15,15,9,13,3,7,9,18,17,18,11,3,0,0,10,11,7,18,4,2,2,16,3,0,6,6,5,14,8,14,14,16,2,1,14,6,9,8,0],[5,3,17,17,4,13,3,2,8,17,2,0,10,5,1,14,9,8,3,10,13,6,4,6,6,16,17,3,9,2,14,11,13,9,13,19,18,13,13,4,4,11,16,2,14,5,13,0,2,3,6,11,6,2,3,10,1,8,5,19,16,7,10,8],[7,14,0,14,4,14,5,3,11,19,15,11,1,7,13,12,15,4,15,14,13,17,18,1,17,7,4,0,10,14,15,4,15,4,0,19,6,7,8,7,17,7,15,13,16,18,5,14,12,7,2,16,8,10,2,16,18,17,18,9,12,1,10,2],[14,9,8,12,17,9,18,13,6,11,12,9,13,18,1,15,4,19,16,6,12,5,2,19,16,18,3,10,5,4,19,7,6,18,12,14,0,3,18,1,10,17,6,19,4,15,0,19,9,2,19,14,7,6,7,12,14,1,16,10,14,9,13,2],[0,3,12,18,11,13,1,2,16,3,10,16,19,6,14,17,13,2,5,6,12,10,9,18,2,3,19,19,18,18,1,17,7,15,1,6,11,15,6,5,10,14,2,2,9,2,5,14,5,4,17,0,0,8,12,19,6,0,2,0,13,18,15,12],[13,12,1,12,0,13,19,1,10,14,8,10,3,15,11,9,18,19,12,14,14,16,15,3,11,8,4,13,8,10,15,12,1,8,14,10,9,4,6,18,9,19,15,17,11,10,5,7,18,13,16,2,14,15,14,19,14,3,17,2,11,12,4,5],[18,12,15,10,18,16,7,14,7,12,3,1,5,14,6,15,2,0,2,14,17,3,15,3,17,12,13,7,12,18,10,18,6,15,4,19,19,7,19,14,12,7,12,4,12,19,19,10,2,9,15,16,19,19,0,10,9,8,8,0,17,8,15,11],[19,7,1,16,1,15,18,5,12,6,15,12,12,13,0,11,9,16,2,6,18,12,14,4,2,7,19,9,2,8,17,17,6,10,3,7,4,3,16,5,7,1,6,19,13,5,4,7,13,13,19,7,13,15,4,2,3,9,0,10,2,0,0,12],[8,5,0,3,3,3,4,14,9,10,3,19,8,18,4,13,13,7,14,11,13,17,3,0,10,9,5,7,1,3,2,19,11,12,6,19,14,19,11,1,6,7,1,18,13,12,12,2,12,7,3,9,8,18,7,5,10,1,13,8,15,14,1,7],[4,10,0,5,2,0,13,5,13,10,4,3,7,12,15,13,16,13,15,3,1,16,3,6,10,7,4,2,18,19,5,7,7,17,18,12,2,5,1,19,0,13,4,1,14,17,6,3,3,5,8,8,1,9,0,2,12,12,2,15,7,16,0,0],[19,15,4,12,2,3,8,7,5,8,17,6,7,11,1,8,10,7,8,4,19,8,14,6,10,15,8,4,16,4,8,17,10,13,14,9,15,9,13,16,2,1,8,1,17,6,15,3,18,18,14,11,12,9,8,17,10,10,12,5,0,8,9,3],[15,3,5,10,4,6,17,2,13,18,7,18,8,3,1,12,5,15,2,0,3,17,15,4,18,7,4,19,16,7,0,9,17,8,15,5,2,5,12,18,11,12,4,4,17,17,6,13,0,14,8,17,8,7,3,14,11,19,11,0,7,9,13,4],[10,17,14,1,4,3,17,8,0,3,6,13,8,16,4,7,2,7,4,17,8,3,3,4,1,5,13,5,13,6,4,3,4,2,13,2,10,10,6,3,4,15,9,4,19,0,5,0,17,9,11,8,2,13,8,1,0,11,2,19,7,11,4,15],[8,19,0,8,13,1,16,4,11,3,13,13,8,10,1,16,0,17,16,10,7,18,1,6,11,3,14,4,17,19,6,8,7,8,3,19,17,4,18,14,16,12,19,12,7,9,1,14,19,7,2,7,14,17,12,19,16,10,6,9,1,18,2,4],[7,16,15,7,12,12,18,14,13,18,9,1,15,18,5,3,1,3,7,0,15,6,3,7,10,12,8,11,10,8,7,6,1,16,18,0,0,4,1,19,13,9,17,12,0,19,17,2,3,1,6,7,19,10,2,18,4,9,2,4,5,2,18,8],[4,16,14,15,13,8,2,14,11,8,18,1,7,14,0,16,2,16,12,13,5,8,2,1,8,16,19,18,8,11,2,3,2,12,2,6,6,13,6,14,17,17,4,8,7,18,7,16,4,17,19,10,10,8,11,8,4,18,19,0,2,12,0,6],[10,8,6,10,3,18,9,4,17,14,15,13,1,12,13,19,10,16,13,6,5,10,17,2,1,15,15,1,4,3,0,1,3,19,18,15,5,15,6,3,0,0,4,0,16,10,7,17,0,13,1,13,2,3,15,16,3,11,13,3,1,10,10,14],[3,14,19,14,15,8,14,8,2,18,17,1,0,7,5,1,2,19,3,12,10,18,10,13,3,10,15,0,14,16,13,2,0,9,3,5,6,6,13,7,7,14,4,5,14,12,11,15,0,11,19,13,4,15,5,18,6,19,7,2,2,6,10,7],[12,11,4,13,5,15,14,19,3,6,4,18,17,2,16,16,2,17,17,17,13,7,1,2,19,11,19,0,5,6,4,9,13,6,4,6,8,14,2,16,10,19,13,17,9,10,5,0,7,5,5,5,0,9,6,10,1,10,10,2,12,2,4,11],[8,12,9,1,0,4,5,3,0,13,11,6,15,3,13,6,13,15,4,19,14,17,12,9,4,8,12,19,18,18,2,14,9,18,3,8,16,19,18,12,12,19,10,10,15,10,1,14,11,2,16,17,13,15,14,16,15,12,14,17,0,12,9,14],[0,12,16,9,13,10,14,18,16,15,6,15,4,6,9,13,0,19,11,1,12,12,1,18,8,15,18,19,13,6,2,14,9,5,17,6,13,5,9,18,18,19,16,6,6,15,8,7,13,18,8,13,0,19,12,8,13,5,14,17,17,19,13,10],[4,15,7,13,19,1,14,3,0,8,3,12,18,1,7,15,2,11,7,15,11,8,0,6,4,2,1,19,17,0,9,11,16,8,1,0,9,13,14,9,8,1,7,7,11,4,2,14,0,18,9,13,17,19,13,3,11,14,19,5,12,7,11,4],[7,5,19,5,7,13,8,10,15,12,12,1,17,13,16,19,17,14,16,5,9,1,10,19,19,4,0,19,7,2,15,18,19,13,5,2,1,17,6,3,5,7,10,16,9,15,2,7,1,8,14,13,3,5,0,11,12,1,4,17,5,13,13,5],[18,14,15,1,13,10,1,15,14,3,17,2,10,19,4,5,10,12,6,12,4,4,11,15,10,1,12,19,14,10,16,0,2,10,1,17,12,11,0,8,6,16,18,5,5,16,13,2,7,5,4,9,15,12,7,6,9,8,2,17,11,3,8,4],[12,14,16,9,4,0,5,18,9,17,8,7,1,2,10,9,8,5,16,18,9,7,19,12,12,1,4,15,12,7,3,0,16,18,10,15,8,18,1,18,14,4,10,14,6,5,11,19,12,14,6,6,17,8,3,19,5,2,13,5,7,7,7,0],[14,6,13,2,13,12,4,11,7,15,2,12,9,13,19,12,5,17,6,8,19,16,9,16,7,17,13,11,2,5,0,6,2,1,16,6,10,2,13,6,18,15,16,16,5,11,15,7,9,18,17,14,0,2,3,18,17,2,12,5,5,19,11,19],[16,8,15,3,11,12,17,7,13,16,6,13,19,6,7,16,1,0,2,19,11,18,1,18,12,19,19,16,7,4,12,18,0,19,11,4,1,19,3,13,10,5,17,17,14,11,18,8,5,17,5,15,8,8,1,11,1,2,8,18,0,1,7,0],[5,18,2,7,11,14,16,8,18,7,14,15,19,16,9,15,10,11,14,2,4,13,17,10,18,9,13,8,9,13,19,8,5,4,6,7,18,9,0,13,10,6,16,3,1,11,16,4,17,7,9,12,7,11,13,18,8,14,5,19,5,0,1,8],[10,14,14,16,17,16,7,16,4,18,3,16,0,7,18,11,17,0,18,17,15,9,10,13,7,13,8,1,16,14,17,4,2,0,2,2,13,16,16,12,5,19,11,8,17,1,8,14,9,12,2,9,11,16,8,11,19,8,2,19,7,12,18,13],[3,6,10,1,12,4,19,14,0,12,0,14,11,16,17,14,13,1,15,4,12,10,19,17,4,13,11,14,5,13,5,6,10,12,2,1,17,1,18,17,19,11,11,6,10,0,7,9,0,16,19,18,16,6,2,10,9,5,16,0,7,17,5,0],[3,1,2,18,1,13,3,18,13,13,18,8,3,17,8,3,0,9,5,11,11,12,14,3,8,4,12,3,12,12,12,19,2,7,3,4,19,14,15,12,13,5,19,16,9,4,4,16,3,3,13,19,3,5,3,12,3,14,18,5,5,11,11,6],[7,11,12,4,16,8,13,16,1,18,9,10,1,16,8,10,7,0,16,10,19,15,16,16,4,6,4,0,1,5,17,5,10,0,16,0,6,9,14,13,19,3,8,18,18,17,4,16,6,14,16,8,12,9,18,8,17,1,5,10,8,3,4,6],[6,17,5,9,16,7,2,16,12,17,16,16,9,11,8,0,9,14,7,2,4,12,16,15,19,7,18,1,7,3,9,2,1,1,11,11,7,17,12,8,5,16,9,16,1,11,15,2,16,7,16,6,11,2,4,13,19,8,17,11,9,7,11,7],[17,5,16,18,2,3,1,10,4,16,12,15,6,16,18,3,15,7,6,17,8,2,7,12,15,16,0,10,3,10,12,17,0,1,5,15,10,14,9,2,18,12,11,7,1,2,0,14,18,4,8,16,7,0,8,5,4,6,18,17,16,17,1,15],[0,13,19,0,14,17,15,15,7,13,0,13,13,10,14,4,17,3,3,13,13,5,13,11,1,7,10,12,12,2,9,6,14,8,17,6,2,8,4,17,15,15,10,1,7,19,8,9,15,0,4,1,5,3,19,15,4,3,10,14,12,15,10,5],[6,7,3,2,7,3,4,9,9,14,9,7,2,9,17,8,14,7,16,9,7,16,5,12,8,2,2,9,19,8,9,1,12,18,1,10,4,19,10,15,11,18,18,12,14,4,2,17,8,11,17,3,12,12,12,12,17,3,12,4,2,19,17,1],[12,9,7,0,6,3,7,10,3,18,15,6,4,1,7,18,7,13,17,13,11,13,4,15,10,7,14,7,0,14,3,18,10,16,2,18,12,19,15,19,11,19,6,18,19,11,12,0,1,13,7,5,5,13,5,18,0,6,10,2,9,16,15,4],[18,18,8,6,18,0,5,17,9,1,2,1,16,2,8,5,7,15,3,1,5,4,18,12,4,12,18,9,10,2,16,10,8,8,19,2,1,9,2,1,10,4,18,5,14,7,19,17,9,10,2,3,0,16,12,6,8,17,5,11,14,2,19,0],[19,6,18,8,16,2,5,6,7,15,8,8,1,17,19,19,3,16,18,13,4,15,2,0,17,8,3,10,2,2,12,11,13,16,1,6,0,7,16,1,18,11,8,0,18,16,10,0,17,19,13,13,0,8,17,13,19,14,13,1,17,18,14,19],[0,1,8,18,0,1,5,17,16,0,0,7,0,16,12,11,7,19,6,1,9,7,17,13,10,3,1,0,11,16,2,15,1,2,16,15,8,3,4,3,9,14,18,7,12,10,15,5,9,18,1,16,2,9,17,1,18,3,9,4,17,18,16,15],[5,11,15,12,17,9,2,2,12,13,4,10,9,5,17,14,11,2,6,5,0,13,14,5,15,5,3,19,18,7,7,5,6,0,3,3,2,8,7,11,19,8,8,18,4,13,14,9,0,17,9,10,1,19,0,12,0,10,1,2,19,12,8,14],[5,13,7,3,16,11,15,17,13,16,17,17,3,19,0,18,2,13,14,2,4,4,18,11,3,2,14,1,15,15,15,16,1,1,3,16,14,14,17,1,17,1,19,14,1,18,17,6,7,10,10,13,2,14,17,13,2,13,3,0,11,18,3,19],[8,1,3,14,16,1,2,3,17,15,3,17,15,14,17,16,12,10,0,19,6,7,5,19,19,3,6,19,17,4,13,6,5,1,19,0,15,1,9,10,11,10,11,5,15,11,18,14,17,19,4,19,5,15,5,4,16,19,13,15,16,18,13,2],[12,5,16,16,6,5,3,18,8,1,5,4,13,14,6,1,10,14,6,15,2,0,2,13,2,2,14,18,13,7,10,7,4,12,4,1,2,17,12,0,0,5,16,1,0,10,9,7,7,11,6,2,9,12,8,5,5,0,7,13,11,3,17,19],[3,16,9,15,18,6,8,16,3,14,4,12,4,19,12,8,12,6,12,16,15,6,18,17,13,17,3,5,3,18,2,5,3,3,6,10,19,19,6,9,5,7,13,1,10,13,3,11,1,10,9,1,7,3,13,17,2,1,14,16,15,5,12,11],[9,1,11,14,11,8,10,12,6,18,15,11,5,1,10,13,6,1,11,13,18,18,1,8,4,7,1,15,18,15,16,5,1,16,12,11,13,9,6,0,10,13,18,16,9,6,12,1,10,13,0,0,7,7,13,2,6,8,18,11,1,8,17,4],[7,14,18,9,2,11,12,2,12,1,3,15,11,13,18,0,3,10,3,2,10,2,10,8,2,0,3,2,19,16,4,4,7,13,3,4,17,9,9,17,14,6,9,12,7,4,7,5,11,10,12,4,12,3,7,14,3,11,15,6,1,12,17,16],[5,15,1,13,7,3,16,2,10,19,11,19,19,17,13,1,16,15,16,16,16,11,10,15,16,16,14,5,14,3,0,8,13,8,3,2,10,13,6,8,14,13,4,16,10,15,17,7,12,2,13,3,15,13,4,7,14,12,4,5,18,14,16,5],[3,6,1,0,13,4,3,16,8,5,5,19,17,19,10,12,8,19,17,9,0,2,12,11,14,15,9,1,10,15,6,3,10,11,15,3,13,19,10,5,5,16,3,13,10,4,18,7,8,6,3,14,7,16,16,19,6,1,13,16,5,12,17,17],[4,4,13,15,1,10,4,12,1,5,11,4,13,2,18,9,0,2,18,15,5,9,13,14,19,13,13,8,7,0,14,8,19,13,12,1,14,6,17,13,4,19,19,5,12,17,5,2,0,5,1,18,9,9,15,17,5,17,16,7,13,8,1,19],[13,6,3,19,19,2,8,19,6,13,5,12,13,1,16,1,8,13,9,12,4,10,13,11,0,11,2,3,3,18,10,3,8,2,1,6,8,10,18,14,19,12,0,13,12,19,19,6,3,7,15,6,1,2,11,13,7,4,15,11,8,8,0,7],[17,14,16,11,18,3,11,15,15,19,12,5,0,14,18,2,11,14,4,18,1,4,11,14,12,7,11,11,16,6,8,8,16,2,16,8,16,11,18,10,6,16,5,6,5,11,16,9,10,0,14,14,18,17,15,15,14,6,18,11,19,10,9,5],[9,14,11,19,19,8,8,6,3,17,12,13,8,9,2,14,18,15,15,4,19,7,4,15,12,11,13,6,10,14,16,11,8,6,6,18,14,14,17,16,16,16,7,3,16,12,11,10,5,12,18,9,9,0,13,7,4,3,2,18,5,13,5,10],[12,17,2,7,17,3,0,15,11,6,12,14,10,8,12,4,0,10,3,10,19,15,11,11,16,6,11,14,3,16,15,9,19,8,18,12,13,1,10,12,9,10,14,7,13,17,5,0,3,19,11,2,6,17,10,10,13,11,7,18,4,1,3,4],[0,19,14,3,13,15,3,18,6,19,9,19,2,4,5,16,14,13,4,12,8,1,2,0,3,4,10,18,5,14,17,0,10,6,6,10,18,10,18,10,10,4,5,4,15,0,5,9,9,17,12,6,0,11,18,18,18,7,5,10,9,3,8,13],[6,8,6,11,3,4,0,3,1,2,15,16,11,2,9,2,0,3,14,14,19,11,2,4,6,9,2,6,16,14,17,14,13,2,2,1,16,3,4,14,7,17,17,9,15,13,12,13,7,7,14,14,12,2,4,1,11,10,3,18,15,5,8,3],[5,4,15,7,2,11,7,2,12,4,5,9,9,1,19,18,9,12,12,9,4,11,14,14,5,17,7,14,1,15,16,13,15,14,19,15,0,6,17,8,12,17,6,19,1,6,10,18,12,19,0,7,14,14,16,10,19,11,14,6,15,5,2,0],[7,16,4,4,19,2,14,3,10,14,8,10,6,8,4,8,10,8,6,15,18,11,12,1,11,0,12,11,8,17,14,15,17,17,1,14,10,10,1,4,16,4,7,4,16,12,2,13,5,3,15,15,1,3,16,17,11,16,17,19,16,5,7,7],[1,10,2,4,5,11,7,15,4,14,11,4,0,9,8,1,11,0,10,2,14,9,12,15,5,15,1,11,12,12,18,15,14,19,0,0,1,19,19,7,0,18,9,5,1,12,17,11,9,14,3,8,17,9,2,14,7,4,13,7,4,19,3,12],[6,12,5,9,5,4,18,8,10,6,0,17,3,15,10,15,3,10,10,17,7,16,8,4,6,8,12,8,14,17,1,9,0,8,7,19,10,9,12,11,19,15,15,15,11,8,2,14,5,16,3,1,12,17,14,5,6,11,1,8,16,14,17,19],[3,17,13,10,3,7,17,4,11,13,12,3,12,18,3,0,19,16,14,4,15,4,2,14,7,17,14,12,10,6,6,2,4,17,10,10,17,13,16,18,2,8,14,5,11,2,7,4,5,13,8,10,0,18,10,14,9,2,3,9,15,10,2,1],[16,9,13,7,18,7,10,10,4,15,6,9,19,15,10,7,3,16,3,7,11,5,6,14,16,13,14,17,3,9,19,8,1,12,3,5,9,13,18,19,4,3,10,19,17,9,9,16,16,13,13,15,1,7,15,14,4,7,4,2,3,3,1,4],[12,12,5,12,19,8,13,18,0,12,0,10,0,12,12,4,18,16,5,2,18,12,17,1,0,16,18,14,14,16,17,16,13,19,16,0,15,15,7,2,19,14,6,8,16,17,3,10,3,13,11,4,1,2,4,11,2,3,11,10,0,9,19,9]]";
//    	matrix = ArrayUtil.str2int2DArray(s);
    	
    	// this runs forever with solution I&II without memoization, but only 1ms for solution IV.
    	// 140
    	String s = "[[0,1,2,3,4,5,6,7,8,9],[19,18,17,16,15,14,13,12,11,10],[20,21,22,23,24,25,26,27,28,29],[39,38,37,36,35,34,33,32,31,30],[40,41,42,43,44,45,46,47,48,49],[59,58,57,56,55,54,53,52,51,50],[60,61,62,63,64,65,66,67,68,69],[79,78,77,76,75,74,73,72,71,70],[80,81,82,83,84,85,86,87,88,89],[99,98,97,96,95,94,93,92,91,90],[100,101,102,103,104,105,106,107,108,109],[119,118,117,116,115,114,113,112,111,110],[120,121,122,123,124,125,126,127,128,129],[139,138,137,136,135,134,133,132,131,130],[0,0,0,0,0,0,0,0,0,0]]";
    	matrix = ArrayUtil.str2int2DArray(s);
    	long t1 = System.currentTimeMillis();
    	int result = instance.longestIncreasingPath(matrix);
    	long t2 = System.currentTimeMillis();
    	System.out.println("result=" + result);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
    
    
    // Solution IV: Best
    // improved from solution III: use a table instead of map for cache, save time.
    static class SolutionIV extends LongestIncreasingPathInAMatrix {
    	private static final int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        public int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
                return 0;
            int rows = matrix.length, cols = matrix[0].length;
            int[][] counts = new int[rows][];
            // for (int[] count : counts)
            //     count = new int[cols];
            for (int i = 0; i < counts.length; counts[i++] = new int[cols]);
            int max = 0;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++)
                    max = Math.max(max, bfs(r, c, matrix, counts));
            }
            return max;
        }
        
        private int bfs(int r, int c, int[][] matrix, int[][] counts) {
            if (counts[r][c] > 0)
                return counts[r][c];
            int max = 1;
            for (int[] move : moves) {
                int r1 = r + move[0], c1 = c + move[1];
                if (r1 >= 0 && r1 < matrix.length && c1 >= 0 && c1 < matrix[0].length && matrix[r1][c1] > matrix[r][c])
                    max = Math.max(max, 1 + bfs(r1, c1, matrix, counts));
            }
            counts[r][c] = max;
            return max;
        }
    }
    
    
    // Solution III: Accepted
    // start from any point, try to expand using dfs;
    // use memoization to record max path from each given point.
    // NOTE: no need to worry about loop; if A->B, then there will not be a B->A.
    // and we don't care visiting the same note again; we are not looking for shortest path, but the opposite.
    static class SolutionIII extends LongestIncreasingPathInAMatrix {
    	private static final int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        private Map<Integer, Integer> cache = new HashMap<>();
        public int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
                return 0;
            int max = 0;
            for (int r = 0; r < matrix.length; r++) {
                for (int c = 0; c < matrix[0].length; c++)
                    max = Math.max(max, dfs(r, c, matrix));
            }
            return max;
        }
        
        private int dfs(int r, int c, int[][] matrix) {
            int rows = matrix.length, cols = matrix[0].length;
            int p = cols*r + c;
            Integer max = cache.get(p);
            if (max != null)
                return max;
            max = 1;
            for (int[] move : moves) {
                int r1 = r + move[0], c1 = c + move[1];
                if (r1 >= 0 && r1 < rows && c1 >= 0 && c1 < cols && matrix[r1][c1] > matrix[r][c])
                    max = Math.max(max, 1 + dfs(r1, c1, matrix));
            }
            cache.put(p, max);
            return max;
        }
    }
    
    
    // Solution II: TLE
    // instead of tracking the path, we only need to track the last (biggest) number on the path;
    // still suffer TLE due to the same reason: no memoization.
    static class SolutionII extends LongestIncreasingPathInAMatrix {
    	private static final int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        public int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
                return 0;
            int rows = matrix.length, cols = matrix[0].length;
            List<Integer> ps = new ArrayList<>();
            List<Integer> counts = new ArrayList<>();
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    int val = matrix[r][c];
                    boolean isMin = true;
                    for (int[] move : moves) {
                        int r1 = r + move[0], c1 = c + move[1];
                        if (less(val, r1, c1, matrix, rows, cols)) {
                            isMin = false;
                            break;
                        }
                    }
                    if (isMin) {
                        ps.add(cols*r + c);
                        counts.add(1);
                    }
                }
            }
            // bfs
            int max = 0;
            while (!ps.isEmpty()) {
                max++;
                List<Integer> nextPs = new ArrayList<>();
                List<Integer> nextCounts = new ArrayList<>();
                for (int i = 0; i < ps.size(); i++) {
                    int p = ps.get(i), count = counts.get(i);
                    int r = p / cols, c = p % cols;
                    int val = matrix[r][c];
                    for (int[] move : moves) {
                        int r1 = r + move[0], c1 = c + move[1];
                        if (bigger(val, r1, c1, matrix, rows, cols)) {
                            nextPs.add(cols*r1 + c1);
                            nextCounts.add(count + 1);
                        }
                    }
                }
                ps = nextPs;
                counts = nextCounts;
            }
            return max;
        }
        
        // return true if valid & less than val
        private boolean less(int val, int r, int c, int[][] matrix, int rows, int cols) {
            return r >= 0 && r < rows && c >= 0 && c < cols && matrix[r][c] < val;
        }
        
        private boolean bigger(int val, int r, int c, int[][] matrix, int rows, int cols) {
            return r >= 0 && r < rows && c >= 0 && c < cols && matrix[r][c] > val;
        }
    }
    
    
    // Solution I: TLE
    // pick the smallest number (no neighbor is bigger) as starting point;
    // bfs to expand and record the max length.
    // problem: didn't use memoization. e.g.:
    // 1,2,8,...
    // 3,4,5,...
    // 1->2->8 and 3->4->5->8 both reaches 8, and we shouldn't calculate max length after 8 twice.
    static class SolutionI extends LongestIncreasingPathInAMatrix {
    	private static final int[][] moves = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        public int longestIncreasingPath(int[][] matrix) {
            int rows = matrix.length;
            if (rows == 0)
                return 0;
            int cols = matrix[0].length;
            if (cols == 0)
                return 0;
            
            Map<Set<Integer>, Integer> pathLast = new HashMap<>();
            List<Set<Integer>> paths = new ArrayList<>();
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    int cell = cols*r + c;
                    Set<Integer> path = new HashSet<>();
                    path.add(cell);
                    paths.add(path);
                    pathLast.put(path, cell);
                }
            }
            int max = 1;
            while (!paths.isEmpty()) {
                List<Set<Integer>> nextPaths = new ArrayList<>();
                for (Set<Integer> path : paths) {
                    Integer cell = pathLast.remove(path);
                    int r1 = cell / cols, c1 = cell % cols;
                    for (int[] move : moves) {
                        int r2 = r1 + move[0];
                        int c2 = c1 + move[1];
                        if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < cols && matrix[r2][c2] > matrix[r1][c1]) {
                            Integer nextCell = cols*r2 + c2;
                            if (!path.contains(nextCell)) {
                                Set<Integer> nextPath = new HashSet<>(path);
                                nextPath.add(nextCell);
                                max = Math.max(max, nextPath.size());
                                nextPaths.add(nextPath);
                                pathLast.put(nextPath, nextCell);
                            }
                        }
                    }
                }
                paths = nextPaths;
            }
            
            return max;
        }
    }
}
