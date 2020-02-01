// 2015-2016 January Gold P2
// implementing given answer
// reference: look @ dynamic time warping

// why does the memoization work?
// let's say we want (1, 2) (N=2, M=3)
// how do we know that the answer stored is optimal?
// process will be 
// (0, 0) -> (1, 0), (0, 1), (1, 1)
// (1, 0) -> (1, 1), (2, 0), (2, 1)
// (1, 1) -> (2, 1), (1, 2), (2, 2)
// once we hit bottom it returns upwards
// everything stemming from (1, 2) will have been searched (dfs)
// by the time (1,2) is filled in, we know it is optimal

#include <iostream>
#include <fstream>
#include <cmath>
#include <cstring>

using namespace std;

int N, M;
int dx[200], dy[200];
long long dp[1001][1001];
int pf[1001][2], pb[1001][2];

long long dist(int a,int b)
{
	return pow(pf[a][0]-pb[b][0],2)+pow(pf[a][1]-pb[b][1],2);
}
long long sol (int f, int b)
{
	//cost of the step
	long long base = dist(f, b);
	//end case
	if (f==N&&b==M) return base;
	
	// big brain - pointer to dp location
	// as ret is updated by solution below it also fills in dp
	long long& ret = dp[f][b];

	// memoizatoin
	if (ret!=-1)  return ret;

	// ignore cost of first step
	if (f==0&&b==0) base=0;

	// recursive step
	ret = 1<<30;
	
	if (f+1<=N) ret = min(ret,base+sol(f+1,b));

	if (b+1<=M) ret = min(ret,base+sol(f,b+1));

	if (f+1<=N&&b+1<=M) ret = min(ret,base+sol(f+1,b+1));
	
	return ret;
}
int main()
{
	int i,j,k;
	dx['E']=1;dx['W']=-1;dy['N']=1;dy['S']=-1;
	ifstream fin("radio.in");
	ofstream fout("radio.out");

	fin >> N >> M;
	fin >> pf[0][0] >> pf[0][1];
	fin >> pb[0][0] >> pb[0][1];

	string F, B;
	fin >> F;
	fin >> B;

	for (i=1;i<=N;i++)
	{
		pf[i][0] = pf[i-1][0]+dx[F[i-1]];
		pf[i][1] = pf[i-1][1]+dy[F[i-1]];
	}

	for (i=1;i<=M;i++)
	{
		pb[i][0] = pb[i-1][0]+dx[B[i-1]];
		pb[i][1] = pb[i-1][1]+dy[B[i-1]];
	}
	for (i=0;i<=M;i++)
	cout << pb[i][0] << " " << pb[i][1] << endl;

	memset(dp, -1, sizeof(dp));
	fout << sol(0,0) << endl;
	return 0;
}
