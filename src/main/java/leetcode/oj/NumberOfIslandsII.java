package leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import leetcode.util.ArrayUtil;

public abstract class NumberOfIslandsII {
	public abstract List<Integer> numIslands2(int m, int n, int[][] positions);
	public static void main(String[] args) {
		NumberOfIslandsII instance = new SolutionIII();
		int m, n; int[][] positions;
		List<Integer> results;
		
		// [1,1,2,3]
//		m = 3; n = 3;
//		positions = ArrayUtil.str2int2DArray("[[0,0], [0,1], [1,2], [2,1]]");
		
		// [1,2,3,4,3,2,1]
		m = 3; n = 3;
		positions = ArrayUtil.str2int2DArray("[[0,1],[1,2],[2,1],[1,0],[0,2],[0,0],[1,1]]");
		
		// [1,2,1]
//		m = 2; n = 2;
//		positions = ArrayUtil.str2int2DArray("[[0,0],[1,1],[0,1]]");
		
		// [1,2,3,4,5,4,4,5,5,5,6,7,8,8,9]
//		m = 8; n = 6;
//		positions = ArrayUtil.str2int2DArray("[[0,5],[5,4],[5,2],[7,3],[6,0],[5,3],[5,1],[1,3],[4,3],[2,3],[3,5],[3,2],[3,0],[2,4],[0,1]]");
		
		// [1,2,3,4,5,6,7,8,9,10,10,11,12,11,11,12,12,13,13,13,13,13,13,13,13]
//		m = 9; n = 9;
//		positions = ArrayUtil.str2int2DArray("[[8,5],[8,0],[3,4],[0,3],[1,0],[5,4],[0,8],[5,7],[0,6],[6,2],[4,7],[2,7],[8,7],[8,6],[5,3],[2,3],[3,5],[3,1],[0,2],[8,8],[6,4],[0,1],[0,4],[7,5],[3,0]]");
	
		results = instance.numIslands2(m, n, positions);
		System.out.println("results=" + results);
	}
	
	
	// Solution VII: Accepted
	// Improved version of solution VI: use array instead of map to manage ids.
	static class SolutionVII extends NumberOfIslandsII {
		private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    public List<Integer> numIslands2(int m, int n, int[][] positions) {
	        int[][] table = new int[m][n];
	        int id = 1, count = 0;
	        int[] map = new int[positions.length+1];
	        List<Integer> rets = new ArrayList<>(positions.length);
	        for (int[] p : positions) {
	            int r = p[0], c = p[1];
	            table[r][c] = id;
	            map[id] = id;
	            count++;
	            Set<Integer> nbIds = new HashSet<>();
	            for (int[] dir : dirs) {
	                int r1 = r + dir[0];
	                int c1 = c + dir[1];
	                if (r1 >= 0 && r1 < m && c1 >= 0 && c1 < n && table[r1][c1] > 0) {
	                    int nbId = find(table[r1][c1], map);
	                    nbIds.add(nbId);
	                }
	            }
	            for (Integer nbId : nbIds) {
	                map[nbId] = id;
	                count--;
	            }
	            id++;
	            rets.add(count);
	        }
	        
	        return rets;
	    }
	    
	    private int find(int key, int[] map) {
	        int val = map[key];
	        if (key == val)
	            return val;
	        else
	            return find(val, map);
	    }
	}
	
	
	// Solution VI: Accepted
	// implementing using disjoint set.
	static class SolutionVI extends NumberOfIslandsII {
		private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    public List<Integer> numIslands2(int m, int n, int[][] positions) {
	        int[][] table = new int[m][n];
	        int id = 1, count = 0;
	        Map<Integer, Integer> map = new HashMap<>();
	        List<Integer> rets = new ArrayList<>(positions.length);
	        for (int[] p : positions) {
	            int r = p[0], c = p[1];
	            Integer firstId = null;
	            for (int[] dir : dirs) {
	                int r1 = r + dir[0];
	                int c1 = c + dir[1];
	                if (r1 >= 0 && r1 < m && c1 >= 0 && c1 < n && table[r1][c1] > 0) {
	                    int nbId = find(table[r1][c1], map);
	                    if (firstId == null) {
	                        firstId = nbId;
	                        table[r][c] = firstId;
	                    } else if (nbId != firstId) {
	                        // find a diff neighbor id, union it with first id
	                        map.put(nbId, firstId);
	                        count--;
	                    }
	                }
	            }
	            if (firstId == null) { // no neighbor id
	                table[r][c] = id;
	                map.put(id, id);
	                id++;
	                count++;
	            }
	            rets.add(count);
	        }
	        
	        return rets;
	    }
	    
	    private int find(int key, Map<Integer, Integer> map) {
	        int val = map.get(key);
	        if (key == val)
	            return val;
	        else
	            return find(val, map);
	    }
	}
	
	
	// Solution V: Accepted
	// improved version of solution I:
	// find adjacent ids; if only 1, use the same id for new cell;
	// if more than 1, flip the others using DFS.
	static class SolutionV extends NumberOfIslandsII {
		private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    public List<Integer> numIslands2(int m, int n, int[][] positions) {
	        List<Integer> rets = new LinkedList<>();
	        int id = 1, count = 0;
	        int[][] table = new int[m][n];
	        for (int[] p : positions) {
	            int r = p[0], c = p[1];
	            Integer nbId = null;
	            for (int[] dir : dirs) {
	                int r1 = r + dir[0], c1 = c + dir[1];
	                if (r1 >= 0 && r1 < m && c1 >= 0 && c1 < n) {
	                    if (table[r1][c1] > 0) {
	                        if (nbId == null) {
	                            nbId = table[r1][c1];
	                            table[r][c] = nbId;
	                        } else if (table[r1][c1] != nbId) {
	                            flip(table, r1, c1, nbId);
	                            count--;
	                        }
	                    }
	                }
	            }
	            if (nbId == null) {
	                table[r][c] = id++;
	                count++;
	            }
	            rets.add(count);
	        }
	        
	        return rets;
	    }
	    
	    private void flip(int[][] table, int r, int c, int id) {
	        int old = table[r][c];
	        table[r][c] = id;
	        for (int[] dir : dirs) {
	            int r1 = r + dir[0], c1 = c + dir[1];
	            if (r1 >= 0 && r1 < table.length && c1 >= 0 && c1 < table[0].length && table[r1][c1] == old) {
	                flip(table, r1, c1, id);
	            }
	        }
	    }
	}
	
	
	static class SolutionIV extends NumberOfIslandsII {
		private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    public List<Integer> numIslands2(int m, int n, int[][] positions) {
	        List<Integer> results = new ArrayList<>();
	        int[][] table = new int[m][];
	        for (int r = 0; r < m; table[r++] = new int[n]);
	        Map<Integer, Set<Integer>> replaceOlds = new HashMap<>();
	        Map<Integer, Integer> oldReplace = new HashMap<>();
	        for (int i = 0; i < positions.length; i++) {
	            int[] pos = positions[i];
	            int r = pos[0], c = pos[1];
	            int id = i + 1;
	            table[r][c] = id;
	            replaceOlds.put(id, new HashSet<>());
	            Set<Integer> nbs = new HashSet<>();
	            for (int[] dir : dirs) {
	                int r1 = r + dir[0], c1 = c + dir[1];
	                if (r1 >= 0 && r1 < m && c1 >= 0 && c1 < n && table[r1][c1] > 0)
	                    nbs.add(table[r1][c1]);
	            }
	            // replace neighbor and values that have been replaced by neighbor value;
	            // replace not the neighbor, but its replacement.
	            Set<Integer> replaced = new HashSet<>();
	            replaced.addAll(nbs);
	            for (int nb : nbs) {
	                Set<Integer> olds = replaceOlds.remove(nb);
	                if (olds != null)
	                	replaced.addAll(olds);
	            }
	            for (Integer rp : replaced) {
	            	oldReplace.put(rp, id);
	            	replaceOlds.get(id).add(rp);
	            }
	            results.add(replaceOlds.size());
	        }
	        return results;
	    }
	}
	
	
	// Solution III: Logic Error
	static class SolutionIII extends NumberOfIslandsII {
		private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    public List<Integer> numIslands2(int m, int n, int[][] positions) {
	        List<Integer> results = new ArrayList<>();
	        int[][] table = new int[m][];
	        for (int r = 0; r < m; table[r++] = new int[n]);
	        Map<Integer, Set<Integer>> replaceOlds = new HashMap<>();
	        Map<Integer, Integer> oldReplace = new HashMap<>();
	        for (int i = 0; i < positions.length; i++) {
	            int[] pos = positions[i];
	            int r = pos[0], c = pos[1];
	            int id = i + 1;
	            table[r][c] = id;
	            replaceOlds.put(id, new HashSet<>());
	            Set<Integer> nbs = new HashSet<>();
	            for (int[] dir : dirs) {
	                int r1 = r + dir[0], c1 = c + dir[1];
	                if (r1 >= 0 && r1 < m && c1 >= 0 && c1 < n && table[r1][c1] > 0)
	                    nbs.add(table[r1][c1]);
	            }
	            // replace neighbor and values that have been replaced by neighbor value;
	            // replace not the neighbor, but its replacement.
	            Set<Integer> replaced = new HashSet<>();
	            replaced.addAll(nbs);
	            for (int nb : nbs) {
	                Set<Integer> olds = replaceOlds.remove(nb);
	                if (olds != null)
	                	replaced.addAll(olds);
	            }
	            for (Integer rp : replaced) {
	            	oldReplace.put(rp, id);
	            	replaceOlds.get(id).add(rp);
	            }
	            results.add(replaceOlds.size());
	        }
	        return results;
	    }
	}
	
	
	// Solution II: Logic Error
	static class SolutionII extends NumberOfIslandsII {
		private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    public List<Integer> numIslands2(int m, int n, int[][] positions) {
	        List<Integer> results = new ArrayList<>();
	        int[][] table = new int[m][];
	        for (int r = 0; r < m; table[r++] = new int[n]);
	        Set<Integer> ids = new HashSet<>();
	        for (int i = 0; i < positions.length; i++) {
	            int[] pos = positions[i];
	            int r = pos[0], c = pos[1];
	            int id = i + 1;
	            table[r][c] = id;
	            ids.add(id);
	            for (int[] dir : dirs) {
	                int r1 = r + dir[0], c1 = c + dir[1];
	                if (r1 >= 0 && r1 < m && c1 >= 0 && c1 < n)
	                    ids.remove(table[r1][c1]);
	            }
	            results.add(ids.size());
	        }
	        return results;
	    }
	}
	
	
	// Solution I: Accepted
	// for each new position, find adjacent non-0s and flip them all, using DFS.
	static class SolutionI extends NumberOfIslandsII {
		private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    public List<Integer> numIslands2(int m, int n, int[][] positions) {
	        List<Integer> results = new ArrayList<>();
	        int count = 0;
	        int[][] table = new int[m][];
	        for (int r = 0; r < m; table[r++] = new int[n]);
	        for (int i = 0; i < positions.length; i++) {
	            int[] p = positions[i];
	            int r = p[0], c = p[1];
	            Set<Integer> isls = new HashSet<>(); // ids of connected isls
	            List<int[]> islPs = new ArrayList<>(); // 1 cell of each connected isl
	            for (int[] dir : dirs) {
	                int r1 = r + dir[0], c1 = c + dir[1];
	                if (r1 >= 0 && r1 < m && c1 >= 0 && c1 < n && table[r1][c1] > 0) {
	                    if (isls.add(table[r1][c1]))
	                        islPs.add(new int[]{r1, c1});
	                }
	            }
	            if (isls.isEmpty()) {
	                count++;
	                table[r][c] = i + 1;
	            } else if (isls.size() == 1) {
	                table[r][c] = isls.iterator().next();
	            } else if (isls.size() > 1) {
	                count -= isls.size() - 1;
	                int id = isls.iterator().next();
	                table[r][c] = id;
	                for (int[] islP : islPs) {
	                    int r2 = islP[0], c2 = islP[1];
	                    if (table[r2][c2] != id)
	                        flip(table, m, n, r2, c2, id);
	                }
	            }
	            results.add(count);
	        }
	        return results;
	    }
	    
	    // bfs flip islands
	    private void flip(int[][] table, int m, int n, int r, int c, int id) {
	        List<int[]> ps = new ArrayList<>();
	        ps.add(new int[]{r, c});
	        table[r][c] = id;
	        while (!ps.isEmpty()) {
	            List<int[]> nextPs = new ArrayList<>();
	            for (int[] p : ps) {
	                for (int[] dir : dirs) {
	                    int r1 = p[0] + dir[0], c1 = p[1] + dir[1];
	                    if (r1 >= 0 && r1 < m && c1 >= 0 && c1 < n && table[r1][c1] != 0 && table[r1][c1] != id) {
	                        table[r1][c1] = id;
	                        nextPs.add(new int[]{r1, c1});
	                    }
	                }
	            }
	            ps = nextPs;
	        }
	    }
	}
}
