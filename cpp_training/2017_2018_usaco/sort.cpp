//2018 US Open Gold P1
//implementing sol from website
/*
 * EXPLANATION OF BTREE
 * for each number - sum represents how many numbers are less than the index
 * number (not number at index)
 * when new number is introduced, eg. 5; numbers less than or equal to 5
 * in the btree's sums will increase by 1
 * total nums processed - nums <= index num processed = nums > index processed
 */
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
pii nbrs[100001];
int btree[100100];
int sum(int k)
{
	int s = 0;
	while (k>=1)
	{
		s += btree[k];
		k -= k&-k;
	}
	return s;
}
void add(int k, int x) 
{
	while (k <= N)
	{
		btree[k] += x;
		k += k&-k;
	}
}
int main()
{
	ifstream fin("sort.in");
	ofstream fout("sort.out");
	int i,j,k;
	fin >> N;
	for (i=1;i<=N;i++)
	{
		fin >> nbrs[i].fi;
		nbrs[i].se = i;
	}
	sort(nbrs+1, nbrs+N+1);
	//for (i=1;i<=N;i++) cout << nbrs[i].fi << " " << nbrs[i].se << endl;
	int ans = 1;
	for (i=1;i<N;i++)
	{
		add(nbrs[i].second, 1);
		ans = max(ans, i-sum(i)); //total nums - nums smaller than index no.
	}
	fout << ans << "\n";
	return 0;
}

