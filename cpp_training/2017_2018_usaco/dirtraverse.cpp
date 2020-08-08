//2018 Feb Gold P2
//bfs and dfs graph problem
#include <bits/stdc++.h>
using namespace std;

#define pb push_back
#define mp make_pair
#define fi first
#define se second
typedef vector<int> vi;
typedef pair<int, int> pii;
typedef long long ll;
const int INF = 1<<30;
const int MAXN = 100001;

int N;
int isf[MAXN], indist[MAXN], par[MAXN], sts[MAXN];
long pathl[MAXN];
int distfromroot[MAXN];
vector<vi> adj; // a -> b, dist
int files=0;

int dfs(int cur, int par)
{
	if (isf[cur]) return 1;
	int ret = 0;
	for (int i=0;i<adj[cur].size();i++)
	{
		if (adj[cur][i] != par) 
			ret+=dfs(adj[cur][i], cur);
	}
	sts[cur] = ret;
	return ret;
}

int main()
{
	int i,j,k;
	ifstream fin("dirtraverse.in");
	ofstream fout("dirtraverse.out");

	fin >> N;
	string name;
	for (i=0;i<=N;i++)
		adj.pb(vi());
	int m;
	for (i=1;i<=N;i++)
	{
		fin >> name;
		fin >> m; 
		indist[i] = name.length();
		indist[i] += m!=0;
		isf[i] = m==0;
		files += m==0;
		for (j=0;j<m;j++)
		{
			fin >> k;
			adj[i].pb(k);
			adj[k].pb(i);
			par[k]=i;
		}
	}


	// bfs for directories
	dfs(1, 0);

	//calc stl[1]
	deque<int>tP;
	tP.pb(1);
	while (!tP.empty())
	{
		int cur = tP.front(); tP.pop_front();
		if (cur != 1)
		{
			// cout << "adding: " << indist[cur] << endl;
			distfromroot[cur] = distfromroot[par[cur]] + indist[cur];
		}
		for (auto a: adj[cur])
		{
			if (a!=par[cur])
				tP.pb(a);
		}
		if (isf[cur]) pathl[1] += distfromroot[cur];
	}
	
	long ans=pathl[1];
	//bfs again - fill out pathl
	tP.pb(1);
	while (!tP.empty())
	{
		int cur = tP.front(); tP.pop_front();
		if (!isf[cur])
		{
			if (cur != 1)
				pathl[cur] = pathl[par[cur]] + (long)3*(files-sts[cur]) - (long)indist[cur]*sts[cur];
			ans = min(ans, pathl[cur]);
		}
		for (auto a: adj[cur])
		{
			if (a!=par[cur])
				tP.pb(a);
		}
	}
	cout << ans << endl;
	fout << ans << endl;
	return 0;
}
