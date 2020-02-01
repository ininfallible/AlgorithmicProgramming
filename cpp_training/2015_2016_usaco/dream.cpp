//2015 Gold P3

#include <iostream>
#include <queue>
#include <fstream>

using namespace std;

const int MAXN=1002;
const int INF=1<<20;
int dr[]{-1, 0, 0, 1};
int dc[]{ 0, 1,-1, 0};

int m[MAXN][MAXN];
int v[MAXN][MAXN][2];
int R, C;

struct tile
{
	int r, c, f;
	tile(int x, int y, int z):r(x), c(y), f(z) {}
};
queue<tile> Q;

int main()
{
	int i, j, k;
	ifstream fin("dream.in");
	ofstream fout("dream.out");
	fin >> R >> C;

	for (i=1;i<=R;i++)
	for (j=1;j<=C;j++)
	{
		fin >> m[i][j];
		v[i][j][0] = v[i][j][1] = INF;
	}
	//debug
	//cout << "m" << endl;

	Q.push(tile(1,1,0));
	v[1][1][0]=0;
	while (!Q.empty()&&(v[R][C][0]==INF&&v[R][C][1]==INF))
	{
		tile ct = Q.front(); Q.pop();
		//debug
		//printf("processing R:%d C:%d\n", ct.r, ct.c);
		//printf("F:%d D: %d\n", ct.f, v[ct.r][ct.c][ct.f]);
		
		int tr, tc, d;
		for (i=0;i<4;i++)
		{
			d = v[ct.r][ct.c][ct.f]+1;
			tr = ct.r+dr[i];
			tc = ct.c+dc[i];
			//debug
			//fout << tr << " " << tc << endl;
			if (!(0<tr&&tr<=R&&0<tc&&tc<=C)) continue;
			int type = m[tr][tc];
			if (type==1&&d<v[tr][tc][ct.f])
			{
				v[tr][tc][ct.f]=d;
				Q.push(tile(tr, tc, ct.f));
			} 
			else if (type==2&&d<v[tr][tc][1])
			{
				v[tr][tc][1]=d;
				Q.push(tile(tr, tc, 1));
			}
			else if (type==3&&d<v[tr][tc][1]&&ct.f==1)
			{
				v[tr][tc][1]=d;
				Q.push(tile(tr, tc, 1));
			}
			else if (type==4)
			{
				while(0<tr&&tr<=R&&0<tc&&tc<=C&&m[tr][tc]==4)
				{
					tr+=dr[i];
					tc+=dc[i];
					d++;
				}
				if (!(0<tr&&tr<=R&&0<tc&&tc<=C)||
					m[tr][tc]==0||m[tr][tc]==3)
				{
					tr-=dr[i];
					tc-=dc[i];
					d--;
					if (d<v[tr][tc][0])
					{
						v[tr][tc][0]=d;
						Q.push(tile(tr,tc,0));
					}
				}
				else if (m[tr][tc]==1&&d<v[tr][tc][0])
				{
					v[tr][tc][0]=d;
					Q.push(tile(tr,tc,0));
				}
				else if (m[tr][tc]==2&&d<v[tr][tc][1])
				{
					v[tr][tc][1]=d;
					Q.push(tile(tr,tc,1));
				}
			}
		}
	}

	
	//debug
	/*
	cout << "v, 0" << endl;
	for (i=1;i<=R;i++)
	{
		for (j=1;j<=C;j++)
			cout << v[i][j][0] << " ";
		cout << endl;
	}
	cout << "v, 1" << endl;
	for (i=1;i<=R;i++)
	{
		for (j=1;j<=C;j++)
			cout << v[i][j][1] << " ";
		cout << endl;
	}
	*/
	int ans = min(v[R][C][0], v[R][C][1]);
	if (ans==INF) ans=-1;
	fout << ans << endl;
	cout << ans << endl;
	return 0;
}
