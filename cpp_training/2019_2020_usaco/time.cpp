//placeholder
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

const int MAXN = 1003;

int N, M, C;
// time, index 
int dp[MAXN][MAXN];
int m[MAXN];
vector<pii> adj;

int main()
{
	int i,j,k;
	//read input
	ifstream fin("time.in");
	ofstream fout("time.out");
	fin >> N >> M >> C;
	for (i=1;i<=N;i++) fin >> m[i];
	for (i=1;i<=M;i++) 
	{
		fin >> j >> k;
		adj.pb(mp(j, k));
	}

	int tmax = (1000-C)/(C*2)+2;

	//init dp array
	for (i=0;i<=tmax;i++) fill(dp[i], dp[i]+N+1, -1);
	dp[0][1] = 0;
	int ans = 0;
	for (int t=1;t<=tmax;t++)
	{
		//t-1 in because t0->t1 means 0*2+1
		for (auto edge : adj)
			if(dp[t-1][edge.fi] >= 0)
				dp[t][edge.se] = max(dp[t][edge.se], 
				dp[t-1][edge.fi]+m[edge.se]-(2*(t-1)+1)*C);
		ans = max(ans, dp[t][1]);
	}
	fout << ans << endl;
	return 0;
}
