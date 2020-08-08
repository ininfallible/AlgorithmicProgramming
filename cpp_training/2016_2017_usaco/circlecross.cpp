//2018 Feb Gold P3
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
const int MAXN = 50000;
int ord[MAXN*2+1];
int used[MAXN+1];
int ft[MAXN*4+1];

int sum(int k)
{
	int s=0;
	while (k>=1)
	{
		s += ft[k];
		k -= k&-k;
	}
	return s;
}

void add(int k)
{
	while (k <= 2*N+2) {
		ft[k] ++; 
		k += k&-k;
	}
}

int main()
{
	ifstream fin("circlecross.in");
	ofstream fout("circlecross.out");
	int i,j,k;
	fin >> N;
	int hi_pos[N+1], lo_pos[N+1], cur;
	for (i=1;i<=2*N;i++)
	{
		fin >> cur;
		ord[i]=cur;
		used[cur]++;
		if (used[cur]==1) lo_pos[cur] = i;
		if (used[cur]==2) hi_pos[cur] = i;
	}

	//debug
	//for (i=0;i<=N;i++)
	//	cout << i << " " << lo_pos[i] << " " << hi_pos[i] << endl;

	long ans = 0;
	fill_n(used, N+1, 0);

	for (i=1;i<=2*N;i++)
	{
		cur = ord[i];
		used[cur]++;
		if (used[cur]==1)
		{
			//cout << "summing at " << lo_pos[cur] << endl;
			ans += sum(2*N+1-lo_pos[cur]) - sum(2*N+1-hi_pos[cur]); 
			//cout << "adding at " << hi_pos[cur] << endl;
			add(2*N+1-hi_pos[cur]);

			//debug
			/*
			for (j=2*N;j>=0;j--)
			{
				cout << sum(j) << " ";
			}
			cout << endl;
			*/
		}
	}
	fout << ans << endl;
	cout << ans << endl;
	return 0;
}

