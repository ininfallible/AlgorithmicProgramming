//2018 US Open Gold P2
//implmenting sol from website
#include <bits/stdc++.h>
#include <queue>
using namespace std;

#define pb push_back
#define mp make_pair
#define fi first
#define se second
typedef vector<int> vi;
typedef pair<int, int> pii;
typedef long long ll;
const int INF = 1<<20;

int N, M;
vector<pii> edges[50001];
// topological sort
/* for a directed graph: listing vertices such that for every edge, the par
 * node comes before the child node
 * needs: no directed cycles
 */

vi children[50001];
int parent[50001], result[50001];

bool doable(int n, int k) 
{
	//reset
	for (int i=0;i<n;i++)
	{
		children[i].clear();
		parent[i] = 0;
	}
	for (int i=0;i<k;i++)
	for (auto p : edges[i])
	{
		children[p.first].push_back(p.second);
		parent[p.second]++;
	}
	priority_queue<int> tP;
	//initialize with cows with no parents
	//negative id means lowest lexographic cow comes first
	for (int i=0;i<N;i++) if (!parent[i]) tP.push(-i);

	//topological sort, should only need to process N nodes
	//doesn't add duplicates of any node since there's a requirement that all parents processed
	for (int i=0;i<N;i++)
	{
		if (tP.empty()) return false;
		int curr = -tP.top();
		tP.pop();
		//answer
		result[i] = curr;
		//for each child, since we've visited parent, remove 1 from parent count
		//if that was last parent, we can process that cow
		for (int next : children[curr])
		{
			parent[next]--;
			if (parent[next]==0) tP.push(next);
		}
	}
	return true;
}

int par[50001];
int res[50001];

int main()
{
	ifstream fin("milkorder.in");
	ofstream fout("milkorder.out");
	int i,j,k,prev,curr;
	fin >> N >> M;
	for (i=0;i<M;i++)
	{
		fin >> j;
		int prev;
		for (k=0;k<j;k++)
		{
			fin >> curr;
			curr--;  //indexing from 0
			if (k>=0) edges[i].pb(mp(prev, curr));
			prev = curr;
		}
	}

	//bin search 
	int lb = 0, ub = M+1;
	while (ub > lb+1)
	{
		int mid = (lb + ub) /2;
		if (doable(N, mid))
			lb = mid;
		else 
			ub = mid;	
	}
	int ans = lb;

	doable(N, ans);
	for (i=0;i<N;i++)
	{
		fout << result[i]+1;
		if (i<N-1) fout << " ";
	}
	fout << '\n';
	
	return 0;
}

