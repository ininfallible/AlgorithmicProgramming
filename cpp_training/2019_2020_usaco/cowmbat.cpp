//2019 Dec Gold P3
#include <bits/stdc++.h>

using namespace std;

const int MAXN = 1e5+1;
const int MAXM = 27;
const int INF = 2e9;

int N, M, K;
int adj[MAXM][MAXM];
int ps[MAXN][MAXM];
int st[MAXN];
int dp[MAXN][MAXM];
int mdp[MAXN];
int main()
{
	int i,j,k;
	ifstream fin("cowmbat.in");
	ofstream fout("cowmbat.out");
	fin>>N>>M>>K;

	string s;
	fin>>s;
	for (i=1;i<=N;i++) 
		st[i]=s[i-1]-'a'+1;

	for (i=1;i<=M;i++)
	for (j=1;j<=M;j++)
		fin >> adj[i][j];
	
	for (k=1;k<=M;k++)
	for (i=1;i<=M;i++)
	for (j=1;j<=M;j++)
		if (adj[i][j]>adj[i][k]+adj[k][j])
			adj[i][j] = adj[i][k]+adj[k][j];


	for (int i=1;i<=N;i++)
	for (int j=1;j<=M;j++)
		ps[i][j]=ps[i-1][j]+adj[st[i]][j];
	
	//prepare arrays
	for (i=1;i<=N;i++)
	{
		mdp[i]=INF;
		for (j=1;j<=M;j++)
			dp[i][j]=INF;
	}
	for (i=1;i<=M;i++)
		dp[K][i]=ps[K][i];
	//perform dp
	for (i=K;i<=N;i++)
	{
		int mdpi = INF;
		for (j=1;j<=M;j++)
		{
			//start new chain
			if (ps[i][j]-ps[i-K][j]+mdp[i-K]<dp[i][j])
				dp[i][j]=ps[i][j]-ps[i-K][j]+mdp[i-K];
			//continue off old
			if (i-K>0 && adj[st[i]][j]+dp[i-1][j]<dp[i][j])
				dp[i][j]=adj[st[i]][j]+dp[i-1][j];
			if (dp[i][j]<mdpi) mdpi = dp[i][j];
		}
		mdp[i] = mdpi;
	}
	
	/*
	//debug
	for (i=1;i<=N;i++) cout << st[i] << " ";
	cout << endl;
	cout << endl;
	for (i=1;i<=N;i++)
	{
		for (j=1;j<=M;j++) cout << dp[i][j] << " ";
		cout << '\n';
	}
	cout << '\n';
	cout << endl;
	*/
	
	int ans = mdp[N];
	//cout << ans << endl;
	fout << ans << endl;
	return 0;
}
