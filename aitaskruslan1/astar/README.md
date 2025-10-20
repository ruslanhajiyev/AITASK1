## ⚙️ How to Run
- inorder to run go to AStarProgram.java and run it in Intellij IDEA
-if you want to run astar_small.txt inside AStarProgram.java file  String filename = "aitaskruslan1/astar/astar_medium.txt"; change astar_medium.xt to astar_small.txt
# A* Search (Three Modes) — Java Implementation

This project implements the A* search algorithm in Java and demonstrates it sin three different modes:
- **UCS** (Uniform Cost Search, where h = 0)
- **A\*** with the **Euclidean** heuristics
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

# 2. Q&A
- Do all three modes return the same cost?
- YES
# astar_small.txt:

~~~
MODE: UCS (h=0)
Optimal cost: 14
Path: 1 -> 2 -> 3 -> 4
Expanded: 5
Pushes: 5
Max frontier: 2
Runtime (s): 0.001771

MODE: A* Euclidean
Optimal cost: 14
Path: 1 -> 2 -> 3 -> 4
Expanded: 4
Pushes: 5
Max frontier: 2
Runtime (s): 0.000033

MODE: A* Manhattan
Optimal cost: 14
Path: 1 -> 2 -> 3 -> 4
Expanded: 4
Pushes: 5
Max frontier: 2
Runtime (s): 0.000020
~~~

# astar_medium.txt:

~~~
MODE: UCS (h=0)
Optimal cost: 17
Path: 1 -> 6 -> 7 -> 12 -> 13 -> 14 -> 19 -> 24 -> 25 -> 30
Expanded: 30
Pushes: 34
Max frontier: 8
Runtime (s): 0.001188

MODE: A* Euclidean
Optimal cost: 17
Path: 1 -> 6 -> 7 -> 12 -> 13 -> 14 -> 19 -> 24 -> 25 -> 30
Expanded: 30
Pushes: 32
Max frontier: 10
Runtime (s): 0.000140

MODE: A* Manhattan
Optimal cost: 17
Path: 1 -> 6 -> 7 -> 12 -> 13 -> 14 -> 19 -> 24 -> 25 -> 30
Expanded: 30
Pushes: 34
Max frontier: 10
Runtime (s): 0.000077

~~~


