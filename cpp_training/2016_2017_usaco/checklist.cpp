//2016-2017 December Gold P2

#include <cstdint>
#include <iostream>
#include <fstream>
#include <cmath>
#include <cstring>

using namespace std;

int H, G;
int h[1002][2], g[1002][2];
long dp[1002][1002][2];

int d(int a[],int b[])
{
	return pow(a[0]-b[0],2)+pow(a[1]-b[1],2);
}

int main()
{
	int i,j,k;
	ifstream fin("checklist.in");
	ofstream fout("checklist.out");
	fin >> H >> G;
	for (i=1;i<=H;i++)
		fin >> h[i][0] >> h[i][1];
	for (i=1;i<=G;i++)
		fin >> g[i][0] >> g[i][1];

	//dynamic programming
	for (i=0;i<1002;i++)
	for (j=0;j<1002;j++)
		dp[i][j][0]=dp[i][j][1]=1<<30;

	dp[1][0][0]=0;
	for (i=0;i<=H;i++)
	for (j=0;j<=G;j++)
	{
		if((i==0&&j==0)||(i==0&&j==1)) continue;
		//g -> h
		dp[i+1][j][0]=min(dp[i+1][j][0], dp[i][j][1]+d(h[i+1],g[j]));
		//h -> h
		dp[i+1][j][0]=min(dp[i+1][j][0], dp[i][j][0]+d(h[i+1],h[i]));
		//g -> g
		dp[i][j+1][1]=min(dp[i][j+1][1], dp[i][j][1]+d(g[j+1],g[j]));
		//h -> g
		dp[i][j+1][1]=min(dp[i][j+1][1], dp[i][j][0]+d(g[j+1],h[i]));
	}
	cout << dp[H][G][0] << endl;
	fout << dp[H][G][0] << endl;
	
	return 0;
}
