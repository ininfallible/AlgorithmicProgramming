//2014 Dec Gold P1
#include <bits/stdc++.h>
#include <climits>
using namespace std;

#define pb push_back
#define mp make_pair
#define fi first
#define se second
typedef vector<int> vi;
typedef pair<int, int> pii;
typedef long long ll;
const int INF = (1<<30)+2;
const int MAXN = (1<<20)+2;

struct cow{
	ll h, w, s;
};

ll w[MAXN], h[MAXN], dp[MAXN];
ll N, H;
vector<cow> cows;

int main()
{
	ifstream fin("guard.in");
	ofstream fout("guard.out");
	ll i,j,k,l;
	fin >> N >> H;
	for (i=0;i<N;i++)
	{
		fin >> j >> k >> l;
		cows.pb(cow{j, k, l});
	}
	//precompute sums of h and w for all subsets n*2^n
	for (i=0;i<(1<<N);i++)
	{
		int sw = 0, sh = 0;
		dp[i] = -INF;
		for (int j=0;j<N;j++)
		{
			if (i&(1<<j)) sw += cows[j].w;
			if (i&(1<<j)) sh += cows[j].h;
		}
		w[i] = sw;
		h[i] = sh;
	}
	ll ans = -1;
	//for each subset: do calc
	for (i=0;i<(1<<N);i++)
	{
		//case j is first cow, i's strength = INF
		for (j=0;j<N;j++)
		{
			if (i & (1<<j)) continue;
			int next = i | (1<<j);
			if (dp[i]==-INF) dp[i] = INF;
			//dp[i] - cows[j].w, cows[j].s: cow goes on top :. min between strength and safety - weight
			//cows[j].s - w[i], dp[i]: cow goes on bottom :. min between cow s - rest of cows, safety of rest of cows;
			dp[next] = max(dp[next], max(min(dp[i] - cows[j].w, cows[j].s), min(cows[j].s - w[i], dp[i])));
			if (h[next]>=H) ans = max(ans, dp[next]);
		}
	}

	if (ans < 0) fout << "Mark is too tall" << endl; 
	else fout << ans << endl;
	return 0;
}

