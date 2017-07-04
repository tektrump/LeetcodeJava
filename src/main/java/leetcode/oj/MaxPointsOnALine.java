package leetcode.oj;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class MaxPointsOnALine {
	public abstract int maxPoints(Point[] points);
	static class Point {
		int x, y;
		Point() {
			x = 0;
			y = 0;
		}
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "[" + x + "," + y + "]";
		}
	}
	
	public static void main(String[] args) {
		MaxPointsOnALine instance = new SolutionIII();
		Point[] points;
		int res;
		
		points = new Point[]{new Point(0, 0), new Point(-1, -1), new Point(2, 2)};
		
		res = instance.maxPoints(points);
		System.out.println("result=" + res);
	}
	
	
	static class SolutionIII extends MaxPointsOnALine {
		public int maxPoints(Point[] points) {
	        if (points.length == 0)
	            return 0;
	        int ret = 0;
	        
	        for (int i = 0; i < points.length; i++) {
	            int dups = 0, max = 0;
	            Map<Double, Integer> slopeCount = new HashMap<>();
	            for (int j = i+1; j < points.length; j++) {
	                Point p1 = points[i], p2 = points[j];
System.out.println("p1=" + p1 + ", p2=" + p2);
	                int x1 = p1.x, y1 = p1.y;
	                int x2 = p2.x, y2 = p2.y;
	                if (x1 == x2 && y1 == y2) {
	                    dups++;
	                } else {
	                    double slope = Double.POSITIVE_INFINITY;
	                    int dx = x1 - x2, dy = y1 - y2;
	                    if (dx != 0) {
	                        if (dy == 0)
	                            slope = 0.0;
	                        else
	                            slope = 1.0*dy/dx;
	                    }
	                    Integer count = slopeCount.get(slope);
	                    if (count == null)
	                        count = 0;

	                    slopeCount.put(slope, count+1);
	                    max = Math.max(max, count+1);
System.out.println("count=" + (count + 1) + ", dups=" + dups);
	                }
	            }
	            ret = Math.max(ret, 1 + max + dups);
	        }
	        
	        return ret;
	    }
	}
	
	
	// Solution II: Accepted
	class SolutionII extends MaxPointsOnALine {
		public int maxPoints(Point[] points) {
	        int max = 0;
	        // point, and slope already checked on this point
	        Map<Point, Set<Double>> pointSlopes = new HashMap<>();
	        for (Point p : points) {
	            int dups = 0;
	            // slopes already checked for p
	            Set<Double> slopes = pointSlopes.get(p);
	            Map<Double, Set<Point>> slopePoints = new HashMap<>();
	            for (Point p1 : points) {
	                if (p.x == p1.x && p.y == p1.y)
	                    dups++;
	                else {
	                    // SYNTAX: if we use Double, then we can't do slope=0 (cannot convert int to Double)!
	                    // we can either use double or we do slope=0.0
	                    double slope = Double.POSITIVE_INFINITY; 
	                    if (p.x != p1.x) {
	                        if (p.y == p1.y)
	                            slope = 0;
	                        else
	                            slope = 1.0*(p.y - p1.y)/(p.x - p1.x); // must use 1.0 to convert to double
	                    }
	                    if (slopes == null || !slopes.contains(slope)) {
	                        Set<Point> pset = slopePoints.get(slope);
	                        if (pset == null) {
	                            pset = new HashSet<>();
	                            slopePoints.put(slope, pset);
	                        }
	                        pset.add(p1);
	                    }
	                }
	            }
	            max = Math.max(max, dups); // check separately, as map slopePoints could be empty
	            for (Map.Entry<Double, Set<Point>> entry : slopePoints.entrySet()) {
	                Double slope = entry.getKey();
	                Set<Point> p1s = entry.getValue();
	                max = Math.max(max, p1s.size() + dups);
	                for (Point p1 : p1s) {
	                    Set<Double> slopeSet = pointSlopes.get(p1);
	                    if (slopeSet == null) {
	                        slopeSet = new HashSet<>();
	                        pointSlopes.put(p1, slopeSet);
	                    }
	                    slopeSet.add(slope);
	                }
	            }
	        }
	        
	        return max;
		}
	}
	
	
	// Solution I: Accepted
	class SolutionI extends MaxPointsOnALine {
		public int maxPoints(Point[] points) {
	        int max = 0;
	        for (Point p : points) {
	            int dups = 0;
	            Map<Double, Integer> slopeCount = new HashMap<>();
	            for (Point p1 : points) {
	                if (p.x == p1.x && p.y == p1.y)
	                    dups++;
	                else {
	                    double slope = Double.POSITIVE_INFINITY; // SYNTAX
	                    if (p.x != p1.x) {
	                        if (p.y == p1.y)
	                            slope = 0;
	                        else
	                            slope = 1.0*(p.y - p1.y)/(p.x - p1.x);
	                    }
	                    Integer count = slopeCount.get(slope);
	                    if (count == null)
	                        count = 0;
	                    slopeCount.put(slope, count+1);
	                }
	            }
	            max = Math.max(max, dups);
	            for (int count : slopeCount.values())
	                max = Math.max(max, count + dups);
	        }
	        
	        return max;
	    }
	}
}
