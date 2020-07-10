//2018 Gold P3 
//dp problem, implementing sol.
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
int dp[10001];
int c[10001];
int main()
{
	int i,j,k;
	ifstream fin("teamwork.in");
	ofstream fout("teamwork.out");
	fin >> N >> K;
	for (i=1;i<=N;i++) fin >> c[i];
	dp[1] = c[1];
	for (i=2;i<N;i++)
	{
		int mx = c[i]; // represents max cow in the previous team
		for (j=i; j>=0 && i-j+1<=K;j--)
		{
			mx = max(mx,c[j]); // examines 1 more cow for max cow
			if (j==0) dp[i] = max(dp[i],mx*(i+1-j));
			else dp[i] = max(dp[i],dp[j-1]+mx*(i+1-j));
		}
	}
	cout << dp[N-1] << '\n';
	return 0;
}

