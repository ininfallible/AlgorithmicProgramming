//doing after competition
//2d dp problem
#include <bits/stdc++.h>

using namespace std;

int N, K, P;
int dp[31][1501];
int ps[31][51];

int main()
{
	cout << (int)0xc0c0c0c0 << endl;
	ifstream cin("2020a2.in");
	int T;
	cin >> T;
	for (int t=1;t<=T;t++)
	{
		cout << "Case #" << t << ": ";

		cout << endl;

		cin >> N >> K >> P;
		memset(dp, 0xc0, sizeof(dp));
		dp[0][0]=0;
		for(int i=1;i<=N;i++)
		for(int j=1;j<=K;j++)
		{
			cin >> ps[i][j];
			ps[i][j]+=ps[i][j-1];
		}
		for(int i=0;i<N;i++)
		{
			memcpy(dp[i+1],dp[i],sizeof(dp[0]));
			for (int c=0;c<=K;c++)
			for (int d=0;d<=K&&c+d<=P;d++)
			{
				dp[i+1][c+d]=max(dp[i+1][c+d], dp[i][c]+ps[i+1][d]);
			}	

			//debug
			for (int j=0;j<=P;j++) cout << dp[i+1][j] << " "; 
			cout << endl;
		}
		cout << dp[N][P];


		cout << endl;
	}
	return 0;
}
