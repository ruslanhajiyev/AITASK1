import java.util.*;

/*
Shared small types used by the program
*/

public class Types {
    public static class Node {
        public int id;
        public int x;
        public int y;
        public Node(int id, int x, int y) { this.id = id; this.x = x; this.y = y; }
    }

    public static class Edge {
        public int to;
        public double weight;
        public Edge(int to, double weight) { this.to = to; this.weight = weight; }
    }

    public static class Graph {
        public Map<Integer, Node> nodes = new HashMap<>();
        public Map<Integer, List<Edge>> adj = new HashMap<>();
        public int source = -1;
        public int destination = -1;
    }

    public static class PathResult {
        public boolean found;
        public double cost;
        public List<Integer> path;
        public int expanded;
        public int pushes;
        public int maxFrontier;
        public double runtime_s;

        public PathResult(boolean found, double cost, List<Integer> path,
                          int expanded, int pushes, int maxFrontier, double runtime_s) {
            this.found = found;
            this.cost = cost;
            this.path = path;
            this.expanded = expanded;
            this.pushes = pushes;
            this.maxFrontier = maxFrontier;
            this.runtime_s = runtime_s;
        }
    }
}