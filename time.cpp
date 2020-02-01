//2019-2020 January Gold P1

#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <cstring>
using namespace std;

int N,M,C;
int c[1000];
vector<int> edges[1000];
//val stores: [node][depth]=money
int val[1000][505];
int main()
{
	int i,j,k;
	ifstream fin("time.in");
	ofstream fout("time.out");
	fin >> N >> M >> C;
	//read cost
	for (i=0;i<N;i++) fin >> c[i];
	//adjacency lists
	for (i=0;i<M;i++)
	{
		fin >> j >> k;
		edges[j-1].push_back(k-1);
	}

	//init stuff
	memset(val, 0, sizeof(val));
	//BFS with limited depth
	queue<pair<int,int>> Q;
	Q.push(pair<int,int>(0,0));
	while(!Q.empty())
	{
		int cc = Q.front().first;
		int cd = Q.front().second;
		//cout<<"processing "<<cc<<" "<<cd<<endl;
		Q.pop();
		//depth limit
		if (C*(2*cd+1)>1000) continue;
		//for each neighbor:
		for(i=0;i<edges[cc].size();i++)
		{
			int nc=edges[cc][i];
			//cout << nc << endl;
			if (val[cc][cd]+c[nc]>=val[nc][cd+1])
			{
				Q.push(pair<int,int>(nc,cd+1));
				val[nc][cd+1]=val[cc][cd]+c[nc];
			}
		}
	}
	int ans=0;
	for (int i=0;i<505;i++)
		ans = max(ans,val[0][i]-C*i*i);
	//cout<<"ans:"<<ans<< endl;
	fout<< ans<< endl;

	return 0;
}
