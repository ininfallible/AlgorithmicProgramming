//2019-2020 January Gold P1

#include <iostream>
#include <fstream>
#include <cstring>

using namespace std;

int N, P;

int dist (int x1,int y1, int x2, int y2)
{
	return abs(x2-x1)+abs(y2-y1);
}
int dp[1002];

int main ()
{
	int i,j,k;
	ifstream fin("boards.in");
	ofstream fout("boards.out");
	fin >> N >> P;
	int b[1002][2][2];
	for (i=1;i<=P;i++)
		fin >> b[i][0][0] >> b[i][0][1] >> b[i][1][0] >> b[i][1][1];
	
	// f(n) = min (dist to end, f of all boards using this board has access to)
	memset(dp, 0x3F, sizeof(dp));
	
	return 0;
}
