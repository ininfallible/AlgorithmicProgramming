//2017 Feb Gold P3
//implmenting sol - did not expect this to be a btree problem
//optimizing from N2 to NlogN is hard
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
const int MAXN = 25000;

int bt[MAXN*4+1];

int N;

int sum(int k)
{
	int ret = 0;
	while (k>=1)
	{
		ret += bt[k];
		k -= k&-k;
	}
	return ret;
}
void add(int k, int x)
{
	while (k < 2*N)
	{
		bt[k]+=x;
		k += k&-k;
	}
}

int main()
{
	ifstream fin("circlecross.in");
	ofstream fout("circlecross.out");
	int i,j,k;
	fin >> N;

	//need to learn how this initialization works
	vector<pii> pos(N, mp(-1, -1));

	for (i=0;i<2*N;i++)
	{
		cin >> j; j--; // indexing from 0
		if (pos[j].fi==-1) pos[j].fi=j;
		else pos[j].se=j;
	}
	sort(pos.begin(), pos.end());
	int ans=0;
	for (int i=0;i<pos.size();i++)
	{
		ans += sum(pos[i].se) - sum(pos[i].fi);
		add(pos[i].se, 1);
	}
	cout << ans << endl;
	return 0;
}

