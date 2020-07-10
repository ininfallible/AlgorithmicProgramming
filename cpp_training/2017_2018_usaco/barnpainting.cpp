//2017 Dec. Gold
//implementing sol from website
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

const int MAXN = 1e5;
const int MOD = 1e9+7;
int N, K;

vi adj[MAXN];
long dp[MAXN][3];
int color[MAXN];

long sol(int cV, int cC, int pV, int pC)
{
	if (pC == cC || (color[cV] >= 0 && cC != color[cV])) return 0;
	if (dp[cV][cC]>=0) return dp[cV][cC];
	dp[cV][cC] = 1;
	for (int out : adj[cV])
	{
		if (out == pV) continue;
		long ways = 0;
		for (int c=0;c<3;c++)
		{
			ways += sol(out, c, cV, cC);
			ways %= MOD;
		}
		dp[cV][cC] *= ways;
		dp[cV][cC] %= MOD;
	}
	return dp[cV][cC];
}

int main()
{
	ifstream fin("barnpainting.in");
	ofstream fout("barnpainting.out");
	int i,j,k;
	fin >> N >> K;
	fill(color, color+N, -1);
	for (auto i: dp) fill(i,i+3,-1);
	for (i=1;i<N;i++) //build graph
	{
		fin >> j >> k;
		j--; k--; // indexing from 0
		adj[j].pb(k);
		adj[k].pb(j);
	}
	for (i=0;i<K;i++)
	{
		fin >> j >> k;
		j--; k--;
		color[j]=k;
	}
	long ans = sol(0, 0, -1, -1) + sol(0, 1, -1, -1) + sol(0, 2, -1, -1);
	fout << ans % MOD << endl;
	
	return 0;
}

