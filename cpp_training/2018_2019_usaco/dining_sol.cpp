// implementing sol. :(
#include <bits/stdc++.h>
using namespace std;

#define fi first
#define se second
#define mp make_pair
typedef vector<int> vi;
typedef pair<int, int> pii;
typedef long long ll;
const int INF = 1<<20;

int N, M, K;
vector<int> nbrs[100001];
map<pii, int> edgewt;

int H[100000], Y[100000];
map<int, int> dist;

void dijkstra (int source)
{
	//priority queue gives largest val by default
	set<pii> tP; //set avoids shennanigans with negative distance
	tP.insert(mp(0, source));
	while (!tP.empty())
	{
		int curr = tP.begin()->second;
		tP.erase(tP.begin());
		for (auto next: nbrs[curr])
		if (dist.count(next)==0 || dist[curr]+edgewt[mp(curr,next)]<dist[next])
		{
			dist[next] = dist[curr] + edgewt[mp(curr,next)];
			tP.insert(mp(dist[next], next));
		}
	}
}

int main()
{
	ifstream fin("dining.in");
	ofstream fout("dining.out");
	fin >> N >> M >> K;
	int i,j,k;
	for(i=0;i<M;i++)
	{
		int a,b,t;
		fin>>a>>b>>t;
		a--;b--; //indexing from 0
		nbrs[a].push_back(b); //build the graph
		nbrs[b].push_back(a);
		edgewt[mp(a,b)] = t;
		edgewt[mp(b,a)] = t;
	}
	for (i=0;i<K;i++)
	{
		fin >> H[i] >> Y[i];
		H[i]--; //indexing from 0
	}
	
	dijkstra(N-1);
	map<int, int> orig_dist = dist;
	//constructs node N with directed edges from N to each bale node
	//why doesn't node N-1 interefere with this search?
	//Node N-1 basically represents cows passing through the barn, so nothing really changes
	//We really only care about distance from node N
	for (int i=0;i<K;i++)
	{
		nbrs[N].push_back(H[i]);
		edgewt[mp(N,H[i])] = orig_dist[H[i]] - Y[i];
	}
	dist.clear();
	dijkstra(N);
	//compare distance to see if bale is favorable
	for (int i=0;i<N-1;i++) fout << (dist[i] <= orig_dist[i]) << "\n";
	return 0;
}

