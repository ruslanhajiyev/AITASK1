import java.io.*;
import java.util.*;

/*
Graph parser and representation.

Input format:
 - Ignore blank lines and lines starting with '#'
 - Lines can appear in any order:
   Vertex: "<id>,<cell_id>"        (one comma)
   Edge:   "<u>,<v>,<w>"           (two commas)
   Source: "S,<id>"
   Dest:   "D,<id>"

cell_id encodes coordinates:
  x = cell_id / 10
  y = cell_id % 10
*/
public class Graph {

    // Parse graph from file
    public static Types.Graph parseGraph(String filename) throws IOException {
        Types.Graph graph = new Types.Graph();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String raw;
        while ((raw = br.readLine()) != null) {
            String line = raw.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;

            // Source line
            if (line.startsWith("S")) {
                // allow "S,1" or "S, 1"
                String[] tok = line.split(",");
                if (tok.length >= 2) {
                    try {
                        graph.source = Integer.parseInt(tok[1].trim());
                    } catch (NumberFormatException ignored) {}
                }
                continue;
            }

            // Destination line
            if (line.startsWith("D")) {
                String[] tok = line.split(",");
                if (tok.length >= 2) {
                    try {
                        graph.destination = Integer.parseInt(tok[1].trim());
                    } catch (NumberFormatException ignored) {}
                }
                continue;
            }

            // Count commas to decide if vertex or edge
            long commas = line.chars().filter(ch -> ch == ',').count();
            String[] parts = line.split(",");
            if (commas == 1 && parts.length >= 2) {
                // Vertex: id,cell
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    int cell = Integer.parseInt(parts[1].trim());
                    Types.Node n = new Types.Node(id, cell / 10, cell % 10);
                    graph.nodes.put(id, n);
                } catch (NumberFormatException ignored) {}
            } else if (commas >= 2 && parts.length >= 3) {
                // Edge: u,v,w
                try {
                    int u = Integer.parseInt(parts[0].trim());
                    int v = Integer.parseInt(parts[1].trim());
                    double w = Double.parseDouble(parts[2].trim());
                    // undirected
                    graph.adj.computeIfAbsent(u, k -> new ArrayList<>()).add(new Types.Edge(v, w));
                    graph.adj.computeIfAbsent(v, k -> new ArrayList<>()).add(new Types.Edge(u, w));
                } catch (NumberFormatException ignored) {}
            }
        }
        br.close();
        return graph;
    }
}