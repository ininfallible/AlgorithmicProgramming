// 2015 December Gold P1

#include <iostream>
#include <fstream>

using namespace std;

bool dp[5000001][2];

int T, A, B;
int main()
{
	int i, j, k;
	ifstream fin("feast.in");
	ofstream fout("feast.out");
	fin >> T >> A >> B;
	dp[0][0] = dp[0][1] = 1;
	for (i=0;i<=T;i++)
	{
		if (i-A>=0&&dp[i-A][0]) dp[i][0]=1;
		if (i-B>=0&&dp[i-B][0]) dp[i][0]=1;
		if (dp[i][0]) dp[i/2][1]=1;
	}
	for (i=0;i<=T;i++)
	{
		if (i-A>=0&&dp[i-A][1]) dp[i][1]=1;
		if (i-B>=0&&dp[i-B][1]) dp[i][1]=1;
	}
	int ans=T;
	while (ans>0&&!(dp[ans][0]||dp[ans][1])) ans--;
	cout << ans <<endl;
	fout << ans <<endl;
	return 0;
}
