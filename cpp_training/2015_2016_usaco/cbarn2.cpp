//2016 Feb Gold P2
//implementing sol from website
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
const int INF = 1<<20;

const int MAXN = 100;
int N, K;

vi req;
ll dp[9][MAXN];
int main()
{
	ifstream fin("cbarn2.in");
	ofstream fout("cbarn2.out");
	int i,j,k;
	fin >> N >> K;
	for (i=0;i<N;i++)
	{
		fin >> j;
		req.pb(j);
	}
	ll ans = LONG_LONG_MAX;
	
	//dp[k][j], where k is no. of doors used, j is pos of leftmost door = total cost of walking
	//maps to total cost of cows to the right
	//this type of transition allows the program to keep a running sum instead of calculating
	//a sum in O(N)

	//for each possible starting point
	for (i=0;i<N;i++)
	{
		//init dp
		memset(dp,0x3F,sizeof(dp));
		dp[0][N]=0; // at rightmost point, cost is 0
		for (int od=1;od<=K;od++)
		for (int curr=0;curr<N;curr++) // rtl
		{
			ll sum = 0;
			for (int prev=curr+1;prev<=N;prev++)
			{
				sum += req[prev-1] * (prev-curr-1); // no. of cows at door to left of prev * dist
				dp[od][curr] = min(dp[od][curr], sum + dp[od-1][prev]); //see recurrence above
			}
		}
		ans = min(ans, dp[K][0]);
		rotate(req.begin(), req.begin()+1, req.end());
	}
	fout << ans << endl;
	return 0;
}

