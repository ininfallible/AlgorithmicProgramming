//placeholder
#include <bits/stdc++.h>
using namespace std;

#define fi first
#define se second
typedef vector<int> vi;
typedef pair<int, int> pii;
typedef long long ll;
const int INF = 1<<20;

int H, W, Q;
char gr[501][501];
int h[501][501], v[501][501];
int main()
{
	int i,j,k;
	cin >> H >> W;
	for (i=1;i<=H;i++)
	{
		scanf("%s", gr[i]+1);
		for (j=1;j<=W;j++)
		{
			h[i][j] = h[i-1][j]+h[i][j-1]-h[i-1][j-1]+(gr[i][j]=='.'&&gr[i][j-1]=='.');
			v[i][j] = v[i-1][j]+v[i][j-1]-v[i-1][j-1]+(gr[i][j]=='.'&&gr[i-1][j]=='.');
		}
	}
	
	int r1, c1, r2, c2;
	cin >> Q;
	for (i=0;i<Q;i++)
	{
		scanf("%d%d%d%d", &r1, &c1, &r2, &c2);
		int ans = 0;
		ans += h[r2][c2]-h[r2][c1]-h[r1-1][c2]+h[r1-1][c1];
		ans += v[r2][c2]-v[r2][c1-1]-v[r1][c2]+v[r1][c1-1];
		cout << ans << endl;
	}
	return 0;
}

