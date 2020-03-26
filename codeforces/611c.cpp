#include <bits/stdc++.h>

using namespace std;

int H, W, Q;
int ps[501][501][4];
int arr[501][501];
const int dx[]{-1, 0, 1, 0};
const int dy[]{0, -1, 0, 1};

int main()
{
	cin >> H >> W; 
	for (int i=1;i<H;i++)
	{
		string s;
		cin >> s;
		for (int j=0;j<W;j++)
			arr[i][j+1]=(s[j]=='#');
	}
	//calc ps
	for (int i=1;i<H;i++)
	for (int j=1;j<W;j++)
	{
		if (!arr[i][j]) continue;
	}
	return 0;
}
