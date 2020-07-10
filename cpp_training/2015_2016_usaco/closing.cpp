//2016 US Open Gold P2
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

const int MAXN = 2e5+1;
int N, M;
int link[MAXN], size[MAXN];
bool used[MAXN], res[MAXN];
int ord[MAXN];
vi adj[MAXN];

int find(int x) 
{
	while (x!=link[x]) x=link[x];
	return x;
}
bool same(int a, int b) {return find(a) == find(b);}
void join(int a, int b)
{
	a = find(a), b = find(b);
	if (size[a]<size[b]) swap(a,b); //make sure b is smaller
	size[a] += size[b];
	link[b] = a; //join b and a
}
int main()
{
	ifstream fin("closing.in");
	ofstream fout("closing.out");
	int i,j,k;
	//read input
	fin >> N >> M;
	for (i=1;i<=N;i++) size[i]=1;
	for (i=1;i<=N;i++) link[i]=i;
	for (i=0;i<M;i++)
	{
		fin >> j >> k;
		adj[j].pb(k);
		adj[k].pb(j);
	}
	for (i=1;i<=N;i++) fin >> ord[i];
	//algo
	int comps = 0;
	for (i=N;i>0;i--)
	{
		comps++;
		int cur = ord[i];
		//cout << "processing: " << cur << endl; // debug
		used[cur] = true;
		for (int a : adj[cur])
		{
			if(used[a] && !same(cur, a))
			{
				//cout << cur << "->" << a << endl; //debug
				comps--;
				join(a, cur);
			}
		}
		//cout << "comps:" << comps << endl; //debug
		res[i] = comps <= 1;
	}
	for (i=1;i<=N;i++) fout << (res[i]?"YES":"NO") << endl;
	
	return 0;
}

