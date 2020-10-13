//placeholder
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

int N;
const int MAXN = 1e5+1;
int ft[2*MAXN];
int h[MAXN];
map<int, int> hpos;

int sum(int i)
{
	int s=0;
	while(i>=1)
		s += ft[i],i-=i&-i;
	return s;
}
void add(int i)
{
	while (i<=N*2)
		ft[i]++, i += i & -i;
}
int ccomp(int a, int b)
{
	if (a < b) swap(a, b);
	//cout << ((b*2)<a || (b==0 && a>0)) << endl;
	return (b*2)<a || (b==0 && a>0);
}

int main()
{
	int i,j,k;
	ifstream fin("bphoto.in");
	ofstream fout("bphoto.out");

	fin >> N;
	for (i=0;i<N;i++)
	{
		fin >> h[i];
		hpos.insert(mp(h[i],i));
	}
	sort(h, h+i);
	
	// for (i=0;i<N;i++) cout << h[i] << " " << hpos[h[i]] << endl; 

	int ans=0;
	// biggest to smallest
	for (i=N-1;i>=0;i--)
	{
		int cur = h[i];
		int lh = sum(hpos[cur]+1);
		int rh = (N-i) - lh-1;

		// cout << h[i]<<": "<<lh<<", "<<rh<<endl;

		ans += ccomp(lh, rh);
		add(hpos[h[i]]+1);
	}
	fout << ans << endl;
	return 0;
}

