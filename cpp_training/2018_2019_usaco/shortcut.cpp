//2019 Jan Gold P3
#include <algorithm>
#include <bits/stdc++.h>
#include <climits>
#include <queue>
using namespace std;

#define fi first
#define se second
typedef vector<int> vi;
typedef pair<int, int> pii;
typedef long long ll;
const int INF = 1<<20;

const int MAXN = 1e4+2;

struct state {
	int f;       //field
	long long d; //dist
};
struct edge {
	int d; // dest
	int w; // weight
};
struct compare_states {
	bool operator()(state a, state b)
	{
		return a.d > b.d;
	}
};

int N, M, T;
int par[MAXN];
long long dist[MAXN];
bool used[MAXN];
long long cow[MAXN];
long long pcow[MAXN];

int main()
{
	int i,j,k;
	ifstream fin("shortcut.in");
	ofstream fout("shortcut.out");

	//read input
	fin >> N >> M >> T;
	int a, b, d;
	for (i=1;i<=N;i++) fin >> cow[i];
	vector<vector<edge>> adj(N+1);
	for (i=1;i<=M;i++)
	{
		fin >> a >> b >> d;
		adj[a].push_back(edge{b, d});
		adj[b].push_back(edge{a, d});
	}

	fill(begin(dist), end(dist), LONG_LONG_MAX);
	fill(begin(par), end(par), -1);

	//dijikstra/bfs
	priority_queue<state, vector<state>, compare_states> dijikstraQ;
	state tmp {1, 0};
	dist[1] = 0;
	used[1] = true;
	dijikstraQ.push(tmp);

	while (!dijikstraQ.empty())
	{
		state cur = dijikstraQ.top();
		dijikstraQ.pop();

		//cout << cur.f << endl; //debug

		int cf = cur.f;
		used[cf] = true;

		for (edge e : adj[cf]) 
		{
			//cout << "edge to: " << e.d << endl; // debug
			int nf = e.d;
			if (used[nf]) continue;
			long long nd = dist[cf] + e.w;
			//cout << "dist: " << nd << endl; // debug
			if (nd < dist[nf])
			{
				//cout << "shorter" << endl; // debug
				dist[nf] = nd;
				par[nf] = cur.f;
				state next {nf, nd};
				dijikstraQ.push(next);
			} 
			else if (nd == dist[nf])
			{
				//compare lexigoraphically
				if (cf < par[nf])
				{
					dist[nf] = nd;
					par[nf] = cf;
					state next {nf, nd};
					dijikstraQ.push(next);
				}
			}
		}
	}
	//cout << "boop" << endl;
	//for (k=1;k<=N;k++) cout << par[k] << endl;
	//calc cows per node with par
	for (k=1;k<=N;k++)
	{
		i=k;
		while (par[i]!=-1)
		{
			//cout << par[i] << endl;
			pcow[i] += cow[k];
			i = par[i];
		}
	}
	
	long long ans = 0;
	for (k = 1; k<=N;k++)
	{
		ans = max(ans, pcow[k]*(dist[k]-T));
	}
	fout << ans << endl;
	cout << ans << endl;

	fin.close();
	fout.close();
	return 0;
}


