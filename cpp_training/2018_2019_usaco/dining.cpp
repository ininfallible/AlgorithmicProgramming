//2018 December Gold P1
//double dijkstra; less elegant solution
#include <bits/stdc++.h>
#include <queue>
using namespace std;

#define fi first
#define se second
#define mp make_pair
typedef vector<int> vi;
typedef pair<int, int> pii;
typedef long long ll;
const int INF = 1<<20;

int N, M, K;
int dist[50001][2];
int bales[50001];
int main()
{
	int i,j,k;
	ifstream fin ("dining.in");
	ofstream fout("dining.out");
	fin >> N >> M >> K;
	//build graph
	vector<vector<pii>> adj(N+1);
	int a,b,t;
	for (i=0;i<M;i++)
	{	
		fin >> a >> b >> t;
		adj[a].push_back(mp(b, t));
		adj[b].push_back(mp(a, t));
	}
	for (i=0;i<K;i++)
	{
		fin >> a >> b;
		bales[a] = b;
	}
	//dijikstra's, pairs store -dist, field no.
	priority_queue<pii> tP; //no bale
	priority_queue<pii> tPb; //bale
	tP.push(mp(0, N));
	for (i=0;i<50001;i++) dist[i][0] = dist[i][1] = INF;
	dist[N][0] = 0;
	while (!tP.empty())
	{	
		pii curr = tP.top();
		curr.fi = -curr.fi;
		tP.pop();
		for (pii edge : adj[curr.se])
		{
			/*
			if (bales[edge.fi]!=0)
			{
				tPb.push(mp(-(curr.fi+edge.se-bales[edge.fi]), edge.fi));
				dist[edge.fi][1] = curr.fi+edge.se-bales[edge.fi];
			}
			*/
			if (curr.fi+edge.se < dist[edge.fi][0])
			{
				dist[edge.fi][0] = curr.fi+edge.se;
				tP.push(mp(-curr.fi-edge.se, edge.fi));
			}
		}
	}

	for (i=1;i<=N;i++) cout << dist[i][0] << " "; //debug
	cout << endl;
	for (i=1;i<=N;i++) 
	if (bales[i]!=0) 
	{
		tPb.push(mp(-(dist[i][0]-bales[i]),i));
		dist[i][1] = dist[i][0]-bales[i];
	}
	while (!tPb.empty())
	{
		pii curr = tPb.top();
		curr .fi = -curr.fi;
		tPb.pop();
		for (pii edge: adj[curr.se])
		{
			if (curr.fi + edge.se < dist[edge.fi][1])
			{
				dist[edge.fi][1] = curr.fi + edge.se;
				tPb.push(mp(-curr.fi-edge.se, edge.fi));
			}
		}
	}
	for (i=1;i<=N;i++) cout << dist[i][1] << " "; //debug
	cout << endl;

	for (i=1;i<N;i++) fout << ((dist[i][1]<=dist[i][0])?1:0) << '\n';
	
	fin.close();
	fout.close();
	return 0;
}

