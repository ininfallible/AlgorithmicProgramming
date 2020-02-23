//2017 January Gold P3

#include <iostream>
#include <fstream>
#include <queue>

using namespace std;

const int MAXN=22;
int N;
const int dx[]{0, 1, 0,-1};
const int dy[]{1, 0,-1, 0};
struct state 
{
	state (int a, int b, int c, int d, int e, int f):
		x1(a), y1(b), x2(c), y2(d), d1(e), dist(f) 
	{
		d2=(d1+1)%4;
	}
	int x1, y1, x2, y2, d1, d2, dist;
};
int main()
{
	int i,j;
	int B[MAXN][MAXN];
	ifstream fin("cownav.in");
	ofstream fout("cownav.out");
	fin >> N;
	string tmp;
	for (i=N;i>0;i--)
	{
		fin >> tmp;
		for (j=1;j<=N;j++) B[i][j]=tmp[j-1]=='H';
	}
	queue<state> Q;
	int dp [MAXN][MAXN][MAXN][MAXN][4];
	dp[1][1][1][1][0]=1;
	Q.push(state(1, 1, 1, 1, 0, 0));
	int out = 1<<20;
	int nd, nx, ny;
	int* np;
	while (!Q.empty())
	{
		state c = Q.front();
		Q.pop();
		//case end
		if(c.x1==N&&c.y1==N&&c.x2==N&&c.y2==N) 
		{
			out = c.dist;
			break;
		}
		//case turn left
		nd = c.d1-1; if (nd<0) nd+=4;
		np = &dp[c.x1][c.y1][c.x2][c.y2][nd];
		if (*np==0) {
			*np = 1;
			Q.push(state(c.x1,c.y1,c.x2,c.y2,nd,c.dist+1));
		}
		//case turn right
		nd = c.d1+1; nd%=4;
		np = &dp[c.x1][c.y1][c.x2][c.y2][nd];
		if (*np==0) {
			*np = 1;
			Q.push(state(c.x1,c.y1,c.x2,c.y2,nd,c.dist+1));
		}
		//case forwards
		state ns = c; ns.dist++;
		nx = c.x1+dx[c.d1];
		ny = c.y1+dy[c.d1];
		if (B[nx][ny]==0 && 1<=nx&&nx<=N && 1<=ny&&ny<=N && !(c.x1==N&&c.y1==N))
		{
			ns.x1=nx; ns.y1=ny;
		}
		nx = c.x2+dx[c.d2];
		ny = c.y2+dy[c.d2];
		if (B[nx][ny]==0 && 1<=nx&&nx<=N && 1<=ny&&ny<=N && !(c.x2==N&&c.y2==N))
		{
			ns.x2=nx; ns.y2=ny;
		}
		np = &dp[ns.x1][ns.y1][ns.x2][ns.y2][ns.d1];
		if (*np==0) {
			*np=1;
			Q.push(ns);
		}
	}
	fout << out << endl;
	return 0;
}
