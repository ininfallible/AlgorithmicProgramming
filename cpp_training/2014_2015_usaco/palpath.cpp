//2015 US Open Gold P2
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

const int MAXN = 501;
int N;
int f[MAXN][MAXN];
ll dp[MAXN][MAXN], ndp[MAXN][MAXN];
int main()
{
	ifstream fin("palpath.in");
	ofstream fout("palpath.out");
	int i,j,k;
	fin >> N;
	for (i=0;i<N;i++)
	{
		string tmp;
		fin >> tmp;
		for (j=0;j<N;j++) f[i][j] = tmp[j];
		//cout<<f[i][0]<<endl; //debug
	}
	for (i=0;i<N;i++) dp[i][i]=1;
	const ll MOD = 1e9+7;
	for (int dist=N-1;dist>=1;dist--) // dist from corner
	{
		for (int a=0;a<N;a++) //top left of diagonal
		{
			int ra = a;
			int ca = dist-1-a;
			if (ca<0) continue;
			for (int b=0;b<N;b++) //bot right of diagonal
			{
				int rb = b;
				int cb = 2*N-dist-1-b;
				if (cb>=N) continue;
				if (f[ra][ca] != f[rb][cb]) continue; 
				ndp[ra][rb]+= dp[ra][rb];
				if (ra+1<N)  ndp[ra][rb] += dp[ra+1][rb]; // case up / side
				if (rb-1>=0) ndp[ra][rb] += dp[ra][rb-1]; // case side / down
				if (ra+1<N&&rb-1>=0) ndp[ra][rb] += dp[ra+1][rb-1]; // case up / down
				ndp[ra][rb] %= MOD;
			}
		}
		for (i=0;i<N;i++)
		for (j=0;j<N;j++)
			dp[i][j] = ndp[i][j];
	}
	cout << dp[0][N-1];
	return 0;
}

