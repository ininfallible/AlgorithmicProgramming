*see marathon.cpp (gold)*
*https://www.geeksforgeeks.org/segment-tree-efficient-implementation/*
*https://en.wikipedia.org/wiki/Segment_tree*

# What is a segment tree
- stores information about intervals
- used for range queries, update the tree quickly
- handles these in log time

# concept
Each node represents an interval
- number attached can be something like sum of terms
- Root would be the whole thing
- By moving down the tree you get a specific query
#query
Queries run in O(log N), where N is number of nodes
- This is because the tree makes the query a binary search
	- You start with a larger interval and narrow it down

	- qa and qb are basically the ends of the interval
	- if a > qb || b < qa:terminate
	- if a >= qa || b <= qb: base case
	- Move down tree
		- recurse for upper and lower halves
		- if sum: add
		- if max: max(first half, second half)

#update
Updates also run in O(log N)
- when a node updates, updates need to travel from larger to smaller (log N ops)
	- update from the top of the tree and work downwards
	- how this works (in marathongold)
	- qa and qb bound search (a is lower of interval, b is upper)

	- if a > qb || b < qa: terminate
	- if a >= qa || b <= qb: base case
	- Moving down the tree:
		- (if we reach this point we are not a base case)
		- recurse for upper and lower halves
		- update active index based on recursed results
# Errata
- due to array based implementation
- structure of tree
- (in marathon) rt changes based on tree layer 
	- rt\* 2 = go down left, +1 = go down right
Below: no. = level of tree, describes space taken
 1 2  3    4
|-|--|----|--------|
