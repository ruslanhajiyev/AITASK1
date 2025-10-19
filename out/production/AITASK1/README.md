# A* Search (Three Modes) — Java Implementation

This project implements the A* search algorithm in Java and demonstrates it in three modes:
- **UCS** (Uniform Cost Search, where h = 0)
- **A\*** using the **Euclidean** heuristic
- **A\*** using the **Manhattan** heuristic

---

## 📂 Files Included

- **AStarProgram.java** — main program that runs the A* algorithm
- **Graph.java** — handles reading input files and building the graph
- **Heuristics.java** — contains heuristic functions (Zero, Euclidean, Manhattan)
- **Types.java** — defines the data structures for Node, Edge, Graph, and PathResult
- **astar_small.txt** — small test graph (about 5 nodes)
- **astar_medium.txt** — medium test graph (around 30 nodes)

---

## ⚙️ How to Build and Run

### 1. Compile the code:
```bash
javac AStarProgram.java Graph.java Heuristics.java Types.java
