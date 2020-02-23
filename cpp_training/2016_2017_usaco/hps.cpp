//2017 Jan Gold P2

#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;
const int MAXN = 100001;

int N, K;
int dp[MAXN][22][3];
//hps
int comp(int a, int b)
{
	return ((a==0&&b==2)||(a==1&&b==0)||(a==2&&b==1));
}
int main()
{
	int i,j,k;
	ifstream fin("hps.in");
	ofstream fout("hps.out");
	fin >> N >> K;
	int FJ[MAXN]={0};
	char tmp;
	for (i=0;i<N;i++)
	{
		fin >> tmp;
		if (tmp=='H') FJ[i]=0;
		if (tmp=='P') FJ[i]=1;
		if (tmp=='S') FJ[i]=2;
	}

	int cfj;
	int out=0;
	dp[0][0][0]=comp(0,FJ[0]);
	dp[0][0][1]=comp(1,FJ[0]);
	dp[0][0][2]=comp(2,FJ[0]);
	for (i=1;i<N;i++) 
	{
		cfj = FJ[i];
		for (j=0;j<=K;j++)
		for (k=0;k<3;k++)
		{
			//case go to h
			if (dp[i][j+(k!=0)][0]<dp[i-1][j][k]+comp(0, cfj))
				dp[i][j+(k!=0)][0]=dp[i-1][j][k]+comp(0, cfj);
			//case go to p
			if (dp[i][j+(k!=1)][1]<dp[i-1][j][k]+comp(1, cfj))
				dp[i][j+(k!=1)][1]=dp[i-1][j][k]+comp(1, cfj);
			//case go to s
			if (dp[i][j+(k!=2)][2]<dp[i-1][j][k]+comp(2, cfj))
				dp[i][j+(k!=2)][2]=dp[i-1][j][k]+comp(2, cfj);
		}
	}
	for (i=0;i<=K;i++) 
	{
		for (j=0;j<3;j++)
			out = max(out, dp[N-1][i][j]);
	}
	fout << out << endl;

	return 0;
}
