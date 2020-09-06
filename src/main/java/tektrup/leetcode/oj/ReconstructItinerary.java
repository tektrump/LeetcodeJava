package tektrup.leetcode.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public abstract class ReconstructItinerary {
	public abstract List<String> findItinerary(String[][] tickets);
	public static void main(String[] args) {
		ReconstructItinerary instance = new SolutionV();
		String[][] tickets;
		List<String> results;
		
		// [JFK, MUC, LHR, SFO, SJC]
//		tickets = new String[][]{{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
		
		// ["JFK","ATL","JFK","SFO","ATL","SFO"]
		tickets = new String[][]{{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
		
//		tickets = new String[][]{{"EZE","AXA"},{"TIA","ANU"},{"ANU","JFK"},{"JFK","ANU"},{"ANU","EZE"},{"TIA","ANU"},{"AXA","TIA"},{"TIA","JFK"},{"ANU","TIA"},{"JFK","TIA"}};
		
		results = instance.findItinerary(tickets);
		System.out.println("results=" + results);
	}
	
	
	// Solution V: Accepted
	// backtracking; change from solution VI: track number of edges used using a set.
	static class SolutionV extends ReconstructItinerary {
		public List<String> findItinerary(String[][] tickets) {
	        Map<String, List<String>> srcDests = new HashMap<>();
	        Map<List<String>, Integer> edgeCount = new HashMap<>();
	        for (String[] t : tickets) {
	            String src = t[0], dest = t[1];
	            List<String> dests = srcDests.get(src);
	            if (dests == null) {
	                dests = new LinkedList<>();
	                srcDests.put(src, dests);
	            }
	            dests.add(dest);
	            List<String> edge = Arrays.asList(src, dest);
	            Integer count = edgeCount.get(edge);
	            if (count == null)
	                count = 0;
	            edgeCount.put(edge, count+1);
	        }
	        for (Map.Entry<String, List<String>> entry : srcDests.entrySet())
	            Collections.sort(entry.getValue());
	        List<String> rets = new LinkedList<>();
	        rets.add("JFK");
	        dfs(rets, srcDests, edgeCount, tickets.length+1);
	        
	        return rets;
	    }
	    
	    private boolean dfs(List<String> rets, Map<String, List<String>> srcDests, Map<List<String>, Integer> edgeCount, int len) {
	        if (rets.size() == len)
	            return true;
	        String src = rets.get(rets.size()-1);
	        List<String> dests = srcDests.get(src);
	        if (dests != null) {
	            for (String dest : dests) {
	                List<String> edge = Arrays.asList(src, dest);
	                Integer count = edgeCount.get(edge);
	                if (count > 0) {
	                    edgeCount.put(edge, count-1); // modify
	                    rets.add(dest); // modify
	                    if (dfs(rets, srcDests, edgeCount, len))
	                        return true; // greedy return
	                    edgeCount.put(edge, count); // restore
	                    rets.remove(rets.size()-1); // restore
	                }
	            }
	        }
	        
	        return false;
	    }
	}
	
	
	// Solution III: Accepted 
	// dfs with backtracking
    // for each vertex, its neighbors are sorted and we shall try each neighbor in sequence;
	// the logic to pick a neighbor and backtrack is not efficient (or maybe wrong).
	static class SolutionIV extends ReconstructItinerary {
		public List<String> findItinerary(String[][] tickets) {
	        Map<String, List<String>> nodeNbs = new HashMap<>();
	        for (String[] ticket : tickets) {
	            String node = ticket[0], nb = ticket[1];
	            List<String> nbs = nodeNbs.get(node);
	            if (nbs == null) {
	                nbs = new LinkedList<>();
	                nodeNbs.put(node, nbs);
	            }
	            nbs.add(nb);
	        }
	        for (List<String> nbs : nodeNbs.values()) {
	            Collections.sort(nbs);
	        }
	        List<String> result = new LinkedList<>();
	        result.add("JFK");
	        bt("JFK", nodeNbs, result);
	        return result;
	    }
	    
	    private boolean bt(String node, Map<String, List<String>> nodeNbs, List<String> result) {
	        // termination
	        if (nodeNbs.isEmpty())
	            return true;
	        List<String> nbs = nodeNbs.get(node);
	        if (nbs == null)
	            return false;
	        List<String> copy = new LinkedList<>(nbs); // NOTE: create a copy of list, unfortunately
	        for (String nb : copy) {
	            result.add(nb); // modify
	            nbs.remove(nb); // modify   // NOTE: removing from linkedlist takes O(n); tradeoff
	            if (nbs.isEmpty())
	                nodeNbs.remove(node);
	            if (bt(nb, nodeNbs, result))
	                return true;
	            result.remove(result.size()-1); // restore
	            nbs.add(nb);    // restore
	            if (nbs.size() == 1)
	                nodeNbs.put(node, nbs);
	        }
	        return false;
	    }
	}
	
	
	// Solution II: TLE
	static class SolutionIII extends ReconstructItinerary {
		public List<String> findItinerary(String[][] tickets) {
	        List<List<String>> results = new ArrayList<>();
	        Map<String, List<String>> nodeNbs = new HashMap<>();
	        for (String[] ticket : tickets) {
	            String node = ticket[0], nb = ticket[1];
	            add(node, nb, nodeNbs);
	        }
	        
	        List<List<String>> paths = new ArrayList<>();
	        List<String> path0 = new ArrayList<>();
	        path0.add("JFK");
	        paths.add(path0);
	        List<Set<String>> visitedList = new ArrayList<>();
	        Set<String> visited0 = new HashSet<>();
	        visited0.add("JFK");
	        visitedList.add(visited0);
	        while (!paths.isEmpty()) {
	            List<List<String>> nextPaths = new ArrayList<>();
	            List<Set<String>> nextVisitedList = new ArrayList<>();
	            for (int i = 0; i < paths.size(); i++) {
	                List<String> path = paths.get(i);
	                Set<String> visited = visitedList.get(i);
	                String last = path.get(path.size() - 1);
	                List<String> nbs = nodeNbs.get(last);
	                if (nbs != null) {
	                    for (String nb : nbs) {
	                        if (!visited.contains(nb)) {
	                            List<String> nextPath = new ArrayList<>(path);
	                            nextPath.add(nb);
	                            if (nextPath.size() == tickets.length + 1) {
	                                results.add(nextPath);
	                                continue;    
	                            }
	                            nextPaths.add(nextPath);
	                            Set<String> nextVisited = new HashSet<>(visited);
	                            nextVisited.add(nb);
	                            nextVisitedList.add(nextVisited);
	                        }
	                    }
	                }
	            }
	            paths = nextPaths;
	            visitedList = nextVisitedList;
	        }
	        
	        Comparator<List<String>> comp = new Comparator<List<String>> () {
	            @Override
	            public int compare(List<String> l1, List<String> l2) {
	                for (int i = 0; i < l1.size(); i++) {
	                    int diff = l1.get(i).compareTo(l2.get(i));
	                    if (diff != 0)
	                        return diff;
	                }
	                return 0;
	            }
	        };
	        PriorityQueue<List<String>> q = new PriorityQueue<>(comp);
	        for (List<String> result : results)
	            q.add(result);
	        return q.poll();
	    }
	    
	    private void add(String node, String nb, Map<String, List<String>> map) {
	        List<String> nbs = map.get(node);
	        if (nbs == null) {
	            nbs = new LinkedList<>();
	            map.put(node, nbs);
	        }
	        nbs.add(nb);
	    }
	}
	
	
	// Solution I: TLE
	static class SolutionII extends ReconstructItinerary {
		public List<String> findItinerary(String[][] tickets) {
	        List<List<String>> results = new ArrayList<>();
	        Map<String, Set<String>> nodeNbs = new HashMap<>();
	        for (String[] ticket : tickets) {
	            String node = ticket[0], nb = ticket[1];
	            add(node, nb, nodeNbs);
	        }
	        
	        List<String> path = new ArrayList<>();
	        path.add("JFK");
	        dfs("JFK", path, tickets.length+1, nodeNbs, results);
	        Comparator<List<String>> comp = new Comparator<List<String>> () {
	            @Override
	            public int compare(List<String> l1, List<String> l2) {
	                for (int i = 0; i < l1.size(); i++) {
	                    int diff = l1.get(i).compareTo(l2.get(i));
	                    if (diff != 0)
	                        return diff;
	                }
	                return 0;
	            }
	        };
	        PriorityQueue<List<String>> q = new PriorityQueue<>(comp);
	        for (List<String> result : results)
	            q.add(result);
	        return q.poll();
	    }
	    
	    private void add(String node, String nb, Map<String, Set<String>> map) {
	        Set<String> nbs = map.get(node);
	        if (nbs == null) {
	            nbs = new HashSet<>();
	            map.put(node, nbs);
	        }
	        nbs.add(nb);
	    }
	    
	    private void dfs(String node, List<String> path, int len, Map<String, Set<String>> nodeNbs, 
	        List<List<String>> results) {
	        // termination
	        if (path.size() == len)
	            results.add(path);
	        else {
	            Set<String> nbs = nodeNbs.get(node);
	            if (nbs != null && !nbs.isEmpty()) {
	                Set<String> nbs2 = new HashSet<>(nbs);
	                for (String nb : nbs2) {
	                    // modify
	                    nbs.remove(nb);
	                    path.add(nb);
	                    dfs(nb, path, len, nodeNbs, results);
	                    // restore
	                    nbs.add(nb);
	                    path.remove(path.size()-1);
	                }
	            }
	        }
	    }
	}
}
