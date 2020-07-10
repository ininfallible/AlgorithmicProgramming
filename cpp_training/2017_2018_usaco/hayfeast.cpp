//2017 Dec Gold P3
//
#include <bits/stdc++.h>
#include <climits>
#include <set>
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
ll M;
const int MAXN = 1e5+1;
int fl[MAXN], sp[MAXN];
int main()
{
	ifstream fin("hayfeast.in");
	ofstream fout("hayfeast.out");
	int i,j,k;
	fin >> N >> M;
	for (i=0;i<N;i++)
		fin >> fl[i] >> sp[i];

	ll flsum = 0; 
	int spmin=INT_MAX;
	multiset<int> spset;

	int hi=0,lo=0;
	for (hi=0;hi<N;hi++)
	{
		flsum += fl[hi];
		spset.insert(sp[hi]);
		while (flsum - fl[lo] >= M)
		{
			spset.erase(spset.find(sp[lo]));
			flsum -= fl[lo];
			lo++;
		}
		if (flsum >= M)
		{
			spmin = min(spmin, *spset.rbegin());
		}

	}
	fout << spmin << endl;
	return 0;
}

