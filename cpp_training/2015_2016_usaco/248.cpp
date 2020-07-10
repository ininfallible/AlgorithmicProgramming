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
const int MAXN = 248;
int dp[MAXN][MAXN], nums[MAXN];
int N;
int main()
{
	int i,j,k;
	ifstream fin("248.in");
	ofstream fout("248.out");
	fin >> N;
	for (i=0;i<N;i++) fin >> nums[i];
	for (i=0;i<MAXN;i++) fill(dp[i], dp[i]+MAXN, -1);
	int ans = 0;
	for (int len=0;len<N;len++)
	for (int lo=0;lo+len<N;lo++)
	{
		int hi = len+lo;
		if (len==0) dp[lo][hi] = nums[lo];
		//calc other within the interval
		for (int mid=lo;mid<hi;mid++) // intervals are inclusive
		{
			if (dp[mid+1][hi]==dp[lo][mid]) 
				dp[lo][hi] = max(dp[lo][hi], dp[lo][mid]+1);
				
		}
		ans = max(ans, dp[lo][hi]);
	}
	fout << ans << endl;
	
	return 0;
}

