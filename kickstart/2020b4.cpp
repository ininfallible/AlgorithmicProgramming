//wrong
#include <algorithm>
#include <bits/stdc++.h>

using namespace std;

double dp[301][301];
int main()
{
	int i,j,k;
	int T;
	int W, H, L, U, R, D;
	//ifstream cin("2020a1.in");
	cin >> T;
	for (int t=1;t<=T;t++)
	{
		double ans = 0;
		cin >> W >> H >> L >> U >> R >> D;
		for (auto a: dp) fill_n(a, 300, 0);
		dp[1][1] = 1;
		for (int i=1;i<=W;i++)
		{
			for (int j=1;j<=H;j++)
			{
				if (L<=i&&i<=R && D<=j&&j<=U)
				{
					dp[i][j]=0;
				}
				else 
				{
					dp[i][j] = (dp[i][j-1] + dp[i-1][j])/2;
					if (i==W) dp[i][j] += dp[i][j-1]/2;
					if (j==H) dp[i][j] += dp[i-1][j]/2;
				}
				if (i==1&&j==1) dp[i][j]=1;
			}
		}
		ans = dp[W][H];
		cout <<"Case #"<<t<<": "<<ans<<'\n';
	}
	return 0;
}
