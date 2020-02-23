//2018 Gold December P1

#include <iostream>
#include <fstream>
#include <vector>
#include <map>
#include <queue>

using namespace std;

const int MAXN = 50001;
typedef pair<int, int> pii;

int N, M, K;

int main()
{
	int i,j,k;
	ifstream fin ("dining.in");
	ofstream fout("dining.out");
	fin >> N >> M >> K;
	vector<pii> adj[MAXN];
	int dist[MAXN][2];
	int bales[MAXN]={-1};
	int a,b,c;
	for (i=0;i<M;i++)
	{
		fin >> a >> b >> c;
		adj[a].push_back(make_pair(b, c));
		adj[b].push_back(make_pair(a, c));
	}
	for (i=0;i<K;i++) fin >> a >> bales[a];
	priority_queue<int,vector<int>,greater<int>> Q;
	
	return 0;
}
