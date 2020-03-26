//2019 Feb. Gold P1
//implementing solution
//help wthatdsajflksdjfj
#include <bits/stdc++.h>

using namespace std;

const int MAXN = 100001;
const int MAXD = 17;

#define pb push_back

int N, Q;
int vals[MAXN];
vector<int> adj[MAXN];

int treesz[MAXN];
int vertextosegtree[MAXN];
int topchain[MAXN];
int lca[MAXN][MAXD];
int depth[MAXN];
int segtree[4*MAXN];

void segtreeupd(int idx, int l, int r, int i, int v)
{
	if (l==r) segtree[idx]=v;
	else
	{
		int m = (l+r)/2;
		if (i<=m) segtreeupd(2*idx, l, m, i, v);
		else segtreeupd(2*idx+1, m+1, r,  i, v);
		segtree[idx]= segtree[2*idx] ^ segtree[2*idx+1];
	}
}

void segtreeupd(int i, int v)
{
	segtreeupd(1, 0, MAXN-1, i, v);
}

int segtreeqry(int idx, int l, int r, int lhs, int rhs)
{
	if (l>=lhs&&r<=rhs) return segtree[idx];
	int ret = 0;
	int m = (l+r)/2;
	if (m  >=lhs) ret ^= segtreeqry(2*idx+1,m+1,r,lhs,rhs);
	if (m+1<=rhs) ret ^= segtreeqry(2*idx+1,m+1,r,lhs,rhs);
	return ret;
}
int segtreeqry(int l, int r)
{
	return segtreeqry(1, 0, MAXN-1, l, r);
}

int getLCA(int a, int b)
{
	//make sure a is deeper
	if (depth[a]<depth[b]) swap(a,b);
	//push a upwards
	for(int d=MAXD-1; d>=0;d--)
	{
		if (depth[a]-(1<<d) >= depth[b])
		{
			a = lca[a][d];
		}
	}
	//push both upwards, as long as ancestors are different
	for(int d = MAXD-1; d>=0;d--)
	{
		if(lca[a][d]!=lca[b][d])
		{
			a = lca[a][d];
			b = lca[b][d];
		}
	}
	if(a != b)
	{
		a = lca[a][0];
		b = lca[b][0];
	}
	return a;
}

void initLCA()
{
	//calculates 2^dth ancestor of each vertex
	//d being from 0 to 17
	for (int d=1;d<MAXD;d++)
	for (int i=0;i<MAXN;i++)
		lca[i][d] = lca[lca[i][d-1]][d-1];
}

void dfsForHLD(int cur, int topPtr, int par, int& internalsegtreeidx)
{
	vertextosegtree[cur] = internalsegtreeidx++;
	segtreeupd(vertextosegtree[cur], vals[cur]);
	topchain[cur]=topPtr;
	int largestchild = -1;
	int largestsz = -1;
	for (int out : adj[cur])
	{
		if (out == par) continue;
		if (treesz[out]>largestsz)
		{
			largestsz = treesz[out];
			largestchild = out;
		}
	}
	if (largestchild < 0) return;
	dfsForHLD(largestchild, topPtr, cur, internalsegtreeidx);
	for(int out: adj[cur])
	{
		if (out == par || out == largestchild) continue;
		dfsForHLD(out, out, cur, internalsegtreeidx);
	}
}

void dfsForSize(int cur, int par)
{
	//size of subtree
	treesz[cur]++;
	for (int n : adj[cur])
	{
		if (n != par)
		{
			depth[n] = depth[cur]+1;
			lca[n][0] = cur;
		}
		dfsForSize(n, cur);
		treesz[cur] += treesz[n];
	}
}
void initHLD()
{
	dfsForSize(0, -1);
	initLCA();
	int internalsegtreeidx = 0;
	dfsForHLD(0, 0, -1, internalsegtreeidx);
}

int pathQuery(int child, int par)
{
	int ret = 0;
	while (child != par)
	{
		if (topchain[child]==child)
		{
			//light edge
			ret ^= vals[child];
			child = lca[child][0];
		}
		else if (depth[topchain[child]]>depth[par])
		{
			ret ^= segtreeqry(vertextosegtree[topchain[child]],vertextosegtree[child]);
			child = lca[topchain[child]][0];
		}
		else 
		{
			ret ^= segtreeqry(vertextosegtree[par]+1, vertextosegtree[child]);
			break;
		}
	}
	return ret;
}
int query(int a, int b)
{
	int r = getLCA(a, b);
	return pathQuery(a, r) ^ pathQuery(b, r) ^ vals[r];
}

int main()
{
	int i,j,k;
	ifstream fin("cowland.in");
	ofstream fout("cowland.out");
	
	fin >> N >> Q;

	for (i=0;i<N;i++)
		fin >> vals[i];
	for (i=1;i<N;i++)
	{
		fin >> j >> k;
		j--; k--;
		adj[j].pb(k);
		adj[k].pb(j);
	}
	initHLD();
	while (Q--)
	{
		int t; cin >> t;
		if (t==1)
		{
			int i, v;
			cin >> i >> v;
			vals[i--] = v;
			segtreeupd(vertextosegtree[i], v);
		}
		else
		{
			int a, b;
			cin >> a >> b;
			cout << query(a--, b--) << "\n";
		}
	}
	return 0;
}
