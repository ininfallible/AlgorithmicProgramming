/*
ID: derek.l3
PROB: fence
LANG: C++11
*/

#include <fstream>
#include <iostream>
#include <vector>

using namespace std;

const int MAX = 501;
const int INF = 1<<20;
int F, ind=0;
int g[MAX][MAX]={{0}};
int c[MAX];
int deg[MAX]={0};


void f_c(int cur)
{
	int i;
	for (i=1;i<MAX;i++)
	{
		if(g[cur][i])
		{
			break;
		}
	}
	if (i==MAX) 
	{
		c[ind]=cur;
		ind++;
	}
	else 
	{
		for (;i<MAX;i++)
		{
			if (g[cur][i])
			{
				g[cur][i]--;
				g[i][cur]--;
				f_c(i);
			}
		}
		c[ind] = cur;
		ind++;
	}
}

int main()
{
	ifstream fin("fence.in");
	ofstream fout("fence.out");
	int x, y, n0=INF;
	int i, j;
	fin >> F;
	for (i=1;i<=F;i++)
	{
		fin >> x >> y;
		n0 = min(n0,min(x,y));
		deg[x]++;
		deg[y]++;
		g[x][y]++;
		g[y][x]++;
	}
	for(i=1;i<MAX;i++)
	{
		if(deg[i]%2==1)
		{
			n0=i;
			break;
		}
	}
	f_c(n0);
	for (i=F;i>=0;i--)
		fout << c[i] << endl;
	return 0;
}
