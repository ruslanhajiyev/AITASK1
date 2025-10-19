/*
Heuristic implementations (pluggable)
Each heuristic has signature: double h(Node a, Node b)
*/

public class Heuristics {

    public static double hZero(Types.Node a, Types.Node b) {
        return 0.0;
    }

    public static double hEuclidean(Types.Node a, Types.Node b) {
        double dx = (double)(a.x - b.x);
        double dy = (double)(a.y - b.y);
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double hManhattan(Types.Node a, Types.Node b) {
        return (double)(Math.abs(a.x - b.x) + Math.abs(a.y - b.y));
    }
}