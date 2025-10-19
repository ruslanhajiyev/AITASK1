```markdown
A* (Three Modes) — Java implementation

Files:
- AStarProgram.java   — main program (A* algorithm and runner)
- Graph.java          — parser and graph construction
- Heuristics.java     — heuristic implementations (zero, Euclidean, Manhattan)
- Types.java          — Node, Edge, Graph, PathResult definitions
- astar_small.txt     — small test graph
- astar_medium.txt    — medium test graph

Build:
1) Compile:
   javac AStarProgram.java Graph.java Heuristics.java Types.java

2) Run:
   java AStarProgram astar_small.txt
   java AStarProgram astar_medium.txt

Behavior and implementation notes:
- Priority is f = g + h. The frontier (priority queue) orders by f first, then node id for deterministic tie-breaking.
- Duplicate frontier entries are allowed. When an entry is popped, it is only expanded if its g equals the best-known g for that node (prevents expanding stale entries).
- Heuristics are "pluggable" via the Heuristic functional interface.
- Stats gathered and printed per run:
  Expanded (number of pops actually expanded),
  Pushes (number of pushes into the priority queue),
  Max frontier (peak queue size),
  Runtime (wall-clock seconds, measured with System.nanoTime()).

Input format:
- Ignore blank lines and lines starting with #
- Lines can appear in any order and are one of:
  Vertex: <id>,<cell_id>
  Edge: <u>,<v>,<w>
  Source: S,<id>
  Destination: D,<id>

Coordinates:
- cell_id encodes grid coordinates:
  x = cell_id / 10
  y = cell_id % 10

Notes on correctness & analysis (to include in your write-up):
- If heuristics are admissible and consistent, all modes should return the same optimal cost.
- Expect search effort ordering UCS (h=0) >= A* Euclidean >= A* Manhattan when heuristics are admissible and Manhattan dominates Euclidean pointwise.
- Verify heuristic admissibility on your graphs: for every edge (u,v) with weight w, must have w >= h(u,v) for admissibility in this formulation.
```