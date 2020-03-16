//2019 Dec Gold P2
//implementing solution
#include <bits/stdc++.h>

using namespace std;

const int MX = 1e5+5;

int N, M;
int T[MX], C[MX];
bool ok[MX];
int dat[MX][2];
vector <int> adj[MX], todo[MX], ord;
pair <int, int> range[MX];
vector<pair<int, int>> stor[MX];

int co = 0;
/*
 * how anc works: range[i].first represents in time of i, range[i].second represents out time
 * in time of an ancestor is earlier than a descendant, while out time is later
 * preprocessing is achieved through dfs
 */
void dfs(int x, int y)
{
	range[x].first = co++;
	for (auto& a: adj[x]) if(a != y) dfs(a, x);
	range[x].second = co-1;
}
bool anc (int a, int b)
{
	return range[a].first <= range[b].first && range[a].second >=range[b].second;
}

void dfs2(int x, int y)
{
	//stor maps type t to all farms with type t and their depth (ord.size = depth)
	stor[T[x]].push_back({x,(int)ord.size()}); ord.push_back(x);
	//for all queries with a point at node x
	//if there are any farms with desired type
	for (auto& a: todo[x]) if ((int)stor[C[a]].size())
	{
		pair<int, int> y = stor[C[a]].back();
		if (y.first == x) ok[a]=1;
		else {
			int Y = ord[y.second+1];
			//x is one of the endpoints for query a
			assert(dat[a][0]==x||dat[a][1]==x);
			//if Y not ancestor of the other endpoint, then farm y is on path
			//Y 1 closer than y to A_i
			if (!anc(Y, dat[a][0]+dat[a][1]-x)) ok[a]=1;
		}
	}
	for (auto& a : adj[x]) if (a != y) dfs2(a, x);
	stor[T[x]].pop_back(); ord.pop_back();
}

int main()
{
	int i,j,k;
	ifstream fin("milkvisits.in");
	ofstream fout("milkvisits.out");
	fin >> N >> M;
	for (i=1;i<=N;i++) fin >> T[i];
	for (i=0;i<N;i++)
	{
		fin >> j >> k;
		adj[j].push_back(k);
		adj[k].push_back(j);
	}
	//preprocess so anc works
	dfs(1, 0);

	//read input
	for (int i=0;i<M;i++) 
	{
		fin >> dat[i][0] >> dat[i][1] >> C[i];
		for (j=0;i<2;j++) todo[dat[i][j]].push_back(i);
	}
	dfs2(1,0);
	for (int i=0;i<M;i++)
	{
		if (ok[i]) cout << 1;
		else cout << 0;
	}
	cout << "\n";
	return 0;
}
