/*
A* (three modes) - Java implementation

Usage:
  javac AStarProgram.java Graph.java Heuristics.java Types.java
  java AStarProgram <input_file>

This program runs three modes on the same graph:
 - UCS (h = 0)
 - A* with Euclidean heuristic
 - A* with Manhattan heuristic

It prints for each mode:
 MODE: <...>
 Optimal cost: <number | NO PATH>
 Path: <S -> ... -> D>   (omitted when NO PATH)
 Expanded: <int>
 Pushes: <int>
 Max frontier: <int>
 Runtime (s): <float>
*/

import java.io.*;
import java.util.*;
import java.lang.*;

public class AStarProgram {

    // Frontier entry with deterministic tie-breaking
    private static class PQEntry {
        int id;
        double f;
        double g;
        PQEntry(int id, double f, double g) { this.id = id; this.f = f; this.g = g; }
    }

    // Functional interface for heuristics
    public interface Heuristic {
        double h(Types.Node a, Types.Node b);
    }

    public static Types.PathResult astar(Types.Graph graph, int start, int goal, Heuristic heuristic) {
        long t0 = System.nanoTime();

        // g-costs initialized to +inf
        Map<Integer, Double> gCost = new HashMap<>();
        for (Integer nid : graph.nodes.keySet()) gCost.put(nid, Double.POSITIVE_INFINITY);
        gCost.put(start, 0.0);

        Map<Integer, Integer> parent = new HashMap<>();

        // PriorityQueue: compare by f then by node id (deterministic)
        PriorityQueue<PQEntry> pq = new PriorityQueue<>(new Comparator<PQEntry>() {
            @Override
            public int compare(PQEntry a, PQEntry b) {
                int cmp = Double.compare(a.f, b.f);
                if (cmp != 0) return cmp;
                return Integer.compare(a.id, b.id);
            }
        });

        // push start
        pq.add(new PQEntry(start, heuristic.h(graph.nodes.get(start), graph.nodes.get(goal)), 0.0));
        int pushes = 1;
        int expanded = 0;
        int maxFrontier = Math.max(1, pq.size());

        // Special case: if start or goal missing in graph, immediate NO PATH
        if (!graph.nodes.containsKey(start) || !graph.nodes.containsKey(goal)) {
            double runtime = (System.nanoTime() - t0) / 1e9;
            return new Types.PathResult(false, 0.0, Collections.emptyList(), expanded, pushes, maxFrontier, runtime);
        }

        while (!pq.isEmpty()) {
            PQEntry cur = pq.poll();

            // stale entry check: expand only if popped g equals best-known g
            double bestG = gCost.getOrDefault(cur.id, Double.POSITIVE_INFINITY);
            if (cur.g > bestG) continue;

            expanded++;

            if (cur.id == goal) {
                double runtime = (System.nanoTime() - t0) / 1e9;
                // reconstruct path
                List<Integer> path = new ArrayList<>();
                int node = goal;
                while (node != start) {
                    path.add(node);
                    node = parent.get(node);
                }
                path.add(start);
                Collections.reverse(path);
                return new Types.PathResult(true, gCost.get(goal), path, expanded, pushes, maxFrontier, runtime);
            }

            List<Types.Edge> neighbors = graph.adj.get(cur.id);
            if (neighbors != null) {
                for (Types.Edge e : neighbors) {
                    double tentative = cur.g + e.weight;
                    double prev = gCost.getOrDefault(e.to, Double.POSITIVE_INFINITY);
                    if (tentative < prev) {
                        gCost.put(e.to, tentative);
                        parent.put(e.to, cur.id);
                        double f = tentative + heuristic.h(graph.nodes.get(e.to), graph.nodes.get(goal));
                        pq.add(new PQEntry(e.to, f, tentative));
                        pushes++;
                    }
                }
            }

            if (pq.size() > maxFrontier) maxFrontier = pq.size();
        }

        double runtime = (System.nanoTime() - t0) / 1e9;
        return new Types.PathResult(false, 0.0, Collections.emptyList(), expanded, pushes, maxFrontier, runtime);
    }

    private static void printResult(String mode, Types.PathResult res) {
        System.out.println("MODE: " + mode);
        if (!res.found) {
            System.out.println("Optimal cost: NO PATH");
        } else {
            System.out.println("Optimal cost: " + (res.cost % 1.0 == 0.0 ? String.format("%.0f", res.cost) : String.format("%f", res.cost)));
            System.out.print("Path: ");
            for (int i = 0; i < res.path.size(); ++i) {
                System.out.print(res.path.get(i));
                if (i + 1 < res.path.size()) System.out.print(" -> ");
            }
            System.out.println();
        }
        System.out.println("Expanded: " + res.expanded);
        System.out.println("Pushes: " + res.pushes);
        System.out.println("Max frontier: " + res.maxFrontier);
        System.out.printf(Locale.ROOT, "Runtime (s): %.6f%n%n", res.runtime_s);
    }

    public static void main(String[] args) {

        String filename = "aitaskruslan1/astar/astar_medium.txt";

        Types.Graph g;
        try {
            g = Graph.parseGraph(filename);
        } catch (IOException e) {
            System.err.println("Failed to read graph file: " + e.getMessage());
            return;
        }

        // Heuristics
        Heuristic hZero = Heuristics::hZero;
        Heuristic hEuclid = Heuristics::hEuclidean;
        Heuristic hMan = Heuristics::hManhattan;

        Types.PathResult r1 = astar(g, g.source, g.destination, hZero);
        printResult("UCS (h=0)", r1);

        Types.PathResult r2 = astar(g, g.source, g.destination, hEuclid);
        printResult("A* Euclidean", r2);

        Types.PathResult r3 = astar(g, g.source, g.destination, hMan);
        printResult("A* Manhattan", r3);
    }
}