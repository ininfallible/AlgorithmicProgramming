//2019 Dec USACO Gold P2
//implementing solution ... chances of gold are slim :/
//Completed 20-09-26
#include <bits/stdc++.h>
using namespace std;

#define pb push_back
#define mp make_pair
#define fi first
#define se second
typedef vector<int> vi;
typedef pair<int, int> pii;
typedef long long ll;
const int INF = 1<<20;

const int MAXN = 1e5+1;
const int MAXM = 1e5+1;
//maps i to position in pre and post order traverses
int preT[MAXN], aftT[MAXN];
int type[MAXN];
int N, M;
int query[MAXN][3];
vi adj[MAXN];
int ok[MAXN];

int co = 0;
vi todo[MAXN];

//constructs pre and post order traverses
void traverse(int cur, int par)
{
	preT[cur] = co++;
	for (auto a : adj[cur]) 
		if (a != par) 
			traverse(a, cur);
	aftT[cur] = co-1;
}

//returns whether A is an ancestor of B
//for this to be true, A < B in pre, A > B in post
bool anc(int a, int b)
{
	return preT[a]<=preT[b] && aftT[a]>=aftT[b];
}

//path from root to cur
vi ord;
//for all types t, store y -> depth[y], where farm y has type t
vector<pii> stor[MAXN];
void sol(int cur, int par)
{
	stor[type[cur]].pb({cur, ord.size()});
	ord.pb(cur);
	//for all paths one endpoint a
	for (auto a: todo[cur])
	{
		//if there are farms with type t on the path from root to cur:
		if (stor[query[a][2]].size())
		{
			//last farm with this property
			pii y = stor[query[a][2]].back();
			//if equal to cur, ok
			if (y.fi == cur) ok[a] = 1;
			else
			{
				int yfarm = y.fi;
				//if anc of other endpoint
				if (!anc(yfarm, query[a][0]+query[a][1]-cur)) ok[a] = 1;
			}
		}
	}

}

int main()
{
	int i,j,k;
	ifstream fin("milkvisits.in");
	ofstream fout("milkvisits.out");
	
	//N nodes, M queries
	fin >> N >> M;

	for (i=1;i<=N;i++)
		fin >> type[i];
	//construct graph
	for (i=0;i<N-1;i++)
	{
		fin >> j >> k;
		adj[j].pb(k);
		adj[k].pb(j);
	}
	//construct pre and post traversals
	traverse(1, 0);
	for (i=0;i<M;i++)
	{
		cin >> query[i][0] >> query[i][1] >> query[i][2];
		//sort endpoints into bins for processing
		todo[query[i][0]].pb(i);
		todo[query[i][1]].pb(i);
	}
	sol(1, 0);
	for (i=0;i<M;i++)
	{
		if (ok[i]) cout << 1;
		else cout << 0;
	}
	cout << "\n";
	return 0;
}

