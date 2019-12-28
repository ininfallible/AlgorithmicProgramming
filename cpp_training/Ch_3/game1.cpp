/*
ID: derek.l3
PROB: game1
LANG: C++
*/

#include <iostream>
#include <fstream>
#include <cstring>

using namespace std;

int dp[101][101];
int N;
int n[101];

int sol(int i, int j)
{
	if (i>j) return 0;
	if (i==j) return dp[i][j]=n[i];
	if (dp[i][j]!=-1) return dp[i][j];
	int out=0;
	out = max(
		min(sol(i+2, j), sol(i+1, j-1))+n[i],
		min(sol(i, j-2), sol(i+1, j-1))+n[j]
	);
	return dp[i][j] = out;
}

int main()
{
	int i, sum=0;
	ifstream fin("game1.in");
	ofstream fout("game1.out");
	fin >> N;
	memset(dp,-1,sizeof(dp));
	for (i=0;i<N;i++)
	{
		fin >> n[i];
		dp[i][i] = n[i];
		sum += n[i];
	}
	fout << sol(0, N-1) << " " << sum - sol(0, N-1) << endl;

	return 0;
}
