// 2020 US Open Gold P1
// implementing solution
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

const int MAXN = 1e5+5;

// sum op. returns no. of smaller numbers in front of ins. no. 
// indexes from 1, 1 -> 0
ll btree[MAXN];
// larger no. in pair -> no. of inversions generated
ll cnt[MAXN];
int N;

// using a Fenwick (Binary Indexed) Tree for range queries
int sum (int k)
{
	k++; //indexing from 1
	int s=0;
	while (k>=1)
		s += btree[k], k -= k&-k;
	return s;
}
void add (int k)
{
	k++; //indexing from 1
	while (k <= N+2)
		btree[k]++, k += k&-k;
}

int main()
{
	int i,j,k;
	
	ifstream fin("haircut.in");
	ofstream fout("haircut.out");
	fin >> N;
	int cur = -1;

	for (i=0;i<N;i++)
	{
		fin >> cur;
		cnt[cur+1] += sum(N) - sum(cur);
		add(cur);
	}
	// debug
	// for (i=0;i<=N;i++) cout << cnt[i] << " ";
	ll ans = 0;
	for (j=0;j<N;j++)
	{
		ans += cnt[j];
		fout << ans << endl;
	}
	
	return 0;
}

