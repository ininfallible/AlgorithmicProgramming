# Graph - set of nodes joined by edges
nodes-what is there to say
edges-relationship between two nodes
Trees-N nodes joined by N-1 Edges
	- Root - Ancestor of all nodes
	- Leaf - other nodes
Directed/Undirected
	- Undirected - All nodes are bidirectional
	- Directed - edges can be one-way
weighting
	- Edges can be assigned weights, often as cost
Cyclic
	- If there is more than one path from A to B
	- Trees are not cyclic
	
# Representation of a graph
Adjacency matrix - A VxV matrix
	- A[i][j] represents 0 if no edge, else edge or edge with weight
	- Query if two nodes are adjacent in O(1)
	- Can represent directed graphs
	- Searches take a long time bc querying edges takes time
Adjacency list
	- More space efficient, easier to search on
	- adj[i] returns a vector with all linked nodes
	- vector<int> adj;
	- Addding edge: adj[i].pb(j);
	- Can also store pairs or structs for edges with data

# Algorithms for graphs
BFS - Breadth First Search
	- visit every node one in a specific order
	pseudocode:
	while tp not empty
		cur = tp.front, erase tp.front();
		visited[cur] = true;
		process cur;
		for all next in neighbors[cur]:
			if !visited[next]
				tP.push(next);

DFS - Depth First search
	- Recursive search
	- func calls func on all its children
		- those need to be resolved
	- Process cur
	- Call func on unvisited neighbors - those need to finish. 
	- be careful with cyclic

Dijkstra - shortest path
	- Becareful about negative cycles

Floyd Warshall - SPF all vertices
	- Works for positive and negative edges
	- V^3 complexity - slow

# Queues can often be represented by sets - sets are naturally ordered
