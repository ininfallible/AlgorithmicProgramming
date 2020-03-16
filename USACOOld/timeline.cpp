//2020 Feb Gold P1

#include <iostream>
#include <fstream>
#include <vector>
#include <queue>

using namespace std;

const int MAX=500001;

int N, M, C;

typedef pair<int, int> pii;

vector<pii> adj[MAX];

int main()
{
	//cout << "P-1"<<endl;
	int i,j,k;
	int a,b,c;
	ifstream fin("timeline.in");
	ofstream fout("timeline.out");
	fin >> N >> M >> C;
	int root[MAX]={1};
	int t[MAX];

	for (i=1;i<=N;i++) fin >> t[i];



	//cout << "P0"<<endl;
	for (i=0;i<C;i++)
	{
		fin >> a >> b >> c;
		adj[a].push_back(make_pair(b,c));
		root[a]=0;
	}

	//cout << "P1"<<endl;

	queue<int> Q;
	for (i=0;i<N;i++)
	{
		if (!root[i]) Q.push(i);
		while (!Q.empty())
		{
			int cur = Q.front(); Q.pop();
			for (int j=0;j<adj[cur].size();j++) 
				if (t[adj[cur][j].first]<t[cur]+adj[cur][j].second)
				{
					t[adj[cur][j].first]=t[cur]+adj[cur][j].second;
					Q.push(adj[cur][j].first);
				}
		}
	}
	 
	//cout << "P2"<<endl;
	for (i=1;i<=N;i++) fout << t[i] << endl;
	return 0;
}
