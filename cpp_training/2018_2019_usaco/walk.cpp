//2019 US Open Gold P2
//implementing sol from website
//ad-hoc/graph problem
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

const int MAXN = 7500;
int N, K, v[MAXN+1];
ll D[MAXN+1];

//https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/
void jarnik(int source)
{
	//set dist from tree of all nodes to inf
	for (int i=1;i<=N;i++) D[i] = 2019201997; 
	for (int i=0;i<N;i++)
	{
		int min_i = 0;
		for (i=1;i<=N;i++) //find closest node to tree
			if (!v[i] && (min_i==0 || D[i]<D[min_i])) min_i = i;
		v[min_i] = 1;
		for (int j=1;j<=N;j++) // update dists from tree with min_i
			if(!v[j])
				D[j] = min(D[j], (2019201913LL*min(min_i,j) + 2019201949LL*max(min_i,j))%2019201997LL);
	}
}

int main()
{
	ifstream fin("walk.in");
	ofstream fout("walk.out");
	int i,j,k;
	jarnik(1);
	sort(D+1, D+N+1);
	fout << D[N+1-K] << "\n"; //N+1-K equivalent to removing edges
	
	return 0;
}

