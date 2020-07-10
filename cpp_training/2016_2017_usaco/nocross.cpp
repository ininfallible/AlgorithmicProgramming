//2017 Feb Gold P2
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
const int MAXN = 1000;
int top[MAXN],bot[MAXN];
int dp[MAXN][MAXN];
int N;
int main()
{
	ifstream fin("nocross.in");
	ofstream fout("nocross.out");
	int i,j,k;
	fin >> N;
	for (i=0;i<N;i++) fin >> top[i];
	for (i=0;i<N;i++) fin >> bot[i];

	// whether first field on one side can connect to other side
	dp[0][0] = abs(top[0]-bot[0])<=4;
	for (i=1;i<N;i++) dp[i][0] = max(dp[i-1][0], (int)(abs(top[i]-bot[0]) <= 4));
	for (i=1;i<N;i++) dp[0][i] = max(dp[0][i-1], (int)(abs(top[0]-bot[i]) <= 4));
	// recurrence - for i, j, either connect or not, if connected then cond., else look at 
	// other i-1,j and i,j-1
	for (i=1;i<N;i++)
		for (j=1;j<N;j++)
			dp[i][j] = max(max(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]+(abs(top[i]-bot[j])<=4));
	fout << dp[N-1][N-1] << '\n';
	return 0;
}
