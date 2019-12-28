/*
ID: derek.l3
PROB: camelot
LANG: C++11
*/

#include <iostream>
#include <fstream>
#include <queue>
#include <cstring>
 
const int MAXN = 10400;
const int MAXR = 42;
const int MAXC = 42;

using namespace std;

int gc[MAXR][MAXC];
int kc[MAXR][MAXC];
int km[MAXR][MAXC];
int nm[MAXR][MAXC][2];
int DR[8]{-2,-2,-1,-1, 1, 1, 2, 2};
int DC[8]{ 1,-1, 2,-2, 2,-2, 1,-1};
int K[2];
int R, C;

int step (int r, int c, int kf)
{
	int i, f=0, d=nm[r][c][kf];
	for (i=0;i<8;i++)
	{
		int tr=r+DR[i];
		int tc=c+DC[i];
		if(0<tr&&tr<=R&&0<tc&&tc<=C&&nm[tr][tc][kf]>d+1)
		{
			nm[tr][tc][kf]=d+1;
			f=1;
		}
	}
	if (kf==0&&nm[r][c][1]>d+km[r][c]) {
		nm[r][c][1]=d+km[r][c];
		f=max(f,km[r][c]);
	}
	return f;
}
//col: letter -> number; row: number
//indexing from 1
void calc_dist(int r, int c) {
	int i, j, f, d, max_dist;
	for	(i=1;i<=R;i++)
	for (j=1;j<=C;j++)
		nm[i][j][0] = nm[i][j][1] = MAXN;
	
	nm[r][c][0]=0;
	max_dist = nm[r][c][1] = km[r][c];

	for (d=0;d<=max_dist;d++)
	{
		for (i=1;i<=R;i++)
		for (j=1;j<=C;j++)
		{
			if (nm[i][j][0]==d)
			{
				f = step(i,j,0);
				max_dist=max(d+f,max_dist);
			}
			if (nm[i][j][1]==d)
			{
				f = step(i,j,1);
				max_dist=max(d+f,max_dist);
			}
		}
	}
}
int main()
{
	int i, j, r;
	char c;
	ifstream fin("camelot.in");
	ofstream fout("camelot.out");
	fin >> R >> C >> c >> r;
	K[0]=r;
	K[1]=c-'A'+1;
	//debug
	//cout << R << " " << C << endl;
	cout << K[0] << " " << K[1] << endl;
	//calculate king distances
	for (i=1;i<=R;i++)
	for (j=1;j<=C;j++)
	{
		km[i][j]=min(abs(K[0]-i),abs(K[1]-j))+abs(abs(K[0]-i)-abs(K[1]-j));
		kc[i][j]=km[i][j];
		gc[i][j]=0;
	}	
	
	//debug
	/*
	for (i=1;i<=R;i++)
	{
		for (j=1;j<=C;j++)
			cout << km[i][j] << " ";
		cout << endl;
	}
	*/

	while (fin >> c >> r)
	{
		c=c-'A'+1;
		calc_dist(r, c);
		//printf("r:%d, c:%d\n", r, c);
		for (i=1;i<=R;i++)
		for (j=1;j<=C;j++)
		{
			gc[i][j] += nm[i][j][0];

			if(nm[i][j][1]-nm[i][j][0]<kc[i][j])
				kc[i][j] = nm[i][j][1] - nm[i][j][0];
		}
	}

	int ans = gc[1][1]+kc[1][1];
	for (i=1;i<=R;i++)
	for (j=1;j<=C;j++)
		ans=min(ans, gc[i][j]+kc[i][j]);

	/*
	for (i=1;i<=R;i++)
	{
		for (j=1;j<=C;j++) printf("%7d", gc[i][j]+kc[i][j]);
		cout << endl;
	}
	*/
	fout << ans << endl;
	//cout << ans << endl;

	return 0;
}
