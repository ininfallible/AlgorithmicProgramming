/*
ID: derek.l3
PROB: butter
LANG: C++11
*/
//USACO training 3.2

#include <iostream>
#include <fstream>
#include <vector>
#include <queue>

using namespace std;

int N, P, C;
const int MAX = 801;
const int INF = 1<<20;
//N: Cows P: Pastures, C: Connecting Paths
int cow[MAX];
int cost[MAX][MAX];
int spt[MAX][MAX];
vector<int> edges[MAX];

void SPFA(int v0) {
	queue<int> Q;
	Q.push(v0);
	bool v[MAX]={0};
	while (!Q.empty())
	{
		int cur = Q.front();
		Q.pop();
		for(int i=0;i<edges[cur].size();i++)
		{
			int next = edges[cur][i];
			if (spt[v0][cur]+cost[cur][next] < spt[v0][next])
			{
				spt[v0][next] = spt[v0][cur]+cost[cur][next];
				if (!v[next])
				{
					Q.push(next);
					v[next]=1;
				}
			}
		}
		v[cur] = 0;
	}
}

int main () 
{
	int i, j, k;
	int x, y, c;
	ifstream fin("butter.in");
	ofstream fout("butter.out");
	//N: Cows P: Pastures, C: Connecting Paths
	fin >> N >> P >> C;

	for (i=0;i<N;i++)
		fin >> cow[i];
	for (i=1;i<=C;i++)
	{
		fin >> x >> y >> c;
		cost[x][y] = cost[y][x] = c;
		edges[x].push_back(y);
		edges[y].push_back(x);
	}
	for (i=1;i<=P;i++)
	for (j=1;j<=P;j++)
		spt[i][j]=INF;
	for (i=1;i<=P;i++)
		cost[i][i]=spt[i][i]=0;
	//find shortest paths
	for (i=1;i<=P;i++)
		SPFA(i);
	//find answer
	int ans = INF;
	for (i=1;i<=P;i++)
	{
		int cur=0;
		for (j=0;j<N;j++)
			cur+=spt[cow[j]][i];
		ans = min(ans, cur);
	}
	cout << ans << endl;
	fout << ans << endl;
	return 0;
}
