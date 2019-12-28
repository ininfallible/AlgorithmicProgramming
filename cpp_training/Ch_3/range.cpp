/*
ID: derek.l3
PROB: range
LANG: C++11
*/

#include <iostream>
#include <fstream>
#include <queue>

using namespace std;

const int INF = 1<<20;
int N;
int dp[251][251];
int field[251][251];

void m_sq(int i, int j)
{
	int d=min(INF,dp[i-1][j-1]+1);
	int u=0,l=0, a=i, b=j;
	while (a>=0&&field[a][j])
	{
		a--;
		u++;
	}
	while (b>=0&&field[i][b])
	{
		b--;
		l++;
	}
	dp[i][j]=min(d, min(u, l));
}

int main()
{
	int i,j,k;
	int r,c;
	ifstream fin("range.in");
	ofstream fout("range.out");

	fin >> N;
	for (r=0;r<N;r++)
	{
		string tmp;
		fin >> tmp;
		for (c=0;c<N;c++)
		{
			field[r][c]=tmp[c]-'0';
		}
	}

	for (i=0;i<N;i++)
	for (j=0;j<N;j++)
	{
		if (i==0||j==0)
			dp[i][j]=field[i][j];
		else
			m_sq(i, j);
		continue;
	}

	int sol[251]{0};
	for (i=0;i<N;i++)
	for (j=0;j<N;j++)
	for (k=2;k<=dp[i][j];k++)
		sol[k]++;

	for (i=2;i<=250;i++)
		if (sol[i]>0)
			fout << i << " " << sol[i] << endl;
	return 0;
}
