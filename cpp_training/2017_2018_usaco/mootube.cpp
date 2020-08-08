//2018 Jan Gold P1
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

int N, Q;
const int MAXN = 1e5+1;

int size[MAXN];
int par[MAXN];
int ans[MAXN];

int find(int a)
{
	while (par[a] != a) a=par[a];
	return a;
}
void join(int a, int b)
{
	a = find(a), b = find(b);
	if (size[a] < size[b]) swap(a, b);
	par[b] = a;
	size[a] += size[b];
}
// to find size of a component, use size[find(i)]

// threshold, video id, query id
struct query {int k, v, id;};
bool q_sort(query const& l, query const& r)
{
	// sort descending
	return l.k > r.k;
}

int main()
{
	int i,j,k;
	ifstream fin("mootube.in");
	ofstream fout("mootube.out");
	fin >> N >> Q;
	// init union-find
	for (i=1;i<=N;i++) size[i]=1, par[i]=i;
	vector<pair<int, pii>> edges;
	// init graph
	int a, b, r;
	for (i=1;i<N;i++)
	{
		fin >> a >> b >> r;
		edges.pb(mp(-r, mp(a, b)));
	}
	sort(edges.begin(), edges.end());
	// init queries
	vector<query> queries;
	for (i=0;i<Q;i++)
	{
		// threshold, vid id
		fin >> j >> k;
		queries.pb(query{j, k, i});
	}
	sort(queries.begin(), queries.end(), q_sort);

	// debug
	// for (i=0;i<N-1;i++) cout << edges[i].se.fi << " " << edges[i].se.se << endl;
	
	// algo - for each query - run all edges with r > query threshold
	int e=0;
	for (int i=0;i<Q;i++)
	{
		// cout << i << endl;
		query cq = queries[i];
		while(cq.k<=-edges[e].fi && e<N-1)
		{
			join(edges[e].se.fi, edges[e].se.se);
			e++;
		}
		ans[cq.id] = size[find(cq.v)]-1;
	}
	for (i=0;i<Q;i++) fout << ans[i] << endl;
	return 0;
}

