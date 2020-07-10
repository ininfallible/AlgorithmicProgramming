//2018 Feb Gold P3
//dp problem
//implementation is hard :( below is from sol.
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

const int MAXN = 101;
int dp[MAXN][MAXN][MAXN]; // day, prev break, no. of breaks
int N;
int cl[MAXN];
int main()
{
	int i,j,k;
	ifstream fin("taming.in");
	ofstream fout("taming.out");
	//read input
	fin >> N;
	for (i=0;i<N;i++)
		fin >> cl[i];
	for (i=0;i<N;i++)
	for (j=0;j<N;j++)
		fill(dp[i][j], dp[i][j]+N+1, INF);

	dp[0][0][1] = cl[0]!=0;

	for (i=1;i<N;i++)
	for (j=0;j<=i;j++)
	for (k=1;k<=i+1;k++)
	{
		if(j<i)
			dp[i][j][k] = dp[i-1][j][k]; //case no break today
		else
			//case break today; check through all possible prev. break days
			for(int j1=0;j1<i;j1++) dp[i][j][k] = min(dp[i][j][k], dp[i-1][j1][k-1]); 
		if (cl[i]!=i-j) //if no break check if increment
			dp[i][j][k]++;
	}
	for (k=1;k<=N;k++)
	{
		int low = INF;
		for (j=0;j<N;j++)
			low = min(low, dp[N-1][j][k]);
		fout << low << '\n';
	}
	
	return 0;
}

