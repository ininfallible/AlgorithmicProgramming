//2019 Dec Gold P1
//implementing solution
#include <bits/stdc++.h>

using namespace std;

const int MAX = 1001;

int N, M;

map<int, vector<int>> nbrs;
map<pair<int, int>, double> edgecost;
map<pair<int, int>, double> edgeflow;
vector<int> flows;

int dijikstra (int source, int dest, int flowmin)
{
	//dist to all other nodes
	map<int, int> dist;
	//to visit, set sorts the pairs by dist
	//basically a priority queue
	set<pair<int, int>> visited;
	visited.insert(make_pair(0,source));
	while (!visited.empty())
	{
		//node i is currently being processed
		int i = visited.begin()->second;
		visited.erase(visited.begin());
		//end con
		if (i==dest) return dist[i];
		for (auto j : nbrs[i])
			//eliminate edges with less flow than flowmin
			if (edgeflow[make_pair(i,j)] >= flowmin)
				//con to insert
				if (dist.count(j)==0||dist[i]+edgecost[make_pair(i,j)]<dist[j])
				{
					dist[j] = dist[i] + edgecost[make_pair(i,j)];
					visited.insert(make_pair(dist[j], j));
				}
	}
	return -1;
}
int main()
{
	int i,j,k;
	ifstream fin("pump.in");
	ofstream fout("pump.out");
	fin >> N >> M;
	int a, b, c, f;
	//set up graph
	for (i=0;i<N;i++)
	{
		fin>>a>>b>>c>>f;
		flows.push_back(f);
		nbrs[a].push_back(b);
		nbrs[b].push_back(a);
		edgecost[make_pair(i,j)] = edgecost[make_pair(j,i)] = c;
		edgeflow[make_pair(i,j)] = edgeflow[make_pair(j,i)] = f;
	}

	long long best_num = 0, best_den = 1, cur_num, cur_den;
	for (int f : flows) 
	{
		//go through; as f is different it represents eliminating edges
		cur_num = f;
		cur_den = dijikstra(1, N, f);
		if (cur_den != -1)
			if (cur_num*best_den>best_num*cur_den) 
			{
				best_num = cur_num;
				best_den = cur_den;
			}
	}
	fout << best_num * 1000000LL / best_den << '\n';
	return 0;
}
