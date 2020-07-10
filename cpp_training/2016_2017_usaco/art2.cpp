//2017 US Open Gold P3
#include <bits/stdc++.h>
using namespace std;

#define pb push_back
#define mp make_pair
#define fi first
#define se second
typedef vector<int> vi;
typedef pair<int, int> pii;
typedef long long ll;
const int INF = 1<<20;

const int MAXN = 1e5+1;
int N;
int minx[MAXN], maxx[MAXN];
int color[MAXN];
vi color_stack;
int main()
{
	ifstream fin("art2.in");
	ofstream fout("art2.out");
	int i,j,k;
	fin >> N;
	fill(minx, minx+MAXN, INF);
	for (i=0;i<N;i++)
	{
		fin >> j;
		color[i]=j;
		if (i>maxx[j]) maxx[j] = i;
		if (i<minx[j]) minx[j] = i;
	}
	/* debug
	for (i=1;i<N;i++)
		cout << minx[i] << " " << maxx[i] << endl;
	*/
	int ans = 0;
	for (i=0;i<N;i++)
	{
		int c = color[i];
		if (c==0) continue;
		// color start
		if (i==minx[c]) 
		{
			color_stack.pb(c);
			ans = max(ans, (int)color_stack.size());
		}
		// makes sure nested structure of color layering is consistent
		if(*color_stack.rbegin()!=c) 
		{
			fout << -1 << '\n';
			return 0;
		}
		//color end
		if (i==maxx[c]) color_stack.pop_back();
	}
	fout << ans << '\n';
	return 0;
}

