//2019 Dec 2019 P1 
#include <bits/stdc++.h>
using namespace std;

#define pb push_back
#define mp make_pair
#define fi first
#define se second
typedef vector<int> vi;
typedef pair<int, int> pii;
typedef long long ll;
const int INF = INT_MAX;
const int MAXN = 1000+1;

struct edge {
	//a, b, cost, flow
	int a, b, c, f;
};

//N junction joints, M pipes
int N, M;
int dist[MAXN]; //cost
vector<edge> adj[MAXN];
set<int> flows;

int djk(int dest, int mf)
{
	//cout << "djk: " << mf << endl;
	fill(dist, dist+N+1, INF);
	dist[1] = 0;
	//implement priority queue using set of pairs
	set<pii> tP;
	tP.insert(mp(0, 1));
	while (!tP.empty())
	{
		int cur = tP.begin()->second;
		//cout << "cur: " << cur << endl;
		tP.erase(tP.begin());
		if (cur==dest) return dist[cur];
		for (auto next: adj[cur])
		{
			if (next.f >= mf)
			{
				if (dist[cur] + next.c < dist[next.b])
				{
					dist[next.b] = dist[cur] + next.c;
					tP.insert(mp(dist[next.b], next.b));
				}
			}
		}
	}
	//case not possible
	return -1;
}

int main()
{
	ifstream fin("pump.in");
	ofstream fout("pump.out");
	int i,j,k;
	fin >> N >> M;
	int a, b;
	for (i=1;i<=M;i++)
	{
		fin >> a >> b >> j >> k;
		flows.insert(k);
		adj[a].pb(edge{a, b, j, k});
		adj[b].pb(edge{b, a, j, k});
	}
	ll best_num = 0, best_den = 1, cur_num, cur_den;

	for (auto a: flows)
	{
		//cout << a << endl;
		cur_num = a;
		cur_den = djk(N, a);
		if (cur_den != -1)
		{
			if (cur_num * best_den > best_num * cur_den)
			{
				best_num = cur_num;
				best_den = cur_den;
			}
		}
	}
	fout << best_num * 1000000LL / best_den << endl;
	
	return 0;
}
