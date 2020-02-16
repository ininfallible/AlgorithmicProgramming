//2017 Feb. Gold P1
#include <iostream>
#include <fstream>
#include <queue>
#include <string.h>

using namespace std;

typedef pair<int, int> pii;

int N, T;
int field[101][101];
int dist[101][101];
int dr[]{ 3, 2, 1, 0,-1,-2,-3,-2,-1, 0, 1, 2, 0, 0,-1, 1};
int dc[]{ 0, 1, 2, 3, 2, 1, 0,-1,-2,-3,-2,-1,-1, 1, 0, 0};

int main()
{
	int i,j,out=0x3f3f3f3f;
	ifstream fin("visitfj.in");
	ofstream fout("visitfj.out");
	fin >> N >> T;
	for (i=0;i<N;i++)
	for (j=0;j<N;j++)
		fin >> field[i][j];
	queue<pii> Q;
	Q.push(make_pair(0,0));
	memset(dist, 0x3F ,sizeof(dist));
	dist[0][0]=0;
	while (!Q.empty())
	{
		pii cur = Q.front();
		Q.pop();
		int d = cur.first;
		int r = cur.second/N;
		int c = cur.second%N;

		if (d != dist[r][c])
			continue;
		
		int fd = abs(N-1-r) + abs(N-1-c);
		if (fd<=2) {
			out = min(out, d + fd * T);
		}
		
		//sizeof(array)/sizeof(item) returns length
		for (i=0;i<sizeof(dr)/sizeof(int);i++)
		{
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr<0 || nc<0 || nc>=N || nr>=N
			|| dist[nr][nc]<d+field[nr][nc]+3*T)
				continue;
			dist[nr][nc] = d+field[nr][nc]+3*T;
			Q.push(make_pair(dist[nr][nc], nr*N+nc));
		}
	}
	fout << out << endl;
	return 0;
}
