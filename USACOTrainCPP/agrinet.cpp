/*
ID: derek.l3
TASK: agrinet
LANG: C++
*/
//USACO Training 3.1: Agrinet

#include <iostream>
#include <fstream>

using namespace std;

int N;
int C[101][101];
int dist[101];
int source[101];
bool V[101];
int main () {
	int i, j;
	int ts=0, tc=0, cur=1;

	ifstream fin("agrinet.in");
	ofstream fout("agrinet.out");

	fin >> N;
	for (i=1;i<=N;i++)
	{
		dist[i]=100000;
		source[i]=1;
		for (j=1;j<=N;j++)
		{
			fin >> C[i][j];
		}
	}
	
	while (ts!=N)
	{
		for (i=1;i<=N;i++)
		{
			if ((V[cur] || dist[i]<dist[cur]) && !V[i])
			{
				cur=i;
			}
		}
		ts++;
		tc+=C[source[cur]][cur];
		V[cur]=true;

		for (i=1;i<=N;i++)
		{
			if (C[cur][i]<=dist[i]) 
			{
				source[i]=cur;
				dist[i]=C[cur][i];
			}
		}
	}
	cout << tc << endl;
	fout << tc << endl;
	return 0;
}
