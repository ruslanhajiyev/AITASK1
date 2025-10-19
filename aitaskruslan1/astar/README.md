# A* Search (Three Modes) — Java Implementation

This project implements the A* search algorithm in Java and demonstrates it in three different modes:
- **UCS** (Uniform Cost Search, where h = 0)
- **A\*** with the **Euclidean** heuristic
- **A\*** with the **Manhattan** heuristic

---

## 📁 Files Included

- **AStarProgram.java** — main program that runs the A* algorithm
- **Graph.java** — handles input parsing and graph construction
- **Heuristics.java** — contains the heuristic functions (Zero, Euclidean, Manhattan)
- **Types.java** — defines the Node, Edge, Graph, and PathResult classes
- **astar_small.txt** — small test graph for basic validation
- **astar_medium.txt** — medium-sized test graph (around 30 nodes)

---

## ⚙️ How to Build and Run

### 1. Compile the program:
```bash
javac AStarProgram.java Graph.java Heuristics.java Types.java
