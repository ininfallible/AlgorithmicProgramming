//2019 US Open Gold P1
//implementing sol. from website
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

int N, K;
int S[401], dp[401][401];
int main()
{
	ifstream fin("snakes.in");
	ofstream fout("snakes.out");
	int i,j,k;
	fin >> N >> K;

	//similar to cbarn2
	int sum=0, high=0;
	for (i=1;i<=N;i++) //for each group of snakes
	{
		fin >> S[i];
		high = max(high, S[i]); // max no. of snakes so far
		// min sum of net sizes ie total net used
		dp[i][0] = high*i;
		for (j=1;j<=K;j++)
		{
			dp[i][j]=1<<30;
			int mx = S[i];
			//calculate transition state: from previous place b:
			//change between b and i = dp[b][j-1] + max size between b and i * dist
			for (int b=i-1;b>=0;b--)
			{
				dp[i][j] = min(dp[i][j], dp[b][j-1]+mx*(i-b));
				mx = max(mx, S[b]);
			}
		}
		sum += S[i];
	}
	cout << dp[N][K] - sum << '\n';
	return 0;
}

