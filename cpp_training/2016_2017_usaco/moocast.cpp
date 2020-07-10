//2016-2017 December Gold P1

#include <iostream>
#include <fstream>
#include <cmath>
#include <cstring>

using namespace std;

int N;
int C[1001][2], D[1001][1001];
int cost(int a, int b)
{
	return pow(C[a][0]-C[b][0],2)+pow(C[a][1]-C[b][1],2);
}

int main()
{
	int i,j,k;
	ifstream fin("moocast.in");
	ofstream fout("moocast.out");

	fin >> N;
	for (int i=0;i<N;i++)
		fin >> C[i][0] >> C[i][1];
	//construct graph
	for (i=0;i<N;i++)
	for (j=0;j<N;j++)
		D[i][j]=D[j][i]=cost(i,j);

	//run prim's
	int tdist[1001], ans=0;
	memset(tdist, 0x3F,sizeof(tdist));
	tdist[0]=0;
	for (int ts=0;ts<N;ts++)
	{
		//determine which node is easiest to reach
		//always better to have two smaller walkie-talkies than 1 bigger b/c pythagoras
		int m_i=0;
		for (i=0;i<N;i++)
			if ((tdist[i]<tdist[m_i]&&tdist[i]!=-1)||tdist[m_i]==-1)
				m_i=i;
		//process m_i
		for (int tc=0;tc<N;tc++)
			if (tc!=m_i&&D[m_i][tc]<tdist[tc]) tdist[tc]=D[m_i][tc];
		ans=max(ans,tdist[m_i]);
		tdist[m_i]=-1;
	}
	//cout << ans << endl;
	fout << ans << endl;
	return 0;
}
